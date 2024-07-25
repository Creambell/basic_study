package com.kh.spring.board.model.service;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring.board.model.dao.BoardMapper;
import com.kh.spring.board.model.vo.Board;
import com.kh.spring.board.model.vo.PageInfo;
import com.kh.spring.board.model.vo.Reply;


@Service
public class BoardServiceImpl implements BoardService{

@Autowired
private BoardMapper bMapper;
	
	@Override
	public int getListCount(int i) {
		return bMapper.getListCount(i);
	}

	@Override
	public ArrayList<Board> selectBoardList(PageInfo pi, int i) {
		int offset = (pi.getCurrentPage() - 1)*pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
		return bMapper.selectBoardList(i, rowBounds);
	}

	@Override
	public int insertBoard(Board b) {
		return bMapper.insertBoard(b);
	}

	@Override
	public Board selectBoard(int bId, String id) {
		Board b = bMapper.selectBoard(bId);
		if(b != null) {
			if(id != null && !b.getBoardWriter().equals(id)){ //조회수 증가 
				int result = bMapper.updateCount(bId);
				if(result > 0) {
					b.setBoardCount(b.getBoardCount()+1);
				}
			}
		}
		return b;
	}

	@Override
	public ArrayList<Reply> selectReply(int bId) {
		return bMapper.selectReply(bId);
	}

	@Override
	public int insertReply(Reply r) {
		return bMapper.insertReply(r);
	}

	@Override
	public int deleteReply(int rId) {
		return bMapper.deleteReply(rId);
	}

	@Override
	public int updateReply(Reply r) {
		return bMapper.updateReply(r);
	}

	@Override
	public int updateBoard(Board b) {
		return bMapper.updateBoard(b);
	}

	@Override
	public int deleteBaord(int bId) {
		return bMapper.deleteBoard(bId);
	}

	
	
}
