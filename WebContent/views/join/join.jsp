<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>대타몬: 회원가입</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="views/css/join.css">
</head>
<body>
<jsp:include page="../common/header.jsp"></jsp:include>
	<div class="container" >
		<div class="join_container">
			<ul>
				<li class="person">
					<div class="guide model">
						<a href="controller.do?command=joininputform&m_role=1" class="join-link" >
							<span class="join-icon"></span>
							<strong>개인 회원</strong>
							<span class="join-guide-text"> 나에게 딱 맞는 알바정보를 찾는 분 </span>
						</a>
					</div>
				
					<div class="btnAction">
						<a class="btn btnType1 id" href="controller.do?command=joininputform&m_role=1" >가입하기</a>
					</div>
					<div id="OauthFrame"></div>
					
					<hr/>
					
					</li>
								<li class="person">
						<div class="guide model">
							<a href="controller.do?command=joininputform&m_role=2" class="join-link" >
								<span class="biz-icon"></span>
								<strong>기업 회원</strong>
								<span class="join-guide-text"> 알바생을 찾는 분 </span>
							</a>
						</div>
					
						<div class="btnAction">
							<a class="btn btnType2 id" href="controller.do?command=joininputform&m_role=2" >가입하기</a>
						</div>
						<div id="OauthFrame"></div>
						
					</li>

			</ul>
		</div>
	</div>
	<!-- 퓨터 -->
	<%@ include file="../common/footer.jsp" %>
</body>
</html>