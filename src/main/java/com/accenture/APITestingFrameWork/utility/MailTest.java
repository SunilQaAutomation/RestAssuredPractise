package com.accenture.APITestingFrameWork.utility;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public class MailTest {


	public static void main(String[] args) throws UnknownHostException {
		
		 MonitoringMail mail = new MonitoringMail();
		 
		  
			String	messageBody = "http://"+InetAddress.getLocalHost().getHostAddress()+
":8080/job/RestAssuredTest/ExtentReports";
				
			
		
		 
		 try {
			mail.sendMail(TestConfig.server, TestConfig.from, TestConfig.to, TestConfig.subject, messageBody);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
