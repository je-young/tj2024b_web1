package day04;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/day04/example1")
public class Example1 extends HttpServlet {
	
	// 1. 쿼리스트링 예]
	// http get url: http://localhost:8080/tj2024b_web1/day04/example1?value1=임의값&value2=임의값
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. 쿼리스트링의 매개변수 값 반환 함수.
		String value1 = req.getParameter("value1");
		int value2 = Integer.parseInt(req.getParameter("value2"));
		System.out.println("value1 : " + value1);
		System.out.println("value2 : " + value2);
		// 2. 응답 http header body
		boolean result = true;
		resp.setContentType("application/json"); // boolean 은 json 으로 자동 변환할 수 있다.
		resp.getWriter().print(result);
	} // doGet end
	
	// 2. HTTP header body(본문) 예] http post url : http://localhost:8080/tj2024b_web1/day04/example1
	// body : { "value1" : "유재석" , "value2" : 40 } 
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. 요청 HTTP HEADER BODY(본문) 의 JSON 타입 --> DTO 로 변환
		ObjectMapper mapper = new ObjectMapper();
		ValueDto valueDto = mapper.readValue( req.getReader() , ValueDto.class );
		System.out.println( valueDto );
		// 2. 응답 HTTP header body 
		ValueDto result = new ValueDto("강호동", 23);
			String jsonResult = mapper.writeValueAsString( result ); // DTO 객체를 문자열(JSON)타입으로 변환
		resp.setContentType("application/json"); // DTO는 JSON 으로 자동 변환할 수 없다.
		resp.getWriter().print( jsonResult ); // 변환된 문자열(JSON) 반환
	} // doGet end

} // Example1 end
