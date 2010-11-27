/*
 * Copyright 2008 ZXing authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package main.java.shopsardine.model.qrcode;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.ReaderException;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import main.java.shopsardine.controller.Ambassador;
import main.java.shopsardine.main.SSApplication;

import org.jdesktop.application.ApplicationContext;

/**
 * <p>
 * Simple GUI frontend to the library. Right now, only decodes a local file.
 * This definitely needs some improvement. Just throwing something down to
 * start.
 * </p>
 * 
 * @author Sean Owen
 */
public final class GUIRunner extends JPanel {
	
	ApplicationContext context = SSApplication.getInstance().getContext();

	private static final long serialVersionUID = 1L;
	private final JLabel imageLabel;
	private final JTextArea textArea;
	private final JButton button;

	public GUIRunner() {
		imageLabel = new JLabel();
		textArea = new JTextArea();
		button = new JButton(context.getResourceMap().getString("loadproduct"));
		textArea.setEditable(false);
		textArea.setMaximumSize(new Dimension(400, 200));
		Container panel = this;
		panel.setLayout(new FlowLayout());
		panel.add(imageLabel);
		panel.add(textArea);
		panel.add(button);
	}

	public void chooseImage() throws MalformedURLException {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.showOpenDialog(this);
		File file = fileChooser.getSelectedFile();
		Icon imageIcon = new ImageIcon(file.toURI().toURL());
		setSize(imageIcon.getIconWidth(), imageIcon.getIconHeight() + 100);
		imageLabel.setIcon(imageIcon);
		final String decodeText = getDecodeText(file);
		textArea.setText(decodeText);
		
		button.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
					Ambassador.getInstance().showProductDetail(Integer.valueOf(decodeText));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});

	}

	private static String getDecodeText(File file) {
		BufferedImage image;
		try {
			image = ImageIO.read(file);
		} catch (IOException ioe) {
			return ioe.toString();
		}
		if (image == null) {
			return "Could not decode image";
		}
		LuminanceSource source = new BufferedImageLuminanceSource(image);
		BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
		Result result;
		try {
			result = new MultiFormatReader().decode(bitmap);
		} catch (ReaderException re) {
			return re.toString();
		}
		return result.getText();
	}

}
