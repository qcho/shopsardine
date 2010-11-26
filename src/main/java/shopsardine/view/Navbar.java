package main.java.shopsardine.view;

import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jdesktop.application.ApplicationContext;

import main.java.shopsardine.main.SSApplication;
import main.java.shopsardine.model.Category;
import main.java.shopsardine.model.Subcategory;

public class Navbar extends JPanel {

	ApplicationContext context;
	
	public JComboBox cats, subcats;
	public JLabel status;
	
	
	String[] cattext = {"Loading categories..."};
	String[] subcattext = {"Loading subcategories..."};
	
	public Navbar() {
		setName("navbar");
		
		context = SSApplication.getInstance().getContext();
		
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		cats = new JComboBox(cattext);
		cats.setName("catsCombo");
		add(cats);
		
		subcats = new JComboBox(subcattext);
		subcats.setName("subcatsCombo");
		add(subcats);
		
		status = new JLabel();
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
		subcats.addItem(context.getResourceMap().getString("subselect"));
		for (Category cat : categories) {
			subcats.addItem(cat);
		}
		
	}
	
}
