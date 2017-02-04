package com.reachout.slackapp.Model;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateChannelResponse {
	@Override
	public String toString() {
		return "CreateChannelResponse [ok=" + ok + ", channel=" + channel + "]";
	}
	String ok;
	Channel channel;
	public String getOk() {
		return ok;
	}
	public void setOk(String ok) {
		this.ok = ok;
	}
	public Channel getChannel() {
		return channel;
	}
	public void setChannel(Channel channel) {
		this.channel = channel;
	}
	
}
