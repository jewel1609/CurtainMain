package com.ktds.curtain.article.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ktds.curtain.article.biz.ArticleBiz;
import com.ktds.curtain.article.vo.ArticleVO;
import com.ktds.curtain.article.vo.BoardId;
import com.ktds.curtain.articleLike.vo.ArticleLikeVO;
import com.ktds.curtain.member.vo.MemberVO;
import com.ktds.curtain.survey.biz.SurveyBiz;
import com.ktds.curtain.survey.vo.SurveyVO;

/**
 * Servlet implementation class StudentMajorAritlceServlet
 */
public class StudentMajorAritlceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArticleBiz articleBiz;
	
	private SurveyBiz surveyBiz;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentMajorAritlceServlet() {
        super();
        articleBiz = new ArticleBiz();
        surveyBiz = new SurveyBiz();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		MemberVO stdMember = (MemberVO) session.getAttribute("_MEMBER_");

		List<ArticleVO> majorArticles = articleBiz.showMajorArticle(stdMember, BoardId.MAJOR_BOARD);
		
		request.setAttribute("member", stdMember);
		request.setAttribute("majorArticles", majorArticles);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/article/article.jsp");
		rd.forward(request, response);
		
		
		
	}

}
