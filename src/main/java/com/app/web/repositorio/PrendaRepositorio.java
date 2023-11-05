package com.app.web.repositorio;

import java.awt.Image;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.web.entidad.Categoria;
import com.app.web.entidad.Prenda;

@Repository
public interface PrendaRepositorio extends JpaRepository<Prenda, Integer> {
	// Puedes agregar consultas personalizadas aquí si es necesario
	
	

	List<Prenda> findByVisibilidadTrue();

	default void softDelete(int id) {
		Prenda prenda = findById(id).orElse(null);
		prenda.setVisibilidad(false);
		save(prenda);
	}
	
	@Query("SELECT COALESCE(MAX(p.id), 0) FROM Prenda p")
    int obtenerMaximoId();
	
	
	List<Prenda> findByCategoriaAndVisibilidadIsTrue(Categoria categoria);

	
	
}
