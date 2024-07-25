package com.kh.spring.member.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring.member.model.dao.MemberMapper;

@Service
public class MemberServiceImple implements MemberService {
	
	@Autowired
	private MemberMapper mMapper;

}
