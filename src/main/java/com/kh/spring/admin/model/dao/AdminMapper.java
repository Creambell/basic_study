package com.kh.spring.admin.model.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.web.multipart.MultipartFile;

import com.kh.spring.board.model.vo.Board;
import com.kh.spring.board.model.vo.Image;
import com.kh.spring.board.model.vo.Reply;
import com.kh.spring.member.model.vo.Member;

@Mapper
public interface AdminMapper {

	Board selectBoard(@Param("boardNo") int boardNo, @Param("cateNo") Integer cateNo);

	int getListCount(int cateNo);

	ArrayList<Board> selectBoardList(int cateNo, RowBounds rowBounds);

	int insertBoard(Board b);

	int updateBoard(Board b);

	int listSearchCount(HashMap<String, Object> map);

	ArrayList<Board> searchQuBoBoard(HashMap<String, Object> map, RowBounds rowBounds);

	int countMemberPost(int i);

	int countMemberReply(int i);

	int insertReply(Reply reply);

	ArrayList<Reply> selectReply(int reNo);

	List<Reply> selectReplyList(int boardNo);

	int updateReply(Reply reply); // 추후 사용가능할수도?

	int deleteReply(int reNo);

	void updateReplyCount(int boardNo);
	
	int updateMemberStatus(Member member);

//	int getUserListCount();
	
//	ArrayList<Member> selectMember(RowBounds rowBounds);
	
	int updateAdminStatus(Member member);
	
	int deleteManyBoards(List<Integer> boardIds);

	int updateBoardCount(int bId);

	ArrayList<Image> selectImageList();

	int insertImage(Image image);

	Image selectImage(int bId);

	Image getMainImageForBoard(int boardNo);

	void deleteImage(int boardNo);

	int deleteBoard(int bId);

	void updateImageBoard(Board board, MultipartFile file);

	Board selectImageBoard(int bId);
	
	int getUserListCount(@Param("searchKeyword") String searchKeyword);

	ArrayList<Member> selectUserList(RowBounds rowBounds, @Param("searchKeyword") String searchKeyword );

	int deleteReplies(List<Integer> reNoList);

}
