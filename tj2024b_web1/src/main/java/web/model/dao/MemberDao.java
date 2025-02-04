package web.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.CountDownLatch;

import lombok.Getter;
import lombok.NoArgsConstructor;
import web.model.dto.MemberDto;

// @Getter // 클래스내 모든 멤버변수에 getter 적용하는 Lombok 어노테이션
@NoArgsConstructor( access = lombok.AccessLevel.PRIVATE) // 클래스내 디폴트 생성자를 private 으로 설정하는 Lombok 어노테이션
public class MemberDao extends Dao{
	
	// + 싱글톤 : 싱글 패턴 구현
		// [1] 멤버변수에 static 인스턴스를 만든다.
	@Getter // instance 변수에 getter를 적용하는 Lombok 어노테이션
	private static MemberDao instance = new MemberDao();
	
		// [2] 디폴트 생성자를 private 으로 설정한다. (Lombok의 @NoArgsConstructor로 대체됨)
	// private MemberDao() {}
	
	
		// [3] static 인스턴스를 반환하는 메소드 만든다. (Lombok의 @Getter로 대체됨)
	// @Getter
	// public static MemberDao getInstance() { return instanceDao; }
	
	// [1]. 회원가입 SQL 처리 메소드
	public boolean signup( MemberDto memberDto ) {
		try {
			// [1] SQL 작성한다.
			String sql = "insert into member( mid , mpwd , mname , mphone , mimg ) values( ? , ? , ? , ? , ? )";
			// [2] DB와 연동된 곳에 SQL 기재한다.
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, memberDto.getMid()); // 아이디
			ps.setString(2, memberDto.getMpwd()); // 비밀번호
			ps.setString(3, memberDto.getMname()); // 이름
			ps.setString(4, memberDto.getMphone()); // 전화번호
			ps.setString(5, memberDto.getMimg()); // 프로필 이미지
			// [3] 기재된 SQL를 실행하고 결과를 받는다.
			int count = ps.executeUpdate(); // 실행 결과로 영향받은 행의 수를 반환
			// [4] 결과에 따른 처리 및 반환을 한다.
			if (count == 1) {
				return true; // 영향받은 행이 1개면 성공
			} // if end
		} catch (SQLException e) {
			System.out.println(e); // SQL 예외 발생시 예외 출력
		} // try end
		
		return false; // 실패 시 false 반환
	} // signup end
	
	// [2]. 로드인 SQL 처리 메소드
	public int login( MemberDto memberDto ) {
		try {
			// [1] SQL 작성한다.
			String sql = "select mno from member where mid = ? and mpwd = ? ";
			// [2] DB와 연동된 곳에 SQL 기재한다.
			PreparedStatement ps = conn.prepareStatement(sql);
			// [*] SQL 의 ? 에 값을 대입한다.
			ps.setString(1, memberDto.getMid()); // 아이디
			ps.setString(2, memberDto.getMpwd()); // 비밀번호
			// [3] 기재된 SQL 실행하고 결과를 받는다.
			ResultSet rs = ps.executeQuery(); // 쿼리 실행 후 결과셋 반환
			// [4] 결과에 따른 처리 및 반환을 한다.
			if (rs.next()) { // 결과셋에 데이터가 있으면
				int mno = rs.getInt("mno"); // 회원번호(mno)를 가져옴
				return mno; // 회원번호 반환 (0 초과이면 로그인 성공)
			} // if end
		} catch (SQLException e) {
			System.out.println(e); // SQL 예외 발생시 예외 출력
		} // try end
		
		return 0; // 로그인 실패 시 0 반환
	} // login end
	
	// [3]. 내정보 보기 SQL 처리 메소드
	public MemberDto myInfo(int loginMno) {
		try {
			System.out.println(loginMno);
			String sql = "select * from member where mno = ? "; // [1] SQL 작성한다.
			PreparedStatement ps = conn.prepareStatement(sql); // [2] DB와 연동된 곳에 SQL 기재한다.
			ps.setInt(1, loginMno); // [*] 기재된 SQL 에 매개변수 값 대입한다.
			ResultSet rs = ps.executeQuery(); // [3] 기재된 SQL 실행하고 결과를 받는다.
			if (rs.next()) { // [4] 결과에 따른 처리 및 반환을 한다.
				MemberDto memberDto = new MemberDto(); // MemberDto 객체 생성
				memberDto.setMno(rs.getInt("mno")); // 회원번호 설정
				memberDto.setMid(rs.getString("mid")); // 아이디 설정
				memberDto.setMname(rs.getString("mname")); // 이름 설정
				memberDto.setMphone(rs.getString("mphone")); // 전화번호 설정
				memberDto.setMdate(rs.getString("mdate")); // 가입일 설정
				memberDto.setMimg(rs.getString("mimg")); // 프로필 이미지 설정
				return memberDto; // 조회된 회원정보를 반환한다.
			} // if end
		} catch (SQLException e) {
			System.out.println(e); // SQL 예외 발생 시 예외 출력
		} // try end
		
		return null; // 조회된 회원정보가 없을 때 null 반환
	} // myInfo end
	
	// [4]. 회원탈퇴 SQL 처리 메소드
	public boolean delete(int loginMno) {
		try {
			String sql = "delete from member where mno = ? "; // [1] SQL 작성한다.
			PreparedStatement ps = conn.prepareStatement(sql); // [2] DB와 연동된 곳에 SQL 기재한다.
			ps.setInt(1, loginMno); // [*] SQL의 ?에 값을 대입한다. (회원번호)
			int count = ps.executeUpdate(); // [3] 기재된 SQL 실행하고 결과를 받는다.
			if (count == 1) {
				return true; // 영향받은 행이 1개면 성공
			} // if end
		} catch (SQLException e) {
			System.out.println(e); // SQL 예외 발생 시 예외 출력
		} // try end
		
		return false; // 실패 시 false 반환
	} // delete end
	
	// [5] 회원수정 SQL 처리 메소드
	public boolean update(MemberDto memberDto) {
		try {
			// [1] SQL 작성한다.
			String sql = "update member set mpwd = ? , mname = ? , mphone = ? where mno = ? ";
			// [2] DB와 연동된 곳(conn)에 SQL 기재한다.
			PreparedStatement ps = conn.prepareStatement(sql);
			// [*] SQL 의 ? 에 값을 대입한다.
			ps.setString(1, memberDto.getMpwd()); // 비밀번호
			ps.setString(2, memberDto.getMname()); // 이름
			ps.setString(3, memberDto.getMphone()); // 전화번호
			ps.setInt(4, memberDto.getMno()); // 회원번호
			// [3] 기재된 SQL 실행하고 결과를 받는다.
			int count = ps.executeUpdate(); // 실행 결과로 영향받은 행의 수를 반환
			// [4] 결과에 따른 처리 및 반환을 한다.
			if (count == 1) {
				return true; // 영향받은 행이 1개면 성공
			} // if end
		} catch (SQLException e) {
			System.out.println(e); // SQL 예외 발생 시 예외 출력
		} // try end
		
		return false; // 실패 시 false 반환
	} // update end

} // class end


