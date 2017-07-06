package com.cqust.chat.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cqust.chat.dao.MessageDao;
import com.cqust.chat.entity.MyMessage;
import com.cqust.chat.service.MessageService;

@Component
public class MessageServiceImpl implements MessageService {
	@Autowired
	private MessageDao messageDao;

	public boolean insertMessage(MyMessage message) {
		return messageDao.insertMessage(message);
	}
	
}
