package com.web.car98.dao;

import java.util.List;

import com.web.car98.model.TalkBean;

public interface TalkDao {

	void persist(TalkBean tb);

	List<TalkBean> getAll();

	List<TalkBean> getPage(int page);

	int lastpage();

	TalkBean selectOne(int postID);

}