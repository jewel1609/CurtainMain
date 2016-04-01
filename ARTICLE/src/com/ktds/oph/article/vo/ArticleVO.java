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
	private String memberId;
	private String title;
	private String descript;
	private int hits;
	private int recommends;
	
	private List<FileVO> fileList;
	
	public List<FileVO> getFileList() {
		return fileList;
	}
	public void setFileList(List<FileVO> fileList) {
		this.fileList = fileList;
	}
	
	public int getArticleId() {
		return articleId;
	}
	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescript() {
		return descript;
	}
	public void setDescript(String descript) {
		this.descript = descript;
	}
	public int getHits() {
		return hits;
	}
	public void setHits(int hits) {
		this.hits = hits;
	}
	public int getRecommends() {
		return recommends;
	}
	public void setRecommends(int recommends) {
		this.recommends = recommends;
	}

}
