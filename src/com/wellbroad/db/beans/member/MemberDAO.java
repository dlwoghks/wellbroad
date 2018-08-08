package com.wellbroad.db.beans.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.wellbroad.db.connection.DBConnectionManager;

public class MemberDAO {
    // 1.연결하고자하는 클래스 객체 선언
    private DBConnectionManager pool = null;

    // 2.생성자 (연결하고자하는 클래스객체 생성)
    public MemberDAO() {
        try {
            pool = DBConnectionManager.getInstance();
            System.out.println("pool = " + pool);
        } catch (Exception e) {
            System.out.println("DBConnectable error = " + e);
        }
    }

    // 3.Member 관련 메소드
    // 3-1. Member 목록 가져오기(admin) getMemberList
    public ArrayList<MemberDTO> getMemberList() {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        MemberDTO member = null;
        String sql = "";
        ArrayList<MemberDTO> memberList = new ArrayList<MemberDTO>();

        try {
            con = pool.getConnection();
            sql = "select * from member";
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            
            while(rs.next()) {
                member = new MemberDTO();
                member.setId(rs.getString("id"));
                member.setPassword(rs.getString("password"));
                member.setName(rs.getString("name"));
                member.setBirth(rs.getString("birth"));
                member.setGender(rs.getString("gender"));
                member.setMail(rs.getString("mail"));
                member.setMailagree(rs.getString("mailagree"));
                member.setPhone(rs.getString("phone"));
                memberList.add(member);
            }
        } catch (Exception e) {
            System.out.println("getMemberList() 실행 중 에러유발=>" + e);
        } finally {
            pool.freeConnection(con, pstmt, rs);
        }
        return memberList;
    }

