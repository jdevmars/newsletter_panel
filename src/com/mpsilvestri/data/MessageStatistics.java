package com.mpsilvestri.data;

public class MessageStatistics {
	
	private String id;
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "MessageStatistics [id=" + id + ", sent=" + sent + ", bounced=" + bounced + ", delivered=" + delivered
				+ ", opens=" + opens + ", uniqueOpens=" + uniqueOpens + ", clicks=" + clicks + ", uniqueClicks="
				+ uniqueClicks + ", clickers=" + clickers + ", complaints=" + complaints + ", unsubscribes="
				+ unsubscribes + "]";
	}

}
