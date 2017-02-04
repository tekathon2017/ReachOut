package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.reachout.slackapp.UTIL.Constants;

public class GrievanceDAO {
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
			rs=stat.executeQuery("select id from channel_details where access_token = '"+access_token+"' and name='"+Constants.GRIEVANCESCHANNELNAME+"'");
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
	public String getMessage(String message_token) {
		// TODO Auto-generated method stub
		String message=null;
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
			rs=stat.executeQuery("select text from grievances_message where token = '"+message_token+"'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while(rs.next())
			{
				message=rs.getString("text");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;
	}
	public String getUser_Name(String message_token) {
		// TODO Auto-generated method stub
		String user_name=null;
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
			rs=stat.executeQuery("select user_name from grievances_message where token = '"+message_token+"'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while(rs.next())
			{
				user_name=rs.getString("user_name");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user_name;
	}
	public String getTeam_Id(String message_token) {
		// TODO Auto-generated method stub
		String team_id=null;
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
			rs=stat.executeQuery("select team_id from grievances_message where token = '"+message_token+"'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while(rs.next())
			{
				team_id=rs.getString("team_id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return team_id;
	}
	public ArrayList<String> getDM_Id(String value) {
		// TODO Auto-generated method stub
		ArrayList<String> dmIDList=new ArrayList<String>();
		String dmList=null;
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
			rs=stat.executeQuery("select dmIDList from department where departmentname ='"+value+"'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		try {
			while(rs.next())
			{
				dmList=rs.getString("dmIDList");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String []ListOfID=dmList.split(",");
		int size=ListOfID.length;
		int index=0;
		while(index<size)
			dmIDList.add(ListOfID[index++]);
		return dmIDList;
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
			stat.executeUpdate("insert into grievances_message values ('"+token+"','"+team_id+"','"+team_domain+"','"+channel_id+"','"+channel_name+"','"+user_id+"','"+user_name+"','"+command+"','"+text+"','"+response_url+"','','','')");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
		
	}

	public void addAnonymityValue(String message_token, String value) {
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
		ResultSet rs=null;
		try {
			stat.executeUpdate("update grievances_message set anonymity ='"+value+"' where token='"+message_token+"'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	public void addPrivacyValue(String message_token, String value) {
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
		ResultSet rs=null;
		try {
			stat.executeUpdate("update grievances_message set privacy ='"+value+"' where token='"+message_token+"'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}
	public void addDepartmentValue(String message_token, String value) {
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
		ResultSet rs=null;
		try {
			stat.executeUpdate("update grievances_message set department ='"+value+"' where token='"+message_token+"'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}
	public String getAnonymity(String message_token) {
		// TODO Auto-generated method stub
		String anonymity=null;
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
			rs=stat.executeQuery("select anonymity from grievances_message where token = '"+message_token+"'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while(rs.next())
			{
				anonymity=rs.getString("anonymity");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return anonymity;
	}

}
