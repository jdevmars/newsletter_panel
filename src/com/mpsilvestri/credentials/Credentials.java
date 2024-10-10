package com.mpsilvestri.credentials;

import javax.swing.JOptionPane;

public class Credentials {
	
	private static String server;
	private static String apiKey;
	
	public String getServer() {
		return server;
	}

	public String getApiKey() {
		return apiKey;
	}

	public static void input_server_and_key() {
		
		server = JOptionPane.showInputDialog("Which is the server number?");
		apiKey = JOptionPane.showInputDialog("What is the API key?");
		
	}

}

