package org.fkjava.hr.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.fkjava.workflow.domain.AbstractBusinessData;

// 请假单，里面包含多个请假的明细
@Entity
@Table(name = "hrm_leave")
public class Leave extends AbstractBusinessData {

	// 通用属性这里都不需要考虑，继承已经得到了通用属性
	// 所以只需要考虑一对多关系即可
	@OneToMany(mappedBy = "leave", cascade = CascadeType.ALL)
	private List<LeaveDetail> details;

	public List<LeaveDetail> getDetails() {
		return details;
	}

	public void setDetails(List<LeaveDetail> details) {
		this.details = details;
	}
}
