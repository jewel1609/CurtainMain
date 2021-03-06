package com.ktds.curtain.articleScrab.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ktds.curtain.articleScrab.biz.ScrabBiz;
import com.ktds.curtain.articleScrab.vo.ArticleScrabVO;
import com.ktds.curtain.history.biz.OperationHistoryBiz;
import com.ktds.curtain.history.vo.ActionCode;
import com.ktds.curtain.history.vo.BuildDescription;
import com.ktds.curtain.history.vo.Description;
import com.ktds.curtain.history.vo.OperationHistoryVO;
import com.ktds.curtain.member.vo.MemberVO;

/**
 * Servlet implementation class ScrabServlet
 */
public class ScrabServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private ScrabBiz scrabBiz;
	private OperationHistoryBiz historyBiz;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ScrabServlet() {
        super();
        scrabBiz = new ScrabBiz();
        historyBiz = new OperationHistoryBiz();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendError(HttpServletResponse.SC_FORBIDDEN, "잘못된 요청입니다.");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String scrabArticleId = request.getParameter("articleId");
		int articleId = Integer.parseInt(scrabArticleId.substring(5));
		
		int boardId = Integer.parseInt(request.getParameter("boardId"));
		
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO) session.getAttribute("_MEMBER_");
		
		ArticleScrabVO scrabVO = new ArticleScrabVO();
		scrabVO.setArticleId(articleId);
		scrabVO.setEmail(member.getEmail());
		scrabVO.setBoardId(boardId);
		
		scrabBiz.insertOrDeleteScrabData(scrabVO);
		
		boolean isExistScrabData = scrabBiz.isExistScrabData(scrabVO);
		if ( isExistScrabData ) {
			
			OperationHistoryVO historyVO = new OperationHistoryVO();
			historyVO.setIp(request.getRemoteHost());
			historyVO.setEmail(member.getEmail());
			historyVO.setUrl(request.getRequestURI());
			historyVO.setActionCode(ActionCode.DO_SCRAB);
			historyVO.setDescription( BuildDescription.get(Description.DO_SCRAB, member.getNickName(), request.getParameter("articleId") ) );
			
			historyBiz.addHistory(historyVO);
			
			StringBuffer json = new StringBuffer();
			json.append("{");
			json.append("\"result\" : true");
			json.append(", \"isScrab\" : " + isExistScrabData);
			json.append(", \"articleId\" : " + articleId);
			json.append("}");
			
			PrintWriter out = response.getWriter();
			out.print( json.toString() );
			out.flush();
			out.close();
		}
		else if ( !isExistScrabData ) {
			
			OperationHistoryVO historyVO = new OperationHistoryVO();
			historyVO.setIp(request.getRemoteHost());
			historyVO.setEmail(member.getEmail());
			historyVO.setUrl(request.getRequestURI());
			historyVO.setActionCode(ActionCode.CANCEL_SCRAB);
			historyVO.setDescription( BuildDescription.get(Description.CANCEL_SCRAB, member.getNickName(), request.getParameter("articleId") ) );
			
			historyBiz.addHistory(historyVO);
			
			StringBuffer json = new StringBuffer();
			json.append("{");
			json.append("\"result\" : true");
			json.append(", \"isScrab\" : " + isExistScrabData);
			json.append(", \"articleId\" : " + articleId);
			json.append("}");
			
			PrintWriter out = response.getWriter();
			out.print( json.toString() );
			out.flush();
			out.close();
		}
		
		
	}

}
