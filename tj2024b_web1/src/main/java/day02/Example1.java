package day02;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


//자바의 클래스를 서블릿 클래스로 만들기 
//1. 클래스명 extends HttpServlet
//2. 클래스선언부 위에 @WebServlet("http주소체계정의") : 
	// 주의할점 : 주소는 아무거나 하되 프로젝트내 중복 불가능하다.
	// @WebServlet("http://localhost:8080/tj2024b_web1/day02/example1") : 절대경로
	// @WebServlet("(프로젝트명 이하생략)/day02/example1")					: 상대경로 
//3. http 요청 받은 방법(함수==기능==메소드==행위) 정의
	// 1. doGet , 2. doPost , 3. doPut , 4. doDelete ===> 4종 구현하면 Rest(휴식)
	// 2. HttpServlet 클래스에서 이미 http method 와 매핑하는 자바 메소드를 상속한다.

@WebServlet("/day02/example1") // http://localhost:8080/tj2024b_web1/day02/example1
public class Example1 extends HttpServlet{
	// 이클립스는 코드가 변경/수정 자동으로 서버에 빌드/배포 : 메뉴 -> 상단[project] -> build auto
			// 1. 서블릿 안에 코드 변경 할 경우는 자동으로 리로드 된다. ctrl+f11 다시 안해도 된다.
			// 2. [서버 재실행]새로운 서블릿은 새로운 매핑HTTP 주소가 서버에 등록(web.xml 자동)해야 하므로 서버를 수동 restart 한다. ctrl+alt+R
	// Restful 구축 : 1.POST 2.GET 3.PUT 4.DELETE
	// 1. POST : dopost + 자동완성 
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("1] HTTP 프로토콜 통신이 POST 방법으로 요청이 왔습니다.");
	}
	
	// 2. GET : doget + 자동완성 
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("2] HTTP 프로토콜 통신이 GET 방법으로 요청이 왔습니다.");
	}
	
	// 3. PUT : doput + 자동완성
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("3] HTTP 프로토콜 통신이 PUT 방법으로 요청이 왔습니다.");
	}
	
	// 4. DELETE : dodelete + 자동완성
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("3] HTTP 프로토콜 통신이 DELETE 방법으로 요청이 왔습니다.");
	}
	
}
