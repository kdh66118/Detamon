<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>    
<% response.setContentType("text/html; charset=UTF-8"); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>    
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>    
<%@ page import="detamon.dto.MemberDto" %>
<%@ page import="detamon.dto.JobBoardDto" %>
<%@ page import="detamon.dto.CompanyDto" %>
<%@ page import="detamon.dto.ContractDto" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>        
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>대타몬:계약확인-정보확인</title>
<link rel="stylesheet" type="text/css" href="views/css/contractinfo.css">
</head>
<% 
	//게시글정보 받아오기
	JobBoardDto jdto = (JobBoardDto)session.getAttribute("jdto");
	//알바생정보
	MemberDto adto = (MemberDto)session.getAttribute("adto");
	//회사정보 받아오기
	CompanyDto pdto = (CompanyDto)session.getAttribute("pdto");
	//사업자정보 받아오기
	MemberDto comdto = (MemberDto)session.getAttribute("comdto"); 
	//회원의 id값으로 현재 로그인 회원정보 
	MemberDto udto = (MemberDto)session.getAttribute("udto");
	//계약정보 list로 받아오기
	List<ContractDto> conDto = (List<ContractDto>)session.getAttribute("contractedDto");
	
%>
<!-- 메일 api -->
<script type= "text/javascript" src="views/js/mail.js"></script>
<!-- 구글차트 -->
<!-- 구글 차트 API -->
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript" src=" https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
	$(function(){
		var alba = "${cdto.albaestimate}";
		var com = "${cdto.comestimate}";
		var is_contract = "${cdto.is_contract}";
		var type_no = "${jdto.type_no}";
		var login = "${udto.m_role}";
	if(is_contract == "N"){
		//alert("알바평가"+alba+"업체평가"+com+"계약여부"+is_contract+"type_no"+type_no+"login등급"+login);
		//게시판 종류 ? 1번 = 대타찾기글(알바가 올리는글)  2번 = 업체찾기글(업체가 올리는글)
		//로그인 정보가 업체이고 게시글이 업체찾기 글이라면 알바가  > 업체로 신청 업체가 수락이나 거절  해야함 		
		if(login == 2 && type_no == 2){
			$('#canBtn').prop('disabled', false);
			$('#acBtn').prop('disabled', false);
			$('#progress').text("수락대기중");
			//alert("로그인 정보가 업체이고 게시글이 업체찾기 글이라면 알바가  > 업체로 신청 업체가 수락이나 거절  해야함 ");
		}
		//로그인이 업체이고 게시글이 알바찾기글이면 업체가 > 알바신청 업체는 수락 버튼 비활성롸
		else if(login == 2 && type_no == 1){
			$('#canBtn').prop('disabled', false);
			$('#acBtn').prop('disabled', true).val("신청완료");
			$('#progress').text("수락대기중");
			//alert("로그인이 업체이고 게시글이 알바찾기글이면 업체가 > 알바신청 업체는 수락 버튼 비활성화");
		}
		//로그인이 알바이고 게시글이 알바찾기이면 알바가 > 업체 신청 알바는 수락 활성화
		else if(login == 1 && type_no ==1){
			$('#canBtn').prop('disabled', false);
			$('#acBtn').prop('disabled', false);
			$('#progress').text("수락대기중");
			//alert("로그인이 알바이고 게시글이 알바찾기이면 업체가 > 알바에게 신청 알바가 수락이나 거절");

		}else{
			$('#canBtn').prop('disabled', false);
			$('#acBtn').prop('disabled', true).val("신청완료");
			$('#progress').text("수락대기중");
			//alert("로그인이 알바이고 게시글이 업체찾기이면 알바가 > 업체 신청 알바는 수락 비활성화");

		}
	}else if(is_contract == "Y"){
		
		if(login == 2){
			//로그인 정보가 업체일때
			//알바평가를 완료했고 업체평가를 했을때
			if(alba=="Y" && com=="Y"){
				console.log(alba);
				$('#canBtn').prop('disabled', true).val("거절불가");
				$('#acBtn').prop('disabled', true).val("수락완료");
				$('#progress').text("평가종료");
				console.log("로그인정보가 알바생이고 업체,알바생 평가 완료했을경우 ");
			}
			//알바평가를 했고 업체평가를 하지 않았을때		
			else if(alba=="Y" && com=="N"){
				$('#canBtn').prop('disabled', true).val("거절불가");
				$('#acBtn').prop('disabled', true).val("수락완료");
				$('#progress').text("평가중");
				console.log("로그인정보가 업체이고 알바생 평가 완료했고 업체평가를 하지않은경우 ");
			}
			//알바평가 미완성 업체평가 완성
			else if(alba=="N" && com=="Y"){
				$('#canBtn').prop('disabled', true).val("거절불가");
				$('#acBtn').prop('disabled', true).val("수락완료");
				$('#progress').text("평가중");
				console.log("로그인정보가 업체이고 알바생 평가 미완료 업체평가를 완료한경우 "); 				
			}

			else if(alba=="N" && com == "N"){
			//로그인정보가 업체이고 알바생 평가 완료 하지 않은 경우 	
				console.log("로그인정보가 업체이고 알바생,업체 평가 완료 하지 않은 경우 ");
					$('#canBtn').prop('disabled', false);
					$('#acBtn').prop('disabled', true).val("수락완료");
					$('#progress').text("진행중");
			}
			
		}else{
			
		//알바평가를 완료했고 업체평가를 했을때
		if(alba=="Y" && com=="Y"){
			console.log(alba);
			$('#canBtn').prop('disabled', true).val("거절불가");
			$('#acBtn').prop('disabled', true).val("수락완료");
			$('#progress').text("평가종료");
			console.log("로그인정보가 알바생이고 업체,알바생 평가 완료했을경우 ");
		}
		//알바평가를 했고 업체평가를 하지 않았을때		
		else if(alba=="Y" && com=="N"){
			$('#canBtn').prop('disabled', true).val("거절불가");
			$('#acBtn').prop('disabled', true).val("수락완료");
			$('#progress').text("평가중");
			console.log("로그인정보가 알바생이고 알바생 평가 완료했고 업체평가를 하지않은경우 ");
		}
		//알바평가 미완성 업체평가 완성
		else if(alba=="N" && com=="Y"){
			$('#canBtn').prop('disabled', true).val("거절불가");
			$('#acBtn').prop('disabled', true).val("수락완료");
			$('#progress').text("평가중");
			console.log("로그인정보가 알바생이고 알바생 평가 미완료 업체평가를 완료한경우 "); 				
		}

		else if(alba=="N" && com == "N"){
		//로그인정보가 업체이고 알바생 평가 완료 하지 않은 경우 	
			console.log("로그인정보가 알바생이고 알바생,업체 평가 완료 하지 않은 경우 ");

			$('#canBtn').prop('disabled', false);
			$('#acBtn').prop('disabled', true).val("수락완료");
			$('#progress').text("진행중");
		}
		}
	}else{
		$('#canBtn').prop('disabled', true).val("거절");
		$('#acBtn').prop('disabled', true).val("수락완료");
		$('#progress').text("계약종료");
		console.log("모든 계약 종료"); 	
	}

	});	
	
	function nocancle(){
		if(confirm("수락하시겠습니까?")){//yes 게시글 번호, 계약여부 Y, 알바생id, 업체id 넘겨주기	
	 
		//메일 알림 
		var com_email = "${com_email}"; 	
		var alba_email = "${alba_email}";
		
		//메일 내용 
		//업체 주소
		var companyAddr = "<%=pdto.getCom_addr()%>";
		//업체전화번호
		var comPhone = "<%=pdto.getCom_phone()%>";
		//업종 
		var comCate = "${comCategory }";
		//시작 날짜 
		var startdate = "${jdto.start_date}";
		//끝나는 날짜 
		var enddate = "${jdto.end_date}";
		//시작시간
		var startTime = "${jdto.start_time}";
		//끝나는시간
		var endTime = "${jdto.end_time}";
		//알바생 이름
		var albaName = "<%=adto.getM_name()%>";
		//알바생 전화번호
		var albaPhone = "<%=adto.getM_phone()%>";

		
		//알바한테 메일전송
		var url = "views/mail/sendAlbaMail.jsp?&alba_email="+alba_email+"&companyAddr="+companyAddr+"&comPhone="+comPhone+"&comCate="+comCate+
					"&startdate="+startdate+"&enddate="+enddate+"&startTime="+startTime+"&endTime="+endTime;
		window.open(url,"이메일 전송", "width=100,height=100 location=no,status=no,menubar=no,scrollbars=no,resizable=no");
	
		alert("인증메일을 "+alba_email+"로 전송했습니다.");
		
		//업체한테 메일 전송
		
		var url = "views/mail/sendComMail.jsp?&com_email="+com_email+"&albaName="+albaName+"&albaPhone="+albaPhone+"&startdate="+startdate+"&enddate="+enddate+"&startTime="+startTime+"&endTime="+endTime;
		window.open(url,"이메일 전송", "width=100,height=100 location=no,status=no,menubar=no,scrollbars=no,resizable=no");
	
		alert("인증메일을 "+com_email+"로 전송했습니다.");
		
		//계약완료 
		location.href="controller.do?command=contracting&is_contract=${cdto.is_contract}&board_no=${jdto.no}&parttimerId=${adto.m_id}&companyId=${pdto.m_id}";
		}
	}
	
	function cancle(){
		if(confirm("거절하시겠습니까?")){//게시글 번호, 알바생 ID, 업체 ID 주고 contract테이블에서 delete하기
			location.href="controller.do?command=cancleContract&board_no=${jdto.no}&parttimerId=${adto.m_id}&companyId=${pdto.m_id}";
		}
	}
	
	//구글차트
	// Load the Visualization API and the corechart package.
    google.charts.load('current', {'packages':['corechart']});
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
            width: 800,
            height: 200,
            bar: {groupWidth: "5%"},
            legend: { position: "none" },
          };

      // Instantiate and draw our chart, passing in some options.
      var chart = new google.visualization.ColumnChart(document.getElementById('chart_div3'));
      chart.draw(data, options);
      
      
    }
	
