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
<title>대타몬: 근처업체찾기</title>
<link rel="stylesheet" type="text/css" href="views/css/findnearcomform.css">
<link rel="stylesheet" type="text/css" href="../css/findnearcomform.css">
</head>


<!--  주소 api -->		          
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<!--  jQuery -->
<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>

<!--  KAKAO MAP API -->
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=484abe6cee8cb658e2dd29b7c144de1d&libraries=services"></script>

<script type="text/javascript">
	/* 주소 검색 팝업창뜨는 함수 */
	function findAddr(input){
		 new daum.Postcode({
	        oncomplete: function(data) {
	            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
	            // 예제를 참고하여 다양한 활용법을 확인해 보세요.
	            var addr = data.address; // 최종 주소 변수
	            
	            $(input).val(addr);
	            $("#postaddr").val(addr);
	            
	            var geocoder = new kakao.maps.services.Geocoder();
	            //주소로 좌표를 검색합니다  //고른주수로	         	       	
	         	geocoder.addressSearch( $("#findaddr").val(), function(result, status) {

	         	    // 정상적으로 검색이 완료됐으면 
	         	     if (status === kakao.maps.services.Status.OK) {
	         			
	           	       var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
	         			
	           	       		//히든input value에 좌표삽입
	         	     		$("#findx").val(result[0].y);
	         	     		$("#findy").val( result[0].x);
	         		}
	         	});
	        }
	    }).open();
	};
	
	$(function(){
		$("#submitBtn").click(function(){
						
			if( $("#findaddr").val() == "" || $("#findaddr").val()==null ){
				alert("주소를 입력해주세요");
				return false;
			}else if($("#findx").val() =="" || $("#findx").val() == null ||$("#findy").val() =="" || $("#findy").val() == null  ){
				alert("이미 검색한 주소입니다.");
				return false;
			}
			$("#postaddr").val( encodeURIComponent( $("#postaddr").val() )  );
		});
	});
	
	function movePage(pagenum){
		$("#pagenum").val(pagenum.text);
		$("#command").val("movepage");
		var findform = document.getElementById('findform');
		$("#postaddr").val( encodeURIComponent( $("#postaddr").val() )  );
		findform.submit();
		
	}
	function nextPageGroup(){
		if( ${endPage < totalPageNum}){
			$("#pagenum").val(${endPage+1});
			
			$("#command").val("movepage");
			var findform = document.getElementById('findform');
			$("#postaddr").val( encodeURIComponent( $("#postaddr").val() )  );
			
			findform.submit();
		}
		
		
	}
	function prePageGroup(){
		if( ${startPage - 1 > 0}){
			$("#pagenum").val(${startPage-1});
			
			$("#command").val("movepage");
			var findform = document.getElementById('findform');
			$("#postaddr").val( encodeURIComponent( $("#postaddr").val() )  );
			findform.submit();
		}
	}
	
</script>
<body>
	<!-- 헤더 -->
	<%@ include file="../common/header.jsp" %>
	
	<!-- 게시판 타이틀 -->
	<div id="boardtitle">
		<p>근처 업체 찾기</p>
	</div>
	
	<!-- 업체 검색 부분 -->
	<div id="findcom">
		<form action="controller.do" method="post" id="findform" name="findform">
			<input type="hidden" name="command" value="findnearcom" id="command">
			<c:choose>
				<c:when test="${findaddr ==null }">
					<input type="text" size="50" style="height:30px" onclick="findAddr($(this));" name="findaddr" id="findaddr" readonly="readonly">
					<input type="hidden" name="postaddr" id="postaddr">
				</c:when>
				<c:otherwise>
					<input type="text" size="50" style="height:30px" onclick="findAddr($(this));" name="findaddr" id="findaddr" readonly="readonly" value="${findaddr }">
					<input type="hidden" name="postaddr" id="postaddr" value="${findaddr }">	
				</c:otherwise>
			</c:choose>
			
			<input type="submit" value="검   색" id="submitBtn" onclick="test(this)">
			<input type="hidden" name="findx" id="findx" value=${findx }>
			<input type="hidden" name="findy" id="findy" value=${findy }>
			<input type="hidden" name="pagenum" id="pagenum">
		</form>
	</div>
	
	<!-- 검색한 게시판 
	  -->
	<div id="boardlist" style="text-align: center">
		<table >
			<col width="600px"><col width="150px"><col width="150px"><col width="200px"><col width="150px"><col width="150px">
			<tr>
				<th>회사명 / 공고제목</th>
				<th>주소</th>
				<th>거리</th>
				<th>근무날짜 / 근무시간</th>
				<th>급여</th>
				<th>작성시간</th>
			</tr>
			
			<!-- 테이블 생성 부분 -->
			<c:choose>
				<c:when test="${findnearlist ==null }">
					<tr>
						<td colspan="6">근 처 업 체 가 없 습 니 다</td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach var="nearcom" items="${findnearlist }">
					<!--  거리 검색 조건!!! -->
					
						<tr onclick="location.href='controller.do?command=boarddetail&no=${nearcom.no }'">
							<td id="title_first">
								<span>
									<p id="com_name">${nearcom.com_name }</p>
									<p id="title">${nearcom.title }</p>
								</span>
								
							</td>
							<td>${nearcom.addr }</td>
							<td>${nearcom.distance }km</td>
							<td>
								<fmt:formatDate value="${nearcom.start_date}" pattern="MM/dd"/>
								~
								<fmt:formatDate value="${nearcom.end_date}" pattern="MM/dd"/>
								<br>
								<fmt:formatDate value="${nearcom.start_time}" pattern="HH:mm"/>
								~
								<fmt:formatDate value="${nearcom.end_time}" pattern="HH:mm"/>
							</td>
							<td>시급<br>${ nearcom.money}원</td>
							<td>
							<fmt:formatDate value="${nearcom.regDate }" pattern="MM/dd HH:mm"/>
							
							</td>
						</tr>
					
					
				</c:forEach>
				
				
				</c:otherwise>
			</c:choose>
			
			
		</table>
	</div>
	<c:if test="${findnearlist !=null }">
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
				</div>
			</c:if>
	<!-- 퓨터 -->
	<%@ include file="../common/footer.jsp" %>
</body>
</html>