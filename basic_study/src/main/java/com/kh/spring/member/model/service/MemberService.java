package com.kh.spring.member.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.kh.spring.member.model.vo.Member;

public interface MemberService {

	Member login(Member m);

	int insertMember(Member m);

	int checkId(String id);

	int checknickName(String nickName);
	
	ArrayList<HashMap<String, Object>> selectMyList(String id);

	int updateMember(Member m);

	int deleteMember(String id);

	int updatePwd(HashMap<String, String> map);
}
