package org.fkjava.hello.domain;

import javax.persistence.Entity;

import org.fkjava.workflow.domain.AbstractBusinessData;

@Entity
public class HelloWorld extends AbstractBusinessData {

	private String loginName;

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
}
