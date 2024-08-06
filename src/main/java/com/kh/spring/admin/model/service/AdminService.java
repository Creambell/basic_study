package com.kh.spring.admin.model.service;

import java.util.ArrayList;

import com.kh.spring.board.model.vo.Board;
import com.kh.spring.board.model.vo.PageInfo;

public interface AdminService {

	Board selectBoard(int bId, String id);

	int getListCount(int i);

	ArrayList<Board> selectBoardList(PageInfo pi, int i);


}
