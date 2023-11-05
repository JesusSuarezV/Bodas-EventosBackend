package com.app.web.entidad.compuesta;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.ForeignKey;


import com.app.web.entidad.Prenda;
import com.app.web.entidad.Talla;

public class PrendaTallaId implements Serializable {

	private Prenda prenda;

	private Talla talla;
	public Prenda getPrenda() {
		return prenda;
	}
	public void setPrenda(Prenda prenda) {
		this.prenda = prenda;
	}
	public Talla getTalla() {
		return talla;
	}
	public void setTalla(Talla talla) {
		this.talla = talla;
	}
	public PrendaTallaId(Prenda prenda, Talla talla) {
		super();
		this.prenda = prenda;
		this.talla = talla;
	}
	public PrendaTallaId() {
		super();
	}

	
}
