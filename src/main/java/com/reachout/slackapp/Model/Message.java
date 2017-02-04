package com.reachout.slackapp.Model;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Message {
	@JsonIgnore
	String token;
	@JsonIgnore
	String channel;
	@JsonIgnore
	String text;
	@JsonIgnore
	String response_type;
	@JsonIgnore
	String replace_original;
	@JsonIgnore
	String delete_original;	
	@JsonIgnore
	List<Attachments> attachments;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getResponse_type() {
		return response_type;
	}
	public void setResponse_type(String response_type) {
		this.response_type = response_type;
	}
	public String getReplace_original() {
		return replace_original;
	}
	public void setReplace_original(String replace_original) {
		this.replace_original = replace_original;
	}
	public String getDelete_original() {
		return delete_original;
	}
	public void setDelete_original(String delete_original) {
		this.delete_original = delete_original;
	}
	public List<Attachments> getAttachments() {
		return attachments;
	}
	public void setAttachments(List<Attachments> attachments) {
		this.attachments = attachments;
	}
	@Override
	public String toString() {
		return "Message [text=" + text + ", response_type=" + response_type + ", replace_original=" + replace_original
				+ ", delete_original=" + delete_original + ", attachments=" + attachments + "]";
	}
	
}
