<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>대타몬: 회원가입</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="views/css/join.css">
<style>
	input{
	    width: 100%;
	    height: 44px;
	    margin: 1px 0;
	    box-sizing: border-box;
	    border-radius: 0;
	    font-size: 14px;
	    background-color: transparent;
	    -webkit-appearance: none;
	    border: 0;
	    resize: none;
	}
	label{
		margin-bottom : 0 !important;
	}

</style>
<!--  주소 api -->		          
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<!--  jQuery -->
<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<!-- 메일 api -->
<script type= "text/javascript" src="views/js/mail.js"></script>

<script type="text/javascript">
	var verificationNum = 0 ; //이메일 인증번호
	
	
	function idChkConfirm(){
		var chk = document.getElementsByName("id")[0].title;
		if(chk == "n"){
			alert("아이디 중복체크를 해주세요");
			document.getElementsByName("id")[0].focus();
			return true;
		}
	}
	/*아이디 중복체크 */
	function idChk(){
		var doc = document.getElementsByName("id")[0];
		var onlyen = document.getElementById("userid");
		var value = onlyen.value;
	
		
		if(doc.value.trim()=="" || doc.value==null ){
			alert("아이디를 입력해주세요");
		}else if( (value.search(/[^a-z|^A-Z|^0-9]/) != -1) || (value == "") ){//영어만 입력
			alert("영어와 숫자만 입력해주세요!!");
			onlyen.value = "";
			onlyen.focus();
			return false;
		}else{
			var target = "controller.do?command=idchk&id="+doc.value.trim();
			open(target,"아이디 중복확인","width=500,height=200,resizable = no");
		}
		
	}
	/*비밀번호 확인 */
	function pwChk(){
		var pw = document.getElementById("passwd").value; 
		var checkPw = document.getElementById("passwd_chk").value;
		
		if(pw != checkPw){
			document.getElementById("checkpw").value ="비밀번호가 일치하지 않습니다.";
			document.getElementById("checkpw").style.color = "red";
			document.getElementById("checkpw").title="n";
			
		}else{
			document.getElementById("checkpw").value ="비밀번호가 일치합니다.";
			document.getElementById("checkpw").style.color = "#4075dd";
			document.getElementById("checkpw").title="y";
		}
	}
	/* 주소 검색 팝업창뜨는 함수 */
	function findAddr(input){
		if(idChkConfirm()){
			return false;
		}else{
		 new daum.Postcode({
	        oncomplete: function(data) {
	            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
	            // 예제를 참고하여 다양한 활용법을 확인해 보세요.
	            var addr = data.address; // 최종 주소 변수
	            
	            $(input).val(addr);
	            $("#postaddr").val(addr);
	            
	           
	        }
	    }).open();
		}
	};
	//메일 보내는 함수
	function sendMail(){
		
		var email = document.getElementById("email");
		if(email.value.trim()=="" || email.value==null ){
			alert("이메일을 입력해주세요");
		}		
		else{
			//인증 번호 생성
			var verificationNum= Math.floor(Math.random()*899999)+100000;
		    $("#randnum").val(verificationNum);
						
			
			var url = "views/mail/emailSendActionJoin.jsp?randnum="+verificationNum+"&email="+email.value;
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
		
		if($("#userid").val().trim()=="" || $("#userid").val()==null ){
			alert("아이디를 입력해주세요");
		}else if ($("#userid").attr("title") == "n" ){
			alert("아이디 중복검사를 해주세요");
		}else if ($("#passwd").val().trim()=="" || $("#passwd").val()==null ){
			alert("비밀번호를 입력해주세요");
		}else if ($("#passwd_chk").val().trim()=="" || $("#passwd_chk").val()==null ){
			alert("비밀번호 확인을 입력해주세요");
		}else if($("#checkpw").attr("title") == "n"){
			alert("비밀번호를 확인을 해주세요.");
		}else if($("#usernm").val().trim()=="" || $("#usernm").val()==null){
			alert("이름을 입력해주세요.");
		}else if($("#rno").val().trim()=="" || $("#rno").val()==null){
			alert("주민등록번호를 입력해주세요.");
		}else if( $(':radio[name="gender"]:checked').length < 1){
			alert("성별을 체크해주세요");
			
		}else if($("#addr").val().trim()=="" || $("#addr").val()==null){
			alert("주소를 입력해주세요.");
		}else if($("#email").val().trim()=="" || $("#email").val()==null){
			alert("이메일을 입력해주세요.");
		}else if($("#checkrand").attr("title") == "n"){
			alert("이메일 인증을 해주세요.");	
		}else if($("#phone").val().trim()=="" || $("#phone").val()==null){
			alert("전화번호를 입력해주세요.");
		}else{
			frm.submit();
		}
		
		//frm.submit();
	}
	
	
</script>
</head>
<body>
<jsp:include page="../common/header.jsp"></jsp:include>
	<div class="conatainer">
		<div class="join_container">
			<h1 class="title">회원가입</h1>
			<form action="controller.do?command=join" method="post">
				<input type="hidden" name="m_role" value="${m_role }">
			<ul class="join__info-list">
				<li class="join__info-item">
					<label class="chain-label" for="userid">아이디</label>
					<div class="sign__input-frame">
						<input type="text" id="userid" name="id" class="chain-input" placeholder="4~15자의 영문, 숫자를 입력하세요." maxlength="15" title="n" autocomplete="off" required="required">
						<button type="button" onclick="idChk();" class="frame-function-btn">중복확인</button>
					</div>
				</li>
				<li class="join__info-item">
					<label class="chain-label" for="passwd">비밀번호</label>
					<div class="sign__input-frame">
						<input type="password" id="passwd" name="pw" class="chain-input" value="" placeholder="8~16자 영문, 숫자, 특수기호를 입력하세요." maxlength="16" onclick="idChkConfirm();" autocomplete="off" required="required">
					</div>
					
				</li>
				
				
				<li class="join__info-item">
					<label class="chain-label" for="passwd_chk">비밀번호 확인 </label>
					<div class="sign__input-frame">
						<input type="password" id="passwd_chk" name=passwd_chk class="chain-input" value="" placeholder="비밀번호 확인" maxlength="16" onclick="idChkConfirm();"
						onkeyup="pwChk();" autocomplete="off" required="required">
							
					</div>
					<br>
					 <input type="text" id="checkpw" readonly="readonly" title='n'>
				</li>
			</ul>
			<br>
			<ul class="join__info-list">
					<li class="join__info-item">
						<label class="chain-label" for="usernm">이름</label>
						<div class="sign__input-frame">
							<input type="text" id="usernm" class="chain-input" name="name" maxlength="20" value="" placeholder="이름" onclick="idChkConfirm(); " autocomplete="off">
						</div>
					</li>
					
					
					<li class="join__info-item join__info-item--birth">
						<label class="chain-label" for="birthdate">주민등록번호<span class="sub-message">(선택)</span></label>
						<div class="sign__input-frame">
							<input type="tel" id="rno" class="chain-input" name="rno" maxlength="14" value="" placeholder="111111-1111111" onclick="idChkConfirm();" autocomplete="off">
						</div>
					</li>
					
					
					<li class="join__info-item">
						<label class="chain-label" for="male">성별<span class="sub-message">(선택)</span></label>
						<div class="sign__input-frame" style="margin-top: 10px;">
							<div class="custom-control custom-radio custom-control-inline">
							  <input type="radio" id="customRadioInline1" name="gender" class="custom-control-input" value="M">
							  <label class="custom-control-label" for="customRadioInline1">남자</label>
							</div>
							<div class="custom-control custom-radio custom-control-inline">
							  <input type="radio" id="customRadioInline2" name="gender" class="custom-control-input" value="F">
							  <label class="custom-control-label" for="customRadioInline2">여자</label>
							</div>
						</div>
						<span id="msg_gender" class="msg choice"></span>
					</li>
					
					
					<li class="join__info-item inputaddr">
						<label class="chain-label" for="addr">주소</label>
						<div class="sign__input-frame">
							<input type="text" id="addr" class="chain-input" name="addr" value="" placeholder="클릭해주세요" maxlength="1000" autocomplete="off" readonly="readonly"
							onclick="findAddr(this);" >
							
						</div>
					</li>
					
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
							<input type="tel" id="certnum" name="certnum" class="chain-input" placeholder="인증번호 입력" maxlength="20" onclick="idChkConfirm();" autocomplete="off" title="n">
							<button type="button" onclick="chkCertNum();" class="frame-function-btn">확인</button>
						</div>
						<br>
						<input type="text" id="checkrand" readonly="readonly" title='n'>
					</li>
					<input type="hidden" id="randnum">
					
					<li class="join__info-item join__info-item--tel">
						<label class="chain-label" for="htel">휴대폰</label>
						<div class="sign__input-frame">
							<input type="tel" id="phone" class="chain-input" name="phone" value="" placeholder="000-0000-0000" maxlength="13" onclick="idChkConfirm();" autocomplete="off">
						</div>
					</li>

				</ul>
				
				
				<input type="button" class="join-submit-btn" value="가입하기" onclick="checksumbit(this.form);">

				
				</form>
		</div>
	</div>
	<!-- 퓨터 -->
	<%@ include file="../common/footer.jsp" %>
</body>
</html>