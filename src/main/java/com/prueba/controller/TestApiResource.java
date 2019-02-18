/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prueba.controller;

/**
 *
 * @author anderson
 */
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestApiResource {

	@GetMapping("/api/test/admin")
	@PreAuthorize("hasRole('1')")
	public String adminAccess() {
		return ">>> usuario es admin";
	}
	
	@GetMapping("/api/test/lider")
	@PreAuthorize("hasRole('Lider')")
	public String liderAccess() {
		return ">>> usuario es lider";
	}
}