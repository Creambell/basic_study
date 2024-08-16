package com.kh.spring.member.controller;

import java.io.PrintWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.kh.spring.member.model.exception.MemberException;
import com.kh.spring.member.model.service.MemberService;
import com.kh.spring.member.model.vo.Member;


@SessionAttributes("loginUser") //로그인 유저 들어오면 세션에 올려줌.

@Controller
public class MemberController {
		
	@Autowired
	private MemberService mService;
	
	@Autowired
	BCryptPasswordEncoder bcrypt;
	
	// 로그인 뷰 이동
	@GetMapping("loginView.me")
	public String moveLoginView(){
		return "login";
	}
	
	// 로그인
	@PostMapping("login.me")
	public String login(@ModelAttribute Member m, Model model) {
	    Member loginUser = mService.login(m);
	    System.out.println("로그인 해본거 " + m);
	    System.out.println("DB: " + loginUser);
	    
	    if (loginUser != null && bcrypt.matches(m.getUserPwd(), loginUser.getUserPwd())) {
	        model.addAttribute("loginUser", loginUser);
	        return "redirect:home.do";
	    } else {
	        throw new MemberException("로그인 실패");
	    }
	}
	
	// 회원가입 페이지 이동
	@GetMapping("joinMember.me")
	public String joinMember() {
		return "joinMember";
	}
	// 회원 가입
	@PostMapping("insertMember.me")
	public String insertMember(@ModelAttribute Member m){
		
		m.setUserPwd(bcrypt.encode(m.getUserPwd()));

		int result = mService.insertMember(m);
		if (result > 0) {
			return "redirect:home.do";
		} else {
			throw new MemberException("회원가입 실패");
		}
	}
	// 아이디 체크
	@GetMapping("checkId.me")		  
	public void checkId(@RequestParam("id") String userId, PrintWriter out) {
		int count = mService.checkId(userId);
		String result = count == 0 ? "yes" : "no"; 
		out.print(result);
	}
	
	// 로그아웃
	@GetMapping("logout.me")
	public String logout(SessionStatus status) {
		status.setComplete();
		return "redirect:home.do";
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
	
	// 내 정보 보기
		@GetMapping("myInfo.me")
		public String myInfo() {
			return "myInfo";
		}
		
	// 내 정보 수정
	@GetMapping("editMyInfo.me")
	public String editMyInfo() {
		return "editMyInfo";
	}

}