</script>
<body>
<header>
	<!-- 헤더 -->
	<%@ include file="../common/header.jsp" %></header>
<section>
<ul id="left">
	<li><a href="controller.do?command=contract">계약정보</a></li>
	<li><a href="controller.do?command=contracted&is_contract=Y&userId=<%=udto.getM_id()%>&role=<%=udto.getM_role()%>">체결된계약</a></li>
</ul>
<div id="right">
	<h1>정보확인</h1>
	<div id="div1">
		<div class="rdiv" id="r2">
			<div class="titleinfo">
				<h1>${jdto.title }</h1>	
			</div>	
			<div class="rdiv">
				<table class="infotable">
					<col width="200px"><col width="300px">
					<tr>
						<td colspan="2">
							<textarea readonly="readonly">${jdto.content }</textarea>
						</td>
					</tr>
				<tr>
					<th>급여</th>
					<td><span>시급 : ${jdto.money }</span></td>
				</tr>
				<tr>
					<th>기간</th>
					<td><span><fmt:formatDate value='${jdto.start_date}' pattern='yyyy년 MM월 dd일 부터'/><br>~<p><fmt:formatDate value='${jdto.end_date}' pattern='yyyy년 MM월 dd일 까지'/></p></span></td>
				</tr>
				<tr>
					<th>시간</th>
					<td><span><fmt:formatDate value='${jdto.start_time}' pattern='HH시mm분 부터'/><br>~<p><fmt:formatDate value='${jdto.end_time}' pattern='HH시mm분 까지'/></p></span></td>
				</tr>
				</table>
			</div>
		</div>
		<div class="rdiv" id="r1">
			<div class="titleinfo">
				<h1>업체정보</h1>	
			</div>	
			<div class="rdiv">
			<table class="infotable">
				<col width="200px"><col width="300px">
				<tr>
					<th>상호명</th>
					<td><span><%=pdto.getCom_name() %></span></td>
				</tr>
				<tr>
					<th>업종</th>
					<td><span>${comCategory }</span></td>
				</tr>
				<tr>
					<th>주소</th>
					<td><span><%=pdto.getCom_addr() %></span></td>
				</tr>
				<tr>
					<th>연락처</th>
					<td><span><%=pdto.getCom_phone() %></span></td>
				</tr>

				<tr>
					<th>업체점수</th>
					<td><span><%=comdto.getM_score() %></span></td>
				</tr>	
			</table>
			</div>
		</div>
		<div class="rdiv" id="r3">
			<div class="titleinfo">
				<h1>대타정보</h1>	
			</div>	
			<div class="rdiv">
				<table class="infotable">
					<col width="150px"><col width="200px">
				<tr>
					<th>이름</th>
					<td><span><%=adto.getM_name() %></span></td>
				</tr>
				<tr>
					<th>연락처</th>
					<td><span><%=adto.getM_phone() %></span></td>
				</tr>
				<tr>
					<th>주소</th>
					<td><span><%=adto.getM_addr() %></span></td>
				</tr>
				<tr>
					<th>성별</th>
					<td><span>${gender}</span></td>
				</tr>
				<tr>
					<th>평점</th>
					<td><span><%=adto.getM_score() %></span></td>
				</tr>
				
				
				</table>
				<div class="titleinfo">
				<h1>업무능숙도</h1>	
				</div>	
				<div id="chart_div3"></div>	
			</div>
			
		</div>
		
		<div class="rdiv" id="r4">
			<div class="titleinfo">
				<h1 id="progress">진행중</h1>
				<div id="btn">
				<input type="button" value="수락" id="acBtn" onclick="nocancle();">
				<input type="button" value="거절" id="canBtn"onclick="cancle();">	
				</div>					
			</div>	
		</div>
		
	</div>
</div>
<c:forEach var="ability" items="${albaabilitiy }">
				
							<input type="hidden" id="${ability.c_no }" value="${ability.a_cnt }">
				
						</c:forEach>
</section>
	<!-- 푸터 -->
	<%@ include file="../common/footer.jsp" %>
</body>
</html>