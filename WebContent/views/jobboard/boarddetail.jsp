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
<title>Detamon 구인글</title>
<link rel="stylesheet" type="text/css" href="views/css/boarddetail.css">
<!--  jQuery -->
<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<!--  KAKAO MAP API -->
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=484abe6cee8cb658e2dd29b7c144de1d&libraries=services"></script>
<!-- 구글 차트 API -->
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
	$(function(){
		var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	    mapOption = {
	        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
	        level: 3 // 지도의 확대 레벨
	    };  

		// 지도를 생성합니다    
		var map = new kakao.maps.Map(mapContainer, mapOption); 

		
		// 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
		var mapTypeControl = new kakao.maps.MapTypeControl();

		// 지도에 컨트롤을 추가해야 지도위에 표시됩니다
		// kakao.maps.ControlPosition은 컨트롤이 표시될 위치를 정의하는데 TOPRIGHT는 오른쪽 위를 의미합니다
		map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);

		// 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
		var zoomControl = new kakao.maps.ZoomControl();
		map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);
		
		// 주소-좌표 변환 객체를 생성합니다
		var geocoder = new kakao.maps.services.Geocoder();

		// 주소로 좌표를 검색합니다
		geocoder.addressSearch( $("#addr").text() , function(result, status) {

	    	// 정상적으로 검색이 완료됐으면 
		     if (status === kakao.maps.services.Status.OK) {

	        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

	        // 결과값으로 받은 위치를 마커로 표시합니다
	        var marker = new kakao.maps.Marker({
	            map: map,
	            position: coords
	        });

	        // 인포윈도우로 장소에 대한 설명을 표시합니다
	        var infowindow = new kakao.maps.InfoWindow({
	            content: '<div style="width:150px;text-align:center;padding:6px 0;">'+$("#com_name").text()+'</div>'
	        });
	        infowindow.open(map, marker);

	        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
	        map.setCenter(coords);
	    } 
	});    
	});
	
	//구글차트
	// Load the Visualization API and the corechart package.
    google.charts.load('current', {'packages':['corechart']});

    // Set a callback to run when the Google Visualization API is loaded.
    google.charts.setOnLoadCallback(drawChart);

    // Callback that creates and populates a data table,
    // instantiates the pie chart, passes in the data and
    // draws it.
    function drawChart() {
		var cnt = $("#applycnt").val();
		
    	var data = google.visualization.arrayToDataTable([
            ["Element", "명", { role: "style" } ],
            ["지원자 수", parseInt(cnt), "#ffd203"],
            
          ]);

          var view = new google.visualization.DataView(data);
          view.setColumns([0, 1,
                           { calc: "stringify",
                             sourceColumn: 1,
                             type: "string",
                             role: "annotation" },
                           2]);

          var options = {
            title: "현재 지원자 수(명)",
            width: 550,
            height: 400,
            bar: {groupWidth: "5%"},
            legend: { position: "none" },
          };

      // Instantiate and draw our chart, passing in some options.
      var chart = new google.visualization.ColumnChart(document.getElementById('chart_div'));
      chart.draw(data, options);
      
      
    }
   
    google.charts.setOnLoadCallback(drawChartGender);
    function drawChartGender() {
    	var maleCnt = $("#malecnt").val();
    	var femaleCnt = $("#femalecnt").val();
    	
    	
      var data = google.visualization.arrayToDataTable([
        ['Task', 'Hours per Day'],
        ['남자',     parseInt(maleCnt) ],
        ['여자',      parseInt(femaleCnt)],
       
      ]);

      var options = {
        title: '지원자 남녀 비율',
         colors:['rgb(90, 175, 231)','rgb(248, 175, 187)']
      };

      var chart = new google.visualization.PieChart(document.getElementById('piechart'));

      chart.draw(data, options);
    }
    
    google.charts.setOnLoadCallback(drawChart2);
    function drawChart2() {
		var cnt = $("#applycnt").val();
		
    	var data = google.visualization.arrayToDataTable([
            ["Element", "개", { role: "style" } ],
            ["지원업체 수", parseInt(cnt), "#ffd203"],
            
          ]);

          var view = new google.visualization.DataView(data);
          view.setColumns([0, 1,
                           { calc: "stringify",
                             sourceColumn: 1,
                             type: "string",
                             role: "annotation" },
                           2]);

          var options = {
            title: "현재 업체 수(개)",
            width: 550,
            height: 400,
            bar: {groupWidth: "5%"},
            legend: { position: "none" },
          };

      // Instantiate and draw our chart, passing in some options.
      var chart = new google.visualization.ColumnChart(document.getElementById('chart_div2'));
      chart.draw(data, options);
      
      
    }
    
    //알바생 업무능숙도
    google.charts.setOnLoadCallback(drawChartAbility);
    function drawChartAbility() {
		var cnt1 = $("#1").val();
		var cnt2 = $("#2").val();
		var cnt3 = $("#3").val();
		var cnt4 = $("#4").val();
		var cnt5 = $("#5").val();
		var cnt6 = $("#6").val();
		var cnt7 = $("#7").val();
		
    	var data = google.visualization.arrayToDataTable([
            ["Element", "개", { role: "style" } ],
            ["카페", parseInt(cnt1), "#ffd203"],
            ["편의점", parseInt(cnt2), "#ffd203"],
            ["음식점", parseInt(cnt3), "#ffd203"],
            ["일용직", parseInt(cnt4), "#ffd203"],
            ["배달", parseInt(cnt5), "#ffd203"],
            ["유흥시설", parseInt(cnt6), "#ffd203"],
            ["기타", parseInt(cnt7), "#ffd203"]
            
          ]);

          var view = new google.visualization.DataView(data);
          view.setColumns([0, 1,
                           { calc: "stringify",
                             sourceColumn: 1,
                             type: "string",
                             role: "annotation" },
                           2]);

          var options = {
            title: "알바생 업종별 체결 계약 횟수(개)",
            width: 1150,
            height: 400,
            bar: {groupWidth: "5%"},
            legend: { position: "none" },
          };

      // Instantiate and draw our chart, passing in some options.
      var chart = new google.visualization.ColumnChart(document.getElementById('chart_div3'));
      chart.draw(data, options);
      
      
    }
    function chkAlbaLogin(frm){
    	
    	if( $("#login_id").val().trim()=="" || $("#login_id").val()==null){
    		alert("로그인을 해주세요");
			    		
    	}else if(${login.m_role != 1}){
    		
    		alert("알바생 계정만 지원 할 수 있습니다.");
    	}else{
    		
    		frm.submit();
    	}
    	
    }
    function chkComLogin(frm){
    	
    	if( $("#login_id").val().trim()=="" || $("#login_id").val()==null){
    		alert("로그인을 해주세요");
			    		
    	}else if(${login.m_role != 2}){
    		
    		alert("업체 계정만 지원 할 수 있습니다.");
    	}else{
    		
    		frm.submit();
    	}
    	
    }
    
   
    
    
