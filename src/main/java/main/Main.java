package main;

import java.sql.ResultSet;
import java.sql.SQLException;

import connection.DBConnection;
import ui.MainFrame;

public class Main
{
	@SuppressWarnings("unused")
	public static void main(String[] args) 
			throws InterruptedException, SQLException
	{
		DBConnection dbConn = new DBConnection(
				"localhost", "postgres", "postgres", "postgres");
		dbConn.initProperties();
		dbConn.init();
		
		ResultSet rs = dbConn.query("select * from accounts");
		
		dbConn.closeConnection();
		
		while(rs.next())
		{
			System.out.println("id: "+rs.getString(1));
			System.out.println("login: "+rs.getString(2));
			System.out.println("password: "+rs.getString(3));
			System.out.println("email: "+rs.getString(4));
			System.out.println("name: "+rs.getString(5));
			System.out.println("surname: "+rs.getString(6));
		}
		
		MainFrame mainFrame = new  MainFrame();
	}
}
