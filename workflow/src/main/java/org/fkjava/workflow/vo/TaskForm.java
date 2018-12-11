package org.fkjava.workflow.vo;

import org.activiti.engine.task.Task;
import org.fkjava.workflow.domain.BusinessData;

// 列表：流程名称 流程开始时间 流程的创始人 任务名称 任务创建时间 任务过期时间
// 表单内容、表单名称、表单数据则显示任务的详情才需要使用
public class TaskForm extends ProcessForm {

	// 任务的运行时实例
	private Task task;
	// 业务数据
	private BusinessData data;

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public BusinessData getData() {
		return data;
	}

	public void setData(BusinessData data) {
		this.data = data;
	}
}
