package com.kh.spring.admin.model.service;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring.admin.model.dao.AdminMapper;
import com.kh.spring.board.model.vo.Board;
import com.kh.spring.board.model.vo.PageInfo;

@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	private AdminMapper aMapper;

	@Override
	public Board selectBoard(int bId, String id) {
		return aMapper.selectBoard(bId,id);
	}

	@Override
	public int getListCount(int i) {
		return aMapper.getListCount(i);
	}

	@Override
	public ArrayList<Board> selectBoardList(PageInfo pi, int i) {
		int offset = (pi.getCurrentPage() - 1)*pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
		return aMapper.selectBoardList(i, rowBounds);
	}
}
