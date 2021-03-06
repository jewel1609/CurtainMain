package com.ktds.oph.major.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ktds.oph.major.biz.MajorGroupBiz;
import com.ktds.oph.major.vo.MajorGroupListVO;
import com.ktds.oph.major.vo.MajorGroupSearchVO;
import com.ktds.oph.member.biz.MemberBiz;
import com.ktds.oph.member.vo.MemberVO;
import com.ktds.oph.operationHistory.biz.OperationHistoryBiz;
import com.ktds.oph.operationHistory.vo.ActionCode;
import com.ktds.oph.operationHistory.vo.BuildDescription;
import com.ktds.oph.operationHistory.vo.Description;
import com.ktds.oph.operationHistory.vo.OperationHistoryVO;
import com.ktds.oph.univ.vo.UnivSearchVO;

/**
 * Servlet implementation class MajorListServlet
 */
public class MajorGroupListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberBiz memberBiz;
	private MajorGroupBiz majorGroupBiz;
	private OperationHistoryBiz historyBiz;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MajorGroupListServlet() {
        super();
        memberBiz = new MemberBiz();
        majorGroupBiz = new MajorGroupBiz();
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
		historyVO.setActionCode(ActionCode.ADMIN_MAJOR_GROUP_PAGE);
		
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
			MajorGroupSearchVO majorSearchVO = new MajorGroupSearchVO();
		
			try {
				pageNo = Integer.parseInt(request.getParameter("pageNo"));
				
				majorSearchVO.setPageNo(pageNo);
				majorSearchVO.setSearchKeyword(request.getParameter("searchKeyword"));
				majorSearchVO.setSearchType(request.getParameter("searchType"));
				historyVO.setDescription( BuildDescription.get(Description.LIST_PAGING, loginMember.getEmail(), pageNo+""));
			}
			catch (NumberFormatException nfe) {
				historyVO.setDescription( BuildDescription.get(Description.VISIT_ADMIN_MAJOR_GROUP_PAGE, loginMember.getEmail()));
				majorSearchVO = (MajorGroupSearchVO) session.getAttribute("_SEARCH_");
				
				if ( majorSearchVO == null ) {
					majorSearchVO = new MajorGroupSearchVO();
					majorSearchVO.setPageNo(0);
					majorSearchVO.setSearchKeyword("");
					majorSearchVO.setSearchType("1");
				}
			}
			
			session.setAttribute("_SEARCH_", majorSearchVO);
			
			MajorGroupListVO majors = majorGroupBiz.getAllMajor(majorSearchVO);
			
			
			historyBiz.addHistory(historyVO);
			
			request.setAttribute("majorSearchVO", majorSearchVO);
			request.setAttribute("majors", majors);
			RequestDispatcher rd = request.getRequestDispatcher("//WEB-INF/view/major/majorGroupList.jsp");
			rd.forward(request, response);
		}
		
	}

}
