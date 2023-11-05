package com.app.web.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.web.entidad.Categoria;
import com.app.web.entidad.Prenda;
import com.app.web.entidad.PrendaTalla;
import com.app.web.entidad.compuesta.PrendaTallaId;
import com.app.web.repositorio.CategoriaRepositorio;
import com.app.web.repositorio.PrendaRepositorio;
import com.app.web.repositorio.PrendaTallaRepositorio;

import java.util.List;
import java.util.Optional;

@Service
public class PrendaTallaServicio {

	@Autowired
	private PrendaTallaRepositorio repositorio;

	public PrendaTalla guardarPrendaTalla(PrendaTalla prendaTalla) {
		return repositorio.save(prendaTalla);

	}

	public PrendaTalla obtenerPrendaTallaPorId(PrendaTallaId id) {
		return repositorio.findById(id).get();

	}

	public void crearPrendaTalla(PrendaTalla prendaTalla) {

		int prendaId = prendaTalla.getPrenda().getId();
		int tallaId = prendaTalla.getTalla().getId();
		int precio = prendaTalla.getPrecio();
		int cantidad = prendaTalla.getCantidad();

		repositorio.customSave(prendaId, tallaId, precio, cantidad, true);
	}

	
	public void actualizarPrendaTalla(PrendaTalla prendaTalla) {

		int prendaId = prendaTalla.getPrenda().getId();
		int tallaId = prendaTalla.getTalla().getId();
		int precio = prendaTalla.getPrecio();
		int cantidad = prendaTalla.getCantidad();

		repositorio.customUpdate(prendaId, tallaId, precio, cantidad, true);
	}

	public void ocultarPrendaTalla(PrendaTalla prendaTalla) {

		int prendaId = prendaTalla.getPrenda().getId();
		int tallaId = prendaTalla.getTalla().getId();
		int precio = prendaTalla.getPrecio();
		int cantidad = prendaTalla.getCantidad();

		repositorio.customUpdate(prendaId, tallaId, precio, cantidad, false);
	}

	public List<PrendaTalla> obtenerPrendaTallaPorPrenda(Prenda prenda) {
		return repositorio.findByPrendaAndVisibilidadIsTrue(prenda);

	}

}
