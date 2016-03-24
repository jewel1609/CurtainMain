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
		int doWriteArticle = 0;
		
		if ( article.getBoardId() == Integer.parseInt(BoardId.MAJOR_BOARD) ) { // 전공
			doWriteArticle = articleDAO.doWriteMajorArticle(article);
		}
		else if (article.getBoardId()== Integer.parseInt(BoardId.UNIV_BOARD)) { // 대학
			doWriteArticle = articleDAO.doWriteUnivArticle(article);
		}
		else if (article.getBoardId()== Integer.parseInt(BoardId.AD_BOARD)) { // 홍보
			doWriteArticle = articleDAO.doWriteAdArticle(article);
		}
		else if (article.getBoardId()== Integer.parseInt(BoardId.SECRET_BOARD_LEVEL1)) { // 비밀 1
			doWriteArticle = articleDAO.doWriteSecretArticleOne(article);
		}
		else if (article.getBoardId()== Integer.parseInt(BoardId.SECRET_BOARD_LEVEL2)) {  // 비밀 2
			doWriteArticle = articleDAO.doWriteSecretArticleTwo(article);
		}
		else if (article.getBoardId()== Integer.parseInt(BoardId.SECRET_BOARD_LEVEL3)) {  // 비밀3
			doWriteArticle = articleDAO.doWriteSecretArticleThree(article);
		}
		return doWriteArticle > 0;
	}
	
	/**
	 * 내가 좋아요 한 글 
	 * @param stdMember
	 * @return
	 */

	public List<ArticleVO> showLikesArticle(StdMemberVO stdMember) {
		articles = new ArrayList<ArticleVO>();
		articles = articleDAO.showMajorArticle(stdMember);
		return articles;
	}

}
