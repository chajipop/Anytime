package net.member.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
	private DataSource ds;
	
	//생성자에서 JNDI 리소스를 참조하여 Connection 객체를 얻어옵니다.
	public MemberDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		}catch(Exception ex) {
			System.out.println("DB 연결 실패 : " + ex);
		}
	}
	
	public int isId(String id) {
		int result=-1; //DB에 해당 id가 없습니다.
		String sql="select id from member where id= ? ";
		
		try(Connection con=ds.getConnection();
			PreparedStatement pstmt=con.prepareStatement(sql);){
			pstmt.setString(1, id);
			
			try(ResultSet rs=pstmt.executeQuery()){
				if(rs.next()) {
					result=0; //DB에 해당 id가 있습니다.
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	} //isId end

	public int insert(Member m) {
		int result=0; //DB에 해당 id가 없습니다.
		String sql="INSERT INTO member"
				  +" (id, password, name, age, gender, email) "
				  +" VALUES(?, ?, ?, ?, ?, ?)";
		
		try(Connection con=ds.getConnection();
			PreparedStatement pstmt=con.prepareStatement(sql);){
			pstmt.setString(1, m.getId());
			pstmt.setString(2, m.getPassword());
			pstmt.setString(3, m.getName());
			pstmt.setInt(4, m.getAge());
			pstmt.setString(5, m.getGender());
			pstmt.setString(6, m.getEmail());
			
			result = pstmt.executeUpdate(); //삽입성공시 result는 1
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	} //insert end	

	public int isId(String id, String pass) {
		   int result = -1 ;//아이디가 존재하지 않습니다.
		   String sql ="select id, password from member where id = ? ";
		   try(Connection con = ds.getConnection();
		         PreparedStatement pstmt = con.prepareStatement(sql);){
		      pstmt.setString(1, id);
		      try(ResultSet rs = pstmt.executeQuery()){
		         if(rs.next()) {
		            if(rs.getString(2).equals(pass)) {
		               result =1; //아이디와 비밀번호가 일치하지 않는 경우
		            }else {
		               result = 0; //비밀번호가 일치하지 않는 경우
		            }
		         }
		         
		      }catch(SQLException e) {
		         e.printStackTrace();
		      }
		      
		   }catch(Exception e ) {
		      e.printStackTrace();
		   }
		   return result;
		}   //isId end

	public Member member_info(String id) {
		Member m = null;
		String sql = "select * from member where id = ? ";
		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);){
					pstmt.setString(1, id);
					try(ResultSet rs= pstmt.executeQuery()){
						if(rs.next()) {
							m=new Member();
							m.setId(rs.getString(1));
							m.setPassword(rs.getString(2));
							m.setName(rs.getString(3));
							m.setAge(rs.getInt(4));
							m.setGender(rs.getString(5));
							m.setEmail(rs.getString(6));
							m.setMemberfile(rs.getString(7));
						}
					}catch(SQLException e) {
						e.printStackTrace();
					}
				}catch(Exception e) {
					e.printStackTrace();
				}
		return m;
	}


	public int update(Member m) {
		int result=0;
		String sql = "update member set name = ?, age = ? , gender = ? , email = ?, memberfile=? "
				   + " where id = ?";
		try(Connection con = ds.getConnection();
		         PreparedStatement pstmt = con.prepareStatement(sql);){
		         pstmt.setString(1, m.getName());
		         pstmt.setInt(2, m.getAge());
		         pstmt.setString(3, m.getGender());
		         pstmt.setString(4, m.getEmail());
		         pstmt.setString(5, m.getMemberfile());
		         pstmt.setString(6, m.getId());
		         result = pstmt.executeUpdate();
		        
		      } catch (Exception e) {
		    	  e.printStackTrace();
		      }
		return result;
	}

	public int getListCount() {
		String sql= "select count(*) from member";
		int x=0;
		try(Connection con=ds.getConnection();
				PreparedStatement pstmt=con.prepareStatement(sql);){
				
				try(ResultSet rs=pstmt.executeQuery()){
					if(rs.next()) {
						x=rs.getInt(1); 
					}
				}catch(SQLException e) {
					e.printStackTrace();
				}
				
			}catch(Exception ex) {
				ex.printStackTrace();
				System.out.println("getListCount() 에러: "+ex);
			}
			return x;
		} //getListCount() end

	public List<Member> getList(int page, int limit) {
		String member_list_sql = " SELECT * "
							   + " FROM (SELECT ROW_NUMBER() OVER (ORDER BY id) "
							   + " AS rnum, id, password, name, age, gender, email FROM member) "
							   + " WHERE rnum BETWEEN ? AND ?";
	    List<Member> memberList = new ArrayList<>();
	    
	    int startrow = (page - 1) * limit + 1;
	    int endrow = page * limit;

	    try (Connection con = ds.getConnection();
	         PreparedStatement pstmt = con.prepareStatement(member_list_sql);) {

	        pstmt.setInt(1, startrow);
	        pstmt.setInt(2, endrow);

	        try (ResultSet rs = pstmt.executeQuery()) {
	            while (rs.next()) {
	                Member member = new Member();
	                member.setId(rs.getString("id"));
	                member.setPassword(rs.getString("password"));
	                member.setName(rs.getString("name"));
	                member.setAge(rs.getInt("age"));
	                member.setGender(rs.getString("gender"));
	                member.setEmail(rs.getString("email"));
	                memberList.add(member);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return memberList;
	}

	public int getListCount(String searchField, String searchWord) {
	    int count = 0;
	    String sql = " SELECT COUNT(*) AS count FROM member "
	    		   + " WHERE " + searchField + " LIKE ?";

	    try (Connection con = ds.getConnection();
	         PreparedStatement pstmt = con.prepareStatement(sql);) {

	        pstmt.setString(1, "%" + searchWord + "%");

	        try (ResultSet rs = pstmt.executeQuery()) {
	            if (rs.next()) {
	                count = rs.getInt("count");
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return count;
	}

	public List<Member> getList(String searchField, String searchWord, int page, int limit) {
	    List<Member> memberList = new ArrayList<>();
	    String sql = " SELECT * "
	    	 	   + " FROM (SELECT ROW_NUMBER() OVER (ORDER BY id) "
	    	 	   + " AS rnum, id, password, name, age, gender, email FROM member "
	    	 	   + " WHERE " + searchField + " LIKE ?) WHERE rnum BETWEEN ? AND ?";

	    int startrow = (page - 1) * limit + 1;
	    int endrow = page * limit;

	    try (Connection con = ds.getConnection();
	         PreparedStatement pstmt = con.prepareStatement(sql);) {

	        pstmt.setString(1, "%" + searchWord + "%");
	        pstmt.setInt(2, startrow);
	        pstmt.setInt(3, endrow);

	        try (ResultSet rs = pstmt.executeQuery()) {
	            while (rs.next()) {
	                Member member = new Member();
	                member.setId(rs.getString("id"));
	                member.setPassword(rs.getString("password"));
	                member.setName(rs.getString("name"));
	                member.setAge(rs.getInt("age"));
	                member.setGender(rs.getString("gender"));
	                member.setEmail(rs.getString("email"));
	                memberList.add(member);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return memberList;
	}


	
} //class end
