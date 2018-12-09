package org.fkjava.disk.service.impl;

import java.io.InputStream;
import java.util.Date;

import org.fkjava.common.data.domain.Result;
import org.fkjava.disk.domain.FileItem;
import org.fkjava.disk.domain.FileItem.ItemType;
import org.fkjava.disk.domain.Quota;
import org.fkjava.disk.repository.FileItemRepository;
import org.fkjava.disk.repository.QuotaRepository;
import org.fkjava.disk.service.DiskService;
import org.fkjava.identity.UserHolder;
import org.fkjava.identity.domain.User;
import org.fkjava.identity.service.IdentityService;
import org.fkjava.storage.domain.FileInfo;
import org.fkjava.storage.repository.FileInfoRepository;
import org.fkjava.storage.service.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class DiskServiceImpl implements DiskService {

	private static final Logger LOG = LoggerFactory.getLogger(DiskServiceImpl.class);
	@Autowired
	private IdentityService identityService;
	@Autowired
	private QuotaRepository quotaRepository;
	@Autowired
	private FileItemRepository fileItemRepository;
	@Autowired
	private StorageService storageService;
	@Autowired
	private FileInfoRepository fileInfoRepository;

	@Override
	public Result register(User user) {
		identityService.save(user);

		Quota q = new Quota();
		// 5GB的默认空间
		q.setTotalSpaces(5 * 1024 * 1024 * 1024);
		q.setUsedSpaces(0);
		q.setUser(user);

		this.quotaRepository.save(q);

		return Result.ok("注册成功");
	}

	@Override
	public Quota getQuota() {
		User user = UserHolder.get();
		Quota q = this.quotaRepository.findByUser(user);
		return q;
	}

	@Override
	public Page<FileItem> findRecentFiles(String keyword, Integer pageNumber) {
		User user = UserHolder.get();
		Sort sort = Sort.by(Order.desc("uploadTime"));
		Pageable pageable = PageRequest.of(pageNumber, 10, sort);
		Page<FileItem> page;
		if (StringUtils.isEmpty(keyword)) {
			page = this.fileItemRepository.findByUserAndType(user, FileItem.ItemType.FILE, pageable);
		} else {
			keyword = "%" + keyword + "%";
			page = this.fileItemRepository.findByTypeAndNameLike(user, FileItem.ItemType.FILE, keyword, pageable);
		}
		return page;
	}

	@Override
	public FileInfo exists(String fingerprint) {
		return this.storageService.findByFingerprint(fingerprint);
	}

	@Override
	public FileItem upload(String fingerprint, FileItem item, InputStream in) {
		// 检查文件指纹，是否有相同的文件，如果有则直接使用之前的文件，不重新保存文件
		FileInfo fileInfo = this.storageService.findByFingerprint(fingerprint);
		if (fileInfo == null) {
			LOG.trace("文件不存在，保存新的文件，文件指纹：{}", fingerprint);
			fileInfo = this.storageService.save(item.getFileInfo(), in);
			if (!fingerprint.equals(fileInfo.getFingerprint())) {
				// 文件指纹不一致，无法上传
				// 删除文件
				this.storageService.deleteFile(item.getFileInfo().getId());
				LOG.trace("文件指纹信息不正确，上传失败，客户端计算的指纹：{}，服务端计算的指纹：{}", //
						fingerprint, fileInfo.getFingerprint());
				throw new IllegalArgumentException("文件指纹信息不正确，上传失败");
			}
		} else {
			LOG.trace("文件已经存在，不保存文件，直接关联已有文件，文件指纹：{}，已经存在的文件名：{}", fingerprint, fileInfo.getName());
		}
		item.setFileInfo(fileInfo);
		return this.upload(item);
	}

	@Override
	public FileItem upload(FileItem item) {
		item.setUser(UserHolder.get());
		return this.fileItemRepository.save(item);
	}

	@Override
	public Result mkdir(String parentId, String name) {

		User user = UserHolder.get();
		FileItem parent = null;
		if (!StringUtils.isEmpty(parentId)) {
			parent = new FileItem();
			parent.setId(parentId);
		}

		// 检查同级是否有同名目录，如果有则创建失败
		FileItem old;
		if (parent != null) {
			old = this.fileItemRepository.findDirByParentAndName(user, parent, name);
		} else {
			old = this.fileItemRepository.findDirByName(user, name);
		}
		if (old != null) {
			return Result.error("存在同名目录，创建失败");
		}

		FileInfo fileInfo = new FileInfo();
		fileInfo.setContentType("dir");
		fileInfo.setOwner(user);
		fileInfo.setName(name);
		fileInfo.setUploadTime(new Date());

		this.fileInfoRepository.save(fileInfo);

		FileItem item = new FileItem();
		item.setType(ItemType.DIR);
		item.setFileInfo(fileInfo);
		item.setUser(user);
		item.setParent(parent);

		this.fileItemRepository.save(item);

		return Result.ok("目录创建成功");
	}

	@Override
	public Page<FileItem> findFiles(String dirId, String keyword, String orderByProperty, String orderByDescription,
			Integer pageNumber) {
		return null;
	}

}
