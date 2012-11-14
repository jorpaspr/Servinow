package com.servinow.android.dao;

import java.util.HashMap;
import java.util.List;

import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.servinow.android.domain.Pedido;
import com.servinow.android.domain.Place;
import com.servinow.android.domain.Producto;
import com.servinow.android.domain.Restaurant;

import android.content.Context;

public class PedidoCache extends ServinowDAOBase<Pedido, Integer> {

	public PedidoCache(Context context) {
		super(context, Pedido.class);
	}
	
	public List<Pedido> getAllPedidos(){
		RuntimeExceptionDao<Pedido, Integer> pedidoDAO = getDAO();
		
		List<Pedido> pedidoList = pedidoDAO.queryForAll();
		
		return pedidoList;
	}
	
	public void insertPedido(Pedido pedido){
		RuntimeExceptionDao<Pedido, Integer> pedidoDAO = getDAO();
		pedidoDAO.create(pedido);
	}
	
	public void deletePedido(Pedido pedido){
		RuntimeExceptionDao<Pedido, Integer> pedidoDAO = getDAO();
		pedidoDAO.delete(pedido);
	}
	
	public void deletePedido(int idPedido){
		RuntimeExceptionDao<Pedido, Integer> pedidoDAO = getDAO();
		pedidoDAO.deleteById(idPedido);
	}
	
	public void deleteAll(){
		RuntimeExceptionDao<Pedido, Integer> pedidoDAO = getDAO();
		List<Pedido> pedidoList = pedidoDAO.queryForAll();
		pedidoDAO.delete(pedidoList);
	}
	
	public Pedido getPedidoNotConfirmed(Place place, Restaurant restaurant){
		
		HashMap<String, Object> sqlFieldsToMatch = new HashMap<String, Object>();
	    sqlFieldsToMatch.put("confirmado", false);
	    sqlFieldsToMatch.put("restaurant", restaurant);
	    sqlFieldsToMatch.put("place", place);
		
	    List<Pedido> pedidosList = getDAO().queryForFieldValues(sqlFieldsToMatch);
		
		Pedido pedido = pedidosList.get(0);
		
		return pedido;
	}
	
	  public List<Pedido> getPedidosNoPagados(int restaurantID) {
    HashMap<String, Object> sqlFieldsToMatch = new HashMap<String, Object>();
    sqlFieldsToMatch.put("restaurant_id", restaurantID);
    sqlFieldsToMatch.put("pagado", false);
    sqlFieldsToMatch.put("confirmado", true);
    
    List<Pedido> pedidosList = getDAO().queryForFieldValues(sqlFieldsToMatch);
    return pedidosList;
    
  }
	  
	public boolean IsThereOrderNotConfirmed(){
		RuntimeExceptionDao<Pedido, Integer> pedidoDAO = getDAO();
		List<Pedido> pedidoList = pedidoDAO.queryForEq("confirmado", false);
		boolean isThereOrderNotConfirmed = true;
		if(pedidoList.size() == 0){
			isThereOrderNotConfirmed = false;
		}
		return isThereOrderNotConfirmed;
	}
}
