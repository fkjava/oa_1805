package org.fkjava.hr.repository;

import org.fkjava.hr.domain.LeaveType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveTypeRepository extends JpaRepository<LeaveType, String> {

	LeaveType findByName(String name);

}
