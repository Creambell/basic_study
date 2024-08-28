package com.kh.spring.admin.model.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.kh.spring.admin.model.dao.AdminMapper;
import com.kh.spring.board.model.exception.BoardException;
import com.kh.spring.board.model.vo.Board;
import com.kh.spring.board.model.vo.Image;
import com.kh.spring.board.model.vo.PageInfo;
import com.kh.spring.board.model.vo.Reply;
import com.kh.spring.member.controller.MemberController;
import com.kh.spring.member.model.vo.Member;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper aMapper;
    
    private Logger logger = LoggerFactory.getLogger(MemberController.class);
    
    public String[] saveFile(MultipartFile upload) {
		String savePath = "C:\\uploadFiles"; // 자기 파일 경로 설정해주세요.
		
		File folder = new File(savePath); // 파일 만들기 io
		if(!folder.exists()) {
			folder.mkdirs();
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		int ranNum = (int)(Math.random()*100000);
		String originFileName = upload.getOriginalFilename();
		String renameFileName = sdf.format(new Date()) + ranNum + originFileName.substring(originFileName.lastIndexOf("."));
		
		String renamePath = folder + "\\" + renameFileName;
		try {
			upload.transferTo(new File(renamePath));
		} catch (Exception e) {
			System.out.println("파일 전송 에러 : " + e.getMessage());
		}
		
		String[] returnArr = new String[2];
		returnArr[0] = savePath;
		returnArr[1] = renameFileName;
		
		return returnArr;
	}
	
	public void deleteFile(String fileName) { // 들어온 이미지 지우기
		String savePath = "C:\\uploadFile"; // 자기 파일 경로 설정해주세요.
		File f = new File(savePath + "\\" + fileName);
		if(f.exists()) {
			f.delete();
		}
	}
    
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
    public int deleteReplies(List<Integer> reNoList) {
        return aMapper.deleteReplies(reNoList);
    }

    @Override
    public int deleteReply(int reNo) {
        return aMapper.deleteReply(reNo);
    }

    @Override
    public void updateReplyCount(int boardNo) {
        aMapper.updateReplyCount(boardNo);
    }

    @Override
    public int updateMemberStatus(Member member) {
        return aMapper.updateMemberStatus(member);
    }

    
    @Override
    public int getUserListCount(String searchKeyword) {
        return aMapper.getUserListCount(searchKeyword);
    }

    @Override
    public ArrayList<Member> selectUserList(PageInfo pi, String searchKeyword) {
        int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
        RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
        return aMapper.selectUserList(rowBounds, searchKeyword);
    }
    
    @Override
    public int deleteManyBoards(List<Integer> boardIds) {
        return aMapper.deleteManyBoards(boardIds);
    }

    @Override
    public Board selectBoard(int bId, int cateNo, int currentUserNo) {
        Board b = aMapper.selectBoard(bId, cateNo);
        if (b != null && currentUserNo != b.getBoardWriterNo()) {
            int result = aMapper.updateBoardCount(bId);
            if (result > 0) {
                b.setBoardCount(b.getBoardCount() + 1);
            }
        }
        return b;
    }

    @Override
    public ArrayList<Image> selectImageList() {
        return aMapper.selectImageList();
    }

    @Override
    @Transactional
    public int insertImageBoard(Board board, Image image) {
        int result = aMapper.insertBoard(board);
        if (result > 0 && image != null) {
            // board 객체의 boardNo가 자동으로 설정되었을 것입니다.
            image.setBoardNo(board.getBoardNo());
            result += aMapper.insertImage(image);
        }
        if (result != 2) {
            throw new RuntimeException("게시글 또는 이미지 저장 실패");
        }
        return result;
    }

    @Override
    public Image selectImage(int bId) {
        return aMapper.selectImage(bId);
    }

    @Override
    public Image getMainImageForBoard(int boardNo) {
        Image image = aMapper.getMainImageForBoard(boardNo);
        if (image != null) {
            logger.info("Image found for board {}: {}", boardNo, image.getImgRename());
        } else {
            logger.warn("No image found for board {}", boardNo);
        }
        return image;
    }

    @Override
    public void updateImageBoard(Board board, Image image) {
        aMapper.updateBoard(board);
        if (image != null) {
            aMapper.deleteImage(board.getBoardNo());
            aMapper.insertImage(image);
        }
    }

    @Override
    public void deleteImageBoard(int bId) {
        aMapper.deleteBoard(bId);
        aMapper.deleteImage(bId);
    }

    @Override
    public Board selectImageBoard(int bId) {
        return aMapper.selectBoard(bId, null);  // Assuming cateNo is not needed for image boards
    }

    @Override
    public ArrayList<Board> selectImageBoardList(PageInfo pi) {
        int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
        RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
        ArrayList<Board> boards = aMapper.selectBoardList(101, rowBounds);  // 101은 이미지 게시판 카테고리 번호
        
        for (Board board : boards) {
            Image image = aMapper.getMainImageForBoard(board.getBoardNo());
            if (image != null) {
                board.setImageUrl("/uploadFiles/" + image.getImgRename());
            }
        }

        return boards;
    }

    @Override
    public void updateImageBoard(Board board, MultipartFile file) {
        if (board == null) {
            throw new BoardException("게시글 정보가 없습니다.");
        }
        
        aMapper.updateBoard(board);
        
        if (file != null && !file.isEmpty()) {
            Image image = new Image();
            String[] returnArr = saveFile(file);
            if (returnArr != null) {
                image.setBoardNo(board.getBoardNo());
                image.setImgPath(returnArr[0]);
                image.setImgName(file.getOriginalFilename());
                image.setImgRename(returnArr[1]);
                image.setImgActive('Y');
                image.setImgRefType("BOARD");
                image.setImgTitle(1);
                
                aMapper.deleteImage(board.getBoardNo());
                aMapper.insertImage(image);
            }
        }
    }

    @Override
    public Board selectImageBoardAndIncrementCount(int bId, int currentUserNo) {
        Board board = aMapper.selectImageBoard(bId);
        if (board != null && currentUserNo != board.getBoardWriterNo()) {
            int result = aMapper.updateBoardCount(bId);
            if (result > 0) {
                board.setBoardCount(board.getBoardCount() + 1);
            }
        }
        return board;
    }
    
    @Override
	public int updateAdminStatus(Member member) {
		return aMapper.updateAdminStatus(member);
	}

	@Override
	public int deleteBoard(int bId) {
		return aMapper.deleteBoard(bId);
	}
}