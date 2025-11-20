package com.example.demo.Pojo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignupApiData {
	@NotNull(message = "name rwquired")
	@NotBlank(message = "name must be two charecters")
	private String name;
	
	@NotNull(message = "email is required")
	@Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")
	private String email;
	
	
	@NotNull(message = "password is required")
	@Size(min = 8,message = "8  charecetrs is requird")
	private String password;
	
	
	@NotNull(message = "passsword is must")
	private String mobile;

}
