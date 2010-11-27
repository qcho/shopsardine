package main.java.shopsardine.view;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import main.java.shopsardine.controller.Ambassador;
import main.java.shopsardine.model.Product;

@SuppressWarnings("serial")
public class ProductViewItem extends JButton {

	Product product;

	public ProductViewItem(Product product) {
		super();
		
		this.product = product;
		
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
		
		addMouseListener(new showProductOnClick(this.product));
		
	}
	
	class showProductOnClick extends MouseAdapter {
		Product product;
		
		public showProductOnClick(Product p) {
			product = p;
		}
		
		public void mouseClicked(MouseEvent e) {
			Ambassador.getInstance().showProductDetail(product.id);
		}
	}
		
}

