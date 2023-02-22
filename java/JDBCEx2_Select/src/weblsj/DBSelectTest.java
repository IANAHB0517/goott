package weblsj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;
import java.util.Scanner;

public class DBSelectTest {

	public static void main(String[] args) {

		// Connect 객체 생성 및 연결
		// String 쿼리문 준비
		// Statement 객체 생성
		// ResultSet 객체 생성

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
			System.out.print("조회할 사원 번호 >>> ");
			Scanner sc = new Scanner(System.in);
			int empNo = sc.nextInt();

			// 실행할 쿼리문 준비
//			String query = "select * from employees where employee_id = " + empNo;
			String query = "select * from employees";

			// Statement 객체 : 쿼리문을 Connection 객체가 연결하고 있는 DB서버로 전송하고 실행하는 객체
			Statement stmt = null;

			// ResultSet 객체 : 쿼리문이 실행된 후의 결과 테이블을 담고 있는 객체. 단반향으로만 탐색 가능(읽기전용)
			ResultSet rs = null;
			try {
				stmt = con.createStatement();// Statement 객체 반환
				rs = stmt.executeQuery(query); // stmt으로 부터 쿼리문을 실행 // rs가 쿼리문의 실행 결과를 가지게 됨

				while (rs.next()) { // 결과 행(row)가 있을 동안 반복
					System.out.println(rs.getInt("EMPLOYEE_ID") + " " + rs.getString("FIRST_NAME") + " "
							+ rs.getString("LAST_NAME") + " " + rs.getString("EMAIL") + " "
							+ rs.getString("PHONE_NUMBER") + " " + rs.getDate("HIRE_DATE") + " "
							+ rs.getString("JOB_ID") + " " + rs.getInt("SALARY") + " " + rs.getFloat("COMMISSION_PCT")
							+ " " + rs.getInt("MANAGER_ID") + " " + rs.getInt("DEPARTMENT_ID"));
				}

//				if (rs == null) { // rs 자체는 null 이 아님
//					System.out.println("no Data!");
//				}

				rs.close();
				stmt.close();
				con.close(); // 연것과 반대로 순서로 close() 해준다. // 정상종료 되었기 때문에 DB에는 Commit이 된다.
								// dcl을 날렸다면 rollBack or Commit 해준다.

			} catch (Exception e) {
				if (e instanceof SQLSyntaxErrorException) {

					System.out.println("쿼리문 에러");
				}
			}

		}
	}

}

// DB에 연결되어 있는 Connection 객체를 통해서 쿼리문을 보내야 하기 때문에 Statement는 Connection 객체로 부터 만들어져야한다.