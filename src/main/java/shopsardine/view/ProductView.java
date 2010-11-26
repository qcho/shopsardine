package main.java.shopsardine.view;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import main.java.shopsardine.model.Product;

@SuppressWarnings("serial")
public class ProductView extends JScrollPane {

	int last, count;
	static final int NCOLS = 2;
	
	JPanel content;
	
	public ProductView() {
		super();
		setViewportView(content = new JPanel());
		
		content.setLayout(new GridLayout(0, NCOLS));
	}
	
	public void addProduct(Product product) {
		addProduct(product, false);
	}
	
	public void addProduct(Product product, boolean refresh) {
		GridLayout layout = (GridLayout) content.getLayout();
		if (++last % NCOLS == 1)
			//if (++count > 8)
				layout.setRows(layout.getRows() + 1);
		
		content.add(new ProductViewItem(product));
		
		if (refresh) {
			refresh();
		}
	}
	
	public void refresh() {
		content.revalidate();
		content.repaint();
	}
	
	public void clearProducts() {
		content.removeAll();
		content.setLayout(new GridLayout(0, NCOLS));
		refresh();
	}
	
	@Override
	public void setPreferredSize(Dimension dim) {
		content.setMaximumSize(new Dimension(dim.width, 0));
		super.setPreferredSize(dim);
	}
	
}

