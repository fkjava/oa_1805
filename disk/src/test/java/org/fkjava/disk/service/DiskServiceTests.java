package org.fkjava.disk.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;

import org.fkjava.common.data.domain.Result;
import org.fkjava.common.data.utils.Checksum;
import org.fkjava.disk.domain.FileItem;
import org.fkjava.disk.domain.FileItem.ItemType;
import org.fkjava.disk.domain.Quota;
import org.fkjava.disk.repository.FileItemRepository;
import org.fkjava.identity.UserHolder;
import org.fkjava.identity.domain.User;
import org.fkjava.storage.domain.FileInfo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DiskServiceTests//
		extends AbstractTransactionalJUnit4SpringContextTests {

	private Logger log = LoggerFactory.getLogger(DiskServiceTests.class);
	@Autowired
	private DiskService diskService;
	@Autowired
	private FileItemRepository fileItemRepository;

	@Before
	public void initTestData() throws IOException, URISyntaxException {
		User user = new User();
		user.setLoginName("13900139000");
		user.setName("张三疯");
		user.setPassword("1234");
		this.diskService.register(user);

		UserHolder.set(user);

		this.initFile(null, "logo_2.png");
		this.initFile(null, "logo.png");

		this.diskService.mkdir(null, "目录1");
		this.diskService.mkdir(null, "目录2");

		// 查询目录，上传文件到对应的目录中
		Pageable pageable = PageRequest.of(0, 10);
		Page<FileItem> dirs = this.fileItemRepository.findByUserAndType(user, ItemType.DIR, pageable);
		for (FileItem dir : dirs.getContent()) {
			String dirId = dir.getId();
			this.initFile(dirId, "logo.png");
			this.initFile(dirId, "logo_2.png");
		}
	}

	private void initFile(String dirId, String name) throws IOException, URISyntaxException {
		log.trace("创建测试文件: {}", name);
		URL url = this.getClass().getResource("/images/" + name);
		String fingerprint;
		try (InputStream in = url.openStream()) {
			fingerprint = new Checksum("SHA-256").sum(in);
		}

		try (InputStream in = url.openStream()) {
			File file = new File(url.toURI());
			FileInfo fileInfo = new FileInfo();
			fileInfo.setContentType("image/png");
			fileInfo.setFileSize(file.length());
			fileInfo.setName(file.getName());

			FileItem item = new FileItem();
			if (dirId != null) {
				FileItem parent = new FileItem();
				parent.setId(dirId);
				item.setParent(parent);
			} else {
				item.setParent(null);
			}
			item.setFileInfo(fileInfo);
			item.setType(ItemType.FILE);

			diskService.upload(fingerprint, item, in);
		}
	}

	/**
	 * 注册成功
	 */
	@Test
	public void registerSuccess() {
		User user = new User();
		user.setLoginName("13900139004");
		user.setName("张4疯");
		user.setPassword("1234");
		Result result = this.diskService.register(user);
		Assert.assertEquals(Result.CODE_OK, result.getCode());
		// 当前用户
		UserHolder.set(user);
	}

	/**
	 * 注册失败
	 */
	@Test
	public void registerFailed() {
		try {
			User user = new User();
			user.setLoginName("13900139000");
			user.setName("张三疯");
			user.setPassword("1234");
			this.diskService.register(user);
			Assert.fail("此处应该注册失败的，因为登录名重复");
		} catch (Exception e) {
			log.trace("注册失败的单元测试执行完成，测试通过");
		}
	}

	/**
	 * 获取限额
	 */
	@Test
	public void getQuota() {
		Quota q = this.diskService.getQuota();
		Assert.assertNotNull(q);
	}

	/**
	 * 指纹存在
	 * 
	 * @throws IOException
	 */
	@Test
	public void fingerprintExists() throws IOException {
		log.trace("测试文件指纹检查");
		// 读取一个文件，计算得到指纹，然后调用方法判断此指纹是否已经存在
		// 需要先初始化一个指纹在数据库里面
		URL url = this.getClass().getResource("/images/logo.png");
		String fingerprint;
		try (InputStream in = url.openStream()) {
			fingerprint = new Checksum("SHA-256").sum(in);
		}
		FileInfo fi = this.diskService.exists(fingerprint);
		Assert.assertNotNull(fi);
	}

	/**
	 * 指纹不存在
	 */
	@Test
	public void fingerprintNotExists() {
		FileInfo fi = this.diskService.exists("不存在的指纹");
		Assert.assertNull("随意写的文件指纹，应该无法找到对应的文件信息", fi);
	}

	/**
	 * 上传失败
	 * 
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	@Test
	public void uploadFailed() throws IOException, URISyntaxException {

		// 指纹不同的时候，上传会失败
		URL url = this.getClass().getResource("/images/logo_3.png");
		try (InputStream in = url.openStream()) {
			File file = new File(url.toURI());
			FileInfo fileInfo = new FileInfo();
			fileInfo.setContentType("image/png");
			fileInfo.setFileSize(file.length());
			fileInfo.setName(file.getName());

			FileItem item = new FileItem();
			item.setParent(null);
			item.setFileInfo(fileInfo);
			item.setType(ItemType.FILE);

			String fingerprint = "错误的指纹";
			try {
				diskService.upload(fingerprint, item, in);
				Assert.fail("在调用upload的时候，必须要出现异常才表示测试通过");
			} catch (Exception e) {
				// 这里没有问题，就是要出现异常的
				e.printStackTrace();
			}
		}
	}

	/**
	 * 上传已经存在文件，快速上传
	 * 
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	@Test
	public void uploadExistsFile() throws IOException, URISyntaxException {
		initFile(null, "logo.png");
	}

	/**
	 * 查询最近文件
	 */
	@Test
	public void testRecent() {
		Page<FileItem> page = this.diskService.findRecentFiles(null, 0);
		Assert.assertNotNull(page);
	}

	@Test
	public void testMkdir() {

		Result r1 = this.diskService.mkdir(null, "目录2");
		// 创建失败
		Assert.assertEquals(Result.CODE_ERROR, r1.getStatus());

		Result r2 = this.diskService.mkdir(null, "目录3");
		// 创建成功
		Assert.assertEquals(Result.CODE_OK, r2.getStatus());

		FileItem dir = this.fileItemRepository.findDirByName(UserHolder.get(), "目录3");

		Result r3 = this.diskService.mkdir(dir.getId(), "目录3");
		// 创建成功
		Assert.assertEquals(Result.CODE_OK, r3.getStatus());

		Result r4 = this.diskService.mkdir(dir.getId(), "目录3");
		// 创建失败
		Assert.assertEquals(Result.CODE_ERROR, r4.getStatus());
	}

	/**
	 * 查询文件
	 */
	@Test
	public void findFiles() {
		Pageable pageable = PageRequest.of(0, 10);
		Page<FileItem> dirs = this.fileItemRepository.findByUserAndType(UserHolder.get(), ItemType.DIR, pageable);
		FileItem dir = dirs.getContent().get(0);
		String dirId = dir.getId();
		Page<FileItem> items = this.diskService.findFiles(dirId, null, "fileInfo.name", "desc", 0);
		Assert.assertNotNull("必须要返回查询结果", items);
		Assert.assertTrue("查询的记录数必须大于0", items.getNumberOfElements() > 0);

		items = this.diskService.findFiles(dirId, "logo", "fileInfo.name", "desc", 0);
		Assert.assertNotNull("必须要返回查询结果", items);
		Assert.assertTrue("查询的记录数必须大于0", items.getNumberOfElements() > 0);

		items = this.diskService.findFiles(null, null, "fileInfo.name", "desc", 0);
		Assert.assertNotNull("必须要返回查询结果", items);
		Assert.assertTrue("查询的记录数必须大于0", items.getNumberOfElements() > 0);
	}
}
