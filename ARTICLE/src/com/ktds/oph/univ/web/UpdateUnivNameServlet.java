package com.ktds.oph.univ.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.BorderFactory;

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
 * Servlet implementation class UpdateUnivNameServlet
 */
public class UpdateUnivNameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UnivBiz univBiz;
	private OperationHistoryBiz historyBiz;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUnivNameServlet() {
        super();
        univBiz = new UnivBiz();
        historyBiz = new OperationHistoryBiz();
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
		String updateUnivName = request.getParameter("updateText");
		int univId = Integer.parseInt(request.getParameter("univId"));
		
		UnivVO univVO = new UnivVO();
		univVO.setUnivId(univId);
		univVO.setUnivName(updateUnivName);
		
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO) session.getAttribute("_MEMBER_");
		
		boolean updateName = univBiz.updateUnivName(univVO, member);
		
		OperationHistoryVO historyVO = new OperationHistoryVO();
		historyVO.setIp(request.getRemoteHost());
		historyVO.setEmail(member.getEmail());
		historyVO.setUrl(request.getRequestURI());
		historyVO.setActionCode(ActionCode.MODIFY_UNIV);
		historyVO.setDescription( BuildDescription.get(Description.MODIFY_UNIV, member.getEmail(),  univId+""));
		historyVO.setEtc( BuildDescription.get(Description.DETAIL_MODIFY_UNIV, updateUnivName));
		
		historyBiz.addHistory(historyVO);
		
// json 만드는 방법 "{ \"key\" : \"value\" }"
		StringBuffer json = new StringBuffer();
		json.append("{");
		json.append("\"result\" : true");
		json.append(", \"updateUnivName\" : \""+ updateUnivName +"\"");
		json.append(", \"updateName\" :" + updateName);
		json.append(", \"univId\" : "+ univId);
		json.append("}");

		PrintWriter out = response.getWriter();
		out.print(json.toString()); //String으로 바껴서 간다.
		out.flush();
		out.close();
	}

}
