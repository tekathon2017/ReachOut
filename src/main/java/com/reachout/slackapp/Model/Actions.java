package com.reachout.slackapp.Model;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Actions {
	String name;
	String value;
	@JsonIgnore
	String text;
	@JsonIgnore
	String style;
	@JsonIgnore
	String type;
	@JsonIgnore
	Confirm confirm;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Confirm getConfirm() {
		return confirm;
	}
	public void setConfirm(Confirm confirm) {
		this.confirm = confirm;
	}
	@Override
	public String toString() {
		return "Actions [name=" + name + ", text=" + text + ", style=" + style + ", type=" + type + ", value=" + value
				+ ", confirm=" + confirm + "]";
	}
	
}
