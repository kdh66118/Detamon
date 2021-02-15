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
	function chkFindPw(frm){
		if( $("#id").val().trim()=="" || $("#id").val()==null ){
			alert("아이디를 입력해주세요");
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
				<input type="hidden" name="command" value="findpw">
				<h1 class="title">비밀번호 찾기</h1>
				<ul class="search__info-list">
					
					<li class="search__info-item">
						<label class="chain-label" for="id">아이디</label>
						<div class="sign__input-frame">
							<input type="text" id="id" class="chain-input" name="id" value="${id }" placeholder="아이디를 입력해주세요" maxlength="50" autocomplete="off">
						</div>
					</li>
					
				</ul>
				<button type="button" class="find_id_resbtn" onclick="location.href='controller.do?command=home'">홈</button>
				<button type="button" class="find_id_resbtn" onclick="chkFindPw(this.form);">비밀번호 찾기</button>
				
			</form>
			
			
			
		</div>
	</div>
	<!-- 퓨터 -->
	<%@ include file="../common/footer.jsp" %>
</body>
</html>