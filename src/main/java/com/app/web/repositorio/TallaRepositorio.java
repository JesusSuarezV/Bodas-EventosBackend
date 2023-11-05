package com.app.web.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.web.entidad.Categoria;
import com.app.web.entidad.Prenda;
import com.app.web.entidad.Talla;

public interface TallaRepositorio extends JpaRepository<Talla, Integer>{
	
	

	List<Talla> findByVisibilidadTrue();

	default void softDelete(int id) {
		
		Talla talla = findById(id).orElse(null);
		talla.setVisibilidad(false);
		save(talla);
	}
	
	@Query("SELECT COALESCE(MAX(t.id), 0) FROM Talla t")
    int obtenerMaximoId();
	
	List<Talla> findByIdAndVisibilidadIsTrue(Talla talla);

	
	
}
