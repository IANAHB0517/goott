package weblsj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteTest {

	public static void main(String[] args) {

		// Connect 객체 생성 및 연결
		// String 쿼리문 준비
		// Statement 객체 생성
		// ResultSet 객체 생성

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

		int deptNo = 280;
		String query = "DELETE from departments where department_id = " + deptNo;
		System.out.println(query);
		Statement stmt = null;
		int result = 0;
		
		if (con != null) {
			try {
				con.setAutoCommit(false);
				stmt = con.createStatement();
				result = stmt.executeUpdate(query);
				
				if(result == 1) {
					System.out.println("행이 삭제 되었습니다.");
				}
				
				if(result == 0) {
					System.out.println("행이 삭제 되지 않았습니다.");
					// 업데이트와 마찬가지로 삭제할 행이 없으면 작업을 수행하지 않으므로 0을 반환한다.
				}
				
				con.rollback();
				con.commit();
				
//				con.rollback(); // auto 커밋이 비활성화 일때만 사용가능 하다는데 oracle 자동커밋 비활성화상태인데 안된다 왜지
								// transaction 객체가 있을때만 사용가능
				stmt.close();
				con.close();
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
