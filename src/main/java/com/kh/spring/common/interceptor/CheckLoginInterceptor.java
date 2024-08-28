package com.kh.spring.common.interceptor;


import org.springframework.web.servlet.HandlerInterceptor;

import com.kh.spring.member.model.vo.Member;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class CheckLoginInterceptor implements HandlerInterceptor{
	// url요청에서 로그인이 되어있는지 검사하는 곳
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		Member loginUser = (Member)session.getAttribute("loginUser");
		
		if(loginUser == null) {
			String url = request.getRequestURI();
			
			String msg =null;
			if(url.contains("selectBoard.bo") || url.contains("selectAttm.at")){
				msg = "로그인 후 이용하세요.";
			} else {
				msg = "로그인 세션이 만료되어 로그인 화면으로 넘어갑니다";
			}
			response.setContentType("text/html; charset=UTF-8"); // 보내는 방식과 인코딩해줍니다. 메세지창에 띄워짐
			response.getWriter().write("<script>alert('"+msg +"');location.href = 'loginView.me';</script>");
			
			
			
			
			
			
			return false;
		}
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
	
	
	
}
