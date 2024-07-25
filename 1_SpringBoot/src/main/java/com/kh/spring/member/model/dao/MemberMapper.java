package com.kh.spring.member.model.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import com.kh.spring.member.model.vo.Member;

@Mapper
public interface MemberMapper {
	
	Member login(Member m);

	int insertMember(Member m);

	ArrayList<HashMap<String, Object>> selectMyList(String id);

	int checkId(String id);

	int checknickName(String nickName);

	int updateMember(Member m);

	int deleteMember(String id);

	int updatePwd(HashMap<String, String> map);
	
}
