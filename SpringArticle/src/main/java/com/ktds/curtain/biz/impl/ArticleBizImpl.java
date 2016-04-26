package com.ktds.curtain.biz.impl;

import java.util.ArrayList;
import java.util.List;

import com.ktds.curtain.biz.ArticleBiz;
import com.ktds.curtain.dao.ArticleDAO;
import com.ktds.curtain.vo.ArticleListVO;
import com.ktds.curtain.vo.ArticleSearchVO;
import com.ktds.curtain.vo.ArticleVO;

import kr.co.hucloud.utilities.web.Paging;

public class ArticleBizImpl implements ArticleBiz {

	private ArticleDAO articleDAO;

	public void setArticleDAO(ArticleDAO articleDAO) {
		this.articleDAO = articleDAO;
	}

	@Override
	public ArticleVO getOneArticle(String articleId) {
		return articleDAO.getOneArticle(articleId);
	}

	@Override
	public boolean insertNewArticle(ArticleVO articleVO) {
		return false;
	}


	@Override
	public ArticleListVO getAllList(int pageNo) {
		ArticleListVO articleListVO = new ArticleListVO();
		Paging paging = new Paging();
		articleListVO.setPaging(paging);
		// 토탈 리스트 수
		int totalCount = articleDAO.getTotalCount();
		paging.setTotalArticleCount(totalCount);
		paging.setPageNumber(pageNo + "");
		
		ArticleSearchVO articleSearchVO = new ArticleSearchVO();
		articleSearchVO.setPageNo(pageNo);
		articleSearchVO.setStartIndex(paging.getStartArticleNumber());
		articleSearchVO.setEndIndex(paging.getEndArticleNumber());
		
		List<ArticleVO> articleList = new ArrayList<ArticleVO>();
		articleList = articleDAO.getAllList(articleSearchVO);
		
		articleListVO.setArticleList(articleList);
		
		return articleListVO;
	}

	@Override
	public void deleteOneArticle(String articleId) {
		articleDAO.deleteOneArticle(articleId);
	}

	@Override
	public boolean modifyOneArticle(ArticleVO articleVO) {
		
		// 
		ArticleVO originalArticleVO = articleDAO.getOneArticle(articleVO.getArticleId());
		
		// 기존의 제목과 새로 입력한 제목이 다르다면, true
		boolean isChangedSubject = !originalArticleVO.getSubject().equals(articleVO.getSubject());
		// 기존의 내용과 새로 입력한 내용이 다르다면, true
		boolean isChangedDescription = !originalArticleVO.getDescription().equals(articleVO.getDescription());
		
		// 변경된 사항이 없다면 true를 보낸다.
		if ( !isChangedSubject && !isChangedDescription) {
			return true;
		}
		
		if ( isChangedSubject ) { 
			originalArticleVO.setSubject( articleVO.getSubject() );
		}
		if ( isChangedDescription ) {
			originalArticleVO.setDescription( articleVO.getDescription() );
		}
		
		return articleDAO.modifyOneArticle(originalArticleVO) > 0;
	}

	@Override
	public boolean doRecommendArticle(String articleId) {
		return articleDAO.doRecommendArticle(articleId) > 0;
	}

}
