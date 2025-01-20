package day03.task4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class WaitingDao {
	private Connection conn;
	private static WaitingDao insttance = new WaitingDao();
	private WaitingDao() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/waitingDB", 
					"root" , "2965");
		} catch (Exception e) {
			System.out.println(e);
		}
	} // WaitingDao() end
	public static WaitingDao getInstance() { return insttance; }
	
	// 1. 대기자 등록 SQL
	public boolean write(WaitingDto waitingDto) {
		try {
			String sql = "insert into waiting(phone,count) values(?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, waitingDto.getPhone());
			ps.setInt(2, waitingDto.getCount());
			int count1 = ps.executeUpdate();
			if (count1 == 1) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		return false;
	} // write end
	
	// 2. 대기자 전체 조회
	public ArrayList<WaitingDto> findAll() {
		ArrayList<WaitingDto> result = new ArrayList<>();
		try {
			String sql = "select * from waiting";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				WaitingDto waitingDto = new WaitingDto();
				waitingDto.setNum(rs.getInt("num"));
				waitingDto.setPhone(rs.getNString("phone"));
				waitingDto.setCount(rs.getInt("count"));
				result.add(waitingDto);
			} // while end
		} catch (SQLException e) {
			System.out.println(e);
		}
		return result;
	} // findAll end
	
	// 3. 대기자 수정 SQL
	public boolean update(WaitingDto waitingDto) {
		try {
			String sql = "update waiting set phone = ? , count = ? " + "where num = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, waitingDto.getPhone());
				ps.setInt(2, waitingDto.getCount());
				ps.setInt(3, waitingDto.getNum());
			int count = ps.executeUpdate();
			if (count == 1) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		return false;
	} // update end
	
	// 4. 대기자 삭제 SQL
	public boolean delete(int num) {
		try {
			String sql = "delete from waiting where num = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1 , num);
			int phone = ps.executeUpdate();
			if (phone == 1 ) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		return false;
	} // delete end

} // class end
