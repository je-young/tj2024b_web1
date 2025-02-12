<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>채팅 페이지</title>
<!-- 부트스트랩 CSS 추가 -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

	<!-- header.jsp 파일을 현재 JSP 페이지에 포함하여 재사용함 -->
	<jsp:include page="/header.jsp"></jsp:include>

	<div class="container mt-5">
		<div class="row justify-content-center">
			<div class="col-md-8">
				<!-- 메시지 박스 -->
				<div class="card">
					<div class="card-header bg-primary text-white">
						<h5 class="card-title mb-0">채팅</h5>
					</div>
					<div class="card-body">
						<div class="msgbox bg-white p-3 mb-3 border rounded" style="height: 300px; overflow-y: auto;">
							<!-- 메시지가 여기에 표시됩니다 -->
						</div>
						<div class="input-group">
							<textarea class="form-control msginput" placeholder="메시지를 입력하세요..." rows="3"></textarea>
							<button class="btn btn-primary" onclick="onMsgSend()" type="button">전송</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- 부트스트랩 JS 및 의존성 추가 -->
	<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>
	<!-- 사용자 정의 JS -->
	<script src="/tj2024b_web1/js/chatting/chatting.js"></script>

</body>
</html>