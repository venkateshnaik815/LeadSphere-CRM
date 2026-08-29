package com.leadsphere.crm.patterns;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Animal implements Serializable {

  private String name;
  private Set<Plant> plantsEaten = new HashSet<>();
  private Set<Animal> animalsEaten = new HashSet<>();

  protected static void iterateXmlForAnimalAndPlants(
      NodeList childNodes, Set<Animal> animalsEaten, Set<Plant> plantsEaten) {
    for (int i = 0; i < childNodes.getLength(); i++) {
      Node child = childNodes.item(i);
      if (child.getNodeType() == Node.ELEMENT_NODE) {
        if (child.getNodeName().equals(Animal.class.getSimpleName())) {
          Animal animalEaten = new Animal();
          animalEaten.createObjectFromXml(child);
          animalsEaten.add(animalEaten);
        } else if (child.getNodeName().equals(Plant.class.getSimpleName())) {
          Plant plant = new Plant();
          plant.createObjectFromXml(child);
          plantsEaten.add(plant);
        }
      }
    }
  }

  public Element toXmlElement(Document xmlDoc) {
    Element root = xmlDoc.createElement(Animal.class.getSimpleName());
    root.setAttribute("name", name);
    for (Plant plant : plantsEaten) {
      Element xmlElement = plant.toXmlElement(xmlDoc);
      if (xmlElement != null) {
        root.appendChild(xmlElement);
      }
    }
    for (Animal animal : animalsEaten) {
      Element xmlElement = animal.toXmlElement(xmlDoc);
      if (xmlElement != null) {
        root.appendChild(xmlElement);
      }
    }
    xmlDoc.appendChild(root);
    return (Element) xmlDoc.getFirstChild();
  }

  public void createObjectFromXml(Node node) {
    name = node.getAttributes().getNamedItem("name").getNodeValue();
    NodeList childNodes = node.getChildNodes();
    iterateXmlForAnimalAndPlants(childNodes, animalsEaten, plantsEaten);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("\nAnimal Name = ").append(name);
    if (!animalsEaten.isEmpty()) {
      sb.append("\n\tAnimals Eaten by ").append(name).append(": ");
    }
    for (Animal animal : animalsEaten) {
      sb.append("\n\t\t").append(animal);
    }
    sb.append("\n");
    if (!plantsEaten.isEmpty()) {
      sb.append("\n\tPlants Eaten by ").append(name).append(": ");
    }
    for (Plant plant : plantsEaten) {
      sb.append("\n\t\t").append(plant);
    }
    return sb.toString();
  }
}
