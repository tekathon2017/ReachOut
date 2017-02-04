package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.reachout.slackapp.UTIL.Constants;

public class ForumDAO {
	public String getAccessToken(String team_id)
	{
		String access_token=null;
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
		ResultSet rs=null;
		try {
			rs=stat.executeQuery("select access_token from team_details where team_id = '"+team_id+"'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while(rs.next())
			{
				access_token=rs.getString("access_token");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return access_token;
	}
	public String getBotAccessToken(String team_id)
	{
		String bot_access_token=null;
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
		ResultSet rs=null;
		try {
			rs=stat.executeQuery("select bot_access_token from team_details where team_id = '"+team_id+"'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while(rs.next())
			{
				bot_access_token=rs.getString("bot_access_token");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bot_access_token;
	}

	public String getChannel_Id(String team_id)
	{
		String access_token=getAccessToken(team_id);
		String channel_id=null;
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
		ResultSet rs=null;
		try {
			rs=stat.executeQuery("select id from channel_details where access_token = '"+access_token+"' and name='"+Constants.FORUMCHANNELNAME+"'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while(rs.next())
			{
				channel_id=rs.getString("id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return channel_id;
		
	}
	public String addMessageToSlackDB(String token, String team_id, String team_domain, String channel_id,
			String channel_name, String user_id, String user_name, String command, String text, String response_url) {
		// TODO Auto-generated method stub
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
			stat.executeUpdate("insert into forum_message values ('"+token+"','"+team_id+"','"+team_domain+"','"+channel_id+"','"+channel_name+"','"+user_id+"','"+user_name+"','"+command+"','"+text+"','"+response_url+"')");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
		
	}
}
