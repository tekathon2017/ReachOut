package com.reachout.slackapp.Model;

import java.util.List;

public class Payload {
	List<Actions> actions;
	String callback_id;
	Team team;
	Channel channel;
	User user;
	String action_ts;
	String message_ts;
	String attachment_id;
	String token;
	String response_url;
	public List<Actions> getActions() {
		return actions;
	}
	public void setActions(List<Actions> actions) {
		this.actions = actions;
	}
	public String getCallback_id() {
		return callback_id;
	}
	public void setCallback_id(String callback_id) {
		this.callback_id = callback_id;
	}
	public Team getTeam() {
		return team;
	}
	public void setTeam(Team team) {
		this.team = team;
	}
	public Channel getChannel() {
		return channel;
	}
	public void setChannel(Channel channel) {
		this.channel = channel;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getAction_ts() {
		return action_ts;
	}
	public void setAction_ts(String action_ts) {
		this.action_ts = action_ts;
	}
	public String getMessage_ts() {
		return message_ts;
	}
	public void setMessage_ts(String message_ts) {
		this.message_ts = message_ts;
	}
	public String getAttachment_id() {
		return attachment_id;
	}
	public void setAttachment_id(String attachment_id) {
		this.attachment_id = attachment_id;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getResponse_url() {
		return response_url;
	}
	public void setResponse_url(String response_url) {
		this.response_url = response_url;
	}
	@Override
	public String toString() {
		return "Payload [actions=" + actions + ", callback_id=" + callback_id + ", team=" + team + ", channel="
				+ channel + ", user=" + user + ", action_ts=" + action_ts + ", message_ts=" + message_ts
				+ ", attachment_id=" + attachment_id + ", token=" + token + ", response_url=" + response_url + "]";
	}
	
}
