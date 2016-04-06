package com.ktds.oph.article.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ktds.oph.article.biz.ArticleBiz;
import com.ktds.oph.article.vo.ArticleListVO;
import com.ktds.oph.article.vo.ArticleSearchVO;
import com.ktds.oph.member.biz.MemberBiz;
import com.ktds.oph.member.vo.MemberVO;
import com.ktds.oph.operationHistory.biz.OperationHistoryBiz;
import com.ktds.oph.operationHistory.vo.ActionCode;
import com.ktds.oph.operationHistory.vo.BuildDescription;
import com.ktds.oph.operationHistory.vo.Description;
import com.ktds.oph.operationHistory.vo.OperationHistoryVO;

/**
 * Servlet implementation class ArticleListServlet
 */
public class ArticleListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private MemberBiz memberBiz;   
    private ArticleBiz articleBiz;   
    private OperationHistoryBiz historyBiz;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ArticleListServlet() {
        super();
        memberBiz = new MemberBiz();
        articleBiz = new ArticleBiz();
        historyBiz = new OperationHistoryBiz();
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
		HttpSession session = request.getSession();
		MemberVO loginMember = (MemberVO) session.getAttribute("_MEMBER_");
		
		OperationHistoryVO operationHistoryVO = new OperationHistoryVO();
		operationHistoryVO.setIp(request.getRemoteHost());
		operationHistoryVO.setEmail(loginMember.getEmail());
		operationHistoryVO.setUrl(request.getRequestURI());
		operationHistoryVO.setActionCode(ActionCode.ARTICLE_LIST_PAGE);
		
		if(!memberBiz.isAdmin(loginMember)){
			response.setContentType("text/html; charset=UTF-8");
			 
			PrintWriter out = response.getWriter();
			 
			out.println("<script>"); 
			out.println("alert('관리자가 아닙니다.');"); 
			out.println("window.history.back();");
			out.println("</script>"); 
			out.close();
		}
		else if(memberBiz.isAdmin(loginMember)){
			int pageNo = 0;
		
			try {
				pageNo = Integer.parseInt(request.getParameter("pageNo"));
				operationHistoryVO.setDescription( BuildDescription.get(Description.LIST_PAGING, loginMember.getEmail(), pageNo+""));
			}
			catch (NumberFormatException nfe) {
				operationHistoryVO.setDescription( BuildDescription.get(Description.VISIT_ARTICLE_LIST_PAGE, loginMember.getEmail()));
			}
			
			ArticleSearchVO searchVO = new ArticleSearchVO();
			searchVO.setPageNo(pageNo);
			
			String startDate = request.getParameter("startDate");
			String endDate = request.getParameter("endDate");
			
			operationHistoryVO.setEtc( BuildDescription.get(Description.DETAIL_HISTORY, startDate, endDate));
			
			ArticleListVO articles = articleBiz.getAllArticle(startDate, endDate,searchVO);
			
			request.setAttribute("articles", articles);
			request.setAttribute("startDate", startDate);
			request.setAttribute("endDate", endDate);
			
			historyBiz.addHistory(operationHistoryVO);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/article/showArticleList.jsp");
			rd.forward(request, response);
		}
	}

}
