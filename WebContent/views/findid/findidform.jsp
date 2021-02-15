<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="views/css/findid.css">
<!--  jQuery -->
<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
	function chkFindId(frm){
		if( $("#name").val().trim()=="" || $("#name").val()==null ){
			alert("이름을 입력해주세요");
		}else if( $("#rno").val().trim()=="" || $("#rno").val()==null ){
			alert("주민등록번호를 입력해주세요");
		}else{
			frm.submit();
		}
		
	}
	
</script>
</head>
<body>
<%@ include file="../common/header.jsp" %>
	<div class="conatainer">
		<div class="search_container">
			<form action="controller.do" method="post">
				<input type="hidden" name="command" value="findid">
				<h1 class="title">아이디 찾기</h1>
				<ul class="search__info-list">
					<li class="search__info-item">
						<label class="chain-label" for="name">이름</label>
						<div class="sign__input-frame">
							<input type="text" id="name" class="chain-input" name="name" maxlength="20" value="" placeholder="이름">
						</div>
					</li>
					<li class="search__info-item">
						<label class="chain-label" for="rno">주민등록번호</label>
						<div class="sign__input-frame">
							<input type="email" id="rno" class="chain-input" name="rno" value="" placeholder="000000-0000000" maxlength="50" autocomplete="off">
						</div>
					</li>
				</ul>
				<button type="button" class="search-submit-btn" onclick="chkFindId(this.form);">찾기</button>
			</form>
			
			
			
		</div>
	</div>
	<!-- 퓨터 -->
	<%@ include file="../common/footer.jsp" %>
</body>
</html>