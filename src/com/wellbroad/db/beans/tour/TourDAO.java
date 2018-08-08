package com.wellbroad.db.beans.tour;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.wellbroad.db.connection.DBConnectionManager;


public class TourDAO {
//1.연결하고자하는 클래스 객체 선언
	private DBConnectionManager pool = null;
	
//2.생성자 (연결하고자하는 클래스객체 생성)
	public TourDAO() {
		try {
			pool=DBConnectionManager.getInstance();
			System.out.println("pool = "+pool);
		} catch(Exception e) {
			System.out.println("DBConnectable error = "+e);
		}
	}	 
	
//3.TourNation 관련 메소드	
	//3-1. TourNation 국가목록 가져오기 getTourNationList
	public List<TourNationDTO> getTourNationList() {
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		String sql="";
		List<TourNationDTO> nationList = new ArrayList();		
		
		try {
			conn=pool.getConnection();
			sql="select * from tour_nation order by code_nation asc";
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next()) {
				TourNationDTO nation = new TourNationDTO();
				nation.setCode_nation(rs.getString("code_nation"));
				nation.setName(rs.getString("name"));
				nationList.add(nation);
			}
		}catch (Exception e) {
			System.out.println("getTourNationList() error");
			e.printStackTrace();
		}finally {
			pool.freeConnection(conn,stmt,rs);
		}		
		return nationList;
	}
	
	//3-2. TourNation 국가 가져오기 getTourNation
	public TourNationDTO getTourNation(String code_nation) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="";
		TourNationDTO nation = new TourNationDTO();
		
		try {
			conn=pool.getConnection();
			sql="select * from tour_nation where code_nation like '%"+code_nation+"%'";
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				nation.setCode_nation(rs.getString("code_nation"));
				nation.setName(rs.getString("name"));
			}
		}catch (Exception e) {
			System.out.println("getTourNation() error");
			e.printStackTrace();
		}finally {
			pool.freeConnection(conn,pstmt,rs);
		}		
		return nation;
	}
		
	//3-3. TourNation 테이블에 국가 추가(admin) insertTourNation
	public int insertTourNation(TourNationDTO nationToInsert) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sql="";
		int insertNationCheck=0; //삽입에 성공하면 1, 실패하면 0 반환
		
		try {
			conn=pool.getConnection();
			sql="insert into tour_nation values(?,?)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, nationToInsert.getCode_nation());
			pstmt.setString(2, nationToInsert.getName());
			insertNationCheck=pstmt.executeUpdate();
		}catch (Exception e) {
			System.out.println("insertTourNation() error");
			e.printStackTrace();
		}finally {
			pool.freeConnection(conn,pstmt);
		}		
		return insertNationCheck;
	}
	
	//3-4. code_nation 중복체크
	public boolean checkCode_nation(String code_nation) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="";
		boolean checkCode_nation=true; //중복이면 false, 중복이 아니면 true
		
		try {
			conn=pool.getConnection();
			sql="select code_nation from tour_nation where code_nation=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, code_nation);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString("code_nation").equals(code_nation)) checkCode_nation=false;
			}
		}catch (Exception e) {
			System.out.println("checkCode_nation() error");
			e.printStackTrace();
		}finally {
			pool.freeConnection(conn,pstmt);
		}		
		return checkCode_nation;
	}
		
	//3-5. TourNation 테이블에 국가 수정(admin) updateTourNation
	public int updateTourNation(TourNationDTO nationToUpdate) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sql="";
		int updateNationCheck=0; //수정에 성공하면 1, 실패하면 0 반환
		
		try {
			conn=pool.getConnection();
			sql="update tour_nation set name=?, code_nation=? where code_nation=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, nationToUpdate.getName());
			pstmt.setString(2, nationToUpdate.getCode_nation());
			pstmt.setString(3, nationToUpdate.getCode_nation());
			updateNationCheck=pstmt.executeUpdate();
		}catch (Exception e) {
			System.out.println("updateTourNation() error");
			e.printStackTrace();
		}finally {
			pool.freeConnection(conn,pstmt);
		}		
		return updateNationCheck;
	}
	
	//3-6. TourNation 테이블에 국가 삭제(admin) deleteTourNation
	public boolean deleteTourNation(String code_nation) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sql="";
		boolean deleteNationCheck=false; //삭제에 성공하면 true, 실패하면 false반환
		//관련 도시들 찾아내기
		List<TourCityDTO> cityList = getTourCityList(code_nation);
		for(TourCityDTO city : cityList) {
			deleteTourCity(city.getCode_city());
		}
		
		try {
			conn=pool.getConnection();						
			//국자삭제
			sql="delete from tour_nation where code_nation=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, code_nation);
			if(pstmt.executeUpdate()==1) deleteNationCheck=true; 
		}catch (Exception e) {
			System.out.println("deleteTourNation() error");
			e.printStackTrace();
		}finally {
			pool.freeConnection(conn,pstmt);
		}		
		return deleteNationCheck;
	}
	
