package org.fkjava.cb.service;

import java.util.List;

import org.fkjava.cb.domain.Notice;
import org.fkjava.cb.domain.NoticeRead;
import org.fkjava.cb.domain.NoticeType;
import org.springframework.data.domain.Page;

public interface NoticeService {

	List<NoticeType> findAllTypes();

	void save(NoticeType type);

	void deleteTypeById(String id);

	void write(Notice notice);

	Page<NoticeRead> findNotices(Integer number, String keyword);

	Notice findById(String id);

	void recall(String id);

	void deleteById(String id);

	void publish(String id);

	void read(String id);

}
