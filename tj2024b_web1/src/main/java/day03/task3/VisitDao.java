package day03.task3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VisitDao {
	private Connection conn;
	// + 싱글톤 
	private static VisitDao instance = new VisitDao();
	private VisitDao() {
		try {
			// DB연동 코드 : 
			// 코드 작성전에 필수 : 프로젝트의 mysql-connector-j-9.1.0.jar
			// 라이브러리 폴더 경로 : src -> main -> webapp -> web-inf -> lib 
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/mydb0116",
					"root" , "2965");
		}catch( Exception e ) { System.out.println(e); }
		
	}
	public static VisitDao getInstance() { return instance; }
	
	// 1. 방문록 등록 SQL 
	public boolean write( VisitDto  visitdto ) {
		try {
			String sql = "insert into visit(content,age)values(?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString( 1 , visitdto.getContent() );
			ps.setInt( 2 , visitdto.getAge() );
			int count = ps.executeUpdate();
			if( count == 1 ) return true;
		}catch( SQLException e ) { System.out.println(e);}
		
		return false;
	} // f end 
	
	// 2. 방문록 전체 조회 SQL
	public ArrayList<VisitDto> findAll() {
		ArrayList<VisitDto> list = new ArrayList<VisitDto>();
		try {
			String sql = "select * from visit";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				VisitDto visitDto = new VisitDto();
				visitDto.setNum(rs.getInt("num"));
				visitDto.setContent(rs.getString("content"));
				visitDto.setAge(rs.getInt("age"));
				list.add(visitDto);
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		return list;
	} // findAll end
	
	// 3. 방문록 수정 SQL
	public boolean update( VisitDto visitDto ) {
		try {
			String sql = "update visit "
					+ " set content = ? , age = ? "
					+ " where num = ? ";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString( 1 , visitDto.getContent() );
			ps.setInt( 2 , visitDto.getAge() );
			ps.setInt( 3 , visitDto.getNum() );
			int count = ps.executeUpdate();
			if( count == 1 ) { return true; }
		}catch( SQLException e ) { System.out.println(e);}
		return false;
	} // update end 
	
	// 4. 방문록 삭제 SQL 
	public boolean delete( int num ) {
		try {
			String sql = "delete from visit where num =? ";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt( 1 , num );
			int count = ps.executeUpdate();
			if( count == 1 ) return true;
		}catch( SQLException e ) {
			System.out.println(e);
		}
		
		return false;
	} // f end 
	
}// class end 
