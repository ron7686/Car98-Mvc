package com.web.car98.forum.service;

import java.util.List;

import com.web.car98.forum.model.LikeOrHateBean;
import com.web.car98.forum.model.TalkBean;

public interface TalkService {

	void persist(TalkBean tb);

	List<TalkBean> getPage(List<TalkBean> li,int page);
	
	List<TalkBean> getPageByType(int page,String type);
	
	public List<TalkBean> getSearchList(List<TalkBean> li,String search);
	
	String intToType(String type);

	int lastpage(String type);
	
	int searchlastpage(String search);

	TalkBean selectOne(int postID);

	List<TalkBean> getAll();
	
	LikeOrHateBean getOneLoh(int postId, int memId);
	
	int getLike(List<LikeOrHateBean> loh);
	
	int getHate(List<LikeOrHateBean> loh);
	
	void setView(int postId);
	
	void deletePost(int postId);
	
	
}