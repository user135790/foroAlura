package com.alura.foroAlura.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity()
public class SecurityConfiguration {

	@Autowired
	AuthorizationFilter authFilter;
	
	@Bean
	public SecurityFilterChain securityfilterChain(HttpSecurity http) throws Exception {
		return http.csrf(csrf-> {
					csrf.disable();
				})
				
				.sessionManagement(session->{
					session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
				})
				.authorizeHttpRequests(auth->{
					auth
						.requestMatchers(HttpMethod.POST, "/login")
						.permitAll()
						.requestMatchers(HttpMethod.GET, "/swagger-ui/")
						.permitAll();
						
				})
				.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
				.build();
	}
	
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Bean
	public AuthenticationManager authenticationManager(
			AuthenticationConfiguration authenticationConfiguration) throws Exception {
		
		return authenticationConfiguration.getAuthenticationManager();
	}
	
}
