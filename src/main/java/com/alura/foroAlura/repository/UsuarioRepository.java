package com.alura.foroAlura.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.alura.foroAlura.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	UserDetails findByEmail(String email);

}
