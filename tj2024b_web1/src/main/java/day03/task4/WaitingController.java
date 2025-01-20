package day03.task4;

import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;

import day03.task4.WaitingDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/day03/waiting2")
public class WaitingController extends HttpServlet {

	// 1. 대기자 등록 : 등록은 주로 POST  , 요청 데이터는 주로 body
	@Override // HTTP POST BODY : http://localhost:8080/tj2024b_web1/day03/waiting
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. HTTP로 부터 요청받은 HTTP HEADER BODY(본문) 를 가져오기.
		ObjectMapper mapper = new ObjectMapper();
		WaitingDto waitingDto = mapper.readValue(req.getReader(), WaitingDto.class);
		// 2. DAO 처리
		boolean result = WaitingDao.getInstance().write(waitingDto);
		// 3. DAO 결과를 HTTP HEADER BODY(본문)으로 응답(response)보내기
		resp.setContentType("application/json"); // (1) 응답할 자료의 타입 명시
		resp.getWriter().print(result); // (2) 응답 자료 보내기.		
	} // doPost end
	
	// 2. 대기자 전체 조회
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ArrayList<WaitingDto> result = WaitingDao.getInstance().findAll();
		// 자바객체(DTO) --> JSON 형식의 문자열 타입변환 
		ObjectMapper mapper = new ObjectMapper();
		String jsonResult = mapper.writeValueAsString( result );
		resp.setContentType("application/json");
		resp.getWriter().print(jsonResult);
	} // doGet end
	
	// 3. 대기자 수정
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. http로 부터 요청(request) 받은 http geader body(본문) 를 DTO 파싱(변환) 가져오기.
		ObjectMapper mapper = new ObjectMapper();
		WaitingDto waitingDto = mapper.readValue(req.getReader(), WaitingDto.class);
		// 2. DAO 처리
		boolean result = WaitingDao.getInstance().update(waitingDto);
		// 3. DAO 결과를 HTTP header body(본문)으로 응답(response)보내기
		resp.setContentType("application/json");
		resp.getWriter().print(result);
	} // doPut end
	
	// 4. 대기자 삭제  : 삭제은 주로 DELETE  , 요청 데이터는 주로 queryString
	@Override // HTTP DELETE queryString : http://localhost:8080/tj2024b_web1/day03/waiting?num=1
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. http로 부터 요청(request) 받은 http queryString 의 매개변수 가져오기
		int num = Integer.parseInt(req.getParameter("num"));
		// 2. DAO 처리
		boolean result = WaitingDao.getInstance().delete(num);
		// 3. DAO 결과를 http header body(본문)으로 응답(response) 보내기
		resp.setContentType("application/json");
		resp.getWriter().print(result);
	}
	
}
