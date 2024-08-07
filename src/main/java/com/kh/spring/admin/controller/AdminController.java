package com.kh.spring.admin.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.kh.spring.admin.model.service.AdminService;
import com.kh.spring.board.model.exception.BoardException;
import com.kh.spring.board.model.vo.Board;
import com.kh.spring.board.model.vo.PageInfo;
import com.kh.spring.common.Pagination;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

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
	public String UserBoard(@RequestParam(value="page", defaultValue="1") int currentPage, Model model,
            HttpServletRequest request) { // 게시글에 대한 전체목록으로 넘어가려는 페이지 니까 => 데이터 필요!(게시글에 대한 전체 정보) => DB에 갖다와야 한다는 것
		// 결합도를 낮추기 위해, 객체를 필드를 빼거나(근데 빼기만 하면 객체를 직접 만들기 때문에), 객체를 framework가 처리하게 하기 위해, 인터페이스 만들기.
		
		int listCount = aService.getListCount(100);// 100 방문자게시판
		System.out.println(listCount);
		
		PageInfo pi = Pagination.getPageInfo(currentPage, listCount, 10);
		ArrayList<Board> list = aService.selectBoardList(pi, 100);
		
		if(list != null && !list.isEmpty()) {
		    model.addAttribute("board", list);
		    model.addAttribute("pi", pi);
		} else {
		    System.out.println("데이터를 가져오지 못했거나 리스트가 비어있습니다.");
		    model.addAttribute("board", new ArrayList<Board>());
		    model.addAttribute("pi", pi);
		}
		return "UserAdmin";
		
	}
	
	@GetMapping("selectBoard.ad")
	public String selectBoard(@RequestParam("bId")int bId,@RequestParam("page")int page,
			HttpSession session,Model model) {
		
		String id = null;
		
		Board board = aService.selectBoard(bId,id);
		System.out.println(board);
		if(board != null) {
			model.addAttribute("b",board);
			model.addAttribute("page",page);
			return "UserAdmin";
		}else {
			throw new BoardException("게시글 보기 실패");
		}
	}
	
	//글쓰기 페이지로 단순 이동
			@GetMapping("writeAdmin.ad")
			public String writeAdmin() {
				return "writeAdmin";
			}
			
	//글쓰기 기능
	@PostMapping("insertBoard.ad")
	public String insertBoard(@ModelAttribute Board b) {
		
		b.setBoardWriterNo(1);
		b.setCateNo(100);
		b.setBoardStatus("Y");
		b.setBoardWriterName("임시 사용자");
		int result = aService.insertBoard(b);
		
		if(result > 0) {
			return "home";
		} else {
			throw new BoardException("게시글 작성 실패");
		}
	    
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
	
	@GetMapping("writeImageAdmin.ad")
	public String writeImageAdmin() {
		return "writeImageAdmin";
	}
	

}
