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
	
	@GetMapping("editPage.me")
	public String moveEditPage() {
		return "editPage";
	}
	
	@GetMapping("myComment.me")
	public String moveMyComment() {
		return  "myComment";
	}
	
	
	@GetMapping("myPost.me")
	public String moveMyPost() {
		return "myPost";
	}
}
