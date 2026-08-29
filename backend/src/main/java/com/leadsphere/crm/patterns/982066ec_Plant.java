package com.leadsphere.crm.patterns;

import java.io.Serializable;
import java.util.StringJoiner;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Plant implements Serializable {

  private String name;
  private String type;

  public Element toXmlElement(Document xmlDoc) {
    Element root = xmlDoc.createElement(Plant.class.getSimpleName());
    root.setAttribute("name", name);
    root.setAttribute("type", type);
    xmlDoc.appendChild(root);
    return xmlDoc.getDocumentElement();
  }

  public void createObjectFromXml(Node node) {
    NamedNodeMap attributes = node.getAttributes();
    name = attributes.getNamedItem("name").getNodeValue();
    type = attributes.getNamedItem("type").getNodeValue();
  }

  @Override
  public String toString() {
    StringJoiner stringJoiner = new StringJoiner(",");
    stringJoiner.add("Name = " + name);
    stringJoiner.add("Type = " + type);
    return stringJoiner.toString();
  }
}
