package com.servinow.android.dao;

import java.util.List;

import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.servinow.android.domain.Producto;

import android.content.Context;

public class ProductoCache extends ServinowDAOBase<Producto, Integer> {

	public ProductoCache(Context context) {
		super(context, Producto.class);
	}
	
	public void insertPedido(Producto producto){
		RuntimeExceptionDao<Producto, Integer> productoDAO = getDAO();
		productoDAO.create(producto);
	}
	
	public void deleteAll(){
		RuntimeExceptionDao<Producto, Integer> productoDAO = servinowDatabase.getRuntimeExceptionDao(Producto.class);
		List<Producto> productoList = productoDAO.queryForAll();
		productoDAO.delete(productoList);
	}
	
}
