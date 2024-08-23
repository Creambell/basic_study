package com.kh.spring.admin.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring.admin.model.dao.AdminMapper;
import com.kh.spring.board.model.vo.Board;
import com.kh.spring.board.model.vo.PageInfo;
import com.kh.spring.board.model.vo.Reply;
import com.kh.spring.member.model.vo.Member;

@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	private AdminMapper aMapper;

	@Override
    public Board selectBoard(int boardNo, Integer cateNo) {
        return aMapper.selectBoard(boardNo, cateNo);
    }
    
    @Override
    public int getListCount(int cateNo) {
        return aMapper.getListCount(cateNo);
    }
    
    @Override
    public ArrayList<Board> selectBoardList(PageInfo pi, int cateNo) {
        int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
        RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
        return aMapper.selectBoardList(cateNo, rowBounds);
    }
    
    @Override
    public int insertBoard(Board b) {
        return aMapper.insertBoard(b);
    }
    
    @Override
    public int updateBoard(Board b) {
        return aMapper.updateBoard(b);
    }

	@Override
	public int deleteBoard(int bId) {
		return aMapper.deleteBoard(bId);
	}

	@Override
	public int listSearchCount(HashMap<String, Object> map) {
		return aMapper.listSearchCount(map);
	}

	@Override
	public ArrayList<Board> searchQuBoBoard(HashMap<String, Object> map, PageInfo pi) {
	    int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
	    RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
	    return (ArrayList<Board>) aMapper.searchQuBoBoard(map, rowBounds);
	}
	
	@Override
	public ArrayList<Member> selectMember() {
		return aMapper.selectMember();
	}

	@Override
	public int countMemberPost(int i) {
		return aMapper.countMemberPost(i);
	}

	@Override
	public int countMemberReply(int i) {
		return aMapper.countMemberReply(i);
	}

	@Override
	public int insertReply(Reply reply) {
		return aMapper.insertReply(reply);
	}

	@Override
	public ArrayList<Reply> selectReply(int reNo) {
		return aMapper.selectReply(reNo);
	}
	
	@Override
	public List<Reply> selectReplyList(int boardNo) {
	    return aMapper.selectReplyList(boardNo);
	}

	@Override
	public int updateReply(Reply reply) {
	    return aMapper.updateReply(reply);
	}

	@Override
	public int deleteReplies(List<Integer> replyIds) {
	    return aMapper.deleteReplies(replyIds);
	}
	
	@Override
    public int deleteReply(int reNo) {
        return aMapper.deleteReply(reNo);
    }
	
	@Override
    public void updateReplyCount(int boardNo) {
        aMapper.updateReplyCount(boardNo);
    }
}
