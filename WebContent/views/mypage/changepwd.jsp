<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>대타몬: 비밀번호 변경</title>
<link rel="stylesheet" type="text/css" href="views/css/changepwd.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">
<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
	function change(){
		if($('input[name=newpwd]').val()!=$('input[name=chkpwd]').val()){
			alert("새비밀 번호와 비밀번호 확인 비밀번호가 다릅니다!");
			return false;
		}else{
			
			return true;
		}
	
	}
</script>
</head>

<body>
<jsp:include page="../common/header.jsp"></jsp:include>
	<form id="fo" method="post" action="controller.do?command=changepw" onsubmit='return change();'>
	<h1  style="font-size:30px; margin-left:24%; margin-top:20px; ">비밀번호 변경</h1>
	<div style="height:400px; width:1000px; margin:auto ; border:1px solid black; margin-top:20px; ">
		<h1 id="h1"style="font-size:20px;">현재 비밀번호 : <input autocomplete="off" name="pwd" value="${dto.m_pw }" type="text">	<br></h1>
		<h1 id="h1"style="font-size:20px;">새 비밀번호 : <input autocomplete="off" name="newpwd" type="text">	<br></h1>
		<h1 id="h1" style="font-size:20px;">비밀번호  확인: <input autocomplete="off" name="chkpwd" type="text">	<br></h1>
		<button id="submit" type="submit">변경</button>
	    
	</div>
	</form>




<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>