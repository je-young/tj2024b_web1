<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3> DAY03 WAITING2 화면 구현 </h3>
	
	<div>
		<div> 대기번호 작성 </div>
		전화번호 : <input class="phoneInput" /> <br/>
		인원수 : <input class="countInput" /> <br/>
		<button onclick="waitingWrite()" type="button"> 대기등록 </button> 
	</div>
	
	<div>
		<div> 대기자 목록 </div>
		<table border="1">			<!--  테이블  -->
			<thead>		<!--  테이블 제목 구역  -->
				<tr> 		<!--  행 추가  -->
					<th> num </th> <!-- 제목[열] 추가 -->
					<th> phone </th> 
					<th> count </th> 
					<th> etc </th> 
				</tr>
			</thead>
			<tbody>		<!--  테이블 본문 구역 -->
				<!-- JS에서 innerHTML 이용하여 HTML 넣을예정  -->
			</tbody>
		</table>
	</div>

	<script src="task2.js"></script>
</body>
</html>