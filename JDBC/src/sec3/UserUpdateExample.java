package sec3;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/*
 * 작성자: 황석현
 * 작성일: 2024-07-23
 * 개요: jdbc 테스트 update
 * 
 * 
 * */
public class UserUpdateExample {
	
	public static void main(String[] args) throws SQLException {
		
		Connection conn = null;
		
		//1.JDBC Driver 메모리로 로딩
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		//2. DB 연결
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", "java", "oracle");
			System.out.println("연결 성공");
			
		//3. DB 작업
			String sql = new StringBuilder()
					.append("UPDATE boards SET ")
					.append("btitle = ?, ")
					.append("bcontent = ?, ")
					.append("bfilename = ?, ")
					.append("bfiledata = ? ")
					.append("WHERE bno = ? ")
					.toString();
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			//FileInputStream fis = new FileInputStream("src/sec3/shhwang.jpg");
			
			
			pstmt.setString(1, "자바"); //컬럼 5개 중에 첫번째 값
			pstmt.setString(2, "자바를 배웁니다");
			pstmt.setString(3, "shhwang.jpg");
			pstmt.setBlob(4, new FileInputStream("src/sec3/shhwang.jpg")); //사진 이라서 Blob 로 바꿨다
			//pstmt.setBlob(4, new FileInputStream("/JDBC/src/sec3/shhwang.jpg")); //사진 이라서 Blob 로 바꿨다
			pstmt.setInt(5, 2);
			
			int rows = pstmt.executeUpdate();//insert, update, delete 일 때에는 update 를 사용한다
			System.out.println("저장된 행 수 :"+rows);
			pstmt.close();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} catch (FileNotFoundException e) {
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
