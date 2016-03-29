

package com.ktds.curtain.dupl.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ktds.curtain.dupl.biz.DuplBiz;
import com.ktds.curtain.dupl.vo.DuplVO;
import com.ktds.curtain.member.vo.MemberVO;

/**
 * Servlet implementation class DuplicationCheckNickNameServlet
 */
public class DuplicationCheckNickNameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private DuplBiz duplBiz;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DuplicationCheckNickNameServlet() {
        super();
        duplBiz = new DuplBiz();
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
		
		
		String userNickName = request.getParameter("userNickName");
		System.out.println(userNickName);
		boolean isCheckId = duplBiz.isExistNickName(userNickName);
		System.out.println(isCheckId);
		
		if ( isCheckId ) { //중복이면
			StringBuffer json = new StringBuffer(); 
			json.append("{");
			json.append("\"isCheckId\" : " + isCheckId);
			json.append("}");
			
			PrintWriter out = response.getWriter();
			out.print(json.toString());
			out.flush();
			out.close();
		}
		else { //중복이아니면
			StringBuffer json = new StringBuffer(); 
			json.append("{");
			json.append("\"isCheckId\" : " + isCheckId);
			json.append("}");
			
			PrintWriter out = response.getWriter();
			out.print(json.toString());
			out.flush();
			out.close();
		}
		
	}

}



