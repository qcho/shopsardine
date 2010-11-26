package main.java.shopsardine.view;

import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class Sidebar extends JPanel {

	public static final int CATALOG = 0;
	public static final int SEARCH = 1;
	public static final int SCAN = 2;
	public static final int STATS = 3;
	public static final int HELP = 4;
	
	public JToggleButton[] buttons;
	public ButtonGroup bg = new ButtonGroup();
	public String[] labels = {"catalog", "search", "scan", "stats", "help", "lang"};
	public Sidebar() {
		setName("sidebar");
		setLayout(new GridLayout(0, 1, 10, 10));
		
		buttons = new JToggleButton[labels.length];
		for (int i = 0; i < labels.length; i++) {
			buttons[i] = new JToggleButton();
			buttons[i].setName("sb" + labels[i]);
			add(buttons[i]);
			bg.add(buttons[i]);
		}
		
	}
	
}
