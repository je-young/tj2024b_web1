package web.controller.member;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import web.model.dao.MemberDao;
import web.model.dto.MemberDto;


@WebServlet("/member/login") // 서블릿 URL 매핑
public class LoginController extends HttpServlet {
	
	// [ 로그인 ]
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. [HTTP 요청의 header body 자료(JSON)를 자바(DTO)로 받는다.]
		ObjectMapper mapper = new ObjectMapper(); // JSON 파싱을 위한 ObjectMapper 객체 생성
		MemberDto memberDto = mapper.readValue(req.getReader(), MemberDto.class); // HTTP 요청의 JSON 데이터를 MemberDto로 변환
		
		// 2. [ 데이터 유효성검사 ]
		// 여기에 데이터 유효성 검사 로직을 추가할 수 있습니다.
		
		// 3. [ DAO 에게 데이터 전달 하고 응답 받기 ]
		int loginMno = MemberDao.getInstance().login(memberDto); // DAO를 통해 로그인 처리, 로그인 성공 시 회원번호(mno) 반환
		
		// ============== 만약에 로그인 성공 했다면 세션 처리 ============== //
		if (loginMno > 0) { // 로그인 성공 시 (회원번호가 0보다 크면)
			HttpSession session = req.getSession(); // 세션 객체 생성: 톰캣 서버의 저장소/메모리
			// (1) 현재 로그인 성공한 회원번호를 세션 속성에 등록
			session.setAttribute("loginMno", loginMno); // 세션에 속성 추가: 톰캣 서버의 데이터 저장
			// (2) 추후에 로그인 인증에서 사용될 예정
            // (3) 세션의 활성화 유지 시간 설정
			session.setMaxInactiveInterval(60 * 10); // 세션 유지 시간 설정: 60초 * 10 -> 10분
		} // if end
		
		// 4. [ 자료(DTO/자바)타입을 JS(JSON)타입으로 변환한다.]
        // 5. [ HTTP 응답의 header body 로 application/json 으로 응답/반환하기]
		resp.setContentType("application/json"); // 응답 타입을 JSON으로 설정
		resp.getWriter().print(loginMno); // 로그인 결과(회원번호 또는 0)를 JSON 형식으로 반환
		
	} // doPost end
	
	// [ 로그아웃 ]
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. [HTTP 요청의 header body 자료(JSON)를 자바(DTO)로 받는다.]
        // 로그아웃은 일반적으로 요청 본문이 필요하지 않으므로 생략됩니다.

        // 2. [ 데이터 유효성검사 ]
        // 로그아웃은 유효성 검사가 필요하지 않으므로 생략됩니다.

        // 3. [ DAO 에게 데이터 전달 하고 응답 받기 ]
		HttpSession session = req.getSession(); // (1) 세션 객체 불러오기
		Object object = session.getAttribute("loginMno"); // (2) 세션의 속성 값(로그인된 회원번호) 불러오기
		boolean logOut = false; // 로그아웃 성공 여부를 저장할 변수
		
		if (object != null) { // (3) 속성값이 존재하면 (로그인 상태라면)
			session.removeAttribute("loginMno"); // 세션에서 로그인된 회원번호 속성 제거
			logOut = true; // 로그아웃 성공
		} // if end
		
		// 4. [ 자료(DTO/자바)타입을 JS(JSON)타입으로 변환한다.]
        // 5. [ HTTP 응답의 header body 로 application/json 으로 응답/반환하기]
		resp.setContentType("application/json"); // 응답 타입을 JSON으로 설정
		resp.getWriter().print(logOut); // 로그아웃 결과(성공: true, 실패: false)를 JSON 형식으로 반환
		
	} // doDelete end

} // class end

/*
 코드 작성 순서 및 기능 설명
 
 	로그인 (doPost 메소드):
		1. HTTP 요청의 JSON 데이터를 DTO로 변환:
			- ObjectMapper를 사용하여 HTTP 요청의 본문(JSON)을 MemberDto 객체로 변환합니다.
			- 예: {"mid": "user123", "mpwd": "password123"} → MemberDto 객체로 변환.
		2. 데이터 유효성 검사:
			- 필요에 따라 아이디나 비밀번호의 유효성을 검사할 수 있습니다. (현재는 생략됨)
		3. DAO를 통해 로그인 처리:
			- MemberDao.getInstance().login(memberDto)를 호출하여 로그인을 시도합니다.
			- 로그인 성공 시 회원번호(mno)를 반환하고, 실패 시 0을 반환합니다.
		4. 세션 처리:
			- 로그인 성공 시 (loginMno > 0), 세션을 생성하고 로그인된 회원번호를 세션에 저장합니다.
			- 세션 유지 시간을 10분으로 설정합니다.
		5. 응답 처리:
			- 응답 타입을 application/json으로 설정하고, 로그인 결과(회원번호 또는 0)를 JSON 형식으로 반환합니다.

	로그아웃 (doDelete 메소드):
		1. 세션에서 로그인 정보 확인:
			- 현재 세션에서 loginMno 속성을 확인합니다.
			- 속성이 존재하면 로그인 상태로 간주합니다.
		2. 세션에서 로그인 정보 제거:
			- session.removeAttribute("loginMno")를 호출하여 세션에서 로그인된 회원번호를 제거합니다.
			- 로그아웃 성공 시 true를 반환합니다.
		3. 응답 처리:
			- 응답 타입을 application/json으로 설정하고, 로그아웃 결과(성공: true, 실패: false)를 JSON 형식으로 반환합니다.
*/
