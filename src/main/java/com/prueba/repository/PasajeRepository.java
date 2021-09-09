package com.prueba.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.prueba.model.Pasaje;

public interface PasajeRepository extends JpaRepository<Pasaje, Long> {

	@Query(value = "select * from pasaje where usuario_id=:id", nativeQuery = true)
	public List<Pasaje> ListPasajeUser(@Param("id") Long id);

}
