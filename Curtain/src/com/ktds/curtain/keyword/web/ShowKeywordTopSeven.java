package com.ktds.curtain.keyword.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ktds.curtain.keyword.biz.KeywordBiz;

/**
 * Servlet implementation class ShowKeywordTopSeven
 */
public class ShowKeywordTopSeven extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private KeywordBiz keywordBiz;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowKeywordTopSeven() {
        super();
        keywordBiz = new KeywordBiz();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		int boardId = (int) session.getAttribute("_BOARD_ID_");
		
		System.out.println(boardId);
		
		List<String> keywords = keywordBiz.getKeywordTopSeven(boardId);
		
		StringBuffer json = new StringBuffer();
		
		int i = 0;
		
		json.append("{");
		for (String keyword : keywords) {
			json.append("\"keyword"+ (i++) + "\" : " + "\"" +keyword + "\"");
			if (i != keywords.size()) {
				json.append(",");
			}
		}
		json.append("}");
		
		PrintWriter out = response.getWriter();
		out.print(json.toString());
		out.flush();
		out.close();
	}

}
