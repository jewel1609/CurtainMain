package com.ktds.curtain.article.web;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ktds.curtain.article.biz.ArticleBiz;
import com.ktds.curtain.article.vo.ArticleVO;
import com.ktds.curtain.article.vo.BoardId;
import com.ktds.curtain.file.biz.FileBiz;
import com.ktds.curtain.file.vo.FileVO;
import com.ktds.curtain.history.biz.OperationHistoryBiz;
import com.ktds.curtain.history.vo.ActionCode;
import com.ktds.curtain.history.vo.BuildDescription;
import com.ktds.curtain.history.vo.Description;
import com.ktds.curtain.history.vo.OperationHistoryVO;
import com.ktds.curtain.member.vo.MemberVO;
import com.ktds.curtain.prohibitedWord.biz.ProhibitedWordBiz;
import com.ktds.curtain.util.MultipartHttpServletRequest;
import com.ktds.curtain.util.MultipartHttpServletRequest.MultipartFile;

/**
 * Servlet implementation class DoUpdateArticleServlet
 */
public class DoUpdateArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArticleBiz articleBiz;
	private FileBiz fileBiz;
	private ProhibitedWordBiz proBiz;
	private OperationHistoryBiz historyBiz;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoUpdateArticleServlet() {
        super();
        articleBiz = new ArticleBiz();
        fileBiz = new FileBiz();
        proBiz = new ProhibitedWordBiz();
        historyBiz= new OperationHistoryBiz();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendError(HttpServletResponse.SC_FORBIDDEN, "잘못된 요청입니다.");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// mutilpartHttpServletResquest 선언
		MultipartHttpServletRequest multipartRequest = new MultipartHttpServletRequest(request);
		
		int articleId = Integer.parseInt(multipartRequest.getParameter("articleId"));
		String articleTitle = multipartRequest.getParameter("articleTitle");
		String articleDescription = multipartRequest.getParameter("articleDescription");
		String articleTypeId = multipartRequest.getParameter("articleId");
		String boardId = multipartRequest.getParameter("boardId");
		MultipartFile file = multipartRequest.getFile("imgFile");
		String movieUrl = multipartRequest.getParameter("movieUrl");
		
		HttpSession session = request.getSession();
		MemberVO stdMember = (MemberVO) session.getAttribute("_MEMBER_");
		
		List<List<String>> wordList = (List<List<String>>) session.getAttribute("_WORDLIST_");

		for (int i = 0; i < wordList.size(); i++) {
			List<String> words = new ArrayList<String>();
			words = wordList.get(i);
			for ( String word : words) {
				if (articleTitle.contains(word)) {
					response.sendRedirect("/showDetail?isFword=1&articleId="+articleId+"&boardId="+boardId);
					return;
				}
				else if (articleDescription.contains(word)) {
					response.sendRedirect("/showDetail?isFword=1&articleId="+articleId+"&boardId="+boardId);
					return;
				}
			}
		}
		
		ArticleVO article = articleBiz.showDetail(articleId, stdMember);
		
		ArticleVO articleVO = new ArticleVO();
		articleVO.setArticleTitle(articleTitle);
		articleVO.setArticleDesc(articleDescription);
		articleVO.setArticleTypeId(Integer.parseInt(articleTypeId));
		articleVO.setBoardId(Integer.parseInt(boardId));
		articleVO.setEmail(stdMember.getEmail());
		articleVO.setMajorGroupId(stdMember.getMajorGroupId());

		boolean doWriteArticle = articleBiz.doUpdateArticle(article, articleVO);
		
		if ( doWriteArticle ) {
			
			OperationHistoryVO historyVO = new OperationHistoryVO();
			historyVO.setIp(request.getRemoteHost());
			historyVO.setEmail(stdMember.getEmail());
			historyVO.setUrl(request.getRequestURI());
			historyVO.setActionCode(ActionCode.UPDATE_ARTICLE);
			historyVO.setDescription( BuildDescription.get(Description.UPDATE_ARTICLE, stdMember.getNickName(), multipartRequest.getParameter("articleId")) );
			historyVO.setEtc(BuildDescription.get(Description.DETAIL_UPDATE_ARTICLE, articleTitle, articleDescription));
			
			historyBiz.addHistory(historyVO);
			
		}
		

		if (articleId > 0) {
			// 이미지 파일이 있을 경우
			if ( !file.getFileName().equals("")) {
				File upFile = file.write("C:\\Users\\206-001\\Documents\\workspace-sts-3.7.2.RELEASE_web\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\Curtain\\resource\\img\\"+file.getFileName());
				FileVO fileVO = new FileVO();
				fileVO.setFileName(file.getFileName());
				fileVO.setFileLocation("C:\\Users\\206-001\\Documents\\workspace-sts-3.7.2.RELEASE_web\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\Curtain\\resource\\img\\"+file.getFileName());
				fileVO.setArticleId(articleId);
				fileVO.setFileType(1);
				fileBiz.insertFile(fileVO);
			}
			// 영상 url이 있을 경우
			if (!movieUrl.equals("")) {
				FileVO fileVO = new FileVO();
				fileVO.setArticleId(articleId);
				fileVO.setFileName(movieUrl);
				fileVO.setFileLocation("");
				fileVO.setFileType(2);

				fileBiz.insertFile(fileVO);
			}
		}
			response.sendRedirect("/showDetail?articleId="+articleId+"&boardId="+article.getBoardId());

	}

}
