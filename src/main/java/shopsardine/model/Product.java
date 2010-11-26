package main.java.shopsardine.model;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;
import java.net.MalformedURLException;
import java.net.URL;

import org.w3c.dom.Element;

public class Product {

	public Image image;
	public String name;
	public double price, rank;
	
	public Product() {}
	
	public Product(String name, double price, double rank, String picurl) {
		this.name = name;
		this.price = price;
		this.rank = rank;
		
		if (picurl != null)
			fetchImage(picurl);
		
	}
	
	public Product(Element e) {
		String picurl;
		
		name = e.getElementsByTagName("name").item(0).getTextContent();
		price = Double.parseDouble(e.getElementsByTagName("price").item(0).getTextContent());
		rank = Double.parseDouble(e.getElementsByTagName("sales_rank").item(0).getTextContent());
		
		if ((picurl = e.getElementsByTagName("image_url").item(0).getTextContent()) != null)
			fetchImage(picurl);
		
	}
	
	public void fetchImage(String url) {
		Image original;
		
		try {
			original = Toolkit.getDefaultToolkit().createImage(new URL(url));
			image = whiteToTransparent(original.getScaledInstance(100, 100, Image.SCALE_SMOOTH));
			
		} catch (MalformedURLException e) {
			image = null;
		}
		
		return;
	}
	
	public String toString() {
		return "[Product " + name + "]";
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

	private static Image whiteToTransparent(Image image) {
		ImageProducer ip = new FilteredImageSource(image.getSource(), filter);
	    return Toolkit.getDefaultToolkit().createImage(ip);
	}

	
}
