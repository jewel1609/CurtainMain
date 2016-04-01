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
import com.ktds.curtain.member.vo.MemberVO;
import com.ktds.curtain.prohibitedWord.biz.ProhibitedWordBiz;
import com.ktds.curtain.util.MultipartHttpServletRequest;
import com.ktds.curtain.util.Root;
import com.ktds.curtain.util.MultipartHttpServletRequest.MultipartFile;


/**
 * Servlet implementation class WriteArticleServlet
 */
public class WriteArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArticleBiz articleBiz;
	private FileBiz fileBiz;
	private ProhibitedWordBiz proBiz;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WriteArticleServlet() {
        super();
        articleBiz = new ArticleBiz();
        fileBiz = new FileBiz();
        proBiz = new ProhibitedWordBiz();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendError(HttpServletResponse.SC_FORBIDDEN, "잘못 된 요청입니다.");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// mutilpartHttpServletResquest 선언
				MultipartHttpServletRequest multipartRequest = new MultipartHttpServletRequest(request);
				
				String articleTitle = multipartRequest.getParameter("articleTitle");
				System.out.println(articleTitle);
				String articleDescription = multipartRequest.getParameter("articleDescription");
				System.out.println(articleDescription);
				String articleTypeId = multipartRequest.getParameter("articleTypeId");
				System.out.println(articleTypeId);
				String boardId = multipartRequest.getParameter("boardId");
				System.out.println(boardId);
				MultipartFile file = multipartRequest.getFile("imgFile");
				System.out.println(file.getFileName());
				String movieUrl = multipartRequest.getParameter("movieUrl");
				
				HttpSession session = request.getSession();
				MemberVO stdMember = (MemberVO) session.getAttribute("_MEMBER_");
				
				List<String> wordList = (List<String>) session.getAttribute("_WORDLIST_");

				for (int i = 0; i < wordList.size(); i++) {
					if (articleTitle.contains(wordList.get(i)) || articleDescription.contains(wordList.get(i))) {
						if ( boardId.equals(BoardId.MAJOR_BOARD)) {
						response.sendRedirect("/studentMajorAritlce?isFword=1");
						}
						else if (boardId.equals(BoardId.UNIV_BOARD)) {
							response.sendRedirect("/studentUnivArticle?isFword=1");
						}
						return;
					}  	
					break;
				}
				
				ArticleVO article = new ArticleVO();
				article.setArticleTitle(articleTitle);
				article.setArticleDesc(articleDescription);
				article.setArticleTypeId(Integer.parseInt(articleTypeId));
				article.setBoardId(Integer.parseInt(boardId));
				article.setEmail(stdMember.getEmail());
				article.setMajorGroupId(stdMember.getMajorGroupId());
				article.setUnivId(stdMember.getUnivId());

				boolean doWriteArticle = articleBiz.doWriteArticle(article, stdMember, request);
				int articleId = articleBiz.getArticleId();
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
						System.out.println("영상 등록 성공");
					}
				}

				if ( boardId.equals(BoardId.MAJOR_BOARD)) {
					response.sendRedirect("/studentMajorAritlce");
				}
				else if ( boardId.equals(BoardId.UNIV_BOARD)) {
					response.sendRedirect("/studentUnivArticle");
				}
				
	}
}
