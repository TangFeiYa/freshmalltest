package com.freshmall.utils;

/**
 * 返回文件上传的消息
 * 
 * @author bruceliu
 *
 */
public class MessageKindEditor {

	private Integer error;
	private String url;
	private String message;

	public MessageKindEditor() {
		super();
	}

	public MessageKindEditor(Integer error, String url, String message) {
		super();
		this.error = error;
		this.url = url;
		this.message = message;
	}

	public Integer getError() {
		return error;
	}

	public void setError(Integer error) {
		this.error = error;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
