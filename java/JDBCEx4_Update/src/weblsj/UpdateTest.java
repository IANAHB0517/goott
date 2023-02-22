package weblsj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateTest {

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
		
		
		if (con != null) {
			String deptName = "무총부";
			int deptNo = 280;
			
			String query = "update departments SET department_name = '" + deptName + "' where department_id = " + deptNo;
//			System.out.println(query);
			Statement stmt = null;
			try {
				stmt = con.createStatement();
				int result = stmt.executeUpdate(query);
				
				if(result == 1) {
					System.out.println("업데이트가 완료되었습니다");
					// 위의 커리의 조건에 부합하는 행이 존재하지 않을 경우 예외조차 나지 않는다.
				}
				if(result == 0) {
					System.out.println("해당 행이 존재 하지 않습니다.");
					// 업데이트가 잘 되었을 때만 executeUpdate()가 1을 반환 하기 때문에 0일 경우에는 실행이 잘 되지 않은 것으로 처리해주었다.
				}
				
				stmt.close();
				con.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
	}

}
