<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>    
<% response.setContentType("text/html; charset=UTF-8"); %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="detamon.dto.MemberDto" %>
<%@ page import="detamon.dto.JobBoardDto" %>
<%@ page import="detamon.dto.CompanyDto" %>
<%@ page import="detamon.dto.ContractDto" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="detamon.dto.MemberScoreDto" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>대타몬:계약확인-체결된계약</title>
<link rel="stylesheet" type="text/css" href="views/css/contracted.css?ver=1">
</head>
<% 	
	//현재 로그인 정보 가져오기	
	MemberDto dto = (MemberDto)session.getAttribute("login"); 
	//계약정보 list로 받아오기
	List<ContractDto> conDto = (List<ContractDto>)session.getAttribute("contractedDto");
	//회원의 id값으로 회원정보 
	MemberDto udto = (MemberDto)session.getAttribute("udto");
	//게시글정보 받아오기
    List<JobBoardDto> jlistdto = (List<JobBoardDto>)session.getAttribute("jlistdto");
	System.out.println(jlistdto.toString());
	//점수판 받아오기
    List<MemberScoreDto> scoredto = (List<MemberScoreDto>)session.getAttribute("scoredto");
	System.out.println(scoredto.toString());

%>
<script type="text/javascript" src=" https://code.jquery.com/jquery-3.5.1.min.js?ver=1"></script>
<script type="text/javascript">
	function estimate(i){
		var alba = $("a[name=albaId]").eq(i-1).text();
		var com = $("a[name=comId]").eq(i-1).text();
		var board_no = $("input[name=boardno]").eq(i-1).val();
		//평가버튼 비활성화를 위한 평가버튼 인덱스
			
		var url = "controller.do?command=score&albaId="+alba+"&comId="+com+"&boardno="+board_no;
		var title = "estimate";
        var prop = "top=200px, left=200px, width=250px, height=200px ";
        var windowObj = window.open(url, title, prop);
        console.log(com)
        console.log(alba);
        //console.log(board_no);
        //windowObj.document.getElementById("albaId").value = $("a[name=comId]").eq(i-1).text();

	}

	$(function(){		
  
		//날짜로 비교 
	      $("input[name=enddate]").each(function(idx){       
		        // 종료 날짜 정보 가져오기
		        var value = $(this).val();	   
		        var eqValue = $("input[name=enddate]:eq(" + idx + ")").val();
		        var time = $("input[name=endtime]:eq(" + idx + ")").val();

		        console.log("엔드날짜?"+eqValue+"엔드타임"+time);
		        var enddate = new Date(eqValue);
		        var endtime = new Date(time);
		       	console.log("엔드타임은?"+enddate+"종료시간"+endtime);
		        var day = enddate.getDate(); // 일
		        var month = enddate.getMonth()+1; // 월
		        var year = enddate.getFullYear(); // 년
		        var hours = endtime.getHours(); // 시
		        var minutes = endtime.getMinutes();  // 분
		        console.log(year+"년"+month+"월"+day+"일"+hours+"시"+minutes+"분");
		       
			    var value = $(this).val();	   
			    var albaValue = $("input[name=albascore]:eq(" + idx + ")").val();
			    var comValue = $("input[name=comscore]:eq(" + idx + ")").val();
			    
			    var grade = ${udto.m_role};
			    
		        
		        //알바 종료 날짜시간
		        var date1 = new Date(year, month-1, day, hours, minutes);
		        //현재 날짜 시간
		        var date2 = new Date();
		        
		        var diffTime = (date2.getTime() - date1.getTime()) / (1000*60*60);
		        
		        if(day<10){
		        	day='0'+day
		        }
		        if(month<10){
		        	month='0'+month
		        }
		        
		         console.log(idx+"번   알바종료"+date1); 
		         console.log(idx+"번   오늘시간"+date2); 

		         console.log(idx+"번  "+diffTime); 
			  	//0보다 크면 평가가능
			  	if(diffTime<0){
			  		$('input[name=estimate]').eq(idx).attr('disabled', true).val("종료 후 진행가능").css("background", "gray");
			         console.log(idx+"-일경우만 "+diffTime); 
				  	$('span[name=progress]').eq(idx).text("진행중");	 

			  	}
			  	//종료 날짜가 오늘 날짜보다 작으면 평가button활성화
			  	else{		
			  		//$('input[name=estimate]').eq(idx).attr('disabled', false);
			  		// $("input[name=estimate]").each(function(idx){       
					        // 

					   // console.log("알바밸류"+albaValue);
					    //console.log("컴밸류"+comValue);
					    //console.log("로그인유저 등급"+grade);
					    //업체이고 업체가 알바생 평가 했을 때	
					    if(grade == 2){ // albaValue = 업체가 알바평가  comValue = 알바가 업체평가
					    	// 업체와 알바생이 둘다 평가 했을경우 
					    	if(albaValue=="Y" && comValue=="Y"){
						  		$('input[name=estimate]').eq(idx).attr('disabled', true).val("평가완료").css("background", "gray");
						  		$('span[name=progress]').eq(idx).text("종료");	   
					    	}
					    	// 업체만 평가한 경우
					    	else if(albaValue=="Y" && comValue=="N"){
						  		$('input[name=estimate]').eq(idx).attr('disabled', true).val("평가완료").css("background", "gray");
						  		$('span[name=progress]').eq(idx).text("평가중");	   
					    	}
					    	//업체만 평가 안한경우
					    	else if(albaValue=="N" && comValue=="Y"){
					  			$('input[name=estimate]').eq(idx).attr('disabled', false);
						  		$('span[name=progress]').eq(idx).text("평가중");	 
					    	}
					    	//둘다 안한경우
					    	else if(albaValue=="N" && comValue=="N"){
					  	//		$('input[name=estimate]').eq(idx).attr('disabled', true);
						 		$('span[name=progress]').eq(idx).text("평가");	 
					    	}
					    }
					    //업체가 아닌경우 = 알바생 
					    else{
					    	//업체 알바생 둘다 했을겨우 
					    	if(comValue == "Y" && albaValue == "Y"){
						  		$('input[name=estimate]').eq(idx).attr('disabled', true).val("평가완료").css("background", "gray");
						  		$('span[name=progress]').eq(idx).text("종료");	 
					    	}
					    	//알바만 평가한경우 
					    	else if(comValue == "Y" && albaValue == "N"){
						  		$('input[name=estimate]').eq(idx).attr('disabled', true).val("평가완료").css("background", "gray");
						  		$('span[name=progress]').eq(idx).text("종료");	
					    	}
					    	//업체만 평가한경우
					    	else if(comValue == "N" && albaValue == "Y"){
					  			$('input[name=estimate]').eq(idx).attr('disabled', false);
						  		$('span[name=progress]').eq(idx).text("평가중");	 
					    	}
					    	//둘다 안한경우
					    	else if(albaValue=="N" && comValue=="N"){
					  		//	$('input[name=estimate]').eq(idx).attr('disabled', true);
						  		$('span[name=progress]').eq(idx).text("평가");	 
					    	}
					    }
					    	    
				      //});      
			  	}
			  	//alert(new Date());
			  	//alert(new Date(year, month-1, day, hours, minutes, seconds));	
			  	//alert(new Date()<=new Date(year, month-1, day, hours, minutes, seconds));           
	      });
		//평가여부확인
	     

	});		

	//서블릿으로 알바생 id, 업체 id, 점수  보내기
	function insertScore(score, alba, com, boardno){
		console.log(alba+com+score+boardno);
		location.href="controller.do?command=insertScore&role=${udto.m_role}&albaId="+alba+"&comId="+com+"&score="+score+"&boardno="+boardno;
	}
	//자식창에서 전달받은 인덱스로 평가버튼 비활성화시키기

	//페이징

	function movePage(pagenum){
		$("#pagenum").val(pagenum.text);
		var pageform = document.getElementById('pageform');
		pageform.submit();
		
	}
	function nextPageGroup(){
	
		if( ${endPage  < totalPageNum}){
			$("#pagenum").val(${endPage+1});
			var pageform = document.getElementById('pageform');
			pageform.submit();
		}
		
	}
	function prePageGroup(){

		if( ${startPage - 1 > 0}){
			$("#pagenum").val(${startPage-1});
			var pageform = document.getElementById('pageform');
			pageform.submit();
		}
	}

