package com.prueba.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "pasaje")
public class Pasaje implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(min = 3, max = 80)
	private String fecha_creacion;

	@NotBlank
	@Size(min = 3, max = 80)
	private String fecha_llegada;

	@NotBlank
	@Size(min = 3, max = 80)
	private String fecha_salida;

	@NotBlank
	@Size(min = 3, max = 80)
	private String ubicacion;

	@JsonIgnoreProperties("")
	@ManyToOne
	private Usuario usuario;
	
	@JsonIgnoreProperties("")
	@ManyToOne
	private Nave nave;

	public Pasaje() {
	}

	public Pasaje(Long id, @NotBlank @Size(min = 3, max = 80) String fecha_creacion,
			@NotBlank @Size(min = 3, max = 80) String fecha_llegada,
			@NotBlank @Size(min = 3, max = 80) String fecha_salida, @NotBlank @Size(min = 3, max = 80) String ubicacion,
			Usuario usuario, Nave nave) {
		super();
		this.id = id;
		this.fecha_creacion = fecha_creacion;
		this.fecha_llegada = fecha_llegada;
		this.fecha_salida = fecha_salida;
		this.ubicacion = ubicacion;
		this.usuario = usuario;
		this.nave = nave;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFecha_creacion() {
		return fecha_creacion;
	}

	public void setFecha_creacion(String fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}

	public String getFecha_llegada() {
		return fecha_llegada;
	}

	public void setFecha_llegada(String fecha_llegada) {
		this.fecha_llegada = fecha_llegada;
	}

	public String getFecha_salida() {
		return fecha_salida;
	}

	public void setFecha_salida(String fecha_salida) {
		this.fecha_salida = fecha_salida;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
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
