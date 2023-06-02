package com.alura.foroAlura.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.alura.foroAlura.dto.DatosActualizarTopico;
import com.alura.foroAlura.dto.DatosObtenerTopico;
import com.alura.foroAlura.dto.DatosRegistroTopico;
import com.alura.foroAlura.model.Topico;
import com.alura.foroAlura.repository.TopicoRespository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

	@Autowired
	TopicoRespository topicoRepo;
	
	@PostMapping
	public ResponseEntity<DatosRegistroTopico> crearTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico,
			UriComponentsBuilder uriComponentsBuilder){
		Topico nuevoTopico = new Topico();
		nuevoTopico.DatosRegistro(datosRegistroTopico);
		topicoRepo.save(nuevoTopico);
		URI uriTopico = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(nuevoTopico.getId()).toUri();
		return ResponseEntity.created(uriTopico).build();
	}
	
	@GetMapping
	public ResponseEntity<Page<DatosObtenerTopico>> obtenerTopicos(Pageable pagination){
		Page<DatosObtenerTopico> topicos = topicoRepo.findAll(pagination).map(topico->new DatosObtenerTopico(topico));
		return ResponseEntity.ok(topicos);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DatosObtenerTopico> buscarTopicoPorId(@PathVariable Long id){
		Topico topico = topicoRepo.getReferenceById(id);
		DatosObtenerTopico datosTopico = new DatosObtenerTopico(topico);
		return ResponseEntity.ok(datosTopico);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminarTopico(@PathVariable Long id){
		Topico topico = topicoRepo.getReferenceById(id);
		topicoRepo.delete(topico);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<DatosActualizarTopico> modificarTopico(@PathVariable Long id, 
			@RequestBody DatosActualizarTopico datosActualizarTopico){
		Topico topico = topicoRepo.getReferenceById(id);
		topico.DatosActualizar(datosActualizarTopico);
		return ResponseEntity.ok().build();
	}
}
