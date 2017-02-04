package com.reachout.slackapp.Endpoints;

import javax.ws.rs.Path;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.json.JSONException;
import org.json.JSONObject;

import com.reachout.slackapp.Model.Actions;
import com.reachout.slackapp.Model.Channel;
import com.reachout.slackapp.Model.Message;
import com.reachout.slackapp.Model.Payload;
import com.reachout.slackapp.Model.Team;
import com.reachout.slackapp.Model.User;
import com.reachout.slackapp.Repository.InteractiveMessageRepo;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.ws.rs.*;
@Path("/interactivemessage")
public class InteractiveMessage {
	InteractiveMessageRepo imrepo=new InteractiveMessageRepo();
	@POST
	public void newInteractiveMessage(String payload)
	{
		imrepo.processInteractiveMessage(payload);
	}

}
