package com.elead.ppm.project.provider.util;

import java.util.Properties;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
 
public class MailUtil {
 
    static int port = 25;
    static String mailHost = "mail.e-lead.cn";
    static String user = "hr@e-lead.cn";
    static String password = "Elead2016";
 
    public static void sendEmail(String subject, String body, String [] tos) {
    	JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		SimpleMailMessage mm = new SimpleMailMessage();

	    mailSender.setHost(mailHost);
	    mailSender.setPort(port);
	    mailSender.setUsername(user);
	    mailSender.setPassword(password);
	    mm.setFrom(user);
	    mm.setTo(tos);
	    mm.setSubject(subject);
	    mm.setText(body);
	    Properties prop = new Properties();  
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.timeout", "25000");  
        mailSender.setJavaMailProperties(prop);  
        mailSender.send(mm);
    }
}