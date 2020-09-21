package com.web.car98.member.model;

public class LoginBean {
	
	
	String user;
	String password;
	boolean rememberMe;
	String invalidCredentials;
	
	public LoginBean() {
	}
	
	public LoginBean(String user, String password, boolean rememberMe) {
		super();
		this.user = user;
		this.password = password;
		this.rememberMe = rememberMe;
	}
	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isRememberMe() {
		return rememberMe;
	}
	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	}

	public String getInvalidCredentials() {
		return invalidCredentials;
	}

	public void setInvalidCredentials(String invalidCredentials) {
		this.invalidCredentials = invalidCredentials;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LoginBean [user=");
		builder.append(user);
		builder.append(", password=");
		builder.append(password);
		builder.append(", rememberMe=");
		builder.append(rememberMe);
		builder.append("]");
		return builder.toString();
	}
	
	
}
