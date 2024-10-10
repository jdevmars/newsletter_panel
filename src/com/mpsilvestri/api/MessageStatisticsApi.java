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

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.mpsilvestri.credentials.Credentials;
import com.mpsilvestri.data.MessageStatistics;

public class MessageStatisticsApi {
	
	private String server;
	private String apiKey;
	private List<String> xml = new ArrayList<String>();
	public static List<String> id_statistics = new ArrayList<String>();
	private int n_individual_xml_responses;
	public static List<MessageStatistics> statistics_list = new ArrayList<MessageStatistics>();
	public static MessageStatistics message_statistics_obj;
	private final String CURRENT_FOLDER = System.getProperty("user.dir");
	
	public MessageStatisticsApi() {
		
		this.server = new Credentials().getServer();
		this.apiKey = new Credentials().getApiKey();
		
	}
	
	public List<String> get_statistics_list() {
		
		final HttpClient httpClient = HttpClient.newBuilder()
				.version(HttpClient.Version.HTTP_2)
				.build();
		
		MessagesListApi messages_list_api_obj = new MessagesListApi();
		int messages_list_size = messages_list_api_obj.messages_list.size();
		
		for (int i = 0; i < messages_list_size; i++) {
		
		HttpRequest request = HttpRequest.newBuilder()
	            .GET()
	            .uri(URI.create("https://api"+this.server+".esv2.com/v2/Api/MessageStatistics/"+messages_list_api_obj.messages_list.get(i).getId()+"?apiKey="+this.apiKey))
	            .setHeader("User-Agent", "Java 16 HttpClient Bot")
	            .build();
		
		id_statistics.add(messages_list_api_obj.messages_list.get(i).getId());
		
		try {
			
			HttpResponse<String> response;
			response = (HttpResponse<String>) httpClient.send(request, HttpResponse.BodyHandlers.ofString());
			
			System.out.println("\nReturn code: " + response.statusCode());
			System.out.println("\n" + response.body());
			
			Path xml_file = Paths.get(this.CURRENT_FOLDER+"/message_"+messages_list_api_obj.messages_list.get(i).getId()+"_statistics.xml");
			Files.deleteIfExists(xml_file);
			
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(this.CURRENT_FOLDER+"/message_"+messages_list_api_obj.messages_list.get(i).getId()+"_statistics.xml",true)));
			
			this.xml.add((java.lang.String) response.body());
			
			out.println(response.body());
			out.println();
			out.close();
			
			
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		
		}
		
		return this.xml;
		
	}
	
	public void create_list_statistics() {
		
		this.n_individual_xml_responses = this.xml.size();
		
		for (int i = 0; i < this.n_individual_xml_responses; i++) {
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		
		try {
			
			DocumentBuilder builder = factory.newDocumentBuilder();
			StringBuilder xmlStringBuilder = new StringBuilder();
			xmlStringBuilder.append(this.xml.get(i)); 
			ByteArrayInputStream input = new ByteArrayInputStream(
			xmlStringBuilder.toString().getBytes("UTF-8"));
			Document doc = builder.parse(input);
			doc.getDocumentElement().normalize();
			
			message_statistics_obj = new MessageStatistics();
			
			NodeList message_nodes = doc.getElementsByTagName("Data");
			Node nNode = message_nodes.item(0);
			
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				message_statistics_obj.setId(this.id_statistics.get(i));
				message_statistics_obj.setSent(eElement.getElementsByTagName("Sent").item(0).getTextContent()); 
				message_statistics_obj.setBounced(eElement.getElementsByTagName("Bounced").item(0).getTextContent());
				message_statistics_obj.setDelivered(eElement.getElementsByTagName("Delivered").item(0).getTextContent());
				message_statistics_obj.setOpens(eElement.getElementsByTagName("Opens").item(0).getTextContent());
				message_statistics_obj.setUniqueOpens(eElement.getElementsByTagName("UniqueOpens").item(0).getTextContent());
				message_statistics_obj.setClicks(eElement.getElementsByTagName("Clicks").item(0).getTextContent());
				message_statistics_obj.setUniqueClicks(eElement.getElementsByTagName("UniqueClicks").item(0).getTextContent());
				message_statistics_obj.setClickers(eElement.getElementsByTagName("Clickers").item(0).getTextContent());
				message_statistics_obj.setComplaints(eElement.getElementsByTagName("Complaints").item(0).getTextContent());
				message_statistics_obj.setUnsubscribes(eElement.getElementsByTagName("Unsubscribes").item(0).getTextContent());
			}
			
			this.statistics_list.add(message_statistics_obj);
			System.out.println("\nObjeto " + i + ":");
			System.out.println(message_statistics_obj);
				
			System.out.println("Rodada " + i + " do looping de criação da lista de estatísticas individuais");

		} catch (ParserConfigurationException | IOException | SAXException e) {
			e.printStackTrace();
		}
		
		} 
		
	}

}
