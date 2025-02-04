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


@WebServlet("/member/info") // 서블릿 URL 매핑
public class InfoController extends HttpServlet {
	
	// [ 내(로그인된) 회원 정보 조회 ]
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. [HTTP 요청의 header body 자료(JSON)를 자바(DTO)로 받는다.]
        // GET 요청은 일반적으로 본문이 없으므로 생략됩니다.

        // 2. [ 데이터 유효성검사 ]
        // 세션에서 로그인된 회원번호를 가져오므로 유효성 검사가 필요하지 않습니다.

        // 3. [ DAO 에게 데이터 전달 하고 응답 받기 ]
		MemberDto result = null; // 조회된 회원 정보를 저장할 변수
		
			// (1) 현재 로그인된 회원의 번호: 세션 객체 내 존재. 속성명: loginMno
		HttpSession session = req.getSession(); // 세션 객체 가져오기
		Object object = session.getAttribute("loginMno"); // 세션 객체 내 지정한 속성 값 가져오기
		
			// (2) 만약에 세션 객체 내 지정한 속성값이 존재하면 로그인 회원번호를 타입 변환한다.
		if (object != null) {
			int loginMno = (Integer)object; // 세션에서 가져온 값을 정수형으로 변환
			
			// (3) 현재 로그인된 회원번호를 매개변수로 전달하여 회원 정보를 조회한다.
			result = MemberDao.getInstance().myInfo(loginMno); // DAO를 통해 회원 정보 조회
		} // if end
		
		// 4. [ 자료(DTO/자바)타입을 JS(JSON)타입으로 변환한다.]
		ObjectMapper mapper = new ObjectMapper(); // JSON 변환을 위한 ObjectMapper 객체 생성
		String jsonResult = mapper.writeValueAsString(result); // 회원 정보를 JSON 문자열로 변환
		
		// 5. [ HTTP 응답의 header body 로 application/json 으로 응답/반환하기]
		resp.setContentType("application/json"); // 응답 타입을 JSON으로 설정
		resp.getWriter().print(jsonResult); // JSON 형식으로 회원 정보 반환
		
	} // doGet end
	
	// [ 내(로그인된) 회원 탈퇴 ]
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. [HTTP 요청의 header body 자료(JSON)를 자바(DTO)로 받는다.]
        // DELETE 요청은 일반적으로 본문이 없으므로 생략됩니다.

        // 2. [ 데이터 유효성검사 ]
        // 세션에서 로그인된 회원번호를 가져오므로 유효성 검사가 필요하지 않습니다.
		
		// 3. [ DAO 에게 데이터 전달 하고 응답 받기 ]
		boolean result = false; // 수정 결과를 저장할 변수
		
		HttpSession session = req.getSession(); // 세션 객체 가져오기
		Object object = session.getAttribute("loginMno"); // 세션 객체 내 지정한 속성 값 가져오기
		
		if (object != null) { // 세션에 로그인된 회원번호가 존재하면
			int loginMno = (Integer)object; // 세션에서 가져온 값을 정수형으로 변환
			
			// DAO를 통해 회원 탈퇴 처리
			result = MemberDao.getInstance().delete(loginMno);
			
			if (result == true) { // 만약에 회원 탈퇴를 성공했다면
				session.removeAttribute("loginMno"); // 세션 객체 내 속성 제거 -> 로그아웃 처리
			} // if end
		} // if end
		
		// 4. [ 자료(DTO/자바)타입을 JS(JSON)타입으로 변환한다.]
        // 5. [ HTTP 응답의 header body 로 application/json 으로 응답/반환하기]
		resp.setContentType("application/json"); // 응답 타입을 JSON으로 설정
		resp.getWriter().print(result); // 탈퇴 결과(성공: true, 실패: false)를 JSON 형식으로 반환
		
	} // doDelete end
	
	// [ 내(로그인된) 회원 정보 수정 ]
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. [HTTP 요청의 header body 자료(JSON)를 자바(DTO)로 받는다.]
		ObjectMapper mapper = new ObjectMapper(); // JSON 파싱을 위한 ObjectMapper 객체 생성
		MemberDto memberDto = mapper.readValue(req.getReader(), MemberDto.class); // HTTP 요청의 JSON 데이터를 MemberDto로 변환
		System.out.println(memberDto); // DTO 객체 출력 (디버깅용)
		
		// 2. [ 데이터 유효성검사 ]
        // 여기에 데이터 유효성 검사 로직을 추가할 수 있습니다.

        // 3. [ DAO 에게 데이터 전달 하고 응답 받기 ]
		boolean result = false; // 수정 결과를 저장할 변수
		
		HttpSession session = req.getSession(); // 세션 객체 가져오기
		Object object = session.getAttribute("loginMno"); // 세션 객체 내 지정한 속성 값 가져오기
		
		if (object != null) { // 세션에 로그인된 회원번호가 존재하면
			int loginMno = (Integer) object; // 세션에서 가져온 값을 정수형으로 변환
			
			memberDto.setMno(loginMno); // 조회된 로그인된 회원번호를 DTO에 설정
			result = MemberDao.getInstance().update(memberDto); // DAO를 통해 회원 정보 수정
		} // if end
		
		// 4. [ 자료(DTO/자바)타입을 JS(JSON)타입으로 변환한다.]
        // 5. [ HTTP 응답의 header body 로 application/json 으로 응답/반환하기]
		resp.setContentType("application/json"); // 응답 타입을 JSON으로 설정
		resp.getWriter().print(result); // 탈퇴 결과(성공: true, 실패: false)를 JSON 형식으로 반환
	} // doPut end

} // class end


