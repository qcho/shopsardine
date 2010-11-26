package main.java.shopsardine.model;

import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class Category {
	
	public int id;
	public String name, code;
	
	public List<Category> subcategories;
	
	public Category(Element element) {

		id = Integer.parseInt(element.getAttribute("id"));
		
		name = element.getElementsByTagName("name").item(0).getTextContent();
		code = element.getElementsByTagName("code").item(0).getTextContent();
		
	}
	
	public static Category fromElement(Element element) {
		return new Category(element);
	}
	
	public void setSubcategories(List<Category> subcategories) {
		this.subcategories = subcategories;
	}
	
	public String toString() {
		return name;
	}
	
}
