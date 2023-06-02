package com.alura.foroAlura.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alura.foroAlura.dto.DatosLogin;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

	@Autowired
	AuthenticationManager authenticationManager;
	
	@PostMapping
	public ResponseEntity<?> login(@RequestBody DatosLogin datosLogin){
		Authentication auth = new UsernamePasswordAuthenticationToken(datosLogin.email(), 
				datosLogin.password(), null);
		authenticationManager.authenticate(auth);
		return ResponseEntity.ok(auth.getPrincipal());
	}
}
