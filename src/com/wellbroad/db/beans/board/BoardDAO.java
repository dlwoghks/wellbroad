package com.wellbroad.db.beans.board;

import java.io.IOException;
//DB에 접속해서 웹상에서 호출할 메서드를 선언->DAO
//DB
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.wellbroad.db.connection.DBConnectionManager;
import com.wellbroad.db.beans.board.*;
import java.sql.Date;

public class BoardDAO {
	private DBConnectionManager pool = null;// 1.연결할 클래스 객체선언
	// 2.생성자를 통해서 자연스럽게 연결=>의존성때문

	public BoardDAO() {
		try {
			pool = DBConnectionManager.getInstance();
			System.out.println("pool=>" + pool);
		} catch (Exception e) {
			System.out.println("Error접속 오류=>" + e);
		}
	}

	// 3.페이징 처리(페이지별로 화면에 보여주는 레코드수를 조절)
	// 1)전체 레코드수를 구해와야 한다.=>select count(*) from board;
	public int getArticleCountNotice() {

		// 1.DB연결
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;

		// 2.메서드 목적에 맞는 SQL구문
		try {
			con = pool.getConnection();
			System.out.println("con=>" + con);
			String sql = "select count(*) from noticeboard";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			// 추가
			if (rs.next()) { // 레코드갯수가 한개이상이라면
				x = rs.getInt(1);// 필드명이 없다->select ~from사이의 필드순서로 지정
				System.out.println("총레코드수(x)=>" + x);
			}
		} catch (Exception e) {
			System.out.println("getArticleCountNotice() 메서드 실행오류=>" + e);
		} finally {// 메모리해제
			pool.freeConnection(con, pstmt, rs);
		}
		return x;
	}

	// (1)게시판의 레코드수를 검색어에 따른 메서드 작성(검색분야,검색어)
	public int getArticleSearchCountNotice(String search, String searchtext) {
		// 1.DB연결
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;
		// 추가
		String sql = "";// 저장시킬 SQL구문
		// 2.메서드 목적에 맞는 SQL구문
		try {
			con = pool.getConnection();
			System.out.println("con=>" + con);
			// ---검색어를 입력하지 않은 경우(검색분야 선택)X------
			if (searchtext == null || searchtext == "") {
				sql = "select count(*) from noticeboard";
			} else { // 검색분야(제목,작성자,제목+본문)->검색했다면
				if (search.equals("title_content")) {
					sql = "select count(*) from noticeboard  where title like '%" + searchtext
							+ "%' or content like  '%" + searchtext + "%' ";

				} else { // 제목 or 작성자->매개변수를 이용해서 하나의 sql통합
					sql = "select count(*) from noticeboard  where " + search + " like '%" + searchtext + "%' ";
					// sql="select count(*) from board where writer like '%"+searchtext+"%' ";
					// sql="select count(*) from board where subject like '%"+searchtext+"%' ";
				}
			}
			System.out.println("getArticleSearchCountNotice 검색어 sql=>" + sql);
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			// 추가
			if (rs.next()) { // 레코드갯수가 한개이상이라면
				x = rs.getInt(1);// 필드명이 없다->select ~from사이의 필드순서로 지정
				System.out.println("총레코드수(x)=>" + x);
			}
		} catch (Exception e) {
			System.out.println("getArticleSearchCountNotice() 메서드 실행오류=>" + e);
		} finally {// 메모리해제
			pool.freeConnection(con, pstmt, rs);
		}
		return x;
	}

