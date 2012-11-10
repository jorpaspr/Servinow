package com.servinow.android.domain;

import java.util.Date;

public class Pedido {
	
	private int id;
	private Date fecha;
	private boolean pagado;
	private boolean confirmado;

	public Pedido() {
		super();
	}

	public Pedido(Date fecha, boolean pagado, boolean confirmado) {
		super();
		this.fecha = fecha;
		this.pagado = pagado;
		this.confirmado = confirmado;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public boolean isPagado() {
		return pagado;
	}

	public void setPagado(boolean pagado) {
		this.pagado = pagado;
	}

	public boolean isConfirmado() {
		return confirmado;
	}

	public void setConfirmado(boolean confirmado) {
		this.confirmado = confirmado;
	}
}
