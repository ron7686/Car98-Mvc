package com.web.car98.forum.dao.impl;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.car98.forum.dao.TalkDao;
import com.web.car98.forum.model.LikeOrHateBean;
import com.web.car98.forum.model.TalkBean;

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
			session.save(tb);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<TalkBean> getAll(){
		List<TalkBean> li = new ArrayList<>();
		String hql = "FROM TalkBean";
		Session session = factory.getCurrentSession();
			li=session.createQuery(hql).list();
		Collections.reverse(li);
		return li;
	}

	@Override
	public List<TalkBean> getPage(List<TalkBean> li,int page){
		int getpage=(page-1)*onepage;
		List<TalkBean> lipage=new ArrayList<>();
		for(int i=getpage;i<getpage+onepage&&i<li.size();i++) {
			lipage.add(li.get(i));
			lipage.get(i-getpage).setPostCom(li.get(i).getComment().size());
		}
		return lipage;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<TalkBean> getAllByType(String type){
		List<TalkBean> li = new ArrayList<>();
		String hql = "FROM TalkBean t where t.PostType=:type";
		Session session = factory.getCurrentSession();
		li=session.createQuery(hql).setParameter("type", type).list();
		Collections.reverse(li);
		return li;
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
		String hql = "FROM TalkBean t where t.PostID=:postID";
		Session session = factory.getCurrentSession();
			tb=(TalkBean)session.createQuery(hql).
					setParameter("postID", postID).
					getSingleResult();
		return tb;
		
	}

	@Override
	public int PostType() {
		
		
		
		
		return 0;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<LikeOrHateBean> getloh(int postId) {
		List<LikeOrHateBean> loh=new ArrayList<>();
		String hql = "FROM LikeOrHateBean l where l.talkBean.PostID=:postId";
		Session session = factory.getCurrentSession();
		loh=session.createQuery(hql).
					setParameter("postId", postId).
					list();
		return loh;
	}

	@Override
	public LikeOrHateBean getOneLoh(int postId, int memId) {
		LikeOrHateBean loh=new LikeOrHateBean();
		String hql = "FROM LikeOrHateBean l where l.talkBean.PostID=:postId and l.memberBean.memId=:memId";
		Session session = factory.getCurrentSession();
		loh=(LikeOrHateBean)session.createQuery(hql).
					setParameter("postId", postId).
					setParameter("memId", memId).
					getSingleResult();
		return loh;
	}
	
	
	
	
}
