package com.pleaseignore.killboardAnalyser;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlHandler {
	private DocumentBuilderFactory dbFac;
	private DocumentBuilder dBuild;
	private Document doc;
	private NodeList nList;
	private Node nNode;
	
	public XmlHandler(String urlIn) {
		dbFac = DocumentBuilderFactory.newInstance();
		try {
			dBuild = dbFac.newDocumentBuilder();
			doc = dBuild.parse(urlIn);
			nList = doc.getElementsByTagName(doc.getDocumentElement().getNodeName());
			nNode = nList.item(0);
		} catch (Exception e) {
			System.out.println("OH GOD SOMETHING BAD HAPPENED.");
			System.out.println("Error Location");
			System.out.println("Function: Constructor :tinfoil:");
			System.out.println("Filename: XmlHandler.java");
			System.err.println(e.toString());
			System.exit(0);
		}
	}
	
	public String getProperty(String tag) {
		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			Element eElement = (Element) nNode;
			NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
			return nlList.item(0).getNodeValue();
		}
		return null;
	}
}