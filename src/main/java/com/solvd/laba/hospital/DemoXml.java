package com.solvd.laba.hospital;

import com.solvd.laba.hospital.model.person.EmployeePerson;
import com.solvd.laba.hospital.model.person.Position;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class DemoXml {
    private static final Logger LOGGER = LogManager.getLogger(DemoXml.class);

    public static void main(String[] args) {
        File file = new File("src/main/resources/xml/person/employee.xml");

        //DOM
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(file);
            Element element = document.getDocumentElement();
            if (element.getTagName().equals("employee")) {
                EmployeePerson employee = new EmployeePerson();
                employee.setId(Long.parseLong(element.getAttribute("id")));
                NodeList childNodes = element.getChildNodes();
                for (int i = 0; i < childNodes.getLength(); i++) {
                    Node node = childNodes.item(i);
                    switch (node.getNodeName()) {
                        case "name":
                            employee.setName(node.getTextContent());
                            break;
                        case "surname":
                            employee.setSurname(node.getTextContent());
                            break;
                        case "phoneNumber":
                            employee.setPhoneNumber(node.getTextContent());
                            break;
                        case "email":
                            employee.setEmail(node.getTextContent());
                            break;
                        case "degree":
                            employee.setDegree(node.getTextContent());
                            break;
                        case "position":
                            employee.setPosition(Position.fromName(node.getTextContent()));
                            break;
                    }
                }
                LOGGER.info(employee);
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            LOGGER.info(e);
        }


    }
}
