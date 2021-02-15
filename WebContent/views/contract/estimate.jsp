<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">








<title>대타몬:계약하기-평가</title>
<style type="text/css">
	body{
		text-align: center;
	}

	input[type=button]{
			background-color: #ffd203;
			height : 30px;
			border: none;
			border-radius: 20px;
	}
	table{
		position: relative;
		width:100%;
		top: -18px;
		border-spacing: 10px;
	}
</style>
<script type="text/javascript" src=" https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
    
    //팝업창에서 평가점수와 업체Id 알바Id 게시글 번호를 부모창으로 보내준다.
        function test(){
        	var score = document.getElementById("score").value;		
			var alba = document.getElementById("albaId").value;
			var com = document.getElementById("comId").value;
			var boardno = document.getElementById("boardno").value;
			if(score==0){
				alert('입력하시오');
			}else{			
				opener.parent.insertScore(score, alba, com, boardno);
				window.close();
			}
        }
</script>
</head>
<body>
    <h1>평가</h1>
    <table>
    	<tr>
    	   <td>    	   	
    			<input type="hidden" id="comId" value="${com }">
    			<input type="hidden" id="albaId" value="${alba }">
    			<input type="hidden" id="boardno" value="${boardno }">
     	   </td>
        </tr>
    	<tr>
    	   <td colspan="2">
    		<input type="range" id="score" min="0" max="100" step="1 " value="0" oninput="document.getElementById('value').innerHTML=this.value;" >
    	 	<span id="value"></span><span>점</span>
    	   </td>
    	</tr>
    	<tr>
    		<td colspan="2">
    			<input type="button" onclick="test();" value="점수주기">
    			<input type="button" onclick="self.close();" value="창닫기">

    		</td>
    	</tr>
    	
    </table>
    
    
    
</body>
</html>