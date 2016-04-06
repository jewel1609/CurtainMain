package com.ktds.oph.univ.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ktds.oph.member.vo.MemberVO;
import com.ktds.oph.univ.biz.UnivBiz;
import com.ktds.oph.univ.vo.UnivVO;
import com.ktds.oph.util.Root;

/**
 * Servlet implementation class UpdateUnivNameServlet
 */
public class UpdateUnivNameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UnivBiz univBiz;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUnivNameServlet() {
        super();
        univBiz = new UnivBiz();
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
