package com.cqust.chat.dao;

import com.cqust.chat.entity.MyMessage;

public interface MessageDao {
	
	boolean insertMessage(MyMessage message);
}
