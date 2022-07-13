package com.example.demo.securityconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repo.UserRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	UserRepo userRepo; 

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub

			
			User user=userRepo.findByEmail(username);
			
			if(user==null) {
				throw new UsernameNotFoundException("User doesn't exist!");
			}else {
				return new CustomUserDetails(user);
			}
			
		
		
		

	}

}
