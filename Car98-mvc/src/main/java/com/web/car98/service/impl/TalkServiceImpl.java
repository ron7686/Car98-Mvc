package com.web.car98.service.impl;

import java.util.List;

import org.hibernate.SessionFactory;

import com.web.car98.dao.TalkDao;
import com.web.car98.dao.impl.TalkDaoImpl;
import com.web.car98.model.TalkBean;
import com.web.car98.service.TalkService;

import _00_init.util.HibernateUtils;


public class TalkServiceImpl implements TalkService  {

	TalkDao dao;
	SessionFactory factory;
	
	public TalkServiceImpl() {
		dao=new TalkDaoImpl();
		factory = HibernateUtils.getSessionFactory();
	}
	
	@Override
	public void persist(TalkBean tb) {
		dao.persist(tb);
	}
	
	@Override
	public List<TalkBean> select(int page){
		List<TalkBean> bean = null;
		bean = dao.getPage(page);
		return bean;
	}
	
	@Override
	public int lastpage() {
		return dao.lastpage();
	}

	@Override
	public TalkBean selectOne(int postID) {
		return dao.selectOne(postID);
	}
}
