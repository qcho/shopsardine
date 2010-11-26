package main.java.shopsardine.main;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Locale;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;

import main.java.shopsardine.controller.Ambassador;
import main.java.shopsardine.model.Category;
import main.java.shopsardine.model.Subcategory;
import main.java.shopsardine.view.MainFrame;
import main.java.shopsardine.view.Sidebar;

import org.jdesktop.application.SingleFrameApplication;

public class SSApplication extends SingleFrameApplication {

	public List<Category> categories;
    public static MainFrame mainFrame;
    
    Ambassador ambassador;
    
    boolean userClickedCategory, userClickedSubcategory;
    int catid, subcatid;

    protected void createMainFrame() {
        mainFrame = new MainFrame();
        
        mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                mainFrame.setVisible(false);
                exit();
            }
        });
        
        getComboBoxThingy(mainFrame.navbar.cats).addMouseListener(new MouseAdapter() {
        	
        	public void mousePressed(MouseEvent e) {
        		userClickedCategory = true;
        	}
        	
        });
        
        mainFrame.navbar.cats.addActionListener(new ActionListener() {
        	
        	public void actionPerformed(ActionEvent e) {
        		
        		if (userClickedCategory) {
        			userClickedCategory = false;
        			
	        		Object selected = mainFrame.navbar.cats.getSelectedItem();

	        		if (selected instanceof Category) {
	        			
	        			catid = ((Category) selected).id;
	        			ambassador.fetchSubcategories(catid);
	        			ambassador.fetchProducts(catid, subcatid = 0);
	        		}
        		}
        	}
        });
        
        getComboBoxThingy(mainFrame.navbar.subcats).addMouseListener(new MouseAdapter() {
        	
        	public void mousePressed(MouseEvent e) {
        		userClickedSubcategory = true;
        	}
        	
        });
        
        mainFrame.navbar.subcats.addActionListener(new ActionListener() {
        	
        	public void actionPerformed(ActionEvent e) {
        		if (userClickedSubcategory) {
        			userClickedSubcategory = false;
		    		Object selected = mainFrame.navbar.subcats.getSelectedItem();
		    		
		    		if (selected instanceof Subcategory) {
		    			subcatid = ((Subcategory) selected).id;
		    			ambassador.fetchProducts(catid, subcatid);
		    			
		    		} else {
		    			if ((selected = mainFrame.navbar.cats.getSelectedItem()) instanceof Category)
		    				ambassador.fetchProducts(catid, subcatid = 0);
		    		}
        		}
        	}
        });
        
        mainFrame.searchbar.bsearch.addActionListener(new ActionListener() {
        	
        	public void actionPerformed(ActionEvent e) {
        		ambassador.fetchSearchResults(mainFrame.searchbar.tsearch.getText());
        	}
        	
        });
        
        mainFrame.sidebar.buttons[Sidebar.CATALOG].addMouseListener(new MouseAdapter() {
        	
        	public void mouseClicked(MouseEvent e) {
        		mainFrame.showCatalog();
        	}
        	
        });
        
        mainFrame.sidebar.buttons[Sidebar.SEARCH].addMouseListener(new MouseAdapter() {
        	
        	public void mouseClicked(MouseEvent e) {
        		mainFrame.showSearch();
        	}
        	
        });
        
        mainFrame.sidebar.buttons[Sidebar.STATS].addMouseListener(new MouseAdapter() {
        	
        	public void mouseClicked(MouseEvent e) {
        		mainFrame.showStats();
        	}
        	
        });
        
        mainFrame.sidebar.buttons[Sidebar.HELP].addMouseListener(new MouseAdapter() {
        	
        	public void mouseClicked(MouseEvent e) {
        		mainFrame.showHelp();
        	}
        	
        });
        
        ambassador = new Ambassador(mainFrame);
        
        catid = 1;
        subcatid = 0;
        
        ambassador.fetchCategories();
        ambassador.fetchSubcategories(catid);
        ambassador.fetchProducts(catid, subcatid);
        ambassador.showProductDetail(28);
        
        mainFrame.setVisible(true);
        
    }

    private static Component getComboBoxThingy(JComboBox box) {  
    	Component ret = null;
    	
    	try {  
    		Field popupInBasicComboBoxUI = BasicComboBoxUI.class.getDeclaredField("popup");  
            popupInBasicComboBoxUI.setAccessible(true);  
            BasicComboPopup popup = (BasicComboPopup) popupInBasicComboBoxUI.get(box.getUI());  
  
            Field scrollerInBasicComboPopup = BasicComboPopup.class.getDeclaredField("scroller");  
            scrollerInBasicComboPopup.setAccessible(true);  
            JScrollPane scroller = (JScrollPane) scrollerInBasicComboPopup.get(popup);  
  
            ret = scroller.getViewport().getView();    
        }  
        catch (Exception e) {  
            e.printStackTrace();
        }
        
        return ret;
    }
    
    public static void main(String[] args) {
        launch(SSApplication.class, args);
    }

	@Override
	protected void startup() {
		getMainFrame().setName("globalFrame");
		createMainFrame();
		show(mainFrame);
	}
}
