package com.TaskMaster.TaskMaster.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.TaskMaster.TaskMaster.Entity.User;
import com.TaskMaster.TaskMaster.Model.DTO.LoginRequestDTO;
import com.TaskMaster.TaskMaster.Model.DTO.RegisterRequestDTO;
import com.TaskMaster.TaskMaster.Repository.UserRepository;
import com.TaskMaster.TaskMaster.Service.AuthService;
import com.TaskMaster.TaskMaster.Security.JwtTokenProvider; // Ensure this matches your package

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Override
	public void register(RegisterRequestDTO request) {
		if (userRepository.existsByUsername(request.getUsername())) {
			throw new RuntimeException("Username is already taken");
		}
		if (userRepository.existsByEmail(request.getEmail())) {
			throw new RuntimeException("Email is already in use");
		}

		User user = new User();
		user.setUsername(request.getUsername());
		user.setEmail(request.getEmail());
		user.setPassword(passwordEncoder.encode(request.getPassword()));

		userRepository.save(user);
	}

	@Override
	public String login(LoginRequestDTO request) {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		return jwtTokenProvider.generateToken(authentication);
	}
}