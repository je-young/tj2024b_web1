package day03;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/day03/example4")
public class Example4 extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("[ HTTP *GET 방식으로 요청이 왔어요. ]");
		
		boolean result = true; // 1. 응답할 자료준
		resp.getWriter().print(result); // 2. .getWriter().print( 보낼자료 );
		System.out.println("[ HTTP 로 자료 를 응답을 했습니다. ]");
	} // doGet end
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("[ HTTP *POST 방식으로 요청이 왔어요. ]");
		String result = "JAVA";
		resp.getWriter().print(result);
		System.out.println("[ HTTP 로 자료 를 응답 했습니다. ]");
	} // doPost end
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("[ HTTP *PUT 방식으로 요청이 왔어요. ]");
		int result = 30;
		resp.getWriter().print(result);
		System.out.println("[ HTTP 로 자료 를 응답 했습니다. ]");
	} // doPut end
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("[ HTTP *DELETE 방식으로 요청이 왔어요. ]");
		DataDto result = new DataDto("유재석", 40);
		resp.getWriter().print(result);
		System.out.println("[ HTTP 로 자료 를 응답 했습니다. ]");
	} // doDelete end
	
} // class end
