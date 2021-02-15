<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<%@ page import ="java.util.List" %>    
<%@ page import ="java.util.ArrayList" %>  
<%@ page import ="detamon.dto.JobBoardDto" %>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=484abe6cee8cb658e2dd29b7c144de1d&libraries=services"></script>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Insert title here</title>
<%
	List<JobBoardDto> list = (List<JobBoardDto>)request.getAttribute("list");
%>
<script type="text/javascript">
	/*거리 계산 함수 (직선 거리)*/
	function getmeter(lat1, lon1, lat2, lon2) {
		
		delta_lon = deg2rad(lon2) - deg2rad(lon1);
		
		distance = Math.acos(Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) 
				+ Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) *
				Math.cos(delta_lon)) *  3963.189; //마일
				
				var gap = parseInt(distance * 1609.344); return gap/1000;
	};
	function deg2rad(val) {
		var pi = Math.PI;
		var de_ra = ((eval(val))*(pi/180));
		
		return de_ra; 
	};
</script>
</head>

<body>
<!--  좌표 저장소 -->
<input type="hidden" id="resultx"> <br>
<input type="hidden" id="resulty"> <br>

<!-- 폼 태그 -->
<form action="controller.do" method="post" id="getdistance" name="send">
	<input type="hidden" value="findnearcomres" name="command">
	<input type="hidden" value=${findaddr } name="findaddr">
	<input type="hidden" value=${pagenum } name="pagenum">
	<input type="hidden" value=${findx } name="findx">
	<input type="hidden" value=${findy } name="findy">
	
</form>

<%
	int cnt = 0;
	for(int i = 0 ; i < list.size(); i++){
%>
	<script type="text/javascript">
	
	
	var geocoder = new kakao.maps.services.Geocoder();
	//주소로 좌표를 검색합니다
	
	geocoder.addressSearch("<%=list.get(i).getAddr()%>", function(result, status) {

	    // 정상적으로 검색이 완료됐으면 
	     if (status === kakao.maps.services.Status.OK) {
			
  	       var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
			
  	       		//좌표 뿌림
	     		$("#resultx").val(result[0].y);
	     		$("#resulty").val( result[0].x);
	     		
	     		  
		}
	    
	    //거리 계산
	     var km = getmeter( "${findx}" , "${findy}", $("#resultx").val(), $("#resulty").val() );
		   
		  
		//계산 결과 hidden태그 생성해서 삽입
		   $("#getdistance").append("<input type='hidden' name='distance' value="+km+">");
			$("#getdistance").append("<input type='hidden' name='no' value='<%=i%>'>");
	    	
		 
	    	
	   
	});
	</script>
<%

	}

%>

<script type="text/javascript">
	setTimeout(function(){send.submit();},3000);
	
	
</script>
<p style="margin-left: 35%; font-size: 50px; font-weight: bold; margin-top: 15%;" id="wait_plz">검색중입니다...</p>
<p style="margin-left: 35%; font-size: 50px; font-weight: bold;" id="move_soon">잠시만 기다려주세요!!</p>

<script type="text/javascript">
	setTimeout(function(){$("#wait_plz").text("결과를 가져오는중입니다...");},2000);
	setTimeout(function(){$("#move_soon").text("");},2000);
	setTimeout(function(){$("#wait_plz").text("검색완료!!");},3000);
	setTimeout(function(){$("#move_soon").text("곧 페이지를 이동합니다.");},3000);
	
	
</script>
</body>
</html>