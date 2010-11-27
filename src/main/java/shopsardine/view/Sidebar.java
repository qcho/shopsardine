package main.java.shopsardine.view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;

import main.java.shopsardine.main.SSApplication;

import org.jdesktop.application.ApplicationContext;

public class Sidebar extends JPanel {

	ApplicationContext context = SSApplication.getInstance().getContext();

	public static final int CATALOG = 0;
	public static final int SEARCH = 1;
	public static final int SCAN = 2;
	public static final int STATS = 3;
	public static final int HELP = 4;
	
	public JToggleButton[] buttons;
	public ButtonGroup bg = new ButtonGroup();
	public String[] labels = {"catalog", "search", "scan", "stats", "help"};
	
	public JLabel logoImage;
	public JLabel loadingImage;
	
	public JLabel header;
	
	public Sidebar() {
		setName("sidebar");
		setLayout(new GridLayout(0, 1, 10, 10));
		
		loadLogoImage();
		loadLoadingImage();
		
		header = logoImage;
		
		add(header);
		
		buttons = new JToggleButton[labels.length];
		for (int i = 0; i < labels.length; i++) {
			buttons[i] = new JToggleButton();
			buttons[i].setName("sb" + labels[i]);
			add(buttons[i]);
			bg.add(buttons[i]);
		}
		
	}
	
	private void loadLoadingImage() {
		loadingImage = new JLabel(new ImageIcon(fetchImage("http://eiffel.itba.edu.ar/hci/g5/files/images/loading.gif")));
		loadingImage.setText(context.getResourceMap().getString("loading"));
		loadingImage.setVerticalTextPosition(JLabel.TOP);
		loadingImage.setHorizontalTextPosition(JLabel.CENTER);
	}

	private void loadLogoImage() {
		logoImage = new JLabel(new ImageIcon(fetchImage("http://eiffel.itba.edu.ar/hci/g5/files/images/logo.png")));
	}

	public void showLogo() {
		remove(header);
		header = logoImage;
		add(header, 0);
		validate();
	}
	
	public void showLoading() {
		remove(header);
		header = loadingImage;
		add(header, 0);
		validate();
	}
	
	public Image fetchImage(String url) {
		Image original;
		Image finalImage;
		
		try {
			original = Toolkit.getDefaultToolkit().createImage(new URL(url));
			finalImage = whiteToTransparent(original.getScaledInstance(100, 100, Image.SCALE_SMOOTH));
			
		} catch (MalformedURLException e) {
			finalImage = null;
		}
		
		return finalImage;
	}
	
	private static Image whiteToTransparent(Image image) {
		ImageProducer ip = new FilteredImageSource(image.getSource(), filter);
	    return Toolkit.getDefaultToolkit().createImage(ip);
	}
	
	static ImageFilter filter;
	
	static {
		filter = new RGBImageFilter() {

			public int markerRGB = Color.WHITE.getRGB() | 0xFF0000;

			public final int filterRGB(int x, int y, int rgb) {
				if ( ( rgb | 0xFF000000 ) == markerRGB )
					return 0x00FFFFFF & rgb;
				else {
					return rgb;
				}
				
			}
		}; 
	}
	
}
