package org.fkjava.hr.service.impl;

import java.util.List;

import org.fkjava.common.data.domain.Result;
import org.fkjava.hr.domain.Leave;
import org.fkjava.hr.domain.LeaveType;
import org.fkjava.hr.repository.LeaveRepository;
import org.fkjava.hr.repository.LeaveTypeRepository;
import org.fkjava.hr.service.LeaveService;
import org.fkjava.workflow.repository.BusinessDataRepository;
import org.fkjava.workflow.service.impl.AbstractBusinessDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LeaveServiceImpl extends AbstractBusinessDataService<Leave> implements LeaveService {

	private Logger log = LoggerFactory.getLogger(LeaveServiceImpl.class);
	@Autowired
	private LeaveTypeRepository typeRepository;
	@Autowired
	private LeaveRepository leaveRepository;

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

	@Override
	public BusinessDataRepository<Leave> getRepository() {
		return leaveRepository;
	}

	@Override
	public String save(Leave data) {
		// 重写父类的方法，让请假和请假明细关联起来
		data.getDetails().forEach(detail -> {
			detail.setLeave(data);
		});
		return super.save(data);
	}
}
