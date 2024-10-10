package com.mpsilvestri.start;

import java.util.List;

import com.mpsilvestri.api.MessageStatisticsApi;
import com.mpsilvestri.api.MessagesListApi;
import com.mpsilvestri.credentials.Credentials;
import com.mpsilvestri.data.Panel;
import com.mpsilvestri.excel.Excel;
import com.mpsilvestri.ui.Screen;
import com.mpsilvestri.ui.Screen_2;
import com.mpsilvestri.ui.Screen_3;

public class Start {
	
	public static String xml_get_messages_list;
	public static List<String> xml_get_message_statistics;
	public static Panel panel;
	
	public static void main(String[] args) {
		
		//Screen screen = new Screen();
		//Screen_2 screen_2 = new Screen_2();
		//screen_2.GUI();
		
		//Screen_3 window = new Screen_3();
        
		
		
		new Credentials();
		Credentials.input_server_and_key();
		MessagesListApi messages_list_api_obj = new MessagesListApi();
		messages_list_api_obj.input_start_end_dates();
		xml_get_messages_list = messages_list_api_obj.get_messages_list();
		messages_list_api_obj.create_list_messages();
		MessageStatisticsApi message_statistics_api_obj = new MessageStatisticsApi();
		xml_get_message_statistics = message_statistics_api_obj.get_statistics_list();
		message_statistics_api_obj.create_list_statistics();
		panel = new Panel();
		System.out.println();
		System.out.println(panel.generate_panel());
		Excel excel = new Excel();
		excel.generate_excel_file_header();
		excel.generate_excel_file();
		excel.open_excel();
		
	}
	
}
