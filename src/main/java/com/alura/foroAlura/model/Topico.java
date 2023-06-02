package com.alura.foroAlura.model;

import java.time.LocalDate;
import java.time.ZoneId;

import com.alura.foroAlura.dto.DatosActualizarTopico;
import com.alura.foroAlura.dto.DatosRegistroTopico;

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
	private LocalDate fechaCreacion;
	@Column(name = "estatus_topico")
	private String estatus;
	private String autor;
	private String curso;
	
	public void DatosRegistro(DatosRegistroTopico datosRegistroTopico) {
		this.autor = datosRegistroTopico.autor();
		this.curso = datosRegistroTopico.curso();
		this.mensaje = datosRegistroTopico.mensaje();
		this.titulo = datosRegistroTopico.titulo();
		this.fechaCreacion = LocalDate.now(ZoneId.of("GMT-5"));
		this.estatus = "activo";
	}

	public void DatosActualizar(DatosActualizarTopico datosActualizarTopico) {
		this.autor = datosActualizarTopico.autor();
		this.curso = datosActualizarTopico.curso();
		this.titulo =  datosActualizarTopico.titulo();
		this.mensaje = datosActualizarTopico.mensaje();
	}
	
}
