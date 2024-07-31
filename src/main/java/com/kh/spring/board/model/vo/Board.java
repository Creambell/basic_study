package com.kh.spring.board.model.vo;


import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Board {
	private int boardNo;
    private int cateNo;
    private int boardWriterNo;
    private String boardWriterName;
    private String boardTitle;
    private String boardContent;
    private int boardCount;
    private char boardStatus;
    private Date createDate;
    private Date updateDate;
}
