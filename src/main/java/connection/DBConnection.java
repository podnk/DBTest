package connection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

//import java.util.Properties;
import services.PropertiesClass;
import ui.MainFrame;

public class DBConnection  
{
	private static final Logger log = Logger.getLogger(MainFrame.class);
	
	static PropertiesClass propClass = new PropertiesClass();
	
	private Connection conn;
	
	public DBConnection()
	{
		try
		{
			propClass.initProperties();
		}
		catch (IOException e)
		{
			e.printStackTrace();
			log.error(e);
		}
	}

	public void init()
	{
		try
		{
			Class.forName("org.postgresql.Driver");

			conn = DriverManager.getConnection(PropertiesClass.url, PropertiesClass.username, PropertiesClass.password);
			
			if (conn != null)
			{
				System.out.println("The connection is established");
				log.info("The connection is established");
			}
		}
		catch (ClassNotFoundException e)
		{
			JOptionPane.showMessageDialog(null, "Error9");
			log.error(e);
		}
		catch (SQLException e)
		{
			JOptionPane.showMessageDialog(null, "Error10");
			log.error(e);
		}
	}

	public void closeConnection()
	{
		try
		{
			conn.close();
			System.out.println("\nThe connection is closed\n");
			log.info("Connection closed");
		}
		catch (SQLException e)
		{
			JOptionPane.showMessageDialog(null, "Error8");
			log.error(e);
		}
	}

	public ResultSet query(String query)
	{
		ResultSet result = null;

		try
		{
			Statement statement = conn.createStatement();
			result = statement.executeQuery(query);

			System.out.println("\nResult set has obtained\n");
			log.info("Result set has obtained");
		}
		catch (SQLException e)
		{
			JOptionPane.showMessageDialog(null, "Error7");
			log.error(e);
		}
		return result;
	}

	public void updateQuery(String query)
	{
		try
		{
			Statement statement = conn.createStatement();
			statement.executeUpdate(query);

			System.out.println("\nTable has updated\n");
			log.info("Table has updated");
		}
		catch (SQLException e)
		{
			JOptionPane.showMessageDialog(null, "Error6");
			log.error(e);
		}
	}
	
	public void insertQuery(String val1, String val2, String val3)
	{
		try
		{
			Statement statement = conn.createStatement();
			statement.executeUpdate("insert into test_table(dep_code, dep_job, "
					+ "description) "
					+ "values('"+val1+"', '"+val2+"', '"+val3+"')");

			System.out.println("\nTable has updated\n");
			log.info("Values inserted");
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "Error12");
			log.error(e);
		}
	}
	
}
