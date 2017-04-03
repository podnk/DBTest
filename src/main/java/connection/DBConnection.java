package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBConnection
{
	private String host;
	private String usrName;
	private String pass;
	private String dbName;
	private String url;

	private Properties props = new Properties();
	private Connection conn;

	public DBConnection(String host, String usrName, String pass, String dbName)
	{
		this.host = host;
		this.usrName = usrName;
		this.pass = pass;
		this.dbName = dbName;
	}

	public DBConnection(String url, Properties props)
	{
		this.url = url;
		this.props = props;
	}
	
	public DBConnection()
	{
		
	}

	public void initProperties()
	{
		url = "jdbc:postgresql://" + host + ":5432/" + dbName;
		props.setProperty("url", url);
		props.setProperty("user", usrName);
		props.setProperty("password", pass);
		props.setProperty("dialect", "org.hibernate.dialect.PostgreSQLDialect");
		props.setProperty("characterEncoding", "UTF-8");
		props.setProperty("useUnicode", "true");
	}

	public void init()
	{
		try
		{
			Class.forName("org.postgresql.Driver");

			conn = DriverManager.getConnection(url, usrName, pass);
			
			if (conn != null)
			{
				System.out.println("\nThe connection is established\n");
			}
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	public void closeConnection()
	{
		try
		{
			conn.close();
			System.out.println("\nThe connection is closed\n");
		}
		catch (SQLException e)
		{
			System.out.println("\nError\n");
			e.printStackTrace();
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
		}
		catch (SQLException e)
		{
			e.printStackTrace();
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
		}
		catch (SQLException e)
		{
			System.out.println("\nError\n");
			e.printStackTrace();
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
		}
		catch (Exception e)
		{
			System.out.println("\nError\n");
			e.printStackTrace();
		}
	}
	
}
