package com.web.car98.forum.service;

import java.util.List;

import com.web.car98.forum.model.LikeOrHateBean;
import com.web.car98.forum.model.TalkBean;

public interface TalkService {

	void persist(TalkBean tb);

	List<TalkBean> getPage(int page);
	
	List<TalkBean> getPageByType(int page,String type);
	
	String intToType(String type);

	int lastpage();

	TalkBean selectOne(int postID);

	List<TalkBean> getAll();
	
	LikeOrHateBean getOneLoh(int postId, int memId);
	
	int getLike(List<LikeOrHateBean> loh);
	
	int getHate(List<LikeOrHateBean> loh);
}