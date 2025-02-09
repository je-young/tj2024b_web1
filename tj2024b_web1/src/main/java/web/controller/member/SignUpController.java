package web.controller.member;

import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import web.model.dao.MemberDao;
import web.model.dto.MemberDto;
import web.model.dto.PointDto;



@WebServlet("/member/signup") // 서블릿 URL 매핑
public class SignUpController extends HttpServlet{
	
	// [ 프로필 등록 가능한 회원가입 ]
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Signup post ok");
		
		// 1. 업로드 경로 가져오기
		String uploadPath = req.getServletContext().getRealPath("/upload"); // 서버의 실제 경로에 "upload" 폴더 경로를 가져옴
		
		// 2. 만일 해당 경로가 없으면 만들어 주기
		File file = new File(uploadPath);
		if (file.exists()) {
			// 경로가 존재하면 아무것도 안함
		}else {
			file.mkdir(); // 경로가 존해하지 않으면 경로(폴더) 생성
		} // if end
		
		// 3. 파일 업로드 설정, DiskFileItemFactory 클래스
		DiskFileItemFactory factory = new DiskFileItemFactory(); // 업로드 설정 객체 생성
		factory.setRepository(file); // 업로드 파일의 임시 저장 경로 설정
		factory.setSizeThreshold(1024 * 1024 * 1024); // 파일 업로드 용량 제한 설정, 1024 -> 1kb , 1024*1024 -> 1mb , 1024*1024*1024->1gb
		factory.setDefaultCharset("UTF-8"); // 파일 이름 인코딩 설정 (한글 지원)
		
		// 4. 설정된 객체를 서블릿 업로드 객체에 대입
		ServletFileUpload fileUpload = new ServletFileUpload(factory);
		
		// 5. HTTP 요청 객체 내 데이터 파싱/가져오기
		String filename = "default.jpg"; // 기본 프로필 이미지 파일명
		try {
			List<FileItem> fileList = fileUpload.parseRequest(req); // HTTP 요청에서 파일 및 폼 데이터를 파싱
			
			// 6. 파싱된 자료들을 반복문으로 하여 하나씩 조회하여 첨부파일 찾기
			for (FileItem item : fileList) { // 향상된 for 문 사용
				// 7. 만약에 조회중인 자료가 일반 텍스트이면
				if (item.isFormField()) {
					// 일반 텍스트 필드는 처리하지 않음
				}else { // 아니면, 조회중인 자료가 첨부파일이면
					if (!item.getName().isEmpty()) { // 첨부파일이 비어있지 않으면
						// 8. UUID 이용한 첨부파일명 조합하기. 예] uuid-파일명, 주의할점: 파일명에 -하이픈을 모두 _언더바로 변경
						filename = UUID.randomUUID().toString() + "-" + item.getName().replaceAll("-", "_");
						// 9. 업로드할 경로와 파일명 조합하여 경로 만들기
						File uploadFile = new File(uploadPath + "/" + filename);
						// 10. 지정한 경로에 업로드하기
						item.write(uploadFile);
					}
				} // if/else end
			} // for end
			
			// 11. 첨부파일 아닌 일반 텍스트/값을 DTO로 직접 파싱
			MemberDto memberDto = new MemberDto();
			memberDto.setMid(fileList.get(0).getString()); // 첫 번째 필드의 텍스트/값 가져오기 (아이디)
			memberDto.setMpwd(fileList.get(1).getString()); // 두 번째 필드의 텍스트/값 가져오기 (비밀번호)
			memberDto.setMname(fileList.get(2).getString()); // 세 번째 필드의 텍스트/값 가져오기 (이름)
			memberDto.setMphone(fileList.get(3).getString()); // 네 번째 필드의 텍스트/값 가져오기 (전화번호)
			memberDto.setMimg(filename); // 업로드된 파일명을 DTO에 설정
			System.out.println(memberDto); // DTO 객체 출력 (디버깅용)
			
			// 12. DAO를 통해 회원가입 처리
			int mno = MemberDao.getInstance().signup(memberDto);
			boolean result = false;
			if (mno > 0) {
				// * 회원가입 성공시 포인트 지급
				PointDto pointDto = new PointDto();
				pointDto.setMno(mno);
				pointDto.setPocomment("회원가입 축하");
				pointDto.setPocount(100);
				MemberDao.getInstance().setPoint(pointDto);
				result = true;
			}
			// 13. 응답 설정 및 결과 반환
			resp.setContentType("application/json"); // 응답 타입을 JSON으로 설정
			resp.getWriter().print(result); // 회원가입 결과를 JSON 형식으로 반환
			
		} catch (Exception e) {
			System.out.println("업로드 실패 : " + e); // 예외 발생 시 에러 메시지 출력
		} // try end
	} // doPost end

	
	// [ 프로필 등록이 불가능한 회원가입 ]
	/*
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(" signup POST OK ");
        // 1. [HTTP 요청의 header body 자료(JSON)를 자바(DTO)로 받는다.]
        ObjectMapper mapper = new ObjectMapper(); // JSON 파싱을 위한 ObjectMapper 객체 생성
        MemberDto memberDto = mapper.readValue(req.getReader(), MemberDto.class); // HTTP 요청의 JSON 데이터를 MemberDto로 변환
        System.out.println("memberDto : " + memberDto); // DTO 객체 출력 (디버깅용)

        // 2. [ 데이터 유효성검사 ]
        // 여기에 데이터 유효성 검사 로직을 추가할 수 있습니다.

        // 3. [ DAO 에게 데이터 전달 하고 응답 받기 ]
        boolean result = MemberDao.getInstance().signup(memberDto); // DAO를 통해 회원가입 처리

        // 4. [ 자료(DTO/자바)타입을 JS(JSON)타입으로 변환한다.]
        // 5. [ HTTP 응답의 header body 로 application/json 으로 응답/반환하기]
        resp.setContentType("application/json"); // 응답 타입을 JSON으로 설정
        resp.getWriter().print(result); // 회원가입 결과를 JSON 형식으로 반환
    }
    */
} // class end


