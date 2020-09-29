package com.web.car98.member.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.car98.member.dao.MemberDao;
import com.web.car98.member.model.MemberBean;
import com.web.car98.member.service.MemberService;

@Service
public class MemberServiceImpl_Spring implements MemberService {

	@Autowired
	MemberDao dao;

	public MemberServiceImpl_Spring() {
	}

	@Transactional
	@Override
	public int saveMember(MemberBean mb) {
		int n = 0;
		dao.saveMember(mb);
		n++;

		return n;
	}

	@Transactional
	@Override
	public boolean idExists(String id) {
		boolean result = false;

		result = dao.idExists(id);

		return result;
	}

	@Transactional
	@Override
	public MemberBean queryMember(Integer id) {
		MemberBean mb = null;

		mb = dao.queryMember(id);

		return mb;
	}

	@Transactional
	@Override
	public MemberBean checkIdPassword(String userId, String password) {
		MemberBean mb = null;

		mb = dao.checkIdPassword(userId, password);

		return mb;
	}

	@Transactional
	@Override
	public int updateLoginTime(String id) {
		int n = 0;

		dao.updateLoginTime(id);
		n++;

		return n;
	}

	@Transactional
	@Override
	public int updateUserData(MemberBean mb) {
		int n = 0;
		dao.updateUserData(mb);
		n++;
		return n;
	}
	
	@Transactional
	@Override
	public List<MemberBean> findAllMembers() {
		return dao.findAllMembers();
	}
}
