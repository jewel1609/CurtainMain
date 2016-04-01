package com.ktds.curtain.article.articleClaim.biz;

import com.ktds.curtain.article.articleClaim.dao.ArticleClaimDAO;
import com.ktds.curtain.article.articleClaim.vo.ArticleClaimVO;

public class WriteClaimBiz {
	private ArticleClaimDAO articleClaimDAO;
	
	public WriteClaimBiz() {
		articleClaimDAO = new ArticleClaimDAO();
	}

	public boolean doWriteClaim(ArticleClaimVO articleClaimVO) {
		int insertClaim = articleClaimDAO.doWriteClaim(articleClaimVO);
		return insertClaim > 0;
	}

	public boolean doWriteReplyClaim(ArticleClaimVO articleClaimVO) {
		int insertClaim = articleClaimDAO.doWriteReplyClaim(articleClaimVO);
		return insertClaim > 0;
	}

}
