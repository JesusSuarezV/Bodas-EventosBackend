package com.app.web.repositorio;

import java.awt.Image;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.web.entidad.Categoria;

@Repository
public interface CategoriaRepositorio extends JpaRepository<Categoria, Integer> {
	// Puedes agregar consultas personalizadas aquí si es necesario
	
	
	
}
