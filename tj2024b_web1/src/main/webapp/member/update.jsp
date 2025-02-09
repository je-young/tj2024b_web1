<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link href="/tj2024b_web1/css/member/info.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="/header.jsp"></jsp:include>
	<!-- 회원가입 폼  -->
	<div class="container col-xl-10 col-xxl-8 px-4 py-5">
	  <div class="row align-items-center g-lg-5 py-5">    
	    <!-- 회원가입 입력 구역  -->
	    <div class="col-md-10 mx-auto col-lg-5">
	    
	      <form class="p-4 p-md-5 border rounded-3 bg-body-tertiary">
	      
	      	<div class="form-floating mb-3 mimgbox">
	          <img src="" class="mimg" />
	        </div>

	        <div class="form-floating mb-3">
	          <input type="text" readonly name="mid" class="form-control mid" id="floatingInput1" placeholder="계정아이디">
	          <label for="floatingInput1">계정아이디</label>
	        </div>
	        
	        <div class="form-floating mb-3">
	          <input type="text" name="mpwd" class="form-control mpwd" id="floatingInput2" placeholder="새로운비밀번호">
	          <label for="floatingInput4">새로운 비밀번호</label>
	        </div>
	        
	        <div class="form-floating mb-3">
	          <input type="text" name="mname" class="form-control mname" id="floatingInput3" placeholder="닉네임">
	          <label for="floatingInput4">닉네임</label>
	        </div>
	        
	       	<div class="form-floating mb-3">
	          <input type="text" name="mphone" class="form-control mphone" id="floatingInput4" placeholder="연락처">
	          <label for="floatingInput5">연락처</label>
	        </div>
	        
	        <button class="w-100 btn btn-lg btn-primary" type="button" onclick="onUpdate()"> 회원 수정 </button>
	      </form>
	    </div>
	  </div>
	</div>
  	
  	<script src="/tj2024b_web1/js/member/update.js"></script>
  	
</body>
</html>







