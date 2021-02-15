<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="javax.mail.Transport" %>
<%@ page import="javax.mail.Message" %>
<%@ page import="javax.mail.Address" %>
<%@ page import="javax.mail.internet.InternetAddress" %>
<%@ page import="javax.mail.internet.MimeMessage" %>
<%@ page import="javax.mail.Session" %>
<%@ page import="javax.mail.Authenticator" %>
<%@ page import="mail.Gmail" %>
<%@ page import="java.util.Properties" %>       
<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>

<%
String com_email = request.getParameter("com_email"); //업체 이메일
String albaName = request.getParameter("albaName");	// 알바이름
String albaPhone = request.getParameter("albaPhone");	// 알바전화
String startdate = request.getParameter("startdate");	// 시작날짜
String enddate = request.getParameter("enddate");		// 종료날짜
String startTime = request.getParameter("startTime");	// 시작시간
String endTime = request.getParameter("endTime");		// 종료시간

startTime = startTime.substring(11, 16);
endTime = endTime.substring(11, 16);


System.out.println("알바이름"+albaName);
System.out.println("알바전화"+albaPhone);
System.out.println("시작날짜"+startdate);
System.out.println("종료날짜"+enddate);
System.out.println("업체이메일"+com_email);
System.out.println("시작시간"+startTime);
System.out.println("종료날짜"+endTime);

	String host = "http://localhost:8787/Detamonlee/";
	String from = "bin9058@gmail.com";
	String subject = "대타몬 계약 완료";//제목
	String content = "<span style='font-size: 30px; font-weight: bold;'>대타몬 계약이 성사되었습니다</span><br>"+
			" <span style='font-size: 20px;'>신청하신 계약이 체결되었습니다. 체결된 계약페이지를 확인해주세요!!</span><br>"+
			" <span>이름 : "+albaName+"</span><br>" + "<span>전화번호 : "+albaPhone+" </span><br>" +
			" <span>기간 : "+startdate+"일부터 ~ "+enddate+"일까지</span><br>"+
			" <span>시간 : "+startTime+"  ~  "+endTime+"</span><br>";
                 
			//알바 메일전송
 Properties p = new Properties(); 
 p.put("mail.smtp.host","gmail-smtp.l.google.com");         
 p.put("mail.smtp.port", "465");
 p.put("mail.smtp.starttls.enable", "true");
 p.put("mail.smtp.auth", "true");
 p.put("mail.smtp.debug", "true");
 p.put("mail.smtp.socketFactory.port", "465");
 p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
 p.put("mail.smtp.socketFactory.fallback", "false");
      try{
    	  Authenticator auth = new Gmail();
         Session ses = Session.getInstance(p, auth);
          ses.setDebug(true);
                     
           MimeMessage msg = new MimeMessage(ses); // 메일의 내용을 담을 객체
            msg.setSubject(subject); // 제목
                     
            Address fromAddr = new InternetAddress(from);
            msg.setFrom(fromAddr); // 보내는 사람
                     
          Address toAddr = new InternetAddress();
            toAddr = new InternetAddress(com_email);

          msg.addRecipient(Message.RecipientType.TO, toAddr); // 받는 사람
                     
            msg.setContent(content, "text/html;charset=UTF-8"); // 내용과 인코딩
                     
          Transport.send(msg); // 전송
     } catch(Exception e){
        e.printStackTrace();
      System.out.println("error");
    }        
   
%>
	<script type="text/javascript">
	$(function(){
		self.close();	
	});
	
	
	</script>    
<%      
    
%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이메일 전송</title>
</head>
<body>
	
</body>
</html>