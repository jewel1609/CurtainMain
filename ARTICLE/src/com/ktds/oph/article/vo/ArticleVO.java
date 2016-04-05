/**
 * 
 */
package com.ktds.oph.article.vo;

import java.util.List;

import com.ktds.oph.file.vo.FileVO;
import com.ktds.oph.member.vo.MemberVO;

/**
 * @author Peace
 *
 */
public class ArticleVO extends MemberVO{
	
	private int articleId;
	private String articleTitle;
	private String articleDesc;
	private String articleRegisterDate;
	private String articleModifyDate;
	private int articleTypeId;
	private int boardId;
	private int hits;
	private int articleLikes;
	private int articleDislike;
	private int articleScrab;
	
	private String startDate;
	private String endDate;
	
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
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
	public int getArticleDislike() {
		return articleDislike;
	}
	public void setArticleDislike(int articleDislike) {
		this.articleDislike = articleDislike;
	}
	public int getArticleScrab() {
		return articleScrab;
	}
	public void setArticleScrab(int articleScrab) {
		this.articleScrab = articleScrab;
	}

}
