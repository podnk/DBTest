package services;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.apache.log4j.Logger;

public class PropertiesClass
{
	private static final Logger log = Logger.getLogger(PropertiesClass.class);
	
	public static String username;
	public static String password;
	public static String dialect;
	public static String driver;
	public static String url;
	
	Properties prop = new Properties();
	FileInputStream fis;
	
	public void initProperties() throws FileNotFoundException, IOException
	{
		try
		{
			fis = new FileInputStream("src/main/resources/java.properties");
			prop.load(fis);
		}
		catch (Exception e)
		{
			log.error(e);
		}
		
		username = prop.getProperty("username");
		password = prop.getProperty("password");
		dialect = prop.getProperty("dialect");
		driver = prop.getProperty("driver");
		url = prop.getProperty("url");
		
		log.info("Properties initialized");
	}
}
