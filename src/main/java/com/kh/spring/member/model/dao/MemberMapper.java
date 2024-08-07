package com.kh.spring.member.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.kh.spring.member.model.vo.Member;

@Mapper
public interface MemberMapper {

	Member login(Member u);

	int insertMember(Member m);

	int checkId(String userId);
}
