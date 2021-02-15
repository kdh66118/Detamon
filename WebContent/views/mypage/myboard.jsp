<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>대타몬: 내게시글 보기</title>
<link rel="stylesheet" type="text/css" href="views/css/albaboard.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
</head>
<body>
<jsp:include page="../common/header.jsp"></jsp:include>
	<br><br><br>
	<h1 style="font-size:30px; margin-left: 20%;">내 게시글</h1>
	<br><br><br>
	
<table class="table table-hover" id="ctn">
		<thead style="font-weight:bold; font-size:15px;">
			<tr>
				<th>게시글제목</th>
				<th>지역</th>
				<th>기간/시간</th>
				<th>급여</th>
				<th>등록일</th>
			</tr>
		</thead>
		<tbody style="font-weight:bold;">
			<c:choose>
				<c:when test="${empty list}">
				<tr>
					<td>-------------------게시글이 없습니다--------------------</td>
				</tr>
				</c:when>
		
			<c:otherwise>
				<c:forEach var="dto" items="${list }">
					<tr style="cursor:pointer;" onclick="location.href='controller.do?command=boarddetail&no=${dto.no}'">
							<td style="font-size:15px;">${dto.title }</td>
							<c:choose>
								<c:when test="${dto.loc == 1}" >
								<td style="font-size:15px;">강남구</td>
								</c:when>
								<c:when test="${dto.loc == 2}" >
								<td style="font-size:15px;">송파구</td>
								</c:when>
								<c:when test="${dto.loc == 3}" >
								<td style="font-size:15px;">서초구</td>
								</c:when>
								<c:when test="${dto.loc == 4}" >
								<td style="font-size:15px;">강동구</td>
								</c:when>
								<c:when test="${dto.loc == 5}" >
								<td style="font-size:15px;">동작구</td>
								</c:when>
								<c:when test="${dto.loc == 6}" >
								<td style="font-size:15px;">관악구</td>
								</c:when>
								<c:when test="${dto.loc == 7}" >
								<td style="font-size:15px;">영등포구</td>
								</c:when>
								<c:when test="${dto.loc == 8}" >
								<td style="font-size:15px;">강서구</td>
								</c:when>
								<c:when test="${dto.loc == 9}" >
								<td style="font-size:15px;">양천구</td>
								</c:when>
								<c:when test="${dto.loc == 10}" >
								<td style="font-size:15px;">구로구</td>
								</c:when>
								<c:when test="${dto.loc == 11}" >
								<td style="font-size:15px;">금천구</td>
								</c:when>
								<c:when test="${dto.loc == 12}" >
								<td style="font-size:15px;">종로구</td>
								</c:when>
								<c:when test="${dto.loc == 13}" >
								<td style="font-size:15px;">중구</td>
								</c:when>
								<c:when test="${dto.loc == 14}" >
								<td style="font-size:15px;">동대문구</td>
								</c:when>
								<c:when test="${dto.loc == 15}" >
								<td style="font-size:15px;">중랑구</td>
								</c:when>
								<c:when test="${dto.loc == 16}" >
								<td style="font-size:15px;">마포구</td>
								</c:when>
								<c:when test="${dto.loc == 17}" >
								<td style="font-size:15px;">용산구</td>
								</c:when>
								<c:when test="${dto.loc == 18}" >
								<td style="font-size:15px;">성동구</td>
								</c:when>
								<c:when test="${dto.loc == 19}" >
								<td style="font-size:15px;">광진구</td>
								</c:when>
								<c:when test="${dto.loc == 20}" >
								<td style="font-size:15px;">은평구</td>
								</c:when>
								<c:when test="${dto.loc == 21}" >
								<td style="font-size:15px;">서대문구</td>
								</c:when>
								<c:when test="${dto.loc == 22}" >
								<td style="font-size:15px;">성북구</td>
								</c:when>
								<c:when test="${dto.loc == 23}" >
								<td style="font-size:15px;">강북구</td>
								</c:when>
								<c:when test="${dto.loc == 24}" >
								<td style="font-size:15px;">도봉구</td>
								</c:when>
								<c:when test="${dto.loc == 25}" >
								<td style="font-size:15px;">노원구</td>
								</c:when>
							</c:choose>
							<td style="font-size:15px;">
							<fmt:formatDate value="${dto.start_date}" pattern="MM/dd"/>
								~
								<fmt:formatDate value="${dto.end_date}" pattern="MM/dd"/>
								<br>
								<fmt:formatDate value="${dto.start_time}" pattern="HH:mm"/>
								~
								<fmt:formatDate value="${dto.end_time}" pattern="HH:mm"/>
							</td>
							<td style="font-size:15px;">${dto.money }원</td>
							<td style="font-size:15px;">${fn:substring(dto.regDate,0,10) }</td>
						</tr>
				</c:forEach>
			</c:otherwise>
			</c:choose>
		</tbody>
	</table>
<br><br>
<div class = "pagin_div">
					<ul class="pagin" >
						<li class="page_li"><a class="next_page" onclick="prePageGroup();"></a></li>
						
						<c:forEach var="i" begin="${startPage }" end="${endPage }" step="1" varStatus="status">
							<c:choose>
								<c:when test="${currentPage == startPage + status.count -1 }"><li class="page_li"><a class="page_a current_page" onclick="movePage(this);">${startPage + status.count -1 }</a></li></c:when>
								<c:otherwise><li class="page_li"><a class="page_a" onclick="movePage(this);">${startPage + status.count -1 }</a></li></c:otherwise>
							</c:choose>
						</c:forEach>
						<li class="page_li"><a class="next_page" onclick="nextPageGroup();">></a></li>
					</ul>
					
					<form action="controller.do" method="post" id="pageform" name="pageform">
						<input type="hidden" name="command" value="list">
						<input type="hidden" name="pagenum" id="pagenum">
					</form>
				
	 </div>


<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>