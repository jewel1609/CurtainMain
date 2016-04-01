package com.ktds.curtain.member.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.curtain.member.biz.MemberBiz;
import com.ktds.curtain.util.Root;

/**
 * Servlet implementation class DoSecedeMemberServlet
 */
public class DoSecedeMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private MemberBiz memberBiz;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoSecedeMemberServlet() {
        super();
        memberBiz = new MemberBiz();
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


		
		String memberEmail = request.getParameter("memberEmail");
		SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ( "yyyy/MM/dd", Locale.KOREA );
		Date currentTime = new Date ( );
		String mTime = mSimpleDateFormat.format ( currentTime );
		
		System.out.println(memberEmail+"<탈퇴회원 이메일");
		System.out.println(mTime+"<탈퇴한 날짜");
		
		memberBiz.secedeMember(memberEmail);
		memberBiz.secedeMemberAdd(memberEmail, mTime);
		
		
		
		request.getSession().invalidate();
		response.sendRedirect(Root.get(this) + "/main.jsp");
	}

}
