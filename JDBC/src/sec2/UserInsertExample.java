package sec2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/*
 * 작성자: 황석현
 * 작성일: 2024-07-23
 * 개요: jdbc 테스트
 * 
 * 
 * */
public class UserInsertExample {
	
	public static void main(String[] args) throws SQLException {
		
		Connection conn = null;
		
		//1.JDBC Driver 메모리로 로딩
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		//2. DB 연결
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", "java", "oracle");
			System.out.println("연결 성공");
			
		//3. DB 작업
			String sql = "INSERT INTO users (userid, username, userpassword, userage, useremail) " + 
					"VALUES (?,?,?,?,?)";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "fall1"); //컬럼 5개 중에 첫번째 값
			pstmt.setString(2, "가을1");
			pstmt.setString(3, "2222");
			pstmt.setInt(4, 25); //나이 라서 int 로 바꿨다
			pstmt.setString(5, "fall@company.com");
			
			int rows = pstmt.executeUpdate();//insert, update, delete 일 때에는 update 를 사용한다
			System.out.println("저장된 행 수 :"+rows);
			pstmt.close();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
