package mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class Gmail extends Authenticator{
	
		
	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication("bin9058", "1346257v");
	}
	
}
