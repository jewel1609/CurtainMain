package com.ktds.curtain.keyword.biz;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ktds.curtain.article.vo.ArticleVO;
import com.ktds.curtain.keyword.dao.KeywordDAO;
import com.ktds.curtain.member.vo.MemberVO;
import com.ktds.curtain.util.nanum.NounExtractorByTitle;

import kr.ac.kaist.swrc.jhannanum.plugin.SupplementPlugin.PosProcessor.NounExtractor.NounExtractor;

public class KeywordBiz {
	NounExtractorByTitle nounExtractor;
	KeywordDAO keywordDAO;
	
	public KeywordBiz () {
		nounExtractor = new NounExtractorByTitle();
		keywordDAO = new KeywordDAO();
	}
	
	public void setNounByArticle (ArticleVO article, HttpServletRequest request) {
		
		String requestPath = request.getServletContext().getRealPath("/").toString();
		String[] requestPathSplit = requestPath.split("\\\\");
		
		List<String> nouns = nounExtractor.nounExtractorByTitle(article.getArticleTitle(), requestPathSplit);
		
		keywordDAO.setNounByArticleList(nouns, article);
	}

	public List<String> getKeywordTopSeven(int boardId, MemberVO member) {
		
		if (boardId == 1) {
			return keywordDAO.getKeywordTopSevenByMajor(member);
		}
		else if ( boardId == 2 ) {
			return keywordDAO.getKeywordTopSevenByUniv(member);
		}
		else {
			return keywordDAO.getKeywordTopSeven(boardId);
		}
	}
	
}
