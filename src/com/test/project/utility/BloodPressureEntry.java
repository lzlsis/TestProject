package com.test.project.utility;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class BloodPressureEntry implements Comparable<BloodPressureEntry>, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int year, month, day;
	private Date date;
	private int systolic, diastolic, pulse;

    private final SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
	
	public BloodPressureEntry(int year, int month, int day, int systolic, int diastolic, int pulse){
		this.year = year;
		this.month = month;
		this.day = day;
		this.systolic = systolic;
		this.diastolic = diastolic;
		this.pulse = pulse;
		this.date = new Date(year-1900, month-1, day);
	}


	public int getYear() {
		return year;
	}


	public void setYear(int year) {
		this.year = year;
	}


	public int getMonth() {
		return month;
	}


	public void setMonth(int month) {
		this.month = month;
	}


	public int getDay() {
		return day;
	}


	public void setDay(int day) {
		this.day = day;
	}

	public String getDateString() {
		return dFormat.format(date);
	}
	
	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public int getSystolic() {
		return systolic;
	}


	public void setSystolic(int systolic) {
		this.systolic = systolic;
	}


	public int getDiastolic() {
		return diastolic;
	}


	public void setDiastolic(int diastolic) {
		this.diastolic = diastolic;
	}


	public int getPulse() {
		return pulse;
	}


	public void setPulse(int pulse) {
		this.pulse = pulse;
	}


	@Override
	public int compareTo(BloodPressureEntry other) {
		return date.compareTo(other.getDate());
	}
	
	public boolean equals(BloodPressureEntry other){
		return date.equals(other.getDate());
	}
	
	public String toString(){
		return dFormat.format(date) 
				+ " S: " + systolic
				+ " D: " + diastolic
				+ " P: " + pulse;
	}
	
	public String toFileString(){
		return "" + year + " " + month + " " + day + " " + systolic + " " + diastolic + " " + pulse;
	}
	
	
}
