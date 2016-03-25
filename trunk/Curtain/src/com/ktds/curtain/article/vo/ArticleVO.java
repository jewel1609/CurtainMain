package com.ktds.curtain.article.vo;
import com.ktds.curtain.member.vo.MemberVO;

public class ArticleVO extends MemberVO {
	
	private int articleId;
	private String articleTitle;
	private String articleDesc;
	private String articleRegisterDate;
	private String articleModifyDate;
	private int articleTypeId;
	private String email;
	private int boardId;
	private int majorGroupId;
	private int univId;
	private int hits;
	private int articleLikes;

	private int articleDisLikes;

	private int articleDislikes;

	private boolean like;

	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getArticleDisLikes() {
		return articleDisLikes;
	}
	public void setArticleDisLikes(int articleDisLikes) {
		this.articleDisLikes = articleDisLikes;
	}

	private boolean dislike;



	public boolean isDislike() {
		return dislike;
	}
	public void setDislike(boolean dislike) {
		this.dislike = dislike;
	}
	public int getArticleDislikes() {
		return articleDislikes;
	}
	public void setArticleDislikes(int articleDislikes) {
		this.articleDislikes = articleDislikes;
	}

	public boolean getLike() {
		return like;
	}
	public void setLike(boolean like) {
		this.like = like;
	}

	private String articleTypeName;
	
	public String getArticleTypeName() {
		return articleTypeName;
	}
	public void setArticleTypeName(String articleTypeName) {
		this.articleTypeName = articleTypeName;
	}
	public int getArticleId() {
		return articleId;
	}
	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}
	public String getArticleTitle() {
		return articleTitle;
	}
	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}
	public String getArticleDesc() {
		return articleDesc;
	}
	public void setArticleDesc(String articleDesc) {
		this.articleDesc = articleDesc;
	}
	public String getArticleRegisterDate() {
		return articleRegisterDate;
	}
	public void setArticleRegisterDate(String articleRegisterDate) {
		this.articleRegisterDate = articleRegisterDate;
	}
	public String getArticleModifyDate() {
		return articleModifyDate;
	}
	public void setArticleModifyDate(String articleModifyDate) {
		this.articleModifyDate = articleModifyDate;
	}
	public int getArticleTypeId() {
		return articleTypeId;
	}
	public void setArticleTypeId(int articleTypeId) {
		this.articleTypeId = articleTypeId;
	}
	public int getBoardId() {
		return boardId;
	}
	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}
	public int getUnivId() {
		return univId;
	}
	public void setUnivId(int univId) {
		this.univId = univId;
	}
	public int getHits() {
		return hits;
	}
	public void setHits(int hits) {
		this.hits = hits;
	}
	public int getArticleLikes() {
		return articleLikes;
	}
	public void setArticleLikes(int articleLikes) {
		this.articleLikes = articleLikes;
	}

	public int getMajorGroupId() {
		return majorGroupId;
	}
	public void setMajorGroupId(int majorGroupId) {
		this.majorGroupId = majorGroupId;
	}
}
