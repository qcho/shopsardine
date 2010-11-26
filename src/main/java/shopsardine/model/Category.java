package main.java.shopsardine.model;

import java.util.List;

import org.w3c.dom.Element;

public class Category {

	public int id;
	public String name, code;
	
	public List<Category> subcategories;
	
	public Category(Element element) {
		
		id = Integer.parseInt(element.getAttribute("id"));
		name = element.getElementsByTagName("name").item(0).getTextContent();
		code = element.getElementsByTagName("code").item(0).getTextContent();
		
	}
	
	public void setSubcategories(Element element) {
		
	}
	
	public String toString() {
		return "[Category '" + name + "']";
	}
	
}
