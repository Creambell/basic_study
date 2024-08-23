package com.kh.spring.member.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring.member.model.dao.MemberMapper;
import com.kh.spring.member.model.vo.Member;

@Service
public class MemberServiceImple implements MemberService {
	
	@Autowired
	private MemberMapper mMapper;

	@Override
	public Member login(Member u) {
		return mMapper.login(u);
	}

	@Override
	public int insertMember(Member m) {
		return mMapper.insertMember(m);
	}

	@Override
	public int checkId(String userId) {
		return mMapper.checkId(userId);
	}

	@Override
	public ArrayList<HashMap<String, Object>> selectMyList(String userId) {
		return mMapper.selectMyList(userId);
	}

	@Override
	public int countMemberPost(int userNo) {
		return mMapper.countMemberPost(userNo);

	}

	@Override
	public int countMemberReply(int userNo) {
		return mMapper.countMemberReply(userNo);
	}

	@Override
	public int deleteMember(int userNo) {
		return mMapper.deleteMember(userNo);
	}

	@Override
	public int updateMember(Member m) {
		return mMapper.updateMember(m);
	}
	
	@Override
	public int checkIdExceptCurrent(String userId, int currentUserNo) {
	    return mMapper.checkIdExceptCurrent(userId, currentUserNo);
	}
	

}