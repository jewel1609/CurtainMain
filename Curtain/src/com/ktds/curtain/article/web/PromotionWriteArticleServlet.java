package com.ktds.curtain.article.web;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ktds.curtain.article.biz.ArticleBiz;
import com.ktds.curtain.article.vo.ArticleVO;
import com.ktds.curtain.file.biz.FileBiz;
import com.ktds.curtain.file.vo.FileVO;
import com.ktds.curtain.history.biz.OperationHistoryBiz;
import com.ktds.curtain.history.vo.ActionCode;
import com.ktds.curtain.history.vo.BuildDescription;
import com.ktds.curtain.history.vo.Description;
import com.ktds.curtain.history.vo.OperationHistoryVO;
import com.ktds.curtain.member.biz.MemberBiz;
import com.ktds.curtain.member.vo.MemberVO;
import com.ktds.curtain.util.MultipartHttpServletRequest;
import com.ktds.curtain.util.Root;
import com.ktds.curtain.util.MultipartHttpServletRequest.MultipartFile;

/**
 * Servlet implementation class PromotionWriteArticleServlet
 */
public class PromotionWriteArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArticleBiz articleBiz;
	private MemberBiz memberBiz;
	private FileBiz fileBiz;
	private OperationHistoryBiz historyBiz;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PromotionWriteArticleServlet() {
		super();
		articleBiz = new ArticleBiz();
		fileBiz = new FileBiz();
		memberBiz = new MemberBiz();
		historyBiz = new OperationHistoryBiz();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendError(HttpServletResponse.SC_FORBIDDEN, "잘못된 요청입니다.");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		MultipartHttpServletRequest multipartRequest = new MultipartHttpServletRequest(request);

		String articleTitle = multipartRequest.getParameter("articleTitle");
		String articleDescription = multipartRequest.getParameter("articleDescription");
		int articleTypeId = Integer.parseInt(multipartRequest.getParameter("articleTypeId"));
		int boardId = Integer.parseInt(multipartRequest.getParameter("boardId"));

		MultipartFile file = multipartRequest.getFile("imgFile");
		String movieUrl = multipartRequest.getParameter("movieUrl");

		HttpSession session = request.getSession();
		MemberVO loginMember = (MemberVO) session.getAttribute("_MEMBER_");

		if (memberBiz.getPointbyEmail(loginMember) > 100) {

			List<String> wordList = (List<String>) session.getAttribute("_WORDLIST_");

			for (int i = 0; i < wordList.size(); i++) {
				if (articleTitle.contains(wordList.get(i)) || articleDescription.contains(wordList.get(i))) {
					response.sendRedirect("/secretArticleList?isFword=1");
					return;
				}
				break;
			}

			ArticleVO article = new ArticleVO();
			article.setArticleTitle(articleTitle);
			article.setArticleDesc(articleDescription);
			article.setArticleTypeId(articleTypeId);
			article.setBoardId(boardId);
			article.setEmail(loginMember.getEmail());

			boolean doWriteArticle = articleBiz.doWriteArticle(article, loginMember, request);

			if (doWriteArticle) {
				OperationHistoryVO historyVO = new OperationHistoryVO();
				historyVO.setIp(request.getRemoteHost());
				historyVO.setEmail(loginMember.getEmail());
				historyVO.setUrl(request.getRequestURI());
				historyVO.setActionCode(ActionCode.WRITE_PROMOTION);
				historyVO.setDescription(BuildDescription.get(Description.WRITE_PROMOTION, loginMember.getNickName()));
				historyVO.setEtc(BuildDescription.get(Description.DETAIL_DESCRIPTION, articleTitle,
						loginMember.getNickName(), articleDescription));

				historyBiz.addHistory(historyVO);
			}

			memberBiz.minusPointByPromotion(loginMember, request);

			int articleId = articleBiz.getArticleId();
			if (articleId > 0) {
				// 이미지 파일이 있을 경우
				if ( file != null && !file.getFileName().equals("")) {
					File upFile = file.write("C:\\Users\\206-001\\Documents\\curtain\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\Curtain\\resource\\img\\"+file.getFileName());
					FileVO fileVO = new FileVO();
					fileVO.setFileName(file.getFileName());
					fileVO.setFileLocation("C:\\Users\\206-001\\Documents\\curtain\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\Curtain\\resource\\img\\"+file.getFileName());
					fileVO.setArticleId(articleId);
					fileVO.setFileType(1);
					fileBiz.insertFile(fileVO);
				}
				// 영상 url이 있을 경우
				if (!movieUrl.equals("")) {
					FileVO fileVO = new FileVO();
					fileVO.setArticleId(articleId);
					fileVO.setFileName(movieUrl);
					fileVO.setFileLocation("");
					fileVO.setFileType(2);

					fileBiz.insertFile(fileVO);
				}
			}
			
			response.sendRedirect(Root.get(this) + "/promotionArticle");
		} // 포인트가 100이 넘을경우
		else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();

			out.println("<script>");
			out.println("alert('포인트가 부족합니다.');");
			out.println("window.history.back();");
			out.println("</script>");
			out.close();
		}
	}
}
