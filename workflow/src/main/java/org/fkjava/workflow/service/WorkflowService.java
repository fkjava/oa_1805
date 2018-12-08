package org.fkjava.workflow.service;

import java.io.InputStream;
import java.util.Map;

import org.activiti.engine.repository.ProcessDefinition;
import org.fkjava.common.data.domain.Result;
import org.fkjava.workflow.vo.ProcessForm;
import org.springframework.data.domain.Page;

public interface WorkflowService {

	// 文件上传以后，会得到两个主要的信息：文件名、文件内容（InputStream）
	// 一般上传文件的时候，都直接使用ZIP格式压缩，所以部署的时候需要使用ZipInputStream解压缩。
	Result deploy(String name, InputStream in);

	Page<ProcessDefinition> findDefinitions(String keyword, int pageNumber);

	void disableProcessDefinition(String processDefinitionId);

	ProcessDefinition findDefnitionById(String processDefinitionId);

	void activeProcessDefinition(String processDefinitionId);

	ProcessForm findDefnitionByKey(String key);

	Result start(String processDefinitionId, Map<String, String[]> params);
}
