package org.fkjava.workflow.domain;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.fkjava.identity.domain.User;
import org.hibernate.annotations.GenericGenerator;

@MappedSuperclass
public abstract class AbstractBusinessData implements BusinessData {

	@Id
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@GeneratedValue(generator = "uuid2")
	private String id;
	@ManyToOne()
	@JoinColumn(name = "user_id")
	private User user;
	@Temporal(TemporalType.TIMESTAMP)
	private Date submitTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}
}
