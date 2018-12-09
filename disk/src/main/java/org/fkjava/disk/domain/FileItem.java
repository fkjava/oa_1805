package org.fkjava.disk.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.fkjava.identity.domain.User;
import org.fkjava.storage.domain.FileInfo;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "disk_file_item")
public class FileItem {

	@Id
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@GeneratedValue(generator = "uuid2")
	private String id;

	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Enumerated(EnumType.STRING)
	private ItemType type;

	@ManyToOne()
	@JoinColumn(name = "parent_id")
	private FileItem parent;

	@ManyToOne
	@JoinColumn(name = "file_info_id")
	private FileInfo fileInfo;
	@Temporal(TemporalType.TIMESTAMP)
	private Date uploadTime;

	public static enum ItemType {
		/**
		 * 文件
		 */
		FILE,
		/**
		 * 目录
		 */
		DIR
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ItemType getType() {
		return type;
	}

	public void setType(ItemType type) {
		this.type = type;
	}

	public FileItem getParent() {
		return parent;
	}

	public void setParent(FileItem parent) {
		this.parent = parent;
	}

	public FileInfo getFileInfo() {
		return fileInfo;
	}

	public void setFileInfo(FileInfo fileInfo) {
		this.fileInfo = fileInfo;
	}

	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}
}
