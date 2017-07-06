package com.cqust.chat.service;

import com.cqust.chat.entity.MyMessage;

public interface MessageService {
	
	boolean insertMessage(MyMessage message);
	
}
