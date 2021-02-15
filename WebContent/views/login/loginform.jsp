<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>대타몬: 로그인</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="views/css/login.css">
</head>
<body>
<jsp:include page="../common/header.jsp"></jsp:include>
	<div class="container">
	
			<div class="login_container">
				<div class="text_div">
					<p>가입하신 아이디와 비밀번호를 입력하세요.</p>
					<p>대타몬에 가입하여 대타 채용을 체험해 보세요.</p>
				</div>
				<div class="inputdiv">
					<form action="controller.do" method="post">
					<input type="hidden" name="command" value="login">
					<input class="input_login" type="text" placeholder="아이디를 입력해주세요" name="id" autocomplete="off"/><br>
					<input class="input_login" type="password" placeholder="비밀번호를 입력해주세요" name="pw" autocomplete="off"/><br>
					<input class="btn_login" type="submit" value="로그인"><br>
					</form>
				</div>
				<div style="padding-top : 10px;	padding-bottom : 60px;">
					<p>
						<a href="controller.do?command=findidform">아이디 찾기</a>ㅣ
						<a href="controller.do?command=findpwform">비밀번호 찾기</a>ㅣ
						<a href="controller.do?command=joinform">회원가입</a>
					</p>
				</div>
			</div>
		
	</div>
	<!-- 퓨터 -->
	<%@ include file="../common/footer.jsp" %>
</body>
</html>