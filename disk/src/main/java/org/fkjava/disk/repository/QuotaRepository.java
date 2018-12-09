package org.fkjava.disk.repository;

import org.fkjava.disk.domain.Quota;
import org.fkjava.identity.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuotaRepository extends JpaRepository<Quota, String> {

	Quota findByUser(User user);

}
