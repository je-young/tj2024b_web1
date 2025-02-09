<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<jsp:include page="/header.jsp"></jsp:include>
	
	<div class="container"> <!-- container 부트스트램 클래스 -->
	
		<a href="write.jsp"> 글작성 </a>
		<button onclick="location.href='write.jsp'"> 글작성 </button>
		
		<table class="table boardlist"> <!-- table 부트스트랩 클래스 -->
			<thead>
				<tr>
					<th> 번호 </th>
					<th> 제목 </th>
					<th> 작성자 </th>
					<th> 작성일 </th>
					<th> 조회수 </th>
				</tr>
			</thead>
			<tbody>
			
			</tbody>
		</table>
	
	</div>
	
	<script src="/tj2024b_web1/js/board/board.js" type="text/javascript"></script>
	
</body>
</html>