package com.kh.spring.board.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import com.kh.spring.board.model.service.BoardService;



@Controller
public class BoardController{
	
	@Autowired
	private BoardService bService;
	
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
	
	
	
	
	
	
	}
