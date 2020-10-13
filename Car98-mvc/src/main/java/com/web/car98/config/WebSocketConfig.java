package com.web.car98.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
//宣告啟用STOMP協定,WebSocket的子協定來傳輸(message broker)消息
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	// 用來映射指定URL,方法內註冊一個STOMP的endpoint,並且指定使用SockJS協定
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
    	// 表示 增加了一個 /socket 端點，客戶端可以從此端點連線 withSockJS 開啟SockJS
    	registry.addEndpoint("/chatRoom").withSockJS();
    }
    
    // 設置消息連接請求的各種規範信息 配製訊息代理
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config)     {
    	// 客戶端接收服務端消息的地址的前置信息    	
    	config.enableSimpleBroker("/msg/getResponse");
    }
}
