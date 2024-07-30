package com.kh.spring.board.model.vo;

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
public class Image {
	private int imgNo;
    private int boardNo;
    private String imgPath;
    private String imgName;
    private String imgRename;
    private char imgActive;
    private Integer imgRefNum;
    private String imgRefType;
    private int imgTitle;
}
