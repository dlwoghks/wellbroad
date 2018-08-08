package com.wellbroad.util;

public class PageUtil {
	private static final int pageUnit = 10;
	private static final int blockUnit = 5;
	private int currentPage;
	private int startPage=1;//페이지는 1부터시작
	private int endPage;
	private int totalPage;
	private int totalNum;
	private int startNum=0;//리스트에 들어있는 숫자는 0부터 시작
	private int endNum;

	
	/* EX)
	 * pageUnit 10, blockUnit 5 (1~5, 6~10, 11~15, ...)
	 * totalnum=133개, totalPage=133/10 +1 = 14페이지
	 * currentPage=12 ? ==>currentPage*(pageUnit-1)~ currentPage*(pageUnit)-1;
	 * 					   11*10 ~ (12*10)-1 (예를들어, 1페이지에는 (1-1)*1~ 1*10-1)
	 * startPage=11 =currentPage/blockUnit = 12/5 = 2
	 *               *blockUnit                     *5 => 10
	 * endPage=14 startPage+blockUnit-1= 11+5-1 = 15
	 *            if(endPage>totalPage) endPage = totalPage;
	 * 
	 * -----------------------------------------------------
	 * if(startPage==endPage) 1
	 * if(startPage>blockUnit) [이전]
	 * if(endPage<totalPage) [다음]
	 * 	 * 	 * 
	 * */
	public PageUtil(int totalNum, int currentPage) {
		super();
		this.totalNum = totalNum;
		this.currentPage = currentPage;
		
		totalPage = (totalNum/pageUnit) +1;
		if(currentPage>blockUnit) {
			startPage += (int)(currentPage/blockUnit)*blockUnit;
		}
		endPage = startPage+blockUnit-1;
		if(endPage>totalPage) endPage = totalPage;
		
		startNum = (currentPage-1)*pageUnit;
		endNum = startNum+pageUnit-1;
		if(endNum>=totalNum) endNum = totalNum-1;
	}

	public int getBlockUnit() {
		return blockUnit;
	}

	public int getStartPage() {
		return startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public int getStartNum() {
		return startNum;
	}

	public int getEndNum() {
		return endNum;
	}

	public int getCurrentPage() {
		return currentPage;
	}
	
}
