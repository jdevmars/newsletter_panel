package com.mpsilvestri.ui;

import javax.swing.JFrame;

import com.toedter.calendar.JDateChooser;

public class Screen_3 {
	
	private JFrame frame;
	
	public Screen_3() {
		
		initialize();
		
	}

	private void initialize() {
		
		frame = new JFrame();
	    frame.setBounds(100, 100, 450, 300);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.getContentPane().setLayout(null);
	         
	    JDateChooser dateChooser = new JDateChooser();
	    dateChooser.setBounds(40, 20, 200, 20);
	    frame.getContentPane().add(dateChooser);
	    
	    frame.setVisible(true);
		
	}

}
