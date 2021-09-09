package com.prueba.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "nave")
public class Nave implements Serializable {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min=3, max = 80)
    private String nombre;

    @NotBlank
    @Size(min=3, max = 300)
    private String descripcion;
    
    
    @JsonIgnoreProperties("")
    @ManyToOne
    private Usuario usuario;

 
    @JsonIgnoreProperties("")
    @ManyToOne
    private Nave nave;

    
	public Nave() {
		super();
	}


	public Nave(Long id, @NotBlank @Size(min = 3, max = 80) String nombre,
			@NotBlank @Size(min = 3, max = 300) String descripcion, Usuario usuario, Nave nave) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.usuario = usuario;
		this.nave = nave;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	public Nave getNave() {
		return nave;
	}


	public void setNave(Nave nave) {
		this.nave = nave;
	}


    
	
}
