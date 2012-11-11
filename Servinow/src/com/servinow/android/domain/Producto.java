package com.servinow.android.domain;

import java.util.Collection;

import android.graphics.drawable.Drawable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;

/*
 * TODO ONLY getters.
 **/
public class Producto {

	@DatabaseField(id = true)
	private int id;
	
	@DatabaseField(canBeNull = false)
	private String nombre;
	
	@DatabaseField(canBeNull = true)
	private String urlImage;
	
	//TODO remove.
	private Drawable imagen;
	
	@DatabaseField(canBeNull = true)
	private String descripcion;
	
	@DatabaseField(canBeNull = false)
	private double precio;
	
	@DatabaseField(canBeNull = false)
	private boolean stock;
	
	@DatabaseField(canBeNull = false)
	private TipoProducto tipo;
	
	@ForeignCollectionField(eager = true)
	private Collection<Producto> meals;
	
	// Requerido por ORMLite
	@SuppressWarnings("unused")
	@DatabaseField(foreign=true, foreignAutoCreate=true, foreignAutoRefresh=true)
	private Categoria category;
	
	// Requerido por ORMLite
	@SuppressWarnings("unused")
	@DatabaseField(foreign=true, foreignAutoCreate=true, foreignAutoRefresh=true)
	private Restaurant restaurant;
	
	public Producto() {
	}

	//TODO remove.
	public Producto(String nombre, Drawable imagen, String descripcion,
			double precio, boolean stock) {
		super();
		this.nombre = nombre;
		this.imagen = imagen;
		this.descripcion = descripcion;
		this.precio = precio;
		this.stock = stock;
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

	public String getUrlImage() {
		return urlImage;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}

	public Drawable getImagen() {
		return imagen;
	}

	public void setImagen(Drawable imagen) {
		this.imagen = imagen;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public boolean isStock() {
		return stock;
	}

	public void setStock(boolean stock) {
		this.stock = stock;
	}

	public TipoProducto getTipo() {
		return tipo;
	}

	public void setTipo(TipoProducto tipo) {
		this.tipo = tipo;
	}

	public Collection<Producto> getMeals() {
		return meals;
	}

	public void setMeals(Collection<Producto> meals) {
		this.meals = meals;
	}
}
