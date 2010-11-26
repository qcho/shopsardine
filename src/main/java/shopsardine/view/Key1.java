package main.java.shopsardine.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Key1 extends JPanel implements ActionListener // MyFrameWithExitHandling
{
	static final String KEYB = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	JButton btList[];
	String keyBuffer = "";
	JTextField TxtField;
	JButton Find, capsLockKey, spaceBar, backSpace;
	boolean capslock;

	public void key() {
		backSpace = new JButton("<---");
		add(backSpace);
		backSpace.addActionListener(this);
		capsLockKey = new JButton("CAPS");
		add(capsLockKey);
		capsLockKey.addActionListener(this);

		int n = KEYB.length();
		btList = new JButton[n];
		for (int i = 0; i < n; i++) {
			btList[i] = new JButton("" + KEYB.charAt(i));
			add(btList[i]);
			btList[i].addActionListener(this);
		}
		// spacebar by DreadedTuesday
		spaceBar = new JButton("Spacebar");
		add(spaceBar);
		spaceBar.addActionListener(this);

	}

	public void actionPerformed(ActionEvent e) {
		int n = KEYB.length();
		if (e.getSource() == backSpace) {
			keyBuffer = keyBuffer.substring(0, TxtField.getText().length() - 1);
			TxtField.setText(keyBuffer);
		} else if (e.getSource() == capsLockKey) {
			if (capslock)
				capslock = false;
			else
				capslock = true;
		} else if (e.getSource() == spaceBar) {
			keyBuffer += " ";
		} else {
			for (int i = 0; i < n; i++) {
				if (e.getSource() == btList[i]) {
					if (capslock) {
						keyBuffer += KEYB.charAt(i);
					} else {
						keyBuffer += KEYB.toLowerCase().charAt(i);
					}
					TxtField.setText("" + keyBuffer);
					break;
				}// end of if
			}// end of for
		}
	}

	public Key1(JTextField textField) {
		TxtField = textField;
		JPanel Findpane;
		Findpane = new JPanel();
		add(Findpane);
		capslock = false; // added by DreadedTuesday
		setPreferredSize(new Dimension(350, 200));
		key();
	}

	public static void main(String s[]) {
		JFrame frame = new JFrame("Search");
		JTextField tf = new JTextField(20);
		Key1 keyboard = new Key1(tf);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setForeground(Color.white);
		frame.setBackground(Color.lightGray);
		
		frame.getContentPane().add(tf);
		frame.getContentPane().add(keyboard, "Center");
		frame.pack();
		frame.setVisible(true);
	}// public void
}// end class
