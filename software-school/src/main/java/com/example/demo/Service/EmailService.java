package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service


public class EmailService {
	
	@Autowired
	
	public JavaMailSender javaMailSender;
	
	@Autowired
	public TemplateEngine templateEngine;
	
	public void sendplainemail(String fromEmail,String toEmail, String Subject,String Body) {
		
		SimpleMailMessage messeage = new SimpleMailMessage();
		
		messeage.setFrom(fromEmail);
		messeage.setTo(toEmail);
		messeage.setSubject(Subject);
		messeage.setText(Body);
		
		javaMailSender.send(messeage);
		 
	}
	
	public void sendhtmlemail( String fromEmail,String toEmail, String Subject,String EmailBody) throws MessagingException {
		
		MimeMessage messeage = javaMailSender.createMimeMessage();
		
		MimeMessageHelper helper= new MimeMessageHelper(messeage,true,"utf-8");
		
		helper.setFrom(fromEmail);
		helper.setTo(toEmail);
		helper.setSubject(Subject);
		helper.setText(EmailBody);
		
		javaMailSender.send(messeage);
	}
	
	 public void senttemplatemail(String fromEmail,String toEmail, String Subject,  String Filename, String imagePath) throws MessagingException {
		 
		 Context context = new Context();
		 
		 String mailbody= templateEngine.process(Filename, context );
		 
		 MimeMessage messeage = javaMailSender.createMimeMessage();
			
		 MimeMessageHelper helper= new MimeMessageHelper(messeage,true,"utf-8");
			
			helper.setFrom(fromEmail);
			helper.setTo(toEmail);
			helper.setSubject(Subject);
			helper.setText(mailbody, true);
			
			FileSystemResource file = new FileSystemResource(new String(imagePath));
			helper.addInline("myphoto", file);			
			javaMailSender.send(messeage);
		 
	 }

}
