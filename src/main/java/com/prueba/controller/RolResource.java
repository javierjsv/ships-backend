package com.prueba.controller;

import com.prueba.exception.ResourceNotFoundException;
import com.prueba.model.Rol;
import com.prueba.model.Tarea;
import com.prueba.repository.RolRepository;
import com.prueba.repository.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", maxAge = 3600)
public class RolResource {

    @Autowired
    RolRepository rolRepository;

    @GetMapping("/roles")
    @PreAuthorize("hasAuthority('Admin')")
    public List<Rol> getAll() {
        return rolRepository.findAll();
    }

    @PostMapping("/roles")
    @PreAuthorize("hasAuthority('Admin')")
    public Rol create(@Valid @RequestBody Rol r) {
        return rolRepository.save(r);
    }

    @GetMapping("/roles/{id}")
    @PreAuthorize("hasAuthority('Admin')")
    public Rol getById(@PathVariable(value = "id") Long id) {
        return rolRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rol", "id", id));
    }

    @PutMapping("/roles/{id}")
    @PreAuthorize("hasAuthority('Admin')")
    public Rol update(@PathVariable(value = "id") Long id,
            @Valid @RequestBody Rol r) {

        Rol rol = rolRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rol", "id", id));

        Rol updatedrol = rolRepository.save(r);
        return updatedrol;
    }

    @DeleteMapping("/roles/{id}")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        Rol rol = rolRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rol", "id", id));

        rolRepository.delete(rol);

        return ResponseEntity.ok().build();
    }
}
