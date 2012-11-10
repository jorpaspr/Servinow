package com.servinow.android.domain;

import android.graphics.drawable.Drawable;

public class Categoria {

	private int id;
	private String nombre;
	private Drawable imagen;
	
	public Categoria() {
		super();
	}

	public Categoria(String nombre, Drawable imagen) {
		super();
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

	public Drawable getImagen() {
		return imagen;
	}

	public void setImagen(Drawable imagen) {
		this.imagen = imagen;
	}
}
