package com.web.car98.member.service;

import java.util.List;

import com.web.car98.member.model.MemberBean;

public interface MemberService {
	boolean idExists(String id);
	int saveMember(MemberBean mb);
	MemberBean queryMember(Integer id);
	MemberBean checkIdPassword(String userId, String password) ;
	int updateLoginTime (String id);
	int updateUserData(MemberBean mb);
	List<MemberBean> findAllMembers();
	int deleteMemberById(Integer memId);
}
