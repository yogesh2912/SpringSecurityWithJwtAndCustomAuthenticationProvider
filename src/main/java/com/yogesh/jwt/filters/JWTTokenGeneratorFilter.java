package com.yogesh.jwt.filters;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.*;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTTokenGeneratorFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		List<GrantedAuthority> al =new ArrayList<>();
		al.add(new SimpleGrantedAuthority("user"));
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if(authentication != null) {
			SecretKey key = Keys.hmacShaKeyFor("mysjhjhdhjahsjhdjjhsjdgjh398668nks&&&&".getBytes(StandardCharsets.UTF_8));
			String jwt = Jwts.builder().setIssuer("Yogesh").setSubject("JWT token")
					.claim("username", authentication.getName())
					.claim("authorities", populateAuthorities(authentication.getAuthorities()))
					.setIssuedAt(new Date())
					.setExpiration(new Date((new Date()).getTime() + 30000000))
                    .signWith(key).compact();
			
			response.setHeader("Authorization", jwt);
		}
		 filterChain.doFilter(request, response);
		
	}
	
	 @Override
	    protected boolean shouldNotFilter(HttpServletRequest request) {
	        return !request.getServletPath().equals("/loginUser");
	    }
	
	 private String populateAuthorities(Collection<? extends GrantedAuthority> collection) {
	        Set<String> authoritiesSet = new HashSet<>();
	        for (GrantedAuthority authority : collection) {
	            authoritiesSet.add(authority.getAuthority());
	        }
	        return String.join(",", authoritiesSet);
	    }

}
