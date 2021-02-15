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
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="views/css/header.css">
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script type="text/javascript">
    	$(window).on('scroll', function(){
    		if($(document).scrollTop() > 200){
    			$('.nav_div').addClass('fixed');
    		}else{
    			$('.nav_div').removeClass('flxed');
    		}
    		if($(document).scrollTop() < 50){
    			$('.nav_div').attr("class","nav_div");
    			
    		}
    		
    	})
    </script>
</head>
<body>
    <div id="hd">
        <h1 id="hd_h1"></h1>
        <div id="tnb">
            <div class="inner">
                <ul id="hd_qnb">
                <c:choose>
                	<c:when test="${login == null }">
                   		 <li><a href="controller.do?command=loginform">로그인</a></li>
                  		 <li><a href="controller.do?command=joinform">회원가입</a></li>                   		 
                    </c:when>
                    <c:otherwise>
	              		 <li>${login.m_name }님 환영합니다!</li>
                    	<li><a href="controller.do?command=logout">로그아웃</a></li>
                    </c:otherwise>
                </c:choose>
                </ul>
            </div>
        </div>
        <div id="hd_wrapper">
    
            <div id="logo">
            <!-- 로고 홈으로 -->
                <a href="controller.do?command=home"><img src="images/logo.jpg" alt="로고" width="140px" height="70px;"></a>
            </div>
        
            <div class="hd_sch_wr">
                <fieldset id="hd_sch">
                    <form name="fsearchbox" method="post" action="controller.do?command=headersearch" >
                    	<input type="hidden" name="command" value="headersearch">
	                    <input type="text" name="search_word" id="sch_stx" maxlength="20" placeholder="검색어를 입력해주세요">
	                    <button type="submit" id="sch_submit" value="검색"><i class="fa fa-search" aria-hidden="true"></i><span class="sound_only">검색</span></button>
                    </form>
    
                </fieldset>
            </div>
            
        </div>
        <div class="nav_div">
            <ul class="nav justify-content-center">
                <li class="nav-item navbar">
                  <a class="nav-link" href="controller.do?command=alba">대타 찾기</a>
                </li>
                <li class="nav-item navbar">
                  <a class="nav-link" href="controller.do?command=list">업체 찾기</a>
                </li>
                <li class="nav-item navbar">
                  <a class="nav-link" href="controller.do?command=contract">계약확인</a>
                </li>
                <li class="nav-item navbar">
                    <a class="nav-link" href="controller.do?command=findnearcomform">근처업체찾기</a>
                  </li>
                <li class="nav-item navbar">
                  <a class="nav-link" href="controller.do?command=mypage">마이페이지</a>
                </li>
              </ul>
        </div> 
    </div>
<!-- } 상단 끝 -->

<hr>
</body>
</html>