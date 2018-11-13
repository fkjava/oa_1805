package org.fkjava.menu.service.impl;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.fkjava.common.data.domain.Result;
import org.fkjava.identity.UserHolder;
import org.fkjava.identity.domain.Role;
import org.fkjava.identity.domain.User;
import org.fkjava.identity.repository.RoleRepository;
import org.fkjava.identity.repository.UserDao;
import org.fkjava.menu.domain.Menu;
import org.fkjava.menu.repository.MenuRepository;
import org.fkjava.menu.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private MenuRepository menuRepository;
	@Autowired
	private UserDao userDao;

	@Override
	public void save(Menu menu) {
		if (StringUtils.isEmpty(menu.getId())) {
			menu.setId(null);
		}
		if (menu.getParent() != null && StringUtils.isEmpty(menu.getParent().getId())) {
			// 上级菜单的id为null，表示没有上级菜单
			menu.setParent(null);
		}

		// 1.检查相同的父菜单里面，是否有同名的子菜单
		// 比如系统管理下面，只能有一个【用户管理】
		Menu old;
		if (menu.getParent() != null) {
			// 有上级菜单，根据上级菜单检查是否有重复
			old = this.menuRepository.findByNameAndParent(menu.getName(), menu.getParent());
		} else {
			// 如果没有上级，则直接找parent_id为null的，检查是否有重复
			old = this.menuRepository.findByNameAndParentNull(menu.getName());
		}

		if (old != null && !old.getId().equals(menu.getId())) {
			// 根据名称查询到数据库里面的菜单，但是两者的id不同
			throw new IllegalArgumentException("菜单的名字不能重复");
		}

		// 2.根据选取的角色ID，查询角色，解决角色的KEY重复的问题
		List<String> rolesIds = new LinkedList<>();
		if (menu.getRoles() == null) {
			menu.setRoles(new LinkedList<>());
		}
		menu.getRoles().forEach(role -> rolesIds.add(role.getId()));
		// 查询数据库里面的所有Role
		List<Role> roles = this.roleRepository.findAllById(rolesIds);// 此处查询出来，绝对不会有重复记录
		Set<Role> set = new HashSet<>();
		set.addAll(roles);// 去重

		menu.getRoles().clear();
		menu.getRoles().addAll(set);

		// 3.设置排序的序号（菜单可以拖动顺序）
		// 找到同级最大的number，然后加10000000，就形成一个新的number作为当前菜单的number
		// 如果是修改，则不需要查询
		if (old != null) {
			menu.setNumber(old.getNumber());
		} else {
			Double maxNumber;
			if (menu.getParent() == null) {
				maxNumber = this.menuRepository.findMaxNumberByParentNull();
			} else {
				maxNumber = this.menuRepository.findMaxNumberByParent(menu.getParent());
			}
			if (maxNumber == null) {
				maxNumber = 0.0;
			}
			Double number = maxNumber + 10000000.0;
			menu.setNumber(number);
		}

		// 4.保存数据
		this.menuRepository.save(menu);
	}

	@Override
	public List<Menu> findTopMenus() {
		return this.menuRepository.findByParentNullOrderByNumber();
	}

	@Override
	@Transactional
	public Result move(String id, String targetId, String moveType) {
		Menu menu = this.menuRepository.findById(id).orElse(null);

		// 移动的重点：重新计算number（排序号），并且要修改parent

		if (StringUtils.isEmpty(targetId)) {
			// 一定是移动到所有一级菜单的最后面
			Double maxNumber = this.menuRepository.findMaxNumberByParentNull();
			if (maxNumber == null) {
				maxNumber = 0.0;
			}
			Double number = maxNumber + 10000000.0;
			menu.setNumber(number);
			menu.setParent(null);

			return Result.ok();
		}

		Menu target = this.menuRepository.findById(targetId).orElse(null);
		if ("inner".equals(moveType)) {
			// 把menu移动到target里面，此时menu的parent直接改为target即可
			// number则是根据target作为父菜单，找到最大的number，然后加上一个数字

			Double maxNumber = this.menuRepository.findMaxNumberByParent(target);
			if (maxNumber == null) {
				maxNumber = 0.0;
			}
			Double number = maxNumber + 10000000.0;

			menu.setParent(target);
			menu.setNumber(number);
		} else if ("prev".equals(moveType)) {

			// number应该小于target的number，并且大于target前一个菜单的number
			Pageable pageable = PageRequest.of(0, 1);// 查询第一页、只要1条数据
			Page<Menu> prevs = this.menuRepository//
					.findByParentAndNumberLessThanOrderByNumberDesc(target.getParent(), target.getNumber(), pageable);

			Double next = target.getNumber();
			Double number;
			if (prevs.getNumberOfElements() > 0) {
				Double prev = prevs.getContent().get(0).getNumber();
				number = (next + prev) / 2;
			} else {
				number = next / 2;
			}
			menu.setNumber(number);
			// 移动到target之前，跟target同级
			menu.setParent(target.getParent());
		} else if ("next".equals(moveType)) {

			// number应该大于target的number，并且小于target后一个菜单的number
			Pageable pageable = PageRequest.of(0, 1);// 查询第一页、只要1条数据
			Page<Menu> prevs = this.menuRepository//
					.findByParentAndNumberGreaterThanOrderByNumberAsc(target.getParent(), target.getNumber(), pageable);

			Double prev = target.getNumber();
			Double number;
			if (prevs.getNumberOfElements() > 0) {
				Double next = prevs.getContent().get(0).getNumber();
				number = (next + prev) / 2;
			} else {
				number = prev + 10000000.0;
			}
			menu.setNumber(number);
			// 移动到target之后，跟target同级
			menu.setParent(target.getParent());
		} else {
			throw new IllegalArgumentException("非法的菜单移动类型，只允许inner、prev、next三选一。");
		}

		return Result.ok();
	}

	@Override
	public Result delete(String id) {
		// 直接调用deleteById的方法，在数据库没有对应的记录时会抛出异常
//		this.menuRepository.deleteById(id);

		// 当对象不存在，会先插入一条，然后再删除！
//		Menu entity = new Menu();
//		entity.setId(id);
//		this.menuRepository.delete(entity);

		Menu entity = this.menuRepository.findById(id).orElse(null);
		if (entity != null) {
			if (entity.getChilds().isEmpty()) {
				this.menuRepository.delete(entity);
			} else {
				return Result.error();
			}
		}

		return Result.ok();
	}

	@Override
	public List<Menu> findMyMenus() {
		// 从线程里面获取的User，不是持久化对象
		User user = UserHolder.get();
		// 从数据库查询持久化的User
		user = userDao.getOne(user.getId());
		// 得到所有的角色
		List<Role> roles = user.getRoles();

		// 根据roles集合，查询所有的菜单，得到用户有权限访问的菜单
		// 此时得到的菜单，可能包括一级、二级、三级……等所有可能存在的等级
		// 左侧的菜单树，其实只要两级（一级、二级）即可，并且应该返回一级、在一级里面包含二级
		// 1.根据角色查询菜单
		List<Menu> menus = this.menuRepository.findByRolesIn(roles);// 使用in查询

		// 准备返回的一级菜单集合
		List<Menu> topMenus = new LinkedList<>();
		// 找到所有的一级菜单，放入topMenus
		// 循环所有的二级、三级……菜单，以parent为key放入map里面，value是一个集合，表示parent对应所有有权的下一级
		Map<Menu, List<Menu>> map = new HashMap<>();
		// 此循环结束以后，Map里面的数据，包括所有上级、下级的菜单，并且全部都是有权的
		// 2.建立菜单和下级菜单的对应关系
		menus.stream()//
				.filter(menu -> menu.getParent() != null)//
				.forEach(menu -> {
					// 得到一级（上级）
					Menu parent = menu.getParent();
					if (!map.containsKey(parent)) {
						// 在Map里面没有包含此上级
						map.put(parent, new LinkedList<>());
					}
					// 获取到parent对应所有的下级菜单
					List<Menu> childs = map.get(parent);
					childs.add(menu);// 把当前菜单加入对应的上级菜单里面
				});

		// 在内存里面，对菜单进行排序，以序号为排序的依据
		Comparator<Menu> comparator = (menu1, menu2) -> {
			if (menu1.getNumber() > menu2.getNumber()) {
				return 1;
			} else if (menu1.getNumber() < menu2.getNumber()) {
				return -1;
			} else {
				return 0;
			}
		};

		// 3.构建新的一级菜单，并且把二级菜单放入一级菜单里面
		map.entrySet().stream()//
				// 判断key没有上级的菜单，表示一级菜单
				.filter(entry -> entry.getKey().getParent() == null)//
				.forEach(entry -> {
					// 一级
					Menu parent = entry.getKey();
					// 二级
					List<Menu> 二级菜单 = entry.getValue();

					// 查询得到的是持久化对象，不要修改它们
					// 返回菜单的时候，由于需要通过权限组装数据，所以是需要修改Menu对象的
					// 因此创建一个新的Menu，避免被意外修改
					Menu menu = this.copy(parent);// 一级

					二级菜单.forEach(child -> {
						Menu subMenu = this.copy(child);// 二级

						menu.getChilds().add(subMenu);
					});
					// 排序二级菜单
					menu.getChilds().sort(comparator);

					// 把一级菜单加入返回的集合里面
					topMenus.add(menu);
				});
		// 排序一级菜单
		topMenus.sort(comparator);

		return topMenus;
	}

	/**
	 * 传入持久化对象，返回瞬态对象
	 * 
	 * @param persist 数据库查询得到的
	 * @return 瞬态的
	 */
	private Menu copy(Menu persist) {
		Menu menu = new Menu();// 瞬态的对象
		menu.setId(persist.getId());
		menu.setName(persist.getName());
		menu.setNumber(persist.getNumber());
		menu.setUrl(persist.getUrl());
		menu.setChilds(new LinkedList<>());

		return menu;
	}
}
