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

public class SuggestionRepo {
	
	InteractiveMessageRepo imrepo;
	
	
	public String newSuggestionMessage(String token, String team_id, String team_domain, String channel_id,
			String channel_name, String user_id, String user_name, String command, String text, String response_url) {
		if (!text.isEmpty()) {
			
			// hit the business logic layer which will in turn call the dao to
			// insert the message into database and also call the android api nd
			// slack api for posting the message into the channel.
			postToSuggestion(text,user_id,team_id);
			return replyBackToUser(channel_id,response_url);

		} else
			return "Invalid usage of the command.Kindly include the message after the command. /suggestion: <Your Messsage>";
	}


	public String postToSuggestion(String message, String user_id, String team_id) {
		String accessToken=null;
		//get the accessToken from database for this team.
	
		String channel_id =null;
		//get the channel_id for Suggestions channel from the database depending on the team_ID 

		URIBuilder builder = null;
		try {
			builder = new URIBuilder(Constants.postMessageURL);
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		builder.setParameter("token", accessToken)
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
		request.setHeader("Content-type", "application/json;charset=UTF-8");
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
		
		
		//imrepo.postInteractiveMessages(response_url,channel_id,1,"String for team id");
/*		// TODO Auto-generated method stub
		String accessToken=null;
		//get the accessToken from database for this team.
	
		URIBuilder builder = null;
		try {
			builder = new URIBuilder(response_url);
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		builder.setParameter("token", accessToken)
				.setParameter("channel", channel_id)
				.setParameter("text", Constants.successMessagePostedReply);
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost request = null;
		try {
			request = new HttpPost(builder.build());
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// request.setEntity(postingString);
		request.setHeader("Content-type", "application/json;charset=UTF-8");
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
*/
		return "Message successfully posted to Forum channel.";

	}

}
