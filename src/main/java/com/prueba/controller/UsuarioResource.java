package com.prueba.controller;

import com.prueba.exception.ResourceNotFoundException;
import com.prueba.model.Usuario;
import com.prueba.repository.UsuarioRepository;
import com.prueba.security.services.UserPrinciple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UsuarioResource {

    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping("/usuarios")
    @PreAuthorize("hasAuthority('Admin') or hasAuthority('Lider')")
    public List<Usuario> getAll() {

        return usuarioRepository.findAll();
    }

    @PostMapping("/usuarios")
    @PreAuthorize("hasAuthority('Admin')")
    public Usuario create(@Valid @RequestBody Usuario u) {
        return usuarioRepository.save(u);
    }

    @GetMapping("/usuarios/{id}")
    @PreAuthorize("hasAuthority('Admin') or hasAuthority('Lider')")
    public Usuario getById(@PathVariable(value = "id") Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", id));
    }

    @PutMapping("/usuarios/{id}")
    @PreAuthorize("hasAuthority('Admin')")
    public Usuario update(@PathVariable(value = "id") Long id,
            @Valid @RequestBody Usuario u) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", id));

        Usuario updatedUsuario = usuarioRepository.save(usuario);
        return updatedUsuario;
    }

    @DeleteMapping("/usuarios/{id}")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", id));

        usuarioRepository.delete(usuario);

        return ResponseEntity.ok().build();
    }
}
