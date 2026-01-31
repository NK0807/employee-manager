package com.example.employee_manager.service;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.employee_manager.entity.User;
import com.example.employee_manager.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
	
	private final UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// DBからユーザーを探す
		User user = userRepository.findById(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
		// Spring Securityが理解できる形 (UserDetails) に変換して返す
		return new org.springframework.security.core.userdetails.User(
					user.getUsername(),
					user.getPassword(),
					AuthorityUtils.createAuthorityList(user.getRole())
				);
	}
}
