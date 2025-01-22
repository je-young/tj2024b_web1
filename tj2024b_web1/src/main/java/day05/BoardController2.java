package day05;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/day05/board/view")
public class BoardController2 extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("GET2 OK");
		// 1. HTTP 요청 : 쿼리스트링의 매개변수를 가져온다.
		int bno = Integer.parseInt(req.getParameter("bno"));
		// 2. DAO 처리 요청 후 응답 받기 
        BoardDto boardDto = BoardDao.getInstance().findById( bno );
        // 3. HTTP 응답 
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString( boardDto );
        resp.setContentType("application/json");
        resp.getWriter().print( jsonString );
	} // doGet end
	

} // BoardController2
