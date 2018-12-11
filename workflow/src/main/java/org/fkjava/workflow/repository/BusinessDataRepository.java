package org.fkjava.workflow.repository;

import org.fkjava.workflow.domain.BusinessData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * 限定：BusinessDataRepository只能操作BusinessData的子类对象，并且主键类型必须String。
 * 
 * @author lwq
 *
 * @param <T> 实际要传入的业务数据类型，不能是抽象类。
 */
@NoRepositoryBean // 用于被继承，自己本身不要产生实例
public interface BusinessDataRepository<T extends BusinessData> extends JpaRepository<T, String> {

}
