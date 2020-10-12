package com.web.car98.forum.service;

import java.util.Date;
import java.util.List;

import com.web.car98.forum.model.ComLikeOrHateBean;
import com.web.car98.forum.model.CommentBean;
import com.web.car98.forum.model.LikeOrHateBean;
import com.web.car98.member.model.MemberBean;

public interface CommentService {

	void persist(CommentBean commentBean);

	int insertCom(CommentBean commentBean);

	List<CommentBean> getComsByFk(Integer postId);  //查詢一篇文章的總留言

	int updateMemCom(String comText, Date comTime, Integer memId, Integer postId, Integer comId);

	int updateComByPk(CommentBean commentBean);

	void deleteComByPk(Integer comId);

	CommentBean selectComByPk(Integer comId);

	List<CommentBean> getPageCom(Integer page, Integer posId,Integer memId);  //得到當頁的留言

	int getLastPage(Integer postId );
	
	MemberBean queryMemberByComId(Integer comId);  // 查出這則留言是哪個會員留的

	MemberBean queryMemberByPostId(Integer postId);  // 查出這則PO文是哪個會員留的

	void saveLike(LikeOrHateBean loh);

	void saveComLike(ComLikeOrHateBean cloh);

	int getComLike(List<ComLikeOrHateBean> cloh);

	int getComHate(List<ComLikeOrHateBean> cloh);
	
	List<ComLikeOrHateBean> getComLoh(int comId);

	ComLikeOrHateBean getComOneLoh(int comId, int memId); //得到一篇留言裡會員的likeorhate (getone)


}
