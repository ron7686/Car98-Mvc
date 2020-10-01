package com.web.car98.forum.model;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;

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
@Table(name = "comment")
public class CommentBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer comId;
	@Column(name = "ComText")
	private String comText;
	@Column(name = "ComTime")
	private Date comTime;
	@Column(name = "ComLike")
	private Integer comLike;
	@Column(name = "ComPic")
	private Blob comPic;
	@Column(name = "ComHate")
	private Integer comHate;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "memId")
	private MemberBean memberBean;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "PostID")
	private TalkBean talkBean;

	public CommentBean() {
		super();
	}

	public CommentBean(Integer comId, MemberBean memberBean, String comText, Date comTime, Integer comLike, Blob comPic,
			Integer comHate, TalkBean talkBean) {
		super();
		this.comId = comId;
		this.memberBean = memberBean;
		this.comText = comText;
		this.comTime = comTime;
		this.comLike = comLike;
		this.comPic = comPic;
		this.comHate = comHate;
		this.talkBean = talkBean;
	}

	public MemberBean getMemberBean() {
		return memberBean;
	}

	public void setMemberBean(MemberBean memberBean) {
		this.memberBean = memberBean;
	}

	public TalkBean getTalkBean() {
		return talkBean;
	}

	public void setTalkBean(TalkBean talkBean) {
		this.talkBean = talkBean;
	}

	public Integer getComId() {
		return comId;
	}

	public void setComId(Integer comId) {
		this.comId = comId;
	}

	public String getComText() {
		return comText;
	}

	public void setComText(String comText) {
		this.comText = comText;
	}

	public Date getComTime() {
		return comTime;
	}

	public void setComTime(Date comTime) {
		this.comTime = comTime;
	}

	public Integer getComLike() {
		return comLike;
	}

	public void setComLike(Integer comLike) {
		this.comLike = comLike;
	}

	public Blob getComPic() {
		return comPic;
	}

	public void setComPic(Blob comPic) {
		this.comPic = comPic;
	}

	public Integer getComHate() {
		return comHate;
	}

	public void setComHate(Integer comHate) {
		this.comHate = comHate;
	}

	@Override
	public String toString() {
		return "CommentBean [comId=" + comId + ", memberBean=" + memberBean + ", comText=" + comText + ", comTime="
				+ comTime + ", comLike=" + comLike + ", comPic=" + comPic + ", comHate=" + comHate + "]";
	}

}
