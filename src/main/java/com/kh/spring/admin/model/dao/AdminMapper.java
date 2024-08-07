package com.kh.spring.admin.model.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.kh.spring.board.model.vo.Board;
import com.kh.spring.board.model.vo.PageInfo;

@Mapper
public interface AdminMapper {

	Board selectBoard(int bId, String id);

	int getListCount(int i);

	ArrayList<Board> selectBoardList(int i, RowBounds rowBounds);

	int insertBoard(Board b);


}
