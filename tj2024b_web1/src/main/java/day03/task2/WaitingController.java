package day03.task2;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import day03.task2.WaitingDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/day03/waiting")
public class WaitingController extends HttpServlet {
	// 1. 대기자 등록 : 등록은 주로 POST  , 요청 데이터는 주로 body
	@Override // HTTP POST BODY : http://localhost:8080/tj2024b_web1/day03/waiting
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. BODY(본문)의 JSON를 DTO로 파싱/변환 하기 위한 인스턴스 생성/준비
		ObjectMapper mapper = new ObjectMapper();
		// 2. 변환
		WaitingDto waitingDto = mapper.readValue(req.getReader(), WaitingDto.class);
		System.out.println(waitingDto);
		// 3. DAO 처리
		boolean result = WaitingDao.getInstance().write(waitingDto);
		System.out.println(result);
		
	}
	
	// 2. 대기자 삭제  : 삭제은 주로 DELETE  , 요청 데이터는 주로 queryString
	@Override // HTTP DELETE queryString : http://localhost:8080/tj2024b_web1/day03/waiting?num=1
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. queryString의 매개변수를 파싱 하기 위한 코드 
		int num = Integer.parseInt(req.getParameter("num"));
		System.out.println("num : " + num);
		// 2 DAO 처리 
		boolean result = WaitingDao.getInstance().delete(num);
		System.out.println(result);
	}
	
}
