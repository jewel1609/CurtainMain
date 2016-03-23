package com.ktds.curtain.file.vo;

import com.ktds.curtain.article.vo.ArticleVO;

public class FileVO extends ArticleVO {

	private int fileId;
	private String fileName;
	private String fileLocation;

	public int getFileId() {
		return fileId;
	}

	public void setFileId(int fileId) {
		this.fileId = fileId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileLocation() {
		return fileLocation;
	}

	public void setFileLocation(String fileLocation) {
		this.fileLocation = fileLocation;
	}

}
