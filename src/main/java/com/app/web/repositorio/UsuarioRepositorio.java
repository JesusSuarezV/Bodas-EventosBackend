package com.app.web.repositorio;

import java.awt.Image;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.web.entidad.Categoria;
import com.app.web.entidad.Prenda;
import com.app.web.entidad.Usuario;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer> {
	
	public Usuario findByCorreo(String correo);
}
