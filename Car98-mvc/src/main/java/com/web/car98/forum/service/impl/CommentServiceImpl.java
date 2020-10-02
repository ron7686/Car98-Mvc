package com.web.car98.forum.service.impl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.car98.forum.dao.CommentDao;
import com.web.car98.forum.model.CommentBean;
import com.web.car98.forum.service.CommentService;
import com.web.car98.member.model.MemberBean;

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
	public void persist(CommentBean commentBean) {
		dao.persist(commentBean);
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
		
//		Session session = factory.getCurrentSession();
//		Transaction tx = null;
//		try {
//			tx = session.beginTransaction();
		return dao.updateMemCom(comText, comTime, memId, postId, comId);
//			tx.commit();
//		} catch (Exception e) {
//			if (tx != null) {
//				tx.rollback();
//			}
//			e.printStackTrace();
//		}
	}

	@Transactional
	@Override
	public int updateComByPk(CommentBean commentBean) {
//		Session session = factory.getCurrentSession();
//		Transaction tx = null;
//		try {
//			tx = session.beginTransaction();
		return dao.updateComByPk(commentBean);
//			tx.commit();
//		} catch (Exception e) {
//			if (tx != null) {
//				tx.rollback();
//			}
//			e.printStackTrace();
//		}
	}

	@Transactional
	@Override
	public int deleteComByPk(Integer comId) {
//		Session session = factory.getCurrentSession();
//		Transaction tx = null;
//		try {
//			tx = session.beginTransaction();
		return dao.deleteComByPk(comId);
//			tx.commit();
//		} catch (Exception e) {
//			if (tx != null) {
//				tx.rollback();
//			}
//			e.printStackTrace();
//		}
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

	@Transactional
	@Override
	public List<CommentBean> getPageCom(Integer page, Integer postId) {
		List<CommentBean> bean = null;
		bean = dao.getPage(page, postId);
		return bean;
	}

	@Transactional
	@Override
	public int getLastpage(Integer postId) {
		return dao.getLastpage(postId);
	}

	@Transactional
	@Override
	public MemberBean queryMemberByComId(Integer comId) {		
		return dao.queryMemberByComId(comId);
	}

}
