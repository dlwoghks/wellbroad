package com.wellbroad.db.beans.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.wellbroad.db.connection.DBConnectionManager;

public class LoginDAO {
//1.연결하고자하는 클래스 객체 선언
   private DBConnectionManager pool = null;
   
//2.생성자 (연결하고자하는 클래스객체 생성)
   public LoginDAO() {
      try {
         pool=DBConnectionManager.getInstance();
         System.out.println("pool = "+pool);
      } catch(Exception e) {
         System.out.println("DBConnectable error = "+e);
      }
   }
   
//3.login 관련 메소드
   public int loginWhoever(String id, String password) { 
       int loginSuccess=0;//0:로그인 실패 , 1:회원 , 2:관리자
       
       if(loginAdmin(id,password)) {
           loginSuccess = 2;
       }else {
           if(loginMember(id,password)) {
               loginSuccess = 1;
           }
       }
       return loginSuccess;
   }
   //3-1. member/admin 로그인 (메소드명 마음에 안듦;;;;) d
   private boolean loginMember(String id, String password) {
      Connection conn=null;
      PreparedStatement pstmt=null;
      ResultSet rs=null;
      String sql="";
      boolean logincheck=false; //로그인 성공
      
      System.out.println(id+","+password);
      try {
         conn=pool.getConnection();
         sql="select id, password from member where id=? and password=?";
         pstmt=conn.prepareStatement(sql);
         pstmt.setString(1, id);
         pstmt.setString(2, password);
         rs=pstmt.executeQuery();
         logincheck=rs.next();
         System.out.println(logincheck);
      }catch (Exception e) {
         System.out.println("loginWhoever() error");
         e.printStackTrace();
      }finally {
         pool.freeConnection(conn,pstmt,rs);
      }      
      return logincheck;
   }
   
   private boolean loginAdmin(String id, String password) {
       Connection conn=null;
       PreparedStatement pstmt=null;
       ResultSet rs=null;
       String sql="";
       boolean logincheck=false; //로그인 성공
       
       System.out.println(id+","+password);
       try {
           conn=pool.getConnection();
           sql="select id, password from admin where id=? and password=?";
           pstmt=conn.prepareStatement(sql);
           pstmt.setString(1, id);
           pstmt.setString(2, password);
           rs=pstmt.executeQuery();
           logincheck=rs.next();
           System.out.println(logincheck);
       }catch (Exception e) {
           System.out.println("loginAdmin() error");
           e.printStackTrace();
       }finally {
           pool.freeConnection(conn,pstmt,rs);
       }       
       return logincheck;
   }
   
 
   
   //3-2. ID찾기 findMyId
   public String findMyId(String name, String birth) {
       Connection conn=null;
       PreparedStatement pstmt=null;
       ResultSet rs=null;
       String sql="";
       String userId="";
       
       System.out.println(name+","+birth);
       try {
          conn=pool.getConnection();
          sql="select id from member where name=? and birth=?";
          pstmt=conn.prepareStatement(sql);
          pstmt.setString(1, name);
          pstmt.setString(2, birth);
          rs=pstmt.executeQuery();
          while (rs.next()) {
              userId = rs.getString(1);
              System.out.println(userId);
          }
          System.out.println(userId);
       }catch (Exception e) {
          System.out.println("findMyId() error");
          e.printStackTrace();
       }finally {
          pool.freeConnection(conn,pstmt,rs);
       }      
       return userId;
    }
   //3-3. 비밀번호찾기 findMyPassword
   public String findMyPassword(String id, String birth) {
       Connection conn=null;
       PreparedStatement pstmt=null;
       ResultSet rs=null;
       String sql="";
       String userPassword="";
       
       System.out.println(id+","+birth);
       try {
          conn=pool.getConnection();
          sql="select password from member where id=? and birth=?";
          pstmt=conn.prepareStatement(sql);
          pstmt.setString(1, id);
          pstmt.setString(2, birth);
          rs=pstmt.executeQuery();
          while (rs.next()) {
              userPassword = rs.getString(1);
              System.out.println(userPassword);
          }
       }catch (Exception e) {
          System.out.println("findMyPassword() error");
          e.printStackTrace();
       }finally {
          pool.freeConnection(conn,pstmt,rs);
       }      
       return userPassword;
    }
}