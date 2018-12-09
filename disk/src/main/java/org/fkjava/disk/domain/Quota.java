package org.fkjava.disk.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.fkjava.identity.domain.User;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "disk_quota")
public class Quota {

	@Id
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@GeneratedValue(generator = "uuid2")
	private String id;

	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;
	/**
	 * 总空间大小
	 */
	private long totalSpaces;
	/**
	 * 已用空间大小，每次上传成功一个文件，就会更新此字段的值
	 */
	private long usedSpaces;

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

	public long getTotalSpaces() {
		return totalSpaces;
	}

	public void setTotalSpaces(long totalSpaces) {
		this.totalSpaces = totalSpaces;
	}

	public long getUsedSpaces() {
		return usedSpaces;
	}

	public void setUsedSpaces(long usedSpaces) {
		this.usedSpaces = usedSpaces;
	}
}
