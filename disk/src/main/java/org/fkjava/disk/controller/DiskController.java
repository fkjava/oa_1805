package org.fkjava.disk.controller;

import java.io.IOException;
import java.io.InputStream;

import org.fkjava.common.data.domain.Result;
import org.fkjava.disk.domain.FileItem;
import org.fkjava.disk.domain.FileItem.ItemType;
import org.fkjava.disk.domain.Quota;
import org.fkjava.disk.service.DiskService;
import org.fkjava.storage.domain.FileInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/disk")
public class DiskController {

	@Autowired
	private DiskService diskService;

	/**
	 * 获取当前用户的限额
	 * 
	 * @return
	 */
	@GetMapping("/quota")
	public Quota quota() {
		return this.diskService.getQuota();
	}

	/**
	 * 按时间倒序列出文件名称，不列出目录
	 * 
	 * @param pageNumber 页码，每页10条记录
	 * @return
	 */
	@GetMapping("/recent")
	public Page<FileItem> recent(@RequestParam(name = "keyword", required = false) String keyword,
			@RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber) {
		return this.diskService.findRecentFiles(keyword, pageNumber);
	}

	/**
	 * 以文件的指纹判断目标文件是否存在，指纹采用SHA-256算法计算整个文件内容得到
	 * 
	 * @param fingerprint 文件指纹，使用SHA-256算法计算整个文件内容得到
	 * @return 文件记录，如果没有根据指纹找到，则返回null
	 */
	@GetMapping("/exists")
	public FileInfo exists(@RequestParam("fingerprint") String fingerprint) {
		return this.diskService.exists(fingerprint);
	}

	/**
	 * 根据指纹没有找到文件信息的时候，那么就真实上传一个文件上来。<br/>
	 * 同时带上刚计算的指纹，如果服务器发现两个指纹不同，不能保存文件的！
	 * 
	 * @param fingerprint 文件指纹，使用SHA-256算法计算整个文件内容得到
	 * @param file        文件内容
	 * @return 文件记录
	 * @throws IOException
	 */
	@PostMapping(value = "/upload", consumes = "multipart/form-data")
	public FileItem upload(@RequestParam("fingerprint") String fingerprint, //
			@RequestParam(name = "parent.id", required = false) String dirId, //
			@RequestParam("file") MultipartFile file//
	) throws IOException {
		String name = file.getOriginalFilename();
		String contentType = file.getContentType();
		long fileSize = file.getSize();

		FileItem parent = new FileItem();
		parent.setId(dirId);

		FileInfo fileInfo = new FileInfo();
		fileInfo.setContentType(contentType);
		fileInfo.setFileSize(fileSize);
		fileInfo.setName(name);

		FileItem item = new FileItem();
		item.setType(ItemType.FILE);
		item.setParent(parent);
		item.setFileInfo(fileInfo);

		try (InputStream in = file.getInputStream()) {
			return this.diskService.upload(fingerprint, item, in);
		}
	}

	/**
	 * @param item
	 * @return
	 * @throws IOException
	 */
	@PostMapping(value = "/quick-upload")
	public FileItem quickUpload(FileItem item) throws IOException {
		return this.diskService.upload(item);
	}

	/**
	 * 根据条件查询文件列表，包括目录和文件，并且可以传入排序条件。默认按名称排序。
	 * 
	 * @param keyword
	 * @param pageNumber
	 * @return
	 */
	@GetMapping("/list")
	public Page<FileItem> list(@RequestParam(name = "keyword", required = false) String keyword, //
			@RequestParam(name = "orderByProperty", defaultValue = "fileInfo.name") String orderByProperty, //
			@RequestParam(name = "orderByDirection", defaultValue = "asc") String orderByDirection, //
			@RequestParam(name = "parent.id", required = false) String dirId, //
			@RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber) {
		return this.diskService.findFiles(dirId, keyword, orderByProperty, orderByDirection, pageNumber);
	}

	@PostMapping
	public Result mkdir(@RequestParam(name = "parent.id", required = false) String parentId,
			@RequestParam(name = "name") String name) {
		Result result = this.diskService.mkdir(parentId, name);
		return result;
	}
}
