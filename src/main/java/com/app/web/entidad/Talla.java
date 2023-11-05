package com.app.web.entidad;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Talla {

	@Id
	@Column(name = "id")
	private int id;

	@Column(name = "medida")
	private String medida;

	@Column(name = "visibilidad")
	private boolean visibilidad;

	public String getMedida() {
		return medida;
	}

	public void setMedida(String medida) {
		this.medida = medida;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Talla [id=" + id + ", medida=" + medida + ", visibilidad=" + visibilidad + "]";
	}

	public boolean isVisibilidad() {
		return visibilidad;
	}

	public void setVisibilidad(boolean visibilidad) {
		this.visibilidad = visibilidad;
	}

	public Talla(int id, String medida, boolean visibilidad) {
		super();
		this.id = id;
		this.medida = medida;
		this.visibilidad = visibilidad;
	}

	public Talla() {
		super();
	}

}
