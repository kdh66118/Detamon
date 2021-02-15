<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>    
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 중복 확인</title>
<script type = "text/javascript">
onload=function(){
	
	if(${isused == true}){
		opener.document.getElementsByName("id")[0].title="n";
		opener.document.getElementsByName("id")[0].focus();
	}else{
		opener.document.getElementsByName("id")[0].title="y";
		opener.document.getElementsByName("passwd")[0].focus();
	}
	
}
	
</script>
<link rel="stylesheet" type="text/css" href="views/css/idchk.css">
</head>
<body>

	<c:choose>
		<c:when test="${isused == true }">
			<p><span>${id }</span>는 사용 할 수 <span id="chk">없는</span> 아이디 입니다.</p>
		</c:when>
		<c:otherwise>
			<p><span>${id }</span>는 사용 할 수 <span id="chk">있는</span> 아이디 입니다.</p>
		</c:otherwise>
	</c:choose>
	<input type="button" value="확인" onclick="self.close();">
	
</body>
</html>