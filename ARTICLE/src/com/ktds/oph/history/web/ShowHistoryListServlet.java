package com.ktds.oph.history.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ktds.oph.history.biz.HistoryBiz;
import com.ktds.oph.history.vo.HistoryListVO;
import com.ktds.oph.history.vo.HistorySearchVO;
import com.ktds.oph.history.vo.HistoryVO;
import com.ktds.oph.member.biz.MemberBiz;
import com.ktds.oph.member.vo.MemberVO;

/**
 * Servlet implementation class ShowHistoryListServlet
 */
public class ShowHistoryListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HistoryBiz historyBiz;
	private MemberBiz memberBiz;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowHistoryListServlet() {
        super();
        historyBiz = new HistoryBiz();
        memberBiz = new MemberBiz();
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
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");

		HistoryVO historyVO = new HistoryVO();
		
		HttpSession session = request.getSession();
		MemberVO loginMember = (MemberVO) session.getAttribute("_MEMBER_");
		
		HistoryListVO historys = null;
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
				
			}
			catch (NumberFormatException nfe) {}
			
			if (startDate==null || endDate==null) {
				historyVO.setStartDate((String) session.getAttribute("_START_DATE_"));
				historyVO.setEndDate((String) session.getAttribute("_END_DATE_"));
			}
			else {
				startDate = startDate.replaceAll("-", "");
				endDate = endDate.replaceAll("-", "");
				historyVO.setStartDate(startDate);
				historyVO.setEndDate(String.valueOf(Integer.parseInt(endDate)+1));
				session.setAttribute("_START_DATE_", startDate);
				session.setAttribute("_END_DATE_", endDate);
			}
			HistorySearchVO historySearchVO = new HistorySearchVO();
			historySearchVO.setPageNo(pageNo);
			
			historys = historyBiz.getAllHistory(historySearchVO, historyVO);
			
			request.setAttribute("historys", historys);
			RequestDispatcher rd = request.getRequestDispatcher("//WEB-INF/view/history/showHistoryList.jsp");
			rd.forward(request, response);
			
		}
	}
}
