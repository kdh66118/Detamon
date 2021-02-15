<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>대타몬: 회원 정보수정</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<%@ page import="detamon.dto.MemberDto" %>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
function findAddr(input){
	
	 new daum.Postcode({
       oncomplete: function(data) {
           // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
           // 예제를 참고하여 다양한 활용법을 확인해 보세요.
           var addr = data.address; // 최종 주소 변수
           
           $(input).val(addr);
           $("#postaddr").val(addr);
           
          
       }
   }).open();
	
};

</script>
<% MemberDto dto = (MemberDto)session.getAttribute("login"); %>
</head>
<body>
<jsp:include page="../common/header.jsp"></jsp:include>
<br>
<h1 style="font-size:30px; margin-left:43%;">회원 정보 수정 </h1>
<br>
	<form id="fo" method="post" action="controller.do?command=membermodify">
	
	<div style="border:1px solid black;margin-left:30%; height:400px; width:700px;">
	
	
		<input type="hidden"  name="id" value="<%=dto.getM_id() %>">
		<input type="hidden"  name="pw" value="<%=dto.getM_pw() %>">
		<h1 id="h1"style="font-size:20px; margin:20px;">아이디 : <%=dto.getM_id() %>	<br></h1>
		<h1 id="h1" style="font-size:20px; margin:20px;">회원등급 :
		<%
			if(dto.getM_role()==2){		
		%>
		사장</h1>
		<%
			}else{
		%>
		알바</h1>
		<%
			}
		%>
		
		<h1 id="h1"style="font-size:20px; margin:20px;">휴대폰 : <input name="phone" type="text" value="<%=dto.getM_phone() %>">	<br></h1>
		<h1 id="h1" style="font-size:20px ; margin:20px;">이메일 : <input name="email" type="text" value="<%=dto.getM_email() %>">	<br></h1>
			<ul class="join__info-list">
			<li>
		<label class="chain-label" for="addr" style="font-size:20px; margin-left:20px; ">주소 :</label>
			<div class="sign__input-frame" style="width:100px;height:30px; float:right; margin-right:75%;margin-top:5px;" >
				<input type="text" id="addr" class="chain-input" name="addr"  value="<%=dto.getM_addr() %>" maxlength="1000" autocomplete="off" 
				onclick="findAddr(this);" >
							
			</div>
			</li>
		</ul>			
		<h1 id="h1" style="font-size:20px ; margin:20px;">이름 : <input name="nickname" type="text" value="<%=dto.getM_name() %>">	<br></h1>
	
	
	
	
	<button type="submit" class="btn btn-outline-warning btn-sm" style="margin-left:50%; margin-bottom:70%;">수정</button>
	</div>
	</form>

	











<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>