package com.prueba.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Entity
@Table(name = "tarea", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
            "alias"
        })
})
public class Tarea implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min=3, max = 80)
    private String nombre;

    @NotBlank
    @Size(min=3, max = 300)
    private String descripcion;

    @NotBlank
    @Size(min=3, max = 45)
    private String alias;

    @NotBlank
    @Size(min=3, max = 20)
    private String estado;

    @NotBlank
    private Instant fechaInicio;
    
    @NotBlank
    private Instant fechaFin;
    
    @NotBlank
    private Integer avance;

    @NotBlank
    @Size(min=3, max = 20)
    private String tiempoTarea;
    
        @NotBlank
    @Size(min=3, max = 100)
    private String proyecto;
        
    
    @JsonIgnoreProperties("")
    @ManyToOne
    private Usuario usuario;
    
    public Tarea() {}

    public Tarea(Long id) {
        this.id = id;
    }

    public Tarea(String nombre, String descripcion, String alias, String estado, Instant fechaInicio, Instant fechaFin, Integer avance, String tiempoTarea, String proyecto) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.alias = alias;
        this.estado = estado;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.avance = avance;
        this.tiempoTarea = tiempoTarea;
        this.proyecto = proyecto;
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

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Instant getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Instant fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Instant getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Instant fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Integer getAvance() {
        return avance;
    }

    public void setAvance(Integer avance) {
        this.avance = avance;
    }

    public String getTiempoTarea() {
        return tiempoTarea;
    }

    public void setTiempoTarea(String tiempoTarea) {
        this.tiempoTarea = tiempoTarea;
    }

    public String getProyecto() {
        return proyecto;
    }

    public void setProyecto(String proyecto) {
        this.proyecto = proyecto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

 
}