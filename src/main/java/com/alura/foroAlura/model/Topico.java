package com.alura.foroAlura.model;

import java.time.LocalDate;
import java.time.ZoneId;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "topicos")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Topico {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titulo;
	private String mensaje;
	@Column(name = "fecha_creacion")
	private LocalDate fechaCreacion = LocalDate.now(ZoneId.of("GMT-5"));
	@Column(name = "estatus_topico")
	private String estatus;
	private String autor;
	private String curso;
	
}
