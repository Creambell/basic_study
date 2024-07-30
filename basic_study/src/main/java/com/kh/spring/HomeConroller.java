package com.kh.spring;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeConroller {
	
	@GetMapping("home.do")
	public String home() {
		return "home";
	}
}
