<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<%@ page session="false" %>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link href= "resources/css/LoginPage.css" rel="stylesheet" type="text/css" />
<script src="resources/js/home.js"></script>
</head>
<body>
 <div class="login">
	<div class="login-html">
		<input id="tab-1" type="radio" name="tab" class="sign-in" checked><label for="tab-1" class="tab">로그인</label>
		<input id="tab-2" type="radio" name="tab" class="sign-up"><label for="tab-2" class="tab">회원가입</label>
		<div class="login-form">
			<form action="/MainMemberlogin" method="post" onsubmit="return validateLogin()">
			<div class="sign-in-htm">
				<div class="group">
					<!-- <label for="user" class="label">아이디</label> -->
					<input id="user" type="text" class="input" name="email" placeholder="이메일">
				</div>
				<div class="group">
					<!-- <label for="pass" class="label">패스워드</label> -->
					<input id="pass" type="password" class="input" name="password" data-type="password"placeholder="패스워드">
				</div>
				<div class="group">
					<input id="check" type="checkbox" class="check" checked>
					<label for="check"><span class="icon"></span>로그인정보 기억하기</label>
				</div>
				 
				<div class="social-icons">
				   	<a href="${url}"><img src="/resources/img/naver.png" class="social-icon"></a>
   <!--			    <a href="https://accounts.google.com/o/oauth2/v2/auth?scope=https%3A//www.googleapis.com/auth/drive.metadata.readonly&access_type=offline&include_granted_scopes=true&response_type=code&state=state_parameter_passthrough_value&redirect_uri=https://localhost:8080/login/oauth2/code/google&client_id=22641404320-61a62g9r725i4erqeqrnlhetivm8s95m.apps.googleusercontent.com"> -->
<!-- 				<a href="https://accounts.google.com/o/oauth2/auth?client_id=22641404320-61a62g9r725i4erqeqrnlhetivm8s95m.apps.googleusercontent.com&redirect_uri=http://localhost:8080/login/oauth2/code/google&scope=https://www.googleapis.com/auth/userinfo.email&response_type=code"> -->
<!--  				<img src="resources/img/google.png" class="social-icon"></a> -->
					<c:url value="/oauth2/google/login" var="googleOAuthUrl" />
					<a href="${googleOAuthUrl}"><img src="resources/img/google.png" class="social-icon"></a>
				    <a href="https://kauth.kakao.com/oauth/authorize?client_id=bb6fa36306e975c901f7c29c94043b31&redirect_uri=http://localhost:8080/oauth/kakao&response_type=code"><img src= "resources/img/kakao.png" class="social-icon"></a>
				</div>
				
				<div class="group">
					<input type="submit" class="button" value="로그인">
				</div>
				
				<div class="hr"></div>
				<div class="foot-lnk">
					<a href="#forgot">비밀번호를 잊으셨습니까?</a>
				</div>
			</div>
			</form>
			<form action="/MainMemberJoin" method="post" onsubmit="return validateForm()">
			<div class="sign-up-htm">
			
				<div class="group">
					<input id="email" type="text" class="input" name="email" placeholder="이메일">
				</div>
				<div class="group">
					<input id="pw" type="password" class="input" name="password" data-type="password"placeholder="패스워드">
				</div>
				
				<div class="group">
					<input id="name" type="text" class="input" name="nickname" placeholder="닉네임">
				</div>
				
				<div class="group">
					<input type="submit" class="button" value="회원가입">
				</div>
				
				<div class="hr"></div>
				<div class="foot-lnk">
					<label for="tab-1"><a>이미 회원이십니까?</a></label>
				</div>
			</div>
			</form>
		</div>
	</div>
</div>
   <script src="https://use.fontawesome.com/releases/v5.15.4/js/all.js" crossorigin="anonymous"></script>
   <script type="text/javascript" src="/resources/js/Cookie.js"></script>
</body>
</html>