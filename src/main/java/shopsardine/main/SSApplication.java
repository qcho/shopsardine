package main.java.shopsardine.main;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JFrame;

import main.java.shopsardine.model.Category;
import main.java.shopsardine.view.MainFrame;

import org.jdesktop.application.Application;

public class SSApplication extends Application {

	public List<Category> categories;
    public MainFrame mainFrame;

    @Override
    protected void startup() {
        mainFrame = new MainFrame();
        mainFrame.setName("mainFrame");
        
        mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                mainFrame.setVisible(false);
                exit();
            }
        });
        
        mainFrame.setVisible(true);
        
    }

    public static void main(String[] args) {
        Application.launch(SSApplication.class, args);
    }

	
}
