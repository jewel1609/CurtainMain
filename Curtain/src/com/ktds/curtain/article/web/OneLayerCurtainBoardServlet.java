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
import com.ktds.curtain.article.vo.BoardId;
import com.ktds.curtain.articleDislike.biz.DislikeBiz;
import com.ktds.curtain.history.biz.OperationHistoryBiz;
import com.ktds.curtain.history.vo.ActionCode;
import com.ktds.curtain.history.vo.BuildDescription;
import com.ktds.curtain.history.vo.Description;
import com.ktds.curtain.history.vo.OperationHistoryVO;
import com.ktds.curtain.member.vo.MemberVO;

/**
 * Servlet implementation class OneLayerCurtainBoardServlet
 */
public class OneLayerCurtainBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArticleBiz articleBiz;
	private DislikeBiz dislikeBiz;
	private OperationHistoryBiz historyBiz;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OneLayerCurtainBoardServlet() {
        super();
		articleBiz = new ArticleBiz();
		dislikeBiz = new DislikeBiz();
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
		MemberVO stdMember = (MemberVO) session.getAttribute("_MEMBER_");
		session.setAttribute("_BOARD_ID_", 5);
		
		OperationHistoryVO historyVO = new OperationHistoryVO();
		historyVO.setIp(request.getRemoteHost());
		historyVO.setEmail(stdMember.getEmail());
		historyVO.setUrl(request.getRequestURI());
		historyVO.setActionCode(ActionCode.ARTICLE_SECRET_ONE);
		historyVO.setDescription( BuildDescription.get(Description.VISIT_ARTICLE_SECRET_ONE, stdMember.getNickName()));
		
		historyBiz.addHistory(historyVO);
		
		List<ArticleVO> secretArticles = articleBiz.showSecretArticle(stdMember, BoardId.SECRET_BOARD_LEVEL1);
		ArticleVO topArticle = articleBiz.showTopArticle(stdMember, BoardId.SECRET_BOARD_LEVEL1);
		
		request.setAttribute("topArticle", topArticle);
		request.setAttribute("secretArticles", secretArticles);
	
		RequestDispatcher rd = request.getRequestDispatcher("//WEB-INF/view/article/oneLayerCurtain.jsp");
		rd.forward(request, response);
	}

}
