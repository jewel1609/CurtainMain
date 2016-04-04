package com.ktds.oph.article.vo;

import java.util.List;

import com.ktds.oph.util.web.Paging;

public class ArticleListVO {
	
	private List<ArticleVO> articleList;
	private List<ClaimArticleVO> claimArticleList;
	public List<ClaimArticleVO> getClaimArticleList() {
		return claimArticleList;
	}
	public void setClaimArticleList(List<ClaimArticleVO> claimArticleList) {
		this.claimArticleList = claimArticleList;
	}
	private Paging paging;
	
	public List<ArticleVO> getArticleList() {
		return articleList;
	}
	public void setArticleList(List<ArticleVO> articleList) {
		this.articleList = articleList;
	}
	public Paging getPaging() {
		return paging;
	}
	public void setPaging(Paging paging) {
		this.paging = paging;
	}

}
