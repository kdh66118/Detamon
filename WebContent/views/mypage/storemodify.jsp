<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>대타몬: 업체 정보수정</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<%@ page import = "detamon.dto.CompanyDto" %>
<%@ page import = "detamon.biz.CompanyBiz" %>
<%@ page import = "detamon.biz.CompanyBizImpl" %>
<%@ page import = "detamon.dto.MemberDto" %>
<% MemberDto dto = (MemberDto)session.getAttribute("login"); %>
<% CompanyBiz companyBiz = new CompanyBizImpl(); %>
<%  CompanyDto com = companyBiz.selectOneById(dto.getM_id()); %>
<script type="text/javascript">
function findAddr(input){
	
	 new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
            // 예제를 참고하여 다양한 활용법을 확인해 보세요.
            var addr = data.address; // 최종 주소 변수
            
            $(input).val(addr);
            $("#postaddr").val(addr);
            
           
        }
    }).open();
	
};


	




</script>
</head>
<body>
<jsp:include page="../common/header.jsp"></jsp:include>
	<br>
	<h1 style="font-size:30px; margin-left:43%;">업체 정보 수정 </h1>
	<br>
	<form id="fo" method="post" action="controller.do?command=storemodify">
	
	<div style="border:1px solid black;margin-left:30%; height:400px; width:700px;">
	
	
	
		<h1 id="h1"style="font-size:20px; margin:20px;">업체명 : <input name="com_name" autocomplete="off" value="${com.com_name } " type="text">	<br></h1>
		<h1 id="h1"style="font-size:20px; margin:20px;">전화번호 : <input name="phone" type="text" autocomplete="off" value="${com.com_phone }">	<br></h1>
			<ul class="join__info-list">
			<li>
		<label class="chain-label" for="addr" style="font-size:20px; margin-left:20px; ">주소 :</label>
			<div class="sign__input-frame" style="width:100px;height:30px; float:right; margin-right:75%;margin-top:5px;" >
				<input type="text" id="addr" class="chain-input" name="addr" value="${com.com_addr }" maxlength="1000" autocomplete="off" readonly="readonly"
				onclick="findAddr(this);" >
							
			</div>
			</li>
		</ul>			
		<h1 id="h1" style="font-size:20px; margin:20px;">업종 :
		     <select name="category">
		 <option value="1" <%if(com.getCom_category()==1){ %>selected   <%} %>>카페</option>
		<option value="2" <%if(com.getCom_category()==2){ %>selected   <%} %>>편의점</option>
		<option value="3" <%if(com.getCom_category()==3){ %>selected   <%} %>>음식점</option>
		<option value="4" <%if(com.getCom_category()==4){ %>selected   <%} %>>일용직</option>
		<option value="5" <%if(com.getCom_category()==5){ %>selected   <%} %>>배달</option>
		<option value="6" <%if(com.getCom_category()==6){ %>selected   <%} %>>유흥시설</option>
		<option value="7" <%if(com.getCom_category()==7){ %>selected   <%} %>>기타</option>
		
		</select>        </h1>
		<h1 id="h1" style="font-size:20px; margin:20px;">지역 :    
		 <select name="loc">
		 <option value="1" <%if(com.getCom_location()==1){ %>selected   <%} %>>강남구</option>
		<option value="2" <%if(com.getCom_location()==2){ %>selected   <%} %>>송파구</option>
		<option value="3" <%if(com.getCom_location()==3){ %>selected   <%} %>>서초구</option>
		<option value="4" <%if(com.getCom_location()==4){ %>selected   <%} %>>강동구</option>
		<option value="5" <%if(com.getCom_location()==5){ %>selected   <%} %>>동작구</option>
		<option value="6" <%if(com.getCom_location()==6){ %>selected   <%} %>>관악구</option>
		<option value="7" <%if(com.getCom_location()==7){ %>selected   <%} %>>영등포구</option>
		<option value="8" <%if(com.getCom_location()==8){ %>selected   <%} %>>강서구</option>
		<option value="9" <%if(com.getCom_location()==9){ %>selected   <%} %>>양천구</option>
		<option value="10" <%if(com.getCom_location()==10){ %>selected   <%} %>>구로구</option>
		<option value="11" <%if(com.getCom_location()==11){ %>selected   <%} %>>금천구</option>
		<option value="12" <%if(com.getCom_location()==12){ %>selected   <%} %>>종로구</option>
		<option value="13" <%if(com.getCom_location()==13){ %>selected   <%} %>>중구</option>
		<option value="14" <%if(com.getCom_location()==14){ %>selected   <%} %>>동대문구</option>
		<option value="15" <%if(com.getCom_location()==15){ %>selected   <%} %>>중랑구</option>
		<option value="16" <%if(com.getCom_location()==16){ %>selected   <%} %>>마포구</option>
		<option value="17" <%if(com.getCom_location()==17){ %>selected   <%} %>>용산구</option>
		<option value="18" <%if(com.getCom_location()==18){ %>selected   <%} %>>성동구</option>
		<option value="19" <%if(com.getCom_location()==19){ %>selected   <%} %>>광진구</option>
		<option value="20" <%if(com.getCom_location()==20){ %>selected   <%} %>>은평구</option>
		<option value="21" <%if(com.getCom_location()==21){ %>selected   <%} %>>서대문구</option>
		<option value="22" <%if(com.getCom_location()==22){ %>selected   <%} %>>성북구</option>
		<option value="23" <%if(com.getCom_location()==23){ %>selected   <%} %>>강북구</option>
		<option value="24" <%if(com.getCom_location()==24){ %>selected   <%} %>>도봉구</option>
		<option value="25" <%if(com.getCom_location()==25){ %>selected   <%} %>>노원구</option>
		
		
		</select>        </h1>
			
	
	<button type="submit" class="btn btn-warning btn-sm" style="margin-left:50%; margin-top:20%;">수정</button>
	</div>
	</form>













<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>