package com.web.car98.member.dao.impl;

import java.sql.Connection;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.car98.member.dao.MemberDao;
import com.web.car98.member.model.MemberBean;
import com.web.car98.member.ude.MemberNotFoundException;
import com.web.car98.order.model.OrderBean;



// 本類別使用為標準的JDBC技術來存取資料庫。
@Repository
public class MemberDaoImpl_Spring implements MemberDao {

	@Autowired
	SessionFactory factory;

	public MemberDaoImpl_Spring() {
//		factory = HibernateUtils.getSessionFactory();
	}

	// 儲存MemberBean物件，將參數mb新增到Member表格內。
	public int saveMember(MemberBean mb) {

		int n = 0;
		Session session = factory.getCurrentSession();
		session.save(mb);
		return n;
	}

	// 判斷參數id(會員帳號)是否已經被現有客戶使用，如果是，傳回true，表示此id不能使用，
	// 否則傳回false，表示此id可使用。
	@SuppressWarnings("unchecked")
	@Override
	public boolean idExists(String id) {
		boolean exist = false;
		Session session = factory.getCurrentSession();
		String hql = "FROM MemberBean WHERE email = :mail";

		List<MemberBean> beans = session.createQuery(hql).setParameter("mail", id).getResultList();
		if (beans.size() == 0) {
			exist = false;
		} else {
			exist = true;
		}
		return exist;
	}

	// 由參數 id (會員帳號) 到Member表格中 取得某個會員的所有資料，傳回值為一個MemberBean物件，
	// 如果找不到對應的會員資料，傳回值為null。
	@SuppressWarnings("unchecked")
	@Override
	public MemberBean queryMember(Integer id) {
		MemberBean mb = null;
		String hql = "FROM MemberBean WHERE memId = :memId";
		Session session = factory.getCurrentSession();

		List<MemberBean> beans = session.createQuery(hql).setParameter("memId", id).getResultList();
		if (beans.size() > 0) {
			mb = beans.get(0);
		}

		return mb;
	}

	// 檢查使用者在登入時輸入的帳號與密碼是否正確。如果正確，傳回該帳號所對應的MemberBean物件，
	// 否則傳回 null。
	@SuppressWarnings("unchecked")
	@Override
	public MemberBean checkIdPassword(String userId, String password) {
		MemberBean mb = null;
		Session session = factory.getCurrentSession();
		String hql = "FROM MemberBean m WHERE m.email = :email and m.password = :password";

		List<MemberBean> beans = session.createQuery(hql).setParameter("email", userId)
				.setParameter("password", password).getResultList();
		if (beans.size() > 0) {
			mb = beans.get(0);
		}
		return mb;
	}

	// 更新使用者登入的時間
	@Override
	public int updateLoginTime(String id) {
		int n = 0;
		Session session = factory.getCurrentSession();
		String hql = "UPDATE MemberBean set LoginTime = now() where email= :email";
		session.createQuery(hql).setParameter("email", id).executeUpdate();
		n++;
		return n;
	}
	
	public int updateUserData(MemberBean mb) {
		int n = 0;
		Session session = factory.getCurrentSession();
		session.merge(mb);
		n++;
		return n;
	}

	@Override
	public void setConnection(Connection conn) {
		throw new RuntimeException("MemberDaoImpl_Hibernate 類別不支援setConnection()方法");
	}
	//功能：更新客戶的未付款訂購金額。
	@Override
	public void updateUnpaidOrderAmount(OrderBean ob) {
		double currentAmount = ob.getTotalPrice(); // 取出該訂單的總金額
		Double unpaidAmount = 0.0;
		MemberBean mb = null;
		// 讀取Member表格中，該客戶的未付款金額(unpaid_amount)
		String hql = "FROM MemberBean m WHERE m.memId = :mid";
		Session session = factory.getCurrentSession();
		
		try {
			mb = (MemberBean)session.createQuery(hql)
								.setParameter("mid", ob.getMemId())
								.getSingleResult();
		} catch(NoResultException ex) {
			
			throw new MemberNotFoundException("會員:" + ob.getMemId() + "找不到");
		} catch(NonUniqueResultException ex) {
			;
		} 	
		unpaidAmount = mb.getUnpaid_amount();
		
		// 更新Member表格之未付款餘額欄位 unpaid_amount
		String hql2 = "UPDATE MemberBean m SET m.unpaid_amount = m.unpaid_amount + :amt " 
		            + " WHERE memId = :mid";
		session.createQuery(hql2)
				.setParameter("amt", currentAmount)
				.setParameter("mid", ob.getMemId())
				.executeUpdate();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<MemberBean> findAllMembers() {
		Session session = factory.getCurrentSession();
		String hql = "From MemberBean";
		List<MemberBean> mbl = session.createQuery(hql).getResultList();
		return mbl;
	}

	@SuppressWarnings("unused")
	@Override
	public int deleteMemberById(Integer memId) {
		Session session = factory.getCurrentSession();
		MemberBean memberBean = session.get(MemberBean.class,memId);
		session.delete(memberBean);
		return 0;
	}
}
