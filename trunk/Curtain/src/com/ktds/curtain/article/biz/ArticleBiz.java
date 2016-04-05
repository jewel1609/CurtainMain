package com.ktds.curtain.article.biz;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.ktds.curtain.article.dao.ArticleDAO;
import com.ktds.curtain.article.vo.ArticleSearchVO;
import com.ktds.curtain.article.vo.ArticleVO;
import com.ktds.curtain.article.vo.BoardId;
import com.ktds.curtain.articleDislike.dao.DislikeDAO;
import com.ktds.curtain.articleDislike.vo.ArticleDislikeVO;
import com.ktds.curtain.articleLike.dao.ArticleLikeDAO;
import com.ktds.curtain.articleLike.vo.ArticleLikeVO;
import com.ktds.curtain.articleScrab.dao.ScrabDAO;
import com.ktds.curtain.articleScrab.vo.ArticleScrabVO;
import com.ktds.curtain.file.dao.FileDAO;
import com.ktds.curtain.file.vo.FileVO;
import com.ktds.curtain.history.biz.OperationHistoryBiz;
import com.ktds.curtain.history.vo.ActionCode;
import com.ktds.curtain.history.vo.BuildDescription;
import com.ktds.curtain.history.vo.Description;
import com.ktds.curtain.history.vo.OperationHistoryVO;
import com.ktds.curtain.keyword.biz.KeywordBiz;
import com.ktds.curtain.member.biz.MemberBiz;
import com.ktds.curtain.member.vo.MemberVO;
import com.ktds.curtain.reply.dao.ReplyDAO;
import com.ktds.curtain.reply.vo.ReplyVO;
import com.ktds.curtain.replyDislike.dao.ReplyDislikeDAO;
import com.ktds.curtain.replyDislike.vo.ReplyDislikeVO;
import com.ktds.curtain.replyLike.dao.ReplyLikeDAO;
import com.ktds.curtain.replyLike.vo.ReplyLikeVO;

public class ArticleBiz {
	private ArticleDAO articleDAO;
	private List<ArticleVO> articles;
	private ArticleLikeDAO articleLikeDAO;
	private DislikeDAO dislikeDAO;

	private ReplyDAO replyDAO;
	private ScrabDAO scrabDAO;
	private FileDAO fileDAO;
	
	private MemberBiz memberBiz;
	private ReplyLikeDAO replyLikeDAO;
	private ReplyDislikeDAO replyDislikeDAO;
	
	private KeywordBiz keywordBiz;
	private OperationHistoryBiz historyBiz;
	
	public ArticleBiz() {
		articleDAO = new ArticleDAO();
		articleLikeDAO = new ArticleLikeDAO();
		dislikeDAO = new DislikeDAO();

		replyDAO = new ReplyDAO();

		scrabDAO = new ScrabDAO();
		fileDAO = new FileDAO();
		
		memberBiz = new MemberBiz();
		replyLikeDAO = new ReplyLikeDAO();
		replyDislikeDAO = new ReplyDislikeDAO();
		
		keywordBiz = new KeywordBiz();
		historyBiz = new OperationHistoryBiz();

	}
	
	/**
	 * 학과 게시판 보기
	 * @param stdMember
	 * @return
	 */

	public List<ArticleVO> showMajorArticle(MemberVO stdMember, String boardId, ArticleSearchVO searchVO, HttpServletRequest request){
		
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO)session.getAttribute("_MEMBER_");
		
		articles = new ArrayList<ArticleVO>();
		if ( searchVO.getSearchType().equals("1")) {
		
			OperationHistoryVO historyVO = new OperationHistoryVO();
			historyVO.setIp(request.getRemoteHost());
			historyVO.setEmail(member.getEmail());
			historyVO.setUrl(request.getRequestURI());
			historyVO.setActionCode(ActionCode.SEARCH_TITLE);
			historyVO.setDescription( BuildDescription.get(Description.SEARCH_TITLE, member.getNickName()) );
			historyVO.setEtc( BuildDescription.get(Description.DETAIL_SEARCH_TITLE, searchVO.getSearchKeyword()));
			
			historyBiz.addHistory(historyVO);
			
			articles = articleDAO.showMajorArticleByTitle(stdMember, searchVO);
			getReplyCount(articles);
			changeDateFormat(articles);
		

		}
		else if (searchVO.getSearchType().equals("2")) {
			
			OperationHistoryVO historyVO = new OperationHistoryVO();
			historyVO.setIp(request.getRemoteHost());
			historyVO.setEmail(member.getEmail());
			historyVO.setUrl(request.getRequestURI());
			historyVO.setActionCode(ActionCode.SEARCH_DESC);
			historyVO.setDescription( BuildDescription.get(Description.SEARCH_DESC, member.getNickName()) );
			historyVO.setEtc( BuildDescription.get(Description.DETAIL_SEARCH_DESC, searchVO.getSearchKeyword()));
			
			historyBiz.addHistory(historyVO);
			
			articles = articleDAO.showMajorArticleByDesc(stdMember, searchVO);
			getReplyCount(articles);
			changeDateFormat(articles);
			
		}
		else {
			articles = articleDAO.showMajorArticle(stdMember);
			getReplyCount(articles);
			changeDateFormat(articles);
			
		}
		
		
		List<ArticleLikeVO> articleLikes = showArticleLike(stdMember, boardId);
		List<ArticleDislikeVO> articleDislikes = showArticleDislike(stdMember, boardId);
		List<ArticleScrabVO> articleScrabs = showArticleScrab(stdMember, boardId);
		
