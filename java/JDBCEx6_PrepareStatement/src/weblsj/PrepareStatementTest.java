package weblsj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;
import java.util.Scanner;

public class PrepareStatementTest {

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
			beforeInsert(con);
			
			System.out.print("조회할 사원 이름 >>> ");
			Scanner sc = new Scanner(System.in);
			String firstName = sc.nextLine();

			// 실행할 쿼리문 준비
			// 아래의 쿼리문을 inLine 쿼리문 이라고 한다.
			// 쿼리문의 매개변수를 아래처럼 작성 하면 SQL Injection 공격에 취약하게 된다.
			// 때문에 PreparedStatement 클래스를 이용하여 쿼리문을 작성 해야 한다.
//			String query = "select * from employees where first_name = '" + firstName + "'";
			
			// 매개 변수가 사용 될 자리에 매개변수의 변수명 대신에 ?를 넣는다.
			String query = "select * from employees where first_name = ?";
			
			PreparedStatement pstmt = null;
			
			
//			System.out.println(query);

			// Statement 객체 : 쿼리문을 Connection 객체가 연결하고 있는 DB서버로 전송하고 실행하는 객체
//			Statement stmt = null;

			// ResultSet 객체 : 쿼리문이 실행된 후의 결과 테이블을 담고 있는 객체. 단반향으로만 탐색 가능(읽기전용)
			ResultSet rs = null;
			try {
				pstmt = con.prepareStatement(query); // prepareStatement	객체 생성 (sql 문장이 pre-compiling됨)
				
				// 매개변수 "?" 를 변수로 할당
				pstmt.setString(1, firstName);
				
				rs = pstmt.executeQuery(); // 실행할 때도 executeQuery() 를 호출해야 함.

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
				pstmt.close();
				con.close(); // 연것과 반대로 순서로 close() 해준다. // 정상종료 되었기 때문에 DB에는 Commit이 된다.
								// dcl을 날렸다면 rollBack or Commit 해준다.

			} catch (Exception e) {
				if (e instanceof SQLSyntaxErrorException) {

					System.out.println("쿼리문 에러");
				}
			}

		}
	}

	private static void beforeInsert(Connection con) {
		
		int deptNo = 310;
		String dName = "자재부";
		int managerId = 145;
		int locationId = 2500;
		
		if (con != null) {
			String query = "insert into departments values(?,?,?,?)";
			
			PreparedStatement pstmt = null;
			try {
				pstmt = con.prepareStatement(query);
				pstmt.setInt(1, deptNo);
				pstmt.setString(2, dName);
				pstmt.setInt(3, managerId);
				pstmt.setInt(4, locationId);
				
				int result = pstmt.executeUpdate();
				
				if(result == 1) {
					System.out.println("저장 성공");
					
					pstmt.close();
					
//					con.close(); // 여기서 con을 닫을 경우에는 다음에 이어지는 select문이 작동하지 않기 때문에 닫아주지 않는다
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}

}

