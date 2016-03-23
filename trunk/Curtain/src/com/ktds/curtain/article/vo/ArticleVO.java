package com.ktds.curtain.article.vo;
import com.ktds.curtain.member.vo.StdMemberVO;

public class ArticleVO extends StdMemberVO {
	
	private int articleId;
	private String articleTitle;
	private String articleDesc;
	private String articleRegisterDate;
	private String articleModifyDate;
	private int articleTypeId;
	private String studentEmail;
	private int boardId;
	private int majorGroupId;
	private int univId;
	private int hits;
	private int articleLikes;

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
	public String getStudentEmail() {
		return studentEmail;
	}
	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
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
