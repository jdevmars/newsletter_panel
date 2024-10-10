package com.mpsilvestri.data;

public class DateFormatter {
	
	private String startDate;
	private String endDate;
	private String day;
	private String month;
	private String year;
	
	public DateFormatter(String startDate, String endDate) {
		
		this.day = startDate.substring(0, 2);
		this.month = startDate.substring(3, 5);
		this.year = startDate.substring(6);
		this.startDate = year +"-"+ month + "-" + day;
		
		this.day = endDate.substring(0, 2);
		this.month = endDate.substring(3, 5);
		this.year = endDate.substring(6);
		this.endDate = year +"-"+ month + "-" + day;

	}
	
	public String getStartDate() {
		return startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	
}
