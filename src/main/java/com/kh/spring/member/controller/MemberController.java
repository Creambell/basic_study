package com.kh.spring.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.kh.spring.member.model.service.MemberService;

@SessionAttributes("loginUser")
@Controller
public class MemberController {
	
	@Autowired
	private MemberService mService;
	@Autowired
	BCryptPasswordEncoder bcrypt;
	
	// 내 정보 수정 페이지
	@GetMapping("editPage.me")
	public String moveEditPage() {
		return "editPage";
	}
	
	// 내 댓글 보기 페이지
	@GetMapping("myComment.me")
	public String moveMyComment() {
		return  "myComment";
	}
	
	// 내 게시글 보기 페이지
	@GetMapping("myPost.me")
	public String moveMyPost() {
		return "myPost";
	}
	
	// 로그인 페이지
		@GetMapping("login.me")
		public String login() {
			return "login";
	}
		
	// 회원가입 페이지
	@GetMapping("joinMember.me")
	public String joinMember() {
		return "joinMember";
	}
}
