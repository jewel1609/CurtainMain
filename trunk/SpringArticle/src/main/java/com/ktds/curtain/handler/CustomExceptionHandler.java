package com.ktds.curtain.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice("com.ktds.curtain")
public class CustomExceptionHandler {

	@ExceptionHandler({RuntimeException.class})
	public ModelAndView runtimeExceptionHandler(RuntimeException re, HttpServletRequest request) {
		ModelAndView view = new ModelAndView();
		view.setViewName("error/500");
		view.addObject("message", re.getMessage());
		
		// 이 페이지를 호출한 페이지를 말한다.
		String referer = request.getHeader("Referer");
		view.addObject("from", referer);
		
		view.addObject("content","내가보냄...");
		
		return view;
	}
}
