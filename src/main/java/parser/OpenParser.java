package parser;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import connection.DBConnection;

public class OpenParser
{
	private String depCode = "";
	private String depJob = "";
	private String description = "";
	
	DBConnection conn = new DBConnection("localhost", "postgres", "postgres", "test_db");
	

	public OpenParser()
	{
		
	}
	
	public OpenParser(File fileToOpenAndParse)
	{
		conn.initProperties();
		conn.init();

		try
		{
			parseOpenedFile(fileToOpenAndParse);
		}
		catch (ParserConfigurationException e)
		{
			e.printStackTrace();
		}
		catch (SAXException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	
	public void parseOpenedFile(File openedFIle) 
			throws ParserConfigurationException, SAXException, IOException
	{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = factory.newDocumentBuilder();
		Document doc = docBuilder.parse(openedFIle);
		
		String elementName = doc.getDocumentElement().getNodeName();
		System.out.println(elementName);
		
		NodeList nodeList = doc.getElementsByTagName("test_table");
		
		
		for (int i = 0; i < nodeList.getLength(); i++) 
		{
			Element el = (Element)nodeList.item(i);
			
			depCode = el.getElementsByTagName("dep_code").
					item(0).getChildNodes().item(0).getNodeValue();
			depJob = el.getElementsByTagName("dep_job").
					item(0).getChildNodes().item(0).getNodeValue();
			description = el.getElementsByTagName("description").
					item(0).getChildNodes().item(0).getNodeValue();
		}
		
		System.out.println(depCode);
		System.out.println(depJob);
		System.out.println(description);
		
		try
		{
			conn.insertQuery(depCode, depJob, description);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public String getDepCode()
	{
		return depCode;
	}
	
	public String getDepJob()
	{
		return depJob;
	}
	
	public String getDescription()
	{
		return description;
	}
}
