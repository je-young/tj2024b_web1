package day05;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

//데이터베이스 접근을 위한 DAO(Data Access Object) 클래스
public class BoardDao {
	
	private Connection conn;
	
	// 싱글톤 패턴 적용 (DAO 객체는 하나만 생성되도록 함)
	private static BoardDao instance = new BoardDao();
	private BoardDao() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/mydb0122",
					"root", "2965");
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	public static BoardDao getInstance() {
		return instance;
	}
	
	// [1] 게시물 등록 메서드
	public boolean boardWrite( BoardDto boardDto ) {
		try {
			String sql = "insert into board( btitle , bcontent , bwriter, bpwd ) values( ? , ? , ? , ? )";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, boardDto.getBtitle());
			ps.setString(2, boardDto.getBcontent());
			ps.setString(3, boardDto.getBwriter());
			ps.setString(4, boardDto.getBpwd());
			int count = ps.executeUpdate();
			if (count == 1) {
				return true;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	} // boardWrite end
	
	// [2] 게시글 전체 조회 메서드
	public ArrayList<BoardDto> findAll(){
		ArrayList<BoardDto> list = new ArrayList<BoardDto>();
		try {
			String sql = "select * from board";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while ( rs.next() ) {
				BoardDto boardDto = new BoardDto();
				boardDto.setBno( rs.getInt("bno") );
				boardDto.setBtitle( rs.getString("btitle") );
				boardDto.setBwriter( rs.getString("bwriter") );
				boardDto.setBview( rs.getInt("bview") );
				list.add( boardDto );
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return list;
	} // findAll end
	
	// [3] 게시물 개별 조회
	public BoardDto findById( int bno ) {
		try {
			String sql = "select * from board where bno = ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt( 1 , bno);
            ResultSet rs = ps.executeQuery();
            if( rs.next() ) {
                    BoardDto boardDto = new BoardDto();
                    boardDto.setBno( rs.getInt( "bno" ));
                    boardDto.setBtitle( rs.getString("btitle") );
                    boardDto.setBwriter( rs.getString( "bwriter" ));
                    boardDto.setBdate( rs.getString( "bdate" ));
                    boardDto.setBview( rs.getInt( "bview" ));
                    boardDto.setBcontent( rs.getString("bcontent") );
                    boardDto.setBpwd( rs.getString("bpwd"));
                    return boardDto;
            }
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	} // findById end
	
	// [4] 게시물 수정 메소드
	
	
	
	// [5] 게시물 삭제 메소드
	
	
	
	
	
	
	
	
	
	
	
} // BoardDAO end
