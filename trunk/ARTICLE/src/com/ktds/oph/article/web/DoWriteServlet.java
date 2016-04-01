package com.ktds.oph.article.web;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ktds.oph.article.biz.ArticleBiz;
import com.ktds.oph.article.vo.ArticleVO;
import com.ktds.oph.file.biz.FileBiz;
import com.ktds.oph.file.vo.FileVO;
import com.ktds.oph.member.vo.MemberVO;
import com.ktds.oph.util.MultipartHttpServletRequest;
import com.ktds.oph.util.MultipartHttpServletRequest.MultipartFile;

/**
 * Servlet implementation class DoWriteServlet
 */
public class DoWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArticleBiz articleBiz;
	private FileBiz fileBiz;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoWriteServlet() {
        super();
        articleBiz = new ArticleBiz();
        fileBiz = new FileBiz();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendError(HttpServletResponse.SC_FORBIDDEN, "글 쓰기가 불가능 합니다.");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// mutilpartHttpServletResquest 선언
		MultipartHttpServletRequest multipartRequest = new MultipartHttpServletRequest(request);
		
		String title = multipartRequest.getParameter("title");
		String descript = multipartRequest.getParameter("descript");
		MultipartFile file = multipartRequest.getFile("file");
		
		ArticleVO article = new ArticleVO();
		article.setTitle(title);
		article.setDescript(descript);
		
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO) session.getAttribute("_MEMBER_");
		
		int articleId = articleBiz.doWrite(article, member);
		
		if ( articleId > 0 ) {
			if ( file.getFileSize() != 0 ) {
				File upFile = file.write("D:\\"+file.getFileName());
				FileVO fileVO = new FileVO();
				fileVO.setFileName(file.getFileName());
				fileVO.setFileLocation("D:\\"+file.getFileName());
				fileVO.setArticleId(articleId);
				
				fileBiz.doWriteFile(fileVO);
			}
		}
		
		response.sendRedirect("/list");
	}

}
