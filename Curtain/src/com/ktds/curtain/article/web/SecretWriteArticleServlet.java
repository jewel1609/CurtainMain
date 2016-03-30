package com.ktds.curtain.article.web;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ktds.curtain.article.biz.ArticleBiz;
import com.ktds.curtain.article.vo.ArticleVO;
import com.ktds.curtain.file.biz.FileBiz;
import com.ktds.curtain.file.vo.FileVO;
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

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SecretWriteArticleServlet() {
		super();
		articleBiz = new ArticleBiz();
		fileBiz = new FileBiz();
		proBiz = new ProhibitedWordBiz();
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
		int boardId = Integer.parseInt(multipartRequest.getParameter("boardId"));

		MultipartFile imgFile = multipartRequest.getFile("imgFile");
		File upImgFile = imgFile.write("D:\\" + imgFile.getFileName());

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
		article.setBoardId(boardId);
		article.setEmail(loginMember.getEmail());

		boolean doWriteArticle = articleBiz.doWriteArticle(article, loginMember);

		FileVO file = null;
		int articleId = articleBiz.getArticleId();
		if (articleId > 0) {
			// 이미지 파일이 있을 경우
			if (imgFile.getFileSize() != 0) {

				file = new FileVO();
				file.setArticleId(articleId);
				file.setFileName(imgFile.getFileName());
				file.setFileLocation("D:\\" + imgFile.getFileName());
				file.setFileType(1);

				fileBiz.insertFile(file);
				System.out.println("이미지 등록 성공");
			}
			// 영상 url이 있을 경우
			if (!movieUrl.equals("")) {
				file = new FileVO();
				file.setArticleId(articleId);
				file.setFileName(movieUrl);
				file.setFileLocation("");
				file.setFileType(2);

				fileBiz.insertFile(file);
				System.out.println("영상 등록 성공");
			}

			response.sendRedirect("/secretArticleList");
			return;
		} else {
			System.out.println("articleId 가져오지 못함 - 등록 실패");
		}
			
		}

	}


