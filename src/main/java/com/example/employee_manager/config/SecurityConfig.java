package com.example.employee_manager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(auth -> auth
									.requestMatchers("/css/**","/js/**").permitAll()
									.anyRequest().authenticated()
								)
								.formLogin(login -> login
									.defaultSuccessUrl("/employees", true).permitAll()
								)
								.logout(logout -> logout
									.logoutSuccessUrl("/login").permitAll()
								);
		return http.build();
	}
	
	// パスワードを暗号化する機能(BCrypt)
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
