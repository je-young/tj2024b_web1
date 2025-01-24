package day07;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/*
	실습2: 아래 [1] [2] [3] [4] 에 코드를 작성하여 아래 구현 실행 결과 와 같이 나오도록 구현하시오.
	새로운 클래스는 생성하지 않습니다.
*/

@WebServlet("/day07/example6")
public class Example6  extends HttpServlet {
	
	//private [1] set = new [2];
	// HashSet을 사용하여 Integer 타입의 값을 저장할 Set 객체를 선언
	private Set<Integer> set = new HashSet<Integer>();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 클라이언트가 POST 방식으로 요청을 보냈을 때 실행
		System.out.println("/day07/example6 POST OK ");
		
		// [3] Jackson 라이브러리의 ObjectMapper를 사용하여 JSON 데이터를 객체로 변환
		ObjectMapper mapper = new ObjectMapper();
		
		// 요청에서 받은 JSON 데이터를 HashSet으로 변환하여 set 변수에 저장
		set = mapper.readValue(req.getReader(), HashSet.class);
		
		// 응답 콘텐츠 타입을 JSON으로 설정
		resp.setContentType("application/json");
		
		// 클라이언트에게 JSON 형태의 true 값을 응답으로 전송
		resp.getWriter().print(true);
		
	} // doPost end
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 클라이언트가 GET 방식으로 요청을 보냈을 때 실행
		System.out.println("/day07/example6 GET OK ");

		// [4] Jackson 라이브러리의 ObjectMapper를 사용하여 객체를 JSON 문자열로 변환
		ObjectMapper mapper = new ObjectMapper();
		
		// set 객체를 JSON 문자열로 변환
		String jsonResult = mapper.writeValueAsString(set); // List Map을 JSON 문자열로 변환 
		
		// 응답 콘텐츠 타입을 JSON으로 설정
		resp.setContentType("application/json");
		
		// 변환된 JSON 문자열을 클라이언트에게 응답으로 전송
		resp.getWriter().print(jsonResult);
		
	} // doGet end

} // class end







