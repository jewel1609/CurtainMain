package com.ktds.curtain.articleScrab.vo;

public class ArticleScrabVO {

	private int articleScrabId;
	private int articleId;
	private String email;
	private String scrabDate;
	private int boardId;

	public int getArticleScrabId() {
		return articleScrabId;
	}

	public void setArticleScrabId(int articleScrabId) {
		this.articleScrabId = articleScrabId;
	}

	public int getArticleId() {
		return articleId;
	}

	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getScrabDate() {
		return scrabDate;
	}

	public void setScrabDate(String scrabDate) {
		this.scrabDate = scrabDate;
	}

	public int getBoardId() {
		return boardId;
	}

	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}

}
