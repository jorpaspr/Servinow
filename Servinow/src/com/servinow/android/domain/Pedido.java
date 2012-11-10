package com.servinow.android.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class Pedido {
	
	private int id;
	private Date fecha;
	private boolean pagado;
	private boolean confirmado;
	private ArrayList<LineaPedido> lineas;

	public Pedido() {
		super();
	}

	public Pedido(Date fecha, boolean pagado, boolean confirmado,
			ArrayList<LineaPedido> lineas) {
		super();
		this.fecha = fecha;
		this.pagado = pagado;
		this.confirmado = confirmado;
		this.lineas = lineas;
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

	public ArrayList<LineaPedido> getLineas() {
		return lineas;
	}

	public void setLineas(ArrayList<LineaPedido> lineas) {
		this.lineas = lineas;
	}
	
	public double getTotal() {
		double total = 0.0;
		
		Iterator<LineaPedido> itr = lineas.iterator();
		while (itr.hasNext()) {
			LineaPedido lp = itr.next();
			total += lp.getTotal();
		}

		return total;
	}
}
