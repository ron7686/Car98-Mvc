package com.web.car98.forum.model;

import java.io.Serializable;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
	private String comTime;
	@Column(name = "ComLike")
	private Integer comLike;
	@Column(name = "ComPic")
	@JsonIgnore
	private Blob comPic;
	private String fileName;	
	@Column(name = "ComHate")
	private Integer comHate;
	
	@Transient
	MultipartFile commentMultipartFile;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "memId")
	private MemberBean memberBean;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "PostID")
	private TalkBean talkBean;
	
	@Transient
	private Integer floor;
	
	@Transient
	private ComLikeOrHateBean ComLikeOrHateBean;
	
	@OneToMany(mappedBy = "commentBean")
	private List<ComLikeOrHateBean> ComLikeOrHate = new ArrayList<>();

	public CommentBean() {
		super();
	}
	
	public CommentBean(Integer comId, String comText, String comTime, Integer comLike, Blob comPic, String fileName,
			Integer comHate, MemberBean memberBean, TalkBean talkBean) {
		super();
		this.comId = comId;
		this.comText = comText;
		this.comTime = comTime;
		this.comLike = comLike;
		this.comPic = comPic;
		this.fileName = fileName;
		this.comHate = comHate;
		this.memberBean = memberBean;
		this.talkBean = talkBean;
	}
	
	public CommentBean(Integer comId, String comText, String comTime, Integer comLike, Blob comPic, String fileName,
			Integer comHate, MultipartFile commentMultipartFile, MemberBean memberBean, TalkBean talkBean) {
		super();
		this.comId = comId;
		this.comText = comText;
		this.comTime = comTime;
		this.comLike = comLike;
		this.comPic = comPic;
		this.fileName = fileName;
		this.comHate = comHate;
		this.commentMultipartFile = commentMultipartFile;
		this.memberBean = memberBean;
		this.talkBean = talkBean;
	}

	
	
	public ComLikeOrHateBean getComLikeOrHateBean() {
		return ComLikeOrHateBean;
	}

	public void setComLikeOrHateBean(ComLikeOrHateBean comLikeOrHateBean) {
		ComLikeOrHateBean = comLikeOrHateBean;
	}

	public List<ComLikeOrHateBean> getComLikeOrHate() {
		return ComLikeOrHate;
	}

	public void setComLikeOrHate(List<ComLikeOrHateBean> comLikeOrHate) {
		ComLikeOrHate = comLikeOrHate;
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

	public String getComTime() {
		return comTime;
	}

	public void setComTime(String comTime) {
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
	

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public MultipartFile getCommentMultipartFile() {
		return commentMultipartFile;
	}

	public void setCommentMultipartFile(MultipartFile commentMultipartFile) {
		this.commentMultipartFile = commentMultipartFile;
	}
	

	public Integer getFloor() {
		return floor;
	}

	public void setFloor(Integer floor) {
		this.floor = floor;
	}

	@Override
	public String toString() {
		return "CommentBean [comId=" + comId + ", comText=" + comText + ", comTime=" + comTime + ", comLike=" + comLike
				+ ", comPic=" + comPic + ", fileName=" + fileName + ", comHate=" + comHate + ", commentMultipartFile="
				+ commentMultipartFile + ", memberBean=" + memberBean + ", talkBean=" + talkBean + "]";
	}

}
