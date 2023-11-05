package com.app.web.entidad;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.app.web.entidad.compuesta.PrendaTallaId;

@Entity
@Table(name = "prenda_talla", schema = "public")
@IdClass(PrendaTallaId.class)
public class PrendaTalla {

	@Id
	@ManyToOne
	@JoinColumn(name = "id_prenda", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_prenda"))
	private Prenda prenda;

	@Id
	@ManyToOne
	@JoinColumn(name = "id_talla", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_talla"))
	private Talla talla;
	@Column(name = "precio")
	private int precio;
	@Column(name = "cantidad")
	private int cantidad;

	@Column(name = "visibilidad")
	private boolean visibilidad;

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

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public boolean isVisibilidad() {
		return visibilidad;
	}

	public void setVisibilidad(boolean visibilidad) {
		this.visibilidad = visibilidad;
	}

	public PrendaTalla(Prenda prenda, Talla talla, int precio, int cantidad, boolean visibilidad) {
		super();
		this.prenda = prenda;
		this.talla = talla;
		this.precio = precio;
		this.cantidad = cantidad;
		this.visibilidad = visibilidad;
	}

	public PrendaTalla() {
		super();
	}

	@Override
	public String toString() {
		return "PrendaTalla [prenda=" + prenda + ", talla=" + talla + ", precio=" + precio + ", cantidad=" + cantidad
				+ ", visibilidad=" + visibilidad + "]";
	}
	

}