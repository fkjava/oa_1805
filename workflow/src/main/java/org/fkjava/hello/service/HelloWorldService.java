package org.fkjava.hello.service;

import org.fkjava.hello.dao.HelloWorldDao;
import org.fkjava.hello.domain.HelloWorld;
import org.fkjava.workflow.repository.BusinessDataRepository;
import org.fkjava.workflow.service.impl.AbstractBusinessDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HelloWorldService extends AbstractBusinessDataService<HelloWorld> {

	@Autowired
	private HelloWorldDao dao;
	
	@Override
	public BusinessDataRepository<HelloWorld> getRepository() {
		return dao;
	}
}
