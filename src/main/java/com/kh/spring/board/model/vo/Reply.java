package com.kh.spring.board.model.vo;

import java.sql.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor 			
@Getter
@Setter
@ToString
public class Reply {
	private int reNo;
    private int reWriterNo;
    private int boardNo;
    private String reContent;
    private Date createDate;
    private Date updateDate;
    private char reStatus;
    private int replyCount;
    private String userName;
	
}
