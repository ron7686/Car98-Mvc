package com.web.car98.forum.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.car98.forum.dao.TalkDao;
import com.web.car98.forum.dao.impl.TalkDaoImpl;
import com.web.car98.forum.model.CommentBean;
import com.web.car98.forum.model.LikeOrHateBean;
import com.web.car98.forum.model.TalkBean;
import com.web.car98.forum.service.TalkService;

import _00_init.util.HibernateUtils;

@Service
@Transactional
public class TalkServiceImpl implements TalkService  {
@Autowired
	TalkDao dao;
	int onepage=6;
//	SessionFactory factory;
	
	public TalkServiceImpl() {
//		dao=new TalkDaoImpl();
//		factory = HibernateUtils.getSessionFactory();
	}
	@Override
	public void persist(TalkBean tb) {
		dao.persist(tb);
	}
	@Override
	public List<TalkBean> getPage(List<TalkBean> li,int page){
		int getpage=(page-1)*onepage;
		List<TalkBean> lipage=new ArrayList<>();
		for(int i=getpage;i<getpage+onepage&&i<li.size();i++) {
			lipage.add(li.get(i));
			lipage.get(i-getpage).setPostCom(li.get(i).getComment().size());
			if(li.get(i).getComment().size()>0)
			lipage.get(i-getpage).setCommentbean(li.get(i).getComment().get(li.get(i).getComment().size()-1));
		}
		return lipage;
	}
	
	@Override
	public int lastpage(String type) {
		List<TalkBean> li=new ArrayList<>();
		if(type==null||type.length()==0) {
			li = dao.getAll();
		}else {
			li = dao.getAllByType(type);
		}
		int lastpage=li.size()/onepage;
		if(li.size()%onepage>0)lastpage++;
		return lastpage;
	}
	@Override
	public TalkBean selectOne(int postID) {
		TalkBean tb=dao.selectOne(postID);
		List<LikeOrHateBean> loh=dao.getloh(postID);
		tb.setPostLike(getLike(loh));
		tb.setPostHate(getHate(loh));
		return tb;
	}
	@Override
	public List<TalkBean> getAll() {
	
		return dao.getAll();
	}
	
	@Override
	public List<TalkBean> getPageByType(int page,String type) {
		List<TalkBean> li=new ArrayList<>();
		if(type==null||type.length()==0) {
			li = dao.getAll();
		}else {
			li = dao.getAllByType(type);
		}
		return getPage(li,page);
	}
	@Override
	public String intToType(String type) {
		switch (type) {
		case "1":
			type = "討論";
			break;
		case "2":
			type = "分享＆心得";
			break;
		case "3":
			type = "求助＆問題";
			break;
		case "4":
			type = "公告";
			break;
		}
		return type;
	}
	@Override
	public int getLike(List<LikeOrHateBean> loh) {
		int count=0;
		for(LikeOrHateBean i:loh) {
			if(i.getLikeOrHate()==1) {
				count++;
			}
		}
		return count;
	}
	@Override
	public int getHate(List<LikeOrHateBean> loh) {
		int count=0;
		for(LikeOrHateBean i:loh) {
			if(i.getLikeOrHate()==2) {
				count++;
			}
		}
		return count;
	}
	@Override
	public LikeOrHateBean getOneLoh(int postId, int memId) {
		return dao.getOneLoh(postId, memId);
	}
	@Override
	public void setView(int postId) {
		TalkBean tb=dao.selectOne(postId);
		tb.setPostView(tb.getPostView()+1);
		dao.persist(tb);
	}
}
