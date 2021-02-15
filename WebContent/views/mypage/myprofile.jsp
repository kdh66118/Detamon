<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>    
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>     
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">
<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>

<script type="text/javascript">

window.onload = function(){
	var cnt1 = $("#1").val();
	var cnt2 = $("#2").val();
	var cnt3 = $("#3").val();
	var cnt4 = $("#4").val();
	var cnt5 = $("#5").val();
	var cnt6 = $("#6").val();
	var cnt7 = $("#7").val();

	var data = [
		  ['업체', '횟수' ],
		  ['카페', parseInt(cnt1)],
		  ['편의점', parseInt(cnt2)],
		  ['음식점', parseInt(cnt3)],
		  ['일용직', parseInt(cnt4)],
		  ['배달', parseInt(cnt5)],
		  ['유흥시설', parseInt(cnt6)],
		  ['기타', parseInt(cnt7)]
		];
		var options = {
		  title: '알바 업종별 능숙도',
		  width: 1000, height: 600,
		  series:{
			  0:{color:'#ffbb00'},
			  1:{color:'#ffbb00'},
			  2:{color:'#ffbb00'},
			  3:{color:'#ffbb00'},
			  4:{color:'#ffbb00'},
			  5:{color:'#ffbb00'},
			  6:{color:'#ffbb00'},
			 
		  }
		};
		google.load('visualization', '1.0', {'packages':['corechart']});
		google.setOnLoadCallback(function() {
		  var chart = new google.visualization.ColumnChart(document.querySelector('#chart_div'));
		  chart.draw(google.visualization.arrayToDataTable(data), options);
	});
}


 
</script>
</head>
<body>
	<jsp:include page="../common/header.jsp"></jsp:include>
	

	
	<div id="chart_div" style="margin-left:25%;"></div>
	<c:forEach var="dto" items="${ albaAblitiy}">
		<input type="hidden" id="${dto.c_no }" value="${dto.a_cnt }">
	</c:forEach>
	
	<button class="btn btn-outline-warning btn-sm" style="margin-left:47%; margin-bottom:10px;"onclick="location.href='controller.do?command=mypage'">돌아가기</button>
	
	
	
	
	
	<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>