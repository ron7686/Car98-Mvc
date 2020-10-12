package com.web.car98.forum.dao;

import java.util.List;

import com.web.car98.forum.model.LikeOrHateBean;
import com.web.car98.forum.model.TalkBean;

public interface TalkDao {

	void persist(TalkBean tb);
	
	void deletePost(int postId);

	List<TalkBean> getAll();
	
	List<TalkBean> getAllByType(String type);

	TalkBean selectOne(int postID);
	
	List<LikeOrHateBean> getloh(int postId);
	
	LikeOrHateBean getOneLoh(int postId,int memId);
}