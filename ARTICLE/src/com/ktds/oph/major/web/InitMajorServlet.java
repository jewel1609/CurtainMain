package com.ktds.oph.major.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ktds.oph.major.vo.MajorGroupSearchVO;
import com.ktds.oph.util.Root;

/**
 * Servlet implementation class InitMajorServlet
 */
public class InitMajorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InitMajorServlet() {
        super();
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
		MajorGroupSearchVO majorSearchVO = (MajorGroupSearchVO) session.getAttribute("_SEARCH_");
		int majorGroupId = majorSearchVO.getMajorGroupId();
		session.removeAttribute("_SEARCH_");
		
		response.sendRedirect(Root.get(this)+"/detailMajor?majorGroupId="+majorGroupId);
	}

}
