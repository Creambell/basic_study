package com.kh.spring.admin.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.kh.spring.board.model.vo.Board;
import com.kh.spring.board.model.vo.Image;
import com.kh.spring.board.model.vo.PageInfo;
import com.kh.spring.board.model.vo.Reply;
import com.kh.spring.member.model.vo.Member;

public interface AdminService {

	Board selectBoard(int boardNo, Integer cateNo);

	int getListCount(int cateNo); 

	ArrayList<Board> selectBoardList(PageInfo pi, int cateNo);

	int insertBoard(Board b);

	int updateBoard(Board b);

	int listSearchCount(HashMap<String, Object> map);

	ArrayList<Board> searchQuBoBoard(HashMap<String, Object> map, PageInfo pi);

	// 작성한 글 수
	int countMemberPost(int i);
	
	// 작성한 댓글 수    
	int countMemberReply(int i);

	int insertReply(Reply reply);

	ArrayList<Reply> selectReply(int reNo);
	
	List<Reply> selectReplyList(int boardNo);
	
	int updateReply(Reply reply);
	
	int deleteReplies(List<Integer> reNoList);
	
    int deleteReply(int reNo);
    
    void updateReplyCount(int boardNo);
    
    int updateMemberStatus(Member member);
	
//   	int getUserListCount();
   	
//   	ArrayList<Member> selectMember(PageInfo pi);

	int deleteManyBoards(List<Integer> boardIds);

	Board selectBoard(int bId, int cateNo, int currentUserNo);

	ArrayList<Image> selectImageList();

	int insertImageBoard(Board board, Image image);

	Image selectImage(int bId);

	Image getMainImageForBoard(int boardNo);
	
	Board selectImageBoard(int bId);
	
    void updateImageBoard(Board board, MultipartFile file);
    
    void deleteImageBoard(int bId);

	ArrayList<Board> selectImageBoardList(PageInfo pi);

	void updateImageBoard(Board board, Image image);

	Board selectImageBoardAndIncrementCount(int bId, int currentUserNo);
	
	int updateAdminStatus(Member member);

	int deleteBoard(int bId);

	int getUserListCount(String searchKeyword);

	ArrayList<Member> selectUserList(PageInfo pi, String searchKeyword);
	
}
