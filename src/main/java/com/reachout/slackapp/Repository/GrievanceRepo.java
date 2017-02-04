package com.reachout.slackapp.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;

import com.reachout.slackapp.UTIL.Constants;

import DAO.GrievanceDAO;

public class GrievanceRepo {
	GrievanceDAO gdao=new GrievanceDAO();
	
	public String newGrievanceMessage(String token, String team_id, String team_domain, String channel_id,
			String channel_name, String user_id, String user_name, String command, String text, String response_url) {
		if (!text.isEmpty()) {
			//String textToPost=text+" posted by "+user_name;
			//first step: send the first interactive message to user to ask for named or anonymous.
			//second step : send second interactive message to ask for public or private.			
			//third step: check if private is selected for the previous message thyen ask the group it needs to be sent to.			
			// hit the business logic layer which will in turn call the dao to
			// insert the message into database and also call the android api nd
			// slack api for posting the message into the channel.
			String dbReturnStatus=addMessageToSlackDB(token, team_id, team_domain, channel_id,
					channel_name, user_id, user_name, command, text, response_url);
			InteractiveMessageRepo imRepo=new InteractiveMessageRepo();

				return imRepo.postInteractiveMessages(response_url,channel_id,team_id,token);
//				return replyBackToUser(channel_id,response_url);
		} else
			return "Invalid usage of the command.Kindly include the message after the command. \nPROPER USAGE - /grievance <Your Messsage>";

	}


	public String postToGrievance(String message_token) {
		String team_id=gdao.getTeam_Id(message_token);
		String channel_id =gdao.getChannel_Id(team_id);
		//get the channel_id for FORUM channel from the database depending on the team_ID 
		String bot_access_token=gdao.getBotAccessToken(team_id);
		String message=gdao.getMessage(message_token);
		String anonymity=gdao.getAnonymity(message_token);
		if(anonymity.equalsIgnoreCase(Constants.NAMED_VALUE))
		{
			String user_name=gdao.getUser_Name(message_token);
			message=message+" posted by "+user_name;
		}
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
			return "success";
	}
	
	public String replyBackToUser(String channel_id, String response_url) {
		return "Message successfully posted to Grievance channel.";

	}
	private String addMessageToSlackDB(String token, String team_id, String team_domain, String channel_id,
			String channel_name, String user_id, String user_name, String command, String text, String response_url) {
		// TODO Auto-generated method stub
		gdao.addMessageToSlackDB(token, team_id, team_domain, channel_id,
				channel_name, user_id, user_name, command, text, response_url);
		return null;
	}


	public void postPrivateDM(String message_token, String value) {
		// TODO Auto-generated method stub
		String team_id=gdao.getTeam_Id(message_token);
		ArrayList<String> dm_idList =gdao.getDM_Id(value);
		//get the channel_id for FORUM channel from the database depending on the team_ID 
		String bot_access_token=gdao.getBotAccessToken(team_id);
		String message=gdao.getMessage(message_token);
		String anonymity=gdao.getAnonymity(message_token);
		if(anonymity.equalsIgnoreCase(Constants.NAMED_VALUE))
		{
			String user_name=gdao.getUser_Name(message_token);
			message=message+" posted by "+user_name;
		}
		String dm_id=null;
		int size=dm_idList.size();
		int index=0;
		while(index<size)
		{
			dm_id=dm_idList.get(index++);
			postDMToID(bot_access_token, message, dm_id);			
		}
		
	}


	private void postDMToID(String bot_access_token, String message, String dm_id) {
		
		URIBuilder builder = null;
		try {
			builder = new URIBuilder(Constants.postMessageURL);
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		builder.setParameter("token", bot_access_token)
				.setParameter("channel", dm_id)
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
	}


}
