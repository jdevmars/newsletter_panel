package com.mpsilvestri.excel;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.file.FileSystemException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.mpsilvestri.api.MessageStatisticsApi;
import com.mpsilvestri.data.MessageStatistics;
import com.mpsilvestri.data.Panel;


public class Excel {
	
	private final String CURRENT_FOLDER = System.getProperty("user.dir");
	private List<Panel> panel_items = Panel.panel_items;
	public static List<MessageStatistics> statistics_list = MessageStatisticsApi.statistics_list;
	public MessageStatistics message_statistics_obj = MessageStatisticsApi.message_statistics_obj;
	
	String test = panel_items.get(0).getBounced();
	
	Workbook wb = new HSSFWorkbook();
	
	Sheet sheet_1 = wb.createSheet("Campaigns");
	
	public Excel() {
		
	}
	
	public void generate_excel_file_header() {
		
		Row row_0 = sheet_1.createRow(0);
		Cell cell_0 = row_0.createCell(0);
		cell_0.setCellValue("Id");
		Cell cell_1 = row_0.createCell(1);
		cell_1.setCellValue("Subject");
		Cell cell_2 = row_0.createCell(2);
		cell_2.setCellValue("Date");
		Cell cell_3 = row_0.createCell(3);
		cell_3.setCellValue("Tags");
		Cell cell_4 = row_0.createCell(4);
		cell_4.setCellValue("Sent");
		Cell cell_5 = row_0.createCell(5);
		cell_5.setCellValue("Bounced");
		Cell cell_6 = row_0.createCell(6);
		cell_6.setCellValue("Delivered");
		Cell cell_7 = row_0.createCell(7);
		cell_7.setCellValue("Opens");
		Cell cell_8 = row_0.createCell(8);
		cell_8.setCellValue("Unique Opens");
		Cell cell_9 = row_0.createCell(9);
		cell_9.setCellValue("Clicks");
		Cell cell_10 = row_0.createCell(10);
		cell_10.setCellValue("Unique Clicks");
		Cell cell_11 = row_0.createCell(11);
		cell_11.setCellValue("Clickers");
		Cell cell_12 = row_0.createCell(12);
		cell_12.setCellValue("Complaints");
		Cell cell_13 = row_0.createCell(13);
		cell_13.setCellValue("Unsubscribes");
		
	}
	
	public void generate_excel_file() {
		
		System.out.println("\nCampanhas para o arquivo Excel: ");
		System.out.println();
		
		try(OutputStream fileOut = new FileOutputStream(this.CURRENT_FOLDER + "/campaigns.xlsx")) {  

			for (int row_counter = 1; row_counter <= statistics_list.size(); row_counter++) {
				Row row = sheet_1.createRow(row_counter);
				
				Cell cell_0 = row.createCell(0);
				cell_0.setCellValue(this.panel_items.get(row_counter-1).getId());
				System.out.println(this.panel_items.get(row_counter-1).getId());
				Cell cell_1 = row.createCell(1);
				cell_1.setCellValue(this.panel_items.get(row_counter-1).getSubject());
				System.out.println(this.panel_items.get(row_counter-1).getSubject());
				Cell cell_2 = row.createCell(2);
				cell_2.setCellValue(date_formatter(this.panel_items.get(row_counter-1).getSentDate()));
				System.out.println(this.panel_items.get(row_counter-1).getSentDate());
				Cell cell_3 = row.createCell(3);
				cell_3.setCellValue(this.panel_items.get(row_counter-1).getTags());
				Cell cell_4 = row.createCell(4);
				cell_4.setCellValue(this.panel_items.get(row_counter-1).getSent());
				Cell cell_5 = row.createCell(5);
				cell_5.setCellValue(this.panel_items.get(row_counter-1).getBounced());
				Cell cell_6 = row.createCell(6);
				cell_6.setCellValue(this.panel_items.get(row_counter-1).getDelivered());
				Cell cell_7 = row.createCell(7);
				cell_7.setCellValue(this.panel_items.get(row_counter-1).getOpens());
				Cell cell_8 = row.createCell(8);
				cell_8.setCellValue(this.panel_items.get(row_counter-1).getUniqueOpens());
				Cell cell_9 = row.createCell(9);
				cell_9.setCellValue(this.panel_items.get(row_counter-1).getClicks());
				Cell cell_10 = row.createCell(10);
				cell_10.setCellValue(this.panel_items.get(row_counter-1).getUniqueClicks());
				Cell cell_11 = row.createCell(11);
				cell_11.setCellValue(this.panel_items.get(row_counter-1).getClickers());
				Cell cell_12 = row.createCell(12);
				cell_12.setCellValue(this.panel_items.get(row_counter-1).getComplaints());
				Cell cell_13 = row.createCell(13);
				cell_13.setCellValue(this.panel_items.get(row_counter-1).getUnsubscribes());
				
			}
			
			wb.write(fileOut);
			
        } catch(ArrayIndexOutOfBoundsException aioobe) {  
            System.out.println("\n" + aioobe.getMessage());  
        } catch (IOException ioe) {
			ioe.printStackTrace();
		}  
		
	}
	
