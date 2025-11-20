package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entiry.Userss;
import java.util.List;
import java.util.Optional;


public interface UserInterface extends JpaRepository<Userss, Integer> {
	
	Optional<Userss> findByEmail(String email);
	
	Optional<Userss> findByPasswordResetKey(String passwordResetKey);

}
