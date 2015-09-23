package com.hz.wechatproject.pojo;

public class ModelSystemFilesPOJO {
	public static Integer FILE_TYPE_FILE=1;
	public static Integer FILE_TYPE_DIR=2;
	
	private String fileName;
	private Integer type;
	private String filePath;
	
	private String content;
	
	public ModelSystemFilesPOJO(){
		
	}

	public ModelSystemFilesPOJO(String fileName, Integer type, String filePath) {
		super();
		this.fileName = fileName;
		this.type = type;
		this.filePath = filePath;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	@Override
	public String toString() {
		return "ModelSystemFilesPOJO [fileName=" + fileName + ", type=" + type
				+ ", filePath=" + filePath + "]";
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	
}