</script>
</head>
<body>
	<!-- 헤더 -->
	<%@ include file="../common/header.jsp" %>
	
		<!-- -------------------------------업체찾기 ----------------------------------------------- -->
	<c:if test="${board.type_no==2 }">
	<div id="container">
		<div class="title_div">
			<div class="com_name">${company.com_name }</div>
			<div class="board_title">${board.title }
				<span class="location">지역:
					<c:choose>
						<c:when test="${board.loc == 1 }">강남구</c:when>
						<c:when test="${board.loc == 2 }">송파구</c:when>
						<c:when test="${board.loc == 3 }">서초구</c:when>
						<c:when test="${board.loc == 4 }">강동구</c:when>
						<c:when test="${board.loc == 5 }">동작구</c:when>
						<c:when test="${board.loc == 6 }">관악구</c:when>
						<c:when test="${board.loc == 7 }">영등포구</c:when>
						<c:when test="${board.loc == 8 }">강서구</c:when>
						<c:when test="${board.loc == 9 }">양천구</c:when>
						<c:when test="${board.loc == 10 }">구로구</c:when>
						<c:when test="${board.loc == 11 }">금천구</c:when>
						<c:when test="${board.loc == 12 }">종로구</c:when>
						<c:when test="${board.loc == 13 }">중구</c:when>
						<c:when test="${board.loc == 14 }">동대문구</c:when>
						<c:when test="${board.loc == 15 }">중랑구</c:when>
						<c:when test="${board.loc == 16 }">마포구</c:when>
						<c:when test="${board.loc == 17 }">용산구</c:when>
						<c:when test="${board.loc == 18 }">성동구</c:when>
						<c:when test="${board.loc == 19 }">광진구</c:when>
						<c:when test="${board.loc == 20 }">은평구</c:when>
						<c:when test="${board.loc == 21 }">서대문구</c:when>
						<c:when test="${board.loc == 22 }">성북구</c:when>
						<c:when test="${board.loc == 23 }">강북구</c:when>
						<c:when test="${board.loc == 24 }">도봉구</c:when>
						<c:when test="${board.loc == 25 }">노원구</c:when>
						
					</c:choose>
					&nbsp&nbsp업종:
					<c:choose>
						<c:when test="${board.category == 1 }">
							카페
						</c:when>
						<c:when test="${board.category == 2 }">
							편의점
						</c:when>
						<c:when test="${board.category == 3 }">
							음식점
						</c:when>
						<c:when test="${board.category == 4 }">
							일용직
						</c:when>
						<c:when test="${board.category == 5 }">
							배달
						</c:when>
						<c:when test="${board.category == 6 }">
							유흥시설
						</c:when>
						<c:when test="${board.category == 7 }">
							기타
						</c:when>
					</c:choose>
					
				</span>
			</div>
			
		</div>
				
		<div class="title_div">
		<div class="title_div2">
			<div class="title_text">
				글 내용
			</div>
			<textarea rows="10" cols="35" readonly="readonly" style="font-size:20px">${board.content }</textarea>
		</div>
		
		<div class="title_div3">
			<div class="title_text">
				모집조건
			</div>
			
			<table>
				<col width="80px"><col width="200px">
				<tr>
					<td class="first_text">근무 기간</td>
					<td class="second_text">
						<fmt:formatDate value="${board.start_date}" pattern="yy/MM/dd"/>
						&nbsp~&nbsp
						<fmt:formatDate value="${board.end_date}" pattern="yy/MM/dd"/>
					</td>
				</tr>
				<tr>
					<td class="first_text">근무 시간</td>
					<td class="second_text">
						<fmt:formatDate value="${board.start_time}" pattern="HH:mm"/>
						&nbsp~&nbsp
						<fmt:formatDate value="${board.end_time}" pattern="HH:mm"/>
					</td>
				</tr>
				<tr>
					<td class="first_text">시급</td>
					<td class="second_text">
						${board.money }원
					</td>
				</tr>
				<tr>
					<td class="first_text">지역</td>
					<td class="second_text">
						<c:choose>
						<c:when test="${board.loc == 1 }">강남구</c:when>
						<c:when test="${board.loc == 2 }">송파구</c:when>
						<c:when test="${board.loc == 3 }">서초구</c:when>
						<c:when test="${board.loc == 4 }">강동구</c:when>
						<c:when test="${board.loc == 5 }">동작구</c:when>
						<c:when test="${board.loc == 6 }">관악구</c:when>
						<c:when test="${board.loc == 7 }">영등포구</c:when>
						<c:when test="${board.loc == 8 }">강서구</c:when>
						<c:when test="${board.loc == 9 }">양천구</c:when>
						<c:when test="${board.loc == 10 }">구로구</c:when>
						<c:when test="${board.loc == 11 }">금천구</c:when>
						<c:when test="${board.loc == 12 }">종로구</c:when>
						<c:when test="${board.loc == 13 }">중구</c:when>
						<c:when test="${board.loc == 14 }">동대문구</c:when>
						<c:when test="${board.loc == 15 }">중랑구</c:when>
						<c:when test="${board.loc == 16 }">마포구</c:when>
						<c:when test="${board.loc == 17 }">용산구</c:when>
						<c:when test="${board.loc == 18 }">성동구</c:when>
						<c:when test="${board.loc == 19 }">광진구</c:when>
						<c:when test="${board.loc == 20 }">은평구</c:when>
						<c:when test="${board.loc == 21 }">서대문구</c:when>
						<c:when test="${board.loc == 22 }">성북구</c:when>
						<c:when test="${board.loc == 23 }">강북구</c:when>
						<c:when test="${board.loc == 24 }">도봉구</c:when>
						<c:when test="${board.loc == 25 }">노원구</c:when>
						
					</c:choose>
					</td>
				</tr>
				<tr>
					<td class="first_text">업종</td>
					<td class="second_text">
						<c:choose>
						<c:when test="${board.category == 1 }">
							카페
						</c:when>
						<c:when test="${board.category == 2 }">
							편의점
						</c:when>
						<c:when test="${board.category == 3 }">
							음식점
						</c:when>
						<c:when test="${board.category == 4 }">
							일용직
						</c:when>
						<c:when test="${board.category == 5 }">
							배달
						</c:when>
						<c:when test="${board.category == 6 }">
							유흥시설
						</c:when>
						<c:when test="${board.category == 7 }">
							기타
						</c:when>
					</c:choose>
					</td>
				</tr>
			</table>
				
			
		</div>
		
		</div>
		<div class="title_div">
			<div class="title_text">
				업체 정보
			</div>
			
			<div class="company_info">
			<table >
				<col width="120px"><col width="350px">
				<tr >
					<td class="first_text">상호명</td>
					<td class="second_text" id="com_name">${company.com_name }</td>
				</tr>
				<tr >
					<td class="first_text">책임자</td>
					<td class="second_text">${member.m_name }</td>
				</tr>
				<tr >
					<td class="first_text">책임자 번호</td>
					<td class="second_text">${member.m_phone }</td>
				</tr>
				<tr >
					<td class="first_text">이메일</td>
					<td class="second_text" id="email" >${member.m_email }</td>
				</tr>
				<tr >
					<td class="first_text">주소</td>
					<td class="second_text" id="addr" >${company.com_addr }</td>
				</tr>
				<tr >
					<td class="first_text">업체 번호</td>
					<td class="second_text">${company.com_phone }</td>
				</tr>
				<tr >
					<td class="first_text">평점</td>
					<td class="second_text">${member.m_score }</td>
				</tr>
				<tr >
					<td rowspan="2" colspan="2" class="warring">※구인/구직이 아닌 광고 등의 목적으로 연락처를 이용할 경우 법적 처벌을 받을 수 있습니다.</td>
				</tr>
			</table>
			</div>
			<div id="map" >
				
			</div>
			
			
			
		</div>
			
		<div class="title_div">
			<div class="title_text">
				지원 현황
			</div>
		<input type="hidden" id="applycnt" value="${applycnt }">
		<input type="hidden" id="malecnt" value="${malecnt }">
		<input type="hidden" id="femalecnt" value="${femalecnt }">
		
		<c:choose>
			<c:when test="${malecnt == 0 && femalecnt ==0 }">
				<div class ="no_apply">지원자가 없습니다.</div>
			</c:when>
			<c:otherwise>
				<div id="chart_div"></div>
				<div id="piechart" style="width: 550px; height: 400px;"></div>
			</c:otherwise>				 
			
		</c:choose>
		</div>
		
		<div class="btn_div">
			<form action="controller.do?" method="post" id="applyform">
			<input type="hidden" id="login_id" value="${login.m_id }">
			<input type="hidden" name="command" value="applyalba">
			<input type="hidden" name="no" value="${board.no }">
			<input type="hidden" name="writer" value="${board.writer }">
			<c:choose>
				<c:when test="${issigncontract == null }">
					<c:choose>
						<c:when test="${login.m_id.equals(board.writer) }"> 
							<input type="button" id = "submit_btn" onclick="chkAlbaLogin(this.form);" value="지원하기" style="margin-left: 18%;">
							</form>
							<input type="button" id = "home_btn" onclick="location.href='controller.do?command=list'" value="업체찾기 목록">
							<input type="button" id = "home_btn" onclick="location.href='controller.do?command=updateboardform&no=${board.no}'" value="수정하기">
							<input type="button" id = "home_btn" onclick="location.href='controller.do?command=deleteboard&no=${board.no}&afterpage=list'" value="삭제하기">
						</c:when>
						<c:otherwise>
							<input type="button" id = "submit_btn" onclick="chkAlbaLogin(this.form);" value="지원하기" style="margin-left: 35%;">
							</form>
							<input type="button" id = "home_btn" onclick="location.href='controller.do?command=list'" value="업체찾기 목록">
						</c:otherwise>
					</c:choose>
				</c:when>
				<c:otherwise>
					<c:choose>
						<c:when test="${login.m_id.equals(board.writer) }">
							<input type="button" id = "submit_btn2" onclick="" value="진행중인계약" style="margin-left: 18%;">
							</form>
							<input type="button" id = "home_btn" onclick="location.href='controller.do?command=list'" value="업체찾기 목록">
							<input type="button" id = "home_btn" onclick="location.href='controller.do?command=updateboardform&no=${board.no}'" value="수정하기">
							<input type="button" id = "home_btn" onclick="location.href='controller.do?command=deleteboard&no=${board.no}&afterpage=list'" value="삭제하기">
						</c:when>
						<c:otherwise>
							<input type="button" id = "submit_btn2" onclick="" value="진행중인계약" style="margin-left: 35%;">
							</form>
							<input type="button" id = "home_btn" onclick="location.href='controller.do?command=list'" value="업체찾기 목록">
						</c:otherwise>
					</c:choose>
				</c:otherwise>
			</c:choose>
		</div>
		</div>
	</c:if>
	
	
	<!-- -------------------------------알바찾기 ----------------------------------------------- -->
	<c:if test="${board.type_no==1 }">
	<div id="container">
		<div class="title_div">
			<div class="com_name">${member.m_name }</div>
			<div class="board_title">${board.title }
				<span class="location">지역:
					<c:choose>
						<c:when test="${board.loc == 1 }">강남구</c:when>
						<c:when test="${board.loc == 2 }">송파구</c:when>
						<c:when test="${board.loc == 3 }">서초구</c:when>
						<c:when test="${board.loc == 4 }">강동구</c:when>
						<c:when test="${board.loc == 5 }">동작구</c:when>
						<c:when test="${board.loc == 6 }">관악구</c:when>
						<c:when test="${board.loc == 7 }">영등포구</c:when>
						<c:when test="${board.loc == 8 }">강서구</c:when>
						<c:when test="${board.loc == 9 }">양천구</c:when>
						<c:when test="${board.loc == 10 }">구로구</c:when>
						<c:when test="${board.loc == 11 }">금천구</c:when>
						<c:when test="${board.loc == 12 }">종로구</c:when>
						<c:when test="${board.loc == 13 }">중구</c:when>
						<c:when test="${board.loc == 14 }">동대문구</c:when>
						<c:when test="${board.loc == 15 }">중랑구</c:when>
						<c:when test="${board.loc == 16 }">마포구</c:when>
						<c:when test="${board.loc == 17 }">용산구</c:when>
						<c:when test="${board.loc == 18 }">성동구</c:when>
						<c:when test="${board.loc == 19 }">광진구</c:when>
						<c:when test="${board.loc == 20 }">은평구</c:when>
						<c:when test="${board.loc == 21 }">서대문구</c:when>
						<c:when test="${board.loc == 22 }">성북구</c:when>
						<c:when test="${board.loc == 23 }">강북구</c:when>
						<c:when test="${board.loc == 24 }">도봉구</c:when>
						<c:when test="${board.loc == 25 }">노원구</c:when>
						
					</c:choose>
					&nbsp&nbsp업종:
					<c:choose>
						<c:when test="${board.category == 1 }">
							카페
						</c:when>
						<c:when test="${board.category == 2 }">
							편의점
						</c:when>
						<c:when test="${board.category == 3 }">
							음식점
						</c:when>
						<c:when test="${board.category == 4 }">
							일용직
						</c:when>
						<c:when test="${board.category == 5 }">
							배달
						</c:when>
						<c:when test="${board.category == 6 }">
							유흥시설
						</c:when>
						<c:when test="${board.category == 7 }">
							기타
						</c:when>
					</c:choose>
					
				</span>
			</div>
			
		</div>
				
		<div class="title_div">
		<div class="title_div2">
			<div class="title_text">
				글 내용
			</div>
			<textarea rows="10" cols="35" readonly="readonly" style="font-size:20px">${board.content }</textarea>
		</div>
		
		<div class="title_div3">
			<div class="title_text">
				희망근무조건
			</div>
			
			<table>
				<col width="80px"><col width="200px">
				<tr>
					<td class="first_text">근무 기간</td>
					<td class="second_text">
						<fmt:formatDate value="${board.start_date}" pattern="yy/MM/dd"/>
						&nbsp~&nbsp
						<fmt:formatDate value="${board.end_date}" pattern="yy/MM/dd"/>
					</td>
				</tr>
				<tr>
					<td class="first_text">근무 시간</td>
					<td class="second_text">
						<fmt:formatDate value="${board.start_time}" pattern="HH:mm"/>
						&nbsp~&nbsp
						<fmt:formatDate value="${board.end_time}" pattern="HH:mm"/>
					</td>
				</tr>
				<tr>
					<td class="first_text">시급</td>
					<td class="second_text">
						${board.money }원
					</td>
				</tr>
				<tr>
					<td class="first_text">지역</td>
					<td class="second_text">
						<c:choose>
						<c:when test="${board.loc == 1 }">강남구</c:when>
						<c:when test="${board.loc == 2 }">송파구</c:when>
						<c:when test="${board.loc == 3 }">서초구</c:when>
						<c:when test="${board.loc == 4 }">강동구</c:when>
						<c:when test="${board.loc == 5 }">동작구</c:when>
						<c:when test="${board.loc == 6 }">관악구</c:when>
						<c:when test="${board.loc == 7 }">영등포구</c:when>
						<c:when test="${board.loc == 8 }">강서구</c:when>
						<c:when test="${board.loc == 9 }">양천구</c:when>
						<c:when test="${board.loc == 10 }">구로구</c:when>
						<c:when test="${board.loc == 11 }">금천구</c:when>
						<c:when test="${board.loc == 12 }">종로구</c:when>
						<c:when test="${board.loc == 13 }">중구</c:when>
						<c:when test="${board.loc == 14 }">동대문구</c:when>
						<c:when test="${board.loc == 15 }">중랑구</c:when>
						<c:when test="${board.loc == 16 }">마포구</c:when>
						<c:when test="${board.loc == 17 }">용산구</c:when>
						<c:when test="${board.loc == 18 }">성동구</c:when>
						<c:when test="${board.loc == 19 }">광진구</c:when>
						<c:when test="${board.loc == 20 }">은평구</c:when>
						<c:when test="${board.loc == 21 }">서대문구</c:when>
						<c:when test="${board.loc == 22 }">성북구</c:when>
						<c:when test="${board.loc == 23 }">강북구</c:when>
						<c:when test="${board.loc == 24 }">도봉구</c:when>
						<c:when test="${board.loc == 25 }">노원구</c:when>
						
					</c:choose>
					</td>
				</tr>
				<tr>
					<td class="first_text">업종</td>
					<td class="second_text">
						<c:choose>
						<c:when test="${board.category == 1 }">
							카페
						</c:when>
						<c:when test="${board.category == 2 }">
							편의점
						</c:when>
						<c:when test="${board.category == 3 }">
							음식점
						</c:when>
						<c:when test="${board.category == 4 }">
							일용직
						</c:when>
						<c:when test="${board.category == 5 }">
							배달
						</c:when>
						<c:when test="${board.category == 6 }">
							유흥시설
						</c:when>
						<c:when test="${board.category == 7 }">
							기타
						</c:when>
					</c:choose>
					</td>
				</tr>
			</table>
				
			
		</div>
		
		</div>
		<div class="title_div">
		<div class="title_div2">
			<div class="title_text">
				알바생 정보
			</div>
			
			<div class="company_info">
			<table >
				<col width="120px"><col width="350px">
				<tr >
					<td class="first_text">이름</td>
					<td class="second_text" id="com_name">${member.m_name }</td>
				</tr>
				<tr >
					<td class="first_text">연락처</td>
					<td class="second_text">${member.m_phone }</td>
				</tr>
				<tr >
					<td class="first_text">이메일</td>
					<td class="second_text">${member.m_email }</td>
				</tr>
				<tr >
					<td class="first_text">성별</td>
					<c:if test="${member.m_gender.equals('M') }">
						<td class="second_text" id="addr" >남성</td>
					</c:if>
					<c:if test="${member.m_gender.equals('F') }">
						<td class="second_text" id="addr" >여성</td>
					</c:if>
				</tr>
				<tr >
					<td class="first_text">평점</td>
					<td class="second_text">${member.m_score }</td>
				</tr>
				<tr >
					<td rowspan="2" colspan="2" class="warring">※구인/구직이 아닌 광고 등의 목적으로 연락처를 이용할 경우 법적 처벌을 받을 수 있습니다.</td>
				</tr>
			</table>
			</div>
			
			
			
			
		</div>
			
		<div class="title_div3">
			<div class="title_text">
				지원 현황
			</div>
		<input type="hidden" id="applycnt" value="${applycnt }">
		
		
		<c:choose>
			<c:when test="${applycnt == 0 }"><br><br><br><br><br><br><br>
				<div class ="no_apply">지원업체가 없습니다.</div>
			</c:when>
			<c:otherwise>
				<div id="chart_div2"></div>				
			</c:otherwise>				 
			
		</c:choose>
		</div>
		</div>
		
		<div class="title_div">
			<div class="title_text">
				알바생 업종별 업무 능숙도
			</div>
			<div id="chart_div3"></div>	
			<c:forEach var="ability" items="${albaabilitiy }">
				
				<input type="hidden" id="${ability.c_no }" value="${ability.a_cnt }">
				
			</c:forEach>
			
		</div>
		
		
		<div class="btn_div">
			<form action="controller.do?" method="post" id="applyform">
			<input type="hidden" id="login_id" value="${login.m_id }">
			<input type="hidden" name="command" value="applycompany">
			<input type="hidden" name="no" value="${board.no }">
			<input type="hidden" name="writer" value="${board.writer }">
			
			<c:choose>
				<c:when test="${issigncontract == null }">
					<c:choose>
						<c:when test="${login.m_id.equals(board.writer) }"> 
							<input type="button" id = "submit_btn" onclick="chkComLogin(this.form);" value="지원하기" style="margin-left: 18%;">
							</form>
							<input type="button" id = "home_btn" onclick="location.href='controller.do?command=alba'" value="대타찾기 목록">
							<input type="button" id = "home_btn" onclick="location.href='controller.do?command=updateboardform&no=${board.no}'" value="수정하기">
							<input type="button" id = "home_btn" onclick="location.href='controller.do?command=deleteboard&no=${board.no}&afterpage=alba'" value="삭제하기">
						</c:when>
						<c:otherwise>
							<input type="button" id = "submit_btn" onclick="chkComLogin(this.form);" value="지원하기" style="margin-left: 35%;">
							</form>
							<input type="button" id = "home_btn" onclick="location.href='controller.do?command=alba'" value="대타찾기 목록">
						</c:otherwise>
					</c:choose>
				</c:when>
				<c:otherwise>
					<c:choose>
						<c:when test="${login.m_id.equals(board.writer) }">
							<input type="button" id = "submit_btn2" onclick="" value="진행중인계약" style="margin-left: 18%;">
							</form>
							<input type="button" id = "home_btn" onclick="location.href='controller.do?command=alba'" value="대타찾기 목록">
							<input type="button" id = "home_btn" onclick="location.href='controller.do?command=updateboardform&no=${board.no}'" value="수정하기">
							<input type="button" id = "home_btn" onclick="location.href='controller.do?command=deleteboard&no=${board.no}&afterpage=alba'" value="삭제하기">
						</c:when>
						<c:otherwise>
							<input type="button" id = "submit_btn2" onclick="" value="진행중인계약" style="margin-left: 35%;">
							</form>
							<input type="button" id = "home_btn" onclick="location.href='controller.do?command=alba'" value="대타찾기 목록">
						</c:otherwise>
					</c:choose>
				</c:otherwise>
			</c:choose>
			
		</div>
		</div>	
	
	</c:if>
	
	
	<!-- 퓨터 -->
	<%@ include file="../common/footer.jsp" %>
</body>
</html>