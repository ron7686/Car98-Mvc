package com.web.car98.webSocket.repository;

public class ChatClientModel {
	private String massage;

	public ChatClientModel() {
		
	}
	
	public ChatClientModel(String massage) {
		this.massage = massage;
	}

	public String getMassage() {
		return massage;
	}

	public void setMassage(String massage) {
		this.massage = massage;
	}
}
