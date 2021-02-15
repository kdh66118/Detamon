<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>    
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="views/css/findid.css">
</head>
<body>
<%@ include file="../common/header.jsp" %>
	<div class="conatainer">
		<div class="search_container">
			<form action="" method="">
				<h1 class="title">비밀번호 찾기 결과</h1>
				<c:choose>
					<c:when test="${findpw == null || findpw == '' }">
						<ul class="search__info-list">
							<li class="search__info-item">
								<p id="second_title"><span id="find_id">${findid }</span>님의 비밀번호 찾기 결과</p>
								<span id="no_res">검색 결과가 없습니다.</span>
							</li>
						</ul>
						<button type="button" class="find_id_resbtn" onclick="location.href='controller.do?command=home'">홈</button>
						<button type="button" class="find_id_resbtn" onclick="location.href='controller.do?command=findpwform'">비밀번호 찾기</button>
					</c:when>
					<c:otherwise>
						<ul class="search__info-list">
							<li class="search__info-item">
								<p id="second_title"><span id="find_id">${findid }</span>님의 비밀번호 찾기 결과</p>
								<span id="find_res">${findpw }</span>
							</li>
						</ul>
						<button type="button" class="find_id_resbtn" onclick="location.href='controller.do?command=home'">홈</button>
						<button type="button" class="find_id_resbtn" onclick="location.href='controller.do?command=loginform'">로그인</button>
					</c:otherwise>
			</c:choose>				
				
			</form>
			
			
			
		</div>
	</div>
	<!-- 퓨터 -->
	<%@ include file="../common/footer.jsp" %>
</body>
</html>