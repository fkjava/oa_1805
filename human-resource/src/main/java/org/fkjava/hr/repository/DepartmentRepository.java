package org.fkjava.hr.repository;

import java.util.List;

import org.fkjava.hr.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, String> {

	Department findByNameAndParentNull(String name);

	Department findByParentAndName(Department parent, String name);

	@Query("select max(number) from Department where parent is null")
	Double findMaxNumberByParentNull();

	// 如果不使用参数注解，那么就需要?1的方式来传参
	@Query("select max(number) from Department where parent = :parent")
	Double findMaxNumberByParent(@Param("parent") Department parent);

	List<Department> findByParentNullOrderByNumber();

}
