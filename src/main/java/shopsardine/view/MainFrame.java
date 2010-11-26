package main.java.shopsardine.view;

import java.awt.BorderLayout;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import main.java.shopsardine.embassy.CatalogRequest;
import main.java.shopsardine.embassy.RequestCallback;
import main.java.shopsardine.model.Category;
import main.java.shopsardine.model.Product;
import main.java.shopsardine.tools.ObjectAction;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class MainFrame extends JFrame {

	public Navbar navbar;
	public Sidebar sidebar;
	public JPanel content;
	
	public ProductView catalog;
	
	public MainFrame() {
		
		getContentPane().setLayout(new BorderLayout());
		
		initComponents();
		setSize(800, 600);
		
	}
	
	private void initComponents() {
		navbar = new Navbar();
		add(navbar, BorderLayout.BEFORE_FIRST_LINE);
		
		sidebar = new Sidebar();
		add(sidebar, BorderLayout.LINE_END);
		
		content = new JPanel();
		
		catalog = new ProductView();
		
		content.add(catalog);
		
		add(catalog);
		pack();
		
		
	}
	
	/*        categories = new LinkedList<Category>();
        
        new CatalogRequest("GetCategoryList", "language_id=1").make(
        new RequestCallback() {
        	public void handle(Document response) {
        		NodeList cats = response.getElementsByTagName("category");
        		
				for (int i = 0; i < cats.getLength(); i++)
					categories.add(new Category((Element) cats.item(i)));
				
				splashFrame.pbar.setValue(splashFrame.pbar.getValue() + 1);
        	}
        });*/
}
