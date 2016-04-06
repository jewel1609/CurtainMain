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

	public ArticleListVO getAllClaimArticle(ArticleSearchVO searchVO, MemberVO member) {
		int allClaimArticleCount = 0;
		List<ClaimArticleVO> claimArticles = null;
		
		if(searchVO.getSearchType().equals("1")){ //전체
			allClaimArticleCount = articleDAO.getAllClaimArticleCount();
		}
		else if(searchVO.getSearchType().equals("2")){ //글ID
			allClaimArticleCount = articleDAO.getAllClaimArticleCountByArticleId(searchVO);
		}
		else if(searchVO.getSearchType().equals("3")){
			allClaimArticleCount = articleDAO.getAllClaimArticleCountByReplyId(searchVO);
		}
		else if(searchVO.getSearchType().equals("4")){
			allClaimArticleCount = articleDAO.getAllClaimArticleCountByEmail(searchVO);
		}
		
		Paging paging = new Paging();
		paging.setTotalArticleCount(allClaimArticleCount);
		paging.setPageNumber(searchVO.getPageNo() + ""); // 1페이지 0번으로 시작함
		
		searchVO.setStartIndex( paging.getStartArticleNumber() );
		searchVO.setEndIndex( paging.getEndArticleNumber() );
		
		if(member.getMemberTypeId() == 6){
			if( searchVO.getSearchType().equals("1")){
				claimArticles = articleDAO.getAllClaimArticle(searchVO);
			}
			else if( searchVO.getSearchType().equals("2")){
				claimArticles = articleDAO.getAllClaimArticleByArticleId(searchVO);
			}
			else if( searchVO.getSearchType().equals("3")){
				claimArticles = articleDAO.getAllClaimArticleByReplyId(searchVO);
			}
			else if( searchVO.getSearchType().equals("4")){
				claimArticles = articleDAO.getAllClaimArticleByEmail(searchVO);
			}
		}
		
		ArticleListVO claimArticleList = new ArticleListVO();
		claimArticleList.setClaimArticleList(claimArticles);
		claimArticleList.setPaging(paging);
		
		return claimArticleList;
	}

	public ArticleVO getClaimArticleInfoByArticleId(String articleId, MemberVO member) {
		if( member.getMemberTypeId() == 6 ) {
			return articleDAO.getClaimArticleInfoByArticleId(articleId);
		}
		return null;
	}


	public ArticleListVO getAllArticle(String startDate,String endDate, ArticleSearchVO searchVO) {
		int allArticleCount = articleDAO.getAllArticleCount(startDate, endDate);

		Paging paging = new Paging();
		paging.setTotalArticleCount(allArticleCount);
		paging.setPageNumber(searchVO.getPageNo() + ""); // 1페이지 0번으로 시작함
		
		searchVO.setStartIndex( paging.getStartArticleNumber() );
		searchVO.setEndIndex( paging.getEndArticleNumber() );
		
		List<ArticleVO> articles = new ArrayList<ArticleVO>();
		articles = articleDAO.getAllArticle(startDate, endDate,searchVO);
		// paging 바꾸는 이유 검색 때문에
		
		ArticleListVO articleList = new ArticleListVO();
		articleList.setArticleList(articles);
		articleList.setPaging(paging);
		
		// 몇 번째 페이지
		
		
		return articleList;
	}


	public void deleteArticles(String[] deleteArticleId, MemberVO member) {
		if( member.getMemberTypeId() == 6 ) {
			for (String articleId : deleteArticleId ) {
				articleDAO.deleteArticle(articleId);
			}
		}
		
	}


	public ArticleVO getArticleInfoByArticleId(String articleId, MemberVO member) {
		if( member.getMemberTypeId() == 6 ) {
			return articleDAO.getArticleInfoByArticleId(articleId);
		}
		return null;
	}

}
