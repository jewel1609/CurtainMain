package com.ktds.oph.history.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ktds.oph.history.biz.HistoryBiz;
import com.ktds.oph.history.vo.HistoryListVO;
import com.ktds.oph.history.vo.HistorySearchVO;
import com.ktds.oph.member.biz.MemberBiz;
import com.ktds.oph.member.vo.MemberVO;

/**
 * Servlet implementation class HistoryListServlet
 */
public class HistoryListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HistoryListServlet() {
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
			RequestDispatcher rd = request.getRequestDispatcher("//WEB-INF/view/history/historyList.jsp");
			rd.forward(request, response);
	}

}
