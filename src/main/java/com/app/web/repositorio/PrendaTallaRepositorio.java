package com.app.web.repositorio;

import java.awt.Image;
import java.util.List;

import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.web.entidad.Categoria;
import com.app.web.entidad.Prenda;
import com.app.web.entidad.PrendaTalla;
import com.app.web.entidad.compuesta.PrendaTallaId;

@Repository
public interface PrendaTallaRepositorio extends JpaRepository<PrendaTalla, PrendaTallaId> {
	// Puedes agregar consultas personalizadas aquí si es necesario

	List<PrendaTalla> findByVisibilidadTrue();

	default void softDelete(PrendaTallaId id) {
		PrendaTalla prendaTalla = findById(id).orElse(null);
		prendaTalla.setVisibilidad(false);
		System.out.println("Waka Waka Eh Eh: " + prendaTalla.getPrenda().getNombre()
				+ prendaTalla.getPrenda().getCategoria().getNombre());
		save(prendaTalla);
	}

	@Modifying
	@Transactional
	@Query(value = "INSERT INTO prenda_talla (id_prenda, id_talla, precio, cantidad, visibilidad) "
			+ "VALUES (:idPrenda, :idTalla, :precio, :cantidad, :visibilidad)", nativeQuery = true)
	void customSave(@Param("idPrenda") int idPrenda, @Param("idTalla") int idTalla, @Param("precio") int precio,
			@Param("cantidad") int cantidad, @Param("visibilidad") boolean visibilidad);

	@Modifying
	@Query("UPDATE PrendaTalla pt SET pt.precio = :precio, pt.cantidad = :cantidad, pt.visibilidad = :visibilidad WHERE pt.prenda.id = :prendaId AND pt.talla.id = :tallaId")
	@Transactional
	void customUpdate(@Param("prendaId") int prendaId, @Param("tallaId") int tallaId,
			@Param("precio") int precio, @Param("cantidad") int cantidad, @Param("visibilidad") boolean visibilidad);

	List<PrendaTalla> findByPrendaAndVisibilidadIsTrue(Prenda prenda);

}
