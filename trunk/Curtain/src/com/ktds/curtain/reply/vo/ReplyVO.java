package com.ktds.curtain.reply.vo;

public class ReplyVO {
	
	private int replyId;
	private String replyDesc;
	private String replyRegisterDate;
	private String replyModifyDate;
	private int articleId;
	private int parentReplyId;
	private int replyOrder;
	private int replyLikes;
	private int boardId;
	private int replyDislikes;
	private String email;
	private String nickName;
	private boolean like;
	private boolean dislike;
	

	public boolean isLike() {
		return like;
	}
	public void setLike(boolean like) {
		this.like = like;
	}
	public boolean isDislike() {
		return dislike;
	}
	public void setDislike(boolean dislike) {
		this.dislike = dislike;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public int getReplyId() {
		return replyId;
	}
	public void setReplyId(int replyId) {
		this.replyId = replyId;
	}
	public String getReplyDesc() {
		return replyDesc;
	}
	public void setReplyDesc(String replyDesc) {
		this.replyDesc = replyDesc;
	}
	public String getReplyRegisterDate() {
		return replyRegisterDate;
	}
	public void setReplyRegisterDate(String replyRegisterDate) {
		this.replyRegisterDate = replyRegisterDate;
	}
	public String getReplyModifyDate() {
		return replyModifyDate;
	}
	public void setReplyModifyDate(String replyModifyDate) {
		this.replyModifyDate = replyModifyDate;
	}
	public int getArticleId() {
		return articleId;
	}
	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}
	public int getParentReplyId() {
		return parentReplyId;
	}
	public void setParentReplyId(int parentReplyId) {
		this.parentReplyId = parentReplyId;
	}
	public int getReplyOrder() {
		return replyOrder;
	}
	public void setReplyOrder(int replyOrder) {
		this.replyOrder = replyOrder;
	}
	public int getReplyLikes() {
		return replyLikes;
	}
	public void setReplyLikes(int replyLikes) {
		this.replyLikes = replyLikes;
	}
	public int getBoardId() {
		return boardId;
	}
	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}
	public int getReplyDislikes() {
		return replyDislikes;
	}
	public void setReplyDislikes(int replyDislikes) {
		this.replyDislikes = replyDislikes;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

}
