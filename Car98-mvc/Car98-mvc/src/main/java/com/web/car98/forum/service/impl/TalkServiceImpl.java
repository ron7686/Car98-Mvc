package com.web.car98.forum.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.car98.forum.dao.TalkDao;
import com.web.car98.forum.dao.impl.TalkDaoImpl;
import com.web.car98.forum.model.TalkBean;
import com.web.car98.forum.service.TalkService;

import _00_init.util.HibernateUtils;

@Service
public class TalkServiceImpl implements TalkService  {
@Autowired
	TalkDao dao;
//	SessionFactory factory;
	
	public TalkServiceImpl() {
//		dao=new TalkDaoImpl();
//		factory = HibernateUtils.getSessionFactory();
	}
	@Transactional
	@Override
	public void persist(TalkBean tb) {
		dao.persist(tb);
	}
	@Transactional
	@Override
	public List<TalkBean> select(int page){
		List<TalkBean> bean = null;
		bean = dao.getPage(page);
		return bean;
	}
	@Transactional
	@Override
	public int lastpage() {
		return dao.lastpage();
	}
@Transactional
	@Override
	public TalkBean selectOne(int postID) {
		return dao.selectOne(postID);
	}
}