		for (ArticleVO article : articles) {
			for (ArticleLikeVO articleLike : articleLikes ) {
				if ( article.getArticleId() == articleLike.getArticleId() ){
					article.setLike(true);
				}
			}
		}
		for(ArticleVO article : articles){
			for(ArticleDislikeVO articleDislike : articleDislikes ) {
				if( article.getArticleId() == articleDislike.getArticleId() ){
					article.setDislike(true);
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
	 * 날짜 형식 바꾸기
	 * @param articles2
	 */
	private void changeDateFormat(List<ArticleVO> articles2) {
		
		String date = "";
		
		for(ArticleVO article : articles){
			date = article.getArticleModifyDate().substring(0, 16);
			article.setArticleModifyDate(date);
		}
		
	}

	private void getReplyCount(List<ArticleVO> articles) {
		
		int replyCount = 0;
		
		for(ArticleVO article : articles){
			replyCount = replyDAO.getReplyCountByArticleId(article.getArticleId());
			article.setReplyCount(replyCount);
		}
	}

	/**
	 * 해당 비밀 게시판 리스트 가져오기
	 * @param stdMember
	 * @return
	 */
	public List<ArticleVO> showSecretArticle(MemberVO stdMember, String boardId, ArticleSearchVO searchVO, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO)session.getAttribute("_MEMBER_");
		
		articles = new ArrayList<ArticleVO>();
		
		if ( searchVO.getSearchType().equals("1")) {
			
			OperationHistoryVO historyVO = new OperationHistoryVO();
			historyVO.setIp(request.getRemoteHost());
			historyVO.setEmail(member.getEmail());
			historyVO.setUrl(request.getRequestURI());
			historyVO.setActionCode(ActionCode.SEARCH_TITLE);
			historyVO.setDescription( BuildDescription.get(Description.SEARCH_TITLE, member.getNickName()) );
			historyVO.setEtc( BuildDescription.get(Description.DETAIL_SEARCH_TITLE, searchVO.getSearchKeyword()));
			
			historyBiz.addHistory(historyVO);
			
			articles = articleDAO.showSecretArticleByTitle(stdMember, boardId, searchVO);
			getReplyCount(articles);
			changeDateFormat(articles);
			
		}
		else if (searchVO.getSearchType().equals("2")) {
			
			OperationHistoryVO historyVO = new OperationHistoryVO();
			historyVO.setIp(request.getRemoteHost());
			historyVO.setEmail(member.getEmail());
			historyVO.setUrl(request.getRequestURI());
			historyVO.setActionCode(ActionCode.SEARCH_DESC);
			historyVO.setDescription( BuildDescription.get(Description.SEARCH_DESC, member.getNickName()) );
			historyVO.setEtc( BuildDescription.get(Description.DETAIL_SEARCH_DESC, searchVO.getSearchKeyword()));
			
			historyBiz.addHistory(historyVO);
			
			articles = articleDAO.showSecretArticleByDesc(stdMember, boardId, searchVO);
			getReplyCount(articles);
			changeDateFormat(articles);
		}
		else {
			articles = articleDAO.showSecretArticle(stdMember, boardId);
			getReplyCount(articles);
			changeDateFormat(articles);
		}
		
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
	public List<ArticleVO> showUnivArticle(MemberVO stdMember, ArticleSearchVO searchVO, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO)session.getAttribute("_MEMBER_");
		
		articles = new ArrayList<ArticleVO>();
		
		if ( searchVO.getSearchType().equals("1")) {
			
			OperationHistoryVO historyVO = new OperationHistoryVO();
			historyVO.setIp(request.getRemoteHost());
			historyVO.setEmail(member.getEmail());
			historyVO.setUrl(request.getRequestURI());
			historyVO.setActionCode(ActionCode.SEARCH_TITLE);
			historyVO.setDescription( BuildDescription.get(Description.SEARCH_TITLE, member.getNickName()) );
			historyVO.setEtc( BuildDescription.get(Description.DETAIL_SEARCH_TITLE, searchVO.getSearchKeyword()));
			
			historyBiz.addHistory(historyVO);
			
			articles = articleDAO.showUnivArticleByTitle(stdMember, searchVO);
			getReplyCount(articles);
			changeDateFormat(articles);
			
		}
		else if (searchVO.getSearchType().equals("2")) {
			
			OperationHistoryVO historyVO = new OperationHistoryVO();
			historyVO.setIp(request.getRemoteHost());
			historyVO.setEmail(member.getEmail());
			historyVO.setUrl(request.getRequestURI());
			historyVO.setActionCode(ActionCode.SEARCH_DESC);
			historyVO.setDescription( BuildDescription.get(Description.SEARCH_DESC, member.getNickName()) );
			historyVO.setEtc( BuildDescription.get(Description.DETAIL_SEARCH_DESC, searchVO.getSearchKeyword()));
			
			historyBiz.addHistory(historyVO);
			
			articles = articleDAO.showUnivArticleByDesc(stdMember, searchVO);
			getReplyCount(articles);
			changeDateFormat(articles);
			
		}
		else {
			
			articles = articleDAO.showUnivArticle(stdMember);
			getReplyCount(articles);
			changeDateFormat(articles);
		}
		
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

	public boolean doWriteArticle(ArticleVO article, MemberVO stdMember, HttpServletRequest request) {
		
		int doWriteArticle = 0;
		
		String description = article.getArticleDesc().replaceAll("\n", "<br/>");
		article.setArticleDesc(description);
				
		if ( article.getBoardId() == Integer.parseInt(BoardId.MAJOR_BOARD) ) { // 전공
			doWriteArticle = articleDAO.doWriteMajorArticle(article);
			memberBiz.addPointAndModifyMemberType(article, request);
			article.setArticleId(articleDAO.getArticleIdByTitleAndDesc(article));
			keywordBiz.setNounByArticle(article, request);
		}
		else if (article.getBoardId()== Integer.parseInt(BoardId.UNIV_BOARD)) { // 대학
			doWriteArticle = articleDAO.doWriteUnivArticle(article);
			memberBiz.addPointAndModifyMemberType(article, request);
			article.setArticleId(articleDAO.getArticleIdByTitleAndDesc(article));
			keywordBiz.setNounByArticle(article, request);
		}
		else if (article.getBoardId()== Integer.parseInt(BoardId.AD_BOARD)) { // 홍보
			doWriteArticle = articleDAO.doWriteAdArticle(article);
			article.setArticleId(articleDAO.getArticleIdByTitleAndDesc(article));
			keywordBiz.setNounByArticle(article, request);
		}
		else if (article.getBoardId()== Integer.parseInt(BoardId.FREE_BOARD)) { // 자유
			doWriteArticle = articleDAO.doWriteSecretArticle(article, stdMember);
			memberBiz.addPointAndModifyMemberType(article, request);
			article.setArticleId(articleDAO.getArticleIdByTitleAndDesc(article));
			keywordBiz.setNounByArticle(article, request);
		}
		else if (article.getBoardId()== Integer.parseInt(BoardId.SECRET_BOARD_LEVEL1)) {  // 비밀 1
			doWriteArticle = articleDAO.doWriteSecretArticle(article, stdMember);
			memberBiz.addPointAndModifyMemberType(article, request);
			article.setArticleId(articleDAO.getArticleIdByTitleAndDesc(article));
			keywordBiz.setNounByArticle(article, request);
		}
		else if (article.getBoardId()== Integer.parseInt(BoardId.SECRET_BOARD_LEVEL2)) {  // 비밀 2
			doWriteArticle = articleDAO.doWriteSecretArticle(article, stdMember);
			memberBiz.addPointAndModifyMemberType(article, request);
			article.setArticleId(articleDAO.getArticleIdByTitleAndDesc(article));
			keywordBiz.setNounByArticle(article, request);
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
	public ArticleVO showDetail(int articleId, MemberVO stdMember) {
		ArticleVO article = articleDAO.showDetail(articleId);
		List<ReplyVO> reply = replyDAO.getReplyListByArticleId(articleId);
		List<ReplyLikeVO> replyLikes = replyLikeDAO.getReplyLikes(articleId, stdMember);
		List<ReplyDislikeVO> replyDislikes = replyDislikeDAO.getReplyDislikes(articleId, stdMember);
		for (ReplyVO replys : reply) {
			for (ReplyLikeVO articleLike : replyLikes ) {
				if ( replys.getReplyId() == articleLike.getReplyId() ){
					replys.setLike(true);
				}
			}
		}
		for (ReplyVO replys : reply) {
			for(ReplyDislikeVO replyDislike : replyDislikes ) {
				if( replys.getReplyId() == replyDislike.getReplyId() ){
					System.out.println("싫어요"+replyDislike.getReplyId());
					replys.setDislike(true);
				}
			}
			
		}
		article.setReplyList(reply);
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
	public ArticleVO showTopArticle(MemberVO stdMember, String boardId) {
		
		ArticleVO article = new ArticleVO();
		article = articleDAO.showTopArticle(stdMember, boardId);
		
		article.setReplyCount(replyDAO.getReplyCountByArticleId(article.getArticleId()));
		article.setArticleModifyDate(article.getArticleModifyDate().substring(0, 16));
		
		List<ArticleDislikeVO> articleDislikes = showArticleDislike(stdMember, boardId);
		List<ArticleLikeVO> articleLikes = showArticleLike(stdMember, boardId);
		List<ArticleScrabVO> articleScrabs = showArticleScrab(stdMember, boardId);
		
		for(ArticleDislikeVO articleDislike : articleDislikes ) {
			if( article.getArticleId() == articleDislike.getArticleId() ){
				article.setDislike(true);
			}
		}
	
		for(ArticleLikeVO articleLike : articleLikes ){
			if( article.getArticleId() == articleLike.getArticleId() ){
				article.setLike(true);
			}
		}

		for(ArticleScrabVO articleScrab : articleScrabs ){
			if( article.getArticleId() == articleScrab.getArticleId() ){
				article.setScrab(true);
			}
		}

		return article;
	}

	/**
	 * 자신의 글이면 삭제하기
	 * @param articleVO
	 */
	public boolean deleteArticle(ArticleVO articleVO) {
		// 게시글 중에 삭제
		int deleteCount = articleDAO.deleteArticle(articleVO);
		// 좋아요 목록에 있는 것 삭제
		articleLikeDAO.deleteArticle(articleVO);
		// 싫어요 목록에 있는 것 삭제
		dislikeDAO.deleteArticle(articleVO);
		// 스크랩 목록에 있는 것 삭제
		scrabDAO.deleteArticle(articleVO);
		// 댓글 삭제
		replyDAO.deleteReply(articleVO);
		return deleteCount > 0;
	}

	/**
	 * 파일 가져오기
	 * @param articleId
	 * @return
	 */
	public List<FileVO> getFileListByArticleId(int articleId) {
		List<FileVO> file = fileDAO.getFileListByArticleId(articleId);
		return file;
	}

	/**
	 * 수정하기
	 * @param article - 원래
	 * @param articleVO - 수정한글
	 * @return
	 */
	public boolean doUpdateArticle(ArticleVO article, ArticleVO articleVO) {
		
		ArticleVO originAticle = article;
		
		int changeCount = 0;
		
		String title = articleVO.getArticleTitle();
		String description = articleVO.getArticleDesc();
		
		ArticleVO changedArticle = new ArticleVO();
		// 원본과 지금 글이 다른가
		if ( !originAticle.getArticleTitle().equals(title) ) {
			changeCount++;
			changedArticle.setArticleTitle(title);
		}
		
		description = description.replaceAll("\n", "<br/>");
		System.out.println(originAticle.getArticleDesc());
		System.out.println(description);
		if ( !originAticle.getArticleDesc().equals(description) ) {
			changeCount++;
			changedArticle.setArticleDesc(description);
		}
		
		// 0이면 예외처리한다.
		if ( changeCount != 0 ) {
			changedArticle.setArticleId(article.getArticleId());
			return articleDAO.updateArticle(changedArticle) > 0;
		}
		return true;
	}

	public List<ArticleVO> showPromotionArticle(MemberVO stdMember, String promotionBoard, ArticleSearchVO searchVO, HttpServletRequest request) {
		articles = new ArrayList<ArticleVO>();
		articles = articleDAO.showPromotionArticle(stdMember, promotionBoard);
		
		if ( searchVO.getSearchType().equals("1")) {
			
			OperationHistoryVO historyVO = new OperationHistoryVO();
			historyVO.setIp(request.getRemoteHost());
			historyVO.setEmail(stdMember.getEmail());
			historyVO.setUrl(request.getRequestURI());
			historyVO.setActionCode(ActionCode.SEARCH_TITLE);
			historyVO.setDescription( BuildDescription.get(Description.SEARCH_TITLE, stdMember.getNickName()) );
			historyVO.setEtc( BuildDescription.get(Description.DETAIL_SEARCH_TITLE, searchVO.getSearchKeyword()));
			
			historyBiz.addHistory(historyVO);
			
			articles = articleDAO.showPromotionArticleByTitle(stdMember, promotionBoard, searchVO);
		}
		else if (searchVO.getSearchType().equals("2")) {
			
			OperationHistoryVO historyVO = new OperationHistoryVO();
			historyVO.setIp(request.getRemoteHost());
			historyVO.setEmail(stdMember.getEmail());
			historyVO.setUrl(request.getRequestURI());
			historyVO.setActionCode(ActionCode.SEARCH_DESC);
			historyVO.setDescription( BuildDescription.get(Description.SEARCH_DESC, stdMember.getNickName()) );
			historyVO.setEtc( BuildDescription.get(Description.DETAIL_SEARCH_DESC, searchVO.getSearchKeyword()));
			
			historyBiz.addHistory(historyVO);
			
			articles = articleDAO.showPromotionArticleByDesc(stdMember, promotionBoard, searchVO);
		}
		else {
			articles = articleDAO.showPromotionArticle(stdMember, promotionBoard);
		}
		
		List<ArticleDislikeVO> articleDislikes = showArticleDislike(stdMember, promotionBoard);
		List<ArticleLikeVO> articleLikes = showArticleLike(stdMember, promotionBoard);
		List<ArticleScrabVO> articleScrabs = showArticleScrab(stdMember, promotionBoard);
		
		
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
	 * 학과에서 가장 조회수가 많은 글
	 * @param stdMember
	 * @param majorBoard
	 * @return
	 */
	public ArticleVO showTopMajorArticle(MemberVO stdMember, String boardId) {
		
		System.out.println("showTopMajorArticle 접근");
		
		ArticleVO article = new ArticleVO();
		article = articleDAO.showTopMajorArticle(stdMember, boardId);
		
		article.setReplyCount(replyDAO.getReplyCountByArticleId(article.getArticleId()));
		article.setArticleModifyDate(article.getArticleModifyDate().substring(0, 16));
		
		List<ArticleDislikeVO> articleDislikes = showArticleDislike(stdMember, boardId);
		List<ArticleLikeVO> articleLikes = showArticleLike(stdMember, boardId);
		List<ArticleScrabVO> articleScrabs = showArticleScrab(stdMember, boardId);
		
		for(ArticleDislikeVO articleDislike : articleDislikes ) {
			if( article.getArticleId() == articleDislike.getArticleId() ){
				article.setDislike(true);
			}
		}
	
		for(ArticleLikeVO articleLike : articleLikes ){
			if( article.getArticleId() == articleLike.getArticleId() ){
				article.setLike(true);
			}
		}

		for(ArticleScrabVO articleScrab : articleScrabs ){
			if( article.getArticleId() == articleScrab.getArticleId() ){
				article.setScrab(true);
			}
		}
		
		return article;
	}


	public ArticleVO showTopUnivArticle(MemberVO stdMember, String majorBoard) {
		
		ArticleVO article = new ArticleVO();
		article = articleDAO.showTopUnivArticle(stdMember, majorBoard);
		
		article.setReplyCount(replyDAO.getReplyCountByArticleId(article.getArticleId()));
		article.setArticleModifyDate(article.getArticleModifyDate().substring(0, 16));
		
		List<ArticleDislikeVO> articleDislikes = showArticleDislike(stdMember, majorBoard);
		List<ArticleLikeVO> articleLikes = showArticleLike(stdMember, majorBoard);
		List<ArticleScrabVO> articleScrabs = showArticleScrab(stdMember, majorBoard);
		
		for(ArticleDislikeVO articleDislike : articleDislikes ) {
			if( article.getArticleId() == articleDislike.getArticleId() ){
				article.setDislike(true);
			}
		}
	
		for(ArticleLikeVO articleLike : articleLikes ){
			if( article.getArticleId() == articleLike.getArticleId() ){
				article.setLike(true);
			}
		}

		for(ArticleScrabVO articleScrab : articleScrabs ){
			if( article.getArticleId() == articleScrab.getArticleId() ){
				article.setScrab(true);
			}
		}

		return article;
	}

}
