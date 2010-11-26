package main.java.shopsardine.view;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.java.shopsardine.main.SSApplication;

import org.jdesktop.application.ApplicationContext;

public class Searchbar extends JPanel {
	
	ApplicationContext context;

	public JTextField tsearch;
	public JButton bsearch;
	
	public Searchbar() {
		super();
		context = SSApplication.getInstance().getContext();
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		tsearch = new JTextField();
		tsearch.setName("tsearch");
		tsearch.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				SSApplication.mainFrame.showSearch();
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				SSApplication.mainFrame.showKeyboard();
			}
		});
		
		bsearch = new JButton(context.getResourceMap().getString("bsearch.text"));
		bsearch.setName("bsearch");
		
		add(tsearch);
		add(bsearch);
	}
	
}
