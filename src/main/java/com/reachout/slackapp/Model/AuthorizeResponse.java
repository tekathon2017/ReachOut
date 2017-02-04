package com.reachout.slackapp.Model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthorizeResponse {
	@Override
	public String toString() {
		return "AuthorizeResponse [ok=" + ok + ", access_token=" + access_token + ", scope=" + scope + ", user_id="
				+ user_id + ", team_name=" + team_name + ", team_id=" + team_id + ", bot=" + bot + "]";
	}
	String ok;
	String access_token;
	String scope;
	String user_id;
	String team_name;
	String team_id;
	Bot bot;
	public String getOk() {
		return ok;
	}
	public void setOk(String ok) {
		this.ok = ok;
	}
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getTeam_name() {
		return team_name;
	}
	public void setTeam_name(String team_name) {
		this.team_name = team_name;
	}
	public String getTeam_id() {
		return team_id;
	}
	public void setTeam_id(String team_id) {
		this.team_id = team_id;
	}
	public Bot getBot() {
		return bot;
	}
	public void setBot(Bot bot) {
		this.bot = bot;
	}
	
}
