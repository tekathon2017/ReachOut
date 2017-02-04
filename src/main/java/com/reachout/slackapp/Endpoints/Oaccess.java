/**
 * 
 */
package com.reachout.slackapp.Endpoints;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

import com.reachout.slackapp.Repository.OaccessRepo;


/**
 * @author sarsh
 *
 */

@Path("/oaccess")
public class Oaccess {
	OaccessRepo orepo=new OaccessRepo();
	
	/** This endpoint is the redirect URL sent in the oauth request.
	 * After processing the oauth request Slack redirects to this URL with accessCode. 
	 * This accessCode is used to generate the accessToken by sending a HTTP request to oaccessURl
	 * Using the accessToken received, three channels are created.
	 * @param code
	 * @return
	 */
	@GET
	public String accessGET(@QueryParam("code") String code) {
		String accessToken=orepo.getAccess(code);
		if(!StringUtils.isEmpty(accessToken))
		{
			String status=orepo.createChannels(accessToken);			
			if(!StringUtils.isEmpty(status))
				return "App Successfully installed and channels created.";
		}		
		return "Access grant failiure";
	}

}
