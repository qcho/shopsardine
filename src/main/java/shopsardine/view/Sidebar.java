package main.java.shopsardine.view;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Sidebar extends JPanel {

	public JButton[] buttons;
	public String[] labels = {"catalog", "search", "scan", "stats", "help"};
	
	public Sidebar() {
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		buttons = new JButton[labels.length];
		for (int i = 0; i < labels.length; i++) {
			buttons[i] = new JButton(labels[i]);
			add(buttons[i]);
		}
		
	}
	
}