/*
 	코드 작성 순서 및 기능 설명
1. 싱글톤 패턴 구현:
	- MemberDao 클래스는 싱글톤 패턴으로 설계되었습니다. 이는 클래스의 인스턴스가 하나만 생성되도록 보장합니다.
	- private static MemberDao instance = new MemberDao();를 통해 클래스 로딩 시 단일 인스턴스가 생성됩니다.
	- @NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)를 통해 디폴트 생성자를 private으로 설정하여 외부에서의 인스턴스 생성을 방지합니다.
	- @Getter를 통해 instance 변수에 대한 getter 메소드가 자동 생성됩니다.

2. 회원가입 처리 (signup 메소드):
	- MemberDto 객체를 받아 회원 정보를 데이터베이스에 저장합니다.
	- SQL 쿼리를 작성하고, PreparedStatement를 사용하여 SQL을 실행합니다.
	- executeUpdate()를 통해 쿼리를 실행하고, 영향받은 행의 수를 반환받습니다.
	- 영향받은 행이 1개면 성공으로 간주하고 true를 반환합니다.

3. 로그인 처리 (login 메소드):
	- MemberDto 객체를 받아 아이디와 비밀번호를 확인하여 로그인을 처리합니다.
	- SQL 쿼리를 작성하고, PreparedStatement를 사용하여 SQL을 실행합니다.
	- executeQuery()를 통해 쿼리를 실행하고, 결과셋을 반환받습니다.
	- 결과셋에 데이터가 있으면 회원번호(mno)를 반환하고, 없으면 0을 반환합니다.

4. 내정보 보기 (myInfo 메소드):
	- 로그인한 회원의 번호(loginMno)를 받아 해당 회원의 정보를 조회합니다.
	- SQL 쿼리를 작성하고, PreparedStatement를 사용하여 SQL을 실행합니다.
	- executeQuery()를 통해 쿼리를 실행하고, 결과셋을 반환받습니다.
	- 결과셋에 데이터가 있으면 MemberDto 객체를 생성하여 회원 정보를 설정하고 반환합니다.

5. 회원탈퇴 처리 (delete 메소드):
	- 로그인한 회원의 번호(loginMno)를 받아 해당 회원을 데이터베이스에서 삭제합니다.
	- SQL 쿼리를 작성하고, PreparedStatement를 사용하여 SQL을 실행합니다.
	- executeUpdate()를 통해 쿼리를 실행하고, 영향받은 행의 수를 반환받습니다.
	- 영향받은 행이 1개면 성공으로 간주하고 true를 반환합니다.

6. 회원정보 수정 (update 메소드):
	- MemberDto 객체를 받아 회원 정보를 수정합니다.
	- SQL 쿼리를 작성하고, PreparedStatement를 사용하여 SQL을 실행합니다.
	- executeUpdate()를 통해 쿼리를 실행하고, 영향받은 행의 수를 반환받습니다.
	- 영향받은 행이 1개면 성공으로 간주하고 true를 반환합니다.
*/