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

/**
 * Servlet implementation class MajorListServlet
 */
public class MajorGroupListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberBiz memberBiz;
	private MajorGroupBiz majorGroupBiz;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MajorGroupListServlet() {
        super();
        memberBiz = new MemberBiz();
        majorGroupBiz = new MajorGroupBiz();
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
			
			MajorGroupSearchVO majorSearchVO = new MajorGroupSearchVO();
			majorSearchVO.setPageNo(pageNo);
			
			MajorGroupListVO majors = majorGroupBiz.getAllMajor(majorSearchVO);
			
			request.setAttribute("majors", majors);
			RequestDispatcher rd = request.getRequestDispatcher("//WEB-INF/view/major/majorGroupList.jsp");
			rd.forward(request, response);
		}
		
	}

}
