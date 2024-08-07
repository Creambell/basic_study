package com.kh.spring.member.model.service;

import com.kh.spring.member.model.vo.Member;

public interface MemberService {

	Member login(Member u);

	int insertMember(Member m);

	int checkId(String userId);
}
