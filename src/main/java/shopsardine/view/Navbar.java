package main.java.shopsardine.view;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Navbar extends JPanel {

	JComboBox cats, subcats;
	JLabel status;
	
	String[] cattext = {"Loading categories..."};
	String[] subcattext = {"Loading subcategories"};
	
	public Navbar() {
		
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		cats = new JComboBox(cattext);
		add(cats);
		
		subcats = new JComboBox(subcattext);
		add(subcats);
		
		status = new JLabel("Welcome to Shopsardine!");
		add(status);

	}
	
}
