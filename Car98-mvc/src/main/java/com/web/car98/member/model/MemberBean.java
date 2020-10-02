package com.web.car98.member.model;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.web.car98.forum.model.CommentBean;
import com.web.car98.forum.model.TalkBean;

@Entity
@Table(name = "mem")
public class MemberBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer memId;

	private String email;
	private String password;
	@Transient
	private String password1;
	private String name;
	private String id;
	private String phone;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Taipei")
	private Date birth;
	private String sex;
	@JsonIgnore
	private Blob headPic;
	private String fileName;
	private Integer levels;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Taipei")
	private Timestamp meCreate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Taipei")
	private Timestamp loginTime;
	Double unpaid_amount;
	@Transient
	MultipartFile memberMultipartFile;

	@OneToMany(mappedBy = "memberBean")
	private List<TalkBean> talkBean = new ArrayList<>();

	@OneToMany(mappedBy = "memberBean")
	private List<CommentBean> commentbean = new ArrayList<>();

	public MemberBean() {

	}

	public MemberBean(Integer memId, String email, String password, String name, String id, String phone, Date birth,
			String sex, Blob headPic, String fileName, Integer levels, Timestamp meCreate, Timestamp loginTime) {
		super();
		this.memId = memId;
		this.email = email;
		this.password = password;
		this.name = name;
		this.id = id;
		this.phone = phone;
		this.birth = birth;
		this.sex = sex;
		this.headPic = headPic;
		this.fileName = fileName;
		this.levels = levels;
		this.meCreate = meCreate;
		this.loginTime = loginTime;
	}

	public MemberBean(Integer memId, String email, String password, String password1, String name, String id,
			String phone, Date birth, String sex, Blob headPic, String fileName, Integer levels, Timestamp meCreate,
			Timestamp loginTime, Double unpaid_amount, MultipartFile memberMultipartFile) {
		super();
		this.memId = memId;
		this.email = email;
		this.password = password;
		this.password1 = password1;
		this.name = name;
		this.id = id;
		this.phone = phone;
		this.birth = birth;
		this.sex = sex;
		this.headPic = headPic;
		this.fileName = fileName;
		this.levels = levels;
		this.meCreate = meCreate;
		this.loginTime = loginTime;
		this.unpaid_amount = unpaid_amount;
		this.memberMultipartFile = memberMultipartFile;
	}

	public MemberBean(Integer memId, String email, String password, String password1, String name, String id,
			String phone, Date birth, String sex, Blob headPic, String fileName, Integer levels, Timestamp loginTime) {
		super();
		this.memId = memId;
		this.email = email;
		this.password = password;
		this.password1 = password1;
		this.name = name;
		this.id = id;
		this.phone = phone;
		this.birth = birth;
		this.sex = sex;
		this.headPic = headPic;
		this.fileName = fileName;
		this.levels = levels;
		this.loginTime = loginTime;
	}

	public Double getUnpaid_amount() {
		return unpaid_amount;
	}

	public void setUnpaid_amount(Double unpaid_amount) {
		this.unpaid_amount = unpaid_amount;
	}

	public Integer getMemId() {
		return memId;
	}

	public void setMemId(Integer memId) {
		this.memId = memId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword1() {
		return password1;
	}

	public void setPassword1(String password1) {
		this.password1 = password1;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Blob getHeadPic() {
		return headPic;
	}

	public void setHeadPic(Blob headPic) {
		this.headPic = headPic;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Integer getLevels() {
		return levels;
	}

	public void setLevels(Integer levels) {
		this.levels = levels;
	}

	public Timestamp getMeCreate() {
		return meCreate;
	}

	public void setMeCreate(Timestamp meCreate) {
		this.meCreate = meCreate;
	}

	public Timestamp getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Timestamp loginTime) {
		this.loginTime = loginTime;
	}

	public MultipartFile getMemberMultipartFile() {
		return memberMultipartFile;
	}

	public void setMemberMultipartFile(MultipartFile memberMultipartFile) {
		this.memberMultipartFile = memberMultipartFile;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MemberBean [memId=");
		builder.append(memId);
		builder.append(", email=");
		builder.append(email);
		builder.append(", password=");
		builder.append(password);
		builder.append(", name=");
		builder.append(name);
		builder.append(", id=");
		builder.append(id);
		builder.append(", phone=");
		builder.append(phone);
		builder.append(", birth=");
		builder.append(birth);
		builder.append(", sex=");
		builder.append(sex);
		builder.append(", headPic=");
		builder.append(headPic);
		builder.append(", fileName=");
		builder.append(fileName);
		builder.append(", levels=");
		builder.append(levels);
		builder.append(", meCreate=");
		builder.append(meCreate);
		builder.append(", loginTime=");
		builder.append(loginTime);
		builder.append("]");
		return builder.toString();
	}
}
