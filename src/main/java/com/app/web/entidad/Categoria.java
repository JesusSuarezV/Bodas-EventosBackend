package com.app.web.entidad;

import java.sql.Types;
import java.util.Base64;

import org.hibernate.annotations.JdbcType;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "Categoria")
public class Categoria {
	@Id
	@Column(name = "Id")
	private int id;
	@Column(name = "Nombre")
	private String nombre;
	@Lob
	@JdbcTypeCode(Types.VARBINARY)
	@Column(name = "Imagen")
	private byte[] imagen;

	public Categoria() {

	}
	
	public String getBase64(byte[] imagen) {
		return Base64.getEncoder().encodeToString(imagen);

		
		
	}

	public Categoria(int id, String nombre, byte[] imagen) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.imagen = imagen;
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

	@Override
	public String toString() {
		return "Categoria [id=" + id + ", nombre=" + nombre + ", imagen=" + imagen + "]";
	}

}
