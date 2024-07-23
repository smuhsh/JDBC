package sec5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * 작성자: 황석현
 * 작성일: 2024-07-23
 * 개요: jdbc 테스트 select
 * 
 * 
 * */
public class UserSelectExample {
	
	public static void main(String[] args) throws SQLException {
		
		Connection conn = null;
		
		//1.JDBC Driver 메모리로 로딩
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		//2. DB 연결
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", "java", "oracle");
			System.out.println("연결 성공");
			
		//3. DB 작업
			String sql = "SELECT userid, username, userpassword, userage, useremail " +
						"FROM users "+
						"WHERE userid = ?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "winter"); //컬럼 5개 중에 첫번째 값
			
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				String userId = rs.getString("userid");
				String userName = rs.getString("username");
				String userPassword = rs.getString("userpassword");
				String userAge = rs.getString(4);
				String userEmail = rs.getString("useremail");
				
				System.out.println("userId :" + userId);
				System.out.println("userName :" + userName);
				System.out.println("userPassword :" + userPassword);
				System.out.println("userAge :" + userAge);
				System.out.println("userEmail :" + userEmail);
				
			} else {
				System.out.println("사용자 아이디가 존재하지 않습니다.");
			}
			
			//int rows = pstmt.executeUpdate();//insert, update, delete 일 때에는 update 를 사용한다
			//System.out.println("저장된 행 수 :"+rows);
			pstmt.close();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			
			if(conn != null) {
				
				try {
					//4. DB 연결 해제
					conn.close();
				} catch (SQLException e) {
					
				}
				System.out.println("연결 끊기");			
			}
		}
	}
}
