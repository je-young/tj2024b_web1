package web.controller.board;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import web.model.dao.BoardDao;
import web.model.dto.BoardDto;

@WebServlet("/board/view")
public class BoardViewController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("boardview get ok");
		
		// [1] 개별 조회할 게시물 번호를 HTTP queryString 의 매개변수 값 가져오기
		int bno = Integer.parseInt(req.getParameter("bno"));
		
		// [2] dao 에게 조회할 게시물 번호를 전달하고 결과를 응답 받는다.
		BoardDto boardDto = BoardDao.getInstance().finddByBno(bno);
		
		// [3] dto 를 json 으로 변환
		ObjectMapper mapper = new ObjectMapper();
		String jsonResult = mapper.writeValueAsString(boardDto);
		
		// [4] 타입 변환된 json형식의 문자열타입 자료를  http response 응답하기
		resp.setContentType( "application/json" );
        resp.getWriter().print( jsonResult );
	} // doGet end

} // class end








