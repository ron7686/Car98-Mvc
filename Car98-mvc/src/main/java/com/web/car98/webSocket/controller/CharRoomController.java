package com.web.car98.webSocket.controller;


import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.web.car98.webSocket.repository.ChatClientModel;
import com.web.car98.webSocket.repository.ServerResponseModel;

@Controller
public class CharRoomController {
	@MessageMapping("/messageControl")
	@SendTo("/msg/getResponse")
	public ServerResponseModel said(ChatClientModel responseMessage) 
			throws InterruptedException{

//		Thread.sleep(1000);
		return new ServerResponseModel(responseMessage.getMassage());			
	}
	
	
}
