package com.ktds.curtain.vo;

public class ArticleVO {
	
	private String articleId;
	private int articleNumber;
	private String subject;
	private String description;
	private String writer;
	private String createdDate;
	private String modifiedDate;
	
	public String getArticleId() {
		return articleId;
	}
	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}
	public int getArticleNumber() {
		return articleNumber;
	}
	public void setArticleNumber(int articleNumber) {
		this.articleNumber = articleNumber;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

}
