<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="views/css/login.css">
</head>
<body>
<jsp:include page="../common/header.jsp"></jsp:include>
	<div class="container">
		<form action="loginController">
			<div class="login_container">
				<div class="text_div">
					<p>가입하신 아이디와 비밀번호를 입력하세요.</p>
					<p>대타몬에 가입하여 대타 채용을 체험해 보세요.</p>
				</div>
				<div class="inputdiv">
					<input class="input_login" type="text"/>
					<input class="input_login" type="text"/>
					<button class="btn_login">로그인</button>
				</div>
				<div style="padding-top : 10px;	padding-bottom : 60px;">
					<p>
						<a>아이디 찾기</a>ㅣ
						<a>비밀번호 찾기 찾기</a>ㅣ
						<a>회원가입</a>
					</p>
				</div>
			</div>
		</form>
	</div>
	
</body>
</html>