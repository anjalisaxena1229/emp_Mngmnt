package com.example.demo.securityconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity
public class MyConfig extends WebSecurityConfigurerAdapter  {

	@Bean
  UserDetailsService  getUserDetailsService() {
		return new CustomUserDetailsService();
	}

    @Bean
    BCryptPasswordEncoder getPassword() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    DaoAuthenticationProvider daoProvider() {
        DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
        dao.setUserDetailsService(getUserDetailsService());
        dao.setPasswordEncoder(getPassword());
        return dao;
    }
	 
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
    	auth.authenticationProvider(daoProvider());
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
		.antMatchers("/showNewEmployeeForm").hasRole("USER")
		.antMatchers("/showFormForUpdate/{id}").hasRole("USER")
		.antMatchers("/deleteEmployee/{id}").hasRole("USER")
		.antMatchers("/loginn").hasRole("USER")
		.antMatchers("/").permitAll()
		.and()
		.formLogin()
		.loginPage("/login")
		.loginProcessingUrl("/dologin").defaultSuccessUrl("/viewData")
		.and()
		.csrf().disable();
		
    }
    
    
    
	
		//.antMatchers("/loginn").hasRole("USER")
    
}
	  
	
