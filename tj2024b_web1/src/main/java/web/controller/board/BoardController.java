package web.controller.board;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.el.parser.ArithmeticNode;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import web.model.dao.BoardDao;
import web.model.dto.BoardDto;
import web.model.dto.PageDto;

@WebServlet("/board")
public class BoardController extends HttpServlet{
	
	// [1] 게시물 쓰기 컨트롤러
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("board post ok");
		
		// json 문자열 --> 특정한 자바 객체 타입으로 변환 기능을 제공하는 라이브러리 객체 생성
		ObjectMapper mapper = new ObjectMapper();
		// http 의 request Body 로 부터 json 문자열을 읽어와서 BoardDto 타입으로 변환하는 readValue 함수 실행
		BoardDto boardDto = mapper.readValue(req.getReader(), BoardDto.class);
		
			// + 현재 로그인된 회원번호를 세션에서 찾아 boardDto 달아주기
			// 요청 정보의 세션 객체 호출
			HttpSession session = req.getSession();
			// 세션 객체내 특정한 속성(로그인된 회원번호)의 값 꺼내기 , .getAttribute("속성명"); , 모든 세션 객체내 속성의 타입은 Object 이다.
			Object object = session.getAttribute("loginMno");
			// 세션 객체내 로그인된 회원번호 속성이 존재하면
			if (object != null) {
				// Object 타입 --> int/Integer 타입으로 변환하기.
				int loginMno = (Integer)object;
				// boardDto 에 로그인된 회원번호 담아주기, 게시물 작성자 == 로그인된 회원
				boardDto.setMno(loginMno);
			} // if end
		
		// 읽어온 자료를 dao 에게 전달하고 결과를 받는다.
		boolean result = BoardDao.getInstance().write( boardDto );
		// http 로 부터 response
		resp.setContentType("application/json");
		resp.getWriter().print(result);
		
	} // doPost
	
	// [2] 게시물 전체 조회 컨트롤러 ( 02/07 +추가 : 카테고리별 , 2/11 +추가 : 페이징 )
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println(" board get ok ");		
		// [1] 요청 매개변수  , cno 카테고리 번호 가져오기. page 번호 가져오기
		int cno = Integer.parseInt(req.getParameter("cno"));
		int page = Integer.parseInt(req.getParameter("page"));
			
			// * 페이징 처리에 필요한 자료를 준비
			// 1. 1페이지당 출력할 게시물 수
			int display = 5; // 페이지 1개당 게시물 5개 출력할 예정
			// 2. 페이지당 조회할 게시물의 시작 번호 , (현재페이지-1) * 페이지당 게시물 수
			int startRow = ( page-1 ) * display; 
				// 게시물이 10개 존재 한다고 가정 : 0번 1번 2번 3번 4번 5번 6번 7번 8번 9번 
				// 1페이지에 시작번호 : 0번 , 2페이지 시작번호 : 4번
			// 3. 특정 카테고리 게시물의 전체 게시물 수 구하기.
			int totalSize = BoardDao.getInstance().getTotalSize( cno );
			// 4. 전체 페이지 수
			int totalPage = 0;
			if (totalSize % display == 0 ) {
				// 전체 게시물 수 나누기 페이지당 게시물수 했을때 나머지가 없으면 몫
				totalPage = totalSize / display;
			}else {
				totalPage = totalSize / display +1; // 몫 + 1
			} // if else end
			// 5. 페이지당 버튼 수
			int btnSize = 5;
			// 6. 시작버튼 번호 구하기
			int startBtn = ((page-1) / btnSize) * btnSize+1;
			// 7. 끝번호 구하기
			int endBtn = startBtn + ( btnSize -1 );
			// * 만약에 끝번호가 전체 페이지 수 보다 커지면 안되므로 끝번호가 전체 페이지수 보다 커지면 전체 페이지수 로 고정
			if (endBtn > totalPage) {
				endBtn = totalPage;
			} // if end
			
        // [2] DAO에게 전체 게시물 요청 하고 결과 받기 
		ArrayList<BoardDto> result = BoardDao.getInstance().findAll( cno , startRow , display );
			// 8.PageDto 객체 만들기
			PageDto pageDto = new PageDto();
			pageDto.setTotalCount(totalSize); // 조회된 전체 게시물 수
			pageDto.setTotalpage(totalPage); // 전체 게시물 페이지 수
			pageDto.setPage(page); // 현재 페이지
			pageDto.setStartbtn(startBtn); // 전체 페이지 수
			pageDto.setEndbtn(endBtn); // 페이징 버튼 끝 번호
			pageDto.setData(result);
			
		// [3] 받은 전체 게시물을 JSON 형식의 문자열로 변환하기 
		ObjectMapper mapper = new ObjectMapper();
		String jsonResult = mapper.writeValueAsString(pageDto); // 9. pageDto 를 json 으로 변환
		// [4] http response 
		resp.setContentType("application.json");
		resp.getWriter().print(jsonResult);
		
	} // doGet end
	
	// [3] 게시물 개별 삭제 컨트롤러 
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("board delete ok");
		// [1] HTTP queryString 매개변수를 가져오기 , URL?bno=1 , req.getParameter("매개변수명") : 반환타입 문자열 이므로 타입변환 필요할 수 있다.
        	// Integer.parseInt( "문자열") : 문자열 타입 --> 정수 타입 변환 함수. 
		int bno = Integer.parseInt(req.getParameter("bno"));
		// [2] Dao 에게 삭제할 번호를 전달하고 결과 받기
		boolean result = BoardDao.getInstance().delete(bno);
		// [3] 결과를 HTTP의 response 로 응답하기 
        resp.setContentType("application/json");        
        resp.getWriter().print(result);  
        
	} // doDelete end
	
	// [4] 게시물 개별 수정 컨트롤러 
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("board put ok");
		// [1]
		ObjectMapper mapper = new ObjectMapper();
		// [2]
		BoardDto boardDto = mapper.readValue(req.getReader(), BoardDto.class);
		// [3]
		boolean result = BoardDao.getInstance().update(boardDto);
		// [4]
		resp.setContentType("application/json");
		resp.getWriter().print(result);
		
	} // doPut end
	

} // class end











