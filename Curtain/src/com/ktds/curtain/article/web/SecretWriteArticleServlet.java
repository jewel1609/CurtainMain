package com.ktds.curtain.article.web;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ktds.curtain.article.biz.ArticleBiz;
import com.ktds.curtain.article.vo.ArticleVO;
import com.ktds.curtain.file.biz.FileBiz;
import com.ktds.curtain.file.vo.FileVO;
import com.ktds.curtain.util.MultipartHttpServletRequest;
import com.ktds.curtain.util.MultipartHttpServletRequest.MultipartFile;

/**
 * Servlet implementation class SecretWriteArticleServlet
 */
public class SecretWriteArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArticleBiz articleBiz;
	private FileBiz fileBiz;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SecretWriteArticleServlet() {
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
		
		MultipartHttpServletRequest multipartRequest = new MultipartHttpServletRequest(request);
				
		String articleTitle = multipartRequest.getParameter("articleTitle");
		System.out.println(articleTitle+"/");
		String articleDescription = multipartRequest.getParameter("articleDescription");
		System.out.println(articleDescription+"/");
		int articleTypeId = Integer.parseInt(multipartRequest.getParameter("articleTypeId"));
		System.out.println(articleTypeId+"/");
		int boardId = Integer.parseInt(multipartRequest.getParameter("boardId"));
		System.out.println(boardId+"/");
		MultipartFile imgFile = multipartRequest.getFile("imgFile");
		
		MultipartFile movieFile = multipartRequest.getFile("movieFile");
	//	File upMovieFile = movieFiles.write("D:\\" + movieFiles.getFileName());
		
	//	HttpSession session = request.getSession();
	//	StdMemberVO loginMember = (StdMemberVO) session.getAttribute("_MEMBER_");
		
		ArticleVO article = new ArticleVO();
		article.setArticleTitle(articleTitle);
		article.setArticleDesc(articleDescription);
		article.setArticleTypeId(articleTypeId);
		article.setStudentEmail("shinmi@curtain.ac.kr");
		article.setBoardId(boardId);
		article.setMajorGroupId(20);
		article.setUnivId(1);
		article.setNickName("송혜교");
		article.setPassword("111");
		
		boolean doWriteArticle = articleBiz.doWriteArticle(article);
		
		int articleId = articleBiz.getArticleId();
		if( articleId > 0 ){
			if( imgFile.getFileSize() != 0 ){
				
				// 파일이 있을 경우
			
				//List<FileVO> fileList = new ArrayList<FileVO>();
				FileVO file = new FileVO();
				
				file.setArticleId(articleId);
				file.setFileName(imgFile.getFileName());
				file.setFileLocation("D:\\"+imgFile.getFileName());
				
				//fileList.add(file);
				
				fileBiz.insertFile(file);
			}
			response.sendRedirect("/secretBoard");
			return;
		}
		else{
			System.out.println("articleId 가져오지 못함 - 등록 실패");
		}
		
			
	

		
		

		
		
		
		
		
	}

}
