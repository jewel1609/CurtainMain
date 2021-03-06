package com.ktds.oph.member.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ktds.oph.member.biz.MemberBiz;
import com.ktds.oph.member.vo.MemberListVO;
import com.ktds.oph.member.vo.MemberSearchVO;
import com.ktds.oph.member.vo.MemberVO;
import com.ktds.oph.operationHistory.biz.OperationHistoryBiz;
import com.ktds.oph.operationHistory.vo.ActionCode;
import com.ktds.oph.operationHistory.vo.BuildDescription;
import com.ktds.oph.operationHistory.vo.Description;
import com.ktds.oph.operationHistory.vo.OperationHistoryVO;
import com.ktds.oph.questionAndAnswer.vo.QuestionAndAnswerSearchVO;

/**
 * Servlet implementation class ShowMemberServlet
 */
public class ShowMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberBiz memberBiz;
	private OperationHistoryBiz historyBiz;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowMemberServlet() {
        super();
        memberBiz = new MemberBiz();
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

		OperationHistoryVO historyVO = new OperationHistoryVO();
		historyVO.setIp(request.getRemoteHost());
		historyVO.setEmail(loginMember.getEmail());
		historyVO.setUrl(request.getRequestURI());
		historyVO.setActionCode(ActionCode.ADMIN_MEMBER_PAGE);
		
		
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
			MemberSearchVO searchVO = new MemberSearchVO();
			session = request.getSession();
			
			try {
				pageNo = Integer.parseInt(request.getParameter("pageNo"));
				
				historyVO.setDescription( BuildDescription.get(Description.LIST_PAGING, loginMember.getEmail(), pageNo+""));
				searchVO.setPageNo(pageNo);
				searchVO.setSearchKeyword(request.getParameter("searchKeyword"));
				searchVO.setSearchType(request.getParameter("searchType"));
			}
			catch (NumberFormatException nfe) {
				historyVO.setDescription( BuildDescription.get(Description.VISIT_ADMIN_MEMBER_PAGE, loginMember.getEmail()));
				searchVO = (MemberSearchVO) session.getAttribute("_MEMBERLIST_SEARCH_");
				if (searchVO == null) {
					searchVO = new MemberSearchVO();
					searchVO.setPageNo(0);
					searchVO.setSearchKeyword("");
					searchVO.setSearchType("1");
				}
			}
			
			session.setAttribute("_MEMBERLIST_SEARCH_", searchVO);
			
			MemberListVO members = memberBiz.getAllMember(searchVO, loginMember);
			
			
			historyBiz.addHistory(historyVO);
			
			request.setAttribute("members", members);
			request.setAttribute("searchVO", searchVO);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/member/memberList.jsp");
			rd.forward(request, response);
		}
	}

}
