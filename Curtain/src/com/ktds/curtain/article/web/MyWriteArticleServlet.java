package com.ktds.curtain.article.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ktds.curtain.article.biz.ArticleBiz;
import com.ktds.curtain.article.vo.ArticleVO;
import com.ktds.curtain.history.biz.OperationHistoryBiz;
import com.ktds.curtain.history.vo.ActionCode;
import com.ktds.curtain.history.vo.BuildDescription;
import com.ktds.curtain.history.vo.Description;
import com.ktds.curtain.history.vo.OperationHistoryVO;
import com.ktds.curtain.member.vo.MemberVO;

/**
 * Servlet implementation class MyWriteArticleServlet
 */
public class MyWriteArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private ArticleBiz articleBiz;
	private OperationHistoryBiz historyBiz;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyWriteArticleServlet() {
        super();
        articleBiz = new ArticleBiz();
        historyBiz = new OperationHistoryBiz();
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
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		
		MemberVO member = (MemberVO) session.getAttribute("_MEMBER_");
		List<ArticleVO> myWriteArticle = articleBiz.showMyWriteArticle(member);
		
		OperationHistoryVO historyVO = new OperationHistoryVO();
		historyVO.setIp(request.getRemoteHost());
		historyVO.setEmail(member.getEmail());
		historyVO.setUrl(request.getRequestURI());
		historyVO.setActionCode(ActionCode.MY_WRITE_ARTICLE);
		historyVO.setDescription( BuildDescription.get(Description.VISIT_MY_WRITE_ARTICLE, member.getNickName()) );
		
		historyBiz.addHistory(historyVO);
		
		
		
		request.setAttribute("myWriteArticle", myWriteArticle);	
		RequestDispatcher rd = request.getRequestDispatcher("//WEB-INF/view/article/myWriteArticle.jsp");
		rd.forward(request, response);
	}

}
