package com.web.car98.forum.model;

import java.io.Serializable;

public class CommentAllBean implements Serializable {
	
	private String comText;
	private Integer comId;
	private Integer postID;
	
	public CommentAllBean() {
		super();

	}
	
	public CommentAllBean(String comText, Integer comId, Integer postID) {
		super();
		this.comText = comText;
		this.comId = comId;
		this.postID = postID;
	}
	public String getComText() {
		return comText;
	}
	public void setComText(String comText) {
		this.comText = comText;
	}
	public Integer getComId() {
		return comId;
	}
	public void setComId(Integer comId) {
		this.comId = comId;
	}
	public Integer getPostID() {
		return postID;
	}
	public void setPostID(Integer postID) {
		this.postID = postID;
	}
	
}
