package com.reachout.slackapp.Model;

import java.util.ArrayList;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Channel {

    String id;
    String name;
    @JsonIgnore
    String is_channel;
    @JsonIgnore
    String created;
    @JsonIgnore
    String creator;
    @JsonIgnore
    String is_archived;
    @JsonIgnore
    String is_general;

    @JsonIgnore
    ArrayList<String> members;

    @JsonIgnore
    Topic topic;
    @JsonIgnore
    Purpose purpose;

    @JsonIgnore
    String is_member;
    @Override
	public String toString() {
		return "Channel [id=" + id + ", name=" + name + ", is_channel=" + is_channel + ", created=" + created
				+ ", creator=" + creator + ", is_archived=" + is_archived + ", is_general=" + is_general + ", members="
				+ members + ", topic=" + topic + ", purpose=" + purpose + ", is_member=" + is_member + ", last_read="
				+ last_read + ", latest=" + latest + ", unread_count=" + unread_count + ", unread_count_display="
				+ unread_count_display + "]";
	}
	@JsonIgnore
    String last_read;
    @JsonIgnore
    String latest;
    @JsonIgnore
    String unread_count;
    @JsonIgnore
    String unread_count_display;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIs_channel() {
		return is_channel;
	}
	public void setIs_channel(String is_channel) {
		this.is_channel = is_channel;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getIs_archived() {
		return is_archived;
	}
	public void setIs_archived(String is_archived) {
		this.is_archived = is_archived;
	}
	public String getIs_general() {
		return is_general;
	}
	public void setIs_general(String is_general) {
		this.is_general = is_general;
	}
	public ArrayList<String> getMembers() {
		return members;
	}
	public void setMembers(ArrayList<String> members) {
		this.members = members;
	}
	public Topic getTopic() {
		return topic;
	}
	public void setTopic(Topic topic) {
		this.topic = topic;
	}
	public Purpose getPurpose() {
		return purpose;
	}
	public void setPurpose(Purpose purpose) {
		this.purpose = purpose;
	}
	public String getIs_member() {
		return is_member;
	}
	public void setIs_member(String is_member) {
		this.is_member = is_member;
	}
	public String getLast_read() {
		return last_read;
	}
	public void setLast_read(String last_read) {
		this.last_read = last_read;
	}
	public String getLatest() {
		return latest;
	}
	public void setLatest(String latest) {
		this.latest = latest;
	}
	public String getUnread_count() {
		return unread_count;
	}
	public void setUnread_count(String unread_count) {
		this.unread_count = unread_count;
	}
	public String getUnread_count_display() {
		return unread_count_display;
	}
	public void setUnread_count_display(String unread_count_display) {
		this.unread_count_display = unread_count_display;
	}

}
