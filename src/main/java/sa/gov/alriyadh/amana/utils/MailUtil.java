package sa.gov.alriyadh.amana.utils;

import java.io.IOException;

import java.util.*;

import javax.mail.*;
import javax.mail.internet.*;

import org.springframework.scheduling.annotation.Async;

public class MailUtil {
    public MailUtil() {
        super();
    }
    
    
    public static boolean sendEmail(String from, String fromName, String to, String cc, String bcc, String subject, String body, int authType, String username, String password){
        String[] toArray = new String[0];
        String[] ccArray = new String[0];
        String[] bccArray = new String[0];
        if(to != null){
            toArray = to.split(";");
        }
        if(cc != null){
            ccArray = cc.split(";");
        }
        if(bcc != null){
            bccArray = bcc.split(";");
        }
            
        // RequestPojo's email ID needs to be mentioned.
        //String to = "mshehata@alriyadh.gov.sa";

        // Assuming you are sending email through relay.jangosmtp.net
        String host = "mail.alriyadh.gov.sa";

        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "25");
        //props.put("mail.mime.charset", "UTF-8");
        props.put("Content-Type", "text/html");

        // Get the Session object.
        Session session = null;
        if(authType == 1){
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            session = Session.getInstance(props,
               new javax.mail.Authenticator() {
                  protected PasswordAuthentication getPasswordAuthentication() {
                     return new PasswordAuthentication(username, password);
                 }
               }); 
        }else{
            session = Session.getDefaultInstance(props);
        }

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            if(from.split(",").length > 1) {
            	message.setFrom(new InternetAddress(from.split(",")[0],from.split(",")[1]));
            }else {
            	message.setFrom(new InternetAddress(from, fromName));
            }
            

            // Set Email: RecipientType.To
            for(int i = 0; i<toArray.length;i++){
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(toArray[i]));
            }
            
            // Set Email: RecipientType.CC
            for(int i = 0; i<ccArray.length;i++){
            	System.out.println("##### ccArray Mail Valueeee:  "+ ccArray[i]);
                message.addRecipient(Message.RecipientType.CC, new InternetAddress(ccArray[i]));
            }
            
            // Set Email: RecipientType.BCC
            for(int i = 0; i<bccArray.length;i++){
                message.addRecipient(Message.RecipientType.BCC, new InternetAddress(bccArray[i]));
            }

            // Set Subject: header field
            message.setHeader("Content-Type", "text/html; charset=UTF-8");
            message.setSubject(subject, "UTF-8"); //message.setSubject("New user has been created");

            // Now set the actual message
            BodyPart messageBodyPart = new MimeBodyPart(); 
            messageBodyPart.setHeader("Content-Type","text/plain; charset=UTF-8"); 
            messageBodyPart.setContent(body.toString(), "text/html;charset=UTF-8");
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart( messageBodyPart );
            message.setContent( multipart);
           // message.setContent(body, "text/html;");

            // Send message
            Transport.send(message);
            return true;

        } catch (Exception e) {
        	e.printStackTrace();
        	System.out.println("E-Mail Send faile : " + e.getMessage());
            return false;
        }
    }
    
    public static boolean isValidEmailAddress(String email) {
    	if(email != null && !email.isEmpty()) {
    		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
            java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
            java.util.regex.Matcher m = p.matcher(email);
            return m.matches();
    	}else {
    		return false;
    	}
        
    }
    
    public static boolean isEnternalEmailAddress(String email) {
    	if(email.contains("alriyadh.gov.sa")) {
    		return true;
    	}else {
    		return false;
    	}
    }
}
