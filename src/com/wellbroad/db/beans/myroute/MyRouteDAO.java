package com.wellbroad.db.beans.myroute;

import com.wellbroad.db.beans.tour.TourListDTO;
import com.wellbroad.db.connection.DBConnectionManager;

import java.sql.*;//DB
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MyRouteDAO {
	private DBConnectionManager pool = null;

	// 3.MyRoute 관련 메소드

	public MyRouteDAO() {
		
		try {
			pool=DBConnectionManager.getInstance();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// new DBConnectionFactory(); <- / -> connected() 함수 호출
	}

	// 국가이름목록 가져오기
	public List<String> getNationName() {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn=null;
		List<String> list = new ArrayList<String>();
		try {
			conn=pool.getConnection();
			String sql = "SELECT name FROM tour_nation GROUP BY name ORDER BY name";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				String name = rs.getString("name");
				list.add(name);
				System.out.println("getNationName()의 add" + name);
			}
			
		} catch (Exception e) {
			System.out.println("getNationName() 호출 오류" + e);
		}finally {
	         pool.freeConnection(conn,pstmt,rs);
	    } 

		return list;
	}

	// 도시이름 목록 가져오기
	public List<String> getCityName(String nationName) {
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<String> listcity = new ArrayList<String>();
		try {
			conn=pool.getConnection();
			String sql = "SELECT name FROM tour_city WHERE code_nation = (SELECT code_nation FROM tour_nation WHERE name='"
					+ nationName + "' )  ORDER BY name";

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				String name = rs.getString("name");

				listcity.add(name);
				System.out.println("getCityName()의 add" + name);
			}
			

		} catch (Exception e) {
			System.out.println("getCityName() 호출 오류" + e);
		}finally {
	         pool.freeConnection(conn,pstmt,rs);
	     } 

		return listcity;
	}

	// 관광지 이름목록 가져오기
	public List<TourListDTO> getTourName(String cityname) {
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<TourListDTO> listtour = new ArrayList<TourListDTO>();
		String where = "";
		try {
			conn=pool.getConnection();
			String sql0 = "SELECT code_city FROM tour_city WHERE name='" + cityname + "'";

			pstmt = conn.prepareStatement(sql0);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				where = rs.getString("code_city");

				System.out.println("where-> " + where);
			}

			// String sql1 = "SELECT name FROM tour_spot WHERE CODE_CITY = (SELECT code_city
			// FROM tour_city WHERE name='"+ cityname+ "' ) ORDER BY name";
			// String sql2 = "SELECT name FROM tour_food WHERE CODE_CITY = (SELECT code_city
			// FROM tour_city WHERE name='"+ cityname+ "' ) ORDER BY name";
			// String sql3 = "SELECT name FROM tour_stay WHERE CODE_CITY = (SELECT code_city
			// FROM tour_city WHERE name='"+ cityname+ "' ) ORDER BY name";
			//
			String sql1 = "SELECT icon,code,name,description FROM tour_spot WHERE CODE_CITY = '" + where
					+ "' ORDER BY name";

			pstmt = conn.prepareStatement(sql1);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				String icon = rs.getString("icon");
				String name = rs.getString("name");
				String code = rs.getString("code");
				String description = rs.getString("description");

				listtour.add(new TourListDTO(icon, code, name, description));
				System.out.println("getTourName()의 add" + name);
			}

			String sql2 = "SELECT icon,code,name,description FROM tour_food WHERE CODE_CITY = '" + where
					+ "' ORDER BY name";

			pstmt = conn.prepareStatement(sql2);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				String icon = rs.getString("icon");
				String name = rs.getString("name");
				String code = rs.getString("code");
				String description = rs.getString("description");

				listtour.add(new TourListDTO(icon, code, name, description));
				System.out.println("getTourName()의 add" + name);
			}

			String sql3 = "SELECT icon,code,name,description FROM tour_stay WHERE CODE_CITY = '" + where
					+ "' ORDER BY name";

			pstmt = conn.prepareStatement(sql3);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				String icon = rs.getString("icon");
				String name = rs.getString("name");
				String code = rs.getString("code");
				String description = rs.getString("description");

				listtour.add(new TourListDTO(icon, code, name, description));
				System.out.println("getTourName()의 add" + name);
			}
		

		} catch (Exception e) {
			System.out.println("getTourName() 호출 오류" + e);
		}finally {
	         pool.freeConnection(conn,pstmt,rs);
	    } 

		return listtour;
	}

	// 코스 저장하기
	public void InsertMyRoute(String userid, String course, String coursename, String startdate, String enddate, int days,
			String iconname, String icon, String nationname,String cityname,String tourname) {
		Connection conn=null;
		PreparedStatement pstmt = null;
		int rs = 0;
		
		String sql = "insert into myroute (NUM, ID, COURSE, COURSENAME, STARTDATE, ENDDATE , DAY, ICON_NAME, ICON, NATIONNAME, CITYNAME, TOURNAME)  "
				+ "values (MYROUTENUM.NEXTVAL ,'"+ userid +"', " + Integer.parseInt(course)  + ", '" + coursename + "', TO_DATE('" + startdate + "', 'YYYYMMDD'),TO_DATE('" + enddate + "', 'YYYYMMDD'),"
				+ days + ", '" + iconname + "', '" + icon + "', '"+nationname+"','"+cityname+"','"+tourname+"')";
	
		try {
			conn=pool.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeUpdate();

			if(rs == 1) {
				System.out.println( coursename+"-"+days+"일 일정이"+" 성공적으로 저장되었습니다.");
			}
			
			pstmt.close();
		} catch (Exception e) {
			System.out.println("InsertMyRoute() 호출 오류" + e);
		}finally {
	         pool.freeConnection(conn,pstmt);
	    } 
	
		

	}

	// 3-1. MyRoute 목록 가져오기 getMyRouteCourseList
	// 3-2. MyRoute 코스1(또는2또는3) 가져오기 getMyRouteCourse
	public MyRouteDTO getMyRouteCourse(String userid, String course){
		Connection conn=null;
		MyRouteDTO myroute = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			String sql = "select * from myroute where id='" + userid + "' and course=" + course + " order by day asc";
			conn=pool.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			myroute = new MyRouteDTO();
			while (rs.next()) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date startdate = dateFormat.parse(rs.getString("startdate"));
				Date enddate = dateFormat.parse(rs.getString("enddate"));
				
				myroute = new MyRouteDTO(rs.getInt("num"), rs.getString("id"),
						rs.getInt("course"), rs.getString("coursename"),
						dateFormat.format(startdate),
						dateFormat.format(enddate), 
						rs.getInt("day"), rs.getString("icon"),
						rs.getString("icon_name"),
						rs.getString("nationname"),
						rs.getString("cityname"),
						rs.getString("tourname"));
				
				
			}
			if(myroute != null) {
				System.out.println("getMyRouteCourse()의 coursename - " + myroute.getCoursename());
			}
			
		} catch (Exception e) {
			System.out.println("getMyRouteCourse() 호출 오류" + e);
		}finally {
	         pool.freeConnection(conn,pstmt,rs);
	     } 
		
		return myroute;
	}
	
	public void updateMyRoute(int num, String userid,String course, String coursename, String startdate, String enddate, int days,
			String iconname, String icon, String nationname,String cityname,String tourname) {
		Connection conn=null;
		PreparedStatement pstmt = null;
		int rs = 0;
	
//		String sql = "update myroute set (COURSENAME, STARTDATE, ENDDATE , DAY, ICON_NAME, ICON, NATIONNAME, CITYNAME, TOURNAME) "
//				+"= ('" + coursename + "', TO_DATE('" + startdate + "', 'YYYYMMDD'),TO_DATE('" + enddate + "', 'YYYYMMDD'),"
//				+ days + ", '" + iconname + "', '" + icon + "', '"+nationname+"','"+cityname+"','"+tourname+"')"
//						+ " where NUM = "+ num;
		String sql = "update myroute set COURSENAME ='"+coursename+"' , STARTDATE = '"+startdate+"', ENDDATE = '"+enddate+"', DAY = "+days+", "+
					 "ICON_NAME = '"+iconname+"' , ICON ='"+icon+"', NATIONNAME = '"+nationname+"', CITYNAME = '"+cityname+"', TOURNAME = '"+tourname+"' where NUM = "+ num;
		System.out.println(sql);
		
		
		try {
			conn=pool.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeUpdate();

			if(rs == 1) {
				System.out.println( coursename+"-"+days+"일 일정이"+" 성공적으로 수정되었습니다.");
			}
			
			
		} catch (Exception e) {
			System.out.println("updateMyRoute() 호출 오류" + e);
		}finally {
	         pool.freeConnection(conn,pstmt);
	    } 
		
		
	}
	// 3-3. MyRoute 테이블에 코스 추가 addMyRouteCourse
	// 3-4. MyRoute 테이블에 코스 수정 editMyRouteCourse
	// 3-5. MyRoute 테이블에 코스 삭제 removeMyRouteCourse

	public void deleteMyroute(int num) {
		// TODO Auto-generated method stub
		Connection conn=null;
		PreparedStatement pstmt = null;
		int rs = 0;
		
		try {
			conn=pool.getConnection();
			String sql = "delete from myroute where num=" + num;
			System.out.println(sql);
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeUpdate();
			
			
			if(rs != 0) {
				System.out.println("정상적으로 삭제 되었습니다! - " + num);
			}
			
			
		} catch (Exception e) {
			System.out.println("deleteMyroute() 호출 오류" + e);
		}finally {
	         pool.freeConnection(conn,pstmt);
	      } 
		
		
	}

	// 4.MyRoutePast 관련 메소드 (MyRoutePast와 형태가 같기 때문에 따로 MyRoutePastDTO 생성 안 했음)
	// =>게시판처럼 뿌릴거임=>할꺼야에 존재 =>따라서 개발 안 해도 됨

}
