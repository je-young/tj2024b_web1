<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- header.jsp 파일을 현재 JSP 페이지에 포함하여 재사용함 -->
	<jsp:include page="/header.jsp"></jsp:include>
	
	<!-- 캐러셀 -->
	<div id="carouselExample" class="carousel slide">
		<!-- 이미지 본문들 -->
	  <div class="carousel-inner">
	  	<!-- 각 이미지 항목 1 -->
	    <div class="carousel-item active">
	      <img src="/tj2024b_web1/img/main1.jpg" class="d-block w-100" alt="...">
	    </div>
	    <!-- 각 이미지 항목 2 -->
	    <div class="carousel-item">
	      <img src="/tj2024b_web1/img/main2.jpg" class="d-block w-100" alt="...">
	    </div>
	    <!-- 각 이미지 항목 3 -->
	    <div class="carousel-item">
	      <img src="/tj2024b_web1/img/main3.jpg" class="d-block w-100" alt="...">
	    </div>
	    <!-- 각 이미지 항목 4 -->
	    <div class="carousel-item">
	      <img src="/tj2024b_web1/img/main4.jpg" class="d-block w-100" alt="...">
	    </div>
	  </div>
	  <!-- 왼쪽 버튼 -->
	  <button class="carousel-control-prev" type="button" data-bs-target="#carouselExample" data-bs-slide="prev">
	    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
	    <span class="visually-hidden">Previous</span>
	  </button>
	  <!-- 오른쪽 버튼 -->
	  <button class="carousel-control-next" type="button" data-bs-target="#carouselExample" data-bs-slide="next">
	    <span class="carousel-control-next-icon" aria-hidden="true"></span>
	    <span class="visually-hidden">Next</span>
	  </button>
	</div>

</body>
</html>