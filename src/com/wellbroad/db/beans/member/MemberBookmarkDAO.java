package com.wellbroad.db.beans.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import com.wellbroad.db.connection.DBConnectionManager;

public class MemberBookmarkDAO {

	private DBConnectionManager pool = null;
	
	public MemberBookmarkDAO() {
		try {
			pool=DBConnectionManager.getInstance();
			System.out.println("pool = "+pool);
		} catch(Exception e) {
			System.out.println("DBConnectable error = "+e);
		}
	}
	// (1)게시판의 레코드수를 검색어에 따른 메서드 작성(검색분야,검색어)
			public int getArticleSearchCountBookmark(String search, String searchtext, String id) {
				// 1.DB연결
				System.out.println("getArticleSearchCountBookmark의 id=>"+id);
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
						sql = "select count(*) from member_bookmark where id=?";
						pstmt = con.prepareStatement(sql);
						pstmt.setString(1, id);					
						
					} else if (search.equals("title")) {
							sql = "select count(*) from member_bookmark  where title like '%" + searchtext + "%'";
							pstmt = con.prepareStatement(sql);
						} 
					rs = pstmt.executeQuery();
					System.out.println("getArticleSearchCountBookmark 검색어 sql=>" + sql);
					// 추가
					if (rs.next()) { // 레코드갯수가 한개이상이라면
						x = rs.getInt(1);// 필드명이 없다->select ~from사이의 필드순서로 지정
						System.out.println("총레코드수(x)=>" + x);
					}
				} catch (Exception e) {
					System.out.println("getArticleSearchCountBookmark() 메서드 실행오류=>" + e);
				} finally {// 메모리해제
					pool.freeConnection(con, pstmt, rs);
				}
				return x;
			}
			
			// (2)검색어에 따른 레코드의 범위 지정에 대한 메서드 추가
			public List<MemberBookmarkDTO> getBookmarkArticles(int start, int end, String search, String searchtext, String id) {

				Connection con = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;// select
				List<MemberBookmarkDTO> articleList = null;// ArrayList articleList=null; //10개를 담을 객체선언
				// 추가
				String sql = "";
				// 2.메서드 목적에 맞는 SQL구문
				try {
					con = pool.getConnection();
					// --------------------------------------------------------------------
					if (search == null || search == "") {
						sql = "select x.* from(select rownum rnum, a.* from(\r\n" + 
								"select * from member_bookmark order by num desc) a\r\n" + 
								"where id=? and rownum <=?) x\r\n" + 
								"where x.rnum >=?\r\n";
					} else {// 제목,작성자->검색범위에 있는것만 가장 최신의 글로 정렬~
						
						if (search.equals("title_id")) {
							sql="select x.* from(select rownum rnum, a.*\r\n" + 
									"from(\r\n" + 
									"select * from member_bookmark order by num desc) a\r\n" + 
									"where title like '%" + searchtext + "%'\r\n" + 
									"and id=? and rownum <=?) x\r\n" + 
									"where x.rnum >=?";

						} else  { 
							sql="select x.* from(select rownum rnum, a.*\r\n" + 
									"from(\r\n" + 
									"select * from member_bookmark order by num desc) a\r\n" + 
									"where " + search + " like '%"+ searchtext+"%' and id=? and rownum<=?) x \r\n" + 
									"where x.rnum >=?";
						}
					}
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, id);
					pstmt.setInt(2, end);
					pstmt.setInt(3, start);// mysql이 내부적으로 레코드순번을 0부터 시작
					
					rs = pstmt.executeQuery();
					// 추가->do~while->기존의 데이터는 그대로 유지=>신규글이 추가=>같이출력
					if (rs.next()) { // 레코드갯수가 한개라도 존재한다면
						articleList = new ArrayList(end);// end갯수 만큼 데이터를 담기위해서
						do { // 무조건 기존의 데이터 추가+새로추가
							MemberBookmarkDTO article = new MemberBookmarkDTO();// MemberDTO mem=new MemberDTO
							article.setNum(rs.getInt("num"));// 번호
							article.setReview_num(rs.getInt("review_num"));
							article.setId(rs.getString("id"));
							article.setTitle(rs.getString("title"));
							articleList.add(article);// 최종 웹상에서 필드별로 출력(Getter)
						} while (rs.next());
					} // if(rs.next()){}
				} catch (Exception e) {
					System.out.println("getbookmarkBoardArticles() 메서드 실행오류=>" + e);
				} finally {// 메모리해제
					pool.freeConnection(con, pstmt, rs);
				}
				return articleList;
			}
			
			 public void insertArticleBookmark(MemberBookmarkDTO article) {

		         Connection con = null;
		         PreparedStatement pstmt = null;
		         ResultSet rs = null;// select ->select max(num) from board->+1(num구함)

		         // -------------신규글인지 답변글인지 구분----------------------
		         int num = article.getNum();// 0->신규글 0아닌 경우->답변글
		         int number = 0;// 테이블에 입력할 게시물번호를 저장할 변수
		         System.out.println("insertArticleReview메서드 내부의 num=>" + num);
		         String sql = "";// sql구문 저장

		         // 2.메서드 목적에 맞는 SQL구문
		         try {
		            con = pool.getConnection();
		            sql = "select max(num) from member_bookmark";
		            pstmt = con.prepareStatement(sql);
		            rs = pstmt.executeQuery();
		            // 추가
		            if (rs.next()) { // 레코드갯수가 한개이상이라면
		               number = rs.getInt(1) + 1;
		            } else {
		               number = 1;// 게시물 번호를 1로 설정
		            }
		            System.out.println("insertArticleBookmark의 number=>" + number);
		            System.out.println("insertArticleBookmark의 id=>" + article.getId());
		            System.out.println("insertArticleBookmark의 review_num=>" + article.getReview_num());
		            System.out.println("insertArticleBookmark의 title=>" + article.getTitle());
		           
		            // 12개의 필드->num,reg_date,readcount(생략)->sysdate,now()<-?
		            // num(자동입력),readcount(디폴트 기능때문에)->0이 들어가게 설정
		            sql = "insert into member_bookmark(num, id, review_num, title) values(?,?,?,?)";
		            pstmt = con.prepareStatement(sql);
		            pstmt.setInt(1, number);
		            pstmt.setString(2, article.getId());
		            pstmt.setInt(3, article.getReview_num());
		            pstmt.setString(4, article.getTitle());
		            int insert = pstmt.executeUpdate();
		            System.out.println("게시판의 글쓰기 성공유무(insert)=>" + insert);
		         } catch (Exception e) {
		            System.out.println("insertArticleBookmark() 메서드 실행오류=>" + e);
		         } finally {// 메모리해제
		            pool.freeConnection(con, pstmt, rs);
		         }
		      }
			
			 public int deleteArticleBookmark(int num, String id) { // String pageNum,HttpRequestResponse res
					// response.sendRedirect(list.jsp?pageNum=pageNum)
					Connection con = null;
					PreparedStatement pstmt = null;
					ResultSet rs = null;// passwd를 조회할 select필요
					int number;// db에서 찾은 암호를 저장
					String sql = "";// sql구문 저장
					int x = -1;// 게시물의 삭제 성공 유무
					// 2.메서드 목적에 맞는 SQL구문
					try {
						System.out.println("deleteArticleBookmark num=>"+num);
						System.out.println("deleteArticleBookmark id=>"+id);
						con = pool.getConnection();
						sql = "delete from member_bookmark where review_num=? and id=?";
						pstmt = con.prepareStatement(sql);
						pstmt.setInt(1, num);// 게시물 번호
						pstmt.setString(2, id);
						System.out.println("여기까지1111111111111111111111111111111");
						//rs = pstmt.executeQuery();
						System.out.println("여기까지1111111111111111111111112222222");
						int delete = pstmt.executeUpdate();
						System.out.println("게시판의 글삭제 성공유무(delete)=>" + delete);
						x = 1; // 삭제성공
						
						//int delete = pstmt.executeUpdate();
						// pstmt.executeUpdate();
					//if(delete ==1) x=1;
					//	else x=0;
					} catch (Exception e) {
						System.out.println("deleteArticlebookmark() 메서드 실행오류=>" + e);
					} finally {// 메모리해제
						pool.freeConnection(con, pstmt, rs);
					}
					return x;// 1->삭제성공, 0->삭제실패
				}
			 
			    public int checkBookmark(int num, String id) {
			        Connection conn = null;
			        PreparedStatement pstmt = null;
			        ResultSet rs = null;
			        String sql = "";
			        int check = 0;; // ID중복 여부
			        try {
			            conn = pool.getConnection();
			            sql = "select review_num from member_bookmark where review_num=? and id=?";
			            pstmt = conn.prepareStatement(sql);
			            pstmt.setInt(1, num);
			            pstmt.setString(2, id);
			            rs = pstmt.executeQuery();
			            if (rs.next()) {
			            check = 1;
			            }else {
			            	check = 0;
			            }
			        } catch (Exception e) {
			            System.out.println("checkBookmark() error"+e);
			            e.printStackTrace();
			        } finally {
			            pool.freeConnection(conn, pstmt, rs);
			        }
			        return check;
			    }
			    
			    public Hashtable pageList(String pageNum,int count) {
					
					//페이징 처리결과를 저장할 Hashtable 또는 HashMap객체를 선언
					Hashtable<String,Integer> pgList=new Hashtable<String,Integer>();
					//-----------ListAction부분의 페이징처리 계산부분을 복사(hashtable에 저장)---------------
					int pageSize=10;//int numPerPage->페이지당 보여주는 게시물수
				    int blockSize=10;//int pagePerBlock->블럭당 보여주는 페이지수
				    
				   //게시판을 맨 처음 실행시킬 경우 초기화 설정부분->1페이지(pageNum)
				   if(pageNum==null){//맨 처음에는 선택한 적이 없기때문에 null
					   pageNum="1";//1
				   }
				   //1.nowPage=>currentPage
				   int currentPage=Integer.parseInt(pageNum);//"1"->1	   
				   int startRow=(currentPage-1)*pageSize+1;
				   int endRow=currentPage*pageSize;
				   int number=0;//4.beginPerPage->페이지별로 맨 처음에 나오는 시작 게시물번호
				   number=count-(currentPage-1)*pageSize;
				   
				   System.out.println("현재 레코드수(count)=>"+count+",number=>"+number);
				   
				   //2.총페이지수,시작,종료페이지 계산
				     int pageCount=count/pageSize+(count%pageSize==0?0:1);
				      //2.시작페이지,끝페이지
				      int startPage=0;
				      //10의 배수인지 아닌지(경계선)
				      if(currentPage%blockSize!=0){//1~9,11~19,21~22
				           startPage=currentPage/blockSize*blockSize+1;
				      }else{ //10,20,30,40
				                            //((10/10)-1)*10+1=1
				           startPage=((currentPage/blockSize)-1)*blockSize+1;
				      }
				       int endPage=startPage+blockSize-1;//1+10-1=10
				       System.out.println("pgList메서드의 startPage="+startPage+",endPage="+endPage);
				       //블럭별로 구분해서 링크걸어서 출력
				       if(endPage > pageCount) 
				    	   endPage=pageCount;  //마지막페이지=총페이지수
				      //ListAction(pageNum,count)=>pgList()처리결과 ->ListAction->list.jsp
				       pgList.put("pageSize", pageSize);//<->pgList.get("pageSize")
				       pgList.put("blockSize", blockSize);
				       pgList.put("currentPage", currentPage);
				       pgList.put("startRow", startRow);
				       pgList.put("endRow", endRow);
				       pgList.put("count", count);
				       pgList.put("number", number);
				       pgList.put("startPage", startPage);
				       pgList.put("endPage", endPage);
				       pgList.put("pageCount", pageCount);
				       
					//------------------------
					return pgList; //ListAction-->list.jsp
				}
}
