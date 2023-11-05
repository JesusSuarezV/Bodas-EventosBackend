package com.app.web.entidad;

import java.sql.Types;
import java.util.Base64;
import java.util.List;

import org.hibernate.annotations.Type;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Categoria")
public class Categoria {
	@Id
	@Column(name = "Id")
	private int id;
	@Column(name = "Nombre")
	private String nombre;
	@Column(name = "Imagen")
	private byte[] imagen;
	@Column(name = "visibilidad")
	private boolean visibilidad;
	public Categoria() {

	}
	
	public String getBase64(byte[] imagen) {
		return Base64.getEncoder().encodeToString(imagen);

		
		
	}

	public Categoria(int id, String nombre, byte[] imagen, boolean visibilidad) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.imagen = imagen;
		this.visibilidad = visibilidad;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public byte[] getImagen() {
		return imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}
	

	public boolean isVisibilidad() {
		return visibilidad;
	}

	public void setVisibilidad(boolean visibilidad) {
		this.visibilidad = visibilidad;
	}

	@Override
	public String toString() {
		return "Categoria [id=" + id + ", nombre=" + nombre + ", imagen=" + imagen + "]";
	}

}
