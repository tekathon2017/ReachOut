package com.reachout.slackapp.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.eclipse.persistence.annotations.CascadeOnDelete;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.reachout.slackapp.Model.Actions;
import com.reachout.slackapp.Model.Attachments;
import com.reachout.slackapp.Model.Confirm;
import com.reachout.slackapp.Model.Message;
import com.reachout.slackapp.Model.Payload;
import com.reachout.slackapp.UTIL.Constants;

import DAO.GrievanceDAO;

public class InteractiveMessageRepo {
	GrievanceDAO gdao=new GrievanceDAO();
	GrievanceRepo grepo=new GrievanceRepo();
	public String postInteractiveMessages(String url,String channel_id,String team_id,String message_token)
	{
		String token=gdao.getAccessToken(team_id);
		List<Attachments> firstMessage=createFirstMessage(message_token);
		postFirstInteractiveMesssage(url,firstMessage);
		//postFirstInteractiveMesssage(Constants.postMessageURL,firstMessage,channel_id,token);
/*		List<Attachments> secondMessage=createSecondMessage(channel_id);
		postSecondInteractiveMesssage(url,secondMessage,"C3Z8W5YEN");
		List<Attachments> thirdMessage=createThirdMessage(channel_id);
		postThirdInteractiveMesssage(url,thirdMessage,"C3Z8W5YEN");
*/		
		return "";
	}
	

	public List<Attachments> createFirstMessage(String token)
	{
		Attachments a=new Attachments();
		Actions a1=new Actions();
		Actions a2=new Actions();
		Confirm c1=new Confirm();
		a1.setName(Constants.NAMED_NAME);
		a1.setText(Constants.NAMED_TEXT);
		a1.setType(Constants.NAMED_TYPE);
		a1.setValue(Constants.NAMED_VALUE);
		a1.setConfirm(null);
		a1.setStyle(null);
		a2.setName(Constants.ANONYMOUS_NAME);
		a2.setText(Constants.ANONYMOUS_TEXT);
		a2.setType(Constants.ANONYMOUS_TYPE);
		a2.setValue(Constants.ANONYMOUS_VALUE);
		a2.setConfirm(null);
		a2.setStyle(null);
		List<Actions> actionList=new ArrayList<Actions>();
		actionList.add(a1);
		actionList.add(a2);
		
		a.setActions(actionList);
		a.setCallback_id(Constants.ANONYMITY_CALLBACK_ID+":"+token);
		a.setColor("#3AA3E3");
		a.setFallback(Constants.ANONYMITY_FALLBACK_MSG);
		a.setTitle(Constants.ANONYMITY_TITLE);
		a.setText(Constants.ANONYMITY_TEXT);
		a.setAttachment_type(Constants.ANONYMITY_DEFAULT_ATTACHMENT_TYPE);
		
		List<Attachments> attachementList=new ArrayList<Attachments>();
		attachementList.add(a);
		return attachementList;
	}
	
	public List<Attachments> createSecondMessage(String token)
	{
		Attachments a=new Attachments();
		Actions a1=new Actions();
		Actions a2=new Actions();
		Confirm c1=new Confirm();
		a1.setName(Constants.PUBLIC_NAME);
		a1.setText(Constants.PUBLIC_TEXT);
		a1.setType(Constants.PUBLIC_TYPE);
		a1.setValue(Constants.PUBLIC_VALUE);
		a1.setConfirm(null);
		a1.setStyle(null);
		a2.setName(Constants.PRIVATE_NAME);
		a2.setText(Constants.PRIVATE_TEXT);
		a2.setType(Constants.PRIVATE_TYPE);
		a2.setValue(Constants.PRIVATE_VALUE);
		a2.setConfirm(null);
		a2.setStyle(null);
		List<Actions> actionList=new ArrayList<Actions>();
		actionList.add(a1);
		actionList.add(a2);
		
		a.setActions(actionList);
		a.setCallback_id(Constants.PRIVACY_CALLBACK_ID+":"+token);
		a.setColor("#3AA3E3");
		a.setFallback(Constants.PRIVACY_FALLBACK_MSG);
		a.setTitle(Constants.PRIVACY_TITLE);
		a.setText(Constants.PRIVACY_TEXT);
		a.setAttachment_type(Constants.PRIVACY_DEFAULT_ATTACHMENT_TYPE);
		
		List<Attachments> attachementList=new ArrayList<Attachments>();
		attachementList.add(a);
		return attachementList;
	}
	
