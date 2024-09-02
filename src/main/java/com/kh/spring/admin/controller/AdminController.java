package com.kh.spring.admin.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.kh.spring.member.controller.MemberController;
import com.kh.spring.member.model.exception.MemberException;
import com.kh.spring.member.model.vo.Member;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class AdminController {
	
	@Autowired
	private AdminService aService;
	
	@Autowired
	private BoardService bService;
	
	private Logger logger = LoggerFactory.getLogger(MemberController.class);
	
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
            model.addAttribute("board", new ArrayList<Board>());
            model.addAttribute("pi", pi);
        }
		return "UserAdmin";
		
	}
	
	// JavaScriptAdmin
    @GetMapping("JavaScriptAdmin.ad")
    public String JavaScriptBoard(@RequestParam(value="page", defaultValue="1") int currentPage, Model model,
            HttpServletRequest request) {
        int listCount = aService.getListCount(105);
        PageInfo pi = Pagination.getPageInfo(currentPage, listCount, 10);
        ArrayList<Board> list = aService.selectBoardList(pi, 105);
        
        if(list != null && !list.isEmpty()) {
            model.addAttribute("board", list);
            model.addAttribute("pi", pi);
        } else {
            model.addAttribute("board", new ArrayList<Board>());
            model.addAttribute("pi", pi);
        }
        return "JavaScriptAdmin";
    }
    
    // ErrorAdmin
    @GetMapping("ErrorAdmin.ad")
    public String ErrorBoard(@RequestParam(value="page", defaultValue="1") int currentPage, Model model,
            HttpServletRequest request) {
        int listCount = aService.getListCount(106);
        PageInfo pi = Pagination.getPageInfo(currentPage, listCount, 10);
        ArrayList<Board> list = aService.selectBoardList(pi, 106);
        
        if(list != null && !list.isEmpty()) {
            model.addAttribute("board", list);
            model.addAttribute("pi", pi);
        } else {
            model.addAttribute("board", new ArrayList<Board>());
            model.addAttribute("pi", pi);
        }
        return "ErrorAdmin";
    }
    
    // BloggerBoard
    @GetMapping("BloggerBoard.ad")
    public String BloggerBoard(@RequestParam(value="page", defaultValue="1") int currentPage, Model model,
            HttpServletRequest request) {
        int listCount = aService.getListCount(107);
        PageInfo pi = Pagination.getPageInfo(currentPage, listCount, 10);
        ArrayList<Board> list = aService.selectBoardList(pi, 107);
        
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
    @GetMapping("SpringAdmin.ad")
    public String SpringBoard(@RequestParam(value="page", defaultValue="1") int currentPage, Model model,
            HttpServletRequest request) {
        int listCount = aService.getListCount(102);
        PageInfo pi = Pagination.getPageInfo(currentPage, listCount, 10);
        ArrayList<Board> list = aService.selectBoardList(pi, 102);
        
        if(list != null && !list.isEmpty()) {
            model.addAttribute("board", list);
            model.addAttribute("pi", pi);
        } else {
            model.addAttribute("board", new ArrayList<Board>());
            model.addAttribute("pi", pi);
        }
        return "SpringAdmin";
    }

    // JavaAdmin
    @GetMapping("JavaAdmin.ad")
    public String JavaBoard(@RequestParam(value="page", defaultValue="1") int currentPage, Model model,
            HttpServletRequest request) {
        int listCount = aService.getListCount(104);
        PageInfo pi = Pagination.getPageInfo(currentPage, listCount, 10);
        ArrayList<Board> list = aService.selectBoardList(pi, 104);
        
        if(list != null && !list.isEmpty()) {
            model.addAttribute("board", list);
            model.addAttribute("pi", pi);
        } else {
            model.addAttribute("board", new ArrayList<Board>());
            model.addAttribute("pi", pi);
        }
        return "JavaAdmin";
    }

    // SpringBootAdmin
    @GetMapping("SpringBootAdmin.ad")
    public String SpringBootBoard(@RequestParam(value="page", defaultValue="1") int currentPage, Model model,
            HttpServletRequest request) {
        int listCount = aService.getListCount(103); // SpringBoot 게시판의 cateNo를 103으로 가정
        PageInfo pi = Pagination.getPageInfo(currentPage, listCount, 10);
        ArrayList<Board> list = aService.selectBoardList(pi, 103);
        
        if(list != null && !list.isEmpty()) {
            model.addAttribute("board", list);
            model.addAttribute("pi", pi);
        } else {
            model.addAttribute("board", new ArrayList<Board>());
            model.addAttribute("pi", pi);
        }
        return "SpringBootAdmin";
    }
    
    // 게시글 선택
    @GetMapping("selectBoard.ad")
    public String selectBoard(@RequestParam("bId") int bId, @RequestParam("page") int page,
                              @RequestParam("cateNo") int cateNo, HttpSession session, Model model) {
        // 현재 로그인한 사용자의 번호를 가져옵니다.
        int currentUserNo = 0; // 비로그인 사용자의 경우 0으로 설정
        Member loginUser = (Member) session.getAttribute("loginUser");
        if(loginUser != null) {
            currentUserNo = loginUser.getUserNo(); // getUserNo()는 Member 클래스에 있다고 가정합니다.
        }

        Board board = aService.selectBoard(bId, cateNo, currentUserNo);
        if(board != null) {
            model.addAttribute("b", board);
            model.addAttribute("page", page);
            return "BoardDetail";
        } else {
            throw new BoardException("게시글 보기 실패");
        }
    }
    
    // 게시글 삽입
    @PostMapping("insertBoard.ad")
    public String insertBoard(@ModelAttribute Board b, 
                              @RequestParam("cateNo") int cateNo,
                              HttpSession session) {
        Member loginUser = (Member) session.getAttribute("loginUser");
        
        // 로그인한 사용자 정보로 게시글 작성자 설정
        b.setBoardWriterNo(loginUser.getUserNo());
        b.setBoardWriterName(loginUser.getUserName());
        
        b.setCateNo(cateNo); // 카테고리는 맞춰서
        b.setBoardStatus("Y"); // Y로 초기설정
        
        int result = aService.insertBoard(b);

        if(result > 0) {
            return getBoardRedirect(cateNo);
        } else {
            throw new BoardException("게시글 작성 실패");
        }
    }
    
    // 게시글 수정
    @PostMapping("updateBoard.ad")
    public String updateBoard(@ModelAttribute Board board, @RequestParam("page") int page, 
                              @RequestParam("cateNo") int cateNo, RedirectAttributes ra) {
        int result = aService.updateBoard(board);
        if(result > 0) {
            ra.addAttribute("bId", board.getBoardNo());
            ra.addAttribute("page", page);
            ra.addAttribute("cateNo", cateNo);
            return "redirect:" + getBoardRedirect(cateNo);
        } else {
            throw new BoardException("게시글 수정 실패");
        }
    }
    
    // 게시글 삭제
    @GetMapping("deleteBoard.ad")
    public String deleteBoard(@RequestParam("bId")int bId, @RequestParam("cateNo") int cateNo, RedirectAttributes ra) {
        int result = aService.deleteBoard(bId);
        if(result > 0) {
            ra.addAttribute("bId", bId);
            return getBoardRedirect(cateNo);
        } else {
            throw new BoardException("게시글 삭제 실패");
        }
    }
    
    // 컨트롤러 메서드 수정
    @GetMapping("deleteManyBoards.ad")
    public String deleteManyBoards(@RequestParam("bIds") String bIds, 
                                   @RequestParam("cateNo") int cateNo, 
                                   RedirectAttributes ra) {
        List<Integer> boardIds = Arrays.stream(bIds.split(","))
                                       .map(Integer::parseInt)
                                       .collect(Collectors.toList());
        int result = aService.deleteManyBoards(boardIds);
        if(result == boardIds.size()) {
            ra.addFlashAttribute("message", "선택한 게시글이 성공적으로 삭제되었습니다.");
            return getBoardRedirect(cateNo);
        } else {
            throw new BoardException("일부 게시글 삭제에 실패했습니다.");
        }
    }

    // cateNo 이전페이지 반환값
    private String getBoardRedirect(int cateNo) {
        switch(cateNo) {
            case 100: return "redirect:UserAdmin.ad";
            case 102: return "redirect:SpringAdmin.ad";
            case 103: return "redirect:SpringBootAdmin.ad";
            case 104: return "redirect:JavaAdmin.ad";
            case 105: return "redirect:JavaScriptAdmin.ad";
            case 106: return "redirect:ErrorAdmin.ad";
            case 107: return "redirect:BloggerBoard.ad";
            default: throw new IllegalArgumentException("잘못된 카테고리 번호: " + cateNo);
        }
    }
    
	 // cateNo 검색어 전용
    private String getBoardViewName(int cateNo) {
        switch(cateNo) {
            case 100: return "UserAdmin";
            case 102: return "SpringAdmin";
            case 103: return "SpringBootAdmin";
            case 104: return "JavaAdmin";
            case 105: return "JavaScriptAdmin";
            case 106: return "ErrorAdmin";
            case 107: return "BloggerBoard";
            default: throw new IllegalArgumentException("잘못된 카테고리 번호: " + cateNo);
        }
    }
	
    // 게시판 보기
    @GetMapping("BoardAdminDetail.ad")
    public String boardDetail(@RequestParam("bId") int bId, @RequestParam("page") int page, 
                              @RequestParam("cateNo") int cateNo, Model model, HttpSession session) {
        
        Member loginUser = (Member) session.getAttribute("loginUser");
        
        // 조회수 증가 
        Board board = bService.selectBoardAndIncrementCount(bId, cateNo, loginUser);
        
        if(board != null) {
            model.addAttribute("board", board);
            model.addAttribute("page", page);
            model.addAttribute("cateNo", cateNo);
            return "BoardAdminDetail";
        } else {
            throw new BoardException("게시글 조회 실패");
        }
    }
	
    // 게시판 수정창
 	@GetMapping("editBoard.ad")
 	public String editBoard(@RequestParam("bId") int bId, @RequestParam("page") int page, Model model,
 							@RequestParam("cateNo") int cateNo) {
 	    Board board = aService.selectBoard(bId, cateNo);
 	    if(board != null) {
 	        model.addAttribute("board", board);
 	        model.addAttribute("page", page);
 	        model.addAttribute("cateNo",cateNo);
 	        return "BoardEdit";
 	    } else {
 	        throw new BoardException("게시글 수정 페이지 로드 실패");
 	    }
 	}
	
	//글쓰기 페이지로 단순 이동
    @GetMapping("writeAdmin.ad")
    public String writeAdmin(@RequestParam("cateNo") int cateNo, Model model) {
        model.addAttribute("cateNo", cateNo);
        return "writeAdmin";
    }
			
    // 이미지
    @GetMapping("ImageAdmin.ad")
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
        return "ImageAdmin";
    }
    
    @GetMapping("writeImageAdmin.ad")
    public String writeImageAdmin() {
        return "writeImageAdmin";
    }

    @PostMapping("insertImageBoard.ad")
    public String insertImageBoard(@ModelAttribute Board board, 
                                   @RequestParam("file") MultipartFile file, 
                                   HttpServletRequest request) {
        Member loginUser = (Member) request.getSession().getAttribute("loginUser");
        board.setBoardWriterNo(loginUser.getUserNo());
        board.setBoardWriterName(loginUser.getUserName());
        board.setCateNo(101); // 이미지 게시판 카테고리 번호
        board.setBoardStatus("Y");

        Image image = new Image();
        if(file != null && !file.isEmpty()) {
            String[] returnArr = saveFile(file);
            
            image.setImgPath(returnArr[0]);
            image.setImgName(file.getOriginalFilename());
            image.setImgRename(returnArr[1]);
            image.setImgActive('Y');
            image.setImgRefType("BOARD");
            image.setImgTitle(1); // 썸네일로 설정
        }

        try {
            int result = aService.insertImageBoard(board, image);
            if(result > 0) {
                return "redirect:ImageAdmin.ad";
            } else {
                throw new BoardException("이미지 게시글 작성 실패");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new BoardException("이미지 게시글 작성 중 오류 발생: " + e.getMessage());
        }
    }

    @GetMapping("selectImageBoard.ad")
    public String selectImageBoard(@RequestParam("bId") int bId, @RequestParam("page") int page, Model model, HttpSession session) {
        Member loginUser = (Member) session.getAttribute("loginUser");
        int currentUserNo = loginUser != null ? loginUser.getUserNo() : 0;

        // 조회수 증가와 함께 게시글 정보를 가져옵니다.
        Board board = aService.selectImageBoardAndIncrementCount(bId, currentUserNo);
        Image image = aService.getMainImageForBoard(bId);
        
        if (board != null) {
            if (image != null) {
                board.setImageUrl("/uploadFiles/" + image.getImgRename());
                logger.debug("Image URL for board {}: {}", bId, board.getImageUrl());
            } else {
                logger.warn("No image found for board {}", bId);
            }
            
            model.addAttribute("board", board);
            model.addAttribute("image", image);
            model.addAttribute("page", page);
            return "imageDetail";
        } else {
            throw new BoardException("이미지 게시글 조회 실패");
        }
    }
    
    @GetMapping("editImageBoard.ad")
    public String editImageBoard(@RequestParam("bId") int bId, @RequestParam("page") int page, Model model) {
        Board board = aService.selectImageBoard(bId);
        Image image = aService.getMainImageForBoard(bId);
        
        if (image != null) {
            board.setImageUrl("/uploadFiles/" + image.getImgRename());
        }
        
        model.addAttribute("board", board);
        model.addAttribute("page", page);
        return "editImageBoard";
    }

    @PostMapping("updateImageBoard.ad")
    public String updateImageBoard(@ModelAttribute Board board, 
                                   @RequestParam(value = "file", required = false) MultipartFile file, 
                                   @RequestParam("page") int page,
                                   @RequestParam("cateNo") int cateNo, // 카테고리 번호 추가
                                   RedirectAttributes redirectAttributes) {
        try {
            board.setCateNo(cateNo); // 카테고리 번호 설정
            aService.updateImageBoard(board, file);
            redirectAttributes.addFlashAttribute("message", "게시글이 성공적으로 수정되었습니다.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "게시글 수정 중 오류가 발생했습니다: " + e.getMessage());
        }
        return "redirect:ImageAdmin.ad?page=" + page;
    }

    @GetMapping("deleteImageBoard.ad")
    public String deleteImageBoard(@RequestParam("bId") int bId, @RequestParam("page") int page) {
        aService.deleteImageBoard(bId);
        return "redirect:ImageAdmin.ad?page=" + page;
    }
    
    
    
        @GetMapping("adminPage.ad")
	public String AdminPage(Model model, 
			@RequestParam(value="page", defaultValue="1") int currentPage,
			@RequestParam(value="searchKeyword", required = false) String searchKeyword
			) {
    	
		//유저 수 세기
    	int listCount = aService.getUserListCount(searchKeyword);
    	
	    PageInfo pi = Pagination.getPageInfo(currentPage, listCount, 10);
	    
	    // 검색 결과 리스트 가져오기
	    ArrayList<Member> list = aService.selectUserList(pi,searchKeyword);

	    if(list != null && !list.isEmpty()) {
	        for (Member member : list) {	
	            int postCount = aService.countMemberPost(member.getUserNo());
	            int replyCount = aService.countMemberReply(member.getUserNo());
	            member.setPostCount(postCount);
	            member.setReplyCount(replyCount);
	        }
	        model.addAttribute("list", list);
	        model.addAttribute("pi", pi);
	        model.addAttribute("searchKeyword", searchKeyword);
	        
	        
	    } return "adminPage";
	}	
	
	@PostMapping("updateMemberStatus.adm")
    @ResponseBody
    public String updateMemberStatus(@RequestParam("userId") String userId, @RequestParam("userStatus") String userStatus) {
        Member member = new Member();
        member.setUserId(userId);
        member.setUserStatus(userStatus);

        int result = aService.updateMemberStatus(member);

        return result == 1 ? "success" : "fail";
    }
	
	@PostMapping("updateAdminStatus.adm")
    @ResponseBody
    public String updateAdminStatus(@RequestParam("userId") String userId, @RequestParam("isAdmin") String isAdmin) {
        Member member = new Member();
        member.setUserId(userId);
        member.setIsAdmin(isAdmin);

        int result = aService.updateAdminStatus(member);

        return result == 1 ? "success" : "fail";
    }
	
	// 검색기능 / import 바람
	@GetMapping("searchList.ad")
	public String searchList(@RequestParam("cateNo") int cateNo,
	                         @RequestParam("condition") String condition,
	                         @RequestParam("search") String search,
	                         @RequestParam(value="page", defaultValue="1") int currentPage,
	                         Model model) {
	    HashMap<String, Object> map = new HashMap<>();
	    map.put("condition", condition);
	    map.put("search", search);
	    map.put("cateNo", cateNo);
	    
	    int listSearchCount = aService.listSearchCount(map);
	    
	    PageInfo pi = Pagination.getPageInfo(currentPage, listSearchCount, 10);
	    
	    ArrayList<Board> searchList = aService.searchQuBoBoard(map, pi);
	    
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
    @GetMapping("getReplyList.ad")
    @ResponseBody
    public List<Reply> getReplyList(@RequestParam("boardNo") int boardNo) {
        return aService.selectReplyList(boardNo);
    }
    
    // 댓글 작성
    @PostMapping("insertReply.ad")
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
        
        int result = aService.insertReply(reply);
        
        return result > 0 ? "success" : "fail";
    }
    
    // 댓글 수정
    @PostMapping("updateReply.ad")
    @ResponseBody
    public String updateReply(@RequestParam("reNo") int reNo, 
                              @RequestParam("reContent") String reContent) {
        Reply reply = new Reply();
        reply.setReNo(reNo);
        reply.setReContent(reContent);
        
        int result = aService.updateReply(reply);
        return result > 0 ? "success" : "fail";
    }
    
    // 댓글 삭제
    @PostMapping("deleteReply.ad")
    @ResponseBody
    public String deleteReply(@RequestParam("reNo") int reNo) {
        int result = aService.deleteReply(reNo);
        return result > 0 ? "success" : "fail";
    }
    
    // 댓글 여럿 삭제
    @PostMapping("deleteReplies.ad")
    @ResponseBody
    public String deleteReplies(@RequestParam("reNos") String reNos) {
        String[] reNoArray = reNos.split(",");
        List<Integer> reNoList = Arrays.stream(reNoArray)
                                       .map(Integer::parseInt)
                                       .collect(Collectors.toList());
        
        int result = aService.deleteReplies(reNoList);
        
        if(result > 0) {
            return "success";
        } else {
            return "fail";
        }
    }
	
	
	
	
	
	
	
	
	
	
	
}
