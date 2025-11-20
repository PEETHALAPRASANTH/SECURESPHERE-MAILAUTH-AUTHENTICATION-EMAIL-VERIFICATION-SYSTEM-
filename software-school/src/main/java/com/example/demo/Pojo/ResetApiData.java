package com.example.demo.Pojo;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ResetApiData {
	
	@NotNull(message = "password reset key is required")
	private String linki;
	
	
	@NotNull(message = "messeage is required")
	@Size(min = 8,message = "password is minimu 8 charecters")
	private String password;
	
	
	@NotNull(message = "pleace enter confirm password")
	@Size(min = 8,message = "password must be match")
	private String confirmpassword;

}
