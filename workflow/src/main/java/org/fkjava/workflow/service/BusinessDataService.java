package org.fkjava.workflow.service;

import org.fkjava.workflow.domain.BusinessData;
import org.fkjava.workflow.repository.BusinessDataRepository;

/**
 * 限定能够处理的数据类型必须是BusinessData的子类（实现类）
 * 
 * @author lwq
 *
 * @param <T>
 */
public interface BusinessDataService<T extends BusinessData> {

	/**
	 * 由实现类提供，表示得到实体类的操作接口
	 * 
	 * @return
	 */
	BusinessDataRepository<T> getRepository();

	/**
	 * 标准的、简单保存方法，里面没有任何的业务逻辑
	 * 
	 * @param data
	 * @return
	 */
	default String save(T data) {
		data = this.getRepository().save(data);
		return data.getId();
	}

	/**
	 * 
	 * @param id 业务数据对象的id
	 * @return 如果没有找到对象，返回null
	 */
	default T get(String id) {
		return this.getRepository().findById(id).orElse(null);
	}
}
