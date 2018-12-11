package org.fkjava.hr.service.impl;

import java.util.List;

import org.fkjava.common.data.domain.Result;
import org.fkjava.hr.domain.LeaveType;
import org.fkjava.hr.repository.LeaveTypeRepository;
import org.fkjava.hr.service.LeaveService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LeaveServiceImpl implements LeaveService {

	private Logger log = LoggerFactory.getLogger(LeaveServiceImpl.class);
	@Autowired
	private LeaveTypeRepository typeRepository;

	@Override
	public Result save(LeaveType type) {
		LeaveType old = this.typeRepository.findByName(type.getName());
		if (old == null || old.getId().equals(type.getId())) {
			this.typeRepository.save(type);
			return Result.ok("请假类型保存成功");
		}
		return Result.error("请假类型保存失败");
	}

	@Override
	public Result deleteType(String id) {
		try {
			this.typeRepository.deleteById(id);
			return Result.ok("请假类型删除成功");
		} catch (Exception e) {
			log.trace("删除请假类型失败：" + e.getLocalizedMessage(), e);
		}
		return Result.error("请假类型删除失败");
	}

	@Override
	public List<LeaveType> findAllTypes() {
		return this.typeRepository.findAll();
	}
}
