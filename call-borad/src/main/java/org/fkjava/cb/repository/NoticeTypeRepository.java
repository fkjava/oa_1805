package org.fkjava.cb.repository;

import org.fkjava.cb.domain.NoticeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeTypeRepository extends JpaRepository<NoticeType, String> {

	NoticeType findByName(String name);

}
