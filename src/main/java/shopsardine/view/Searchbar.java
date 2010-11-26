package main.java.shopsardine.view;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Searchbar extends JPanel {

	public JTextField tsearch;
	public JButton bsearch;
	
	public Searchbar() {
		super();
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		tsearch = new JTextField();
		tsearch.setName("tsearch");
		
		bsearch = new JButton("Search");
		bsearch.setName("bsearch");
		
		add(tsearch);
		add(bsearch);
	}
	
}
