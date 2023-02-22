package weblsj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;

public class InsertTest {

	public static void main(String[] args) {
		
		// 오라클에 접속하기 위해 필요한 정보 (아이디, 패스워드, DB서버의 주소)
		String id = "hr";
		String pwd = "1234";
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe"; // db끼리 db주소라는 것을 알려주는 형식
															// :데이터베이스종류:드라이브형식:주소:포트번호:스키마아이디

		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); // 드라이버가 있는지

			con = DriverManager.getConnection(url, id, pwd); // 실제 Connection 객체를 얻어옴

		} catch (ClassNotFoundException e) {
			System.out.println("드라이버를 찾을 수 없습니다.");

		} catch (SQLException e) {
			System.out.println("DB에 연결하지 못했습니다.");

		}

		if (con != null) {
			int deptNo= 290;
			String dName = "실험부";
			int managerId = 200;
			int locationId = 5700;
			
			String query = "insert into departments values(" + deptNo + ", '" + dName + "', " +  managerId + ", " + locationId + ")";
			System.out.println(query);
			
			// 2) Statement 객체
			
			// 3) select문을 제외한 DML문(insert, update, delete) 모두 executeUpdate() 메서드 호출해서 실행
			Statement stmt = null;
			try {
				stmt = con.createStatement();
				int result =  stmt.executeUpdate(query); // 쿼리문 실행 하고 영향 받은 행의 갯수를 result에 가져옴
				
				if (result == 1) {
					System.out.println("저장 완료");
				}
				if (result == 0) {
					System.out.println("저장 하지 못 함");
				}
				
				
				stmt.close();
				con.close();
				
			} catch (Exception e) {
				
				if (e instanceof SQLIntegrityConstraintViolationException) {
					System.out.println("프라이머리키 오류!");
				}
				
				if (e instanceof SQLException) {
					System.out.println("쿼리문 오류!");
				}
				
			}
			
		}

	}

}
