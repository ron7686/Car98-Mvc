package com.web.car98.forum.service;

import java.util.Date;
import java.util.List;

import com.web.car98.forum.model.CommentBean;
import com.web.car98.member.model.MemberBean;

public interface CommentService {

	void persist(CommentBean commentBean);

	int insertCom(CommentBean commentBean);

	List<CommentBean> getComsByFk(Integer postId);

	int updateMemCom(String comText, Date comTime, Integer memId, Integer postId, Integer comId);

	int updateComByPk(CommentBean commentBean);

	int deleteComByPk(Integer comId);

	CommentBean selectComByPk(Integer comId);

	List<CommentBean> getPageCom(Integer page, Integer posId);

	MemberBean queryMemberByComId(Integer comId);

	MemberBean queryMemberByPostId(Integer postId);

	int getLastPage(Integer postId, Integer page);

}