    // 회원정보 가져오기
    public MemberDTO getMember(String memberId) {

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        MemberDTO member = null;
        String sql = "";

        try {
            con = pool.getConnection();
            sql = "select * from member where id=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, memberId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                member = new MemberDTO();
                member.setId(rs.getString("id"));
                member.setPassword(rs.getString("password"));
                member.setName(rs.getString("name"));
                member.setBirth(rs.getString("birth"));
                member.setGender(rs.getString("gender"));
                member.setMail(rs.getString("mail"));
                member.setMailagree(rs.getString("mailagree"));
                member.setPhone(rs.getString("phone"));
            }
        } catch (Exception e) {
            System.out.println("getMember() 실행 중 에러유발=>" + e);
        } finally {
            pool.freeConnection(con, pstmt, rs);
        }
        return member;
    }

    // 3-2. Member 테이블에 추가 registerMember
    public boolean registerMember(MemberDTO member) {

        Connection con = null;
        PreparedStatement pstmt = null;
        // ResultSet rs=null ->insert이기때문
        boolean check = false;// 회원가입 성공유무
        String sql = "";// sql구문 저장

        System.out.println("MemberDAO registerMember : " + member.getId());
        try {
            con = pool.getConnection();
            // 트랜잭션처리 시작구문
            con.setAutoCommit(false);
            // -----입력을 받을때는 DTO->각각의 필드에 값을 저장->Getter Method
            sql = "insert into member values(?,?,?,?,?,?,?,?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, member.getId());
            pstmt.setString(2, member.getPassword());
            pstmt.setString(3, member.getName());
            pstmt.setString(4, member.getBirth());
            pstmt.setString(5, member.getGender());
            pstmt.setString(6, member.getMail());
            pstmt.setString(7, member.getMailagree());
            pstmt.setString(8, member.getPhone());

            int insert = pstmt.executeUpdate();// 1->성공, 0->실패->메모리상 저장
            if (insert == 1) {
                sql = "insert into member_login values(?,?)";
                pstmt = con.prepareStatement(sql);
                pstmt.setString(1, member.getId());
                pstmt.setString(2, member.getPassword());
                pstmt.execute();
            }
            // 메모리상에 저장된 값이=>실제 테이블에 저장(동기화)
            con.commit();
            System.out.println("회원가입 성공유무(insert)=>" + insert);
            if (insert > 0) {
                check = true;
            }
        } catch (Exception e) {
            System.out.println("registerMember() 실행 에러유발=>" + e);
        } finally {
            pool.freeConnection(con, pstmt);
        }
        return check;
    }

    // 3-2-1. Member 중복ID체크
    public boolean checkDuplicationId(String id) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "";
        boolean check = false; // ID중복 여부
        try {
            conn = pool.getConnection();
            sql = "select id from admin where id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            check = rs.next();
            if(check == false) {
                sql = "select id from member where id=?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, id);
                rs = pstmt.executeQuery();
                check = rs.next();
            }
        } catch (Exception e) {
            System.out.println("checkDuplicationId() error");
            e.printStackTrace();
        } finally {
            pool.freeConnection(conn, pstmt, rs);
        }
        return check;
    }

    // 3-3. Member 테이블에 수정 updateMember
    public boolean updateMember(MemberDTO member) {

        System.out.println("MemberDAO => member.getId()=>" + member.getId());
        // ------------------------------------------------------------------------
        Connection con = null;
        PreparedStatement pstmt = null;
        // ResultSet rs=null ->insert이기때문
        boolean check = false;// 회원수정 성공유무
        String sql = "";// sql구문 저장

        try {
            con = pool.getConnection();
            // 트랜잭션처리 시작구문
            con.setAutoCommit(false);// 수정

            sql = "update member set password=?, mail=?, phone=? , mailagree=? where id=?";

            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, member.getPassword());
            pstmt.setString(2, member.getMail());
            pstmt.setString(3, member.getPhone());
            pstmt.setString(4, member.getMailagree());
            pstmt.setString(5, member.getId());
            int update = pstmt.executeUpdate();// 1->성공, 0->실패->메모리상 저장
            // 메모리상에 저장된 값이=>실제 테이블에 저장(동기화)
            con.commit();
            System.out.println("회원수정 성공유무(update)=>" + update);
            if (update == 1) {
                check = true; // 수정성공
            }
        } catch (Exception e) {
            System.out.println("updateMember() 실행 에러유발=>" + e);
        } finally {
            pool.freeConnection(con, pstmt);
        }
        return check; // true or false
    }

    // 3-4. Member 테이블에서 삭제 deleteMember
    public int deleteMember(String id, String password) {

        // 1.DB연결
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;// select passwd from member where id='nup'
        int x = -1;// 암호를 체크하기위한 변수(성공->1, 실패->0)
        String dbpasswd = "";// id값에 해당하는 DB의 암호값저장
        String sql = "";// sql구문을 저장

        // 2.메서드 목적에 맞는 SQL구문
        try {
            con = pool.getConnection();
            con.setAutoCommit(false);// 트랜잭션 처리 시작
            sql = "select password from member where id=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, id);// 웹상에서 입력한 id값
            rs = pstmt.executeQuery();
            // id값에 대한 DB에 저장된 암호를 찾았다면
            if (rs.next()) {
                dbpasswd = rs.getString("password");
                System.out.println("dbpasswd=>" + dbpasswd);
                // db상의 암호==웹상에 입력한 암호
                if (dbpasswd.equals(password)) {
                    pstmt = con.prepareStatement("delete from member where id=?");
                    pstmt.setString(1, id);
                    int delete = pstmt.executeUpdate();
                    con.commit();
                    System.out.println("delete 회원탈퇴 성공유무=>" + delete);
                    x = 1;
                } else {
                    x = 0;// 암호가 실패해서 삭제가 안되는 경우
                }
            } // else { x=-1 } 데이터를 못 찾은 경우
        } catch (Exception e) {
            System.out.println("deleteMember() 메서드 실행오류=>" + e);
        } finally {// 메모리해제
            pool.freeConnection(con, pstmt, rs);
        }
        return x;
    }
    
//이메일 형식 맞추기.
    public static boolean isValidEmail(String mail) {
        boolean err = false;
        
        String regex = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";   
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(mail);
        if(m.matches()) {
         err = true; 
        }
        return err;
       }
    
}