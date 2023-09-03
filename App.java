package com.Aems;

import java.io.File;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class App 
{
    public static void main( String[] args )
    {
    	System.out.println("preparing to send message");
        System.out.println( "Hello World!" );
        String message = "hello, dear this is message for security check.";
        String subject = "sample male";
        String from = "shirishashirishav@gmail.com";
        String to = "shirishashirishav@gmail.com";
        
//        sendEmail(message,subject,to,from);
        sendAttach(message,subject,to,from);
    }
   // this is responsible to send the message with attachment 
private static void sendAttach(String message, String subject, String to, String from) {
	String host = "smtp.gmail.com";
	//get the system properties
	Properties properties = System.getProperties();
	System.out.println("PROPERTIES" +properties);
	//setting important  information
	//host set
	properties.put("mail.smtp.host", host);
	properties.put("mail.smtp.port","465");
    properties.put("mail.smtp.ssl.enable", "true");
    properties.put("mail.smtp.auth", "true");
    
//step 1 : to get session object..
  Session session=  Session.getInstance(properties, new Authenticator()
    		{

				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					// TODO Auto-generated method stub
					return new PasswordAuthentication("shirishashirishav@gmail.com", "llycehvgbvhrhaid");
				}
    	
    		});
  session.setDebug(true);
  //step 2 :compose the message
  MimeMessage m = new MimeMessage(session);
  //from email 
  try
  {
  m.setFrom(from);
  //adding recipient to message
  m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
  //adding subject to message
  m.setSubject(subject);
  //setting text
  //attachment ..
  //file path
  String path = "C:\\Users\\Shirisha\\OneDrive\\Desktop\\Body.txt";
  MimeMultipart mimeMultipart = new MimeMultipart();
  //text
  //file
  MimeBodyPart textMime = new MimeBodyPart();
  MimeBodyPart fileMine = new MimeBodyPart();
  try
  {
	  textMime.setText(message);
	  File file = new File(path);
	  fileMine.attachFile(file);
	  mimeMultipart.addBodyPart(textMime);
	  mimeMultipart.addBodyPart(fileMine);
	  
  }catch (Exception e)
  {
	  e.printStackTrace();
  }
  
  m.setContent(mimeMultipart);
  Transport.send(m);
  System.out.println("send message sucessfully");	
	}
  catch (Exception e)
  {
	  e.printStackTrace();
  }
}
}
