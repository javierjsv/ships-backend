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
import com.prueba.model.Nave;
import com.prueba.repository.NaveRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", maxAge = 3600)
public class NaveResource {

	@Autowired
	NaveRepository naveRepository;

	@GetMapping("/naves")
	public List<Nave> getAll() {
		return naveRepository.findAll();
	}

	@PostMapping("/naves")
	public Nave create(@Valid @RequestBody Nave t) {
		return naveRepository.save(t);
	}

	@GetMapping("/naves/{id}")
	public Nave getById(@PathVariable(value = "id") Long id) {
		return naveRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Nave", "id", id));
	}

	@GetMapping("/naves/list/{id}")
	public List<Nave> getByIdList(@PathVariable(value = "id") Long id) {
		return naveRepository.ListNavesUser(id);
	}

	@PutMapping("/naves/{id}")
	public Nave update(@PathVariable(value = "id") Long id, @Valid @RequestBody Nave t) {

		Nave nave = naveRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Nave", "id", id));

		Nave updatedNave = naveRepository.save(t);
		return updatedNave;
	}

	@DeleteMapping("/naves/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
		Nave nave = naveRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Nave", "id", id));

		naveRepository.delete(nave);
		return ResponseEntity.ok().build();
	}

}
