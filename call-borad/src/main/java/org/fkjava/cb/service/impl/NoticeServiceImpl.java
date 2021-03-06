package org.fkjava.cb.service.impl;

import java.util.Date;
import java.util.List;

import org.fkjava.cb.domain.Notice;
import org.fkjava.cb.domain.Notice.Status;
import org.fkjava.cb.domain.NoticeRead;
import org.fkjava.cb.domain.NoticeType;
import org.fkjava.cb.repository.NoticeReadRepository;
import org.fkjava.cb.repository.NoticeRepository;
import org.fkjava.cb.repository.NoticeTypeRepository;
import org.fkjava.cb.service.NoticeService;
import org.fkjava.identity.UserHolder;
import org.fkjava.identity.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	private NoticeTypeRepository typeRepository;
	@Autowired
	private NoticeRepository noticeRepository;
	@Autowired
	private NoticeReadRepository noticeReadRepository;

	@Override
	public List<NoticeType> findAllTypes() {
		Sort sort = Sort.by("name");

		return this.typeRepository.findAll(sort);
	}

	@Override
	public void save(NoticeType type) {
		NoticeType old = this.typeRepository.findByName(type.getName());
		if (old == null || old.getId().equals(type.getId())) {
			// 要么新的、要么修改的
			this.typeRepository.save(type);
		} else {
			throw new IllegalArgumentException("公告类型的名称不能重复！");
		}
	}

	@Override
	public void deleteTypeById(String id) {
		this.typeRepository.deleteById(id);
	}

	@Override
	public void write(Notice notice) {
		// 1.填充字段
		notice.setAuthor(UserHolder.get());
		notice.setWriteTime(new Date());
		notice.setReleaseTime(null);
		notice.setStatus(Status.DRAFT);

		if (StringUtils.isEmpty(notice.getId())) {
			notice.setId(null);
		}

		// 2.保存公告
		this.noticeRepository.save(notice);
	}

	@Override
	public Page<NoticeRead> findNotices(Integer number, String keyword) {
		// 标题、撰写时间、作者都可以非常方便查询出来，内容在列表显示的时候不关心
		// 状态：草稿、已发布、已撤回
		// 还有一个特殊状态：不同的用户有阅读状态，没有阅读、并且已经发布的，使用粗体字显示
		// 还未发布的，只能修改；已撤回、已发布不能再修改。

		// 要查询的列表数据包括：
		// 1.当前用户自己写的、还未发布的
		// 2.已经发布、可以阅读，需要查询阅读状态，表关联查询
		// 3.已经撤回的，只有作者能够查看
		User author = UserHolder.get();
		Pageable pageable = PageRequest.of(number, 10);
		// Page<Notice> page = this.noticeRepository.findNotices(author, pageable);

		// 如果没有阅读记录，那么也会有公告记录
		Page<NoticeRead> dataPage = this.noticeReadRepository.findNotices(author, author, pageable);
		List<NoticeRead> content = dataPage.getContent();

		Page<NoticeRead> page = new PageImpl<>(content, pageable, dataPage.getTotalElements());

		return page;
	}

	@Override
	public Notice findById(String id) {
		return this.noticeRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void recall(String id) {
		Notice n = this.findById(id);
		if (n != null) {
			n.setStatus(Status.RECALL);
		}
	}

	@Override
	public void deleteById(String id) {
		Notice n = this.findById(id);
		if (n != null) {
			this.noticeRepository.delete(n);
		}
	}

	@Override
	@Transactional
	public void publish(String id) {
		Notice n = this.findById(id);
		if (n != null) {
			n.setStatus(Status.RELEASED);
			n.setReleaseTime(new Date());
		}
	}

	@Override
	@Transactional
	public void read(String id) {
		User user = UserHolder.get();
		Notice notice = this.findById(id);
		Date readTime = new Date();

		NoticeRead old = this.noticeReadRepository.findByNoticeAndUser(notice, user);
		if (old == null) {
			NoticeRead nr = new NoticeRead();
			nr.setNotice(notice);
			nr.setReadTime(readTime);
			nr.setUser(user);

			this.noticeReadRepository.save(nr);
		}
	}
}
