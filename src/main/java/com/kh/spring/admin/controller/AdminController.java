package com.kh.spring.admin.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MultipartFile;

import com.kh.spring.admin.model.service.AdminService;

@Controller
public class AdminController {
	
	@Autowired
	private AdminService aService;
	
	public String[] saveFile(MultipartFile upload) {
		String savePath = "C:\\uploadFiles"; // 자기 파일 경로 설정해주세요.
		
		File folder = new File(savePath); // 파일 만들기 io
		if(!folder.exists()) {
			folder.mkdirs();
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		int ranNum = (int)(Math.random()*100000);
		String originFileName = upload.getOriginalFilename();
		String renameFileName = sdf.format(new Date()) + ranNum + originFileName.substring(originFileName.lastIndexOf("."));
		
		String renamePath = folder + "\\" + renameFileName;
		try {
			upload.transferTo(new File(renamePath));
		} catch (Exception e) {
			System.out.println("파일 전송 에러 : " + e.getMessage());
		}
		
		String[] returnArr = new String[2];
		returnArr[0] = savePath;
		returnArr[1] = renameFileName;
		
		return returnArr;
	}
	
	public void deleteFile(String fileName) { // 들어온 이미지 지우기
		String savePath = "C:\\uploadFile"; // 자기 파일 경로 설정해주세요.
		File f = new File(savePath + "\\" + fileName);
		if(f.exists()) {
			f.delete();
		}
	}
	
	@GetMapping("UserAdmin.ad")
	public String UserBoard() {
		
		
		
		return "UserAdmin";
	}
	
	@GetMapping("ImageAdmin.ad")
	public String ImageBoard() {
		return "ImageAdmin";
	}
	
	@GetMapping("SpringAdmin.ad")
	public String SpringBoard() {
		return "SpringAdmin";
	}
	@GetMapping("JavaAdmin.ad")
	public String JavaBoard() {
		return "JavaAdmin";
	}
	@GetMapping("JavaScriptAdmin.ad")
	public String JavaScriptBoard() {
		return "JavaScriptAdmin";
	}
	@GetMapping("ErrorAdmin.ad")
	public String ErrorBoard() {
		return "ErrorAdmin";
	}
	
	@GetMapping("AdminPage.ad")
	public String AdminPage() {
		return "adminPage";
	}
	
	@GetMapping("BloggerBoard.ad")
    public String bloggerBoard() {
        return "BloggerBoard";
    }

	@GetMapping("SpringBootAdmin.ad")
    public String springbootBoard() {
        return "SpringBootAdmin";
    }

}
