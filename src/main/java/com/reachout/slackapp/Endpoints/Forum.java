package com.reachout.slackapp.Endpoints;

import javax.inject.Inject;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.reachout.slackapp.Repository.ForumRepo;

@Path("/forum")
public class Forum {
	ForumRepo frepo=new ForumRepo();
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String postForumCommand(@FormParam("token") String token,
			@FormParam("team_id") String team_id,
			@FormParam("team_domain") String team_domain,
			@FormParam("channel_id") String channel_id,
			@FormParam("channel_name") String channel_name,
			@FormParam("user_id") String user_id,
			@FormParam("user_name") String user_name,
			@FormParam("command") String command,
			@FormParam("text") String text,
			@FormParam("response_url") String response_url) {
		
		String returnStatus=frepo.newForumMessage(token, team_id, team_domain, channel_id, channel_name, user_id, user_name, command, text, response_url);
		return returnStatus;
	}	
}
