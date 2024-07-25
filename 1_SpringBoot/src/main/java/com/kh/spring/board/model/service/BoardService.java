package com.kh.spring.board.model.service;

import java.util.ArrayList;

import com.kh.spring.board.model.vo.Board;
import com.kh.spring.board.model.vo.PageInfo;
import com.kh.spring.board.model.vo.Reply;

public interface BoardService {

	int getListCount(int i);

	ArrayList<Board> selectBoardList(PageInfo pi, int i);

	int insertBoard(Board b);

	Board selectBoard(int bId, String id);

	ArrayList<Reply> selectReply(int bId);

	int insertReply(Reply r);

	int deleteReply(int rId);

	int updateReply(Reply r);

	int updateBoard(Board b);

	int deleteBaord(int bId);

}
