package com.kh.spring.member.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring.member.model.dao.MemberMapper;
import com.kh.spring.member.model.vo.Member;

@Service
public class MemberServiceImple implements MemberService {
	
	@Autowired
	private MemberMapper mMapper;
	
	@Override
	public Member login(Member m) {
		return mMapper.login(m);
	}

	@Override
	public int insertMember(Member m) {
		return mMapper.insertMember(m);
	}

	@Override
	public int checkId(String id) {
		return mMapper.checkId(id);
	}

	@Override
	public int checknickName(String nickName) {
		return mMapper.checknickName(nickName);
	}
	
	@Override
	public ArrayList<HashMap<String, Object>> selectMyList(String id) {
		return mMapper.selectMyList(id);
	}

	@Override
	public int updateMember(Member m) {
		return mMapper.updateMember(m);
	}

	@Override
	public int deleteMember(String id) {
		return mMapper.deleteMember(id);
	}

	@Override
	public int updatePwd(HashMap<String, String> map) {
		return mMapper.updatePwd(map);
	}

}
