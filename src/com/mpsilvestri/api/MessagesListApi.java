package com.mpsilvestri.api;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.mpsilvestri.credentials.Credentials;
import com.mpsilvestri.data.DateFormatter;
import com.mpsilvestri.data.MessagesList;

public class MessagesListApi {
	
	private String server;
	private String apiKey;
	private String startDate;
	private String endDate;
	private String xml;
	private int messages;
	public static List<MessagesList> messages_list;
	
	public List<MessagesList> getter_lista_mensagens() {
		return messages_list;
	}

	private final String CURRENT_FOLDER = System.getProperty("user.dir");
	
	public MessagesListApi() {
			
	}
	
	public void input_start_end_dates() {
		
		this.server = new Credentials().getServer();
		this.apiKey = new Credentials().getApiKey();
		
		this.startDate = JOptionPane.showInputDialog("What is the start date (dd/MM/yyyy)?");
		this.endDate = JOptionPane.showInputDialog("What is the end date (dd/MM/yyyy)?");
		
		DateFormatter df = new DateFormatter(this.startDate, this.endDate);
		
		this.startDate = df.getStartDate();
		this.endDate = df.getEndDate();
	}
	
	public String get_messages_list() {
		
		final HttpClient httpClient = HttpClient.newBuilder()
				.version(HttpClient.Version.HTTP_2)
				.build();
		
		HttpRequest request = HttpRequest.newBuilder()
	            .GET()
	            .uri(URI.create("https://api"+this.server+".esv2.com/v2/Api/Messages?apiKey="+this.apiKey+"&startDate="+this.startDate+"&endDate="+this.endDate+"&type=Newsletter"))
	            .setHeader("User-Agent", "Java 16 HttpClient Bot")
	            .build();
				
		try {
			
			HttpResponse<String> response;
			response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
			
			System.out.println("\nReturn code: " + response.statusCode());
			System.out.println("\n" + response.body());
			
			Path xml_file = Paths.get(this.CURRENT_FOLDER+"/messages_list.xml");
			Files.deleteIfExists(xml_file);
			
			FileWriter file = new FileWriter(this.CURRENT_FOLDER+"/messages_list.xml",true);
			BufferedWriter output = new BufferedWriter(file);
			PrintWriter out = new PrintWriter(output);
			
			this.xml = response.body();
			
			out.println(response.body());
			out.println();
			out.close();
			
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		
		return this.xml;

	}
	
	public void create_list_messages() {
		

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		
		try {
			
			DocumentBuilder builder = factory.newDocumentBuilder();
			StringBuilder xmlStringBuilder = new StringBuilder();
			xmlStringBuilder.append(this.xml); // era (Start.xml_get_messages_list)
			ByteArrayInputStream input = new ByteArrayInputStream(
			xmlStringBuilder.toString().getBytes("UTF-8"));
			Document doc = builder.parse(input);
			doc.getDocumentElement().normalize();
			
			NodeList nList = doc.getElementsByTagName("Message");
			this.messages = nList.getLength();
			
			System.out.println("\n This is the number of messages sent during the chosen period: "+ messages);
			System.out.println();
			
			this.messages_list = new ArrayList<MessagesList>();
			
			for (int temp = 0; temp < nList.getLength(); temp++) {
				
				NodeList message_nodes = doc.getElementsByTagName("Message");
				Node nNode = message_nodes.item(temp);
				MessagesList messages_list_obj = new MessagesList();  
				
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					messages_list_obj.setSubject(eElement.getElementsByTagName("Subject").item(0).getTextContent()); 
					messages_list_obj.setId(eElement.getElementsByTagName("Id").item(0).getTextContent());
					messages_list_obj.setType(eElement.getElementsByTagName("Type").item(0).getTextContent());
					int isDatePresent = eElement.getElementsByTagName("SentDate").getLength();
					messages_list_obj.setSentDate(isDatePresent !=0 ? eElement.getElementsByTagName("SentDate").item(0).getTextContent() : "0000/00/00");
					int isTagPresent = eElement.getElementsByTagName("Tags").getLength();
					messages_list_obj.setTags(isTagPresent !=0 ? eElement.getElementsByTagName("Tags").item(0).getTextContent() : "None");
					messages_list_obj.setFromName(eElement.getElementsByTagName("FromName").item(0).getTextContent());
					messages_list_obj.setFromEmail(eElement.getElementsByTagName("FromEmail").item(0).getTextContent());
					System.out.println("Rodada " + temp + " do looping");
				}
				
				 messages_list.add(messages_list_obj);
				 System.out.println(messages_list_obj);
				
			}
			
			
		} catch (ParserConfigurationException | IOException | SAXException e) {
			e.printStackTrace();
		}
		
		
	}

}
