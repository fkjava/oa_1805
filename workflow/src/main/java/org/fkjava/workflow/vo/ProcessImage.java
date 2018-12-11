package org.fkjava.workflow.vo;

public class ProcessImage {
	// 图片名称
	private String name;
	// 图片内容
	private byte[] content;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}
}
