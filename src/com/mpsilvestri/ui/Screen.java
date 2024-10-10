package com.mpsilvestri.ui;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import org.jdatepicker.JDatePicker;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import com.toedter.calendar.JDateChooser;

import org.jdatepicker.graphics.*;

public class Screen extends JFrame implements ActionListener {
	
	private Container container;
	private JLabel title;
	private JLabel account;
	private JLabel start_date;
	private JLabel end_date;
	private JRadioButton accormail_all_com_rb;
	private JRadioButton mail_accorhotels_com_br_rb;
	private ButtonGroup account_button_group;
	private JButton generate_btn;
	JDateChooser dateChooser_start;
	JDateChooser dateChooser_end;
	
	public static String start_year;
	public static String start_month;
	public static String start_day;
	
	public static String end_year;
	public static String end_month;
	public static String end_day;
	
	public static String api_key;
	public static String server;
	
	public Screen() {
		
		setTitle("Custom Report Generator (by MPSilvestri)");
		setBounds(300, 90, 900, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        
        container = getContentPane();
        container.setLayout(null);
        
        title = new JLabel("Custom Report Generator");
        title.setFont(new Font("Arial", Font.PLAIN, 30));
        title.setSize(500, 30);
        title.setLocation(300, 30);
        container.add(title);
        
        account = new JLabel("Business Unit: ");
        account.setFont(new Font("Arial", Font.PLAIN, 20));
        account.setSize(300, 20);
        account.setLocation(100, 100);
        container.add(account);
        
        accormail_all_com_rb = new JRadioButton("accormail-all.com");
        accormail_all_com_rb.setFont(new Font("Arial", Font.PLAIN, 15));
        accormail_all_com_rb.setSelected(true);
        accormail_all_com_rb.setSize(300, 20);
        accormail_all_com_rb.setLocation(99, 145);
        container.add(accormail_all_com_rb);
        
        mail_accorhotels_com_br_rb = new JRadioButton("mail-accorhotels.com.br");
        mail_accorhotels_com_br_rb.setFont(new Font("Arial", Font.PLAIN, 15));
        mail_accorhotels_com_br_rb.setSelected(false);
        mail_accorhotels_com_br_rb.setSize(300, 20);
        mail_accorhotels_com_br_rb.setLocation(99, 165);
        container.add(mail_accorhotels_com_br_rb);
        
        account_button_group = new ButtonGroup();
        account_button_group.add(accormail_all_com_rb);
        account_button_group.add(mail_accorhotels_com_br_rb);
        
        start_date = new JLabel("Start Date:");
        start_date.setFont(new Font("Arial", Font.PLAIN, 15));
        start_date.setSize(300, 20);
        start_date.setLocation(400, 100);
        container.add(start_date);
        
        dateChooser_start = new JDateChooser();
        dateChooser_start.setBounds(400, 130, 200, 20);
	    getContentPane().add(dateChooser_start);
	    
	    start_date = new JLabel("End Date:");
        start_date.setFont(new Font("Arial", Font.PLAIN, 15));
        start_date.setSize(300, 20);
        start_date.setLocation(650, 100);
        container.add(start_date);
        
        dateChooser_end = new JDateChooser();
        dateChooser_end.setBounds(650, 130, 200, 20);
	    getContentPane().add(dateChooser_end);
        
        generate_btn = new JButton("Submit");
        generate_btn.setFont(new Font("Arial", Font.PLAIN, 15));
        generate_btn.setSize(90, 20);
        generate_btn.setLocation(100, 205);
        generate_btn.addActionListener(this);
        container.add(generate_btn);
  
        setVisible(true);
        
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == generate_btn) {
            if (!this.dateChooser_start.getDate().toString().equalsIgnoreCase("") && !this.dateChooser_end.getDate().toString().equalsIgnoreCase("")) {
                
            	this.start_year = Integer.toString(this.dateChooser_start.getDate().getYear());
            	this.start_month = Integer.toString(this.dateChooser_start.getDate().getMonth());
            	this.start_day = Integer.toString(this.dateChooser_start.getDate().getDay());
            	
            	this.end_year = Integer.toString(this.dateChooser_end.getDate().getYear());
            	this.end_month = Integer.toString(this.dateChooser_end.getDate().getMonth());
            	this.end_day = Integer.toString(this.dateChooser_end.getDate().getDay());
            	
            	if (mail_accorhotels_com_br_rb.isSelected()) {
            		this.server = "4";
            		this.api_key = "mzWsQkpGL38CEctzCe3q";
            		JOptionPane.showMessageDialog(null, "Accor Hotels selected");
            	} else if (accormail_all_com_rb.isSelected()) {
            		this.server = "4";
            		this.api_key = "VBhPD7iIfsNScvhcc3YS";
            		JOptionPane.showMessageDialog(null, "Accor Mail selected");
            	}
            	
            }
         }
	}
}
