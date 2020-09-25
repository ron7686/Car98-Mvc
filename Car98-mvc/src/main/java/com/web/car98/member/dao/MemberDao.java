package com.web.car98.member.dao;

import java.sql.Connection;

import com.web.car98.member.model.MemberBean;
import com.web.car98.order.model.OrderBean;



public interface MemberDao {
	
	public boolean idExists(String id);

	public int saveMember(MemberBean mb) ;
	
	public MemberBean queryMember(Integer id);
	
	public MemberBean checkIdPassword(String userId, String password);	
	
	public int updateLoginTime(String id);
	
	public int updateUserData(MemberBean mb);

	public void setConnection(Connection con);
	
	void updateUnpaidOrderAmount(OrderBean ob);
}