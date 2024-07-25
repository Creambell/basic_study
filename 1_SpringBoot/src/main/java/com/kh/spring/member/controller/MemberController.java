package com.kh.spring.member.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.kh.spring.member.model.exception.MemberException;
import com.kh.spring.member.model.service.MemberService;
import com.kh.spring.member.model.vo.Member;

import jakarta.servlet.http.HttpSession;

@SessionAttributes("loginUser")
@Controller
public class MemberController {
	
	@Autowired
	private MemberService mService;
	@Autowired
	BCryptPasswordEncoder bcrypt;
	
	@GetMapping("loginView.me")
	public String moveLoginView() {
		return "login";
	}
	
	@PostMapping("login.me")
	public String login(@ModelAttribute Member m, Model model) {
		Member loginUser = mService.login(m);
		if(bcrypt.matches(m.getPwd(), loginUser.getPwd())) {
			model.addAttribute("loginUser",loginUser);
			return "redirect:home.do";
		}else {
			throw new MemberException("로그인을 실패하였습니다");
		}
	}
	
	@GetMapping("logout.me")
	public String logout(SessionStatus status) {
		status.setComplete();
		return "redirect:home.do";
	}

	@GetMapping("enroll.me")
	public String enroll() {
		return "enroll";
	}
	
	@GetMapping("checkId.me")
	public void checkId(@RequestParam("id") String id ,PrintWriter out) {
		int count = mService.checkId(id);
		String result = count == 0 ? "yes" : "no";
		out.print(result);
		// return result; // aJax로 넘어갈테니 반환을 굳이 경로로 하지 않아도 된다.
	}
	
	@GetMapping("checknickName.me")
	@ResponseBody
	public String checknickName(@RequestParam("nickName") String nickName) {
		int count = mService.checknickName(nickName);
		return count == 0 ? "yes" : "no";  // @ResponseBody 를 써주면 데이터상태로 보낼 수 있습니다.
	}
	
	
	
	@PostMapping("insertMember.me")
	public String insertMember(@ModelAttribute Member m, 
						       @RequestParam("emailId") String emailId,
						       @RequestParam("emailDomain") String emailDomain) {
		
		String email = null;
		if(!emailId.equals("")) {
			email = emailId + "@" + emailDomain;
		}
		m.setEmail(email);
		m.setPwd(bcrypt.encode(m.getPwd()));
		
		int result = mService.insertMember(m);
		if(result > 0) {
			return "redirect:home.do";
		}else {
			throw new MemberException("회원가입을 실패하였습니다.");
		}
	}
	
	@GetMapping("myInfo.me")
	public String myInfo(HttpSession session, Model model) {
		String id = ((Member)session.getAttribute("loginUser")).getId();
		ArrayList<HashMap<String,Object>> list = mService.selectMyList(id);
		model.addAttribute("list", list);
		System.out.println(list);
		return "myInfo";
	}
	
	@GetMapping("editMyInfo.me")
	public String editMyInfo() {
		return "edit";
	}
	
	@PostMapping("updateMember.me")
	public String updateMember(@ModelAttribute Member m, @RequestParam("emailId") String emailId, @RequestParam("emailDomain") String emailDomain, Model model) {
		String email = null;
		if(!emailId.trim().equals("")) {
			email = emailId + "@" + emailDomain;
		}
		m.setEmail(email);
		int result = mService.updateMember(m);
		if(result > 0) {
			model.addAttribute("loginUser", mService.login(m));
			return "redirect:myInfo.me";
		}else {
			throw new MemberException("회원정보 수정 실패");
		}
	}
	
	@GetMapping("deleteMember.me")
	public String deleteMember(Model model) {
		String id = ((Member)model.getAttribute("loginUser")).getId();
		int result = mService.deleteMember(id);
		if(result > 0) {
			return "redirect:logout.me";
		}else {
			throw new MemberException("회원 탈퇴 실패");
		}
	}
	
	@PostMapping("updatePassword.me")
	public String updatePassword(@RequestParam("currentPwd") String currentpwd, @RequestParam("newPwd") String newPwd, Model model) {
		Member m = (Member)model.getAttribute("loginUser");
		if(bcrypt.matches(currentpwd, m.getPwd())) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("id", m.getId());
			map.put("newPwd", bcrypt.encode(newPwd));
			
			int result = mService.updatePwd(map);
			if(result > 0) {
				model.addAttribute("loginUser", mService.login(m));
				return "redirect:home.do";
			}else {
				throw new MemberException("비밀번호 수정 실패");
			}
		}else {
			throw new MemberException("비밀번호 수정 실패");
		}
	}
}
