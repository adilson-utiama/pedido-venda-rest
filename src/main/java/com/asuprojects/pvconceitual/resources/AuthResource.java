package com.asuprojects.pvconceitual.resources;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asuprojects.pvconceitual.dto.EmailDTO;
import com.asuprojects.pvconceitual.security.UserSS;
import com.asuprojects.pvconceitual.security.jwt.JwtUtil;
import com.asuprojects.pvconceitual.services.AuthService;
import com.asuprojects.pvconceitual.services.UserService;

@RestController
@RequestMapping("/auth")
public class AuthResource {
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private AuthService authService;
	
	@PostMapping("/refresh_token")
	public ResponseEntity<Void> refreshToken(HttpServletResponse response){
		UserSS user = UserService.authenticated();
		String token = jwtUtil.generateToken(user.getUsername());
		response.addHeader("Authorization", "Bearer " + token);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping("/forgot")
	public ResponseEntity<Void> forgot(@Valid @RequestBody EmailDTO objDTO){
		authService.sendNewPassword(objDTO.getEmail());
		return ResponseEntity.noContent().build();
	}

}
