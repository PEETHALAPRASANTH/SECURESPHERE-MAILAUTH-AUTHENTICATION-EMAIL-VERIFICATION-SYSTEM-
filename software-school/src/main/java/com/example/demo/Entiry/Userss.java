package com.example.demo.Entiry;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity

@Table(name = "userss")
@Data
public class Userss {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private int id;
	private String name;
	private String email;
	private String  password;
	private String passwordResetKey;
	private String mobile;
	
	
	private LocalDateTime createdOn  = LocalDateTime.now();
	
	private Boolean isActive = true ;
	
	private Boolean isemailVerifyed = false;;
	
	

}
