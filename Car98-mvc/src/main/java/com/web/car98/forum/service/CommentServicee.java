package com.web.car98.forum.service;

import java.util.Date;
import java.util.List;

import com.web.car98.forum.model.CommentBean;

public interface CommentServicee {

	void persist(CommentBean cb);

	int insertCom(CommentBean commentBean);

	List<CommentBean> selectCom();

	int updateMemCom(String comText, Date comTime, Integer memId, Integer postId, Integer comId);

	int updateComByPk(CommentBean commentBean);

	int deleteComByPk(Integer comId);
}
