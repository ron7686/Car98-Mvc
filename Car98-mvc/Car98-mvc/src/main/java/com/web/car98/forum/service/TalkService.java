package com.web.car98.forum.service;

import java.util.List;

import com.web.car98.forum.model.TalkBean;

public interface TalkService {

	void persist(TalkBean tb);

	List<TalkBean> select(int page);

	int lastpage();

	TalkBean selectOne(int postID);

}