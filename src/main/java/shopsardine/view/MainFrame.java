package main.java.shopsardine.view;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

public class MainFrame extends JFrame {

	public Navbar navbar;
	public Searchbar searchbar;
	public Sidebar sidebar;
	public JPanel content;
	public JTable stats;
	public JLabel help;
	
	public ProductView catalog, search;
	
	Component current_content, current_top;
	
	public MainFrame() {
		setName("mainFrame");
		getContentPane().setLayout(new BorderLayout());
		
		initComponents();
		setSize(880, 600);
		setMaximumSize(getSize());
		setMinimumSize(getSize());
		
	}
	
	private void initComponents() {
		navbar = new Navbar();
		add(current_top = navbar, BorderLayout.BEFORE_FIRST_LINE);
		
		sidebar = new Sidebar();
		add(sidebar, BorderLayout.LINE_END);

		catalog = new ProductView();
		add(current_content = catalog);
		
		search = new ProductView();
		searchbar = new Searchbar();
		
		stats = new JTable(); // No se que hacer con esto, que vamos a guardar?
		stats.setName("stats");
		
		help = new JLabel();
		help.setName("help");

		pack();
		
		
	}
	
	public void showSearch() {
		if (current_content != search) {
			remove(current_top);
			remove(current_content);
		
			add(current_top = searchbar, BorderLayout.BEFORE_FIRST_LINE);
			add(current_content = search);
		
			pack();
			setSize(880, 600);
			repaint();
		}
	}
	
	public void showCatalog() {
		if (current_content != catalog) {
			remove(current_top);
			remove(current_content);
		
			add(current_top = navbar, BorderLayout.BEFORE_FIRST_LINE);
			add(current_content = catalog);
		
			pack();
			setSize(880, 600);
			
			repaint();
		}
	}
	
	public void showStats() {
		if (current_content != stats) {
			remove(current_top);
			remove(current_content);
		
			add(current_top = new JLabel(), BorderLayout.BEFORE_FIRST_LINE);
			add(current_content = stats);
		
			pack();
			setSize(880, 600);
			
			repaint();
		}
	}
	
	public void showHelp() {
		if (current_content != help) {
			remove(current_top);
			remove(current_content);
		
			add(current_top = new JLabel(), BorderLayout.BEFORE_FIRST_LINE);
			add(current_content = help);
		
			pack();
			setSize(880, 600);
			
			repaint();
		}
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
