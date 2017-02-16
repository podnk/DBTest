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
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Parser 
{
	public void saveFile() throws ParserConfigurationException
	{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = factory.newDocumentBuilder();

		Document document = docBuilder.newDocument();

		Element accountsEl = document.createElement("accounts");
		document.appendChild(accountsEl);

		Element idEl = document.createElement("id");
		accountsEl.appendChild(idEl);
		Attr attrId = document.createAttribute("id");
		attrId.setValue("1");
		idEl.setAttributeNode(attrId);

		Element loginEL = document.createElement("login");
		accountsEl.appendChild(loginEL);


		Element passwordEL = document.createElement("password");
		accountsEl.appendChild(passwordEL);


		Element emailEL = document.createElement("email");
		accountsEl.appendChild(emailEL);

		Element nameEL = document.createElement("name");
		accountsEl.appendChild(nameEL);

		Element surnameEl = document.createElement("surname");
		accountsEl.appendChild(surnameEl);

		TransformerFactory  transformerFactory = TransformerFactory.newInstance();
		try
		{
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource domSource = new DOMSource(document);
			StreamResult resultFile = new StreamResult("accounts.xml");
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
