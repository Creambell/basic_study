package com.kh.spring.board.controller;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.spring.board.model.exception.BoardException;
import com.kh.spring.board.model.service.BoardService;
import com.kh.spring.board.model.vo.Board;
import com.kh.spring.board.model.vo.PageInfo;
import com.kh.spring.board.model.vo.Reply;
import com.kh.spring.common.Pagination;
import com.kh.spring.member.model.vo.Member;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;



@Controller
public class BoardController{
	
	@Autowired
	private BoardService bService;
	
	
	@GetMapping("list.bo") //view이동 + 데이터 이동
	public String selectBoardList(@RequestParam(value="page", defaultValue="1")int currentPage, 
										Model model, HttpServletRequest request) {

			int listCount = bService.getListCount(1);  

			PageInfo pi = Pagination.getPageInfo(currentPage, listCount, 5);
			
			ArrayList<Board> list = bService.selectBoardList(pi,1);  
																	
			if(list!=null) {						 				 
				model.addAttribute("list",list);
				model.addAttribute("pi",pi);
				model.addAttribute("loc",request.getRequestURI());
				
				return "boardList";
			}else {
				throw new BoardException("게시글 조회 실패했어요.");
			}
		}
	
	@GetMapping("writeBoard.bo")
	public String writeBoard() {
		return "writeBoard";
	}
	
	@PostMapping("insertBoard.bo")
	public String insertBoard(@ModelAttribute Board b, HttpSession session) {
		String boardWriter = ((Member)session.getAttribute("loginUser")).getId();
		b.setBoardWriter(boardWriter);
		b.setBoardType(1);
		
//		System.out.println("insert전의 b: ");				//	boardId를 변수에 저장해서, 이전에 들어간 시퀀스가 남아있으니 똑같은 보드에서 관리 ㄴ. 안정성 더 높음.
//		System.out.println(b);
		int result = bService.insertBoard(b);
//		System.out.println("insert후의 b: ");
//		System.out.println(b);
		
		if(result > 0) {
			return "redirect:list.bo";
		}else {
			throw new BoardException("게시글 작성 실패했어요.");		
		}
	}
	
	@GetMapping("selectBoard.bo")
	public String selectBoard(@RequestParam("bId") int bId, @RequestParam("page")int page,			//@RequestParam은 name속성(파라미터) 가져옴. 
								HttpSession session, Model model) {
		Member loginUser = (Member)session.getAttribute("loginUser");
		String id = null;
		if(loginUser != null) {
			id = loginUser.getId();		
			}
		Board board = bService.selectBoard(bId, id);
		ArrayList<Reply> list = bService.selectReply(bId);
		if(board != null) { // 07-25.2 쿼리스트링을 위한 모델값이 못받아오는걸 확인했습니다.
// 1. 업데이트보드의 모델을 사용하지않고 updateBoard.bo 반환값뒤에 + b.getBoardId() + "&page="+page;로 직접 url을 변경하는게 가장 간단한 방법이고
// 2. 모델 대신에 RedirectAttributes "별칭" 으로 진행할 수 있다. 단 이럴 경우에도 모델을 지워줘야한다.
			model.addAttribute("b", board);
			model.addAttribute("page", page);
			model.addAttribute("list", list);
			return "boardDetail";
		}else {
			throw new BoardException("게시글 상세보기 실패."); 
		}
	}
	
	
	@GetMapping(value="insertReply.bo", produces="application/json; charset=UTF-8")
	@ResponseBody
	public String insertReply(@ModelAttribute Reply r) {
		int result = bService.insertReply(r);
		JSONArray array = new JSONArray();

		ArrayList<Reply> list = bService.selectReply(r.getRefBoardId());
		for(Reply reply : list) {
			JSONObject json = new JSONObject(); // {key:value}
			//객체는 JSONObject에 담고, JSONObject를 한번에 담는 list를 만듦.
			json.put("replyId", reply.getReplyId());
			json.put("replyContent", reply.getReplyContent());
			json.put("refBoardId", reply.getRefBoardId());
			json.put("replyWriter", reply.getReplyWriter());
			json.put("nameNick", reply.getNickName());	
			json.put("replyCreateDate", reply.getReplyCreateDate());
			json.put("replyModifyDate", reply.getReplyModifyDate());
			json.put("replyStatus", reply.getReplyStatus());
			
			array.put(json);
		}
		return array.toString();
	}
	
	@GetMapping("deleteReply.bo")
	@ResponseBody // return view주소가 아닌 데이터로 넘기게 .
	public String deleteReply(@RequestParam("rId") int rId) {
		int result = bService.deleteReply(rId);
		return result == 1 ? "success" : "fail";
	}
	
	@GetMapping("updateReply.bo")
	@ResponseBody
	public String updateReply(@ModelAttribute Reply r) {
		int result = bService.updateReply(r);
		return result == 1 ? "success" : "fail";
	}

	@PostMapping("updateForm.bo")
	public String updateForm(@RequestParam("boardId") int bId, @RequestParam("page") int page, Model model) {
		Board b = bService.selectBoard(bId, null);
		model.addAttribute("b", b);
		model.addAttribute("page", page); // 페이지 유지 위해 받아옴.
		return "board/edit"; //멤버에도 edit있으니 board넣음.
	}

	@PostMapping("updateBoard.bo")
	public String updateBoard(@ModelAttribute Board b, @RequestParam("page") int page, RedirectAttributes ra) {
		b.setBoardType(1);
		int result = bService.updateBoard(b);
		if(result>0) {
//			model.addAttribute("bId", b.getBoardId());
//			model.addAttribute("page", page);
//			return "redirect:selectBoard.bo"; // 현재 이 상태로 진행하면 400에러가 뜨는데 셀렉 보드로 보냈지만
			// 쿼리스트링을 해주는 model이 진행이 안되기때문에 변경해줘야합니다. 07-25.1
			
			ra.addAttribute("bId",b.getBoardId());
			ra.addAttribute("page",page);
			return "redirect:selectBoard.bo";
		} else {							
			throw new BoardException("게시글 수정을 실패했어요.");
		}
	}
	
	@PostMapping("delete.bo") // 성공은 했지만 왜 Post인지 모르겠음 modal에는 Post가 적혀있는게 없는데, 맨위 div에 post라 넘긴다고함 확인해야할듯.
				// 확인결과 post를 보내는 div가 delete까지 담고있기때문에 PostMapping으로 진행해야합니다.
	public String deleteBoard(@RequestParam("boardId") int bId) {
		int result = bService.deleteBaord(bId);
		if(result > 0) {
			return "redirect:list.bo";
		} else {
			throw new BoardException("게시글 삭제를 실패했습니다.");
		}
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	}
