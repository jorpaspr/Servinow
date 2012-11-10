package com.servinow.android.domain;

public class LineaPedido {
	
	private int id;
	private int cantidad;
	private Estado estado;

	public LineaPedido() {
		super();
	}

	public LineaPedido(int cantidad, Estado estado) {
		super();
		this.cantidad = cantidad;
		this.estado = estado;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
}
