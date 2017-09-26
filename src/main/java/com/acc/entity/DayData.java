package com.acc.entity;

public class DayData {
	Integer date;
	String shift;
	Boolean isOnShoreHoliday;
	Boolean isOffShoreHoliday;
		public Integer getDate() {
		return date;
	}
	public void setDate(Integer date) {
		this.date = date;
	}
	public String getShift() {
		return shift;
	}
	public void setShift(String shift) {
		this.shift = shift;
	}
	public Boolean getIsOnShoreHoliday() {
		return isOnShoreHoliday;
	}
	public void setIsOnShoreHoliday(Boolean isOnShoreHoliday) {
		this.isOnShoreHoliday = isOnShoreHoliday;
	}
	public Boolean getIsOffShoreHoliday() {
		return isOffShoreHoliday;
	}
	public void setIsOffShoreHoliday(Boolean isOffShoreHoliday) {
		this.isOffShoreHoliday = isOffShoreHoliday;
	}


}
