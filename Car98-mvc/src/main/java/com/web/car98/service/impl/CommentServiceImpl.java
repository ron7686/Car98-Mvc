package com.web.car98.service.impl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.car98.dao.CommentDao;
import com.web.car98.model.CommentBean;
import com.web.car98.service.CommentService;


@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	CommentDao dao;
//	SessionFactory factory;

	public CommentServiceImpl() {
//		dao = new CommentDaoImpl();
//		factory = HibernateUtils.getSessionFactory();
	}

	@Transactional
	@Override
	public void persist(CommentBean cb) {
		dao.persist(cb);
	}

	@Transactional
	@Override
	public int insertCom(CommentBean commentBean) {
		int n = 0;
//		Session session = factory.getCurrentSession();
//		Transaction tx = null;
//		try {
//			tx = session.beginTransaction();
			n = dao.insertCom(commentBean);
//			tx.commit();
//		} catch (Exception e) {
//			if (tx != null) {
//				tx.rollback();
//			}
//			e.printStackTrace();
//		}
		return n;
	}

	@Transactional
	@Override
	public List<CommentBean> selectCom(Integer postId) {
		List<CommentBean> list = null;
//		Session session = factory.getCurrentSession();
//		Transaction tx = null;
//		try {
//			tx = session.beginTransaction();
			list = dao.selectCom(postId);
//			tx.commit();
//		} catch (Exception e) {
//			if (tx != null) {
//				tx.rollback();
//			}
//			e.printStackTrace();
//		}
		return list;
	}

	@Transactional
	@Override
	public int updateMemCom(String comText, Date comTime, Integer memId, Integer postId, Integer comId) {
		int n = 0;
//		Session session = factory.getCurrentSession();
//		Transaction tx = null;
//		try {
//			tx = session.beginTransaction();
			n = dao.updateMemCom(comText,comTime,memId,postId,comId);
			n++;
//			tx.commit();
//		} catch (Exception e) {
//			if (tx != null) {
//				tx.rollback();
//			}
//			e.printStackTrace();
//		}
		return n;
	}

	@Transactional
	@Override
	public int updateComByPk(CommentBean commentBean) {
		int n = 0;
//		Session session = factory.getCurrentSession();
//		Transaction tx = null;
//		try {
//			tx = session.beginTransaction();
			n = dao.updateComByPk(commentBean);
			n++;
//			tx.commit();
//		} catch (Exception e) {
//			if (tx != null) {
//				tx.rollback();
//			}
//			e.printStackTrace();
//		}
		return n;
	}

	@Transactional
	@Override
	public int deleteComByPk(Integer comId) {
		int n = 0;
//		Session session = factory.getCurrentSession();
//		Transaction tx = null;
//		try {
//			tx = session.beginTransaction();
			n = dao.deleteComByPk(comId);
			n++;
//			tx.commit();
//		} catch (Exception e) {
//			if (tx != null) {
//				tx.rollback();
//			}
//			e.printStackTrace();
//		}
		return n;
	}

	@Transactional
	@Override
	public CommentBean selectComByPk(Integer comId) {
		CommentBean commentbean = null;
//		Session session = factory.getCurrentSession();
//		Transaction tx = null;
//		try {
//			tx = session.beginTransaction();
			commentbean = dao.selectComByPk(comId);
//			tx.commit();
//		} catch (Exception e) {
//			if (tx != null) {
//				tx.rollback();
//			}
//			e.printStackTrace();
//		}
		return commentbean;
	}

}
