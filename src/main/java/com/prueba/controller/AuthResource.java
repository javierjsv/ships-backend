package com.prueba.controller;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import com.prueba.message.request.LoginForm;
import com.prueba.message.request.SignUpForm;
import com.prueba.message.response.JwtResponse;
import com.prueba.model.Rol;
import com.prueba.model.Usuario;
import com.prueba.security.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.prueba.repository.RolRepository;
import com.prueba.repository.UsuarioRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthResource {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    RolRepository rolRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        return ResponseEntity.ok(new JwtResponse(jwt));
    }

    @PostMapping("/registrar")
    public ResponseEntity<String> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
        if(usuarioRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity<String>("Fail -> Ya se encuentra registrado el email!",
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        Usuario user = new Usuario(signUpRequest.getNombre(), signUpRequest.getCedula(),
                signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRol();
        Set<Rol> roles = new HashSet<>();

        strRoles.forEach(role -> {
        	switch(role) {
	    		case "Administrador":
	    			Rol adminRol = rolRepository.findByNombre(role)
	                .orElseThrow(() -> new RuntimeException("Fail! -> Causa: rol no encontrado"));
	    			roles.add(adminRol);
	    			
	    			break;
	    		case "Lider":
	            	Rol liderRol = rolRepository.findByNombre(role)
	                .orElseThrow(() -> new RuntimeException("Fail! -> Causa: rol no encontrado"));
	            	roles.add(liderRol);
	            	
	    			break;
	    		    			
        	}
        });
        
        user.setRoles(roles);
        usuarioRepository.save(user);

        return ResponseEntity.ok().body("Usuario registrado correctamente!");
    }
}