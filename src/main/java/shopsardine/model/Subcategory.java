package main.java.shopsardine.model;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class Subcategory extends Category {

	public int parent_id;
	
	public static String getXMLTag() {
		return "subcategory";
	}
	
	public Subcategory(Element element) {
		super(element);
		
		Node node_parent = element.getElementsByTagName("category_id").item(0);
		
		if (node_parent != null)
			parent_id = Integer.parseInt(node_parent.getTextContent());
	}
	
	public static Subcategory fromElement(Element element) {
		return new Subcategory(element);
	}
}
