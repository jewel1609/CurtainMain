package com.ktds.oph.major.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ktds.oph.major.biz.MajorGroupBiz;
import com.ktds.oph.major.vo.MajorVO;
import com.ktds.oph.member.vo.MemberVO;

/**
 * Servlet implementation class UpdateMajorNameServlet
 */
public class UpdateMajorNameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MajorGroupBiz majorGroupBiz;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateMajorNameServlet() {
        super();
        majorGroupBiz = new MajorGroupBiz();
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
		String updateMajorName = request.getParameter("updateText");
		int majorId = Integer.parseInt(request.getParameter("majorId"));
		
		MajorVO majorVO = new MajorVO();
		majorVO.setMajorId(majorId);
		majorVO.setMajorName(updateMajorName);
		
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO) session.getAttribute("_MEMBER_");
		
		boolean updateName = majorGroupBiz.updateMajorName(majorVO, member);
		
// json 만드는 방법 "{ \"key\" : \"value\" }"
		StringBuffer json = new StringBuffer();
		json.append("{");
		json.append("\"result\" : true");
		json.append(", \"updateMajorName\" : \""+ updateMajorName +"\"");
		json.append(", \"updateName\" :" + updateName);
		json.append(", \"majorId\" : "+ majorId);
		json.append("}");

		PrintWriter out = response.getWriter();
		out.print(json.toString()); //String으로 바껴서 간다.
		out.flush();
		out.close();
	}

}
