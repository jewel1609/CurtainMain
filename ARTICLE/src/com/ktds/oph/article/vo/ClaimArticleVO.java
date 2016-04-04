package com.ktds.oph.article.vo;

public class ClaimArticleVO extends ArticleVO{
	private int articleClaimId;
	private int replyId;
	private String claimDate;
	private String claimText;
	
	private int claimCount;
	
	private int claimReplyCount;
	
	public int getClaimReplyCount() {
		return claimReplyCount;
	}
	public void setClaimReplyCount(int claimReplyCount) {
		this.claimReplyCount = claimReplyCount;
	}
	public int getClaimCount() {
		return claimCount;
	}
	public void setClaimCount(int claimCount) {
		this.claimCount = claimCount;
	}
	public int getArticleClaimId() {
		return articleClaimId;
	}
	public void setArticleClaimId(int articleClaimId) {
		this.articleClaimId = articleClaimId;
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
	public String getClaimText() {
		return claimText;
	}
	public void setClaimText(String claimText) {
		this.claimText = claimText;
	}
	
}
