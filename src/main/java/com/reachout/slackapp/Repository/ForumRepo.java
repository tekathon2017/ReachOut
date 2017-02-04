package com.reachout.slackapp.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;

import com.reachout.slackapp.UTIL.Constants;

import DAO.ForumDAO;

public class ForumRepo {
	ForumDAO fdao=new ForumDAO();
	public String newForumMessage(String token, String team_id, String team_domain, String channel_id,
			String channel_name, String user_id, String user_name, String command, String text, String response_url) {
		if (!text.isEmpty()) {
			
			String textToPost=text+" posted by "+user_name;
			// hit the business logic layer which will in turn call the dao to
			// insert the message into database and also call the android api nd
			// slack api for posting the message into the channel.
			String dbReturnStatus=addMessageToSlackDB(token, team_id, team_domain, channel_id,
					channel_name, user_id, user_name, command, textToPost, response_url);
			String returnStatus=postToForum(textToPost,user_id,team_id);
			if(returnStatus.equalsIgnoreCase("success"))
				return replyBackToUser(channel_id,response_url);
			else
				return "Oops!!We are terribly sorry.Posting to "+Constants.FORUMCHANNELNAME+" channel failed.";
		} else
			return "Invalid usage of the command.Kindly include the message after the command. \nPROPER USAGE - /forum <Your Messsage>";
	}


	private String addMessageToSlackDB(String token, String team_id, String team_domain, String channel_id,
			String channel_name, String user_id, String user_name, String command, String text, String response_url) {
		// TODO Auto-generated method stub
		fdao.addMessageToSlackDB(token, team_id, team_domain, channel_id,
				channel_name, user_id, user_name, command, text, response_url);
		return null;
	}


	public String postToForum(String message, String user_id, String team_id) {	
		String channel_id =fdao.getChannel_Id(team_id);
		//get the channel_id for FORUM channel from the database depending on the team_ID 
		String bot_access_token=fdao.getBotAccessToken(team_id);
		
		URIBuilder builder = null;
		try {
			builder = new URIBuilder(Constants.postMessageURL);
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		builder.setParameter("token", bot_access_token)
				.setParameter("channel", channel_id)
				.setParameter("text", message);
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost request = null;
		try {
			request = new HttpPost(builder.build());
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// request.setEntity(postingString);
		//request.setHeader("Content-type", "application/json;charset=UTF-8");
		HttpResponse response = null;
		try {
			response = client.execute(request);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(response.getStatusLine());
		System.out.println(response.getEntity().toString());
		BufferedReader rd = null;
		try {
			rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		} catch (UnsupportedOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		StringBuffer result = new StringBuffer();
		String line = "";
		try {
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("result from command is: " + result);
		if(response.getStatusLine().getStatusCode()==200)
			return "success";
		else
			return "Failed";
	}
	
	
	public String replyBackToUser(String channel_id, String response_url) {
		return "Message successfully posted to Forum channel.";

	}

}
