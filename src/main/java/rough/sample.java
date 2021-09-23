package rough;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class sample {

	public static void main(String[] args) {
		boolean debug = false;
		Properties props = new Properties();
//		props.put("mail.smtp.starttls.enable", "true");
//		props.put("mail.smtp.EnableSSL.enable","true");
//		props.put("mail.smtp.starttls.enable", "true");
//		props.put("mail.smtp.auth", "true");
//		props.put("mail.smtp.ssl.enable", "true");
//		props.put("mail.smtp.ssl.trust", "*");
//
//		props.put("mail.smtp.host", TestConfig.server); 
//		props.put("mail.debug", "true");
//		
//	     props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");   
//	     props.setProperty("mail.smtp.socketFactory.fallback", "false");   
//	     props.setProperty("mail.smtp.port", "587");   
//	     props.setProperty("mail.smtp.socketFactory.port", "587"); 
		
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", TestConfig.server);
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.ssl.trust", "*");

		
		  Authenticator auth = new SMTPAuthenticator();
		    Session session = Session.getDefaultInstance(props, auth);

		    session.setDebug(debug);
		
		try
		{
			
			
			Transport bus = session.getTransport("smtp");
			bus.connect();
            Message message = new MimeMessage(session);
        
         //X-Priority values are generally numbers like 1 (for highest priority), 3 (normal) and 5 (lowest).
            
             message.addHeader("X-Priority", "1");
             message.setFrom(new InternetAddress(TestConfig.from));
             InternetAddress[] addressTo = new InternetAddress[TestConfig.to.length];
             for (int i = 0; i < TestConfig.to.length; i++)
      		 addressTo[i] = new InternetAddress(TestConfig.to[i]);
             message.setRecipients(Message.RecipientType .TO, addressTo);
             message.setSubject(TestConfig.subject);
                  
             
             BodyPart body = new MimeBodyPart();

            // body.setText(messageBody);
            body.setContent(TestConfig.messageBody,"text/html");

             //BodyPart attachment = new MimeBodyPart();
             //DataSource source = new FileDataSource(attachmentPath);
            // attachment.setDataHandler(new DataHandler(source));
             //attachment.setFileName(attachmentName);
             MimeMultipart multipart = new MimeMultipart();
             multipart.addBodyPart(body);
            // multipart.addBodyPart(attachment);
             message.setContent(multipart);
             Transport.send(message);
             System.out.println("Sucessfully Sent mail to All Users");
         	 bus.close();
    		
		}
		catch (MessagingException mex)
		{
            mex.printStackTrace();
        }		
	} 


	}