	// No LibreOffice, acentos corretos, porém a vírgula é entendida como separadora de coluna em Tags e quebra o arquivo
	// No Excel, todas as colunas aparecem corretamente, porém não se reconhecem os acentos e caracteres especiais
	public void generate_plain_text_file() {
		
		Panel panel = new Panel();
		panel.getPanel_items();
		
		try {
			
			Path xml_file = Paths.get(this.CURRENT_FOLDER+"/campaigns.csv");
			Files.deleteIfExists(xml_file);
			
			// PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(this.CURRENT_FOLDER+"/campaigns.csv",true)));
			//PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.CURRENT_FOLDER+"/campaigns.csv", true), StandardCharsets.UTF_8)));
			PrintWriter out = new PrintWriter(new OutputStreamWriter(new FileOutputStream(this.CURRENT_FOLDER+"/campaigns.csv"), "UTF-8"));
			
			out.print("Id");
			out.print("\t");
			out.print("Subject");
			out.print("\t");
			out.print("Date");
			out.print("\t");
			out.print("Tags");
			out.print("\t");
			out.print("Sent");
			out.print("\t");
			out.print("Bounced");
			out.print("\t");
			out.print("Delivered");
			out.print("\t");
			out.print("Opens");
			out.print("\t");
			out.print("Unique Opens");
			out.print("\t");
			out.print("Clicks");
			out.print("\t");
			out.print("Unique Clicks");
			out.print("\t");
			out.print("Clickers");
			out.print("\t");
			out.print("Complaints");
			out.print("\t");
			out.print("Unsubscribes");
			out.println();
			
			for(int i = 0; i < panel_items.size(); i++) {
				
				out.print(panel_items.get(i).getId());
				out.print("\t");
				out.print(panel_items.get(i).getSubject());
				out.print("\t");
				out.print(panel_items.get(i).getSentDate());
				out.print("\t");
				out.print(panel_items.get(i).getTags());
				out.print("\t");
				out.print(panel_items.get(i).getSent());
				out.print("\t");
				out.print(panel_items.get(i).getBounced());
				out.print("\t");
				out.print(panel_items.get(i).getDelivered());
				out.print("\t");
				out.print(panel_items.get(i).getOpens());
				out.print("\t");
				out.print(panel_items.get(i).getUniqueOpens());
				out.print("\t");
				out.print(panel_items.get(i).getClicks());
				out.print("\t");
				out.print(panel_items.get(i).getUniqueClicks());
				out.print("\t");
				out.print(panel_items.get(i).getClickers());
				out.print("\t");
				out.print(panel_items.get(i).getComplaints());
				out.print("\t");
				out.print(panel_items.get(i).getUnsubscribes());
				out.println();
				
			}
			
			
			out.println();
			out.close();
			
		} catch (FileSystemException fse) {
			System.out.println("CSV file is being used by another application!!!");
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} 

	}
	
	public void open_excel() {
		
		boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");
		
		if (isWindows)
			try {
				Runtime.getRuntime().exec(String.format("cmd.exe /c start %s", this.CURRENT_FOLDER + "/campaigns.xlsx"));
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		else
			try {
				Runtime.getRuntime().exec(String.format("/bin/sh -c open %s", this.CURRENT_FOLDER + "/campaigns.xlsx"));
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	public String date_formatter(String date) {
		
		String day;
		String month;
		String year;
		String hours;
		String minutes;
		String seconds;
		
		year = date.substring(0, 4);
		month = date.substring(5, 7);
		day = date.substring(8, 10);
		
		if(date.length() > 10) {
			hours = date.substring(11, 13);
			minutes = date.substring(14, 16);
			seconds = date.substring(18);
		} else {
			hours = "00";
			minutes = "00";
			seconds = "00";
		}
		
		return day + "/" + month + "/" + year + " @ " + hours + ":" + minutes + ":" + seconds;
		
	}

}
