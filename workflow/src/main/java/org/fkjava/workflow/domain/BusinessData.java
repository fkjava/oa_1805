package org.fkjava.workflow.domain;

import java.util.Date;

import org.fkjava.identity.domain.User;

public interface BusinessData {

	/**
	 * 获取业务数据的ID的值
	 * 
	 * @return
	 */
	String getId();

	/**
	 * 谁提交的、新增的业务数据
	 * 
	 * @return
	 */
	User getUser();

	/**
	 * 什么时候提交的业务数据
	 * 
	 * @return
	 */
	Date getSubmitTime();

	/**
	 * 在统一保存业务数据的时候，用于把当前用户设置进来
	 * 
	 * @param user
	 */
	void setUser(User user);

	/**
	 * 保存之前，设置当前时间
	 * 
	 * @param submitTime
	 */
	void setSubmitTime(Date submitTime);
}
