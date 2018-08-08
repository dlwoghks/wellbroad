package com.wellbroad.db.beans.login;

public class MemberLoginDTO {
	String id;
	String password;

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

/*	public void setPassword(String password) { //비밀번호 변경시 자동로그아웃 되면 필요없음;
		this.password = password;
	}*/
}
