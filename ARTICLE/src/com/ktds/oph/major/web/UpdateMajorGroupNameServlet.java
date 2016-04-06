package com.ktds.oph.major.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ktds.oph.major.biz.MajorGroupBiz;
import com.ktds.oph.major.vo.MajorGroupVO;
import com.ktds.oph.member.vo.MemberVO;
import com.ktds.oph.operationHistory.biz.OperationHistoryBiz;
import com.ktds.oph.operationHistory.vo.ActionCode;
import com.ktds.oph.operationHistory.vo.BuildDescription;
import com.ktds.oph.operationHistory.vo.Description;
import com.ktds.oph.operationHistory.vo.OperationHistoryVO;
import com.ktds.oph.univ.vo.UnivVO;

/**
 * Servlet implementation class UpdateMajorGroupNameServlet
 */
public class UpdateMajorGroupNameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MajorGroupBiz majorGroupBiz;
    private OperationHistoryBiz historyBiz;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateMajorGroupNameServlet() {
        super();
        majorGroupBiz = new MajorGroupBiz();
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
		String updateMajorGroupName = request.getParameter("updateText");
		int majorGroupId = Integer.parseInt(request.getParameter("majorGroupId"));
		
		MajorGroupVO majorGroupVO = new MajorGroupVO();
		majorGroupVO.setMajorGroupId(majorGroupId);
		majorGroupVO.setMajorGroupName(updateMajorGroupName);
		
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO) session.getAttribute("_MEMBER_");
		
		boolean updateName = majorGroupBiz.updateMajorGroupName(majorGroupVO, member);
		
		OperationHistoryVO historyVO = new OperationHistoryVO();
		historyVO.setIp(request.getRemoteHost());
		historyVO.setEmail(member.getEmail());
		historyVO.setUrl(request.getRequestURI());
		historyVO.setActionCode(ActionCode.MODIFY_MAJOR_GROUP);
		historyVO.setDescription( BuildDescription.get(Description.MODIFY_MAJOR_GROUP, member.getEmail(),  majorGroupId+""));
		historyVO.setEtc( BuildDescription.get(Description.DETAIL_MODIFY_MAJOR_GROUP, updateMajorGroupName));
		
		historyBiz.addHistory(historyVO);
		
// json 만드는 방법 "{ \"key\" : \"value\" }"

		// json 만드는 방법 "{ \"key\" : \"value\" }"
		StringBuffer json = new StringBuffer();
		json.append("{");
		json.append("\"result\" : true");
		json.append(", \"updateMajorGroupName\" : \""+ updateMajorGroupName +"\"");
		json.append(", \"updateName\" :" + updateName);
		json.append(", \"majorGroupId\" : "+ majorGroupId);
		json.append("}");

		PrintWriter out = response.getWriter();
		out.print(json.toString()); //String으로 바껴서 간다.
		out.flush();
		out.close();
	
	}

}
