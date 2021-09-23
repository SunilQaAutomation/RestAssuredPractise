package rough;

import javax.mail.PasswordAuthentication;

import com.accenture.APITestingFrameWork.utility.TestConfig;

	
	public class SMTPAuthenticator extends javax.mail.Authenticator
	{

	    public PasswordAuthentication getPasswordAuthentication()
	    {
	        String username = TestConfig.from;
	        String password = TestConfig.password;
	        return new PasswordAuthentication(username, password);
	    }
	}
	