</script>
<body>

<header>
	<!-- 헤더 -->
	<%@ include file="../common/header.jsp" %></header>
<section>
<ul id="left">
	<li><a href="controller.do?command=contract">계약정보</a></li>
	<li><a href="controller.do?command=contracted&is_contract=Y&userId=<%=dto.getM_id()%>&role=<%=udto.getM_role()%>">체결된계약</a></li>
</ul>
	<div id="right">
			<h1>체결된계약</h1>
			<div class="r1">
				<table id="rtable">
				<!-- 자식창에서 점수와 코멘트 담을 input태그 -->
				<input type="hidden" name="score" >
				<input type="hidden" name="alba" >
				<input type="hidden" name="com" >
				
					<col width="100px"><col width="400px"><col width="200px"><col width="100px"><col width="200px">
						<thead>
						<tr>
							<th>알바생ID</th>
							<th>업체ID</th>
							<th>계약정보확인</th>
							<th>상태</th>				
							<th>평가하기</th>
						</tr>
						</thead>
				   <c:choose> 
						<c:when test="${empty conDto }"> <!-- 비어있을때 -->
							<tr>
								<td colspan="5">-----글이 존재하지 않습니다.-----</td>	
							</tr>
						</c:when>
						<c:otherwise> <!-- 아닐때 -->
							<c:forEach var="item" items="${list }" varStatus="status">
									<tbody>
										<tr>
											<td><a href="controller.do?command=boarddetail&no=${item.board_no}" name="albaId">${item.parttimer_id }</a></td>		<!-- 알바생 아이디 -->
											<td><a href="controller.do?command=boarddetail&no=${item.board_no}" name="comId">${item.company_id }</a></td>
											<td><input type="button" value="정보확인" onclick="location.href='controller.do?command=contractinfo&jobboardno=${item.board_no}&companyId=${item.company_id }&divide=${scoredto[status.index].divide}&parttimerId=${item.parttimer_id }&accept=Y'"></td>
											<td><span name="progress">수락</span></td>
											<td><input type="button" value="평가하기" name="estimate" onclick="estimate(${status.count});"></td>
											<input type="hidden" name="boardno" value="${item.board_no}">								
											<input type="hidden" name="enddate" value="${jlistdto[status.index].end_date }">
											<input type="hidden" name="endtime" value="${jlistdto[status.index].end_time }">
											<input type="hidden" name="albascore" value="${item.albaestimate }">
											<input type="hidden" name="comscore" value="${item.comestimate }">
											
										</tr>
									</tbody>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</table>
			</div>
	
		
	<!-- 페이징 -->
	<div class = "pagin_div">
					<ul class="pagin" >
						<li class="page_li"><a class="next_page" onclick="prePageGroup();"><</a></li>
						
						<c:forEach var="i" begin="${startPage }" end="${endPage }" step="1" varStatus="status">
							<c:choose>
								<c:when test="${currentPage == startPage + status.count -1 }"><li class="page_li"><a class="page_a current_page" onclick="movePage(this);">${startPage + status.count -1 }</a></li></c:when>
								<c:otherwise><li class="page_li"><a class="page_a" onclick="movePage(this);">${startPage + status.count -1 }</a></li></c:otherwise>
							</c:choose>
						</c:forEach>
						<li class="page_li"><a class="next_page" onclick="nextPageGroup();">></a></li>
					</ul>
					
					<form action="controller.do" method="post" id="pageform" name="pageform">
						<input type="hidden" name="command" value="contracted">
						<input type="hidden" name="pagenum" id="pagenum">
						<input type="hidden" name="is_contract" value="Y">
						<input type="hidden" name="userId" value="<%=dto.getM_id()%>">
						<input type="hidden" name="role" value="<%=udto.getM_role()%>">
						
					</form>
				
	 </div>
	
	</div>

</section>
	<!-- 푸터 -->
	<%@ include file="../common/footer.jsp" %>
</body>
</html>