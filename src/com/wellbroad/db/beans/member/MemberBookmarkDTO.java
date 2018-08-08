package com.wellbroad.db.beans.member;

import com.wellbroad.util.SecureTest;

public class MemberBookmarkDTO {
	private int num;
	private String id;
	private int review_num;
	private String title;

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

	public int getReview_num() {
		return review_num;
	}

	public void setReview_num(int review_num) {
		this.review_num = review_num;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = SecureTest.convert(title);
	}

}
