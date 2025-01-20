package day03.task2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class WaitingDao {
	private Connection conn;
	private static WaitingDao insttance = new WaitingDao();
	private WaitingDao() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/waiting",
					"root" , "2965");
		} catch (Exception e) { System.out.println(e); }
	}
	public static WaitingDao getInstance() { return insttance; }
	
	// 1. 대기자 등록 SQL
	public boolean write(WaitingDto waitingDto) {
		try {
			String sql = "insert into waiting(phone,count)values(?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, waitingDto.getPhone());
			ps.setInt(2, waitingDto.getCount());
			int phone1 = ps.executeUpdate();
			if (phone1 == 1) {
				return true;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	} // write end
	
	// 대기자 삭제 SQL
	public boolean delete(int num) {
		try {
			String sql = "delete from waiting where num = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1 , num);
			int phone = ps.executeUpdate();
			if (phone == 1 ) {
				return true;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}
}