/*

	코드 작성 순서 및 기능 설명
	
	내(로그인된) 회원 정보 조회 (doGet 메소드):
		1. 세션에서 로그인된 회원번호 확인:
			- 세션 객체에서 loginMno 속성을 가져옵니다.
			- 속성이 존재하면 로그인 상태로 간주합니다.

		2. DAO를 통해 회원 정보 조회:
			- MemberDao.getInstance().myInfo(loginMno)를 호출하여 로그인된 회원의 정보를 조회합니다.
		3. JSON 변환 및 응답:
			- 조회된 회원 정보를 JSON 형식으로 변환하고, 클라이언트에 반환합니다.

	내(로그인된) 회원 탈퇴 (doDelete 메소드):
		1. 세션에서 로그인된 회원번호 확인:
			- 세션 객체에서 loginMno 속성을 가져옵니다.
			- 속성이 존재하면 로그인 상태로 간주합니다.

		2. DAO를 통해 회원 탈퇴 처리:
			- MemberDao.getInstance().delete(loginMno)를 호출하여 회원 탈퇴를 처리합니다.
			- 탈퇴 성공 시 세션에서 loginMno 속성을 제거하여 로그아웃 처리합니다.

		3. 응답 처리:
			- 탈퇴 결과(성공: true, 실패: false)를 JSON 형식으로 반환합니다.

	내(로그인된) 회원 정보 수정 (doPut 메소드):
		1. HTTP 요청의 JSON 데이터를 DTO로 변환:
			- ObjectMapper를 사용하여 HTTP 요청의 본문(JSON)을 MemberDto 객체로 변환합니다.

		2. 세션에서 로그인된 회원번호 확인:
			- 세션 객체에서 loginMno 속성을 가져옵니다.
			- 속성이 존재하면 로그인 상태로 간주합니다.

		3. DAO를 통해 회원 정보 수정:
			- MemberDao.getInstance().update(memberDto)를 호출하여 회원 정보를 수정합니다.

		4. 응답 처리:
			- 수정 결과(성공: true, 실패: false)를 JSON 형식으로 반환합니다.

*/