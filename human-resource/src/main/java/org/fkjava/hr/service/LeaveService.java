package org.fkjava.hr.service;

import java.util.List;

import org.fkjava.common.data.domain.Result;
import org.fkjava.hr.domain.LeaveType;

public interface LeaveService {

	Result save(LeaveType type);

	Result deleteType(String id);

	List<LeaveType> findAllTypes();
}
