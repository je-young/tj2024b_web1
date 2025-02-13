<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사이드 바</title>
<!-- 부트스트랩 CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>

	<button style="display: none;" class="btn btn-primary 사이드바"
		type="button" data-bs-toggle="offcanvas"
		data-bs-target="#offcanvasExample" aria-controls="offcanvasExample"></button>
	<div class="offcanvas offcanvas-start" tabindex="-1"
		id="offcanvasExample" aria-labelledby="offcanvasExampleLabel">
		<div class="offcanvas-header">
			<h5 class="offcanvas-title" id="offcanvasExampleLabel">약국 상세정보</h5>
			<button type="button" class="btn-close" data-bs-dismiss="offcanvas"
				aria-label="Close"></button>
		</div>
		<div class="offcanvas-body">
			<div class="약국명"></div>
			<div class="전화번호"></div>
			<div class="주소"></div>
		</div>
		Ï
	</div>

	<!-- 카카오 맵 API 연결 -->
	<script type="text/javascript"
		src="//dapi.kakao.com/v2/maps/sdk.js?appkey=9bb72e86204249e83524ff8cb808b122&libraries=clusterer"></script>
	<!-- js 파일 연결 -->
	<script src="kakao.js"></script>
</body>
</html>