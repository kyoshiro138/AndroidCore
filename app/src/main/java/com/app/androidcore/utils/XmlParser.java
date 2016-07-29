package com.app.androidcore.utils;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class XmlParser {
    private DocumentBuilder mBuilder;

    public XmlParser() {
        try {
            mBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    public Document parse(String xmlString) {
        Document xmlDocument = null;
        if (mBuilder != null && !xmlString.equals("")) {
            InputSource input = new InputSource();
            input.setCharacterStream(new StringReader(xmlString));

            try {
                xmlDocument = mBuilder.parse(input);
            } catch (SAXException | IOException e) {
                e.printStackTrace();
            }
        }

        return xmlDocument;
    }

    public String getValue(Document xmlDocument, String tagName, String tagClass) {
        if (xmlDocument != null) {
            NodeList nodeList = xmlDocument.getChildNodes();
            return getValue(nodeList, tagName, tagClass);
        } else {
            return "";
        }
    }

    private String getValue(NodeList nodeList, String tagName, String tagClass) {
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeName().equals(tagName) && node.hasAttributes()) {
                for (int j = 0; j < node.getAttributes().getLength(); j++) {
                    if (node.getAttributes().item(j).getNodeName().equals("class")
                            && node.getAttributes().item(j).getNodeValue().equals(tagClass)) {
                        return node.getTextContent();
                    }
                }
            }
            if (node.hasChildNodes()) {
                return getValue(node.getChildNodes(), tagName, tagClass);
            }
        }
        return "";
    }

    private Node getNode(NodeList nodeList, String tagName, String tagClass) {
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeName().equals(tagName) && node.hasAttributes()) {
                for (int j = 0; j < node.getAttributes().getLength(); j++) {
                    if (node.getAttributes().item(j).getNodeName().equals("class")
                            && node.getAttributes().item(j).getNodeValue().equals(tagClass)) {
                        return node;
                    }
                }
            }
            if (node.hasChildNodes()) {
                Node childNode = getNode(node.getChildNodes(), tagName, tagClass);
                if (childNode != null) {
                    return childNode;
                }
            }
        }
        return null;
    }

    private Node getNode(NodeList nodeList, String tagName) {
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeName().equals(tagName)) {
                return node;
            }
            if (node.hasChildNodes()) {
                Node childNode = getNode(node.getChildNodes(), tagName);
                if (childNode != null) {
                    return childNode;
                }
            }
        }
        return null;
    }

    private String getAttributeValue(Node node, String attribute) {
        if (node != null && node.hasAttributes()) {
            for (int i = 0; i < node.getAttributes().getLength(); i++) {
                Node attributeNode = node.getAttributes().item(i);
                if (attributeNode.getNodeName().equals(attribute)) {
                    return attributeNode.getNodeValue();
                }
            }
        }
        return "";
    }
}
