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
import com.ktds.curtain.file.vo.FileVO;
import com.ktds.curtain.history.biz.OperationHistoryBiz;
import com.ktds.curtain.history.vo.ActionCode;
import com.ktds.curtain.history.vo.BuildDescription;
import com.ktds.curtain.history.vo.Description;
import com.ktds.curtain.history.vo.OperationHistoryVO;
import com.ktds.curtain.member.vo.MemberVO;

/**
 * Servlet implementation class ShowDetailServlet
 */
public class ShowDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArticleBiz articleBiz;
	private OperationHistoryBiz historyBiz;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowDetailServlet() {
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

		int isFword = 0;

		int articleId = Integer.parseInt(request.getParameter("articleId"));
//		int boardId = Integer.parseInt(request.getParameter("boardId"));
		
		HttpSession session = request.getSession();
		MemberVO stdMember = (MemberVO) session.getAttribute("_MEMBER_");
		
		ArticleVO article = articleBiz.showDetail(articleId, stdMember);
		List<FileVO> files = articleBiz.getFileListByArticleId(articleId);
		
		OperationHistoryVO historyVO = new OperationHistoryVO();
		historyVO.setIp(request.getRemoteHost());
		historyVO.setEmail(stdMember.getEmail());
		historyVO.setUrl(request.getRequestURI());
		historyVO.setActionCode(ActionCode.DETAIL_PAGE);
		historyVO.setDescription( BuildDescription.get(Description.VISIT_DETAIL, stdMember.getNickName(), request.getParameter("articleId")) );
		historyVO.setEtc(BuildDescription.get(Description.DETAIL_DESCRIPTION, article.getArticleTitle(), stdMember.getNickName(), article.getArticleDesc() ));
		
		historyBiz.addHistory(historyVO);
		
		
		try {
			isFword = Integer.parseInt(request.getParameter("isFword"));
			request.setAttribute("isFword", isFword);
		}
		catch (NumberFormatException nfe) {
			isFword = 0;
		}
		finally {
			request.setAttribute("article", article);
//			request.setAttribute("boardId", boardId);
			request.setAttribute("files", files);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/article/detail.jsp");
			rd.forward(request, response);
			
		}

	}

}
