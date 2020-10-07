package com.web.car98.forum.model;

import java.io.Serializable;

public class CommentAllBean implements Serializable {
	
	private String comText;
	private String comId;
	private String postID;
	
	public CommentAllBean() {
		super();

	}
	
	public CommentAllBean(String comText, String comId, String postID) {
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
	public String getComId() {
		return comId;
	}
	public void setComId(String comId) {
		this.comId = comId;
	}
	public String getPostID() {
		return postID;
	}
	public void setPostID(String postID) {
		this.postID = postID;
	}
	
}
