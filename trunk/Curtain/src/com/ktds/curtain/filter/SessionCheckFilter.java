package com.ktds.curtain.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ktds.curtain.member.vo.MemberVO;


/**
 * Servlet Filter implementation class SessionCheckFilter
 */
public class SessionCheckFilter implements Filter {

	// white list
	private List<String> whiteList;
	
	private List<String> staticResourceList;
	
    /**
     * Default constructor. 
     */
    public SessionCheckFilter() {
    	whiteList = new ArrayList<String>();
    	// 모든 게스트가 필터를 지나쳐 갈 수 있는 URL
    	whiteList.add("/");
    	whiteList.add("/doLogin");
    	whiteList.add("/registerMember");
    	whiteList.add("/addNewMember");
    	whiteList.add("/checkId");
//    	whiteList.add("/favicon.ico");
    	
    	staticResourceList = new ArrayList<String>();
    	
    	// /resource/ --> resourceImage 등도 가능하니 /re
    	staticResourceList.add("/resource/");
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// request를 HttpServletRequest로 캐스팅 한 후
		HttpServletRequest req = (HttpServletRequest) request;
		
		String uri = req.getRequestURI();
		System.out.println(uri);
		
		// whiteList url가 없다면 session을 체크해라
		if ( !whiteList.contains(uri) ) {
			// resource로
			boolean isURIResourceFile = false;
			for ( String staticResource : staticResourceList ) {
				
				if ( uri.startsWith(staticResource) ) {
					isURIResourceFile = true;
					break;
				}
				
			}
			
			if ( !isURIResourceFile ) { 
			
				// session을 받아온다.
				HttpSession session = req.getSession();
				
				// Object로 넘어오기 때문에 MemberVO로 캐스팅해준다.
				MemberVO member = (MemberVO) session.getAttribute("_STU_MEMBER_");
				
				// 로그인 했는지 안 했는지는 null로 check
				// null이면 사용자가 로그인 하지 않은 상태
				if ( member == null ) {
					HttpServletResponse res = (HttpServletResponse) response;
					res.sendRedirect("/");
					return;
				}
//				else {
//					OperationHistoryVO historyVO = new OperationHistoryVO();
//					historyVO.setIp(request.getRemoteHost());
//					historyVO.setMemberId(member.getMemberId());
//					historyVO.setUrl(req.getRequestURI());
//					
//					request.setAttribute("OperationHistoryVO", historyVO);
//				}
			}
		}
		
		
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
