package parser;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import connection.DBConnection;

public class SaveParser extends DBConnection
{
	public SaveParser()
	{
		
	}
	
	public SaveParser(String fileName)
	{
		try
		{
			saveFile(fileName);
		}
		catch (ParserConfigurationException e)
		{
			e.printStackTrace();
		}
	}
	
	public void saveFile(String fileName) throws ParserConfigurationException
	{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = factory.newDocumentBuilder();

		Document doc = docBuilder.newDocument();

		Element accountsEl = doc.createElement("test_table");
		doc.appendChild(accountsEl);

//		Element idEl = doc.createElement("id");
//		accountsEl.appendChild(idEl);
//		Attr attrId = doc.createAttribute("id");
//		attrId.setValue("1");
//		idEl.setAttributeNode(attrId);

		Element depCodeElem = doc.createElement("dep_code");
		depCodeElem.appendChild(doc.createTextNode("depCodeText"));
		accountsEl.appendChild(depCodeElem);


		Element depJobElem = doc.createElement("dep_job");
		depJobElem.appendChild(doc.createTextNode("depJobText"));
		accountsEl.appendChild(depJobElem);


		Element descriptionElem = doc.createElement("description");
		descriptionElem.appendChild(doc.createTextNode("descrText"));
		accountsEl.appendChild(descriptionElem);

		TransformerFactory  transformerFactory = TransformerFactory.newInstance();
		try
		{
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource domSource = new DOMSource(doc);
			StreamResult resultFile = new StreamResult(fileName);
			transformer.transform(domSource, resultFile);
			System.out.println("doc saved");
		}
		catch (TransformerConfigurationException ex)
		{
			ex.printStackTrace();
		}
		catch (TransformerException ex)
		{
			ex.printStackTrace();
		}
	}
}
