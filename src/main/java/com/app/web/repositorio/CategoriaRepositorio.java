package com.app.web.repositorio;

import java.awt.Image;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.web.entidad.Categoria;

@Repository
public interface CategoriaRepositorio extends JpaRepository<Categoria, Integer> {
	// Puedes agregar consultas personalizadas aquí si es necesario

	List<Categoria> findByVisibilidadTrue();

	default void softDelete(int id) {
		Categoria categoria = findById(id).orElse(null);
		categoria.setVisibilidad(false);
		save(categoria);
	}
	
	@Query("SELECT COALESCE(MAX(c.id), 0) FROM Categoria c")
    int obtenerMaximoId();

}
