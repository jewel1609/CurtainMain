package com.ktds.curtain.article.biz;

import java.util.ArrayList;
import java.util.List;

import com.ktds.curtain.article.dao.ArticleDAO;
import com.ktds.curtain.article.vo.ArticleVO;
import com.ktds.curtain.article.vo.BoardId;
import com.ktds.curtain.member.vo.StdMemberVO;

public class ArticleBiz {
	private ArticleDAO articleDAO;
	private List<ArticleVO> articles;
	
	public ArticleBiz() {
		articleDAO = new ArticleDAO();
	}
	
	public List<ArticleVO> showMajorArticle(StdMemberVO stdMember) {
		articles = new ArrayList<ArticleVO>();
		articles = articleDAO.showMajorArticle(stdMember);
		return articles;
	}
	
/**
 * 글쓰기
 * @param article
 * @return
 */
	public boolean doWriteArticle(ArticleVO article) {
		boolean doWriteArticle = false;
		
		if ( article.getMajorArticle().equals(BoardId.MAJOR_BOARD) ) {
			doWriteArticle = doWriteMajorArticle(article);
		}
		else if (article.getMajorArticle().equals(BoardId.UNIV_BOARD)) {
			doWriteArticle = doWriteUnivArticle(article);
		}
		else if (article.getMajorArticle().equals(BoardId.AD_BOARD)) {
			doWriteArticle = doWriteAdArticle(article);
		}
		return doWriteArticle;
	}

	/**
	 * 전공
	 * @param article
	 * @return
	 */
	private boolean doWriteMajorArticle(ArticleVO article) {
		articleDAO.doWriteMajorArticle(article);
		return false;
	}
	
	/**
	 * 대학
	 * @param article
	 * @return
	 */
	private boolean doWriteUnivArticle(ArticleVO article) {
		articleDAO.doWriteUnivArticle(article);
		return false;
	}

	/**
	 * 홍보
	 * @param article
	 * @return
	 */
	private boolean doWriteAdArticle(ArticleVO article) {
		articleDAO.doWriteAdArticle(article);
		return false;
	}

}
