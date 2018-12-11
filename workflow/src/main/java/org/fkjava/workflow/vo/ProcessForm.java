package org.fkjava.workflow.vo;

import org.activiti.engine.form.FormData;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.repository.ProcessDefinition;
import org.fkjava.identity.domain.User;

// VO : 值对象，专门用于封装多个值
public class ProcessForm {
	// 流程定义
	private ProcessDefinition definition;
	// 流程实例，历史流程实例里面的信息比较完整
	private HistoricProcessInstance instance;
	// 流程的创始人（谁创建流程、谁提交流程）
	private User initialUser;
	// 表单内容
	private Object content;
	// 表单的名称
	private String formKey;
	// 表单数据，包括表单属性在里面
	private FormData formData;

	public User getInitialUser() {
		return initialUser;
	}

	public void setInitialUser(User initialUser) {
		this.initialUser = initialUser;
	}

	public HistoricProcessInstance getInstance() {
		return instance;
	}

	public void setInstance(HistoricProcessInstance instance) {
		this.instance = instance;
	}

	public ProcessDefinition getDefinition() {
		return definition;
	}

	public void setDefinition(ProcessDefinition definition) {
		this.definition = definition;
	}

	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		this.content = content;
	}

	public String getFormKey() {
		return formKey;
	}

	public void setFormKey(String formKey) {
		this.formKey = formKey;
	}

	public FormData getFormData() {
		return formData;
	}

	public void setFormData(FormData formData) {
		this.formData = formData;
	}
}
