<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Kakao 지도 시작하기</title>
<!-- 부트스트랩 CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<!-- sidebar.jsp 파일을 현재 JSP 페이지에 포함하여 재사용함 -->
	<jsp:include page="/kakao/sidebar.jsp"></jsp:include>

	<!-- 카카오 맵 설정 -->
	<div id="map" style="width:100%;height:890px;"></div>
	
	<!-- JQUERY 라이브러리 : 카카오지도에 필요한 라이브러리 -->
	<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
	<!-- 카카오 지도의 클러스터 기능을 사용하기 위해 앱키 뒤 &libraries=clusterer 추가  -->
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=9bb72e86204249e83524ff8cb808b122&libraries=clusterer"></script>
	<!-- 부트스트랩 js 연결 -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
	<!-- js 파일 연결 -->
	<script src="kakao.js"></script>	
</body>

</html>