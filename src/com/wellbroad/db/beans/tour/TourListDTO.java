package com.wellbroad.db.beans.tour;

public class TourListDTO {

	private String name;
	private String code;
	private String description;
	private String icon;

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public TourListDTO() {
	}

	public TourListDTO(String icon,String code, String name, String description) {
		this.icon = icon;
		this.code = code;
		this.name = name;
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDiscription() {
		return description;
	}

	public void setDiscription(String description) {
		this.description = description;
	}

	@Override 
	public String toString() { 
//		description = description.replace("/\n/gi","\\r\\n"); 

	return "{\"icon\":\""+icon+"\",\"code\":\"" + code + "\", \"name\":\"" + name + "\", \"description\":\"" + description + "\"}";

	}

}
