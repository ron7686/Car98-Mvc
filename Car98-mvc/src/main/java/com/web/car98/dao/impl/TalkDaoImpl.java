package com.web.car98.dao.impl;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.car98.dao.TalkDao;
import com.web.car98.model.TalkBean;

import _00_init.util.HibernateUtils;

@Repository
public class TalkDaoImpl implements TalkDao  {
	int onepage=5;
	@Autowired
	SessionFactory factory;
	
	
	public TalkDaoImpl() {
//		factory = HibernateUtils.getSessionFactory();
	}
	
	@Override
	public void persist(TalkBean tb) {
		Session session = factory.getCurrentSession();
//		Transaction tx=null;
//		try {
//			tx=session.beginTransaction();
			session.persist(tb);
//			tx.commit();
//		} catch (Exception e) {
//			if(tx !=null) {
//				tx.rollback();
//			}
//			e.printStackTrace();
//		}
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<TalkBean> getAll(){
		
		List<TalkBean> li = new ArrayList<>();
//		Transaction tx = null;
		String hql = "FROM TalkBean";
		Session session = factory.getCurrentSession();
//		try {
//			tx = session.beginTransaction();
			li=session.createQuery(hql).list();
//			tx.commit();
//		} catch (Exception ex) {
//			if (tx != null)
//				tx.rollback();
//			ex.printStackTrace();
//			throw new RuntimeException(ex);
//		}
		Collections.reverse(li);
		return li;
	}

	@Override
	public List<TalkBean> getPage(int page){
		int getpage=(page-1)*onepage;
		List<TalkBean> li = getAll();
		List<TalkBean> lipage=new ArrayList<>();
		for(int i=getpage;i<getpage+onepage&&i<li.size();i++) {
			lipage.add(li.get(i));
		}
		return lipage;
	}

	@Override
	public int lastpage() {
		int lastpage;
		int page;
		List<TalkBean> li = getAll();
		lastpage=li.size()/onepage;
		page=li.size()%onepage;
		if(page>0)lastpage++;
		return lastpage;
	}
	
	@Override
	public TalkBean selectOne(int postID) {

		TalkBean tb = new TalkBean();
//		Transaction tx = null;
		String hql = "FROM TalkBean t where t.PostID=:postID";
		Session session = factory.getCurrentSession();
//		try {
//			tx = session.beginTransaction();
			tb=(TalkBean)session.createQuery(hql).setParameter("postID", postID).getSingleResult();
//			tx.commit();
//		} catch (Exception ex) {
//			if (tx != null)
//				tx.rollback();
//			ex.printStackTrace();
//			throw new RuntimeException(ex);
//		}
		return tb;
		
	}
}
