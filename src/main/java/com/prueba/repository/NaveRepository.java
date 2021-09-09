package com.prueba.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.prueba.model.Nave;

@Repository
public interface NaveRepository extends JpaRepository<Nave, Long> {

	@Query(value = "select * from nave where usuario_id=:id", nativeQuery = true)
	public List<Nave> ListNavesUser(@Param("id") Long id);

	// public List<Nave> findAllByUser_id(Long id);

}
