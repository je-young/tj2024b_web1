package day02.task1;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/day02/waiting") // 매핑주소
public class WaitingController extends HttpServlet{
	
	@Override // doPost()
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. 대기번호 등록
		String phone = req.getParameter("phone");
		int count = Integer.parseInt(req.getParameter("count"));
		
		// 2. 대기조회
		boolean result = WaitingDao.getInstance().write(phone, count);
		System.out.println(result);

	} // doPost end
	
	
	@Override // doDelete()
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int num = Integer.parseInt(req.getParameter("num"));
		boolean result = WaitingDao.getInstance().delete(num);
		System.out.println(result);
	}

} // class end
