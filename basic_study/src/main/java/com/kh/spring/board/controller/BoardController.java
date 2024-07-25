package com.kh.spring.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.kh.spring.board.model.service.BoardService;



@Controller
public class BoardController{
	
	@Autowired
	private BoardService bService;
	
	}
