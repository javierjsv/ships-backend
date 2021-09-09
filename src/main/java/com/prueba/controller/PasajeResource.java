package com.prueba.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.exception.ResourceNotFoundException;
import com.prueba.model.Pasaje;
import com.prueba.repository.PasajeRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", maxAge = 3600)
public class PasajeResource {

	@Autowired
	PasajeRepository pasajeRepository;

	@GetMapping("/pasaje")
	public List<Pasaje> getAll() {
		return pasajeRepository.findAll();
	}

	@PostMapping("/pasaje")
	public Pasaje create(@Valid @RequestBody Pasaje t) {
		return pasajeRepository.save(t);
	}

	@GetMapping("/pasaje/{id}")
	public Pasaje getById(@PathVariable(value = "id") Long id) {
		return pasajeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pasaje", "id", id));
	}

	@GetMapping("/pasaje/list/{id}")
	public List<Pasaje> getAllPasaje(@PathVariable(value = "id") Long id) {
		return pasajeRepository.ListPasajeUser(id);
	}

	@PutMapping("/pasaje/{id}")
	public Pasaje update(@PathVariable(value = "id") Long id, @Valid @RequestBody Pasaje t) {

		Pasaje Pasaje = pasajeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Pasaje", "id", id));

		Pasaje updatedTarea = pasajeRepository.save(t);
		return updatedTarea;
	}

	@DeleteMapping("/pasaje/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
		Pasaje Pasaje = pasajeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Pasaje", "id", id));

		pasajeRepository.delete(Pasaje);

		return ResponseEntity.ok().build();
	}

}
