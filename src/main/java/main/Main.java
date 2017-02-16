package main;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import connection.DBConnection;

public class Main
{
	public static void main(String[] args) 
			throws ParserConfigurationException, SAXException, IOException, 
				SQLException
	{
		DBConnection conn = new DBConnection("localhost", "postgres", "postgres", "test_db");
		conn.initProperties();
		conn.init();
		
		conn.insertQuery("пёс", "конь", "овца");

		ResultSet rs = conn.query("select * from test_table");
		
		while(rs.next())
		{
			System.out.println(rs.getString(2));
			System.out.println(rs.getString(3));
			System.out.println(rs.getString(4));
		}
		
		conn.closeConnection();
		
//		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//		DocumentBuilder docBuilder = factory.newDocumentBuilder();
//		Document doc = docBuilder.parse(new File("accounts.xml"));
//		
//		String elementName = doc.getDocumentElement().getNodeName();
//		System.out.println(elementName);
//		
//		NodeList nodeList = doc.getElementsByTagName("accounts");
//		
//		String login = "";
//		String password = "";
//		
//		for (int i = 0; i < nodeList.getLength(); i++) 
//		{
//			Element el = (Element)nodeList.item(i);
//			login = el.getElementsByTagName("login").item(0).getChildNodes().item(0).getNodeValue();
//			password = el.getElementsByTagName("password").item(0).getChildNodes().item(0).getNodeValue();
//		}
//		
//		System.out.println(login);
//		System.out.println(password);
	}
}
