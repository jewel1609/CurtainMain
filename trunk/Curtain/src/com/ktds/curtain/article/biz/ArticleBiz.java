package com.ktds.curtain.article.biz;

import java.util.ArrayList;
import java.util.List;

import com.ktds.curtain.article.dao.ArticleDAO;
import com.ktds.curtain.article.vo.ArticleVO;
import com.ktds.curtain.article.vo.BoardId;
import com.ktds.curtain.articleLike.dao.ArticleLikeDAO;
import com.ktds.curtain.articleLike.vo.ArticleLikeVO;
import com.ktds.curtain.member.vo.StdMemberVO;

public class ArticleBiz {
	private ArticleDAO articleDAO;
	private List<ArticleVO> articles;
	private ArticleLikeDAO articleLikeDAO;
	
	public ArticleBiz() {
		articleDAO = new ArticleDAO();
		articleLikeDAO = new ArticleLikeDAO();
	}
	
	/**
	 * 학과 게시판 보기
	 * @param stdMember
	 * @return
	 */
	public List<ArticleVO> showMajorArticle(StdMemberVO stdMember, String BoardId) {
		articles = new ArrayList<ArticleVO>();
		articles = articleDAO.showMajorArticle(stdMember);
		
		List<ArticleLikeVO> articleLikes = showMajorArticleLike(stdMember, BoardId);
		
		for (ArticleVO article : articles) {
			for (ArticleLikeVO articleLike : articleLikes ) {
				if ( article.getArticleId() == articleLike.getArticleId() ){
					article.setLike(true);
				}
			}
		}
		
		return articles;
	}
	
	/**
	 * 내가 좋아요 한 글
	 * @param stdMember
	 * @return
	 */
	private List<ArticleLikeVO> showMajorArticleLike(StdMemberVO stdMember, String BoardId) {
		List<ArticleLikeVO> articleLikes = articleLikeDAO.showMajorArticleLike(stdMember, BoardId);
		return articleLikes;
	}

	/**
	 * 해당 비밀 게시판 리스트 가져오기
	 * @param stdMember
	 * @return
	 */
	public List<ArticleVO> showSecretArticle(StdMemberVO stdMember) {
		articles = new ArrayList<ArticleVO>();
		articles = articleDAO.showSecretArticle(stdMember);
		return articles;
	}
	
	/**
	 * 학교 게시판 보기
	 * @param stdMember
	 * @return
	 */
	public List<ArticleVO> showUnivArticle(StdMemberVO stdMember) {
		articles = new ArrayList<ArticleVO>();
		articles = articleDAO.showUnivArticle(stdMember);
		return articles;

	}
	
	/**
	 * 조회수 올리기
	 * @param articleVO
	 */
	public boolean hitsCount(ArticleVO articleVO) {
		int updateCount = 0;
		updateCount = articleDAO.hitsCount(articleVO);
		return updateCount > 0;
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
			doWriteArticle = articleDAO.doWriteMajorArticle(article);
		}
		else if (article.getBoardId()== Integer.parseInt(BoardId.SECRET_BOARD_LEVEL2)) {  // 비밀 2
			doWriteArticle = articleDAO.doWriteMajorArticle(article);
		}
		else if (article.getBoardId()== Integer.parseInt(BoardId.SECRET_BOARD_LEVEL3)) {  // 비밀3
			doWriteArticle = articleDAO.doWriteMajorArticle(article);
		}
		return doWriteArticle > 0;
	}
	
	public int getArticleId() {
		int articleId = articleDAO.getArticleId();
		return articleId;
	}

	/**
	 * 글 상세 정보 보기
	 * @param articleId
	 * @return
	 */
	public ArticleVO showDetail(int articleId) {
		ArticleVO article = articleDAO.showDetail(articleId);
		return article;
	}

	/**
	 * 내가 좋아요 한 글
	 * 
	 * @param stdMember
	 * @return
	 */
	public List<ArticleVO> showLikesArticle(StdMemberVO stdMember, String BoardId) {
		articles = new ArrayList<ArticleVO>();
		articles = articleDAO.showLikesArticle(stdMember);
		
		List<ArticleLikeVO> articleLikes = showMajorArticleLike(stdMember, BoardId);
		
		for (ArticleVO article : articles) {
			for (ArticleLikeVO articleLike : articleLikes ) {
				if ( article.getArticleId() == articleLike.getArticleId() ){
					article.setLike(true);
				}
			}
		}
		
		return articles;
	}
	
	public List<ArticleVO> showMyScrabArticle(StdMemberVO stdMember) {
		articles = new ArrayList<ArticleVO>();
		articles = articleDAO.showMyScrabArticle(stdMember);
		return articles;
	}
	
	public List<ArticleVO> showMyWriteArticle(StdMemberVO stdMember) {
		articles = new ArrayList<ArticleVO>();
		articles = articleDAO.showMyWriteArticle(stdMember);
		return articles;
	}



}
