package com.kh.spring.board.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.spring.admin.model.service.AdminService;
import com.kh.spring.board.model.exception.BoardException;
import com.kh.spring.board.model.service.BoardService;
import com.kh.spring.board.model.vo.Board;
import com.kh.spring.board.model.vo.Image;
import com.kh.spring.board.model.vo.PageInfo;
import com.kh.spring.board.model.vo.Reply;
import com.kh.spring.common.Pagination;
import com.kh.spring.member.model.vo.Member;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService bService;
	
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
	
	// 유저 게시판
	@GetMapping("UserBoard.bo")
	public String UserBoard(@RequestParam(value="page", defaultValue="1") int currentPage, Model model,
            HttpServletRequest request) { // 게시글에 대한 전체목록으로 넘어가려는 페이지 니까 => 데이터 필요!(게시글에 대한 전체 정보) => DB에 갖다와야 한다는 것
		// 결합도를 낮추기 위해, 객체를 필드를 빼거나(근데 빼기만 하면 객체를 직접 만들기 때문에), 객체를 framework가 처리하게 하기 위해, 인터페이스 만들기.
		
		int listCount = bService.getListCount(100);// 100 방문자게시판
		System.out.println(listCount);
		
		PageInfo pi = Pagination.getPageInfo(currentPage, listCount, 10);
		ArrayList<Board> list = bService.selectBoardList(pi, 100);
		
		if(list != null && !list.isEmpty()) {
		    model.addAttribute("board", list);
		    model.addAttribute("pi", pi);
		} else {
		    System.out.println("데이터를 가져오지 못했거나 리스트가 비어있습니다.");
		    model.addAttribute("board", new ArrayList<Board>());
		    model.addAttribute("pi", pi);
		}
		return "UserBoard";
		
	}
	
	// JavaScriptAdmin
    @GetMapping("JavaScriptBoard.bo")
    public String JavaScriptBoard(@RequestParam(value="page", defaultValue="1") int currentPage, Model model,
            HttpServletRequest request) {
        int listCount = bService.getListCount(105);
        PageInfo pi = Pagination.getPageInfo(currentPage, listCount, 10);
        ArrayList<Board> list = bService.selectBoardList(pi, 105);
        
        if(list != null && !list.isEmpty()) {
            model.addAttribute("board", list);
            model.addAttribute("pi", pi);
        } else {
            model.addAttribute("board", new ArrayList<Board>());
            model.addAttribute("pi", pi);
        }
        return "JavaScriptBoard";
    }
    
    // ErrorAdmin
    @GetMapping("ErrorBoard.bo")
    public String ErrorBoard(@RequestParam(value="page", defaultValue="1") int currentPage, Model model,
            HttpServletRequest request) {
        int listCount = bService.getListCount(106);
        PageInfo pi = Pagination.getPageInfo(currentPage, listCount, 10);
        ArrayList<Board> list = bService.selectBoardList(pi, 106);
        
        if(list != null && !list.isEmpty()) {
            model.addAttribute("board", list);
            model.addAttribute("pi", pi);
        } else {
            model.addAttribute("board", new ArrayList<Board>());
            model.addAttribute("pi", pi);
        }
        return "ErrorBoard";
    }
    
    // BloggerBoard
    @GetMapping("BloggerBoard.bo")
    public String BloggerBoard(@RequestParam(value="page", defaultValue="1") int currentPage, Model model,
            HttpServletRequest request) {
        int listCount = bService.getListCount(107);
        PageInfo pi = Pagination.getPageInfo(currentPage, listCount, 10);
        ArrayList<Board> list = bService.selectBoardList(pi, 107);
        
        if(list != null && !list.isEmpty()) {
            model.addAttribute("board", list);
            model.addAttribute("pi", pi);
        } else {
            model.addAttribute("board", new ArrayList<Board>());
            model.addAttribute("pi", pi);
        }
        return "BloggerBoard";
    }
    
 // SpringAdmin
    @GetMapping("SpringBoard.bo")
    public String SpringBoard(@RequestParam(value="page", defaultValue="1") int currentPage, Model model,
            HttpServletRequest request) {
        int listCount = bService.getListCount(102);
        PageInfo pi = Pagination.getPageInfo(currentPage, listCount, 10);
        ArrayList<Board> list = bService.selectBoardList(pi, 102);
        
        if(list != null && !list.isEmpty()) {
            model.addAttribute("board", list);
            model.addAttribute("pi", pi);
        } else {
            model.addAttribute("board", new ArrayList<Board>());
            model.addAttribute("pi", pi);
        }
        return "SpringBoard";
    }

    // JavaAdmin
    @GetMapping("JavaBoard.bo")
    public String JavaBoard(@RequestParam(value="page", defaultValue="1") int currentPage, Model model,
            HttpServletRequest request) {
        int listCount = bService.getListCount(104);
        PageInfo pi = Pagination.getPageInfo(currentPage, listCount, 10);
        ArrayList<Board> list = bService.selectBoardList(pi, 104);
        
        if(list != null && !list.isEmpty()) {
            model.addAttribute("board", list);
            model.addAttribute("pi", pi);
        } else {
            model.addAttribute("board", new ArrayList<Board>());
            model.addAttribute("pi", pi);
        }
        return "JavaBoard";
    }

    // SpringBootAdmin
    @GetMapping("SpringBootBoard.bo")
    public String SpringBootBoard(@RequestParam(value="page", defaultValue="1") int currentPage, Model model,
            HttpServletRequest request) {
        int listCount = bService.getListCount(103); // SpringBoot 게시판의 cateNo를 103으로 가정
        PageInfo pi = Pagination.getPageInfo(currentPage, listCount, 10);
        ArrayList<Board> list = bService.selectBoardList(pi, 103);
        
        if(list != null && !list.isEmpty()) {
            model.addAttribute("board", list);
            model.addAttribute("pi", pi);
        } else {
            model.addAttribute("board", new ArrayList<Board>());
            model.addAttribute("pi", pi);
        }
        return "SpringBootBoard";
    }
    
    // 게시글 선택 (오정현 수정 사항)
    @GetMapping("selectBoard.bo")
    public String selectBoard(@RequestParam("bId") int bId, @RequestParam("page") int page,
                              @RequestParam("cateNo") int cateNo, HttpSession session, Model model) {
		Member loginUser = (Member)session.getAttribute("loginUser");
		int currentUserNo = 0;
		if(loginUser != null) {
			currentUserNo = loginUser.getUserNo();
		}
		
    	
        Board board = bService.selectBoard(bId, cateNo, currentUserNo);
        if(board != null) {
            model.addAttribute("b", board);
            model.addAttribute("page", page);
            return "BoardDetail";
        } else {
            throw new BoardException("게시글 보기 실패");
        }
    }
    
    // 새로운 메서드 (조회수 증가 포함)
    @GetMapping("viewBoard.bo")
    public String viewBoard(@RequestParam("bId") int bId, @RequestParam("page") int page,
                            @RequestParam("cateNo") int cateNo, Model model) {
        Board board = bService.selectBoardWithIncrementCount(bId, cateNo);
        if(board != null) {
            model.addAttribute("b", board);
            model.addAttribute("page", page);
            return "BoardDetail";
        } else {
            throw new BoardException("게시글 보기 실패");
        }
    }
    
    // 게시글 삽입 (오정현 수정사항)
    @PostMapping("insertBoard.bo")
    public String insertBoard(@ModelAttribute Board b, 
                              @RequestParam("cateNo") int cateNo,
                              HttpSession session) {
        Member loginUser = (Member) session.getAttribute("loginUser");
        
        // 로그인한 사용자 정보로 게시글 작성자 설정
        b.setBoardWriterNo(loginUser.getUserNo());
        b.setBoardWriterName(loginUser.getUserName());
        
        b.setCateNo(cateNo); // 카테고리는 맞춰서
        b.setBoardStatus("Y"); // Y로 초기설정
        
        int result = bService.insertBoard(b);

        if(result > 0) {
            return getBoardRedirect(cateNo);
        } else {
            throw new BoardException("게시글 작성 실패");
        }
    }
    
    // 게시글 수정
    @PostMapping("updateBoard.bo")
    public String updateBoard(@ModelAttribute Board board, @RequestParam("page") int page, 
                              @RequestParam("cateNo") int cateNo, RedirectAttributes ra) {
        int result = bService.updateBoard(board);
        if(result > 0) {
            ra.addAttribute("bId", board.getBoardNo());
            ra.addAttribute("page", page);
            ra.addAttribute("cateNo", cateNo);
            return "redirect:" + getBoardRedirect(cateNo);
        } else {
            throw new BoardException("게시글 수정 실패");
        }
    }
    
    // 게시글 삭제 (오정현 수정)
	@PostMapping("deleteBoard.bo")
	public String deleteBoard(@RequestParam("bId") int bId, @RequestParam("cateNo") int cateNo) {
		int result = bService.deleteBoard(bId);
		if(result > 0 ) {
			return getBoardRedirect(cateNo);
		} else {
			throw new BoardException("게시글 삭제를 실패했습니다");
		}
	}
	

    // cateNo 이전페이지 반환값
    private String getBoardRedirect(int cateNo) {
        switch(cateNo) {
            case 100: return "redirect:UserBoard.bo";
            case 102: return "redirect:SpringBoard.bo";
            case 103: return "redirect:SpringBootBoard.bo";
            case 104: return "redirect:JavaBoard.bo";
            case 105: return "redirect:JavaScriptBoard.bo";
            case 106: return "redirect:ErrorBoard.bo";
            case 107: return "redirect:BloggerBoard.bo";
            default: throw new IllegalArgumentException("잘못된 카테고리 번호: " + cateNo);
        }
    }
    
	 // cateNo 검색어 전용
    private String getBoardViewName(int cateNo) {
        switch(cateNo) {
            case 100: return "UserBoard";
            case 102: return "SpringBoard";
            case 103: return "SpringBootBoard";
            case 104: return "JavaBoard";
            case 105: return "JavaScriptBoard";
            case 106: return "ErrorBoard";
            case 107: return "BloggerBoard";
            default: throw new IllegalArgumentException("잘못된 카테고리 번호: " + cateNo);
        }
    }
	
    // 게시판 보기
    @GetMapping("BoardDetail.bo")
    public String boardDetail(@RequestParam("bId") int bId, @RequestParam("page") int page, 
                              @RequestParam("cateNo") int cateNo, Model model) {
        Board board = bService.selectBoard(bId, cateNo);
        if(board != null) {
            model.addAttribute("board", board);
            model.addAttribute("page", page);
            model.addAttribute("cateNo", cateNo);
            return "BoardDetail";
        } else {
            throw new BoardException("게시글 조회 실패");
        }
    }
	
    // 게시판 수정창 (오정현 수정)
 	@GetMapping("editBoard.bo")
 	public String editBoard(@RequestParam("bId") int bId, @RequestParam("page") int page, Model model,
 							@RequestParam("cateNo") int cateNo) {
 	    Board board = bService.selectBoard(bId, cateNo);
 	    if(board != null) {
 	        model.addAttribute("board", board);
 	        model.addAttribute("page", page);
 	        model.addAttribute("cateNo",cateNo);
 	        return "UserBoardEdit";
 	    } else {
 	        throw new BoardException("게시글 수정 페이지 로드 실패");
 	    }
 	}
	
	//글쓰기 페이지로 단순 이동
    @GetMapping("writeBoard.bo")
    public String writeAdmin(@RequestParam("cateNo") int cateNo, Model model) {
        model.addAttribute("cateNo", cateNo);
        return "writeBoard";
    }
			
    // 이미지
    @GetMapping("ImageBoard.bo")
    public String imageList(@RequestParam(value="page", defaultValue="1") int currentPage, Model model) {
        int listCount = aService.getListCount(101); // 이미지 게시판 카테고리 번호
        PageInfo pi = Pagination.getPageInfo(currentPage, listCount, 9);
        ArrayList<Board> list = aService.selectImageBoardList(pi);
        
        // 각 게시글의 대표 이미지 정보를 가져옵니다.
        for (Board board : list) {
            Image image = aService.getMainImageForBoard(board.getBoardNo());
            if (image != null) {
                board.setImageUrl("uploadFiles/" + image.getImgRename());
            }
        }

        model.addAttribute("bList", list);
        model.addAttribute("pi", pi);
        return "ImageBoard";
    }
	
	
	@GetMapping("writeImageBoard.bo")
	public String writeImageBoard() {
		return "writeImageBoard";
	}
	
	// 검색기능 / import 바람
	@GetMapping("searchList.bo")
	public String searchList(@RequestParam("cateNo") int cateNo,
	                         @RequestParam("condition") String condition,
	                         @RequestParam("search") String search,
	                         @RequestParam(value="page", defaultValue="1") int currentPage,
	                         Model model) {
	    HashMap<String, Object> map = new HashMap<>();
	    map.put("condition", condition);
	    map.put("search", search);
	    map.put("cateNo", cateNo);
	    
	    int listSearchCount = bService.listSearchCount(map);
	    
	    PageInfo pi = Pagination.getPageInfo(currentPage, listSearchCount, 10);
	    
	    ArrayList<Board> searchList = bService.searchQuBoBoard(map, pi);
	    
	    if(searchList != null) {
	        model.addAttribute("board", searchList);
	        model.addAttribute("pi", pi);
	        model.addAttribute("condition", condition);
	        model.addAttribute("search", search);
	        
	        return getBoardViewName(cateNo);
	    } else {
	        throw new BoardException("검색 결과를 가져오는데 실패했습니다.");
	    }
	}
	
	
	// 댓글 목록 가져오기
	@GetMapping("getReplyList.bo")
	@ResponseBody
	public List<Reply> getReplyList(@RequestParam("boardNo") int boardNo) {
	    return bService.selectReplyList(boardNo);
	}
	
	// 댓글 작성
	@PostMapping("insertReply.bo")
	@ResponseBody
	public String insertReply(@RequestParam("boardNo") int boardNo, 
	                          @RequestParam("replyContent") String replyContent, 
	                          HttpSession session) {
	    Member loginUser = (Member) session.getAttribute("loginUser");
	    if(loginUser == null) {
	        return "login_required";
	    }
	    
	    Reply reply = new Reply();
	    reply.setBoardNo(boardNo);
	    reply.setReContent(replyContent);
	    reply.setReWriterNo(loginUser.getUserNo());
	    
	    int result = bService.insertReply(reply);
	    
	    if(result > 0) {
//	        bService.updateReplyCount(boardNo);
	        return "success";
	    } else {
	        return "fail";
	    }
	}

	// 댓글 수정
	@PostMapping("updateReply.bo")
	@ResponseBody
	public String updateReply(@RequestParam("reNo") int reNo, 
	                          @RequestParam("reContent") String reContent) {
	    Reply reply = new Reply();
	    reply.setReNo(reNo);
	    reply.setReContent(reContent);
	    
	    int result = bService.updateReply(reply);
	    return result > 0 ? "success" : "fail";
	}
	
	// 댓글 삭제
	@PostMapping("deleteReply.bo")
	@ResponseBody
	public String deleteReply(@RequestParam("reNo") int reNo) {
	    int result = bService.deleteReply(reNo);
	    return result > 0 ? "success" : "fail";
	}

	@GetMapping("myReply.me")
	public String MyReply(@RequestParam(value="page", defaultValue="1") int currentPage,
			HttpSession session,
			Model model) {
		
		Member loginUser = (Member) session.getAttribute("loginUser");
	    if (loginUser == null) {
	        return "redirect:login.me"; 
	    }
        String userId = ((Member)session.getAttribute("loginUser")).getUserId();
        
		int listCount = bService.getMyReplyCount(userId);
		
		PageInfo pi = Pagination.getPageInfo(currentPage, listCount, 10);
		    
		ArrayList<Reply> list = bService.selectMyReplyList(pi,userId);
		
		System.out.println(list);
		
		if(list != null) {
			model.addAttribute("list", list);
			model.addAttribute("pi", pi);
			return "myReply";
		}else {
			throw new BoardException("내 글 조회 실패.");
		}
	}
	
	// 내 게시글 보기 페이지
	@GetMapping("myBoard.me")
	public String MyBoard(@RequestParam(value="page", defaultValue="1") int currentPage,
			HttpSession session,
			Model model) {
		
		Member loginUser = (Member) session.getAttribute("loginUser");
	    if (loginUser == null) {
	        return "redirect:login.me"; 
	    }
        String userId = ((Member)session.getAttribute("loginUser")).getUserId();
        
		int listCount = bService.getMyBoardCount(userId);
		
		PageInfo pi = Pagination.getPageInfo(currentPage, listCount, 10);
		    
		ArrayList<Board> list = bService.selectMyBoardList(pi,userId);
		
		if(list != null) {
			model.addAttribute("list", list);
			model.addAttribute("pi", pi);
			return "myBoard";
		}else {
			throw new BoardException("내 글 조회 실패.");
		}
	}
	
	
	
	
	
	
	
	
	
	
	
}