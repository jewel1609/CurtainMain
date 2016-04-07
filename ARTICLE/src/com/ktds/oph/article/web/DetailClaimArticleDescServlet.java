package com.ktds.oph.article.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ktds.oph.article.biz.ArticleBiz;
import com.ktds.oph.article.vo.ClaimArticleVO;
import com.ktds.oph.member.vo.MemberVO;

/**
 * Servlet implementation class DetailClaimArticleDescServlet
 */
public class DetailClaimArticleDescServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ClaimArticleVO claimInfo;  
	private ArticleBiz articleBiz;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailClaimArticleDescServlet() {
        super();
        claimInfo = new ClaimArticleVO();
        articleBiz = new ArticleBiz();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String articleClaimId = request.getParameter("articleClaimId");
		
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO) session.getAttribute("_MEMBER_");
		
		claimInfo = (ClaimArticleVO) articleBiz.getClaimInfoByClaimArticleId(articleClaimId, member);
		
		request.setAttribute("claimInfo", claimInfo);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/article/detailClaimArticleDesc.jsp");
		rd.forward(request, response);
		
	}

}
