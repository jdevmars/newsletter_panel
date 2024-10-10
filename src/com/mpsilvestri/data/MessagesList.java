package com.mpsilvestri.data;

public class MessagesList {
	
	private String subject;
	private String id;
	private String type;
	private String sentDate;
	private String tags;
	private String fromName;
	private String fromEmail;
	
	public MessagesList() {
		
	}
	
	@Override
	public String toString() {
		return "MessagesList [subject=" + subject + ", id=" + id + ", type=" + type + ", sentDate=" + sentDate
				+ ", tags=" + tags + ", fromName=" + fromName + ", fromEmail=" + fromEmail + "]";
	}

	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSentDate() {
		return sentDate;
	}
	public void setSentDate(String sentDate) {
		this.sentDate = sentDate;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getFromName() {
		return fromName;
	}
	public void setFromName(String fromName) {
		this.fromName = fromName;
	}
	public String getFromEmail() {
		return fromEmail;
	}
	public void setFromEmail(String fromEmail) {
		this.fromEmail = fromEmail;
	}
	
}