	public List<Attachments> createThirdMessage(String token)
	{
		Attachments a=new Attachments();
		Actions a1=new Actions();
		Actions a2=new Actions();
		Actions a3=new Actions();
		Actions a4=new Actions();
		Actions a5=new Actions();
		Actions a6=new Actions();
		Actions a7=new Actions();
		Confirm c1=new Confirm();
		a1.setName(Constants.TEK_ADMIN_NAME);
		a1.setText(Constants.TEK_ADMIN_TEXT);
		a1.setType(Constants.TEK_ADMIN_TYPE);
		a1.setValue(Constants.TEK_ADMIN_VALUE);
		a1.setConfirm(null);
		a1.setStyle(null);
		a2.setName(Constants.VENKAT_ORG_NAME);
		a2.setText(Constants.VENKAT_ORG_TEXT);
		a2.setType(Constants.VENKAT_ORG_TYPE);
		a2.setValue(Constants.VENKAT_ORG_VALUE);
		a2.setConfirm(null);
		a2.setStyle(null);
		a3.setName(Constants.UMESH_ORG_NAME);
		a3.setText(Constants.UMESH_ORG_TEXT);
		a3.setType(Constants.UMESH_ORG_TYPE);
		a3.setValue(Constants.UMESH_ORG_VALUE);
		a3.setConfirm(null);
		a3.setStyle(null);
		a4.setName(Constants.RAJAN_ORG_NAME);
		a4.setText(Constants.RAJAN_ORG_TEXT);
		a4.setType(Constants.RAJAN_ORG_TYPE);
		a4.setValue(Constants.RAJAN_ORG_VALUE);
		a4.setConfirm(null);
		a4.setStyle(null);
		a5.setName(Constants.AMULYA_ORG_NAME);
		a5.setText(Constants.AMULYA_ORG_TEXT);
		a5.setType(Constants.AMULYA_ORG_TYPE);
		a5.setValue(Constants.AMULYA_ORG_VALUE);
		a5.setConfirm(null);
		a5.setStyle(null);
		a6.setName(Constants.HR_DEPARTMENT_ORG_NAME);
		a6.setText(Constants.HR_DEPARTMENT_ORG_TEXT);
		a6.setType(Constants.HR_DEPARTMENT_ORG_TYPE);
		a6.setValue(Constants.HR_DEPARTMENT_ORG_VALUE);
		a6.setConfirm(null);
		a6.setStyle(null);
		a7.setName(Constants.FINANCE_DEPARTMENT_ORG_NAME);
		a7.setText(Constants.FINANCE_DEPARTMENT_ORG_TEXT);
		a7.setType(Constants.FINANCE_DEPARTMENT_ORG_TYPE);
		a7.setValue(Constants.FINANCE_DEPARTMENT_ORG_VALUE);
		a7.setConfirm(null);
		a7.setStyle(null);
		List<Actions> actionList=new ArrayList<Actions>();
		actionList.add(a1);
		actionList.add(a2);
		actionList.add(a3);
		actionList.add(a4);
		actionList.add(a5);
		actionList.add(a6);
		actionList.add(a7);
		
		a.setActions(actionList);
		a.setCallback_id(Constants.DEPARTMENT_CALLBACK_ID+":"+token);
		a.setColor("#3AA3E3");
		a.setFallback(Constants.DEPARTMENT_FALLBACK_MSG);
		a.setTitle(Constants.DEPARTMENT_TITLE);
		a.setText(Constants.DEPARTMENT_TEXT);
		a.setAttachment_type(Constants.DEPARTMENT_DEFAULT_ATTACHMENT_TYPE);
		
		List<Attachments> attachementList=new ArrayList<Attachments>();
		attachementList.add(a);

		return attachementList;
	}

