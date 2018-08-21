package com.asuprojects.pvconceitual.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class JWTAuthorizatonFilter extends BasicAuthenticationFilter {
	
	private JwtUtil jwtUtil;
	private UserDetailsService userDetailsSevice;

	public JWTAuthorizatonFilter(AuthenticationManager authenticationManager,
			JwtUtil jwtUtil, UserDetailsService userDetailsSevice) {
		super(authenticationManager);
		this.jwtUtil = jwtUtil;
		this.userDetailsSevice = userDetailsSevice;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		String headerToken = request.getHeader("Authorization");
		
		if(headerToken != null && headerToken.startsWith("Bearer ")) {
			UsernamePasswordAuthenticationToken auth = getAuthentication(request, headerToken.substring(7));
			SecurityContextHolder.getContext().setAuthentication(auth);
		}
		chain.doFilter(request, response);
	}

	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request, String token) {
		if(jwtUtil.tokenValido(token)) {
			String username = jwtUtil.getUsername(token);
			UserDetails user = userDetailsSevice.loadUserByUsername(username);
			return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		}
		return null;
	}
}
