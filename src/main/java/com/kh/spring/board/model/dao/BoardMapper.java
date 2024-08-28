package com.kh.spring.board.model.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.kh.spring.board.model.vo.Board;
import com.kh.spring.board.model.vo.Image;
import com.kh.spring.board.model.vo.Reply;
import com.kh.spring.member.model.vo.Member;

@Mapper
public interface BoardMapper {
	
	Board selectBoard(@Param("boardNo") int boardNo, @Param("cateNo") Integer cateNo);

	int getListCount(int cateNo);

	ArrayList<Board> selectBoardList(int cateNo, RowBounds rowBounds);

	int insertBoard(Board b);

	int updateBoard(Board b);

	int deleteBoard(int bId);

	int listSearchCount(HashMap<String, Object> map);

	ArrayList<Board> searchQuBoBoard(HashMap<String, Object> map, RowBounds rowBounds);

	ArrayList<Member> selectMember();
	
	int countMemberPost(int i);

	int countMemberReply(int i);

	int insertReply(Reply reply);

	ArrayList<Reply> selectReply(int reNo);

	List<Reply> selectReplyList(int boardNo);

	int updateReply(Reply reply); // 추후 사용가능할수도?

	int deleteReplies(List<Integer> replyIds);

	int deleteReply(int reNo);

	List<Board> selectBoardListByCategory(int cateNo);

//	void updateReplyCount(int boardNo);

	void incrementBoardCount(int bId);
	
	int getMyBoardCount(String userId);

    ArrayList<Board> selectMyBoardList(@Param("rowBounds") RowBounds rowBounds, @Param("userId") String userId);

	int getMyReplyCount(String userId);
	
    ArrayList<Reply> selectMyReplyList(@Param("rowBounds") RowBounds rowBounds, @Param("userId") String userId);

	int updateBoardCount(int bId);

}
