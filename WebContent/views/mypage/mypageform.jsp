<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>대타몬: 마이페이지</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="views/css/mypage.css">
<%@ page import="detamon.dto.MemberDto" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 <%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<% MemberDto dto = (MemberDto)session.getAttribute("login"); %>

</head>

<body>
<jsp:include page="../common/header.jsp"></jsp:include>
	<div class="container">
		<h1 class="title">MY PAGE</h1>
		<div class=mypage_container>
		  <div class="row">
		    <div class="col-10">
			    <div class="row">
			    	<div class="col info_box">
			    	<div style="margin-bottom : 80px; margin-top: 10px;">
						<h1 style="font-size : 30px; float : left;">회원정보</h1>
						<button style="float : right;" class="btn btn-warning" onclick="location.href='controller.do?command=membermodifyform'">수정</button>
					</div>
						<table class="table">

	
						  <tbody>
						    <tr>
						      <td>아이디 :</td>
						      <td><%=dto.getM_id() %></td>
						    </tr>
						    <tr>
						      <td>이름 :</td>
						      <td><%=dto.getM_name() %></td>
						    </tr>
						    <tr>
						      <td>회원등급 :</td>
						      <%
						      if(dto.getM_role()==2){
						      %>
						      <td>사장</td>
						      
						      <%
						      }else{
						      %>
						      <td>알바</td>
						      <%
						      }
						      %>
						    </tr>
		
						  </tbody>
					
						</table>
					</div>
					
			    	<div class="col info_box" style="position:static;" >
			    		<div style="margin-bottom : 80px; margin-top: 10px;">	
							<h1 style="font-size : 30px; float : left;">비밀번호</h1>
							<button style="float : right;" class="btn btn-warning" onclick="location.href='controller.do?command=changeform'">변경</button>
						</div>
						<div style="margin-bottom : 80px; margin-top: 10px;">
							<h1 style="font-size : 30px; float : left;">회원 탈퇴</h1>
							<button style="float : right;" class="btn btn-warning" onclick="window.open('views/mypage/deletemember.jsp','회원탈퇴','width=400,height=200,location=no,status=no,scrollerbars=no');">탈퇴</button>
						</div>
						<div style="position:absolute; bottom:200px;">	
							<h1 style="font-size : 30px; float : left;">내가 쓴글</h1>
							<button style="position: absolute; left:414px;" class="btn btn-warning" onclick="location.href='controller.do?command=myboard'">확인</button>
						</div>
						<%
							if(dto.getM_role()==2){						
						%>
						<div style="position:absolute; bottom:245px;">	
							<h1 style="font-size : 30px; float : left;">업체 정보</h1>
							
				
							
							<button style="position: absolute; left:414px;" class="btn btn-warning" onclick="location.href='controller.do?command=storemodifyform'">수정</button>
						</div>
						<%
							}
						%>
						<%if(dto.getM_role()==2){ %>
						<c:choose>
						<c:when test="${dto2.m_id ==null  }">
						<button style="position: absolute; left:947px; bottom:243px;" class="btn btn-warning" onclick="location.href='controller.do?command=storeupload'">등록</button>
						</c:when>
						</c:choose>
						<%} %>
					</div>
					
			    </div>
			    <div class="row">
			    	<div class="col info_box2">
			    		<div style="margin-top : 10px;">
			    			<h1 style="font-size : 30px; float : left;">나의 평가</h1><br>
			    			<%
								if(dto.getM_role()==1){
			    			%>
			    			<button style="position: absolute; left:800px;" class="btn btn-warning" onclick="location.href='controller.do?command=myprofile'">프로필 확인</button>
			    			<%
								}
			    			%>
			    		</div>
			    		<br>
			    		
			    		<div class="progress" style="margin-top : 50px; margin-bottom : 50px;">
						  <div class="progress-bar  bg-warning" role="progressbar" style="width:<%=dto.getM_score()%>%;"  ><%=dto.getM_score() %>점</div>
						</div>
			    	</div>
			    </div>
			</div>
		   
		</div>
	</div>
	</div>
	<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>