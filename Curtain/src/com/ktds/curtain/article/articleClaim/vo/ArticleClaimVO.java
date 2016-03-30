package com.ktds.curtain.article.articleClaim.vo;

public class ArticleClaimVO {
	
	private int articleClaimId;
	private String email;
	private int articleId;
	private int replyId;
	private String claimDate;
	private String claimText;
	
	public String getClaimText() {
		return claimText;
	}
	public void setClaimText(String claimText) {
		this.claimText = claimText;
	}
	public int getArticleClaimId() {
		return articleClaimId;
	}
	public void setArticleClaimId(int articleClaimId) {
		this.articleClaimId = articleClaimId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getArticleId() {
		return articleId;
	}
	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}
	public int getReplyId() {
		return replyId;
	}
	public void setReplyId(int replyId) {
		this.replyId = replyId;
	}
	public String getClaimDate() {
		return claimDate;
	}
	public void setClaimDate(String claimDate) {
		this.claimDate = claimDate;
	}
	
}
