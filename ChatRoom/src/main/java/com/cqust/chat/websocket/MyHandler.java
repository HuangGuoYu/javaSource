package com.cqust.chat.websocket;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import com.cqust.chat.entity.MyMessage;
import com.cqust.chat.service.MessageService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
@Component
public class MyHandler implements WebSocketHandler{
	
	public static final Map<Long, WebSocketSession> userSocketSessionMap ;
	@Autowired
	private MessageService messageService;
	static{
		userSocketSessionMap = new HashMap<Long, WebSocketSession>();
	}
	
	
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
			
	}

	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		Long id = (Long) session.getAttributes().get("id");
		if(userSocketSessionMap.get(id) == null){
			userSocketSessionMap.put(id,session);
			System.out.println("以保存"+id);
		}
	}
	
	/**
	 * 消息处理，在客户端通过Websocket API发送的消息会经过这里，然后进行相应的处理
	 */
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		System.out.println(message.getPayload());
		MyMessage msg = new Gson().fromJson(message.getPayload().toString(), MyMessage.class);
		System.out.println(msg.getTo());
		msg.setDate(new Date());
		messageService.insertMessage(msg);
		if(userSocketSessionMap.get(msg.getTo()) != null)
		sendToUser(session,msg.getTo(),new TextMessage(new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson(msg)));
		else
		{
			msg.setFromName("系统消息");
			msg.setContent("对方不在线请等待对方上线");
			session.sendMessage(new TextMessage(new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson(msg)));
		}
	}
	
	private void sendToUser(WebSocketSession iSession,Long to, TextMessage textMessage) {
		WebSocketSession session = userSocketSessionMap.get(to);
		if(session.isOpen()){
			try {
				session.sendMessage(textMessage);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

	/**
	 * 消息传输错误处理
	 */
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		
	}

	public boolean supportsPartialMessages() {
		return false;
	}

	public void sendAll() {
		
			new Thread(new Runnable() {
				
				public void run() {
						WebSocketSession session = userSocketSessionMap.get(1);
						if(session != null && session.isOpen()){
							try {
								session.sendMessage(new TextMessage("123456"));
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
						System.out.println("session is null");
				}
			}).start();
		}
	

}
