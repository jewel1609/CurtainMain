package com.ktds.oph.article.biz;

import java.util.ArrayList;
import java.util.List;

import com.ktds.oph.article.dao.ArticleDAO;
import com.ktds.oph.article.vo.ArticleListVO;
import com.ktds.oph.article.vo.ArticleSearchVO;
import com.ktds.oph.article.vo.ArticleVO;
import com.ktds.oph.file.dao.FileDAO;
import com.ktds.oph.member.vo.MemberVO;
import com.ktds.oph.util.MultipartHttpServletRequest;
import com.ktds.oph.util.web.Paging;

public class ArticleBiz {
	
	private ArticleDAO articleDAO;
	private List<ArticleVO> articles;
	private ArticleVO articleVO;
	private FileDAO fileDAO;
	
	public ArticleBiz() {
		articleDAO = new ArticleDAO();
		articleVO = new ArticleVO();
		articles = new ArrayList<ArticleVO>();
		fileDAO = new FileDAO();
	}

	/**
	 * 게시글 전체 LIST
	 * @param memberVO
	 * @return
	 */
	public ArticleListVO getAllArticle(ArticleSearchVO searchVO) {
		int allArticleCount = articleDAO.getAllArticleCount();
		
		Paging paging = new Paging();
		paging.setTotalArticleCount(allArticleCount);
		paging.setPageNumber(searchVO.getPageNo() + ""); // 1페이지 0번으로 시작함
		
		searchVO.setStartIndex( paging.getStartArticleNumber() );
		searchVO.setEndIndex( paging.getEndArticleNumber() );
		
		List<ArticleVO> articles = new ArrayList<ArticleVO>();
		articles = articleDAO.getAllArticle(searchVO);
		// paging 바꾸는 이유 검색 때문에
		
		ArticleListVO articleList = new ArticleListVO();
		articleList.setArticleList(articles);
		articleList.setPaging(paging);
		
		// 몇 번째 페이지
		
		
		return articleList;
	}

	/**
	 * 하나의 게시글
	 * @param articleVO
	 * @return
	 */
	public ArticleVO showDetail(int articleId) {
		articleVO = articleDAO.showDetail(articleId);
		articleVO.setFileList(fileDAO.showDetail(articleId));
		return articleVO;
	}

	public boolean addHit(int articleId) {
		int updateCount = articleDAO.addHit(articleId);
		return updateCount>0;
	}

	/**
	 * 추천수 올리기
	 * @param articleId
	 * @return
	 */
	public boolean addRecommend(int articleId) {
		int updateCount = articleDAO.addRecommend(articleId);
		return updateCount>0;
	}

	public int doWrite(ArticleVO article, MemberVO member) {
		int articleId = articleDAO.doWrite(article, member);
		return articleId;
	}

	public boolean doUpdate(MultipartHttpServletRequest requset) {		
		int articleId = Integer.parseInt(requset.getParameter("articleId"));
		
		ArticleVO originAticle = showDetail(articleId);
		
		int changeCount = 0;
		
		String title = requset.getParameter("title");
		String description = requset.getParameter("descript");
		
		ArticleVO changedArticle = new ArticleVO();
		// 원본과 지금 글이 다른가
		if ( !originAticle.getTitle().equals(title) ) {
			changeCount++;
			changedArticle.setTitle(title);
		}
		
		description = description.replaceAll("\n", "<br/>");
		if ( !originAticle.getDescript().equals(description) ) {
			changeCount++;
			changedArticle.setDescript(description);
		}
		
		// 0이면 예외처리한다.
		if ( changeCount == 0 ) {
			throw new RuntimeException("변경된 사항이 없습니다.");
		}
		else { // 0이 아니면 update한다.
			changedArticle.setArticleId(articleId);
			return articleDAO.doUpdate(changedArticle) > 0;
		}

	}

	public boolean deleteArticle(int articleId) {
		int deleteCount = articleDAO.deleteArticle(articleId);
		int deleteCountF = fileDAO.deleteFile(articleId);
		
		return deleteCount > 0;
	}

}
