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
		
		if ( article.getBoardId() == Integer.parseInt(BoardId.MAJOR_BOARD) ) {
			doWriteArticle = doWriteMajorArticle(article);
		}
		else if (article.getBoardId()== Integer.parseInt(BoardId.UNIV_BOARD)) {
			doWriteArticle = doWriteUnivArticle(article);
		}
		else if (article.getBoardId()== Integer.parseInt(BoardId.AD_BOARD)) {
			doWriteArticle = doWriteAdArticle(article);
		}
		else if (article.getBoardId()== Integer.parseInt(BoardId.SECRET_BOARD_LEVEL1)) {
			doWriteArticle = doWriteSecretArticleOne(article);
		}
		else if (article.getBoardId()== Integer.parseInt(BoardId.SECRET_BOARD_LEVEL2)) {
			doWriteArticle = doWriteSecretArticleTwo(article);
		}
		else if (article.getBoardId()== Integer.parseInt(BoardId.SECRET_BOARD_LEVEL3)) {
			doWriteArticle = doWriteSecretArticleThree(article);
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
	
	/**
	 * 비밀게시판 등급1
	 * @param article
	 * @return
	 */
	private boolean doWriteSecretArticleOne(ArticleVO article) {
		articleDAO.doWriteSecretArticleOne(article);
		return false;
	}

	/**
	 * 비밀게시판 등급2
	 * @param article
	 * @return
	 */
	private boolean doWriteSecretArticleTwo(ArticleVO article) {
		articleDAO.doWriteSecretArticleTwo(article);
		return false;
	}
	
	/**
	 * 비밀게시판 등급3
	 * @param article
	 * @return
	 */
	private boolean doWriteSecretArticleThree(ArticleVO article) {
		articleDAO.doWriteSecretArticleThree(article);
		return false;	
	}

	
}