//1.[HTTP 요청의 header body 자료(JSON)를 자바(DTO)로 받는다.]
//2.[ 데이터 유효성검사 ]
//3.[ DAO 에게 데이터 전달 하고 응답 받기 ]
//4.[ 자료(DTO/자바)타입을 JS(JSON)타입으로 변환한다.]
//5.[ HTTP 응답의 header body 로 application/json 으로 응답/반환하기]

/*
	코드 작성 순서 및 기능 설명
	
	1. 프로필 등록 가능한 회원가입 (doPost 메소드):
		- 파일 업로드를 포함한 회원가입을 처리합니다.
		- 업로드 경로 설정:
			- uploadPath를 통해 서버의 실제 경로에 "upload" 폴더를 생성하거나 확인합니다.
		- 파일 업로드 설정:
			- DiskFileItemFactory를 사용하여 파일 업로드 설정을 구성합니다.
			- 파일 크기 제한, 인코딩 설정 등을 지정합니다.
		- 파일 업로드 처리:
			- ServletFileUpload를 사용하여 HTTP 요청에서 파일 및 폼 데이터를 파싱합니다.
			- UUID를 사용하여 고유한 파일명을 생성하고, 파일을 지정된 경로에 업로드합니다.
		- 텍스트 데이터 처리:
			- 폼 데이터를 MemberDto 객체에 매핑합니다.
		- 회원가입 처리:
			- MemberDao를 통해 회원가입을 처리하고 결과를 반환합니다.
		- 응답 처리:
			- 결과를 JSON 형식으로 클라이언트에 반환합니다.

	2. 프로필 등록이 불가능한 회원가입 (주석 처리된 doPost 메소드):
		- 파일 업로드 없이 JSON 형식의 데이터를 받아 회원가입을 처리합니다.
		- JSON 파싱:
			- ObjectMapper를 사용하여 HTTP 요청의 JSON 데이터를 MemberDto 객체로 변환합니다.
		- 회원가입 처리:
			- MemberDao를 통해 회원가입을 처리하고 결과를 반환합니다.
		- 응답 처리:
			- 결과를 JSON 형식으로 클라이언트에 반환합니다.
*/