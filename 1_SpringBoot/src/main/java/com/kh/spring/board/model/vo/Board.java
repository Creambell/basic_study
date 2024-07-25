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
	private int boardId;
	private String boardTitle;
	private String boardWriter;
	private String nickName;
	private String boardContent;
	private int boardCount;
	private Date boardCreateDate;
	private Date boardModifyDate;
	private String status;
	private int boardType;
}
