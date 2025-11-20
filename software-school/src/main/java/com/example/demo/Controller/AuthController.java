package com.example.demo.Controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entiry.Userss;
import com.example.demo.Pojo.ForgotApiData;
import com.example.demo.Pojo.LoginApiData;
import com.example.demo.Pojo.ResetApiData;
import com.example.demo.Pojo.SignupApiData;
import com.example.demo.Service.Authservice;
import com.example.demo.Service.EmailService;
//import com.sun.accessibility.internal.resources.accessibility;

import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
@RestController
public class AuthController {
	
	@Autowired
	public Authservice authservice;
	
	@Autowired
	
	public EmailService emailService;
	
	
	@PostMapping("/sign-up")
	public ResponseEntity signup(@Valid @RequestBody SignupApiData signupApiData) throws Exception {
		
		Object userdbdataOptional=authservice.handlesignup(signupApiData);
		
		
		Map<String, Object> responaceMap=new HashMap<String, Object>();
		
		responaceMap.put("String", "sucess");
		responaceMap.put("data", userdbdataOptional);
		
		
		return ResponseEntity.status(HttpStatus.OK).body(responaceMap);
		
	}
	
	@PostMapping("/login")
	
	public Object login(@Valid @RequestBody LoginApiData loginApiData) throws Exception {
		
		Userss userdata = authservice.handlelogin(loginApiData);
		
		Map<String, Object> responceMap= new HashMap<String, Object>();
		
		responceMap.put("result","sucess");
		responceMap.put("data", userdata);
		
		
		return ResponseEntity.status(HttpStatus.OK).body(responceMap);
		
	}
	
	@PostMapping("/forgot-email")
	public ResponseEntity<?> Forgotpassword(@Valid @RequestBody ForgotApiData forgotApiData) throws Exception {
		
		
		
		authservice.handleforgotapi(forgotApiData);
		
		
		Map<String, String> messeage=  new HashMap<String, String>();
		messeage.put("result","sucess");
		messeage.put("messeage"," will sent to reset password link to your email");
		
		return ResponseEntity.status(HttpStatus.OK).body(messeage);
		
	}
	
	@PostMapping("/reset-password")
	public ResponseEntity<?> reset(@Valid @RequestBody ResetApiData resetApiData) throws Exception {
        
	    authservice.handlereset(resetApiData);

	    Map<String, String> message = new HashMap<>();
	    message.put("result", "success");
	    message.put("message", "Password reset successfully");

	    return ResponseEntity.status(HttpStatus.OK).body(message);
	}

	
	
	
	
	
	@GetMapping("/send-mail")
	public ResponseEntity<?> sendmail() throws MessagingException {
		
		String fromEmail="prashu79953@gmail.com";
		String toEmail="roy731025@gmail.com";
		String Subject=" hai this prashu";
		String EmailBody="Hai prashu, thsi email from spring boot ";
		String imagepath = "C:\\Users\\roy73\\OneDrive\\Pictures\\prop.jpg";

		
		emailService.sendplainemail(fromEmail, toEmail, Subject, EmailBody);
		
		
		EmailBody= "Hai Prashu," +
				   "This email is from Spring Boot." +
                   "<a href='https://www.google.com'>Click here</a>" +
	               "<b>Regards,<br>Prashu</b>";
		
		emailService.sendhtmlemail(fromEmail, toEmail, Subject, EmailBody);
		
		emailService.senttemplatemail(fromEmail, toEmail, Subject, "mail",imagepath);
		
		Map<String, String> responceMap= new HashMap<String, String>();
		
		responceMap.put("result", "sucess");
		responceMap.put("messeage","email sent");
		
		return ResponseEntity.status(HttpStatus.OK).body(responceMap);
	}
	
	
	
	

}
