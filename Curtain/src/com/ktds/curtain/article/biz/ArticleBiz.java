package com.ktds.curtain.article.biz;

import java.util.ArrayList;
import java.util.List;

import com.ktds.curtain.article.dao.ArticleDAO;
import com.ktds.curtain.article.vo.ArticleVO;
import com.ktds.curtain.article.vo.BoardId;
import com.ktds.curtain.articleDislike.dao.DislikeDAO;
import com.ktds.curtain.articleDislike.vo.ArticleDislikeVO;
import com.ktds.curtain.articleLike.dao.ArticleLikeDAO;
import com.ktds.curtain.articleLike.vo.ArticleLikeVO;
import com.ktds.curtain.articleScrab.dao.ScrabDAO;
import com.ktds.curtain.articleScrab.vo.ArticleScrabVO;
import com.ktds.curtain.member.vo.MemberVO;
import com.ktds.curtain.reply.dao.ReplyDAO;

public class ArticleBiz {
	private ArticleDAO articleDAO;
	private List<ArticleVO> articles;
	private ArticleLikeDAO articleLikeDAO;
	private DislikeDAO dislikeDAO;

	private ReplyDAO replyDAO;
	private ScrabDAO scrabDAO;

	
	public ArticleBiz() {
		articleDAO = new ArticleDAO();
		articleLikeDAO = new ArticleLikeDAO();
		dislikeDAO = new DislikeDAO();

		replyDAO = new ReplyDAO();

		scrabDAO = new ScrabDAO();

	}
	
	/**
	 * 학과 게시판 보기
	 * @param stdMember
	 * @return
	 */

	public List<ArticleVO> showMajorArticle(MemberVO stdMember, String boardId){

		articles = new ArrayList<ArticleVO>();
		articles = articleDAO.showMajorArticle(stdMember);
		
		List<ArticleLikeVO> articleLikes = showMajorArticleLike(stdMember, boardId);
		
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
	private List<ArticleLikeVO> showMajorArticleLike(MemberVO stdMember, String boardId) {
		List<ArticleLikeVO> articleLikes = articleLikeDAO.showArticleLike(stdMember, boardId);
		return articleLikes;
	}

	/**
	 * 해당 비밀 게시판 리스트 가져오기
	 * @param stdMember
	 * @return
	 */


	public List<ArticleVO> showSecretArticle(MemberVO stdMember, String boardId) {

		articles = new ArrayList<ArticleVO>();
		articles = articleDAO.showSecretArticle(stdMember);
		
		List<ArticleDislikeVO> articleDislikes = showArticleDislike(stdMember, boardId);
		List<ArticleLikeVO> articleLikes = showArticleLike(stdMember, boardId);
		List<ArticleScrabVO> articleScrabs = showArticleScrab(stdMember, boardId);
		
		
		for(ArticleVO article : articles){
			for(ArticleDislikeVO articleDislike : articleDislikes ) {
				if( article.getArticleId() == articleDislike.getArticleId() ){
					article.setDislike(true);
				}
			}
			
		}
		
		for(ArticleVO article : articles){
			for(ArticleLikeVO articleLike : articleLikes ){
				if( article.getArticleId() == articleLike.getArticleId() ){
					article.setLike(true);
				}
			}
		}
		
		for(ArticleVO article : articles){
			for(ArticleScrabVO articleScrab : articleScrabs ){
				if( article.getArticleId() == articleScrab.getArticleId() ){
					article.setScrab(true);
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
	private List<ArticleLikeVO> showArticleLike(MemberVO stdMember, String BoardId) {
		List<ArticleLikeVO> articleLikes = articleLikeDAO.showArticleLike(stdMember, BoardId);
		return articleLikes;
	}


	/**
	 * 내가 싫어요한 글
	 * @param stdMember
	 * @param boardId
	 * @return
	 */
	private List<ArticleDislikeVO> showArticleDislike(MemberVO stdMember, String boardId) {
		List<ArticleDislikeVO> articleDislikes = dislikeDAO.showArticleDislike(stdMember, boardId);
		return articleDislikes;
	}
	
	/**
	 * 내가 스크랩한 글
	 * @param stdMember
	 * @param boardId
	 * @return
	 */
	private List<ArticleScrabVO> showArticleScrab(MemberVO stdMember, String boardId){
		List<ArticleScrabVO> articleScrabs = scrabDAO.showArticleScrab(stdMember, boardId);
		return articleScrabs;
	}

	/**
	 * 학교 게시판 보기
	 * @param stdMember
	 * @return
	 */
	public List<ArticleVO> showUnivArticle(MemberVO stdMember) {
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

	public boolean doWriteArticle(ArticleVO article, MemberVO stdMember) {
		int doWriteArticle = 0;
		
		if ( article.getBoardId() == Integer.parseInt(BoardId.MAJOR_BOARD) ) { // 전공
			doWriteArticle = articleDAO.doWriteMajorArticle(article, stdMember);
		}
		else if (article.getBoardId()== Integer.parseInt(BoardId.UNIV_BOARD)) { // 대학
			doWriteArticle = articleDAO.doWriteUnivArticle(article, stdMember);
		}
		else if (article.getBoardId()== Integer.parseInt(BoardId.AD_BOARD)) { // 홍보
			doWriteArticle = articleDAO.doWriteAdArticle(article, stdMember);
		}
		else if (article.getBoardId()== Integer.parseInt(BoardId.SECRET_BOARD_LEVEL1)) { // 비밀 1
			doWriteArticle = articleDAO.doWriteMajorArticle(article, stdMember);
		}
		else if (article.getBoardId()== Integer.parseInt(BoardId.SECRET_BOARD_LEVEL2)) {  // 비밀 2
//			doWriteArticle = articleDAO.doWriteMajorArticle(article);
		}
		else if (article.getBoardId()== Integer.parseInt(BoardId.SECRET_BOARD_LEVEL3)) {  // 비밀3
//			doWriteArticle = articleDAO.doWriteMajorArticle(article);
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
		article.setReplyList(replyDAO.getReplyListByArticleId(articleId));
		return article;
	}

	/**
	 * 내가 좋아요 한 글
	 * 
	 * @param stdMember
	 * @return
	 */
	public List<ArticleVO> showLikesArticle(MemberVO member) {
		articles = new ArrayList<ArticleVO>();
		articles = articleDAO.showLikesArticle(member);
		return articles;
	}
	
	public List<ArticleVO> showMyScrabArticle(MemberVO member) {
		articles = new ArrayList<ArticleVO>();
		articles = articleDAO.showMyScrabArticle(member);
		return articles;
	}
	
	public List<ArticleVO> showMyWriteArticle(MemberVO member) {
		articles = new ArrayList<ArticleVO>();
		articles = articleDAO.showMyWriteArticle(member);
		return articles;
	}

	
	/**
	 * 가장 조회수 많은 게시글 
	 * @param stdMember
	 * @param boardId
	 * @return
	 */
	public ArticleVO showTopArticle(MemberVO stdMember, int boardId) {
		
		ArticleVO article = new ArticleVO();
		article = articleDAO.showTopArticle(stdMember);
		return article;
		
	}



}
