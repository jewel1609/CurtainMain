package com.ktds.curtain.articleLike.vo;

public class ArticleLikeVO {
	
	private int articleLikeId;
	private int articleId;
	private String studentEmail;
	private String likeDate;
	private int boradId;
	
	public int getBoradId() {
		return boradId;
	}
	public void setBoradId(int boradId) {
		this.boradId = boradId;
	}
	public int getArticleLikeId() {
		return articleLikeId;
	}
	public void setArticleLikeId(int articleLikeId) {
		this.articleLikeId = articleLikeId;
	}
	public int getArticleId() {
		return articleId;
	}
	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}
	public String getStudentEmail() {
		return studentEmail;
	}
	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}
	public String getLikeDate() {
		return likeDate;
	}
	public void setLikeDate(String likeDate) {
		this.likeDate = likeDate;
	}

}
