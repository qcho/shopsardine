package main.java.shopsardine.view;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Sidebar extends JPanel {

	public static final int CATALOG = 0;
	public static final int SEARCH = 1;
	public static final int SCAN = 2;
	public static final int STATS = 3;
	public static final int HELP = 4;
	
	public JButton[] buttons;
	public String[] labels = {"catalog", "search", "scan", "stats", "help"};
	
	public Sidebar() {
		setName("sidebar");
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		buttons = new JButton[labels.length];
		for (int i = 0; i < labels.length; i++) {
			buttons[i] = new JButton(labels[i]);
			buttons[i].setName("sb" + labels[i]);
			add(buttons[i]);
		}
		
	}
	
}
