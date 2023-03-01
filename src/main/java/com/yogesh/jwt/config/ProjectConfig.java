package com.yogesh.jwt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.yogesh.jwt.filters.JWTTokenGeneratorFilter;
import com.yogesh.jwt.filters.JWTTokenValidatorFilter;

@Configuration
public class ProjectConfig {
	
	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			.csrf().disable()
			.addFilterAfter(new JWTTokenGeneratorFilter(), BasicAuthenticationFilter.class)
			.addFilterBefore(new JWTTokenValidatorFilter(), BasicAuthenticationFilter.class)
			.authorizeHttpRequests()
			.requestMatchers("/register").permitAll()
			.anyRequest().authenticated();
		
		http.httpBasic();
		http.formLogin();
		
		return http.build();
	}
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
