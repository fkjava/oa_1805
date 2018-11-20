package org.fkjava.hr.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "hr_dept")
public class Department implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 跟菜单通用的属性，可以写成一个Tree类作为通用父类，用继承映射
	@Id
	@Column(length = 36)
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	private String id;
	private String name;

	@ManyToOne()
	@JoinColumn(name = "parent_id")
	private Department parent;

	@OneToMany(mappedBy = "parent")
	@JsonProperty("children")
	@OrderBy("number")
	private List<Department> childs;

	private Double number;

	// 经理，必须是员工；员工必须是用户。
	@OneToOne()
	@JoinColumn(name = "manager_id")
	private Employee manager;

	// 部门里面的员工，关系由Employee来维护
	@OneToMany(mappedBy = "department")
	@JsonIgnore
	private List<Employee> employees;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Employee getManager() {
		return manager;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public Department getParent() {
		return parent;
	}

	public void setParent(Department parent) {
		this.parent = parent;
	}

	public List<Department> getChilds() {
		return childs;
	}

	public void setChilds(List<Department> childs) {
		this.childs = childs;
	}

	public Double getNumber() {
		return number;
	}

	public void setNumber(Double number) {
		this.number = number;
	}
}
