<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>loginForm.jsp</title>
<link href="/Board_Ajax/css/login.css" type="text/css" rel="stylesheet">
<script src="<%=request.getContextPath()%>/js/jquery-3.7.0.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
  <script>
  	$(function(){
  		$(".join").click(function(){
  			location.href="join.net";
  		});
  		
  		const id='${id}';
  		if(id){
  			$("#id").val(id);
  			$("#remember").prop('checked', true);
  		}
  	})
  
  </script>
</head>
<body>
	<form name="loginform" action="loginProcess.net" method="post">
		<h1>로그인</h1>
		<hr>
		<b>아이디</b>
			<input type="text" name="id" placeholder="Enter id" id="id" required>
			
		<b>Password</b>
			<input type="password" name="pass" placeholder="Enter password" required>
			<input type="checkbox" id="remember" name="pass" value="store">
			<span>remember</span>
				
		<div class="clearfix">
			<button type="submit" class="submitbtn">로그인</button>
			<button type="button" class="join">회원가입</button>
		</div>		
	</form>
</body>
</html>