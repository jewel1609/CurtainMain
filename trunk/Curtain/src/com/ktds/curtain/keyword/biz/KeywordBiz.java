package com.ktds.curtain.keyword.biz;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ktds.curtain.article.vo.ArticleVO;
import com.ktds.curtain.keyword.dao.KeywordDAO;
import com.ktds.curtain.util.nanum.NounExtractor;

public class KeywordBiz {
	NounExtractor nounExtractor;
	KeywordDAO keywordDAO;
	
	public KeywordBiz () {
		nounExtractor = new NounExtractor();
		keywordDAO = new KeywordDAO();
	}
	
	public void setNounByArticle (ArticleVO article, HttpServletRequest request) {
		
		String requestPath = request.getServletContext().getRealPath("/").toString();
		String[] requestPathSplit = requestPath.split("\\\\");
		
		List<String> nouns = nounExtractor.nounExtractorByTitle(article.getArticleTitle(), requestPathSplit);
		
		keywordDAO.setNounByArticleList(nouns, article);
	}

	public List<String> getKeywordTopSeven(int boardId) {
		return keywordDAO.getKeywordTopSeven(boardId);
	}
	
}
