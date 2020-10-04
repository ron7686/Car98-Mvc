package com.web.car98.forum.dao;

import java.util.Date;
import java.util.List;

import com.web.car98.forum.model.CommentBean;
import com.web.car98.forum.model.TalkBean;
import com.web.car98.member.model.MemberBean;

public interface CommentDao {

	void persist(CommentBean commentBean);

	int insertCom(CommentBean commentBean);

	List<CommentBean> getComsByFk(Integer postId);   //查詢一篇文章的總留言

	int updateMemCom(String comText, Date comTime, Integer memId, Integer postId, Integer comId);

	int updateComByPk(CommentBean commentBean);

	int deleteComByPk(Integer comId);

	CommentBean selectComByPk(Integer comId);

	TalkBean getTalkBeanById(int id);

	List<CommentBean> getPageCom(Integer page, Integer postId);  //得到當頁的留言

	MemberBean queryMemberByComId(Integer comId);  // 查出這則留言是哪個會員留的

	MemberBean queryMemberByPostId(Integer postId);  // 查出這則PO文是哪個會員留的

	int getLastPage(Integer postId, Integer page);

}
