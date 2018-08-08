package com.wellbroad.db.beans.board;

import java.util.Date;

import com.wellbroad.util.SecureTest;

public class ReviewBoardDTO {
	private int num;
	private String id;
	private String ip;
	private String title;
	private String content;
	private Date day;
	private int readcount;
	private int ref;
	private int re_step;
	private int re_level;

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
		this.id = SecureTest.convert(id);
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

	public void setDay(Date day) {
		this.day = day;
	}

	public int getReadcount() {
		return readcount;
	}

	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}

	public int getRef() {
		return ref;
	}

	public void setRef(int ref) {
		this.ref = ref;
	}

	public int getRe_step() {
		return re_step;
	}

	public void setRe_step(int re_step) {
		this.re_step = re_step;
	}

	public int getRe_level() {
		return re_level;
	}

	public void setRe_level(int re_level) {
		this.re_level = re_level;
	}
}
