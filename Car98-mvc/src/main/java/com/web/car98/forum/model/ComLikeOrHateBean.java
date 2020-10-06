package com.web.car98.forum.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.web.car98.member.model.MemberBean;

@Entity
@Table(name = "comLikeOrHate")
public class ComLikeOrHateBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "comLohId")
	private Integer comLohId;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "memId")
	private MemberBean memberBean;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "comId")
	private CommentBean commentBean;
		
	@Column(name="comLikeOrHate")
	private Integer comLikeOrHate;

	public ComLikeOrHateBean() {
		super();
	}

	public ComLikeOrHateBean(Integer comLohId, MemberBean memberBean, CommentBean commentBean, Integer comLikeOrHate) {
		super();
		this.comLohId = comLohId;
		this.memberBean = memberBean;
		this.commentBean = commentBean;
		this.comLikeOrHate = comLikeOrHate;
	}

	public Integer getComLohId() {
		return comLohId;
	}

	public void setComLohId(Integer comLohId) {
		this.comLohId = comLohId;
	}

	public MemberBean getMemberBean() {
		return memberBean;
	}

	public void setMemberBean(MemberBean memberBean) {
		this.memberBean = memberBean;
	}

	public CommentBean getCommentBean() {
		return commentBean;
	}

	public void setCommentBean(CommentBean commentBean) {
		this.commentBean = commentBean;
	}

	public Integer getComLikeOrHate() {
		return comLikeOrHate;
	}

	public void setComLikeOrHate(Integer comLikeOrHate) {
		this.comLikeOrHate = comLikeOrHate;
	}

	@Override
	public String toString() {
		return "ComLikeOrHateBean [comLohId=" + comLohId + ", memberBean=" + memberBean + ", commentBean=" + commentBean
				+ ", comLikeOrHate=" + comLikeOrHate + "]";
	}


}