	private void postFirstInteractiveMesssage(String url, List<Attachments> firstMessage) {
		// TODO Auto-generated method stub
		Gson gson = new Gson();
		URIBuilder builder = null;
		StringEntity params=null;
		Message m1=new Message();
		m1.setAttachments(firstMessage);
		m1.setText(Constants.FIRST_MESSAGE_TEXT);
		try {
			params =new StringEntity(gson.toJson(m1));
		} catch (UnsupportedEncodingException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		try {
			builder = new URIBuilder(url);
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
		
		builder.setParameter("text", "hi");
		try {
			System.out.println(builder.build().toString());
		} catch (URISyntaxException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost request = null;
		try {
			request = new HttpPost(builder.build());
			request.setHeader("Content-type", "application/json");
			request.setEntity(params);
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
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
		System.out.println("result from first interactive message is: " + result);	
		
	}

	private void postSecondInteractiveMesssage(String url, List<Attachments> secondMessage) {
		// TODO Auto-generated method stub
		Gson gson = new Gson();
		URIBuilder builder = null;
		StringEntity params=null;
		Message m1=new Message();
		m1.setAttachments(secondMessage);
		m1.setText(Constants.SECOND_MESSAGE_TEXT);
		
		try {
			params =new StringEntity(gson.toJson(m1));
		} catch (UnsupportedEncodingException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		try {
			builder = new URIBuilder(url);
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
		
		builder.setParameter("text", "hi");
		try {
			System.out.println(builder.build().toString());
		} catch (URISyntaxException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost request = null;
		try {
			request = new HttpPost(builder.build());
			request.setHeader("Content-type", "application/json");
			request.setEntity(params);
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
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
		System.out.println("result from second interactive message is: " + result);	
		
	}
	
	private void postThirdInteractiveMesssage(String url, List<Attachments> thirdMessage) {
		// TODO Auto-generated method stub
		Gson gson = new Gson();
		URIBuilder builder = null;
		StringEntity params=null;
		Message m1=new Message();
		m1.setAttachments(thirdMessage);
		m1.setText(Constants.THIRD_MESSAGE_TEXT);
		
		try {
			params =new StringEntity(gson.toJson(m1));
		} catch (UnsupportedEncodingException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		try {
			builder = new URIBuilder(url);
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
		
		builder.setParameter("text", "hi");
		try {
			System.out.println(builder.build().toString());
		} catch (URISyntaxException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost request = null;
		try {
			request = new HttpPost(builder.build());
			request.setHeader("Content-type", "application/json");
			request.setEntity(params);
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
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
		System.out.println("result from third interactive message is: " + result);	
		
	}


	public String processInteractiveMessage(String payload) {
		System.out.println("Interactive message received with parameters:"+payload);
		payload=payload.replace("payload=", "");
		Payload pl=new Payload();
		String result =null;
		
		try {
			result = java.net.URLDecoder.decode(payload, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		org.codehaus.jackson.map.ObjectMapper mapper = new org.codehaus.jackson.map.ObjectMapper();

		try {
			pl = mapper.readValue(result, Payload.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String callback_id=pl.getCallback_id();
		String []callback_id_array = callback_id.split(":");
		String token = callback_id_array[1];
		String value=pl.getActions().get(0).getValue();
		if(callback_id.contains(Constants.ANONYMITY_CALLBACK_ID))
		{
			gdao.addAnonymityValue(token,value);
			List<Attachments> secondMessage=createSecondMessage(token);
			postSecondInteractiveMesssage(pl.getResponse_url(), secondMessage);
		}
		else if(callback_id.contains(Constants.PRIVACY_CALLBACK_ID))
		{
			gdao.addPrivacyValue(token,value);

			if(pl.getActions().get(0).getValue().equalsIgnoreCase("Private"))
			{
				List<Attachments> thirdMessage=createThirdMessage(token);
				postThirdInteractiveMesssage(pl.getResponse_url(), thirdMessage);							
			}
			else
			{
				grepo.postToGrievance(token);
				//write logic to post the message to grievance group.				
			}
		}
		else if(callback_id.contains(Constants.DEPARTMENT_CALLBACK_ID))
		{
			gdao.addDepartmentValue(token,value);
			grepo.postPrivateDM(token,value);
			//write logic to post the message to specific person.
		}
//		System.out.println("Interactive message received with parameters:callback_id"+callback_id+"action_ts"+action_ts+"message_ts"+message_ts+"attachment_id"+attachment_id+"token"+token+"response_url"+response_url);
		return "";
	}	


}
