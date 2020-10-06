package com.web.car98.forum.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.web.car98.member.model.MemberBean;

@Entity
@Table(name = "likeOrHate")
public class LikeOrHateBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "aa")
	private Integer aa;
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "postId")
	private TalkBean talkBean;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "MemId")
	private MemberBean memberBean;
	
	@Column(name="likeOrHate")
	private Integer likeOrHate;

	
	
	
	
	public LikeOrHateBean() {
		super();
	}

	

	public LikeOrHateBean(Integer aa, TalkBean talkBean, MemberBean memberBean, Integer likeOrHate) {
		super();
		this.aa = aa;
		this.talkBean = talkBean;
		this.memberBean = memberBean;
		this.likeOrHate = likeOrHate;
	}



	public Integer getAa() {
		return aa;
	}



	public void setAa(Integer aa) {
		this.aa = aa;
	}



	public TalkBean getTalkBean() {
		return talkBean;
	}

	public void setTalkBean(TalkBean talkBean) {
		this.talkBean = talkBean;
	}

	public MemberBean getMemberBean() {
		return memberBean;
	}

	public void setMemberBean(MemberBean memberBean) {
		this.memberBean = memberBean;
	}

	public Integer getLikeOrHate() {
		return likeOrHate;
	}

	public void setLikeOrHate(Integer likeOrHate) {
		this.likeOrHate = likeOrHate;
	}



	@Override
	public String toString() {
		return "LikeOrHateBean [aa=" + aa + ", talkBean=" + talkBean + ", memberBean=" + memberBean + ", likeOrHate="
				+ likeOrHate + "]";
	}

	
}
