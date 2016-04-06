package com.ktds.oph.univ.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ktds.oph.article.vo.ArticleSearchVO;
import com.ktds.oph.member.biz.MemberBiz;
import com.ktds.oph.member.vo.MemberVO;
import com.ktds.oph.operationHistory.biz.OperationHistoryBiz;
import com.ktds.oph.operationHistory.vo.ActionCode;
import com.ktds.oph.operationHistory.vo.BuildDescription;
import com.ktds.oph.operationHistory.vo.Description;
import com.ktds.oph.operationHistory.vo.OperationHistoryVO;
import com.ktds.oph.univ.biz.UnivBiz;
import com.ktds.oph.univ.vo.UnivListVO;
import com.ktds.oph.univ.vo.UnivSearchVO;

/**
 * Servlet implementation class UnivListServlet
 */
public class UnivListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberBiz memberBiz;
	private UnivBiz univBiz;
	private OperationHistoryBiz historyBiz;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UnivListServlet() {
        super();
        memberBiz = new MemberBiz();
        univBiz = new UnivBiz();
        historyBiz = new OperationHistoryBiz();
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
		
		HttpSession session = request.getSession();
		MemberVO loginMember = (MemberVO) session.getAttribute("_MEMBER_");
		
		OperationHistoryVO historyVO = new OperationHistoryVO();
		historyVO.setIp(request.getRemoteHost());
		historyVO.setEmail(loginMember.getEmail());
		historyVO.setUrl(request.getRequestURI());
		historyVO.setActionCode(ActionCode.ADMIN_UNIV_PAGE);
		
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
			UnivSearchVO univSearchVO = new UnivSearchVO();
		
			try {
				pageNo = Integer.parseInt(request.getParameter("pageNo"));
				
				univSearchVO.setPageNo(pageNo);
				univSearchVO.setSearchKeyword(request.getParameter("searchKeyword"));
				univSearchVO.setSearchType(request.getParameter("searchType"));
				historyVO.setDescription( BuildDescription.get(Description.LIST_PAGING, loginMember.getEmail(), pageNo+""));
			}
			catch (NumberFormatException nfe) {
				historyVO.setDescription( BuildDescription.get(Description.VISIT_ADMIN_UNIV_PAGE, loginMember.getEmail()));
				univSearchVO = (UnivSearchVO) session.getAttribute("_SEARCH_");
				
				if ( univSearchVO == null ) {
					univSearchVO = new UnivSearchVO();
					univSearchVO.setPageNo(0);
					univSearchVO.setSearchKeyword("");
					univSearchVO.setSearchType("1");
				}
			}
			session.setAttribute("_SEARCH_", univSearchVO);
			
			UnivListVO univs = univBiz.getAllUniv(univSearchVO);
			
			historyBiz.addHistory(historyVO);
			
			request.setAttribute("univs", univs);
			request.setAttribute("univSearchVO", univSearchVO);
			RequestDispatcher rd = request.getRequestDispatcher("//WEB-INF/view/univ/univList.jsp");
			rd.forward(request, response);
		}
	}

}
