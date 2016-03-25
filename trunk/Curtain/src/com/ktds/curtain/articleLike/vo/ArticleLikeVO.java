package com.ktds.curtain.articleLike.vo;

public class ArticleLikeVO {
	
	private int articleLikeId;
	private int articleId;
	private String email;
	private String likeDate;
	private int boradId;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
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
	public String getLikeDate() {
		return likeDate;
	}
	public void setLikeDate(String likeDate) {
		this.likeDate = likeDate;
	}

}
