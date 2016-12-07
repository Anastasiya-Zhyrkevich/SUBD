package by.bsu.var4.entity;

import java.util.Date;

public class Question {
	String date;
	int year;
	String result;
	String date2;
	

	public String getDate2() {
		return date2;
	}

	public void setDate2(String date2) {
		this.date2 = date2;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Question() {
		super();
		this.date = "2006/08/03";
		this.date2 = "2006/08/03";
		this.year = 2006;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
}
