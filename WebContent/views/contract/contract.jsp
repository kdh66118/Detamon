<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>    
<% response.setContentType("text/html; charset=UTF-8"); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="detamon.dto.MemberDto" %>
<%@ page import="detamon.dto.JobBoardDto" %>
<%@ page import="detamon.dto.CompanyDto" %>
<%@ page import="detamon.dto.ContractDto" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>대타몬:계약확인-계약정보</title>
<link rel="stylesheet" type="text/css" href="views/css/contract.css">
<link rel="stylesheet" type="text/css" href="../css/contract.css">
</head>
<!-- 로그인 정보 받아오기  -->
<%  
	//현재 로그인정보 가져오기
	MemberDto dto = (MemberDto)session.getAttribute("login");
	//계약정보 list로 받아오기
	List<ContractDto> cdto = (List<ContractDto>)session.getAttribute("cdto");
	//회원의 id값으로 회원정보 
	MemberDto udto = (MemberDto)session.getAttribute("udto");
	System.out.println("cdto"+cdto.isEmpty());
%>
<script type="text/javascript" src=" https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
//새로고침해야 데이터를 뿌려주는 상황이 발생 .. 일단은 자바스크립트 코드로 새로고침 1회 하도록 처리
$(function(){
	  if (self.name != 'reload') {
	         self.name = 'reload';
	         self.location.reload(true);
	     } else self.name = ''; 

});

$(function(){
	var grade= <%=dto.getM_role()%>;
	$('input[name=type_no]').each(function(idx){
		var value = $(this).val();

		console.log("등급과"+grade+"밸류"+value);
		//게시판 종류 1번 = 대타찾기글 알바생이 올리고 업체가 신청,  2번 = 업체찾기글 업체가 글올리고 알바생이 지원하는거 
		// 등급이 2이고 게시글 no가 1이면 알바생이 올린글 업체가 신청
		if(grade == 2 && value == 1){
  			$('span[name=progress]').eq(idx).text("신청완료");
		}
		// 등급이 2이고 게시글 no가 2이면 업체가 올린 대타찾기 글 알바가 이글을 보고 신청넣은 상황임  
		else if(grade == 2 && value ==2){	
  			$('span[name=progress]').eq(idx).text("수락대기중");
		}
		// 등급이 1이고  게시글 no가 1이면 알바생이 올린 대타찾기글을 보고 업체가 신청 넣은 상황
		else if(grade ==1 && value == 1){
  			$('span[name=progress]').eq(idx).text("수락대기중");
		}
		else{
  			$('span[name=progress]').eq(idx).text("신청완료");
		}
	});
});


$(function(){
	  $('input[name=accept]').each(function(idx){       
	        // 수락여부 확인
	        var value = $(this).val();	   
	        var eqValue = $("input[name=accept]:eq(" + idx + ")").val() ;
	        console.log(eqValue == 'X');
	        
	  		if(eqValue == 'X'){
	  			$('input[name=infoBtn]').eq(idx).attr('disabled', true).val("거절");
	  			$('span[name=progress]').eq(idx).text("종료");
	  		}else if(eqValue == 'Z'){
	  			$('input[name=infoBtn]').eq(idx).attr('disabled', false).val("정보확인");
	  			$('span[name=progress]').eq(idx).text("계약종료");
	  		}
	  }); 
	
});

//페이징

	function movePage(pagenum){
		$("#pagenum").val(pagenum.text);
		var pageform = document.getElementById('pageform');
		pageform.submit();
		
	}
	function nextPageGroup(){
	
		if( ${endPage < totalPageNum}){
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
<!-- 로그인 정보 있을 때  -->

<header>
	<!-- 헤더 -->
	<%@ include file="../common/header.jsp" %></header>
<section>
<ul id="left">
	<li><a href="controller.do?command=contract">계약정보</a></li>
	<li><a href="controller.do?command=contracted&is_contract=Y&userId=<%=dto.getM_id()%>&role=<%=udto.getM_role()%>">체결된계약</a></li>
</ul>
	<div id="right">
			<h1>계약정보</h1>
			<div class="r1">
				<table id="rtable">
					<col width="100px"><col width="250px"><col width="350px"><col width="100px"><col width="200px">
						<thead>
						<tr>
							<th>번호</th>
							<th>알바생ID</th>
							<th>업체ID</th>
							<th>상태</th>				
							<th>계약정보확인</th>
						</tr>
						</thead>
			   		<c:choose> 
						<c:when test="${empty list }"> <!-- 비어있을때 -->
					<tr>
						<td colspan="5">-----글이 존재하지 않습니다.-----</td>	
					</tr>
					</c:when>
						<c:otherwise> <!-- 아닐때 -->
							<c:forEach var="item" items="${list }" varStatus="status">
								<c:set var="sum" value="${sum+1}"/>
									<tbody>
									<tr>
									<td><a href="controller.do?command=boarddetail&no=${item.board_no}">${sum }</a></td> <!-- 번호 매기기 -->
									<td>${item.parttimer_id }</td>		<!-- 알바생 아이디 -->
									<td>${item.company_id }</td>			<!-- 업체 아이디 -->
									<td><span name="progress">진행중</span><input type="hidden" name="accept" value="${item.is_contract }"></td>
									<td><input type="button" name="infoBtn" value="정보확인" onclick="location.href='controller.do?command=contractinfo&jobboardno=${item.board_no}&companyId=${item.company_id }&parttimerId=${item.parttimer_id }&accept=${item.is_contract }'"></td>
									<td><input type="hidden" name="type_no" value="${jlistdto[status.index].type_no }">	</td>
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
						<input type="hidden" name="command" value="contract">
						<input type="hidden" name="pagenum" id="pagenum">
					</form>
				
	 </div>
	
	</div>
</section>
	<!-- 푸터 -->
	<%@ include file="../common/footer.jsp" %>
</body>
</html>