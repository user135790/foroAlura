package com.alura.foroAlura.dto;

import java.time.LocalDate;

import com.alura.foroAlura.model.Topico;

public record DatosObtenerTopico(String titulo, String mensaje, LocalDate fechaCreacion,
		String estatus, String autor, String curso) {
	public DatosObtenerTopico(Topico topico) {
		this(topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(),
				topico.getEstatus(), topico.getAutor(), topico.getCurso());
	}
}
