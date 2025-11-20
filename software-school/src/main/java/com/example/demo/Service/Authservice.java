package com.example.demo.Service;

import java.security.PublicKey;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.aspectj.weaver.patterns.HasMemberTypePatternForPerThisMatching;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.Entiry.Userss;
//import com.example.demo.Pojo.ForgorApiData;
import com.example.demo.Pojo.ForgotApiData;
import com.example.demo.Pojo.LoginApiData;
import com.example.demo.Pojo.ResetApiData;
import com.example.demo.Pojo.SignupApiData;
import com.example.demo.Repository.UserInterface;

import jakarta.validation.Valid;
@Service
public class Authservice {
	
	@Autowired
	public UserInterface userInterface;
	
	@Autowired
	public EmailService emailService;
	
	
	public PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
	
	public Object handlesignup(SignupApiData signupApiData) throws Exception {
		
		Optional<Userss> dbdata = userInterface.findByEmail(signupApiData.getEmail());
		
		if(dbdata.isEmpty()) {
			
			Userss userobj= new Userss();
			
			userobj.setName(signupApiData.getName());
			userobj.setEmail(signupApiData.getEmail());
			userobj.setPassword(passwordEncoder.encode(signupApiData.getPassword()));
			userobj.setMobile(signupApiData.getMobile());
			
			
			Userss dbuserdata = userInterface.save(userobj);
			
			return dbuserdata;
			
		}else {
			throw new Exception("user alreday exist pleace login ");
			
		}
		
	}
	
	
	public Userss handlelogin(LoginApiData loginApiData) throws Exception {
		
		Optional<Userss> dbdatalogin= userInterface.findByEmail(loginApiData.getEmail());
		
		if(dbdatalogin.isEmpty()) {
			throw new Exception("user doesnot exist pleace signup");
		}else {
			Userss userdata=dbdatalogin.get();
			Boolean isMatching = passwordEncoder.matches(loginApiData.getPassword(),userdata.getPassword());
			if(isMatching == true) {
				
				return userdata;
				
			}else {
				
				throw new Exception("password doenot matcing");
			}
		
		}
			
			
		}
	
	
		public void handleforgotapi( ForgotApiData forgotApiData) throws Exception {
			
			
			Optional<Userss> dbdata =userInterface.findByEmail(forgotApiData.getEmail());
			
		if(dbdata.isEmpty()) {
			throw new Exception("email is not regisred with us pleace check and try again ");
		}else {
			//System.out.println(dbdata.get());
			//System.out.println(UUID.randomUUID().toString());
			String passwordResetKey=UUID.randomUUID().toString();
			
			Userss userdbdata= dbdata.get();
			userdbdata.setPasswordResetKey(passwordResetKey);
			userInterface.save(userdbdata);
			
			
			String mailBody= "Hi " + userdbdata.getName() + ",<br/><br/>" +
							"Please find the below link to reset your password.<br/><br/>" +
							"Click here: <a href='http://localhost:8080/password-reset-ui?linkids=" + passwordResetKey + "'>Reset Password</a><br/><br/>" +
							"Regards<br/>" +
							"<b>Prashu-Springboot App</b>";

			
			emailService.sendhtmlemail("prashu79953@gmail.com", userdbdata.getEmail(),"password resret link", mailBody);
			
			
		}
		
		
			
		}
		
		/*
		 * public void handlereset(ResetApiData resetApiData) throws Exception {
		 * 
		 * if(resetApiData.getPassword().equals(resetApiData.getConfirmpassword())==
		 * false) {
		 * 
		 * throw new Exception("password and confirm pasword must be match");
		 * 
		 * 
		 * 
		 * }
		 * 
		 * }
		 */


		public void handlereset(@Valid ResetApiData resetApiData) throws Exception {
			if(resetApiData.getPassword().equals(resetApiData.getConfirmpassword())==false) {
				throw new Exception("password and confirm pasword must be match");
				
				
			}
			
			 Optional<Userss> dbdata =userInterface.findByPasswordResetKey(resetApiData.getLinki());
			 
			 if(dbdata.isEmpty()) {
				 throw new Exception("invalis password or key experied");
			 }
			 
			 Userss userdata= dbdata.get();
			 
			 userdata.setPassword(passwordEncoder.encode(resetApiData.getPassword()));
			 userdata.setPasswordResetKey(" ");
			 
			 userInterface.save(userdata);
			
		}
	
	
		
		
		
	}
	
