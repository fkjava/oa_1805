package org.fkjava.hello.dao;

import org.fkjava.hello.domain.HelloWorld;
import org.fkjava.workflow.repository.BusinessDataRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HelloWorldDao extends BusinessDataRepository<HelloWorld> {

}
