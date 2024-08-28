package com.kh.spring.common.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import com.kh.spring.member.model.vo.Member;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class CheckAdminInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		Member loginUser = (Member)session.getAttribute("loginUser");
		String msg = null;
		
		if(loginUser == null || loginUser.getIsAdmin().equals("N")) {
			msg = "권한이 없습니다";
			
			response.setContentType("text/html; charset=UTF-8"); // 
			response.getWriter().write("<script> alert('"+ msg +"'); location.href='home.do';</script>");
			
			return false;
		}
		
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

}
