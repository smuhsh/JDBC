package sec6;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/*
 * 작성자: 황석현
 * 작성일: 2024-07-23
 * 개요: jdbc 프로시저 테스트
 * 
 * 
 * */
public class ProcedureExample {
	
	public static void main(String[] args) throws SQLException {
		
		Connection conn = null;
		
		//1.JDBC Driver 메모리로 로딩
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		//2. DB 연결
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", "java", "oracle");
			System.out.println("연결 성공");
			
		//3. DB 작업
			String sql = "{call user_create(?,?,?,?,?,?)}";
			
			CallableStatement cstmt = conn.prepareCall(sql);
			
			cstmt.setString(1, "java11");
			cstmt.setString(2, "자바11");
			cstmt.setString(3, "1234");
			cstmt.setInt(4, 25);
			cstmt.setString(5, "java@company.com");
			cstmt.registerOutParameter(6, Types.INTEGER);
			
			cstmt.execute();
			
			int rows = cstmt.getInt(6);
			System.out.println("저장된 행 수 :"+rows);
			cstmt.close();			
			
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
