package weblsj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionTest {

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
			System.out.println("DB연결 성공 ! :" + con.toString());
			try {
				con.close();
			} catch (SQLException e) {
				//
				e.printStackTrace();
			}
		}
	}

}
