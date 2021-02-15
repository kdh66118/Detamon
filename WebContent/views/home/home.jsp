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
<title>대타몬</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="views/css/home.css">
</head>
<body>
	
	<%@ include file="../common/header.jsp" %>
		
	
	
	<div id="mainTop">
	    <div class="mainTop1" >	
            <div class="choice_box" >
				<p class="choice_title">지역별 구인/구직</p>
				<div class="loc_box">
				<c:forEach var="loclist" items="${loclist }">
					<c:choose>
						<c:when test="${loclist.l_name.length() == 4 }">
							<a href="controller.do?command=findboardbyloc&locno=${loclist.l_no }" class="loc_text4">${loclist.l_name }</a>						
						</c:when>
						<c:when test="${loclist.l_name.length() == 2 }">
							<a href="controller.do?command=findboardbyloc&locno=${loclist.l_no }" class="loc_text2">${loclist.l_name }</a>						
						</c:when>
						<c:otherwise>
							<a href="controller.do?command=findboardbyloc&locno=${loclist.l_no }" class="loc_text3">${loclist.l_name }</a>						
						</c:otherwise>

					</c:choose>
					<c:choose>
						<c:when test="${loclist.l_no % 5 == 0  }">
							<p class="locbr"></p>
						</c:when>
					</c:choose>
				</c:forEach>
				</div>
            </div>
            <div class="choice_box" style="border-left: none;">
				<p class="choice_title">업종별 구인/구직 </p>
				<div class="cate_box">
					<c:forEach var="catelist" items="${categorylist }">
						<a href="controller.do?command=findboardbycategory&categoryno=${catelist.c_no }" class="cate_text">${catelist.c_name }</a>
					
						<c:choose>
								<c:when test="${catelist.c_no % 4 == 0  }">
									<p class="catebr"></p>
								</c:when>
						</c:choose>
					</c:forEach>
				</div>
            </div>

	    </div>
	    
	    <div class="mainTop2">
	    	<c:choose>
	    		
	    		<c:when test="${login == null }">
	        <div id="Login" class="login-main">
	            <p class="login-main__guide">로그인 후 다양한 서비스를 이용해보세요!</p>
	            <div class="login-main__action"><a href="controller.do?command=loginform">로그인</a></div>
	            <ul class="login-main__member">
	                <li><a href="controller.do?command=joinform">회원가입</a></li>
	                <li><a class="idSearch" href="controller.do?command=findidform">아이디 찾기</a></li>
	                <li><a class="pwSearch" href="controller.do?command=findpwform">비밀번호 찾기</a></li>
	            </ul>
	        </div>	
	        	</c:when>
	        	
	        	
	        	<c:otherwise>
	        		<div id=login_id>
	        			<img src="images/member.png" width="80px" height="70px">
	        			<span>${login.m_id }님</span>
	        			 <button onclick="location.href='controller.do?command=logout'">로그아웃</button> <br>
	        		</div>
	        		
	        		<div id=logindiv>
	        			<a href="controller.do?command=mypage">
	        				<div id=myinfo>
	        					<img src="images/my.png" width="80px" height="70px">
	        					<p>내 정보 보기</p>
	    	    			</div>
	    	    		</a>
	    	    		<a href="controller.do?command=myboard">
		        			<div id=mywrite>
			        			<img src="images/write.png" width="100px" height="70px">
			        			<p>내가 쓴 글</p>
	        				</div>
	        			</a>
	        		</div>
	        		
	        	</c:otherwise>
	        </c:choose>
	    </div>
	    
	    <div class="mainTop3">
	        <div class="mini_title">
	        	대타찾기 조회수 TOP5
	        </div>
	       <div id="boardlist" style="text-align: center">
				<table >
					<col width="600px"><col width="150px"><col width="150px"><col width="200px"><col width="150px"><col width="150px">
					<tr>
					<th>알바생 아이디 / 공고제목</th>
					<th>지역</th>
					<th>근무날짜 / 근무시간</th>
					<th>급여</th>
					<th>작성시간</th>
					<th>조회수</th>
				</tr>
			
			<!-- 테이블 생성 부분 -->
				<c:choose>
					<c:when test="${parttimerlist.size() == 0 }">
						<tr>
							<td colspan="6">게 시 물 이 없 습 니 다</td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach var="parttimer" items="${parttimerlist }">
						
							<tr onclick="location.href='controller.do?command=boarddetail&no=${parttimer.no }'">
								<td id="title_first">
									<span>
										<p id="com_name">${parttimer.writer }</p>
										<p id="title">${parttimer.title }</p>
									</span>
								
								</td>
								<c:if test="${parttimer.loc == 1 }">
									<td>강남구</td>
								</c:if>
								<c:if test="${parttimer.loc == 2 }">
									<td>송파구</td>
								</c:if>
								<c:if test="${parttimer.loc == 3 }">
									<td>서초구</td>
								</c:if>
								<c:if test="${parttimer.loc == 4 }">
									<td>강동구</td>
								</c:if>
								<c:if test="${parttimer.loc == 5 }">
									<td>동작구</td>
								</c:if>
								<c:if test="${parttimer.loc == 6 }">
									<td>관악구</td>
								</c:if>
								<c:if test="${parttimer.loc == 7 }">
									<td>영등포구</td>
								</c:if>
								<c:if test="${parttimer.loc == 8 }">
									<td>강서구</td>
								</c:if>
								<c:if test="${parttimer.loc == 9 }">
									<td>양천구</td>
								</c:if>
								<c:if test="${parttimer.loc == 10 }">
									<td>구로구</td>
								</c:if>
								<c:if test="${parttimer.loc == 11 }">
									<td>금천구</td>
								</c:if>
								<c:if test="${parttimer.loc == 12 }">
									<td>종로구</td>
								</c:if>
								<c:if test="${parttimer.loc == 13 }">
									<td>중구</td>
								</c:if>
								<c:if test="${parttimer.loc == 14 }">
									<td>동대문구</td>
								</c:if>
								<c:if test="${parttimer.loc == 15 }">
									<td>중랑구</td>
								</c:if>
								<c:if test="${parttimer.loc == 16 }">
									<td>마포구</td>
								</c:if>
								<c:if test="${parttimer.loc == 17 }">
									<td>용산구</td>
								</c:if>
								<c:if test="${parttimer.loc == 18 }">
									<td>성동구</td>
								</c:if>
								<c:if test="${parttimer.loc == 19 }">
									<td>광진구</td>
								</c:if>
								<c:if test="${parttimer.loc == 20 }">
									<td>은평구</td>
								</c:if>
								<c:if test="${parttimer.loc == 21 }">
									<td>서대문구</td>
								</c:if>
								<c:if test="${parttimer.loc == 22 }">
									<td>성북구</td>
								</c:if>
								<c:if test="${parttimer.loc == 23 }">
									<td>강북구</td>
								</c:if>
								<c:if test="${parttimer.loc == 24 }">
									<td>도봉구</td>
								</c:if>
								<c:if test="${parttimer.loc == 25 }">
									<td>노원구</td>
								</c:if>
								<td>
									<fmt:formatDate value="${parttimer.start_date}" pattern="MM/dd"/>
									~
									<fmt:formatDate value="${parttimer.end_date}" pattern="MM/dd"/>
									<br>
									<fmt:formatDate value="${parttimer.start_time}" pattern="HH:mm"/>
									~
									<fmt:formatDate value="${parttimer.end_time}" pattern="HH:mm"/>
								</td>
								<td>시급<br>${ parttimer.money}</td>
								<td>
								<fmt:formatDate value="${parttimer.regDate }" pattern="MM/dd HH:mm"/>
								
								</td>
								<td>${parttimer.cnt }</td>
							</tr>
						
					</c:forEach>
					</c:otherwise>
				</c:choose>
		
			
			</table>
			</div>
	    </div>
    	
   	   
		<div class="mainTop3" >
	        <div class="mini_title">
	        	업체찾기 조회수 TOP5
	        </div>
	       <div id="boardlist" style="text-align: center">
				<table >
					<col width="600px"><col width="150px"><col width="150px"><col width="200px"><col width="150px"><col width="150px">
					<tr>
					<th>회사명 / 공고제목</th>
					<th>주소</th>
					<th>근무날짜 / 근무시간</th>
					<th>급여</th>
					<th>작성시간</th>
					<th>조회수</th>
				</tr>
			
			<!-- 테이블 생성 부분 -->
				<c:choose>
					<c:when test="${companylist.size() ==0 }">
						<tr>
							<td colspan="6">게 시 물 이 없 습 니 다</td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach var="companylist" items="${companylist }">
						
							<tr onclick="location.href='controller.do?command=boarddetail&no=${companylist.no }'">
								<td id="title_first">
									<span>
										<p id="com_name">${companylist.com_name }</p>
										<p id="title">${companylist.title }</p>
									</span>
								
								</td>
								<td>${companylist.addr }</td>
								<td>
									<fmt:formatDate value="${companylist.start_date}" pattern="MM/dd"/>
									~
									<fmt:formatDate value="${companylist.end_date}" pattern="MM/dd"/>
									<br>
									<fmt:formatDate value="${companylist.start_time}" pattern="HH:mm"/>
									~
									<fmt:formatDate value="${companylist.end_time}" pattern="HH:mm"/>
								</td>
								<td>시급<br>${ companylist.money}</td>
								<td>
								<fmt:formatDate value="${companylist.regDate }" pattern="MM/dd HH:mm"/>
								
								</td>
								<td>${companylist.cnt }</td>
							</tr>
						
					</c:forEach>
					</c:otherwise>
				</c:choose>
		
			
			</table>
			</div>
	    </div>
	
	
	</div>
<!-- 퓨터 -->
	<%@ include file="../common/footer.jsp" %>

	
		
	
</body>

</html>