package com.mpsilvestri.data;

import java.util.ArrayList;
import java.util.List;

import com.mpsilvestri.api.MessageStatisticsApi;
import com.mpsilvestri.api.MessagesListApi;

public class Panel {
	
	private String subject;
	private String id;
	private String type;
	private String sentDate;
	private String tags;
	private String fromName;
	private String fromEmail;
	private String sent;
	private String bounced;
	private String delivered;
	private String opens;
	private String uniqueOpens;
	private String clicks;
	private String uniqueClicks;
	private String clickers;
	private String complaints;
	private String unsubscribes;
	
	private List<MessagesList> messages_list = MessagesListApi.messages_list;
	private List<MessageStatistics> statistics_list = MessageStatisticsApi.statistics_list;
	public static List<Panel> panel_items = new ArrayList<Panel>();
	public static Panel panel_item;

	private int panel_size = messages_list.size();
	
	public List<Panel> getPanel_items() {
		return panel_items;
	}	
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSentDate() {
		return sentDate;
	}
	public void setSentDate(String sentDate) {
		this.sentDate = sentDate;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getFromName() {
		return fromName;
	}
	public void setFromName(String fromName) {
		this.fromName = fromName;
	}
	public String getFromEmail() {
		return fromEmail;
	}
	public void setFromEmail(String fromEmail) {
		this.fromEmail = fromEmail;
	}
	public String getSent() {
		return sent;
	}
	public void setSent(String sent) {
		this.sent = sent;
	}
	public String getBounced() {
		return bounced;
	}
	public void setBounced(String bounced) {
		this.bounced = bounced;
	}
	public String getDelivered() {
		return delivered;
	}
	public void setDelivered(String delivered) {
		this.delivered = delivered;
	}
	public String getOpens() {
		return opens;
	}
	public void setOpens(String opens) {
		this.opens = opens;
	}
	public String getUniqueOpens() {
		return uniqueOpens;
	}
	public void setUniqueOpens(String uniqueOpens) {
		this.uniqueOpens = uniqueOpens;
	}
	public String getClicks() {
		return clicks;
	}
	public void setClicks(String clicks) {
		this.clicks = clicks;
	}
	public String getUniqueClicks() {
		return uniqueClicks;
	}
	public void setUniqueClicks(String uniqueClicks) {
		this.uniqueClicks = uniqueClicks;
	}
	public String getClickers() {
		return clickers;
	}
	public void setClickers(String clickers) {
		this.clickers = clickers;
	}
	public String getComplaints() {
		return complaints;
	}
	public void setComplaints(String complaints) {
		this.complaints = complaints;
	}
	public String getUnsubscribes() {
		return unsubscribes;
	}
	public void setUnsubscribes(String unsubscribes) {
		this.unsubscribes = unsubscribes;
	}

	@Override
	public String toString() {
		return "Panel [subject=" + subject + ", id=" + id + ", type=" + type + ", sentDate=" + sentDate + ", tags="
				+ tags + ", fromName=" + fromName + ", fromEmail=" + fromEmail + ", sent=" + sent + ", bounced="
				+ bounced + ", delivered=" + delivered + ", opens=" + opens + ", uniqueOpens=" + uniqueOpens
				+ ", clicks=" + clicks + ", uniqueClicks=" + uniqueClicks + ", clickers=" + clickers + ", complaints="
				+ complaints + ", unsubscribes=" + unsubscribes + "]";
	}
	
	public Panel(String subject, String id, String type, String sentDate, String tags, String fromName,
			String fromEmail, String sent, String bounced, String delivered, String opens, String uniqueOpens,
			String clicks, String uniqueClicks, String clickers, String complaints, String unsubscribes) {

		this.subject = subject;
		this.id = id;
		this.type = type;
		this.sentDate = sentDate;
		this.tags = tags;
		this.fromName = fromName;
		this.fromEmail = fromEmail;
		this.sent = sent;
		this.bounced = bounced;
		this.delivered = delivered;
		this.opens = opens;
		this.uniqueOpens = uniqueOpens;
		this.clicks = clicks;
		this.uniqueClicks = uniqueClicks;
		this.clickers = clickers;
		this.complaints = complaints;
		this.unsubscribes = unsubscribes;
	}
	
	public Panel() {
		
	}
		
	public List<Panel> generate_panel() {
		
		if (messages_list.size() == statistics_list.size()) {
			
			for (int i = 0; i < panel_size; i++) {
				
				panel_item = new Panel();
				
				String id_in_messages_list = messages_list.get(i).getId();
				String id_in_statistics_list = statistics_list.get(i).getId();
				
				if (id_in_messages_list.equals(id_in_statistics_list)) {
					
					panel_item.setSubject(messages_list.get(i).getSubject());
					panel_item.setId(messages_list.get(i).getId());
					panel_item.setType(messages_list.get(i).getType());
					panel_item.setSentDate(messages_list.get(i).getSentDate());
					panel_item.setTags(messages_list.get(i).getTags());
					panel_item.setFromName(messages_list.get(i).getFromName());
					panel_item.setFromEmail(messages_list.get(i).getFromEmail());
					panel_item.setSent(statistics_list.get(i).getSent());
					panel_item.setBounced(statistics_list.get(i).getBounced());
					panel_item.setDelivered(statistics_list.get(i).getDelivered());
					panel_item.setOpens(statistics_list.get(i).getOpens());
					panel_item.setUniqueOpens(statistics_list.get(i).getUniqueOpens());
					panel_item.setClicks(statistics_list.get(i).getClicks());
					panel_item.setUniqueClicks(statistics_list.get(i).getUniqueClicks());
					panel_item.setClickers(statistics_list.get(i).getClickers());
					panel_item.setComplaints(statistics_list.get(i).getComplaints());
					panel_item.setUnsubscribes(statistics_list.get(i).getUnsubscribes());
					
				}
				
				panel_items.add(panel_item);

			}
			
		}
		
		return panel_items;
				
	}
}
