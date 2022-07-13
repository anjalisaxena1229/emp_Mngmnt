package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.User;
import com.example.demo.repo.UserRepo;

@Controller
public class UserController {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private BCryptPasswordEncoder bp;
	
	
	
	@GetMapping("/showRegister")
	public String showRegPage() {
		return "register";
	}
	
	
	@PostMapping("/register")
	public String register(@ModelAttribute User u,HttpSession session) {
		
		u.setPassword(bp.encode(u.getPassword()));
		u.setRole("ROLE_USER");
		userRepo.save(u);
		session.setAttribute("message","User registered successfully !!!");
		return "redirect:/showRegister";
	}
	
	@GetMapping("/login")
	public String showLoginPage() {
		return "login";
	}
	

}
