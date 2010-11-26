package main.java.shopsardine.view;

import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.java.shopsardine.model.Category;
import main.java.shopsardine.model.Subcategory;

public class Navbar extends JPanel {

	public JComboBox cats, subcats;
	public JLabel status;
	
	String[] cattext = {"Loading categories..."};
	String[] subcattext = {"Loading subcategories"};
	
	public Navbar() {
		setName("navbar");		
		
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		cats = new JComboBox(cattext);
		cats.setName("catsCombo");
		add(cats);
		
		subcats = new JComboBox(subcattext);
		subcats.setName("subcatsCombo");
		add(subcats);
		
		status = new JLabel("Welcome to Shopsardine!");
		status.setName("statusLabel");
		add(status);

	}
	
	public void populateCategories(List<Category> categories) {
		cats.removeAllItems();
		for (Category cat : categories) {
			cats.addItem(cat);
		}
		
	}
	
	public void populateSubcategories(List<Subcategory> categories) {
		subcats.removeAllItems();
		subcats.addItem("(No subcategory selected)");
		for (Category cat : categories) {
			subcats.addItem(cat);
		}
		
	}
	
}
