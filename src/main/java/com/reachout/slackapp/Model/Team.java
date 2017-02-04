package com.reachout.slackapp.Model;

public class Team {
	@Override
	public String toString() {
		return "Team [id=" + id + ", domain=" + domain + "]";
	}
	String id;
	String domain;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	
}
