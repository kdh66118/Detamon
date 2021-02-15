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
<link rel="stylesheet" type="text/css" href="views/css/join.css">
<!--  jQuery -->
<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<!-- 메일 api -->
<script src = "https://smtpjs.com/smtp.js"></script>
<script type= "text/javascript" src="views/js/mail.js"></script>
<script type="text/javascript">
	
	function sendMail(){
		var verificationNum= Math.floor(Math.random()*899999)+100000;
		var email = document.getElementById("email");
		if( $("#email").val().trim()=="" || $("#email").val()==null ){
			alert("이메일을 입력해주세요");
		}else if( $("#email").val() != $("#findemail").val() ){
			alert("본인확인 이메일주소와 이메일주소가 다릅니다!!")
		}
		
		else{
			
			
		    $("#randnum").val(verificationNum);
						
			
			var url = "views/mail/emailSendAction.jsp?randnum="+verificationNum+"&email="+email.value;
			window.open(url,"이메일 전송", "width=100,height=100 location=no,status=no,menubar=no,scrollbars=no,resizable=no");
		
			alert("인증메일을 "+email.value+"로 전송했습니다.");
		}
	}
	//인증번호 확인 함수
	function chkCertNum(){
		if( $("#certnum").val() == $("#randnum").val() ){
			
			$("#checkrand").attr("title", "y");
			$("#checkrand").val("인증성공");
			document.getElementById("checkrand").style.color = "#4075dd";			
			
		}else{
			$("#checkrand").attr("title", "n");
			$("#checkrand").val("인증번호가 다릅니다.");
			document.getElementById("checkrand").style.color = "red";
		}
	}
	
	//submit전 유효성 검사
	function checksumbit(frm){
		
		
		if($("#checkrand").attr("title") == "n"){
			alert("이메일 인증을 해주세요.");	
		}else{
			frm.submit();
		}
		
		//frm.submit();
	}
	
</script>
</head>
<body>
<%@ include file="../common/header.jsp" %>
	<div class="conatainer">
		<div class="search_container">
			<form action="controller.do" method="post">
				<input type="hidden" name="command" value="findpwres">
				<h1 class="title">비밀번호 찾기</h1>
				<c:choose>
					<c:when test="${findemail == null || findemail == '' }">
						<ul class="search__info-list">
							<li class="search__info-item">
								
								<span id="no_res">비밀번호가 존재하지 않습니다.</span>
							</li>
						</ul>
						<button type="button" class="find_id_resbtn" onclick="location.href='controller.do?command=home'">홈</button>
						<button type="button" class="find_id_resbtn" onclick="location.href='controller.do?command=findpwform'">비밀번호 찾기</button>
					</c:when>
					<c:otherwise>
						<ul class="search__info-list">
							<input type="hidden" value="${findid }" id="id" name="id">
							<input type="hidden" value="${findemail }" id="findemail" name="email">
					
							<li class="join__info-item join__info-item--email">
								<label class="chain-label" for="email">이메일</label>
								<div class="sign__input-frame">
									<input type="email" id="email" class="chain-input" name="email" value="" placeholder="이메일 (email@detamon.com)" maxlength="100" autocomplete="off" onclick="idChkConfirm();"
							 		autocomplete="off">
									<button type="button" onclick="sendMail();" class="frame-function-btn">전송</button>
								</div>
							</li>
					
							<li class="join__info-item join__info-item--tel">
								<label class="chain-label" for="certnum">인증번호</label>
								<div class="sign__input-frame">
									<input type="text" id="certnum" name="certnum" class="chain-input" placeholder="인증번호 입력" maxlength="20" onclick="" autocomplete="off" title="n">
									<button type="button" onclick="chkCertNum();" class="frame-function-btn">확인</button>
								</div>
								<br>
								<input type="text" id="checkrand" readonly="readonly" title='n'>
							</li>
							<input type="hidden" id="randnum">
						</ul>
						
						<button type="button" class="find_id_resbtn" onclick="location.href='controller.do?command=home'">홈</button>
						<button type="button" class="find_id_resbtn" onclick="checksumbit(this.form);">비밀번호 찾기</button>
					</c:otherwise>
				</c:choose>					
				
			</form>
			
			
			
		</div>
	</div>
	<!-- 퓨터 -->
	<%@ include file="../common/footer.jsp" %>
</body>
</html>