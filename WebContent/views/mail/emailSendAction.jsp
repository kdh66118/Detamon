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
String email = request.getParameter("email"); //받는사람 이메일
int randnum = Integer.parseInt(request.getParameter("randnum")); //인증번호
	String host = "http://localhost:8787/Detamonlee/";
	String from = "bin9058@gmail.com";
	String subject = "대타몬 비밀번호 찾기 인증번호";//제목
	String content = " <span style='font-size: 30px; font-weight: bold;'>    대타몬 비밀번호 찾기 인증번호 입니다.</span><br> " +
			" <span style='font-size: 20px;'> " +
		    " 인증번호: </span><span style='font-size: 20px; color: red; font-weight: bold;'>"+randnum+"</span> ";//내용
	
    String to = email;//받는사람
  
                 //메일전송
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
                     
          Address toAddr = new InternetAddress(to);
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