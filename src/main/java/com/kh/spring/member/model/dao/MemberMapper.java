package com.kh.spring.member.model.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import com.kh.spring.member.model.vo.Member;

@Mapper
public interface MemberMapper {

	Member login(Member u);

	int insertMember(Member m);

	int checkId(String userId);

	ArrayList<HashMap<String, Object>> selectMyList(String userId);



}