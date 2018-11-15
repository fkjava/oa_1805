package org.fkjava.cb.service.impl;

import java.util.List;

import org.fkjava.cb.domain.NoticeType;
import org.fkjava.cb.repository.NoticeTypeRepository;
import org.fkjava.cb.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	private NoticeTypeRepository typeRepository;

	@Override
	public List<NoticeType> findAllTypes() {
		Sort sort = Sort.by("name");

		return this.typeRepository.findAll(sort);
	}

	@Override
	public void save(NoticeType type) {
		NoticeType old = this.typeRepository.findByName(type.getName());
		if (old == null || old.getId().equals(type.getId())) {
			// 要么新的、要么修改的
			this.typeRepository.save(type);
		} else {
			throw new IllegalArgumentException("公告类型的名称不能重复！");
		}
	}

	@Override
	public void deleteTypeById(String id) {
		this.typeRepository.deleteById(id);
	}
}
