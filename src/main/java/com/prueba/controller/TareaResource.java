package com.prueba.controller;

import com.prueba.exception.ResourceNotFoundException;
import com.prueba.model.Tarea;
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
public class TareaResource {

    @Autowired
    TareaRepository tareaRepository;

    @GetMapping("/tareas")
    @PreAuthorize("hasAuthority('Admin') or hasAuthority('Lider')")
    public List<Tarea> getAll() {
        return tareaRepository.findAll();
    }

    @PostMapping("/tareas")
    @PreAuthorize("hasAuthority('Admin') or hasAuthority('Lider')")
    public Tarea create(@Valid @RequestBody Tarea t) {
        return tareaRepository.save(t);
    }

    @GetMapping("/tareas/{id}")
    @PreAuthorize("hasAuthority('Admin') or hasAuthority('Lider')")
    public Tarea getById(@PathVariable(value = "id") Long id) {
        return tareaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tarea", "id", id));
    }

    @PutMapping("/tareas/{id}")
    @PreAuthorize("hasAuthority('Admin')")
    public Tarea update(@PathVariable(value = "id") Long id,
            @Valid @RequestBody Tarea t) {

        Tarea tarea = tareaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tarea", "id", id));

        Tarea updatedTarea = tareaRepository.save(t);
        return updatedTarea;
    }

    @DeleteMapping("/tareas/{id}")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        Tarea tarea = tareaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tarea", "id", id));

        tareaRepository.delete(tarea);

        return ResponseEntity.ok().build();
    }
}
