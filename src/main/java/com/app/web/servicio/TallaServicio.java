package com.app.web.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.web.entidad.Prenda;
import com.app.web.entidad.Talla;
import com.app.web.repositorio.TallaRepositorio;

@Service
public class TallaServicio {

	@Autowired
	private TallaRepositorio repositorio;

	public List<Talla> listarTodasLasTallas() {

		return repositorio.findByVisibilidadTrue();

	}

	public Talla guardarTalla(Talla talla) {
		return repositorio.save(talla);

	}

	public Talla obtenerTallaPorId(int id) {
		return repositorio.findById(id).get();

	}

	public void ocultarTalla(int id) {
		repositorio.softDelete(id);
	}

	public Talla actualizarTalla(Talla tala) {
		return repositorio.save(tala);
	}

	public int obtenerMaximoId() {

		return repositorio.obtenerMaximoId();

	}

}
