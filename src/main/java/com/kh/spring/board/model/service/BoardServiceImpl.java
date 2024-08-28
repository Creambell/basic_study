package com.kh.spring.board.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.spring.board.model.dao.BoardMapper;
import com.kh.spring.board.model.vo.Board;
import com.kh.spring.board.model.vo.PageInfo;
import com.kh.spring.board.model.vo.Reply;
import com.kh.spring.member.model.vo.Member;


@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardMapper bMapper;

	@Override
    public Board selectBoard(int boardNo, Integer cateNo) {
        return bMapper.selectBoard(boardNo, cateNo);
    }
	
	@Override
    public Board selectBoardWithIncrementCount(int bId, int cateNo) {
        Board board = bMapper.selectBoard(bId, cateNo);
        if (board != null) {
            bMapper.incrementBoardCount(bId);
            board.setBoardCount(board.getBoardCount() + 1);
        }
        return board;
    }

	@Override
	public Board selectBoardAndIncrementCount(int bId, int cateNo, Member loginUser) {
	    Board board = bMapper.selectBoard(bId, cateNo);
	    if(board != null) {
	        // 다른 사람이 볼떄 조회수 증가
	        if(loginUser == null || loginUser.getUserNo() != board.getBoardWriterNo()) {
	            bMapper.incrementBoardCount(bId);
	            board.setBoardCount(board.getBoardCount() + 1);
	        }
	    }
	    return board;
	}
	
    @Override
    public int getListCount(int cateNo) {
        return bMapper.getListCount(cateNo);
    }
    
    @Override
    public ArrayList<Board> selectBoardList(PageInfo pi, int cateNo) {
        int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
        RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
        return bMapper.selectBoardList(cateNo, rowBounds);
    }
    
    @Override
    public int insertBoard(Board b) {
        return bMapper.insertBoard(b);
    }
    
    @Override
    public int updateBoard(Board b) {
        return bMapper.updateBoard(b);
    }

	@Override
	public int deleteBoard(int bId) {
		return bMapper.deleteBoard(bId);
	}

	@Override
	public int listSearchCount(HashMap<String, Object> map) {
		return bMapper.listSearchCount(map);
	}

	@Override
	public ArrayList<Board> searchQuBoBoard(HashMap<String, Object> map, PageInfo pi) {
	    int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
	    RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
	    return (ArrayList<Board>) bMapper.searchQuBoBoard(map, rowBounds);
	}
	
	@Override
	public ArrayList<Member> selectMember() {
		return bMapper.selectMember();
	}

	@Override
	public int countMemberPost(int i) {
		return bMapper.countMemberPost(i);
	}

	@Override
	public int countMemberReply(int i) {
		return bMapper.countMemberReply(i);
	}

	@Override
	public int insertReply(Reply reply) {
		return bMapper.insertReply(reply);
	}

	@Override
	public ArrayList<Reply> selectReply(int reNo) {
		return bMapper.selectReply(reNo);
	}
	
	@Override
	public List<Reply> selectReplyList(int boardNo) {
	    return bMapper.selectReplyList(boardNo);
	}

	@Override
	public int updateReply(Reply reply) {
	    return bMapper.updateReply(reply);
	}

	@Override
	public int deleteReplies(List<Integer> replyIds) {
	    return bMapper.deleteReplies(replyIds);
	}
	
	@Override
    public int deleteReply(int reNo) {
        return bMapper.deleteReply(reNo);
    }
	
//	@Override
//    public void updateReplyCount(int boardNo) {
//		bMapper.updateReplyCount(boardNo);
//    }
	
	 @Override
	    public List<Board> selectBoardListByCategory(int cateNo) {
	        return bMapper.selectBoardListByCategory(cateNo);
	    }

	 @Override
		public int getMyBoardCount(String userId) {
			return bMapper.getMyBoardCount(userId);
		}

	@Override
	public ArrayList<Board> selectMyBoardList(PageInfo pi, String userId) {
		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
        RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
        return bMapper.selectMyBoardList(rowBounds,userId);
	}

	@Override
	public int getMyReplyCount(String userId) {
		return bMapper.getMyReplyCount(userId);
	}

	@Override
	public ArrayList<Reply> selectMyReplyList(PageInfo pi, String userId) {
		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
        RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
        return bMapper.selectMyReplyList(rowBounds,userId);
		}
	
	// (오정현 수정)
	@Override
	public Board selectBoard(int bId, int cateNo, int currentUserNo) {
	    Board b = bMapper.selectBoard(bId, cateNo);
	    if(b != null && currentUserNo != b.getBoardWriterNo()) {
	        int result = bMapper.updateBoardCount(bId);
	        System.out.println("조회수 증가 결과: " + result);
	        if(result > 0) {
	            b.setBoardCount(b.getBoardCount() + 1);
	        }
	    }
	    return b;
	}


}
