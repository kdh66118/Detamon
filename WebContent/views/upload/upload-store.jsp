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
<!DOCTYPE html>
<html>
<head>
<!-- 로그인 정보 받아오기  -->
<%  
	//현재 로그인정보 가져오기
	MemberDto m = (MemberDto)request.getAttribute("m");
	//회원의 id값으로 업체정보
	CompanyDto comdto = (CompanyDto)request.getAttribute("comdto");
	System.out.println("회사정보는 ???"+comdto.toString());
%>
<meta charset="UTF-8">
<title>업체 찾기</title>
<link rel="stylesheet" type="text/css" href="views/css/upload-store.css">
<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>   
<script type="text/javascript">

		$(function(){
			var startdate = document.getElementById("start-date");
			var enddate = document.getElementById("end-date");
	
			enddate.onchange = function(){
				startdate = document.getElementById("start-date");
				enddate = document.getElementById("end-date");
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
    	
    	function checksumbit(frm){
		
		if($("#title-area").val().trim()=="" || $("#title-area").val()==null ){
			alert("제목을 입력해주세요");
		}else if ($("#content-area").val().trim()=="" || $("#content-area").val()==null ){
			alert("내용을 입력해주세요");
			
		}else if ($("#store-namelabel").val().trim()=="" || $("#store-namelabel").val()==null ){
			alert("이름을 입력해 주세요.");
		}else if($("#store-phonelabel").val().trim()=="" || $("#store-phonelabel").val()==null){
			alert("연락처를 입력해주세요.");
		}else if($("#store-locationlabel").val().trim()=="" || $("#store-locationlabel").val()==null){
			alert("주소를 입력해주세요.");
		}else if($("#store-creditlabel").val().trim()=="" || $("#store-creditlabel").val()==null){
			alert("신뢰도를 입력해주세요.");
			
		}else if($("#pay-text").val().trim()=="" || $("#pay-text").val()==null){
			alert("급여를 입력해주세요.");
		}else if($("#start-date").val().trim()=="" || $("#start-date").val()==null){
			alert("시작 날짜를 지정해주세요.");
		}else if($("#end-date").val().trim()=="" || $("#end-date").val()==null){
			alert("종료 날짜를 지정해주세요.");	
		}else if($("#start-time").val().trim()=="" || $("#start-time").val()==null){
			alert("시작 시간을 지정해주세요.");
		}else if($("#end-time").val().trim()=="" || $("#end-time").val()==null){
			alert("종료 시간을 지정해주세요.");	
		}else if( $(':checkbox[name="job-name"]:checked').length < 1){
			alert("업종을 체크해주세요");
			
	//	}else if( $(':select[name="location-btn"]:checked').length < 1){
	//		alert("지역을 체크해주세요");
		}else{
			frm.submit();
		}
		

	}
    </script>

</head>
<body>
<jsp:include page="../common/header.jsp"></jsp:include>
	<div id="body">
		
		<div id="center">
		
		<br>
			<form action="controller.do?command=insertJobboard" method="post">
			<input type="hidden" name="m_role" value="<%=m.getM_role()%>">
			<input type="hidden" name="writer" value="<%=m.getM_id()%>">
			<div id="store-intro">
				<p><strong>업체 소개</strong></p>
				<div id="title">
					<label id="mini-title" >제목</label>
					<textarea rows="1" cols="100" id="title-area" name="title" placeholder="50자 이내"></textarea>
				</div>
				
				<div id="content">
					<label id="mini-content">내용</label>
					<textarea rows="7" cols="100" id="content-area" name="content" placeholder="300자 이내"></textarea>
				</div>
			</div>
			
				<br><br>
				
				
			<div id="store-impo">
				<p><strong>업체 정보</strong></p>

				<div id="name">
         			<label id="store-name">업체명:</label>
         			<input type="text" readonly="readonly" name="comInfo" id="store-namelabel" value="<%=comdto.getCom_name()%>">
         		</div>

          		<div id="phone">
          			<label id="store-phone">연락처:</label>
					<input type="text" readonly="readonly" name="comInfo" id="store-phonelabel" value="<%=comdto.getCom_phone()%>">
          		</div>

          		<div id="address">
          			<label id="store-location">주소:</label>
          			<input type="text" readonly="readonly" name="comInfo" id="store-locationlabel" value="<%=comdto.getCom_addr()%>">
          		</div>

         		<div id="credit">
         			<label id="store-score">신뢰도: </label>
         			<input type="text" readonly="readonly" name="comInfo" id="store-creditlabel" value="${login.m_score }">
        		</div>
         	</div>
         		
         		<br>
         		
         		<div id="recruit">
         		<p><strong>공고 조건</strong></p>
        		<div id="pay">
        			<label id="pay-label">급여: </label>
        			<input type="text" name="money" id="pay-text">
        		</div>
        		
        		<div id="date">
        			<label id="date-label">근무 날짜: </label>
        			<input type="date" name="start-date" id="start-date"> ~ <input type="date" name="end-date" id="end-date">
        			<br>
        			<input type="time" name="start-time" id="start-time"> ~ <input type="time" name="end-time" id="end-time">
        		</div>
        		
         		<div id="job">
         		<label id="hope-job">업종:</label>
         		<span id="job-btn">
         		<input type="checkbox" name="job-name" value="1" style="width:13px;height:13px;">카페
				<input type="checkbox" name="job-name" value="2" style="width:13px;height:13px;">편의점
				<input type="checkbox" name="job-name" value="3" style="width:13px;height:13px;">음식점
				<input type="checkbox" name="job-name" value="4" style="width:13px;height:13px;">일용직
				<input type="checkbox" name="job-name" value="5" style="width:13px;height:13px;">배달
				<input type="checkbox" name="job-name" value="6" style="width:13px;height:13px;">유흥시설
				<input type="checkbox" name="job-name" value="7" style="width:13px;height:13px;">기타
         		</span>
         		</div>
         		
         		<div id="location-seoul">
         			<label id="hope-location">지역:</label>
          				<select id="location" name="location" style="font-size:10pt;">
          				<option value="1">강남구</option>
                		<option value="4">강동구</option>
                		<option value="23">강북구</option>
                		<option value="8">강서구</option>
                		<option value="6">관악구</option>
                		<option value="19">광진구</option>
                		<option value="10">구로구</option>
                		<option value="11">금천구</option>
                		<option value="25">노원구</option>
                		<option value="24">도봉구</option>
						<option value="14">동대문구</option>
						<option value="5">동작구</option>
           				<option value="16">마포구</option>
             			<option value="21">서대문구</option>
               			<option value="3">서초구</option>
                		<option value="18">성동구</option>
              			<option value="22">성북구</option>
                		<option value="2">송파구</option>
                		<option value="9">양천구</option>
                		<option value="7">영등포구</option>
                		<option value="17">용산구</option>
                		<option value="20">은평구</option>
              			<option value="12">종로구</option>
               			<option value="13">중구</option>
                		<option value="15">중랑구</option>
          				</select>
          				<br>
         		</div>
        				<br><br>
         				<input type="submit" value="공고 올리기" id="complete" onclick="checksumbit(this.form);">
         	</div>

			<jsp:include page="../common/footer.jsp"></jsp:include>
			</form>
		</div>
	</div>
</body>
</html>