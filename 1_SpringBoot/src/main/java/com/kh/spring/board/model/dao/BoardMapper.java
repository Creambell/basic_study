package com.kh.spring.board.model.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.kh.spring.board.model.vo.Board;
import com.kh.spring.board.model.vo.Reply;

@Mapper
public interface BoardMapper {

	int getListCount(int i);

	ArrayList<Board> selectBoardList(int i, RowBounds rowBounds);

	int insertBoard(Board b);

	ArrayList<Reply> selectReply(int bId);

	Board selectBoard(int bId);

	int updateCount(int bId);

	int insertReply(Reply r);

	int deleteReply(int rId);

	int updateReply(Reply r);

	int updateBoard(Board b);

	int deleteBoard(int bId);

}
