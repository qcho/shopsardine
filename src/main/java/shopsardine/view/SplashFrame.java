package main.java.shopsardine.view;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

public class SplashFrame extends JFrame {

	public Image logo;
	public JLabel logolabel;
	public JProgressBar pbar;
	
	public SplashFrame() {
		setName("splashFrame");

		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		
		logolabel = new JLabel(
				    new ImageIcon(
				    Toolkit.getDefaultToolkit().createImage("/home/santiago/008.jpg").
				    getScaledInstance(200, 200, Image.SCALE_SMOOTH)));

		logolabel.setAlignmentX(CENTER_ALIGNMENT);
		add(logolabel);
		
		pbar = new JProgressBar();
		add(pbar);
		
		pack();
	}
	
}
