package com.wellbroad.db.beans.myroute;

import java.util.Date;
import java.util.List;

public class MyRouteDTO {
	int num;
	String id;
	int course;
	String coursename;
	String startdate;
	String enddate;
	int day;
	String icon; //bar, beatch, coffee
	String icon_name; //코드명 
	String nationname;
	String cityname;
	String tourname;
	
	
	
	
	public MyRouteDTO() {}
	
	public MyRouteDTO(int num, String id, int course, String coursename, String startdate, String enddate, int day,
			String icon, String icon_name,String nationname,String cityname,String tourname) {
		super();
		this.num = num;
		this.id = id;
		this.course = course;
		this.coursename = coursename;
		this.startdate = startdate;
		this.enddate = enddate;
		this.day = day;
		this.icon = icon;
		this.icon_name = icon_name;
		this.nationname= nationname;
		this.cityname = cityname;
		this.tourname = tourname;
		
		
	}
	
	
	
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getCourse() {
		return course;
	}
	public void setCourse(int course) {
		this.course = course;
	}
	public String getCoursename() {
		return coursename;
	}
	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}
	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getIcon_name() {
		return icon_name;
	}
	public void setIcon_name(String icon_name) {
		this.icon_name = icon_name;
	}

	public String getNationname() {
		return nationname;
	}

	public void setNationname(String nationname) {
		this.nationname = nationname;
	}

	public String getCityname() {
		return cityname;
	}

	public void setCityname(String cityname) {
		this.cityname = cityname;
	}

	public String getTourname() {
		return tourname;
	}

	public void setTourname(String tourname) {
		this.tourname = tourname;
	}
	
	
}

	
	