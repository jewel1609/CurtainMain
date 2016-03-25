package com.ktds.curtain.articleDislike.vo;

public class ArticleDislikeVO {

	private int articleDislikeId;
	private int articleId;
	private String studentEmail;
	private String dislikeDate;
	private int boardId;

	public int getArticleDislikeId() {
		return articleDislikeId;
	}

	public void setArticleDislikeId(int articleDislikeId) {
		this.articleDislikeId = articleDislikeId;
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

	public String getDislikeDate() {
		return dislikeDate;
	}

	public void setDislikeDate(String dislikeDate) {
		this.dislikeDate = dislikeDate;
	}

	public int getBoardId() {
		return boardId;
	}

	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}

}
