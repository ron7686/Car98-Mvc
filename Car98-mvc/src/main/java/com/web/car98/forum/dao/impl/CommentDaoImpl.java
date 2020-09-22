package com.web.car98.forum.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.car98.forum.dao.CommentDao;
import com.web.car98.forum.model.CommentBean;
@Repository
public class CommentDaoImpl implements CommentDao {
@Autowired
	SessionFactory factory;

	public CommentDaoImpl() {
//		factory = HibernateUtils.getSessionFactory();
	}

	@Override
	public void persist(CommentBean commentBean) {
		Session session = factory.getCurrentSession();
//		Transaction tx = null;
//		try {
//			tx = session.beginTransaction();
			session.persist(commentBean);
//			tx.commit();
//		} catch (Exception e) {
//			if (tx != null) {
//				tx.rollback();
//			}
//			e.printStackTrace();
//		}

	}

	@Override
	public int insertCom(CommentBean commentBean) {
		Session session = factory.getCurrentSession();
		int n = 0;
		session.save(commentBean);
		n++;
		return n;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<CommentBean> selectCom(Integer postId) {
		Session session = factory.getCurrentSession();
		List<CommentBean> list = null;
		String hql = "FROM CommentBean";
		list = session.createQuery(hql).getResultList();
		return list;
	}

	@Override
	public int updateMemCom(String comText, Date comTime, Integer memId, Integer postId, Integer comId) {
		Session session = factory.getCurrentSession();
		String hql = "UPDATE CommentBean SET comText = comText +: comText, comTime+: comTime"
				+ "WHERE comId =: comId AND postId =: postId AND memId =: memId";
		int n = 0;
		session.createQuery(hql).setParameter("comText", comText).setParameter("comTime", comTime)
				.setParameter("comId", comId).setParameter("postId", postId).setParameter("memId", memId);
		n++;
		return n;
	}

	@Override
	public int updateComByPk(CommentBean commentBean) {
		Integer n = 0;
		Session session = factory.getCurrentSession();
		session.saveOrUpdate(commentBean);
	    n++;
		return n;
	}

	@Override
	public int deleteComByPk(Integer comId) {
		Integer n = 0;
		Session session = factory.getCurrentSession();
		CommentBean commentbean = session.get(CommentBean.class, comId);
		session.delete(commentbean);
		n++;
		return n;
		
	}
	@Override
	public CommentBean selectComByPk(Integer comId) {
		CommentBean commentbean = null;
		Session session = factory.getCurrentSession();
	    commentbean = session.get(CommentBean.class, comId);
		return commentbean;
	}
	

}