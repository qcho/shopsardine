package main.java.shopsardine.controller;

import java.util.LinkedList;
import java.util.List;

import javax.swing.SwingUtilities;

import main.java.shopsardine.embassy.CatalogRequest;
import main.java.shopsardine.embassy.RequestCallback;
import main.java.shopsardine.model.Category;
import main.java.shopsardine.model.Product;
import main.java.shopsardine.model.Subcategory;
import main.java.shopsardine.tools.ObjectAction;
import main.java.shopsardine.view.MainFrame;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Ambassador {

	/* The ambassador class provides a bridge between the interface and the back-end */
	
	int langid = 1;
	MainFrame fmain;
	
	public Ambassador(MainFrame fmain) {
		this.fmain = fmain;
	}
	
	public void fetchCategories() {
		fmain.navbar.cats.disable();
		new CatalogRequest("GetCategoryList", "language_id=" + langid).make(
        new RequestCallback() {
        	public void handle(Document response) {
        		
        		List<Category> categories = new LinkedList<Category>();
        		
        		NodeList catnodes = response.getElementsByTagName("category");
        		
				for (int i = 0; i < catnodes.getLength(); i++)
					categories.add(new Category((Element) catnodes.item(i)));
				
				SwingUtilities.invokeLater(new ObjectAction<List<Category>>(categories) {
					
					public void run() {
						fmain.navbar.populateCategories(object);
						fmain.navbar.cats.enable();
					}
					
				});
        	}
        });
	}
	
	public void fetchSubcategories(int category_id) {
		fmain.navbar.subcats.disable();
		new CatalogRequest("GetSubcategoryList", "language_id=" + langid + "&category_id=" + category_id).make(
        new RequestCallback() {
        	public void handle(Document response) {
        		
        		List<Subcategory> subcategories = new LinkedList<Subcategory>();
        		
        		NodeList catnodes = response.getElementsByTagName("subcategory");
        		
				for (int i = 0; i < catnodes.getLength(); i++)
					subcategories.add(new Subcategory((Element) catnodes.item(i)));
				
				SwingUtilities.invokeLater(new ObjectAction<List<Subcategory>>(subcategories) {
					
					public void run() {
						fmain.navbar.populateSubcategories(object);
						fmain.navbar.subcats.enable();
					}
					
				});
        	}
        });
	}
	
	public void fetchProducts(int category_id, int subcategory_id) {
		final RequestCallback rcb = new RequestCallback() {
        	public void handle(Document response) {
        		
        		fmain.catalog.clearProducts();
        		NodeList nodelist = response.getElementsByTagName("product");
        		
				for (int i = 0; i < nodelist.getLength(); i++) {
					Product product = new Product((Element) nodelist.item(i));
					fmain.catalog.addProduct(product, true);
					/*SwingUtilities.invokeLater(new ObjectAction<Product>(product) {
						
						public void run() {
							fmain.catalog.addProduct(object, true);
						}
						
					});*/
				}
        	}
        };
        
		if (category_id != 0) {
			if (subcategory_id == 0)
				new CatalogRequest("GetProductListByCategory",
								   "language_id=" + langid + "&category_id=" + category_id).make(rcb);
			else
				new CatalogRequest("GetProductListBySubcategory",
						   "language_id=" + langid + "&category_id=" + category_id + "&subcategory_id=" + subcategory_id).make(rcb);
		}
	}
}
