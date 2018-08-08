package com.wellbroad.db.beans.tour;

import com.wellbroad.db.connection.DBConnectionManager;

public class TourInformationDAO {
//1.연결하고자하는 클래스 객체 선언
	private DBConnectionManager pool = null;
	
//2.생성자 (연결하고자하는 클래스객체 생성)
	public TourInformationDAO() {
		try {
			pool=DBConnectionManager.getInstance();
			System.out.println("pool = "+pool);
		} catch(Exception e) {
			System.out.println("DBConnectable error = "+e);
		}
	}
	
//3.TourNation 관련 메소드
	//3-0. TourNation 총 국가 수 getTourNationCount
	//3-1. TourNation 국가목록 가져오기 getTourNationList
	//3-2. TourNation 테이블에 국가 추가(admin) insertTourNation
	//3-3. TourNation 테이블에 국가 수정(admin) updateTourNation
	//3-4. TourNation 테이블에 국가 삭제(admin) deleteTourNation

//4.TourCity 관련 메소드
	//4-0. TourCity 총 도시 수 getTourCityCount
	//4-1. TourCity 도시목록 가져오기 getTourCityList
	//4-2. TourCity 테이블에 도시 추가(admin) insertTourCity
	//4-3. TourCity 테이블에 도시 수정(admin) updateTourCity
	//4-4. TourCity 테이블에 도시 삭제(admin) deleteTourCity

//5.TourFood/Spot/Stay 관련 메소드
	//4-0. TourFood/Spot/Stay 총 수 getTourInformationCount
	//4-1. TourFood/Spot/Stay 목록 가져오기 getTourInformationList
	//4-2. TourFood/Spot/Stay 테이블에 추가(admin) insertTourInformation
	//4-3. TourFood/Spot/Stay 테이블에 수정(admin) updateTourInformation
	//4-4. TourFood/Spot/Stay 테이블에 삭제(admin) deleteTourInformation
}
