package com.reachout.slackapp.Model;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Purpose {
	String value;
    String creator;
    String last_set;
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getLast_set() {
		return last_set;
	}
	public void setLast_set(String last_set) {
		this.last_set = last_set;
	}
	@Override
	public String toString() {
		return "Purpose [value=" + value + ", creator=" + creator + ", last_set=" + last_set + "]";
	}

}
