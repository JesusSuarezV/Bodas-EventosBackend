package com.app.web.entidad;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Arrays;
import java.util.Base64;

import javax.persistence.Column;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "prenda", schema = "public")
public class Prenda {

	@Id
	@Column(name = "id")
	private int id;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "imagen")
	private byte[] imagen;

	@ManyToOne
	@JoinColumn(name = "id_categoria", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_categoria"))
	private Categoria categoria;
	@Column(name = "visibilidad")
	private boolean visibilidad;
	
	public Prenda() {

	}

	// Getters and setters

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

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String getBase64(byte[] imagen) {
		return Base64.getEncoder().encodeToString(imagen);

	}
	
	

	public boolean isVisibilidad() {
		return visibilidad;
	}

	public void setVisibilidad(boolean visibilidad) {
		this.visibilidad = visibilidad;
	}

	public Prenda(int id, String nombre, byte[] imagen, Categoria categoria, boolean visibilidad) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.imagen = imagen;
		this.categoria = categoria;
		this.visibilidad = visibilidad;
	}
	
	

	public Prenda(int id) {
		super();
		this.id = id;
	}

	@Override
	public String toString() {
		return "Prenda [id=" + id + ", nombre=" + nombre + ", imagen=" + Arrays.toString(imagen) + ", categoria="
				+ categoria + ", visibilidad=" + visibilidad + "]";
	}

}