	// 2)글목록보기에 대한 메서드구현(범위)
	// select * from board order by ref desc,re_step asc limit ?,?
	// 1.레코드의 시작번호 2.웹상에 보여줄 레코드갯수
	public List<NoticeBoardDTO> getArticlesNotice(int start, int end) { // 1,10,11,10,20,10

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;// select
		List<NoticeBoardDTO> articleList = null;// ArrayList articleList=null; //10개를 담을 객체선언

		// 2.메서드 목적에 맞는 SQL구문
		try {
			con = pool.getConnection();
			// 가장 최신의글을 내림차순으로 정렬하되 re_step값은 오름차순으로 정렬
			// (몇번째 레코드를 기준으로 몇개의 레코드 범위)
			String sql = "select * from noticeboard where rownum>=? and rownum <=? order by ref desc, re_level asc";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, start);// mysql이 내부적으로 레코드순번을 0부터 시작
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();
			// 추가->do~while->기존의 데이터는 그대로 유지=>신규글이 추가=>같이출력
			if (rs.next()) { // 레코드갯수가 한개라도 존재한다면
				// articleList = new List();
				// articleList=new 자식클래명();
				articleList = new ArrayList(end);// 초기생성값을 미리 부여end(크기 지정)
				do { // 무조건 기존의 데이터 추가+새로추가
					NoticeBoardDTO article = new NoticeBoardDTO();// MemberDTO mem=new MemberDTO
					article.setNum(rs.getInt("num"));// 번호
					article.setAdmin_id(rs.getString("admin_id"));
					article.setIp(rs.getString("ip"));// 글쓴이의 ip
					article.setTitle(rs.getString("title"));// 제목
					article.setContent(rs.getString("content"));// 글내용
					article.setDay(rs.getTimestamp("day"));
					article.setReadcount(rs.getInt("readcount"));// 조회수
					articleList.add(article);// 최종 웹상에서 필드별로 출력(Getter)
				} while (rs.next());
			} // if(rs.next()){}
		} catch (Exception e) {
			System.out.println("getArticlesNotice() 메서드 실행오류=>" + e);
		} finally {// 메모리해제
			pool.freeConnection(con, pstmt, rs);
		}
		return articleList;
	}

	// (2)검색어에 따른 레코드의 범위 지정에 대한 메서드 추가
	public List<NoticeBoardDTO> getNoticeBoardArticles(int start, int end, String search, String searchtext) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;// select
		List<NoticeBoardDTO> articleList = null;// ArrayList articleList=null; //10개를 담을 객체선언
		// 추가
		String sql = "";
		// 2.메서드 목적에 맞는 SQL구문
		try {
			con = pool.getConnection();
			// --------------------------------------------------------------------
			if (search == null || search == "") {
				sql = "select x.* from(select rownum rnum, a.* from(\r\n"
						+ "select * from noticeboard order by num desc) a\r\n" + "where rownum <=?) x\r\n"
						+ "where x.rnum >=?\r\n";
			} else {// 제목,작성자->검색범위에 있는것만 가장 최신의 글로 정렬~
				if (search.equals("title_content")) {
					sql = "select x.* from(select rownum rnum, a.*\r\n" + "from(\r\n"
							+ "select * from noticeboard order by num desc) a\r\n" + "where title like '%" + searchtext
							+ "%' or content like '%" + searchtext + "%' \r\n" + "and rownum <=?) x\r\n"
							+ "where x.rnum >=?";

				} else { // 제목 or 작성자->매개변수를 이용해서 하나의 sql통합
					sql = "select x.* from(select rownum rnum, a.*\r\n" + "from(\r\n"
							+ "select * from noticeboard order by num desc) a\r\n" + "where " + search + " like '%"
							+ searchtext + "%' and rownum<=?) x \r\n" + "where x.rnum >=?";
				}
			}
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, end);
			pstmt.setInt(2, start);// mysql이 내부적으로 레코드순번을 0부터 시작

			rs = pstmt.executeQuery();
			// 추가->do~while->기존의 데이터는 그대로 유지=>신규글이 추가=>같이출력
			if (rs.next()) { // 레코드갯수가 한개라도 존재한다면
				articleList = new ArrayList(end);// end갯수 만큼 데이터를 담기위해서
				do { // 무조건 기존의 데이터 추가+새로추가
					NoticeBoardDTO article = new NoticeBoardDTO();// MemberDTO mem=new MemberDTO
					article.setNum(rs.getInt("num"));// 번호
					article.setAdmin_id(rs.getString("admin_id"));
					article.setIp(rs.getString("ip"));// 글쓴이의 ip
					article.setTitle(rs.getString("title"));// 제목
					article.setContent(rs.getString("content"));// 글내용
					article.setDay(rs.getTimestamp("day"));
					article.setReadcount(rs.getInt("readcount"));// 조회수
					articleList.add(article);// 최종 웹상에서 필드별로 출력(Getter)
				} while (rs.next());
			} // if(rs.next()){}
		} catch (Exception e) {
			System.out.println("getNoticeBoardArticles() 메서드 실행오류=>" + e);
		} finally { // 메모리해제
			pool.freeConnection(con, pstmt, rs);
		}
		return articleList;
	}

	// (3)페이징 처리를 재조정해주는 메서드 작성(ListAction클래스 너무 복잡)
	// 1.화면에 보여주는 페이지번호 2.화면에 출력할 레코드갯수
	public Hashtable pageList(String pageNum, int count) {

		// 페이징 처리결과를 저장할 Hashtable 또는 HashMap객체를 선언
		Hashtable<String, Integer> pgList = new Hashtable<String, Integer>();
		// -----------ListAction부분의 페이징처리 계산부분을 복사(hashtable에 저장)---------------
		int pageSize = 10;// int numPerPage->페이지당 보여주는 게시물수
		int blockSize = 10;// int pagePerBlock->블럭당 보여주는 페이지수

		// 게시판을 맨 처음 실행시킬 경우 초기화 설정부분->1페이지(pageNum)
		if (pageNum == null) {// 맨 처음에는 선택한 적이 없기때문에 null
			pageNum = "1";// 1
		}
		// 1.nowPage=>currentPage
		int currentPage = Integer.parseInt(pageNum);// "1"->1
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;
		int number = 0;// 4.beginPerPage->페이지별로 맨 처음에 나오는 시작 게시물번호
		number = count - (currentPage - 1) * pageSize;

		System.out.println("현재 레코드수(count)=>" + count + ",number=>" + number);

		// 2.총페이지수,시작,종료페이지 계산
		int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
		// 2.시작페이지,끝페이지
		int startPage = 0;
		// 10의 배수인지 아닌지(경계선)
		if (currentPage % blockSize != 0) {// 1~9,11~19,21~22
			startPage = currentPage / blockSize * blockSize + 1;
		} else { // 10,20,30,40
					// ((10/10)-1)*10+1=1
			startPage = ((currentPage / blockSize) - 1) * blockSize + 1;
		}
		int endPage = startPage + blockSize - 1;// 1+10-1=10
		System.out.println("pgList메서드의 startPage=" + startPage + ",endPage=" + endPage);
		// 블럭별로 구분해서 링크걸어서 출력
		if (endPage > pageCount)
			endPage = pageCount; // 마지막페이지=총페이지수
		// ListAction(pageNum,count)=>pgList()처리결과 ->ListAction->list.jsp
		pgList.put("pageSize", pageSize);// <->pgList.get("pageSize")
		pgList.put("blockSize", blockSize);
		pgList.put("currentPage", currentPage);
		pgList.put("startRow", startRow);
		pgList.put("endRow", endRow);
		pgList.put("count", count);
		pgList.put("number", number);
		pgList.put("startPage", startPage);
		pgList.put("endPage", endPage);
		pgList.put("pageCount", pageCount);

		// ------------------------
		return pgList; // ListAction-->list.jsp
	}

	// 3.insertArticle()->글쓰기 및 답변글 쓰기
	// insert into~ ->내부에서 성공유무 체크,->list.jsp로 자동으로 이동
	public void insertArticleNotice(NoticeBoardDTO article) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;// select ->select max(num) from board->+1(num구함)

		// -------------신규글인지 답변글인지 구분----------------------
		// int num = article.getNum();// 0->신규글 0아닌 경우->답변글
		int number = 0;// 테이블에 입력할 게시물번호를 저장할 변수
		String sql = "";// sql구문 저장

		// 2.메서드 목적에 맞는 SQL구문
		try {
			con = pool.getConnection();
			sql = "select max(num) from noticeboard";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			// 추가
			if (rs.next()) { // 레코드갯수가 한개이상이라면
				number = rs.getInt(1) + 1;
			} else {
				number = 1;// 게시물 번호를 1로 설정
			}
			pstmt.close();
			System.out.println("insertArticleNotice의 number=>" + number);
			// 만약에 답변글이라면 ->num=0 신규글이기 때문에

			// 12개의 필드->num,reg_date,readcount(생략)->sysdate,now()<-?
			// num(자동입력),readcount(디폴트 기능때문에)->0이 들어가게 설정
			sql = "insert into noticeboard(num, admin_id, ip, title, content, day, readcount) values(?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, number);
			pstmt.setString(2, article.getAdmin_id());
			pstmt.setString(3, article.getIp());
			pstmt.setString(4, article.getTitle());//
			pstmt.setString(5, article.getContent());// 작성 날짜
			pstmt.setTimestamp(6, (Timestamp) article.getDay());// 날짜
			pstmt.setInt(7, article.getReadcount());// 접속자의 ip주소
			// 실행
			int insert = pstmt.executeUpdate();
			System.out.println("게시판의 글쓰기 성공유무(insert)=>" + insert);
		} catch (Exception e) {
			System.out.println("insertArticleNotice() 메서드 실행오류=>" + e);
		} finally {// 메모리해제
			pool.freeConnection(con, pstmt, rs);
		}
	}

	// 4.글 상세보기->조회수도 증가
	// update board set readcount=readcount+1 where num=?
	// select * from board where num=?
	public NoticeBoardDTO getArticleNotice(int num) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;// select * from board where num=?
		NoticeBoardDTO article = null;// 게시물번호에 해당하는 레코드 한개

		// 2.메서드 목적에 맞는 SQL구문
		try {
			con = pool.getConnection();

			String sql = "update noticeboard set readcount=readcount+1 where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			int update = pstmt.executeUpdate();
			System.out.println("조회수 증가유무(update)=>" + update);// 1
			// pstmt.close();
			sql = "select * from noticeboard where  num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();

			if (rs.next()) { // 레코드갯수가 한개라도 존재한다면
				article = makeArticleFromResultNotice(rs);
			}
		} catch (Exception e) {
			System.out.println("getArticleNotice() 메서드 실행오류=>" + e);
		} finally {// 메모리해제
			pool.freeConnection(con, pstmt, rs);
		}
		return article;
	}

	// ----중복된 레코드한개를 담을 수 있는 메서드를 따로 처리해주는
	// 메서드작성-------------------------------------------------------------
	// 이 메서드는 웹상에서 호출되는 메서드X=>이 클래스내부에서만 사용(private)
	// private MemberDTO makeMemberFromResult(ResultSet rs){}
	private NoticeBoardDTO makeArticleFromResultNotice(ResultSet rs) throws Exception {
		NoticeBoardDTO article = new NoticeBoardDTO();// MemberDTO mem=new MemberDTO
		article.setNum(rs.getInt("num"));// 번호
		article.setAdmin_id(rs.getString("admin_id"));
		article.setIp(rs.getString("ip"));// 글쓴이의 ip
		article.setTitle(rs.getString("title"));// 제목
		article.setContent(rs.getString("content"));// 글내용
		article.setDay(rs.getTimestamp("day"));
		article.setReadcount(rs.getInt("readcount"));// 조회수
		return article;
	}

	// 5.글수정을 하기위한 게시물번호에 해당하는 레코드를 찾는 메서드
	// ->updateGetArticle(int num)->select * from board where num=?
	public NoticeBoardDTO updateGetArticleNotice(int num) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;// select * from board where num=?
		NoticeBoardDTO article = null;// 게시물번호에 해당하는 레코드 한개

		// 2.메서드 목적에 맞는 SQL구문
		try {
			con = pool.getConnection();
			String sql = "select * from noticeboard where  num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) { // 레코드갯수가 한개라도 존재한다면
				article = makeArticleFromResultNotice(rs);
			} // if(rs.next()){}
		} catch (Exception e) {
			System.out.println("updateGetArticleNotice() 메서드 실행오류=>" + e);
		} finally {// 메모리해제
			pool.freeConnection(con, pstmt, rs);
		}
		return article;
	}

	// 6.글수정시켜주는 메서드->updateArticle(BoardDTO article){}
	// =>글쓰기(insertArticle()와 기능이 동일(데이터를 저장)->인증->수정
	public int updateArticleNotice(NoticeBoardDTO article) { // (int passwd)

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;// passwd를 조회할 select필요
		String admin_id = null;// db에서 찾은 암호를 저장
		String sql = "";// sql구문 저장
		int x = -1;// 게시물의 수정 성공 유무

		// 2.메서드 목적에 맞는 SQL구문
		try {
			con = pool.getConnection();
			sql = "select admin_id from noticeboard where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, article.getNum()); // 수정하고자하는 데이터에서 암호분리
			rs = pstmt.executeQuery();
			// 추가
			if (rs.next()) { // 게시물번호에 해당하는 암호를 찾았다면
				admin_id = rs.getString("admin_id");
				System.out.println("id=>" + admin_id);
				// db상의 암호=웹상에 입력한 암호가 맞다면
				if (admin_id.equals(article.getAdmin_id())) {

					sql = "update noticeboard set title=?, content=? where num=? ";
					pstmt.close();
					pstmt = con.prepareStatement(sql);
					// pstmt.setString(1, article.getIp());
					pstmt.setString(1, article.getTitle());// 암호
					pstmt.setString(2, article.getContent());// 글내용
					// pstmt.setTimestamp(2, (Timestamp) article.getDay());// 게시물 번호
					pstmt.setInt(3, article.getNum());// 게시물 번호
					// 실행
					int update = pstmt.executeUpdate();

					System.out.println("게시판의 글수정 성공유무(update)=>" + update);
					x = 1;
				} else {
					x = 0;// 수정실패->암호가 틀린경우
				}
			} // if(rs.next())=>암호가 존재한다면
		} catch (Exception e) {
			System.out.println("updateArticle() 메서드 실행오류=>" + e);
		} finally {// 메모리해제
			pool.freeConnection(con, pstmt, rs);
		}
		return x;// 1->수정성공, 0->수정실패
	}

	// 글삭제 시켜주는 메서드=>회원탈퇴(삭제)=>암호를 물어본다.(deletePro.jsp)
	// select passwd from board where num=? ->직접
	// delete from board where num=?
	public int deleteArticleNotice(int num, String admin_id) { // String pageNum,HttpRequestResponse res
		// response.sendRedirect(list.jsp?pageNum=pageNum)
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;// passwd를 조회할 select필요
		String dbadmin_id = null;// db에서 찾은 암호를 저장
		String sql = "";// sql구문 저장
		int x = -1;// 게시물의 삭제 성공 유무

		// 2.메서드 목적에 맞는 SQL구문
		try {
			con = pool.getConnection();
			sql = "select admin_id from noticeboard where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num); // 삭제하고자하는 데이터에서 암호분리
			rs = pstmt.executeQuery();
			// 추가
			if (rs.next()) { // 게시물번호에 해당하는 암호를 찾았다면
				dbadmin_id = rs.getString("admin_id");
				System.out.println("dbpasswd=>" + dbadmin_id);
				// db상의 암호=웹상에 입력한 암호가 맞다면
				if (dbadmin_id.equals(admin_id)) {

					sql = "delete from noticeboard where num=? ";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, num);// 게시물 번호
					// 실행
					int delete = pstmt.executeUpdate();
					System.out.println("게시판의 글삭제 성공유무(delete)=>" + delete);
					x = 1; // 삭제성공
				} else {
					x = 0;// 삭제실패->암호가 틀린경우
				}
			} // if(rs.next())=>암호가 존재한다면
		} catch (Exception e) {
			System.out.println("deleteArticlenotice() 메서드 실행오류=>" + e);
		} finally {// 메모리해제
			pool.freeConnection(con, pstmt, rs);
		}
		return x;// 1->삭제성공, 0->삭제실패
	}

	// 3.페이징 처리(페이지별로 화면에 보여주는 레코드수를 조절)
	// 1)전체 레코드수를 구해와야 한다.=>select count(*) from board;
	public int getArticleCountReview() {

		// 1.DB연결
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;

		// 2.메서드 목적에 맞는 SQL구문
		try {
			con = pool.getConnection();
			System.out.println("con=>" + con);
			String sql = "select count(*) from reviewboard";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			// 추가
			if (rs.next()) { // 레코드갯수가 한개이상이라면
				x = rs.getInt(1);// 필드명이 없다->select ~from사이의 필드순서로 지정
				System.out.println("총레코드수(x)=>" + x);
			}
		} catch (Exception e) {
			System.out.println("getArticleCountReview() 메서드 실행오류=>" + e);
		} finally {// 메모리해제
			pool.freeConnection(con, pstmt, rs);
		}
		return x;
	}


	// (1)게시판의 레코드수를 검색어에 따른 메서드 작성(검색분야,검색어)
	public int getArticleSearchCountReview(String search, String searchtext) {
		// 1.DB연결
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;
		// 추가
		String sql = "";// 저장시킬 SQL구문
		// 2.메서드 목적에 맞는 SQL구문
		try {
			con = pool.getConnection();
			System.out.println("con=>" + con);
			// ---검색어를 입력하지 않은 경우(검색분야 선택)X------
			if (search == null || search == "") {
				sql = "select count(*) from reviewboard";
			} else { // 검색분야(제목,작성자,제목+본문)->검색했다면
				if (search.equals("title_content")) {
					sql = "select count(*) from reviewboard  where title like '%" + searchtext
							+ "%' or content like  '%" + searchtext + "%' ";

				} else { // 제목 or 작성자->매개변수를 이용해서 하나의 sql통합
					sql = "select count(*) from reviewboard  where " + search + " like '%" + searchtext + "%' ";
				}
			}
			System.out.println("getArticleSearchCountReview 검색어 sql=>" + sql);
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			// 추가
			if (rs.next()) { // 레코드갯수가 한개이상이라면
				x = rs.getInt(1);// 필드명이 없다->select ~from사이의 필드순서로 지정
				System.out.println("총레코드수(x)=>" + x);
			}
		} catch (Exception e) {
			System.out.println("getArticleSearchCountReview() 메서드 실행오류=>" + e);
		} finally {// 메모리해제
			pool.freeConnection(con, pstmt, rs);
		}
		return x;
	}

	public List<ReviewBoardDTO> getArticlesReview(int start, int end) { // 1,10,11,10,20,10

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;// select
		List<ReviewBoardDTO> articleList = null;// ArrayList articleList=null; //10개를 담을 객체선언
		end=start+end-1;
		// 2.메서드 목적에 맞는 SQL구문
		try {
			con = pool.getConnection();
			// 가장 최신의글을 내림차순으로 정렬하되 re_step값은 오름차순으로 정렬
			// (몇번째 레코드를 기준으로 몇개의 레코드 범위)
			String sql = "select * from reviewboard where rownum>=? and rownum <=? order by ref desc,re_step asc";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, start - 1);// mysql이 내부적으로 레코드순번을 0부터 시작
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();
			// 추가->do~while->기존의 데이터는 그대로 유지=>신규글이 추가=>같이출력
			if (rs.next()) { // 레코드갯수가 한개라도 존재한다면
				// articleList = new List();
				// articleList=new 자식클래명();
				articleList = new ArrayList(end);// 초기생성값을 미리 부여end(크기 지정)
				do { // 무조건 기존의 데이터 추가+새로추가
					ReviewBoardDTO article = new ReviewBoardDTO();// MemberDTO mem=new MemberDTO
					article.setNum(rs.getInt("num"));// 번호
					article.setId(rs.getString("id"));// 아이디
					article.setTitle(rs.getString("title"));// 제목
					article.setContent(rs.getString("content"));// 내용
					article.setDay(rs.getTimestamp("day"));// 날자
					article.setReadcount(rs.getInt("readcount"));// 조회수
					article.setRef(rs.getInt("ref"));// 그룹번호->답변다는 위치지정
					article.setRe_step(rs.getInt("re_step"));// 답변글 순서
					article.setRe_level(rs.getInt("re_level"));// 답변의 깊이(depth)

					articleList.add(article);// 최종 웹상에서 필드별로 출력(Getter)
				} while (rs.next());
			} // if(rs.next()){}
		} catch (Exception e) {
			System.out.println("getArticlesReview() 메서드 실행오류=>" + e);
		} finally {// 메모리해제
			pool.freeConnection(con, pstmt, rs);
		}
		return articleList;
	}

	// (2)검색어에 따른 레코드의 범위 지정에 대한 메서드 추가
	public List<ReviewBoardDTO> getReviewBoardArticles(int start, int end, String search, String searchtext) { // 1,10,11,10,20,10

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;// select
		List<ReviewBoardDTO> articleList = null;// ArrayList articleList=null; //10개를 담을 객체선언
		// 추가
		String sql = "";
		end=start+end-1; 
		// 2.메서드 목적에 맞는 SQL구문
		try {
			con = pool.getConnection();
			// --------------------------------------------------------------------
			if (search == null || search == "") {
				sql = "select x.* from(select rownum rnum, a.* from(\r\n"
						+ "select * from reviewboard order by ref desc, re_level asc ) a\r\n" + "where rownum <=?) x \r\n"
						+ "where x.rnum >=?";
			} else {// 제목,작성자->검색범위에 있는것만 가장 최신의 글로 정렬~
				if (search.equals("title_content")) {
					sql = "select x.* from(select rownum rnum, a.*\r\n" + "from(\r\n"
							+ "select * from reviewboard order by ref desc, re_level asc) a\r\n" + "where title like '%" + searchtext
							+ "%' or content like '%" + searchtext + "%' \r\n" + "and rownum <=?) x\r\n"
							+ "where x.rnum >=?";

				} else { // 제목 or 작성자->매개변수를 이용해서 하나의 sql통합
					sql = "select x.* from(select rownum rnum, a.*\r\n" + "from(\r\n"
							+ "select * from reviewboard order by ref desc, re_level asc) a\r\n" + "where " + search + " like '%"
							+ searchtext + "%' and rownum<=?) x \r\n" + "where x.rnum >=?";
				}
			}
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, end);
			pstmt.setInt(2, start);// mysql이 내부적으로 레코드순번을 0부터 시작
			rs = pstmt.executeQuery();
			// 추가->do~while->기존의 데이터는 그대로 유지=>신규글이 추가=>같이출력
			if (rs.next()) { // 레코드갯수가 한개라도 존재한다면
				articleList = new ArrayList(end);// end갯수 만큼 데이터를 담기위해서
				do { // 무조건 기존의 데이터 추가+새로추가
					ReviewBoardDTO article = new ReviewBoardDTO();// MemberDTO mem=new MemberDTO
					article.setNum(rs.getInt("num"));// 번호
					article.setId(rs.getString("id"));// 아이디
					article.setIp(rs.getString("ip"));// 글쓴이의 ip
					article.setTitle(rs.getString("title"));// 제목
					article.setContent(rs.getString("content"));// 내용
					article.setDay(rs.getTimestamp("day"));// 날자
					article.setReadcount(rs.getInt("readcount"));// 조회수
					article.setRef(rs.getInt("ref"));// 그룹번호->답변다는 위치지정
					article.setRe_step(rs.getInt("re_step"));// 답변글 순서
					article.setRe_level(rs.getInt("re_level"));// 답변의 깊이(depth)
					articleList.add(article);// 최종 웹상에서 필드별로 출력(Getter)
				} while (rs.next());
			} // if(rs.next()){}
		} catch (Exception e) {
			System.out.println("getReviewBoardArticles() 메서드 실행오류=>" + e);
		} finally {// 메모리해제
			pool.freeConnection(con, pstmt, rs);
		}
		return articleList;
	}

	// 3.insertArticle()->글쓰기 및 답변글 쓰기
	// insert into~ ->내부에서 성공유무 체크,->list.jsp로 자동으로 이동
	public void insertArticleReview(ReviewBoardDTO article) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;// select ->select max(num) from board->+1(num구함)

		// -------------신규글인지 답변글인지 구분----------------------
		int num = article.getNum();// 0->신규글 0아닌 경우->답변글
		int ref = article.getRef();
		int re_step = article.getRe_step();
		int re_level = article.getRe_level();
		// 추가
		int number = 0;// 테이블에 입력할 게시물번호를 저장할 변수
		System.out.println("insertArticleReview메서드 내부의 num=>" + num);
		System.out.println("ref=" + ref + ",re_step=>" + re_step + ",re_level=>" + re_level);
		String sql = "";// sql구문 저장

		// 2.메서드 목적에 맞는 SQL구문
		try {
			con = pool.getConnection();
			sql = "select max(num) from reviewboard";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			// 추가
			if (rs.next()) { // 레코드갯수가 한개이상이라면
				number = rs.getInt(1) + 1;
			} else {
				number = 1;// 게시물 번호를 1로 설정
			}
			System.out.println("insertArticleReview의 number=>" + number);
			// 만약에 답변글이라면 ->num=0 신규글이기 때문에
			if (num != 0) {
				// 같은 그룹번호를 가지고 있으면서 내가 들어가 위치에 있는 게시물의
				// re_step값을 하나 증가->빈 step->답변글

				sql = "update reviewboard set re_step=re_step+1 where ref=? and re_step > ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, ref);
				pstmt.setInt(2, re_step);
				int update = pstmt.executeUpdate();
				System.out.println("댓글 수정 유무(update)=>" + update);// 1
				re_step = re_step + 1;
				re_level = re_level + 1;
			} else { // 신규글이라면

				ref = number;// 1,2,3,4,5,6,
				re_step = 0;
				re_level = 0;
			}
			// 12개의 필드->num,reg_date,readcount(생략)->sysdate,now()<-?
			// num(자동입력),readcount(디폴트 기능때문에)->0이 들어가게 설정
			sql = "insert into reviewboard(num, id, ip, title, content, day, readcount,";
			sql += " ref,re_step,re_level) values(?,?,?,?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, number);
			pstmt.setString(2, article.getId());
			pstmt.setString(3, article.getIp());
			pstmt.setString(4, article.getTitle());// 암호
			pstmt.setString(5, article.getContent());// 작성 날짜
			pstmt.setTimestamp(6, (Timestamp) article.getDay());// 글내용
			pstmt.setInt(7, article.getReadcount());// 접속자의 ip주소
			pstmt.setInt(8, ref);// ref ->article.getRe_ref() X
			pstmt.setInt(9, re_step);// re_step
			pstmt.setInt(10, re_level);// re_level
			// 실행

			int insert = pstmt.executeUpdate();

			System.out.println("게시판의 글쓰기 성공유무(insert)=>" + insert);
		} catch (Exception e) {
			System.out.println("insertArticleReview() 메서드 실행오류=>" + e);
		} finally {// 메모리해제
			pool.freeConnection(con, pstmt, rs);
		}
	}

	// 4.글 상세보기->조회수도 증가
	// update board set readcount=readcount+1 where num=?
	// select * from board where num=?
	public ReviewBoardDTO getArticleReview(int num) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;// select * from board where num=?
		ReviewBoardDTO article = null;// 게시물번호에 해당하는 레코드 한개

		// 2.메서드 목적에 맞는 SQL구문
		try {
			con = pool.getConnection();

			String sql = "update reviewboard set readcount=readcount+1 where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			int update = pstmt.executeUpdate();
			System.out.println("조회수 증가유무(update)=>" + update);// 1
			// pstmt.close();
			sql = "select * from reviewboard where  num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();

			if (rs.next()) { // 레코드갯수가 한개라도 존재한다면
				article = makeArticleFromResultReview(rs);
			}
		} catch (Exception e) {
			System.out.println("getArticleReview() 메서드 실행오류=>" + e);
		} finally {// 메모리해제
			pool.freeConnection(con, pstmt, rs);
		}
		return article;
	}

	private ReviewBoardDTO makeArticleFromResultReview(ResultSet rs) throws Exception {
		ReviewBoardDTO article = new ReviewBoardDTO();// MemberDTO mem=new MemberDTO

		article.setNum(rs.getInt("num"));// 번호
		article.setId(rs.getString("id"));// 아이디
		article.setIp(rs.getString("ip"));// 글쓴이의 ip
		article.setTitle(rs.getString("title"));// 제목
		article.setContent(rs.getString("content"));// 내용
		article.setDay(rs.getTimestamp("day"));// 날자
		article.setReadcount(rs.getInt("readcount"));// 조회수
		article.setRef(rs.getInt("ref"));// 그룹번호->답변다는 위치지정
		article.setRe_step(rs.getInt("re_step"));// 답변글 순서
		article.setRe_level(rs.getInt("re_level"));// 답변의 깊이(depth)
		return article;
	}

	// 5.글수정을 하기위한 게시물번호에 해당하는 레코드를 찾는 메서드
	// ->updateGetArticle(int num)->select * from board where num=?
	public ReviewBoardDTO updateGetArticleReview(int num) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;// select * from board where num=?
		ReviewBoardDTO article = null;// 게시물번호에 해당하는 레코드 한개

		// 2.메서드 목적에 맞는 SQL구문
		try {
			con = pool.getConnection();
			System.out.println("********************" + num);
			String sql = "select * from reviewboard where  num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) { // 레코드갯수가 한개라도 존재한다면
				article = makeArticleFromResultReview(rs);
			} // if(rs.next()){}
		} catch (Exception e) {
			System.out.println("updateGetArticleReview=() 메서드 실행오류=>" + e);
		} finally {// 메모리해제
			pool.freeConnection(con, pstmt, rs);
		}
		return article;
	}

	// 6.글수정시켜주는 메서드->updateArticle(BoardDTO article){}
	// =>글쓰기(insertArticle()와 기능이 동일(데이터를 저장)->인증->수정
	public int updateArticleReview(ReviewBoardDTO article) { // (int passwd)
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;// passwd를 조회할 select필요
		String id = null;// db에서 찾은 암호를 저장
		String sql = "";// sql구문 저장
		int x = -1;// 게시물의 수정 성공 유무

		// 2.메서드 목적에 맞는 SQL구문
		try {
			con = pool.getConnection();
			sql = "select * from reviewboard where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, article.getNum()); // 수정하고자하는 데이터에서 암호분리
			rs = pstmt.executeQuery();
			System.out.println("updateArticleReview() 메소드 실행"+article.getId());
			// 추가
			if (rs.next()) { // 게시물번호에 해당하는 암호를 찾았다면
				id = rs.getString("id");
				System.out.println("id=>" + id);
				// db상의 암호=웹상에 입력한 암호가 맞다면
				if (id.equals(article.getId())) {
					sql = "update reviewboard set title=?, content=? where num=? ";
					pstmt.close();
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, article.getTitle());// 암호
					pstmt.setString(2, article.getContent());// 글내용
					pstmt.setInt(3, article.getNum());// 게시물 번호
					// 실행
					int update = pstmt.executeUpdate();
					System.out.println("게시판의 글수정 성공유무(update)=>" + update);
					x = 1;
				} else {
					x = 0;// 수정실패->암호가 틀린경우
				}
			} // if(rs.next())=>암호가 존재한다면
		} catch (Exception e) {
			System.out.println("updateArticleReview() 메서드 실행오류=>" + e);
		} finally {// 메모리해제
			pool.freeConnection(con, pstmt, rs);
		}
		return x;// 1->수정성공, 0->수정실패
	}
	// 글삭제 시켜주는 메서드=>회원탈퇴(삭제)=>암호를 물어본다.(deletePro.jsp)
	// select passwd from board where num=? ->직접
	// delete from board where num=?

	public int deleteArticleReview(int num, String id) {
		// TODO Auto-generated method stub
		// response.sendRedirect(list.jsp?pageNum=pageNum)
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;// passwd를 조회할 select필요
		String dbid = null;// db에서 찾은 암호를 저장
		String sql = "";// sql구문 저장
		int x = -1;// 게시물의 삭제 성공 유무

		// 2.메서드 목적에 맞는 SQL구문
		try {
			con = pool.getConnection();
			sql = "select id from reviewboard where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num); // 삭제하고자하는 데이터에서 암호분리
			rs = pstmt.executeQuery();
			// 추가
			if (rs.next()) { // 게시물번호에 해당하는 암호를 찾았다면
				dbid = rs.getString("id");
				System.out.println("dbpasswd=>" + dbid);
				// db상의 암호=웹상에 입력한 암호가 맞다면
				if (dbid.equals(id)) {

					sql = "delete from reviewboard where num=? ";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, num);// 게시물 번호
					// 실행
					int delete = pstmt.executeUpdate();
					System.out.println("게시판의 글삭제 성공유무(delete)=>" + delete);
					x = 1; // 삭제성공
				} else {
					x = 0;// 삭제실패->암호가 틀린경우
				}
			} // if(rs.next())=>암호가 존재한다면
		} catch (Exception e) {
			System.out.println("deleteArticlereview() 메서드 실행오류=>" + e);
		} finally {// 메모리해제
			pool.freeConnection(con, pstmt, rs);
		}
		return x;// 1->삭제성공, 0->삭제실패
	}

	// --------------------------------Qna---------------------------------------

	// 3.페이징 처리(페이지별로 화면에 보여주는 레코드수를 조절)
	// 1)전체 레코드수를 구해와야 한다.=>select count(*) from board;
	public int getArticleCountQna() {

		// 1.DB연결
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;

		// 2.메서드 목적에 맞는 SQL구문
		try {
			con = pool.getConnection();
			System.out.println("con=>" + con);
			String sql = "select count(*) from qnaboard";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			// 추가
			if (rs.next()) { // 레코드갯수가 한개이상이라면
				x = rs.getInt(1);// 필드명이 없다->select ~from사이의 필드순서로 지정
				System.out.println("총레코드수(x)=>" + x);
			}
		} catch (Exception e) {
			System.out.println("getArticleCountQna() 메서드 실행오류=>" + e);
		} finally {// 메모리해제
			pool.freeConnection(con, pstmt, rs);
		}
		return x;
	}

	// (1)게시판의 레코드수를 검색어에 따른 메서드 작성(검색분야,검색어)
	public int getArticleSearchCountQna(String search, String searchtext) {
		// 1.DB연결
		System.out.println("getArticleSearchCountQna 호출");
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;
		// 추가
		String sql = "";// 저장시킬 SQL구문
		// 2.메서드 목적에 맞는 SQL구문
		try {
			con = pool.getConnection();
			System.out.println("con=>" + con);
			// ---검색어를 입력하지 않은 경우(검색분야 선택)X------
			if (search == null || search == "") {
				sql = "select count(*) from qnaboard";
			} else { // 검색분야(제목,작성자,제목+본문)->검색했다면
				if (search.equals("title_content")) {
					sql = "select count(*) from qnaboard  where title like '%" + searchtext + "%' or content like  '%"
							+ searchtext + "%' ";

				} else { // 제목 or 작성자->매개변수를 이용해서 하나의 sql통합
					sql = "select count(*) from qnaboard  where " + search + " like '%" + searchtext + "%' ";
					// sql="select count(*) from board where writer like '%"+searchtext+"%' ";
					// sql="select count(*) from board where subject like '%"+searchtext+"%' ";
				}
			}
			System.out.println("getArticleSearchCountQna 검색어 sql=>" + sql);
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			// 추가
			if (rs.next()) { // 레코드갯수가 한개이상이라면
				x = rs.getInt(1);// 필드명이 없다->select ~from사이의 필드순서로 지정
				System.out.println("총레코드수(x)=>" + x);
			}
		} catch (Exception e) {
			System.out.println("getArticleSearchCountQna() 메서드 실행오류=>" + e);
		} finally {// 메모리해제
			pool.freeConnection(con, pstmt, rs);
		}
		return x;
	}

	// 2)글목록보기에 대한 메서드구현(범위)
	// select * from board order by ref desc,re_step asc limit ?,?
	// 1.레코드의 시작번호 2.웹상에 보여줄 레코드갯수
	public List<QnaBoardDTO> getArticlesQna(int start, int end) { // 1,10,11,10,20,10

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;// select
		List<QnaBoardDTO> articleList = null;// ArrayList articleList=null; //10개를 담을 객체선언

		// 2.메서드 목적에 맞는 SQL구문
		try {
			con = pool.getConnection();
			// 가장 최신의글을 내림차순으로 정렬하되 re_step값은 오름차순으로 정렬
			// (몇번째 레코드를 기준으로 몇개의 레코드 범위)
			String sql = "select * from qnaboard where rownum>=? and rownum <=? order by ref desc,ref_step asc";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, start - 1);// mysql이 내부적으로 레코드순번을 0부터 시작
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();
			// 추가->do~while->기존의 데이터는 그대로 유지=>신규글이 추가=>같이출력
			if (rs.next()) { // 레코드갯수가 한개라도 존재한다면
				// articleList = new List();
				// articleList=new 자식클래명();
				articleList = new ArrayList(end);// 초기생성값을 미리 부여end(크기 지정)
				do { // 무조건 기존의 데이터 추가+새로추가
					QnaBoardDTO article = new QnaBoardDTO();// MemberDTO mem=new MemberDTO
					article.setNum(rs.getInt("num"));// 번호
					article.setId(rs.getString("id"));// 아이디
					article.setMail(rs.getString("mail"));// 글쓴이의 ip
					article.setTitle(rs.getString("title"));// 제목
					article.setContent(rs.getString("content"));// 내용
					article.setDay(rs.getTimestamp("day"));// 날자
					article.setRef(rs.getInt("ref"));// 그룹번호->답변다는 위치지정
					article.setRef_step(rs.getInt("ref_step"));// 답변글 순서
					article.setRef_level(rs.getInt("ref_level"));// 답변의 깊이(depth)

					articleList.add(article);// 최종 웹상에서 필드별로 출력(Getter)
				} while (rs.next());
			} // if(rs.next()){}
		} catch (Exception e) {
			System.out.println("getArticlesQna() 메서드 실행오류=>" + e);
		} finally {// 메모리해제
			pool.freeConnection(con, pstmt, rs);
		}
		return articleList;
	}

	// (2)검색어에 따른 레코드의 범위 지정에 대한 메서드 추가
	public List<QnaBoardDTO> getQnaBoardArticles(int start, int end, String search, String searchtext) { // 1,10,11,10,20,10
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;// select
		List<QnaBoardDTO> articleList = new ArrayList(end);
		;// ArrayList articleList=null; //10개를 담을 객체선언
		// 추가
		String sql = "";
		end = start + end - 1;
		// 2.메서드 목적에 맞는 SQL구문
		try {
			con = pool.getConnection();
			System.out.println("[BoardDAO] getQnaBoardArticles() 실행중 :" + start + "," + end);
			;
			System.out.println("[BoardDAO] getQnaBoardArticles() 실행중 :" + search + "," + searchtext);
			// --------------------------------------------------------------------
			if (search == null || search == "") {
				sql = "select x.* " + "from ( " + "select rownum rnum, a.* " + "from ( " + "select * "
						+ "from qnaboard order by ref desc, re_level asc) a " + "where rownum <= ? ) x " + "where x.rnum >= ?";
			} else {// 제목,작성자->검색범위에 있는것만 가장 최신의 글로 정렬~
				if (search.equals("title_content")) {
					sql = "select x.* " + "from ( " + "select rownum rnum, a.* " + "from ( " + "select * "
							+ "from qnaboard order by ref desc, re_level asc) a " + "where  title like '%" + searchtext
							+ "%' or content like '%" + searchtext + "%' " + "and rownum <= ? ) x "
							+ "where x.rnum >= ?";

				} else { // 제목 or 작성자->매개변수를 이용해서 하나의 sql통합
					sql = "select x.* " + "from ( " + "select rownum rnum, a.* " + "from ( " + "select * "
							+ "from qnaboard order by ref desc, re_level asc) a " + "where " + search + " like '%" + searchtext
							+ "%' " + "and rownum <= ? ) x " + "where x.rnum >= ?";
				}
			}
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(2, start);
			pstmt.setInt(1, end);// mysql이 내부적으로 레코드순번을 0부터 시작
			rs = pstmt.executeQuery();
			while (rs.next()) {
				QnaBoardDTO article = new QnaBoardDTO();// MemberDTO mem=new MemberDTO
				article.setNum(rs.getInt("num"));// 번호
				article.setId(rs.getString("id"));// 아이디
				article.setMail(rs.getString("mail"));// 글쓴이의 ip
				article.setTitle(rs.getString("title"));// 제목
				article.setContent(rs.getString("content"));// 내용
				article.setDay(rs.getTimestamp("day"));// 날자
				article.setRef(rs.getInt("ref"));// 그룹번호->답변다는 위치지정
				article.setRef_step(rs.getInt("ref_step"));// 답변글 순서
				article.setRef_level(rs.getInt("ref_level"));// 답변의 깊이(depth)
				articleList.add(article);// 최종 웹상에서 필드별로 출력(Getter)
				System.out.println("[BoardDAO] getQnaBoardArticles() dowhile문 article.title :" + article.getTitle());
			}
			// 추가->do~while->기존의 데이터는 그대로 유지=>신규글이 추가=>같이출력
		 } catch (Exception e) {
			System.out.println("getQnaBoardArticles() 메서드 실행오류=>" + e);
		} finally {// 메모리해제
			pool.freeConnection(con, pstmt, rs);
		}
		return articleList;
	}

	// 3.insertArticle()->글쓰기 및 답변글 쓰기
	// insert into~ ->내부에서 성공유무 체크,->list.jsp로 자동으로 이동
	public void insertArticleQna(QnaBoardDTO article) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;// select ->select max(num) from board->+1(num구함)

		// -------------신규글인지 답변글인지 구분----------------------
		int num = article.getNum();// 0->신규글 0아닌 경우->답변글
		int ref = article.getRef();
		int ref_step = article.getRef_step();
		int ref_level = article.getRef_level();
		// 추가
		int number = 0;// 테이블에 입력할 게시물번호를 저장할 변수
		System.out.println("insertArticleQna메서드 내부의 num=>" + num);
		System.out.println("ref=" + ref + ",ref_step=>" + ref_step + ",ref_level=>" + ref_level);
		String sql = "";// sql구문 저장

		// 2.메서드 목적에 맞는 SQL구문
		try {
			con = pool.getConnection();
			sql = "select max(num) from qnaboard";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			// 추가
			if (rs.next()) { // 레코드갯수가 한개이상이라면
				number = rs.getInt(1) + 1;
			} else {
				number = 1;// 게시물 번호를 1로 설정
			}
			System.out.println("insertArticleQna의 number=>" + number);
			// 만약에 답변글이라면 ->num=0 신규글이기 때문에
			if (num != 0) {
				// 같은 그룹번호를 가지고 있으면서 내가 들어가 위치에 있는 게시물의
				// re_step값을 하나 증가->빈 step->답변글

				sql = "update qnaboard set ref_step=ref_step+1 where ref=? and ref_step > ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, ref);
				pstmt.setInt(2, ref_step);
				int update = pstmt.executeUpdate();
				System.out.println("댓글 수정 유무(update)=>" + update);// 1
				ref_step = ref_step + 1;
				ref_level = ref_level + 1;
			} else { // 신규글이라면

				ref = number;// 1,2,3,4,5,6,
				ref_step = 0;
				ref_level = 0;
			}
			// 12개의 필드->num,reg_date,readcount(생략)->sysdate,now()<-?
			// num(자동입력),readcount(디폴트 기능때문에)->0이 들어가게 설정
			sql = "insert into qnaboard(num, id, mail, title, content, day,";
			sql += " ref,ref_step,ref_level) values(?,?,?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, number);
			pstmt.setString(2, article.getId());
			pstmt.setString(3, article.getMail());
			pstmt.setString(4, article.getTitle());// 암호
			pstmt.setString(5, article.getContent());// 글내용
			pstmt.setTimestamp(6, (Timestamp) article.getDay());// 작성 날짜
			pstmt.setInt(7, ref);// ref ->article.getRe_ref() X
			pstmt.setInt(8, ref_step);// re_step
			pstmt.setInt(9, ref_level);// re_level
			// 실행

			int insert = pstmt.executeUpdate();

			System.out.println("게시판의 글쓰기 성공유무(insert)=>" + insert);
		} catch (Exception e) {
			System.out.println("insertArticleQna() 메서드 실행오류=>" + e);
		} finally {// 메모리해제
			pool.freeConnection(con, pstmt, rs);
		}
	}

	// 4.글 상세보기->조회수도 증가
	// update board set readcount=readcount+1 where num=?
	// select * from board where num=?
	public QnaBoardDTO getArticleQna(int num) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;// select * from board where num=?
		QnaBoardDTO article = null;// 게시물번호에 해당하는 레코드 한개

		// 2.메서드 목적에 맞는 SQL구문
		try {
			con = pool.getConnection();
			String sql = "select * from qnaboard where  num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();

			if (rs.next()) { // 레코드갯수가 한개라도 존재한다면
				article = makeArticleFromResultQna(rs);
			}
		} catch (Exception e) {
			System.out.println("getArticleQna() 메서드 실행오류=>" + e);
		} finally {// 메모리해제
			pool.freeConnection(con, pstmt, rs);
		}
		return article;
	}
	// ----중복된 레코드한개를 담을 수 있는 메서드를 따로 처리해주는
	// 메서드작성-------------------------------------------------------------
	// 이 메서드는 웹상에서 호출되는 메서드X=>이 클래스내부에서만 사용(private)
	// private MemberDTO makeMemberFromResult(ResultSet rs){}

	private QnaBoardDTO makeArticleFromResultQna(ResultSet rs) throws Exception {
		QnaBoardDTO article = new QnaBoardDTO();// MemberDTO mem=new MemberDTO

		article.setNum(rs.getInt("num"));// 번호
		article.setId(rs.getString("id"));// 아이디
		article.setMail(rs.getString("mail"));// 글쓴이의 ip
		article.setTitle(rs.getString("title"));// 제목
		article.setContent(rs.getString("content"));// 내용
		article.setDay(rs.getTimestamp("day"));// 날자
		article.setRef(rs.getInt("ref"));// 그룹번호->답변다는 위치지정
		article.setRef_step(rs.getInt("ref_step"));// 답변글 순서
		article.setRef_level(rs.getInt("ref_level"));// 답변의 깊이(depth)
		return article;
	}

	// 5.글수정을 하기위한 게시물번호에 해당하는 레코드를 찾는 메서드
	// ->updateGetArticle(int num)->select * from board where num=?
	public QnaBoardDTO updateGetArticleQna(int num) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;// select * from board where num=?
		QnaBoardDTO article = null;// 게시물번호에 해당하는 레코드 한개

		// 2.메서드 목적에 맞는 SQL구문
		try {
			con = pool.getConnection();
			System.out.println("********************" + num);
			String sql = "select * from qnaboard where  num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) { // 레코드갯수가 한개라도 존재한다면
				article = makeArticleFromResultQna(rs);
			} // if(rs.next()){}
		} catch (Exception e) {
			System.out.println("updateGetArticleQna=() 메서드 실행오류=>" + e);
		} finally {// 메모리해제
			pool.freeConnection(con, pstmt, rs);
		}
		return article;
	}

	// 6.글수정시켜주는 메서드->updateArticle(BoardDTO article){}
	// =>글쓰기(insertArticle()와 기능이 동일(데이터를 저장)->인증->수정
	public int updateArticleQna(QnaBoardDTO article) { // (int passwd)

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;// passwd를 조회할 select필요
		String id = null;// db에서 찾은 암호를 저장
		String sql = "";// sql구문 저장
		int x = -1;// 게시물의 수정 성공 유무

		// 2.메서드 목적에 맞는 SQL구문
		try {
			con = pool.getConnection();
			sql = "select * from qnaboard where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, article.getNum()); // 수정하고자하는 데이터에서 암호분리
			rs = pstmt.executeQuery();
			// 추가
			if (rs.next()) { // 게시물번호에 해당하는 암호를 찾았다면
				id = rs.getString("id");
				System.out.println("id=>" + id);
				// db상의 암호=웹상에 입력한 암호가 맞다면
				if (id.equals(article.getId())) {

					sql = "update qnaboard set title=?, content=? where num=? ";
					pstmt.close();
					pstmt = con.prepareStatement(sql);
					// pstmt.setString(1, article.getIp());
					pstmt.setString(1, article.getTitle());// 암호
					pstmt.setString(2, article.getContent());// 글내용
					// pstmt.setTimestamp(2, (Timestamp) article.getDay());// 게시물 번호
					pstmt.setInt(3, article.getNum());// 게시물 번호
					// 실행
					int update = pstmt.executeUpdate();

					System.out.println("게시판의 글수정 성공유무(update)=>" + update);
					x = 1;
				} else {
					x = 0;// 수정실패->암호가 틀린경우
				}
			} // if(rs.next())=>암호가 존재한다면
		} catch (Exception e) {
			System.out.println("updateArticleQna() 메서드 실행오류=>" + e);
		} finally {// 메모리해제
			pool.freeConnection(con, pstmt, rs);
		}
		return x;// 1->수정성공, 0->수정실패
	}

	public int deleteArticleQna(int num, String id, String mail) {
		// TODO Auto-generated method stub
		// response.sendRedirect(list.jsp?pageNum=pageNum)
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;// passwd를 조회할 select필요
		String dbid = null;// db에서 찾은 암호를 저장
		String dbmail = null;
		String sql = "";// sql구문 저장
		int x = -1;// 게시물의 삭제 성공 유무

		System.out.println("deleteArticleQna() " + num + "," + mail);
		// 2.메서드 목적에 맞는 SQL구문
		try {
			con = pool.getConnection();
			// sql = "select id from qnaboard where num=?";
			sql = "select mail from qnaboard where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num); // 삭제하고자하는 데이터에서 암호분리
			rs = pstmt.executeQuery();
			// 추가
			if (rs.next()) { // 게시물번호에 해당하는 암호를 찾았다면
				// dbid = rs.getString("id");
				dbmail = rs.getString("mail");
				// System.out.println("dbpasswd=>" + dbid);
				System.out.println("dbpasswd=>" + dbmail);
				// db상의 암호=웹상에 입력한 암호가 맞다면
				// if (dbid.equals(id)) {
			} // if(rs.next())=>암호가 존재한다면
			pstmt.close();
			if (dbmail.equals(mail)) {
				// sql = "delete from qnaboard where ref=? ";
				sql = "delete from qnaboard where num=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, num);// 게시물 번호
				// 실행
				int delete = pstmt.executeUpdate();
				System.out.println("게시판의 글삭제 성공유무(delete)=>" + delete);
				x = 1; // 삭제성공
			} else {
				x = 0;// 삭제실패->암호가 틀린경우
			}
		} catch (Exception e) {
			System.out.println("deleteArticleQna() 메서드 실행오류=>" + e);
		} finally {// 메모리해제
			pool.freeConnection(con, pstmt, rs);
		}
		return x;// 1->삭제성공, 0->삭제실패

	}
	
	// 크롤링
    public String crawlingBoard(String search) throws IOException {

        List<String> title = new ArrayList();
        Document url = Jsoup.connect("https://www.google.co.kr/search?ei=KEPlWpiCEcWy0gShl43ICw&q=" + search
                + "+%ED%9B%84%EA%B8%B0&oq=" + search
                + "+%ED%9B%84%EA%B8%B0&gs_l=psy-ab.12..35i39k1j0i7i30k1l9.9202.9202.0.11674.1.1.0.0.0.0.150.150.0j1.1.0....0...1c.1.64.psy-ab..0.1.149....0.7AC9-Ndj6Fg")
                .get();

        Elements tagVal = url.select("div.rc h3 a");

        // String text= tagVal .select("a").html();
        // for(int i=0; i<title.size(); i++) {
        // title.add(text);
        // }
        // System.out.println( "tagVal : " + tagVal );
        // System.out.println( "text : " + text );
        return tagVal.toString();
    }
	
}