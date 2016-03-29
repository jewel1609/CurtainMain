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
import com.ktds.curtain.util.MultipartHttpServletRequest;
import com.ktds.curtain.util.MultipartHttpServletRequest.MultipartFile;

/**
 * Servlet implementation class DoUpdateArticleServlet
 */
public class DoUpdateArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArticleBiz articleBiz;
	private FileBiz fileBiz;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoUpdateArticleServlet() {
        super();
        articleBiz = new ArticleBiz();
        fileBiz = new FileBiz();
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
		System.out.println(articleId);
		String articleTitle = multipartRequest.getParameter("articleTitle");
		String articleDescription = multipartRequest.getParameter("articleDescription");
		String articleTypeId = multipartRequest.getParameter("articleId");
		String boardId = multipartRequest.getParameter("boardId");
		MultipartFile file = multipartRequest.getFile("imgFile");
		
		HttpSession session = request.getSession();
		MemberVO stdMember = (MemberVO) session.getAttribute("_MEMBER_");
		
		ArticleVO article = articleBiz.showDetail(articleId);
		
		ArticleVO articleVO = new ArticleVO();
		articleVO.setArticleTitle(articleTitle);
		articleVO.setArticleDesc(articleDescription);
		articleVO.setArticleTypeId(Integer.parseInt(articleTypeId));
		articleVO.setBoardId(Integer.parseInt(boardId));
		articleVO.setEmail(stdMember.getEmail());
		articleVO.setMajorGroupId(stdMember.getMajorGroupId());

		boolean doWriteArticle = articleBiz.doUpdateArticle(article, articleVO);

		if ( !file.getFileName().equals("")) {
			File upFile = file.write("C:\\Users\\206-001\\Documents\\workspace-sts-3.7.2.RELEASE_web\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\Curtain\\resource\\img\\"+file.getFileName());
			FileVO fileVO = new FileVO();
			fileVO.setFileName(file.getFileName());
			fileVO.setFileLocation("C:\\Users\\206-001\\Documents\\workspace-sts-3.7.2.RELEASE_web\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\Curtain\\resource\\img\\"+file.getFileName());
			fileVO.setArticleId(articleId);
			fileVO.setFileType(1);
			
			fileBiz.insertFile(fileVO);
		}
		if ( article.getBoardId() == Integer.parseInt(BoardId.MAJOR_BOARD)) {
			response.sendRedirect("/showDetail?articleId="+articleId);
			return;
		}


		
	}

}
