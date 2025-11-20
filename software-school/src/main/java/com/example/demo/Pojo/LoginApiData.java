package com.example.demo.Pojo;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginApiData {
	
	
	@NotNull(message = "email is required")
	@Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$",message = "email is invalid")
	private String email;
	
	
	@NotNull(message = "password is must")
	@Size(min = 8, message = "minimum 8 cahrecters")
	private String password;
	

}
