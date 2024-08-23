package com.kh.spring.admin.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.kh.spring.board.model.vo.Board;
import com.kh.spring.board.model.vo.PageInfo;
import com.kh.spring.board.model.vo.Reply;
import com.kh.spring.member.model.vo.Member;

public interface AdminService {

	Board selectBoard(int boardNo, Integer cateNo);

	int getListCount(int cateNo); 

	ArrayList<Board> selectBoardList(PageInfo pi, int cateNo);

	int insertBoard(Board b);

	int updateBoard(Board b);

	int deleteBoard(int bId);

	int listSearchCount(HashMap<String, Object> map);

	ArrayList<Board> searchQuBoBoard(HashMap<String, Object> map, PageInfo pi);

	ArrayList<Member> selectMember();
	
	// 작성한 글 수
	int countMemberPost(int i);
	
	// 작성한 댓글 수    
	int countMemberReply(int i);

	int insertReply(Reply reply);

	ArrayList<Reply> selectReply(int reNo);
	
	List<Reply> selectReplyList(int boardNo);
	
	int updateReply(Reply reply);
	
	int deleteReplies(List<Integer> replyIds);
	
    int deleteReply(int reNo);
    
    void updateReplyCount(int boardNo);

}
