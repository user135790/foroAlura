package com.alura.foroAlura.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alura.foroAlura.dto.DatosLogin;
import com.alura.foroAlura.dto.DatosToken;
import com.alura.foroAlura.security.TokenService;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	TokenService tokenService;
	
	@PostMapping
	public ResponseEntity<?> login(@RequestBody DatosLogin datosLogin){
		String token = null;
		Authentication auth = new UsernamePasswordAuthenticationToken(datosLogin.email(), 
				datosLogin.password());
		authenticationManager.authenticate(auth);
		token = tokenService.createJWT((String)auth.getPrincipal());
		return ResponseEntity.ok(new DatosToken(token));
	}
}