//4.TourCity 관련 메소드
	//4-1. TourCity 도시목록 가져오기 getTourCityList
	public List<TourCityDTO> getTourCityList(String code_nation) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="";
		List<TourCityDTO> cityList = new ArrayList();		
		
		try {
			conn=pool.getConnection();
			sql="select * from tour_city where code_nation like '%"+code_nation+"%' order by code_city asc";
			pstmt=conn.prepareStatement(sql);
			/*pstmt.setString(1, code_nation);*/
			rs=pstmt.executeQuery();
			while(rs.next()) {
				TourCityDTO city = new TourCityDTO();
				city.setCode_city(rs.getString("code_city"));
				city.setName(rs.getString("name"));
				city.setCode_nation(code_nation);
				cityList.add(city);
			}
		}catch (Exception e) {
			System.out.println("getTourCityList() error");
			e.printStackTrace();
		}finally {
			pool.freeConnection(conn,pstmt,rs);
		}		
		return cityList;
	}
	
	//4-2. TourCity 도시 가져오기 getTourCity
	public TourCityDTO getTourCity(String code_city) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="";
		TourCityDTO city = new TourCityDTO();		
		
		try {
			conn=pool.getConnection();
			sql="select * from tour_city where code_city like '%"+code_city+"%'";
			pstmt=conn.prepareStatement(sql);
			/*pstmt.setString(1, code_city);*/
			rs=pstmt.executeQuery();
			while(rs.next()) {
				city.setCode_city(rs.getString("code_city"));
				city.setName(rs.getString("name"));
				city.setDescription(rs.getString("description"));
				city.setCode_nation(rs.getString("code_nation"));
			}
		}catch (Exception e) {
			System.out.println("getTourCity() error");
			e.printStackTrace();
		}finally {
			pool.freeConnection(conn,pstmt,rs);
		}		
		return city;
	}
		
	//4-3. TourCity 테이블에 도시 추가(admin) insertTourCity
	public int insertTourCity(TourCityDTO cityToInsert) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sql="";
		int insertCityCheck=0; //삽입에 성공하면 1, 실패하면 0 반환
		
		try {
			conn=pool.getConnection();
			sql="insert into tour_city values(?,?,?,?)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, cityToInsert.getCode_city());
			pstmt.setString(2, cityToInsert.getName());
			pstmt.setString(3, cityToInsert.getCode_nation());
			pstmt.setString(4, cityToInsert.getDescription());
			insertCityCheck=pstmt.executeUpdate();
			if(insertCityCheck==1) makeRuleAboutCodeConnection(cityToInsert.getCode_nation(),cityToInsert.getCode_city());
		}catch (Exception e) {
			System.out.println("insertTourCity() error");
			e.printStackTrace();
		}finally {
			pool.freeConnection(conn,pstmt);
		}		
		return insertCityCheck;
	}
	
	/* 4-3-번외. TourCity를 삽입함과 동시에 여행정보들의 규칙에 따른 코드 생성
	 *  예를 들어) 국가코드가 KOR이고, 도시코드가 seoul이라면, 
	 *  		코드 앞 2글자는 KS로 시작 +
	 *  						KS로 시작하는 코드가 없다면 KS0, 있다면 KS1,KS2,KS3,,,,
	 *  
	 */
	private void makeRuleAboutCodeConnection(String code_nation,String code_city) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="";
		String precode="";
		precode=code_nation.substring(0,1)+code_city.substring(0,1).toUpperCase();
		precode=getRuleAboutCodeConnection(precode);
		
		try {
			conn=pool.getConnection();
			sql="insert into codeConnection values(?,?)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, code_city);
			pstmt.setString(2, precode);
			rs=pstmt.executeQuery();
		}catch (Exception e) {
			System.out.println("makeRuleAboutCodeConnection() error");
			e.printStackTrace();
		}finally {
			pool.freeConnection(conn,pstmt);
		}		
	}
	
	/*4-3-번외-0. 비어있는 코드값 찾기
	 * EX. 대전 KD0, 대구 KD1, 둘다 KD로 시작할 때,
	 * 대전을 삭제하고 다시 대전을 추가했을 경우, KD2가 아니라 비어있는 KD0이 될 수 있도록!
	 * */
	private String getRuleAboutCodeConnection(String precode) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="";
		int codearr[] = new int[10];
		
		try {
			conn=pool.getConnection();
			sql="select precode from codeConnection where precode like '"+precode+"%' order by precode";
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			char tempcode;
			while(rs.next()) {
				tempcode = rs.getString(1).charAt(2);
				codearr[tempcode-'0']=1;
			}
			int i=0;
			while(codearr[i]==1) {
				i++;
				if(i>9) break;
			}
			precode+=Integer.toString(i);
		}catch (Exception e) {
			System.out.println("getRuleAboutCodeConnection() error");
			e.printStackTrace();
		}finally {
			pool.freeConnection(conn,pstmt);
		}
		return precode;
	}
	
	//4-4. code_city 중복체크
	public boolean checkCode_city(String code_city) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="";
		boolean checkCode_city=true; //중복이면 false, 중복이 아니면 true
		
		try {
			conn=pool.getConnection();
			sql="select code_city from tour_city where code_city=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, code_city);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString("code_city").equals(code_city)) checkCode_city=false;
			}
		}catch (Exception e) {
			System.out.println("checkCode_city() error");
			e.printStackTrace();
		}finally {
			pool.freeConnection(conn,pstmt);
		}		
		return checkCode_city;
	}
	
	//4-5. TourCity 테이블에 도시 수정(admin) updateTourCity
	public int updateTourCity(TourCityDTO cityToUpdate) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sql="";
		int updateCityCheck=0; //삽입에 성공하면 1, 실패하면 0 반환
		
		try {
			conn=pool.getConnection();
			sql="update tour_city set name=?, description=? where code_city=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, cityToUpdate.getName());
			pstmt.setString(2, cityToUpdate.getDescription());
			pstmt.setString(3, cityToUpdate.getCode_city());
			updateCityCheck=pstmt.executeUpdate();
		}catch (Exception e) {
			System.out.println("updateTourCity() error");
			e.printStackTrace();
		}finally {
			pool.freeConnection(conn,pstmt);
		}		
		return updateCityCheck;
	}
	
	//4-6. TourCity 테이블에 도시 삭제(admin) deleteTourCity
	public boolean deleteTourCity(String code_city) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sql="";
		boolean deleteCityCheck=false; //삭제에 성공하면 true, 실패하면 false반환
		//관련 관광,숙소,식당 먼저 삭제
		String code = getCodeConnection(code_city);
		deleteTour("tour_spot",code);
		deleteTour("tour_stay",code);
		deleteTour("tour_food",code);
		
		try {
			conn=pool.getConnection();
			sql="delete from tour_city where code_city=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, code_city);
			if(pstmt.executeUpdate()==1) deleteCityCheck=true; 
		}catch (Exception e) {
			System.out.println("deleteTourCity() error");
			e.printStackTrace();
		}finally {
			pool.freeConnection(conn,pstmt);
		}		
		return deleteCityCheck;
	}
	
