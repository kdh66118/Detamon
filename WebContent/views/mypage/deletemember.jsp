<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>대타몬: 회원 탈퇴</title>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">
<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
	function btn_click(str){
		if(str=="yes"){
			opener.location.href="../../controller.do?command=deletemember";
			self.close();
		}else if(str =="no"){
			self.close();
		}
	}
</script>
</head>
<body>
	<form method="post"  >
	<div>
		정말 탈퇴하시겠습니까????????
		<button type="submit"  onclick='btn_click("yes")'>예</button>
		<button  type="submit"  onclick='btn_click("no")'>아니오</button>
	</div>
	</form>
	
	
</body>
</html> 