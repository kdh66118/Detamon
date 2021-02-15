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
    	var startdate = document.getElementById("startdate");
    	var enddate = document.getElementById("enddate");
    	
    	enddate.onchange = function(){
    		startdate = document.getElementById("startdate");
    		enddate = document.getElementById("enddate");
    		if( enddate.value < startdate.value && startdate.value !=null && startdate.value.trim() != ""){
    			alert("시작 날짜보다 빠른 날짜입니다. ");
    			enddate.value = null;
    		}
    	}
    	startdate.onchange = function(){
    		startdate = document.getElementById("startdate");
    		enddate = document.getElementById("enddate");
    		if( enddate.value < startdate.value && enddate.value !=null && enddate.value.trim() != ""){
    			alert("끝 날짜보다 늦은 날짜입니다. ");
    			startdate.value = null;
    		}
    	}
       
    });
    function updateform(frm){
    	
    	if ( $("#title").val().trim()=="" || $("#title").val()==null ){
    		alert("제목을 입력해주세요.");
    	}else if( $("#content").val().trim()=="" || $("#content").val()==null ){
    		alert("내용을 입력해주세요.");
    	}else if( $("#startdate").val().trim()=="" || $("#startdate").val()==null ){
    		alert("시작 날짜를 입력해주세요.");
    	}else if( $("#enddate").val().trim()=="" || $("#enddate").val()==null ){
    		alert("끝 날짜를 입력해주세요.");
    	}else if( $("#starttime").val().trim()=="" || $("#starttime").val()==null ){
    		alert("시작 시간을 입력해주세요.");
    	}else if( $("#endtime").val().trim()=="" || $("#endtime").val()==null ){
    		alert("끝 시간을 입력해주세요.");
    	}else if( $("#money").val().trim()=="" || $("#money").val()==null ){
    		alert("시급을 입력해주세요.");
    	}else if( $("#loc").val().trim()=="" || $("#loc").val()==null ){
    		alert("지역을 입력해주세요.");
    	}else if( $("#category").val().trim()=="" || $("#category").val()==null ){
    		alert("업종을 입력해주세요.");
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
	<form action="controller.do?" method="post">
	<input type="hidden" name="command" value="updateboard">
	<input type="hidden" name="no" value="${board.no }">
	<div id="container">
		<div class="title_div">
			<div class="com_name">${company.com_name }</div>
			<div class="board_title">
			<input type="text" value="${board.title }" name="title" style="width:1000px;" id="title">
				
			</div>
			
		</div>
				
		<div class="title_div">
		<div class="title_div2">
			<div class="title_text">
				글 내용
			</div>
			<textarea rows="10" cols="35"  style="font-size:20px" name="content" id="content">${board.content }</textarea>
		</div>
		
		<div class="title_div3">
			<div class="title_text">
				희망근무조건
			</div>
			
			<table>
				<col width="100px"><col width="500px">
				<tr>
					<td class="first_text">근무 기간</td>
					<td class="second_text">
						<input type="date" value="<fmt:formatDate value='${board.start_date}' pattern='yyyy-MM-dd'/>" style="display:inline-block;" name="startdate" id="startdate">
						&nbsp~&nbsp
						<input type="date" value="<fmt:formatDate value='${board.end_date}' pattern='yyyy-MM-dd'/>" style="display:inline-block;" name="enddate" id="enddate">
					</td>
				</tr>
				<tr>
					<td class="first_text">근무 시간</td>
					<td class="second_text">
						<input type="time" value="<fmt:formatDate value='${board.start_date}' pattern='HH:mm'/>" style="display:inline-block;" name="starttime" id="starttime">
						&nbsp~&nbsp
						<input type="time" value="<fmt:formatDate value='${board.end_time}' pattern='HH:mm'/>" style="display:inline-block;" name="endtime" id="endtime">
					</td>
				</tr>
				<tr>
					<td class="first_text">시급</td>
					<td class="second_text">
						<input type="text" value="${board.money }" name="money" id="money">원
					</td>
				</tr>
				<tr>
					<td class="first_text">지역</td>
									
					<td class="second_text">
						<select name="loc" id="loc">
						<c:forEach var="loc" items="${loc }">
							<c:choose>
								<c:when test="${loc.l_no == board.loc }">
									<option value="${loc.l_no }" selected="selected">${loc.l_name }</option>
								</c:when>
								<c:otherwise>
									<option value="${loc.l_no }">${loc.l_name }</option>
								</c:otherwise>
							</c:choose>
							
						</c:forEach>
					</select>		
					</td>
				</tr>
				<tr>
					<td class="first_text">업종</td>
					<td class="second_text">
						<select name="category" id="category">
							<c:forEach var="category" items="${category }">
									<c:choose>
										<c:when test="${category.c_no == board.category }">
											<option value="${category.c_no }" selected="selected">${category.c_name }</option>
										</c:when>
										<c:otherwise>
											<option value="${category.c_no }">${category.c_name }</option>
										</c:otherwise>
									</c:choose>
								
							</c:forEach>
						</select>	
					
					</td>
				</tr>
			</table>
				
			
		</div>
		
		</div>
		
		
		
		
		<div class="btn_div">
			
					<input type="button" id = "home_btn" onclick="updateform(this.form);" value="수정하기" style="margin-left: 35%;">
					<input type="button" id = "home_btn" onclick="location.href='controller.do?command=boarddetail&no=${board.no}'" value="취소">
			
			
		</div>
		</div>	
		</form>
	</c:if>
	
	
	<!-- -------------------------------알바찾기 ----------------------------------------------- -->
	<c:if test="${board.type_no==1 }">
	<form action="controller.do?" method="post">
	<input type="hidden" name="command" value="updateboard">
	<input type="hidden" name="no" value="${board.no }">
	<div id="container">
		<div class="title_div">
			<div class="com_name">${member.m_name }</div>
			<div class="board_title">
			<input type="text" value="${board.title }" name="title" style="width:1000px;" id="title">
				
			</div>
			
		</div>
				
		<div class="title_div">
		<div class="title_div2">
			<div class="title_text">
				글 내용
			</div>
			<textarea rows="10" cols="35"  style="font-size:20px" name="content" id="content">${board.content }</textarea>
		</div>
		
		<div class="title_div3">
			<div class="title_text">
				희망근무조건
			</div>
			
			<table>
				<col width="100px"><col width="500px">
				<tr>
					<td class="first_text">근무 기간</td>
					<td class="second_text">
						<input type="date" value="<fmt:formatDate value='${board.start_date}' pattern='yyyy-MM-dd'/>" style="display:inline-block;" name="startdate" id="startdate">
						&nbsp~&nbsp
						<input type="date" value="<fmt:formatDate value='${board.end_date}' pattern='yyyy-MM-dd'/>" style="display:inline-block;" name="enddate" id="enddate">
					</td>
				</tr>
				<tr>
					<td class="first_text">근무 시간</td>
					<td class="second_text">
						<input type="time" value="<fmt:formatDate value='${board.start_date}' pattern='HH:mm'/>" style="display:inline-block;" name="starttime" id="starttime">
						&nbsp~&nbsp
						<input type="time" value="<fmt:formatDate value='${board.end_time}' pattern='HH:mm'/>" style="display:inline-block;" name="endtime" id="endtime">
					</td>
				</tr>
				<tr>
					<td class="first_text">시급</td>
					<td class="second_text">
						<input type="text" value="${board.money }" name="money" id="money">원
					</td>
				</tr>
				<tr>
					<td class="first_text">지역</td>
									
					<td class="second_text">
						<select name="loc" id="loc">
						<c:forEach var="loc" items="${loc }">
							<c:choose>
								<c:when test="${loc.l_no == board.loc }">
									<option value="${loc.l_no }" selected="selected">${loc.l_name }</option>
								</c:when>
								<c:otherwise>
									<option value="${loc.l_no }">${loc.l_name }</option>
								</c:otherwise>
							</c:choose>
							
						</c:forEach>
					</select>		
					</td>
				</tr>
				<tr>
					<td class="first_text">업종</td>
					<td class="second_text">
						<select name="category" id="category">
							<c:forEach var="category" items="${category }">
									<c:choose>
										<c:when test="${category.c_no == board.category }">
											<option value="${category.c_no }" selected="selected">${category.c_name }</option>
										</c:when>
										<c:otherwise>
											<option value="${category.c_no }">${category.c_name }</option>
										</c:otherwise>
									</c:choose>
								<
							</c:forEach>
						</select>	
					
					</td>
				</tr>
			</table>
				
			
		</div>
		
		</div>
		
		
		
		
		<div class="btn_div">
			
					<input type="button" id = "home_btn" onclick="updateform(this.form);" value="수정하기" style="margin-left: 35%;">
					<input type="button" id = "home_btn" onclick="location.href='controller.do?command=boarddetail&no=${board.no}'" value="취소">
			
			
		</div>
		</div>	
		</form>
	</c:if>
	
	
	<!-- 퓨터 -->
	<%@ include file="../common/footer.jsp" %>
</body>
</html>