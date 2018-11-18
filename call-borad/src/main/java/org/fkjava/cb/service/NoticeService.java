package org.fkjava.cb.service;

import java.util.List;

import org.fkjava.cb.domain.Notice;
import org.fkjava.cb.domain.NoticeType;
import org.springframework.data.domain.Page;

public interface NoticeService {

	List<NoticeType> findAllTypes();

	void save(NoticeType type);

	void deleteTypeById(String id);

	void write(Notice notice);

	Page<Notice> findNotices(Integer number, String keyword);

}
