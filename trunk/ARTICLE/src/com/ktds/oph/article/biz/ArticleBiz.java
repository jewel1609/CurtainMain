package com.ktds.oph.article.biz;

import java.util.ArrayList;
import java.util.List;

import com.ktds.oph.article.dao.ArticleDAO;
import com.ktds.oph.article.vo.ArticleListVO;
import com.ktds.oph.article.vo.ArticleSearchVO;
import com.ktds.oph.article.vo.ArticleVO;
import com.ktds.oph.article.vo.ClaimArticleVO;
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


	public boolean deleteArticle(int articleId) {
		int deleteCount = articleDAO.deleteArticle(articleId);
		int deleteCountF = fileDAO.deleteFile(articleId);
		
		return deleteCount > 0;
	}

	/////////////////////////Curtain 관리자///////////////////////////////

	public ArticleListVO getAllClaimArticle(ArticleSearchVO searchVO) {
		int allClaimArticleCount = articleDAO.getAllClaimArticleCount();

		Paging paging = new Paging();
		paging.setTotalArticleCount(allClaimArticleCount);
		paging.setPageNumber(searchVO.getPageNo() + ""); // 1페이지 0번으로 시작함
		
		searchVO.setStartIndex( paging.getStartArticleNumber() );
		searchVO.setEndIndex( paging.getEndArticleNumber() );
		
		List<ClaimArticleVO> claimArticles = new ArrayList<ClaimArticleVO>();
		claimArticles = articleDAO.getAllClaimArticle(searchVO);
		// paging 바꾸는 이유 검색 때문에
		
		ArticleListVO claimArticleList = new ArticleListVO();
		claimArticleList.setClaimArticleList(claimArticles);
		claimArticleList.setPaging(paging);
		
		// 몇 번째 페이지
		
		
		return claimArticleList;
	}

	public ArticleVO getClaimArticleInfoByArticleId(String articleId, MemberVO member) {
		if( member.getMemberTypeId() == 6 ) {
			return articleDAO.getClaimArticleInfoByArticleId(articleId);
		}
		return null;
	}

	//////////////////////////////////////////////////////////////////////
}