//5.TourFood/Spot/Stay 관련 메소드
	//5-1-1. TourFood/Spot/Stay 목록 가져오기 getTourList
	public List<TourDTO> getTourList(String tourType){
		return getTourList(tourType,"","");
	}
	
	public List<TourDTO> getTourList(String tourType,String code_city){
		return getTourList(tourType,code_city,"");
	}
	
	public List<TourDTO> getTourList(String tourType,String code_city,String code_nation) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="";
		List<TourDTO> tourList = new ArrayList();
		TourDTO tour = null;
		
		try {
			conn=pool.getConnection();
			sql =	  "select tour.code, tour.name "
					+ "from tour_city city, "+tourType +" tour "
					+ "where city.code_city=tour.code_city "+ "and city.code_nation like '%"+code_nation+"%'"
					+ "and tour.code_city like'%"+code_city+"%' order by tour.code asc";
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				tour = new TourDTO();
				tour.setCode(rs.getString("code"));
				tour.setName(rs.getString("name"));
				tourList.add(tour);
			}
		}catch (Exception e) {
			System.out.println("getTourList() error");
			e.printStackTrace();
		}finally {
			pool.freeConnection(conn,pstmt,rs);
		}		
		return tourList;
	}
	
	//5-2. option check한 TourFood/Spot/Stay 가져오기 getTour
	public TourDTO getTour(String tourType,String code) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="";
		TourDTO tour = null;
		
		try {
			conn=pool.getConnection();
			sql="select * from "+tourType +" where code like '%"+code+"%'";
			pstmt=conn.prepareStatement(sql);
			/*pstmt.setString(1, code);*/
			rs=pstmt.executeQuery();
			rs.next();
			tour = new TourDTO();
			tour.setCode(rs.getString("code"));
			tour.setName(rs.getString("name"));
			tour.setAddress(rs.getString("address"));
			tour.setDescription(rs.getString("description"));
			tour.setIcon(rs.getString("icon"));
			tour.setCode_city(rs.getString("code_city"));
		}catch (Exception e) {
			System.out.println("getTourList() error");
			e.printStackTrace();
		}finally {
			pool.freeConnection(conn,pstmt,rs);
		}		
		return tour;
	}	
		
	//5-3. TourFood/Spot/Stay 테이블에 추가(admin) insertTour
	public int insertTour(String tour_type, TourDTO tourToInsert) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sql="";
		int insertTourCheck=0; //삽입에 성공하면 1, 실패하면 0 반환
		try {
			conn=pool.getConnection();
			sql="insert into "+tour_type+" values(?,?,?,?,?,?)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, createCode(tour_type, tourToInsert.getCode_city()));
			pstmt.setString(2, tourToInsert.getName());
			pstmt.setString(3, tourToInsert.getAddress());
			pstmt.setString(4, tourToInsert.getCode_city());
			pstmt.setString(5, tourToInsert.getIcon());
			pstmt.setString(6, tourToInsert.getDescription());
			insertTourCheck=pstmt.executeUpdate();
		}catch (Exception e) {
			System.out.println("insertTour() error");
			e.printStackTrace();
		}finally {
			pool.freeConnection(conn,pstmt);
		}		
		return insertTourCheck;
	}
	
	//5-4. code 자동생성
	private String createCode(String tour_type, String code_city) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="";
		String precode=getCodeConnection(code_city);
		if(tour_type.equals("tour_spot")) precode+="P";
		else precode+=tour_type.substring(5,6).toUpperCase();
		String code=precode;
		
		try {
			conn=pool.getConnection();
			sql="select NVL(max(code),'000') from "+tour_type+" where code like '"+precode+"%'";
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				String maxCode = rs.getString(1);
				if(!maxCode.equals("000")) {
					System.out.println("변환해야함");
					System.out.println(maxCode);
					int maxtemp=Integer.parseInt(maxCode.substring(4,7))+1;
					maxCode=String.format("%03d", maxtemp);
				}
				code += maxCode;
			}
		}catch (Exception e) {
			System.out.println("createCode() error");
			e.printStackTrace();
		}finally {
			pool.freeConnection(conn,pstmt);
		}		
		return code;
	}
	
	/* 5-4-번외2. 국가코드,도시코드에 의한 여행정보들의 규칙에 따른 코드 반환	 *    
	 */
	private String getCodeConnection(String code_city) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="";
		String precode="";

		try {
			conn=pool.getConnection();
			sql="select precode from codeConnection where code_city =?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, code_city);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				precode=rs.getString("precode");
			}
		}catch (Exception e) {
			System.out.println("getCodeConnection error");
			e.printStackTrace();
		}finally {
			pool.freeConnection(conn,pstmt);
		}
		return precode;
	}
	
	//5-5. TourFood/Spot/Stay 테이블에 수정(admin) updateTour
	public int updateTour(String tour_type,TourDTO tourToUpdate) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sql="";
		int updateCheck=0; //삽입에 성공하면 1, 실패하면 0 반환
		
		try {
			conn=pool.getConnection();
			sql="update "+tour_type+" set name=?, address=?, icon=?, description=? where code=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, tourToUpdate.getName());
			pstmt.setString(2, tourToUpdate.getAddress());
			pstmt.setString(3, tourToUpdate.getIcon());
			pstmt.setString(4, tourToUpdate.getDescription());
			pstmt.setString(5, tourToUpdate.getCode());
			updateCheck=pstmt.executeUpdate();
		}catch (Exception e) {
			System.out.println("updateTour) error");
			e.printStackTrace();
		}finally {
			pool.freeConnection(conn,pstmt);
		}		
		return updateCheck;
	}
	
	//5-6. TourFood/Spot/Stay 테이블에 삭제(admin) deleteTour
	public boolean deleteTour(String tour_type,String code) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sql="";
		boolean deleteCheck=false; //삭제에 성공하면 true, 실패하면 false반환
		
		try {
			conn=pool.getConnection();
			sql="delete from "+tour_type+" where code like '"+code+"%'";
			pstmt=conn.prepareStatement(sql);
			if(pstmt.executeUpdate()==1) deleteCheck=true; 
		}catch (Exception e) {
			System.out.println("deleteTour() error");
			e.printStackTrace();
		}finally {
			pool.freeConnection(conn,pstmt);
		}		
		return deleteCheck;
	}
}
