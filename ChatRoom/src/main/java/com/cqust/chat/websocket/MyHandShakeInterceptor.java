package com.cqust.chat.websocket;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import com.cqust.chat.entity.Student;

public class MyHandShakeInterceptor implements HandshakeInterceptor{

	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Map<String, Object> attributes) throws Exception {
			System.out.println("WebSocket用户ID："+
					((ServletServerHttpRequest) request).getServletRequest().getSession(false).getAttribute("stu")
			+"建立了连接");
		
			if(request instanceof ServletServerHttpRequest){
				ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
				HttpSession session = servletRequest.getServletRequest().getSession();
				Student stu = (Student) session.getAttribute("stu");
				Long id = stu.getId();
				if(id != null)
					attributes.put("id", id);
				else
					return false;
			}
			
		return true;
	}

	public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Exception exception) {
		
	}

}
