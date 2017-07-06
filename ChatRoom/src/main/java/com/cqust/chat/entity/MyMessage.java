package com.cqust.chat.entity;

import java.util.Date;

public class MyMessage {
	private Integer id;
	private  Long from;//来自于那个用户（用户ID）
	private String fromName;//来自于那个用户（名字）
	private Long to;//发送给那个用户
	private String content;//发送的内容
	private Date date;//发送的时间
	
	
	public Long getFrom() {
		return from;
	}
	public void setFrom(Long from) {
		this.from = from;
	}
	public String getFromName() {
		return fromName;
	}
	public void setFromName(String fromName) {
		this.fromName = fromName;
	}
	public Long getTo() {
		return to;
	}
	public void setTo(Long to) {
		this.to = to;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
}
