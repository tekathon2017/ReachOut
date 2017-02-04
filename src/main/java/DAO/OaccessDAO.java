package DAO;

import com.reachout.slackapp.Model.AuthorizeResponse;
import com.reachout.slackapp.Model.CreateChannelResponse;
import com.reachout.slackapp.Model.Topic;

import java.sql.*;

public class OaccessDAO {
	public static String addTeamDetails(AuthorizeResponse ar)
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Connection myConn=null;
		try {
			myConn=DriverManager.getConnection("jdbc:mysql://localhost:3306/testslack","root","");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Statement stat=null;
		try {
			stat=myConn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			stat.executeUpdate("insert into team_details values ('"+ar.getAccess_token()+"','"+ar.getScope()+"','"+ar.getTeam_id()+"','"+ar.getTeam_name()+"','"+ar.getUser_id()+"','"+ar.getBot().getBot_access_token()+"','"+ar.getBot().getBot_user_id()+"')");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
	}
	public static String addChannelDetails(CreateChannelResponse ccr,String access_token)
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Connection myConn=null;
		try {
			myConn=DriverManager.getConnection("jdbc:mysql://localhost:3306/testslack","root","");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Statement stat=null;
		try {
			stat=myConn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			stat.executeUpdate("insert into channel_details values ('"+ccr.getChannel().getId()+"','"+ccr.getChannel().getIs_archived()+"','"+ccr.getChannel().getIs_channel()+"','"+ccr.getChannel().getIs_member()+"','"+ccr.getChannel().getIs_general()+"','"+ccr.getChannel().getLast_read()+"','"+ccr.getChannel().getLatest()+"','"+ccr.getChannel().getName()+"','"+ccr.getChannel().getUnread_count()+"','"+ccr.getChannel().getUnread_count_display()+"','"+ccr.getChannel().getPurpose().getCreator()+"','"+ccr.getChannel().getPurpose().getLast_set()+"','"+ccr.getChannel().getPurpose().getValue()+"','"+ccr.getChannel().getTopic().getCreator()+"','"+ccr.getChannel().getTopic().getLast_set()+"','"+ccr.getChannel().getTopic().getValue()+"','"+ccr.getChannel().getMembers()+"','"+ccr.getChannel().getCreated()+"','"+ccr.getChannel().getCreator()+"','"+access_token+"')");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
	}
}
