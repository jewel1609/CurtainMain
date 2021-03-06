package com.ktds.curtain.article.vo;

import java.util.List;

import com.ktds.curtain.file.vo.FileVO;
import com.ktds.curtain.member.vo.MemberVO;
import com.ktds.curtain.reply.vo.ReplyVO;

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
	private int articleDislikes;
	private String articleTypeName;
	private int articleScrab;
	private String majorName;
	private int replyCount;

	private boolean dislike;
	private boolean like;
	private boolean scrab;
	
	private List<ReplyVO> replyList;
	private List<FileVO> fileList;
	
	private int totalCount;
	
	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}

	public String getMajorName() {
		return majorName;
	}

	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}

	public List<FileVO> getFileList() {
		return fileList;
	}

	public void setFileList(List<FileVO> fileList) {
		this.fileList = fileList;
	}

	public List<ReplyVO> getReplyList() {
		return replyList;
	}

	public void setReplyList(List<ReplyVO> replyList) {
		this.replyList = replyList;
	}

	// setters and getters
	public int getArticleScrab() {
		return articleScrab;
	}

	public void setArticleScrab(int articleScrab) {
		this.articleScrab = articleScrab;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isDislike() {
		return dislike;
	}

	public boolean getLike() {
		return like;
	}

	public void setLike(boolean like) {
		this.like = like;
	}

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

	public int getArticleDislikes() {
		return articleDislikes;
	}

	public void setArticleDislikes(int articleDislikes) {
		this.articleDislikes = articleDislikes;
	}

	public void setDislike(boolean dislike) {
		this.dislike = dislike;
	}

	public boolean getDislike() {
		return dislike;
	}

	public boolean isScrab() {
		return scrab;
	}

	public void setScrab(boolean scrab) {
		this.scrab = scrab;
	}

}
