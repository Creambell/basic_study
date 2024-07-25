package com.kh.spring.board.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring.board.model.dao.BoardMapper;


@Service
public class BoardServiceImpl implements BoardService{

@Autowired
private BoardMapper bMapper;

}
