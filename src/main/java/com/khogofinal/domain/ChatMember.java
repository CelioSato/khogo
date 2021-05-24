package com.khogofinal.domain;

public class ChatMember {
	
	private Integer chatId;
	private String title;
	
	public ChatMember() {
		
	}
	
	public ChatMember(Integer chatId, String title) {
		super();
		this.chatId = chatId;
		this.title = title;
		
	}

	public Integer getChatId() {
		return chatId;
	}

	public void setChatId(Integer chatId) {
		this.chatId = chatId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}	
	
}
