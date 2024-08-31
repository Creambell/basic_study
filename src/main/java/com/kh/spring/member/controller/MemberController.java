package com.kh.spring.member.controller;

import java.io.PrintWriter;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
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
	
	@PostMapping("login.me")
	public String login(@ModelAttribute Member m, Model model) {
	    System.out.println("입력된 사용자 정보: " + m);
	    
	    Member loginUser = mService.login(m);
	    System.out.println("DB에서 조회된 사용자: " + loginUser);
	    
	    if (loginUser == null) {
	        throw new MemberException("사용자를 찾을 수 없습니다.");
	    }
	    
	    if (bcrypt.matches(m.getUserPwd(), loginUser.getUserPwd())) {
	        model.addAttribute("loginUser", loginUser);
	        return "redirect:home.do";
	    } else {
	        throw new MemberException("비밀번호가 일치하지 않습니다.");
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
		System.out.println(m);
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

	// 내 정보 보기
	@GetMapping("myInfo.me")
    public String myInfo(Model model, @SessionAttribute("loginUser") Member loginUser) {
        int userNo = loginUser.getUserNo();
        
        int postCount = mService.countMemberPost(userNo);
        int replyCount = mService.countMemberReply(userNo);
        
        model.addAttribute("postCount", postCount);
        model.addAttribute("replyCount", replyCount);
        
        return "myInfo";
    }
	
	// 내 정보 수정페이지 이동
	@GetMapping("editMyInfo.me")
	public String editMyInfo() {
		return "editMyInfo";
	}
	
	// 내 정보 수정
	@PostMapping("updateMember.me")
	public String updateMember(@ModelAttribute Member m,
	                           @RequestParam(value="currentPwd") String currentPwd,
	                           @RequestParam(value="userPwd", required = false) String userPwd,
	                           @SessionAttribute(name = "loginUser", required = false) Member loginUser,
	                           Model model) {
		
	    	//현재 비번 입력하고, 암호화 풀고 비교하고 일치할 경우
	    	if (bcrypt.matches(currentPwd, loginUser.getUserPwd())) {
	        // 새 비번 입력되면
	    	if (userPwd != null && !userPwd.isEmpty()) {
	    		// 새 비번 입력되면 암호화해서 setUserPwd.
	    		m.setUserPwd(bcrypt.encode(userPwd));
	    	} else {
	    	    // 새 비번제공되지 않았을 경우, 기존 비밀번호 유지
	    	    m.setUserPwd(loginUser.getUserPwd());
	    	}
	        m.setUserNo(loginUser.getUserNo());
	        
	        int result = mService.updateMember(m);
	        
	        //업데이트 된거 잇으면 
	        if (result > 0) {
	            Member updatedUser = mService.login(m);
	            System.out.println("업데이트 후 조회된 회원 정보: " + updatedUser);
	            
	            model.addAttribute("loginUser", updatedUser);
				return "redirect:myInfo.me";
	        } else {
	            throw new MemberException("회원 수정을 실패했습니다.");
	        }
	    } else {
	        throw new MemberException("현재 비밀번호가 일치하지 않습니다.");
	    }
	}

		
		
	
	// 로그아웃
	@GetMapping("logout.me")
	public String logout(SessionStatus status) {
		status.setComplete();
		return "redirect:home.do";
	}
	
	// 회원탈퇴
	@GetMapping("deleteMember.me")
	public String deleteMember(Model model) {
		int userNo = ((Member)model.getAttribute("loginUser")).getUserNo();
		int result = mService.deleteMember(userNo);
			if(result>0) {
				return "redirect:logout.me"; 
			}else {
				throw new MemberException("회원 탈퇴 실패");
			}
	}
	
	
	

}
