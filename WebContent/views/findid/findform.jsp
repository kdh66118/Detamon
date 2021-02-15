<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
			<h1 class="title">아이디 찾기</h1>
			<ul class="search__info-list">
				<li class="search__info-item">
					<label class="chain-label" for="usernm">이름</label>
					<div class="sign__input-frame">
						<input type="text" id="usernm" class="chain-input" name="usernm" maxlength="20" value="" placeholder="이름">
					</div>
				</li>
				<li class="search__info-item">
					<label class="chain-label" for="email">이메일</label>
					<div class="sign__input-frame">
						<input type="email" id="email" class="chain-input" name="email" value="" placeholder="이메일 (email@alba.com)" maxlength="50" autocomplete="off">
					</div>
				</li>
			</ul>
			<button type="button" class="search-submit-btn" onclick="">찾기</button>
			
			<br>
			<hr>
			<br>
			
			<h1 class="title">비밀번호 찾기</h1>
			<ul class="search__info-list">
				<li class="search__info-item">
					<label class="chain-label" for="userid">아이디</label>
					<div class="sign__input-frame">
						<input type="text" id="userid" name="userid" class="chain-input" placeholder="4~15자의 영문, 숫자를 입력하세요." maxlength="15">
					</div>
				</li>
				<li class="search__info-item">
					<label class="chain-label" for="usernm">이름</label>
					<div class="sign__input-frame">
						<input type="text" id="usernm" class="chain-input" name="usernm" maxlength="20" value="" placeholder="이름">
					</div>
				</li>
				<li class="search__info-item">
					<label class="chain-label" for="email">이메일</label>
					<div class="sign__input-frame">
						<input type="email" id="email" class="chain-input" name="email" value="" placeholder="이메일 (email@alba.com)" maxlength="50" autocomplete="off">
					</div>
				</li>
			</ul>
			<button type="button" class="search-submit-btn" onclick="">찾기</button>
		</div>
	</div>
	
</body>
</html>