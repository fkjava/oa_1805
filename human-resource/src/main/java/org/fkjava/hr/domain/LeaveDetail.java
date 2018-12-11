package org.fkjava.hr.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.fkjava.common.data.converters.DateTimeToStringConverter;
import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "hrm_leave_detail")
public class LeaveDetail {
	@Id
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@GeneratedValue(generator = "uuid2")
	private String id;
	@ManyToOne()
	@JoinColumn(name = "type_id")
	private LeaveType type;

	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(converter = DateTimeToStringConverter.class)
	private Date startTime;

	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(converter = DateTimeToStringConverter.class)
	private Date endTime;

	private double leaveHours;
	@ManyToOne
	@JoinColumn(name = "leave_id")
	@JsonIgnore
	private Leave leave;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public LeaveType getType() {
		return type;
	}

	public void setType(LeaveType type) {
		this.type = type;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public double getLeaveHours() {
		return leaveHours;
	}

	public void setLeaveHours(double leaveHours) {
		this.leaveHours = leaveHours;
	}

	public Leave getLeave() {
		return leave;
	}

	public void setLeave(Leave leave) {
		this.leave = leave;
	}
}
