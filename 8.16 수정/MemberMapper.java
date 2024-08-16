package com.kh.spring.member.model.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.kh.spring.member.model.vo.Member;

@Mapper
public interface MemberMapper {

	Member login(Member u);

	int insertMember(Member m);

	int checkId(String userId);

	ArrayList<HashMap<String, Object>> selectMyList(String userId);

	int countMemberPost(int userNo);

	int countMemberReply(int userNo);

	int deleteMember(int userNo);

	int updateMember(Member m);

	int checkIdExceptCurrent(String userId, int currentUserNo);




}
