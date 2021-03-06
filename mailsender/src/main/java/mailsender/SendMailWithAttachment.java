package mailsender;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendMailWithAttachment {
	
	public void send() throws IOException {
		  String to = "rahul.karnati2011@gmail.com"; // to address. It can be any like gmail, hotmail etc.
		  final String from = "karnatirahul2392@gmail.com"; // from address. As this is using Gmail SMTP.
		  final String password = "!Feb1992"; // password for from mail address. 
		 
		  Properties prop = new Properties();
		  prop.put("mail.smtp.host", "smtp.gmail.com");
		  prop.put("mail.smtp.port", "465");
		  prop.put("mail.smtp.auth", "true");
		  prop.put("mail.smtp.socketFactory.port", "465");
		  prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		 
		  Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
		   protected PasswordAuthentication getPasswordAuthentication() {
		    return new PasswordAuthentication(from, password);
		   }
		  });
		 
		  try {
		 
		   Message message = new MimeMessage(session);
		   message.setFrom(new InternetAddress(from));
		   message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
		   message.setSubject("Message from Java Simplifying Tech");
		    
		   String msg = "This email sent using JavaMailer API from Java Code!!!";
		   StringBuilder email = new StringBuilder();

	        email.append("<html><head><style type='text/css'>table .out {border-width:1px, "
	                    + "border-color: black}</style></head>"
	                    + "<body>"
	                    + "<table class'out'><span style=border-color: black, border-width: 1px>");
	        
	            email.append("<tr>");
	            email.append("<td>");

	            email.append("abc");
	            email.append("</td>");

	            email.append("<td>");
	            email.append("qwe");
	            email.append("</td>");

	            email.append("<td>");
	            email.append("rgt");
	            email.append("</td>");

	            email.append("<td>");
	            email.append("zxc");
	            email.append("</td>");

	            email.append("<tr>");
	        

	        email.append("</table></body></html>");
		    
		   MimeBodyPart mimeBodyPart = new MimeBodyPart();
		   mimeBodyPart.setContent(email.toString(), "text/html");
		     
		   Multipart multipart = new MimeMultipart();
		   multipart.addBodyPart(mimeBodyPart);
		    
		   MimeBodyPart attachmentBodyPart = new MimeBodyPart();
		   attachmentBodyPart.attachFile(new File("E://Aadarsh_1.6_Exp_Hyd_Resume.docx"));
		   multipart.addBodyPart(attachmentBodyPart);
		   message.setContent(multipart);
		 
		   Transport.send(message);
		 
		   System.out.println("Mail successfully sent..");
		 
		  } catch (MessagingException e) {
		   e.printStackTrace();
		  }
		 }

}
