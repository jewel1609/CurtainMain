package com.ktds.oph.univ.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ktds.oph.member.vo.MemberVO;
import com.ktds.oph.operationHistory.biz.OperationHistoryBiz;
import com.ktds.oph.operationHistory.vo.ActionCode;
import com.ktds.oph.operationHistory.vo.BuildDescription;
import com.ktds.oph.operationHistory.vo.Description;
import com.ktds.oph.operationHistory.vo.OperationHistoryVO;
import com.ktds.oph.univ.biz.UnivBiz;
import com.ktds.oph.univ.vo.UnivVO;
import com.ktds.oph.util.Root;

/**
 * Servlet implementation class RegisterNewUnivServlet
 */
public class RegisterNewUnivServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UnivBiz univBiz;
	private OperationHistoryBiz historyBiz;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterNewUnivServlet() {
        super();
        univBiz = new UnivBiz();
        historyBiz = new OperationHistoryBiz();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendError(HttpServletResponse.SC_FORBIDDEN,"잘못된 요청입니다.");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String newUniv = request.getParameter("newUniv");
		
		UnivVO univVO = new UnivVO();
		univVO.setUnivName(newUniv);
		
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO) session.getAttribute("_MEMBER_");
		
		boolean registerUniv = univBiz.registerUniv(univVO, member);
		
		OperationHistoryVO historyVO = new OperationHistoryVO();
		historyVO.setIp(request.getRemoteHost());
		historyVO.setEmail(member.getEmail());
		historyVO.setUrl(request.getRequestURI());
		
		if (registerUniv) {
			historyVO.setActionCode(ActionCode.ADMIN_UNIV_ADD);
			historyVO.setDescription( BuildDescription.get(Description.DO_ADMIN_UNIV_ADD, member.getEmail()));
			historyVO.setEtc( BuildDescription.get(Description.DETAIL_UNIV_ADD, newUniv));
			
			historyBiz.addHistory(historyVO);
			response.sendRedirect(Root.get(this) + "/univList");
		}
		else {
			historyVO.setActionCode(ActionCode.ADMIN_UNIV_ADD_ERROR);
			historyVO.setDescription( BuildDescription.get(Description.DO_ADMIN_UNIV_ADD_ERROR, member.getEmail()));
			historyVO.setEtc( BuildDescription.get(Description.DETAIL_UNIV_ADD_ERROR, newUniv));
			
			historyBiz.addHistory(historyVO);
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			 
			out.println("<script>"); 
			out.println("alert('학교명이 중복됩니다.');");
			out.println("window.history.back();");
			out.println("</script>");
			out.flush();
			out.close();
		}
		
	}

}
