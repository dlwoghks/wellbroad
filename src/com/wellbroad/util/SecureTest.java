package com.wellbroad.util;

public class SecureTest {
    //정적메서드->공통으로 자주 사용이 되는 메서드->새로운 코드를 작성
	public static String convert(String input) {
		if(input!=null){
			input=input.replaceAll("<","&lt;");
			input=input.replaceAll(">","&gt;");
			//추가
			input=input.replaceAll("\\(","&#40;");
			input=input.replaceAll("\\)","&#41;");
			input=input.replaceAll("\"","&quot;");
			input=input.replaceAll("\'","&apos;");
		  }
		return input;
	}
}
