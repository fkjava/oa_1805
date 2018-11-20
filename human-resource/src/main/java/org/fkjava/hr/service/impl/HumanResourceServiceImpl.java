package org.fkjava.hr.service.impl;

import java.util.List;

import org.fkjava.hr.domain.Department;
import org.fkjava.hr.domain.Employee;
import org.fkjava.hr.repository.DepartmentRepository;
import org.fkjava.hr.repository.EmployeeRepository;
import org.fkjava.hr.service.HumanResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class HumanResourceServiceImpl implements HumanResourceService {

	@Autowired
	private DepartmentRepository departmentRepository;
	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public void save(Department department) {
		if (department.getParent() != null && StringUtils.isEmpty(department.getParent().getId())) {
			department.setParent(null);
		}
		if (StringUtils.isEmpty(department.getId())) {
			department.setId(null);
		}

		// 1.同级部门，不能重名
		Department old;
		if (department.getParent() != null) {
			// 有上级
			old = this.departmentRepository.findByParentAndName(department.getParent(), department.getName());
		} else {
			// 没有上级
			old = this.departmentRepository.findByNameAndParentNull(department.getName());
		}

		if (old != null && !old.getId().equals(department.getId())) {
			// ID不相同，表示重名
			throw new IllegalArgumentException("部门的名称不能重复");
		}

		// 2.设置部门经理为当前部门的员工
		if (department.getManager() != null //
				&& department.getManager().getUser() != null //
				&& !StringUtils.isEmpty(department.getManager().getUser().getId())) {
			Employee employee = department.getManager();
			Employee oldEmmployee = this.employeeRepository.findByUser(employee.getUser());
			if (oldEmmployee == null) {
				// 没有User对应的员工，意味着用户还不是员工
				// 此时应该新增一个员工
				employee = this.employeeRepository.save(employee);
			} else {
				// 原本有员工
				employee = oldEmmployee;
			}
			employee.setDepartment(department);
			department.setManager(employee);
		} else {
			// 没有选择部门经理
			// 在后面的其他业务处理的过程，如果一定需要部门经理处理，那么就要求使用独立的一个表来记录【委托】，把部门经理的职责委托给其他的用户
			// 委托的用户，不一定是当前部门的
			// 现在这里不处理委托
			department.setManager(null);
		}

		// 3.排序的序号查询、处理

		if (old != null) {
			department.setNumber(old.getNumber());
		} else {
			Double maxNumber;
			if (department.getParent() == null) {
				maxNumber = this.departmentRepository.findMaxNumberByParentNull();
			} else {
				maxNumber = this.departmentRepository.findMaxNumberByParent(department.getParent());
			}
			if (maxNumber == null) {
				maxNumber = 0.0;
			}
			Double number = maxNumber + 10000000.0;
			department.setNumber(number);
		}

		this.departmentRepository.save(department);
	}

	@Override
	public List<Department> findTopDepartments() {
		return this.departmentRepository.findByParentNullOrderByNumber();
	}
}
