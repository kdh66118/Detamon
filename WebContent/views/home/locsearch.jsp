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
<title>대타몬: 통합검색</title>


<link rel="stylesheet" type="text/css" href="views/css/headersearch.css">

</head>


<!--  주소 api -->		          
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<!--  jQuery -->
<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>

<!--  KAKAO MAP API -->
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=484abe6cee8cb658e2dd29b7c144de1d&libraries=services"></script>

<script type="text/javascript">
function moveCategory(category){
	
	$("#pagenum").val("1");
	$("#category").val(category.title);
	var findform = document.getElementById('searchform');
	findform.submit();
}


	
</script>
<body>
<%@ include file="../common/header.jsp" %>	
	
	<div id="boardtitle">
		
			<c:if test="${locno == 1 }">
				<span id="title">업종별 구인/구직 : 강남구</span>
			</c:if>
			<c:if test="${locno == 2 }">
				<span id="title">업종별 구인/구직 : 송파구</span>
			</c:if>
			<c:if test="${locno == 3 }">
				<span id="title">업종별 구인/구직 : 서초구</span>
			</c:if>
			<c:if test="${locno == 4 }">
				<span id="title">업종별 구인/구직 : 강동구</span>
			</c:if>
			<c:if test="${locno == 5 }">
				<span id="title">업종별 구인/구직 : 동작구</span>
			</c:if>
			<c:if test="${locno == 6 }">
				<span id="title">업종별 구인/구직 : 관악구</span>
			</c:if>
			<c:if test="${locno == 7 }">
				<span id="title">업종별 구인/구직 : 영등포구</span>
			</c:if>
			<c:if test="${locno == 8 }">
				<span id="title">업종별 구인/구직 : 강서구</span>
			</c:if>
			<c:if test="${locno == 9 }">
				<span id="title">업종별 구인/구직 : 양천구</span>
			</c:if>
			<c:if test="${locno == 10 }">
				<span id="title">업종별 구인/구직 : 구로구</span>
			</c:if>
			<c:if test="${locno == 11 }">
				<span id="title">업종별 구인/구직 : 금천구</span>
			</c:if>
			<c:if test="${locno == 12 }">
				<span id="title">업종별 구인/구직 : 종로구</span>
			</c:if>
			<c:if test="${locno == 13 }">
				<span id="title">업종별 구인/구직 : 중구</span>
			</c:if>
			<c:if test="${locno == 14 }">
				<span id="title">업종별 구인/구직 : 동대문구</span>
			</c:if>
			<c:if test="${locno == 15 }">
				<span id="title">업종별 구인/구직 : 중랑구</span>
			</c:if>
			<c:if test="${locno == 16 }">
				<span id="title">업종별 구인/구직 : 마포구</span>
			</c:if>
			<c:if test="${locno == 17 }">
				<span id="title">업종별 구인/구직 : 용산구</span>
			</c:if>
			<c:if test="${locno == 18 }">
				<span id="title">업종별 구인/구직 : 성동구</span>
			</c:if>
			<c:if test="${locno == 19 }">
				<span id="title">업종별 구인/구직 : 광진구</span>
			</c:if>
			<c:if test="${locno == 20 }">
				<span id="title">업종별 구인/구직 : 은평구</span>
			</c:if>
			<c:if test="${locno == 21 }">
				<span id="title">업종별 구인/구직 : 서대문구</span>
			</c:if>
			<c:if test="${locno == 22 }">
				<span id="title">업종별 구인/구직 : 성북구</span>
			</c:if>
			<c:if test="${locno == 23 }">
				<span id="title">업종별 구인/구직 : 강북구</span>
			</c:if>
			<c:if test="${locno == 24 }">
				<span id="title">업종별 구인/구직 : 도봉구</span>
			</c:if>
			<c:if test="${locno == 25 }">
				<span id="title">업종별 구인/구직 : 노원구</span>
			</c:if>
		
	</div>
	
	<br><br>
		<p>
	    <div class="mainTop1" style="width:100%;" >	
            <div class="choice_box" style="width:65.5%;border:1px solid #9e9e9e; height: 100px; float:left; padding-top:10px; padding-left:30px; padding-ringth:30px; margin-left:285px;">
				<div class="loc_box" style="padding:10px; text-align:center;">
				<c:forEach var="loclist" items="${loclist }">
					<c:choose>
						<c:when test="${loclist.l_name.length() == 4 }">
							<a href="controller.do?command=findboardbyloc&locno=${loclist.l_no }" class="loc_text4" style="padding-right:6px;margin-bottom:10px;font-size:16px;">${loclist.l_name }</a>						
						</c:when>
						<c:when test="${loclist.l_name.length() == 2 }">
							<a href="controller.do?command=findboardbyloc&locno=${loclist.l_no }" class="loc_text2" style="padding-right:34px; font-size:16px;">${loclist.l_name }</a>						
						</c:when>
						<c:otherwise>
							<a href="controller.do?command=findboardbyloc&locno=${loclist.l_no }" class="loc_text3" style="padding-right:20px;font-size:16px;text-decoration:none;">${loclist.l_name }</a>						
						</c:otherwise>

					</c:choose>
					<c:choose>
						<c:when test="${loclist.l_no % 13 == 0  }">
							<p class="locbr"></p>
						</c:when>
					</c:choose>
				</c:forEach>
				</div>
            </div>
	    </div>
	    <br><br>
	<form action="controller.do?command=findboardbyloc&locno=${locno}" method="post" id="searchform">
		<input type="hidden" name="command" value="locsearch'" id="command">
		<input type="hidden" name="category" id="category" value="2" id="category">
		<input type="hidden" name="pagenum" id="pagenum" value="${pagenum }" id="pagenum">
		<input type="hidden" name="search_word" value="${searchword }">
	</form>
	<br><br><br>
	
	<div id="category_div">
		<c:if test="${category==1 }">
			<div class="category_title selectedcate" onclick="moveCategory(this);" title="1">
				업체 찾기
			</div>
			<div class="category_title" onclick="moveCategory(this);" title="2">
				대타 찾기
			</div>
		</c:if>
		<c:if test="${category==2 }">
			<div class="category_title" onclick="moveCategory(this);" title="1">
				업체 찾기
			</div>
			<div class="category_title  selectedcate" onclick="moveCategory(this);" title="2">
				대타 찾기
			</div>
		</c:if>
		
	</div>
	
	<!-- 검색한 게시판 
	  -->
	 <c:choose>
	 
	
	 <c:when test="${category==1 }"> 
	<div id="boardlist" style="text-align: center">
		<table >
			<col width="600px"><col width="150px"><col width="150px"><col width="200px"><col width="150px">
			<tr>
				<th>회사명 / 공고제목</th>
				<th>지역</th>
				<th>근무 날짜 / 근무 시간</th>
				<th>급여</th>
				<th>등록일</th>
			</tr>
			
			<!-- 테이블 생성 부분 -->
			<c:choose>
				<c:when test="${empty list }">
					<tr>
						<td colspan="5">검 색 결 과 가 없 습 니 다</td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach var="searchlist" items="${list }">
					<!--  거리 검색 조건!!! -->
					
						<tr onclick="location.href='controller.do?command=boarddetail&no=${searchlist.no }'">
							<td id="title_first">
								<span>
									<p id="com_name">${searchlist.writer }</p>
									<p id="title">${searchlist.title }</p>
								</span>
								
							</td>
							<td>
								<c:choose>
									<c:when test="${searchlist.loc == 1 }">강남구</c:when>
									<c:when test="${searchlist.loc == 2 }">송파구</c:when>
									<c:when test="${searchlist.loc == 3 }">서초구</c:when>
									<c:when test="${searchlist.loc == 4 }">강동구</c:when>
									<c:when test="${searchlist.loc == 5 }">동작구</c:when>
									<c:when test="${searchlist.loc == 6 }">관악구</c:when>
									<c:when test="${searchlist.loc == 7 }">영등포구</c:when>
									<c:when test="${searchlist.loc == 8 }">강서구</c:when>
									<c:when test="${searchlist.loc == 9 }">양천구</c:when>
									<c:when test="${searchlist.loc == 10 }">구로구</c:when>
									<c:when test="${searchlist.loc == 11 }">금천구</c:when>
									<c:when test="${searchlist.loc == 12 }">종로구</c:when>
									<c:when test="${searchlist.loc == 13 }">중구</c:when>
									<c:when test="${searchlist.loc == 14 }">동대문구</c:when>
									<c:when test="${searchlist.loc == 15 }">중랑구</c:when>
									<c:when test="${searchlist.loc == 16 }">마포구</c:when>
									<c:when test="${searchlist.loc == 17 }">용산구</c:when>
									<c:when test="${searchlist.loc == 18 }">성동구</c:when>
									<c:when test="${searchlist.loc == 19 }">광진구</c:when>
									<c:when test="${searchlist.loc == 20 }">은평구</c:when>
									<c:when test="${searchlist.loc == 21 }">서대문구</c:when>
									<c:when test="${searchlist.loc == 22 }">성북구</c:when>
									<c:when test="${searchlist.loc == 23 }">강북구</c:when>
									<c:when test="${searchlist.loc == 24 }">도봉구</c:when>
									<c:when test="${searchlist.loc == 25 }">노원구</c:when>
						
								</c:choose>
							</td>
							
							<td>
								<fmt:formatDate value="${searchlist.start_date}" pattern="MM/dd"/>
								~
								<fmt:formatDate value="${searchlist.end_date}" pattern="MM/dd"/>
								<br>
								<fmt:formatDate value="${searchlist.start_time}" pattern="HH:mm"/>
								~
								<fmt:formatDate value="${searchlist.end_time}" pattern="HH:mm"/>
							</td>
							<td>시급<br>${ searchlist.money}</td>
							<td>
							<fmt:formatDate value="${searchlist.regDate }" pattern="MM/dd HH:mm"/>
							
							</td>
						</tr>
					
					
				</c:forEach>
				
				
				</c:otherwise>
			</c:choose>
			
			
		</table>
	</div>
	</c:when>
	
	
	<c:otherwise>
	<div id="boardlist" style="text-align: center">
		<table >
			<col width="600px"><col width="150px"><col width="150px"><col width="200px"><col width="150px">
			<tr>
				<th>작성자 / 공고제목</th>
				<th>지역</th>
				<th>근무 날짜 / 근무 시간</th>
				<th>급여</th>
				<th>등록일</th>
			</tr>
			
			<!-- 테이블 생성 부분 -->
			<c:choose>
				<c:when test="${empty list }">
					<tr>
						<td colspan="5">검 색 결 과 가 없 습 니 다</td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach var="searchlist" items="${list }">
					<!--  거리 검색 조건!!! -->
					
						<tr onclick="location.href='controller.do?command=boarddetail&no=${searchlist.no }'">
							<td id="title_first">
								<span>
									<p id="com_name">${searchlist.writer }</p>
									<p id="title">${searchlist.title }</p>
								</span>
								
							</td>
							<td>
								<c:choose>
									<c:when test="${searchlist.loc == 1 }">강남구</c:when>
									<c:when test="${searchlist.loc == 2 }">송파구</c:when>
									<c:when test="${searchlist.loc == 3 }">서초구</c:when>
									<c:when test="${searchlist.loc == 4 }">강동구</c:when>
									<c:when test="${searchlist.loc == 5 }">동작구</c:when>
									<c:when test="${searchlist.loc == 6 }">관악구</c:when>
									<c:when test="${searchlist.loc == 7 }">영등포구</c:when>
									<c:when test="${searchlist.loc == 8 }">강서구</c:when>
									<c:when test="${searchlist.loc == 9 }">양천구</c:when>
									<c:when test="${searchlist.loc == 10 }">구로구</c:when>
									<c:when test="${searchlist.loc == 11 }">금천구</c:when>
									<c:when test="${searchlist.loc == 12 }">종로구</c:when>
									<c:when test="${searchlist.loc == 13 }">중구</c:when>
									<c:when test="${searchlist.loc == 14 }">동대문구</c:when>
									<c:when test="${searchlist.loc == 15 }">중랑구</c:when>
									<c:when test="${searchlist.loc == 16 }">마포구</c:when>
									<c:when test="${searchlist.loc == 17 }">용산구</c:when>
									<c:when test="${searchlist.loc == 18 }">성동구</c:when>
									<c:when test="${searchlist.loc == 19 }">광진구</c:when>
									<c:when test="${searchlist.loc == 20 }">은평구</c:when>
									<c:when test="${searchlist.loc == 21 }">서대문구</c:when>
									<c:when test="${searchlist.loc == 22 }">성북구</c:when>
									<c:when test="${searchlist.loc == 23 }">강북구</c:when>
									<c:when test="${searchlist.loc == 24 }">도봉구</c:when>
									<c:when test="${searchlist.loc == 25 }">노원구</c:when>
						
								</c:choose>
							</td>
							
							<td>
								<fmt:formatDate value="${searchlist.start_date}" pattern="MM/dd"/>
								~
								<fmt:formatDate value="${searchlist.end_date}" pattern="MM/dd"/>
								<br>
								<fmt:formatDate value="${searchlist.start_time}" pattern="HH:mm"/>
								~
								<fmt:formatDate value="${searchlist.end_time}" pattern="HH:mm"/>
							</td>
							<td>시급<br>${ searchlist.money}</td>
							<td>
							<fmt:formatDate value="${searchlist.regDate }" pattern="MM/dd HH:mm"/>
							
							</td>
						</tr>
					
					
				</c:forEach>
				
				
				</c:otherwise>
			</c:choose>
			
			
		</table>
	</div>
	</c:otherwise>
	</c:choose>
	
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
			
	<!-- 퓨터 -->
	<%@ include file="../common/footer.jsp" %>
</body>
</html>