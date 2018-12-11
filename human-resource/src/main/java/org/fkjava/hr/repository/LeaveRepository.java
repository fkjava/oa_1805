package org.fkjava.hr.repository;

import org.fkjava.hr.domain.Leave;
import org.fkjava.workflow.repository.BusinessDataRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveRepository extends BusinessDataRepository<Leave> {

}
