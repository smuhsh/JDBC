package sec1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * 작성자: 황석현
 * 작성일: 2024-07-23
 * 개요: jdbc 테스트
 * 
 * 
 * */
public class ConnectionExample {

	public static void main(String[] args) throws SQLException {
		
		Connection conn = null;
		
		//1.JDBC Driver 메모리로 로딩
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		//2. DB 연결
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", "java", "oracle");
			System.out.println("연결 성공");
			
		//3. DB 작업
			
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
