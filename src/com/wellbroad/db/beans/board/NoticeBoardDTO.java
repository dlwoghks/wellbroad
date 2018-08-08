package com.wellbroad.db.beans.board;

import java.sql.Timestamp;
import java.util.Date;

import com.wellbroad.util.SecureTest;

public class NoticeBoardDTO {
	
	private int num;
	private String admin_id;
	private String ip;
	private String title;
	private String content;
	private Timestamp day;
	private int readcount;

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getAdmin_id() {
		return admin_id;
	}

	public void setAdmin_id(String admin_id) {
		this.admin_id = admin_id;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title =  SecureTest.convert(title);
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = SecureTest.convert(content);
	}

	public Date getDay() {
		return day;
	}

	public void setDay(Timestamp day) {
		this.day = day;
	}

	public int getReadcount() {
		return readcount;
	}

	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}
}
