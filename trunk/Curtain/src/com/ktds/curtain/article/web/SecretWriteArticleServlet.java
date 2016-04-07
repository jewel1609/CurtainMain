package com.ktds.curtain.article.web;

import java.io.File;
import java.io.IOException;
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
 * Servlet implementation class SecretWriteArticleServlet
 */
public class SecretWriteArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArticleBiz articleBiz;
	private FileBiz fileBiz;
	private ProhibitedWordBiz proBiz;
	private OperationHistoryBiz historyBiz;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SecretWriteArticleServlet() {
		super();
		articleBiz = new ArticleBiz();
		fileBiz = new FileBiz();
		proBiz = new ProhibitedWordBiz();
		historyBiz = new OperationHistoryBiz();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendError(HttpServletResponse.SC_FORBIDDEN, "잘못된 요청입니다.");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		MultipartHttpServletRequest multipartRequest = new MultipartHttpServletRequest(request);

		String articleTitle = multipartRequest.getParameter("articleTitle");
		String articleDescription = multipartRequest.getParameter("articleDescription");
		int articleTypeId = Integer.parseInt(multipartRequest.getParameter("articleTypeId"));
		String boardId = multipartRequest.getParameter("boardId");
		MultipartFile file0 = multipartRequest.getFile("imgFile0");
		MultipartFile file1 = multipartRequest.getFile("imgFile1");
		MultipartFile file2 = multipartRequest.getFile("imgFile2");
		String movieUrl = multipartRequest.getParameter("movieUrl");

		HttpSession session = request.getSession();
		MemberVO loginMember = (MemberVO) session.getAttribute("_MEMBER_");
		
		List<String> wordList = (List<String>) session.getAttribute("_WORDLIST_");

		for (int i = 0; i < wordList.size(); i++) {
			if (articleTitle.contains(wordList.get(i)) || articleDescription.contains(wordList.get(i))) {
				response.sendRedirect("/secretArticleList?isFword=1");
				return;
			}  	
			break;
		}
		
		ArticleVO article = new ArticleVO();
		article.setArticleTitle(articleTitle);
		article.setArticleDesc(articleDescription);
		article.setArticleTypeId(articleTypeId);
		article.setBoardId(Integer.parseInt(boardId));
		article.setEmail(loginMember.getEmail());


		boolean doWriteArticle = articleBiz.doWriteArticle(article, loginMember, request);
		int articleId = articleBiz.getArticleId();
		if (articleId > 0) {
			// 이미지 파일이 있을 경우
			if ( !file0.getFileName().equals("")) {
				File upFile = file0.write("C:\\Users\\206-001\\Documents\\workspace-sts-3.7.2.RELEASE_web\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\Curtain\\resource\\img\\"+file0.getFileName());
				FileVO fileVO = new FileVO();
				fileVO.setFileName(file0.getFileName());
				fileVO.setFileLocation("C:\\Users\\206-001\\Documents\\workspace-sts-3.7.2.RELEASE_web\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\Curtain\\resource\\img\\"+file0.getFileName());
				fileVO.setArticleId(articleId);
				fileVO.setFileType(1);
				fileBiz.insertFile(fileVO);
			}
			if ( !file1.getFileName().equals("")) {
				File upFile = file1.write("C:\\Users\\206-001\\Documents\\workspace-sts-3.7.2.RELEASE_web\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\Curtain\\resource\\img\\"+file1.getFileName());
				FileVO fileVO = new FileVO();
				fileVO.setFileName(file1.getFileName());
				fileVO.setFileLocation("C:\\Users\\206-001\\Documents\\workspace-sts-3.7.2.RELEASE_web\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\Curtain\\resource\\img\\"+file1.getFileName());
				fileVO.setArticleId(articleId);
				fileVO.setFileType(1);
				fileBiz.insertFile(fileVO);
			}
			if ( !file2.getFileName().equals("")) {
				File upFile = file2.write("C:\\Users\\206-001\\Documents\\workspace-sts-3.7.2.RELEASE_web\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\Curtain\\resource\\img\\"+file2.getFileName());
				FileVO fileVO = new FileVO();
				fileVO.setFileName(file2.getFileName());
				fileVO.setFileLocation("C:\\Users\\206-001\\Documents\\workspace-sts-3.7.2.RELEASE_web\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\Curtain\\resource\\img\\"+file2.getFileName());
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

		if ( boardId.equals(BoardId.FREE_BOARD) ) {
			
			OperationHistoryVO historyVO = new OperationHistoryVO();
			historyVO.setIp(request.getRemoteHost());
			historyVO.setEmail(loginMember.getEmail());
			historyVO.setUrl(request.getRequestURI());
			historyVO.setActionCode(ActionCode.WRITE_FREE);
			historyVO.setDescription( BuildDescription.get(Description.WRITE_FREE, loginMember.getEmail()));
			historyVO.setEtc(BuildDescription.get(Description.DETAIL_DESCRIPTION, articleTitle, loginMember.getNickName(), articleDescription ));
			
			historyBiz.addHistory(historyVO);
			
			
			response.sendRedirect("/secretArticleList");
			return;
		} 
		else if( boardId.equals(BoardId.SECRET_BOARD_LEVEL1)) {
			
			OperationHistoryVO historyVO = new OperationHistoryVO();
			historyVO.setIp(request.getRemoteHost());
			historyVO.setEmail(loginMember.getEmail());
			historyVO.setUrl(request.getRequestURI());
			historyVO.setActionCode(ActionCode.WRITE_ONE_LAYER);
			historyVO.setDescription( BuildDescription.get(Description.WRITE_ONE_LAYER, loginMember.getEmail()));
			historyVO.setEtc(BuildDescription.get(Description.DETAIL_DESCRIPTION, articleTitle, loginMember.getNickName(), articleDescription ));
			
			historyBiz.addHistory(historyVO);
			
			response.sendRedirect("/oneLayerCurtain");
			return;
		}
		else if( boardId.equals(BoardId.SECRET_BOARD_LEVEL2)) {
			
			OperationHistoryVO historyVO = new OperationHistoryVO();
			historyVO.setIp(request.getRemoteHost());
			historyVO.setEmail(loginMember.getEmail());
			historyVO.setUrl(request.getRequestURI());
			historyVO.setActionCode(ActionCode.WRITE_TWO_LAYER);
			historyVO.setDescription( BuildDescription.get(Description.WRITE_TWO_LAYER, loginMember.getNickName()));
			historyVO.setEtc(BuildDescription.get(Description.DETAIL_DESCRIPTION, articleTitle, loginMember.getNickName(), articleDescription ));
			
			historyBiz.addHistory(historyVO);
			
			response.sendRedirect("/twoLayerCurtain");
			return;
		}
	}
}


