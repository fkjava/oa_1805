package org.fkjava.disk.service;

import java.io.InputStream;

import org.fkjava.common.data.domain.Result;
import org.fkjava.disk.domain.FileItem;
import org.fkjava.disk.domain.Quota;
import org.fkjava.identity.domain.User;
import org.fkjava.storage.domain.FileInfo;
import org.springframework.data.domain.Page;

public interface DiskService {

	/**
	 * 用户注册，注册的时候，会自动分配限额
	 * 
	 * @param user 最少要求提供姓名、手机号码
	 * @return 返回注册结果
	 */
	Result register(User user);

	Quota getQuota();

	Page<FileItem> findRecentFiles(String keyword, Integer pageNumber);

	FileInfo exists(String fingerprint);

	/**
	 * 一般上传，文件还不存在的时候使用
	 * 
	 * @param fingerprint
	 * @param item
	 * @param in
	 * @return
	 */
	FileItem upload(String fingerprint, FileItem item, InputStream in);

	/**
	 * 快速上传，文件已经存在的情况下使用
	 * 
	 * @param item
	 * @return
	 */
	FileItem upload(FileItem item);

	Page<FileItem> findFiles(String dirId, String keyword, String orderByProperty, String orderByDescription,
			Integer pageNumber);

	Result mkdir(String parentId, String name);
}
