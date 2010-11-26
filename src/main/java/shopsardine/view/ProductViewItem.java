package main.java.shopsardine.view;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import main.java.shopsardine.model.Product;

@SuppressWarnings("serial")
public class ProductViewItem extends JButton {


	public ProductViewItem(Product product) {
		super();
		
		if (product.image != null) {
			ImageIcon icon = new ImageIcon(product.image);
			
			setIcon(icon);
		}
		
		String name = product.name;
		if (name.length() > 40)
			name = name.substring(0, 28) + "...";
		
		setText("<html>" + 
				"<b>" + name + "<br />" +
				"$" + product.price);
		
		
		setHorizontalAlignment(SwingConstants.LEFT);
		
	}
	
	
		
}

