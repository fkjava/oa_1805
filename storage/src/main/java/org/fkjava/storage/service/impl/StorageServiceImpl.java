package org.fkjava.storage.service.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.UUID;

import org.fkjava.common.data.domain.Result;
import org.fkjava.common.data.utils.Checksum;
import org.fkjava.identity.UserHolder;
import org.fkjava.identity.domain.User;
import org.fkjava.storage.domain.FileInfo;
import org.fkjava.storage.repository.FileInfoRepository;
import org.fkjava.storage.service.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@ConfigurationProperties(prefix = "fkjava.storage")
public class StorageServiceImpl implements StorageService {

	private static final Logger LOG = LoggerFactory.getLogger(StorageServiceImpl.class);

	// 使用普通Spring项目的时候，通过PropertyPlaceHolder方法加载文件的时候，可以利用@Value注入属性的值
	// 冒号后面不能有空格，最好前面也不要有
	// 冒号后面表示【默认值】
	// @Value(value = "${fkjava.storage.dir?:/tmp/storage}")

	// 在Spring Boot，配合@ConfigurationProperties注解和set方法，即可得到application.yaml文件中的参数
	// 等号后面的初始值是在没有配置参数的时候使用的默认值
	private String dir = "/tmp/storage";

	@Autowired
	private FileInfoRepository fileInfoRepository;

	public void setDir(String dir) {
		this.dir = dir;
	}

	@Override
	public FileInfo save(FileInfo info, InputStream in) {
		// 保存文件
		String path = UUID.randomUUID().toString();
		File file = new File(dir, path);
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		LOG.trace("文件的实际存储路径： {}", file.getAbsolutePath());
		// Path target = file.toPath();
		Checksum.Builder builder = Checksum.builder("SHA-256");
		try (BufferedInputStream bis = new BufferedInputStream(in);
				FileOutputStream fos = new FileOutputStream(file);
				BufferedOutputStream bos = new BufferedOutputStream(fos)) {
			// Files.copy(in, target);
			byte[] b = new byte[1024];
			for (int len = bis.read(b); len != -1; len = bis.read(b)) {
				// 写出数据到文件
				bos.write(b, 0, len);
				// 更新数据摘要
				builder.update(b, 0, len);
			}
		} catch (IOException e) {
			throw new RuntimeException("保存文件到硬盘失败：" + e.getLocalizedMessage(), e);
		}
		// 计算文件指纹
		String fingerprint = builder.sum();

		info.setFingerprint(fingerprint);

		// 保存文件信息
		info.setOwner(UserHolder.get());
		info.setUploadTime(new Date());
		info.setPath(path);

		FileInfo fi = this.fileInfoRepository.save(info);
		// 为了把id返回给控制器使用
		info.setId(fi.getId());
		return fi;
	}

	@Override
	public Page<FileInfo> findFiles(String keyword, Integer number) {
		// 只查询当前用户的文件
		User user = UserHolder.get();
		if (StringUtils.isEmpty(keyword)) {
			keyword = null;
		}

		Pageable pageable = PageRequest.of(number, 10);
		Page<FileInfo> page;
		if (keyword == null) {
			page = this.fileInfoRepository.findByOwner(user, pageable);
		} else {
			page = this.fileInfoRepository.findByOwnerAndNameContaining(user, keyword, pageable);
		}

		return page;
	}

	@Override
	public FileInfo findById(String id) {
		return this.fileInfoRepository.findById(id).orElse(null);
	}

	@Override
	public InputStream getFileContent(FileInfo fi) throws FileNotFoundException {
		try {
			File file = new File(dir, fi.getPath());
			FileInputStream inputStream = new FileInputStream(file);
			return inputStream;
		} catch (FileNotFoundException e) {
			LOG.trace("文件没有找到：" + e.getLocalizedMessage(), e);
			return null;
		}
	}

	@Override
	public Result deleteFile(String id) {
		// 1.根据id获取文件信息
		FileInfo info = this.fileInfoRepository.findById(id).orElse(null);
		if (info != null) {
			// 2.删除硬盘上的文件
			File file = new File(dir, info.getPath());
			file.delete();
			// 3.删除文件信息
			this.fileInfoRepository.delete(info);
		}
		return null;
	}

	@Override
	public FileInfo findByFingerprint(String fingerprint) {
		return this.fileInfoRepository.findByFingerprint(fingerprint);
	}
}
