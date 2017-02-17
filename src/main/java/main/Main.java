package main;

import java.sql.SQLException;
import connection.DBConnection;
import ui.FrameController;

public class Main
{
	public static void main(String[] args) throws SQLException
	{
		new DBConnection("localhost", "postgres", "postgres", "test_db");
		new FrameController();
	}
}
