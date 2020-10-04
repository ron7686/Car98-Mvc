package com.web.car98.forum.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.car98.forum.dao.CommentDao;
import com.web.car98.forum.model.CommentBean;
import com.web.car98.forum.model.TalkBean;
import com.web.car98.member.model.MemberBean;

@Repository
public class CommentDaoImpl implements CommentDao {

	private int pageNo = 1; // 存放目前顯示頁面的編號
	private int onepage = 5; // 每頁顯示5則資料

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
	public int insertCom(CommentBean cb) {
		Session session = factory.getCurrentSession();
		int n = 0;
		session.save(cb);
		n++;
		return n;
	}

	@Override
	public TalkBean getTalkBeanById(int id) {
		Session session = factory.getCurrentSession();
		TalkBean tb = session.get(TalkBean.class, id);
		return tb;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<CommentBean> getComsByFk(Integer postId) {
		Session session = factory.getCurrentSession();
		List<CommentBean> list = null;
		String hql = "FROM CommentBean c where c.talkBean.PostID=:p";

		list = session.createQuery(hql).setParameter("p", postId).getResultList();

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
//		String hql = "Delete FROM CommentBean where comId=:comId";
		CommentBean commentbean = session.get(CommentBean.class, comId);
		session.delete(commentbean);
//		session.createQuery(hql).setParameter("comId",comId).executeUpdate();
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

	@Override
	public List<CommentBean> getPageCom(Integer page, Integer postId) {
		int getpage = (page - 1) * onepage;
		List<CommentBean> li = getComsByFk(postId);
		List<CommentBean> list_floor = new ArrayList<>(); // new一個容器裝有floor的
		Integer floor = 2; // 起始樓層
		for (CommentBean cb : li) {
			cb.setFloor(floor);
			floor++;
			list_floor.add(cb);
		}

		List<CommentBean> lipage = new ArrayList<>();
		if (page == 1) {
			for (int i = getpage; i < getpage + onepage - 1 && i < list_floor.size(); i++) {
				lipage.add(list_floor.get(i));
			}
		} else {
			for (int i = getpage - 1; i < getpage - 1 + onepage && i < list_floor.size(); i++) {
				lipage.add(list_floor.get(i));
			}
		}
		return lipage;
	}

	@Override
	public int getLastPage(Integer postId, Integer page) {
		int lastpage = 0;
		int op = 0;
		List<CommentBean> li = getComsByFk(postId);

		if (page == 1) {
			op = onepage - 1;
			lastpage = li.size() / op;
			page = li.size() % op;
			if (page > 0) lastpage++;
		} else {
			lastpage = li.size() / onepage;
			page = li.size() % onepage;
			if(page == 0) {
				lastpage++;
			}
		}
		return lastpage;
	}

	// 查出這則留言是哪個會員留的
	@Override
	public MemberBean queryMemberByComId(Integer comId) {
		CommentBean commentbean = null;
		Session session = factory.getCurrentSession();
		commentbean = session.get(CommentBean.class, comId);
		MemberBean memberBean = commentbean.getMemberBean();
		return memberBean;
	}

	// 查出這則PO文是哪個會員留的
	@Override
	public MemberBean queryMemberByPostId(Integer postId) {
		TalkBean talkBean = null;
		Session session = factory.getCurrentSession();
		talkBean = session.get(TalkBean.class, postId);
		MemberBean memberBean = talkBean.getMemberBean();
		return memberBean;
	}

}
