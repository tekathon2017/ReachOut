package com.reachout.slackapp.Model;

import java.util.List;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Attachments {
	String text;
	String title;
	String fallback;
	String callback_id;
	String color;
	String attachment_type;
	List<Actions> actions;
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getFallback() {
		return fallback;
	}
	public void setFallback(String fallback) {
		this.fallback = fallback;
	}
	public String getCallback_id() {
		return callback_id;
	}
	public void setCallback_id(String callback_id) {
		this.callback_id = callback_id;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getAttachment_type() {
		return attachment_type;
	}
	public void setAttachment_type(String attachment_type) {
		this.attachment_type = attachment_type;
	}
	public List<Actions> getActions() {
		return actions;
	}
	public void setActions(List<Actions> actions) {
		this.actions = actions;
	}
	@Override
	public String toString() {
		return "Attachments [title=" + title + ", fallback=" + fallback + ", callback_id=" + callback_id + ", color="
				+ color + ", actions=" + actions + "]";
	}
	
}
