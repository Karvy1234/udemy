package mailsender;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class XmlMail {
	
	private Properties mailAccessCredentials;
    private Session mailSession;
    private MimeMessage mailMessage;        

    public XmlMail() throws MessagingException{
        populateProperties();
    }    

    private void populateProperties() throws AddressException, MessagingException {     
        //Step1    
        System.out.println("\n 1st ===> setup Mail Server Properties..");
        mailAccessCredentials = System.getProperties();
        mailAccessCredentials.put("mail.smtp.port", "587"); // TLS Port
        mailAccessCredentials.put("mail.smtp.auth", "true"); // Enable Authentication
        mailAccessCredentials.put("mail.smtp.starttls.enable", "true"); // Enable StartTLS
        System.out.println("Mail Server Properties have been setup successfully..");     
    }    

    public void populateMailMessage(String msg) throws MessagingException{               
        //Step2    
        System.out.println("\n\n 2nd ===> get Mail Session..");
        mailSession = Session.getDefaultInstance(mailAccessCredentials, null);
        mailMessage = new MimeMessage(mailSession);
        mailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress("n****@gmail.com"));

        mailMessage.setSubject("Report from Database Payments now Due");

        mailMessage.setContent(msg, "text/html");
        System.out.println("Mail Session has been created successfully..");
    }

    public void sendMail() throws MessagingException{        
        //Step3    
        System.out.println("\n\n 3rd ===> Get Session and Send mail");
        Transport transport = mailSession.getTransport("smtp");

        // Enter your correct gmail UserID and Password
        transport.connect("smtp.gmail.com", "lettingssmart@******", "****");
        transport.sendMessage(mailMessage, mailMessage.getAllRecipients());
        transport.close();
    }

    public static void main(String[] args) throws MessagingException {
    	XmlMail sm = new XmlMail();
        //sm.populateMailMessage();
        sm.sendMail();
    }

}
