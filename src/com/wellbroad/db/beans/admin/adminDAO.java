package com.wellbroad.db.beans.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.wellbroad.db.connection.DBConnectionManager;

public class adminDAO {

    private DBConnectionManager pool = null;
    
    public adminDAO(){
        try {
            pool = DBConnectionManager.getInstance();
            System.out.println("pool = " + pool);
        } catch (Exception e) {
            System.out.println("DBConnectable error = " + e);
        }
    }
    
    public int deleteMember(String id) {
        // 1.DB연결
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;// select passwd from member where id='nup'
        int x = -1;// 암호를 체크하기위한 변수(성공->1, 실패->0)
        String sql = "";// sql구문을 저장

        // 2.메서드 목적에 맞는 SQL구문
        try {
            con = pool.getConnection();
            con.setAutoCommit(false);// 트랜잭션 처리 시작
            sql = "select id from member where id=?";
            System.out.println("admindao deletemember에서 받은 id값 => " + id);
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, id);// 웹상에서 입력한 id값
            System.out.println("admindao deletemember에서 받은 id값 => " + id);
            rs = pstmt.executeQuery();
            // id값에 대한 DB에 저장된 암호를 찾았다면
            if (rs.next()) { 
                // db상의 암호==웹상에 입력한 암호
                pstmt = con.prepareStatement("delete from member where id=?");
                pstmt.setString(1, id);
                int delete = pstmt.executeUpdate();
                con.commit();
                System.out.println("delete 회원삭제 성공유무=>" + delete);
                x = 1;
            } else {
                x = 0;// 암호가 실패해서 삭제가 안되는 경우
            } // else { x=-1 } 데이터를 못 찾은 경우
        } catch (Exception e) {
            System.out.println("deleteMember() 메서드 실행오류=>" + e);
        } finally {// 메모리해제
            pool.freeConnection(con, pstmt, rs);
        }
        return x;
    }
}