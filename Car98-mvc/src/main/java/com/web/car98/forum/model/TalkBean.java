package com.web.car98.forum.model;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Date;
import java.sql.Timestamp;
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
@Table(name = "talk")
public class TalkBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer PostID;
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "MemID")
	private MemberBean memberBean;
	@Column(name = "MemID")
	@Transient
	private Integer MemID;
	
	@Transient
	MultipartFile PostMultipartFile;

	@Column(name = "PostTitle")
	private String PostTitle;
	@Column(name = "PostText")
	private String PostText;
	@Column(name = "PostTime")
	private String PostTime;
	@Transient
	private Integer PostLike;
	@Transient
	private Integer PostHate;
	@Transient
	private Integer PostCom;
	@Column(name = "PostView")
	private Integer PostView;
	@Transient
	private String Postmember;
	@Column(name = "PostType")
	private String PostType;
	@JsonIgnore
	@Column(name = "PostPic")
	private Blob PostPic;
	private String PostFileName;
	
	@OneToMany(mappedBy = "talkBean")
	private List<CommentBean> comment = new ArrayList<>();
	@OneToMany(mappedBy = "talkBean")
	private List<LikeOrHateBean> likeOrHate = new ArrayList<>();

	public TalkBean() {
		super();
	}

	public TalkBean(Integer postID, MemberBean memberBean, Integer memID,MultipartFile postMultipartFile, String postTitle, String postText,
			String postTime, Integer postLike, Integer postHate, Integer postCom, Integer postView, String postmember,
			String postType, Blob postPic,String PostFileName, List<CommentBean> comment) {
		super();
		PostID = postID;
		this.memberBean = memberBean;
		MemID = memID;

		PostTitle = postTitle;
		PostText = postText;
		PostTime = postTime;
		PostLike = postLike;
		PostHate = postHate;
		PostCom = postCom;
		PostView = postView;
		Postmember = postmember;
		PostType = postType;
		PostPic = postPic;
		this.comment = comment;
	}

	public List<LikeOrHateBean> getLikeOrHate() {
		return likeOrHate;
	}

	public void setLikeOrHate(List<LikeOrHateBean> likeOrHate) {
		this.likeOrHate = likeOrHate;
	}

	public MemberBean getMemberBean() {
		return memberBean;
	}

	public void setMemberBean(MemberBean memberBean) {
		this.memberBean = memberBean;
	}

	public Integer getMemID() {
		return MemID;
	}

	public void setMemID(Integer memID) {
		MemID = memID;
	}

	
	
	
	public MultipartFile getPostMultipartFile() {
		return PostMultipartFile;
	}

	public void setPostMultipartFile(MultipartFile postMultipartFile) {
		PostMultipartFile = postMultipartFile;
	}

	public Integer getPostView() {
		return PostView;
	}

	public void setPostView(Integer postView) {
		PostView = postView;
	}

	public String getPostmember() {
		return Postmember;
	}

	public void setPostmember(String postmember) {
		Postmember = postmember;
	}

	public String getPostType() {
		return PostType;
	}

	public void setPostType(String postType) {
		this.PostType = postType;
	}

	public List<CommentBean> getComment() {
		return comment;
	}

	public void setComment(List<CommentBean> comment) {
		this.comment = comment;
	}

	public Integer getPostID() {
		return PostID;
	}

	public void setPostID(Integer postID) {
		PostID = postID;
	}

	public String getPostTitle() {
		return PostTitle;
	}

	public void setPostTitle(String postTitle) {
		PostTitle = postTitle;
	}

	public String getPostText() {
		return PostText;
	}

	public void setPostText(String postText) {
		PostText = postText;
	}

	public String getPostTime() {
		return PostTime;
	}

	public void setPostTime(String postTime) {
		PostTime = postTime;
	}

	public Integer getPostLike() {
		return PostLike;
	}

	public void setPostLike(Integer postLike) {
		PostLike = postLike;
	}

	public Integer getPostHate() {
		return PostHate;
	}

	public void setPostHate(Integer postHate) {
		PostHate = postHate;
	}

	public Integer getPostCom() {
		return PostCom;
	}

	public void setPostCom(Integer postCom) {
		PostCom = postCom;
	}

	public Blob getPostPic() {
		return PostPic;
	}

	public void setPostPic(Blob postPic) {
		PostPic = postPic;
	}

	
	
	
	

	public String getPostFileName() {
		return PostFileName;
	}

	public void setPostFileName(String postFileName) {
		PostFileName = postFileName;
	}

	@Override
	public String toString() {
		return "TalkBean [PostID=" + PostID + ", memberBean=" + memberBean + ", PostTitle=" + PostTitle + ", PostText="
				+ PostText + ", PostTime=" + PostTime + ", PostLike=" + PostLike + ", PostHate=" + PostHate
				+ ", PostCom=" + PostCom + ", PostType=" + PostType + ", PostPic=" + PostPic + "]";
	}

}
