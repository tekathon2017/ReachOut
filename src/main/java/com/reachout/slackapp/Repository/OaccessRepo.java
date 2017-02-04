package com.reachout.slackapp.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

import com.reachout.slackapp.Model.AuthorizeResponse;
import com.reachout.slackapp.Model.CreateChannelResponse;
import com.reachout.slackapp.UTIL.Constants;

import DAO.OaccessDAO;

/**
 * @author sarsh
 *
 */
public class OaccessRepo {
	String accessToken;
	OaccessDAO odao=new OaccessDAO();
	/**Method send request to oaccess url and get the accesstoken.
	 * @param code: code received in the get request.
	 * @return accessToken if the request is successful. Else return null.
	 * TODO:	save the team_id, accesstoken etc to database for every team.
	 */
	public String getAccess(String code) {
		URIBuilder builder = null;
		try {
			builder = new URIBuilder(Constants.oaccessURL);
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		builder.setParameter("client_id", Constants.clientID).setParameter("client_secret", Constants.clientSecret)
				.setParameter("code", code).setParameter("redirect_uri", Constants.redirect_uri);

		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = null;
		try {
			request = new HttpGet(builder.build());
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

		System.out.println(response.getStatusLine());
		if(response.getStatusLine().getStatusCode()==200)
		{
/*			BufferedReader rd = null;
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
			System.out.println(result);
	*/
			org.codehaus.jackson.map.ObjectMapper mapper = new org.codehaus.jackson.map.ObjectMapper();
			AuthorizeResponse authorizeResponse = null;
	
			try {
				authorizeResponse = mapper.readValue(response.getEntity().getContent(), AuthorizeResponse.class);
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
	
			System.out.println(authorizeResponse.getAccess_token() + authorizeResponse.getScope());
			odao.addTeamDetails(authorizeResponse);
			accessToken = authorizeResponse.getAccess_token();
			return accessToken;
		}	
		else
		{
			return null;
		}
	}

	/**Method to create the three groups: forum,suggestions,grievances. 
	 * Calls corresponding methods which send request for group creation.
	 * 
	 * @param accessToken: accessToken recieved by sending request to oaccessURL.
	 * @return
	 * TODO:	save the channel_id and other details to database for every team.
	 */
	public String createChannels(String accessToken) {

		String forumGroupCreationStatus = createForumChannel(accessToken);
		String suggestionsGroupCreationStatus = createSuggestionsChannel(accessToken);
		String grievancesGroupCreationStatus = createGrievancesChannel(accessToken);
		return "success";
	}

	/**Method to send request to the groupCreationURl to create FORUM group.
	 * @param accessToken: accessToken recieved by sending request to oaccessURL.
	 * @return
	 */
	public String createForumChannel(String accessToken) {
		URIBuilder createChannelBuilder = null;
		try {
			createChannelBuilder = new URIBuilder(Constants.createGroupURL);
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		createChannelBuilder.setParameter("token", accessToken).setParameter("name", Constants.FORUMCHANNELNAME);

		HttpClient createChannelClient  = HttpClientBuilder.create().build();
		HttpGet createChannelrequest = null;
		try {
			createChannelrequest = new HttpGet(createChannelBuilder.build());
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		HttpResponse createChannelResponse = null;
		try {
			createChannelResponse = createChannelClient.execute(createChannelrequest);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(createChannelResponse.getStatusLine());

		BufferedReader createChannelrd = null;
		try {
			createChannelrd = new BufferedReader(new InputStreamReader(createChannelResponse.getEntity().getContent()));
		} catch (UnsupportedOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		StringBuffer createChannelResult = new StringBuffer();
		String createChannelLine = "";
		try {
			while ((createChannelLine = createChannelrd.readLine()) != null) {
				createChannelResult.append(createChannelLine);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(createChannelResult);

		org.codehaus.jackson.map.ObjectMapper createChannelMapper = new org.codehaus.jackson.map.ObjectMapper();
		CreateChannelResponse ccr = null;

		try {
			ccr = createChannelMapper.readValue(createChannelResult.toString(), CreateChannelResponse.class);
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
		odao.addChannelDetails(ccr,accessToken);
		return "success";
	}

	/**Method to send request to the groupCreationURl to create SUGGESTIONS group.
	 * @param accessToken: accessToken received by sending request to oaccessURL.
	 * @return
	 */
	public String createSuggestionsChannel(String accessToken) {
		URIBuilder createChannelBuilder1 = null;
		try {
			createChannelBuilder1 = new URIBuilder(Constants.createGroupURL);
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		createChannelBuilder1.setParameter("token", accessToken).setParameter("name", Constants.SUGGESTIONSCHANNELNAME);
		HttpClient createChannelClient1 = HttpClientBuilder.create().build();
		HttpGet createChannelRequest1 = null;
		try {
			createChannelRequest1 = new HttpGet(createChannelBuilder1.build());
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		HttpResponse createChannelResponse1 = null;
		try {
			createChannelResponse1 = createChannelClient1.execute(createChannelRequest1);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(createChannelResponse1.getStatusLine());
		BufferedReader createChannelrd1 = null;
		try {
			createChannelrd1 = new BufferedReader(new InputStreamReader(createChannelResponse1.getEntity().getContent()));
		} catch (UnsupportedOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		StringBuffer createChannelResult1 = new StringBuffer();
		String createChannelLine1 = "";
		try {
			while ((createChannelLine1 = createChannelrd1.readLine()) != null) {
				createChannelResult1.append(createChannelLine1);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(createChannelResult1);
		org.codehaus.jackson.map.ObjectMapper createChannelMapper1 = new org.codehaus.jackson.map.ObjectMapper();
		CreateChannelResponse ccr1 = null;
		try {
			ccr1 = createChannelMapper1.readValue(createChannelResult1.toString(),
					CreateChannelResponse.class);
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
		odao.addChannelDetails(ccr1,accessToken);
		return "success";
	}

	/**Method to send request to the groupCreationURl to create GRIEVANCES group.
	 * @param accessToken: accessToken received by sending request to oaccessURL.
	 * @return
	 */
	public String createGrievancesChannel(String accessToken) {
		URIBuilder createChannelBuilder2 = null;
		try {
			createChannelBuilder2 = new URIBuilder(Constants.createGroupURL);
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		createChannelBuilder2.setParameter("token", accessToken).setParameter("name", Constants.GRIEVANCESCHANNELNAME);

		HttpClient createChannelClient2 = HttpClientBuilder.create().build();
		HttpGet createChannelRequest2 = null;
		try {
			createChannelRequest2 = new HttpGet(createChannelBuilder2.build());
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		HttpResponse createChannelResponse2 = null;
		try {
			createChannelResponse2 = createChannelClient2.execute(createChannelRequest2);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(createChannelResponse2.getStatusLine());
		BufferedReader createChannelrd2 = null;
		try {
			createChannelrd2 = new BufferedReader(new InputStreamReader(createChannelResponse2.getEntity().getContent()));
		} catch (UnsupportedOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		StringBuffer createChannelResult2 = new StringBuffer();
		String createChannelLine2 = "";
		try {
			while ((createChannelLine2 = createChannelrd2.readLine()) != null) {
				createChannelResult2.append(createChannelLine2);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(createChannelResult2);
		org.codehaus.jackson.map.ObjectMapper createChannelMapper2 = new org.codehaus.jackson.map.ObjectMapper();
		CreateChannelResponse ccr2 = null;
		try {
			ccr2 = createChannelMapper2.readValue(createChannelResult2.toString(),
					CreateChannelResponse.class);
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
		odao.addChannelDetails(ccr2,accessToken);
		return "success";
	}
}
