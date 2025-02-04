package web.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Dao {
	// DB와 연동 결과를 조작하는 인터페이스 
	protected Connection conn;
	
	// 연동할 DB 서버의 URL
	private String dburl = "jdbc:mysql://localhost:3306/jspweb";
	// 연동할 DB 서버의 계정명
	private String dbuser = "root";
	// 연동할 DB 서버의 비밀번호
	private String dbpwd = "2965";
	
	public Dao() {
		try {
			// 1. JDBC 클래스 드라이버 로드 , Class.forName() * 예외처리 try{}catch(){}
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 2. 설정한 경로/계정/비밀번호 로 DB 서버 연동 시도 하고 결과를 ( 구현체 ) 반환 
			conn = DriverManager.getConnection(dburl , dbuser , dbpwd);
		} catch (Exception e) {
			System.out.println("[DB 연동 실패]" + e );
		} // try end
	} // public Dao end
	

} // class end
