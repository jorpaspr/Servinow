package com.servinow.android.pedidosSystem;

import java.util.Date;

import android.content.Context;

import com.servinow.android.domain.Estado;
import com.servinow.android.domain.LineaPedido;
import com.servinow.android.domain.Pedido;
import com.servinow.android.domain.Place;
import com.servinow.android.domain.Producto;
import com.servinow.android.domain.Restaurant;
import com.servinow.android.pedidosSystem.IdPedidoAndIdLinea;
import com.servinow.android.dao.LineaPedidoCache;
import com.servinow.android.dao.PedidoCache;
import com.servinow.android.dao.PlaceCache;
import com.servinow.android.dao.RestaurantCache;

public class pedidosHandler {
	
	private Context context;
	
	public pedidosHandler(Context context){
		this.context = context;
	}
	
	public IdPedidoAndIdLinea insertarPedido(Producto producto, int cantidad, int mesaId, int restauranteId){
		
		PedidoCache pedidoCache = new PedidoCache(this.context);
		LineaPedidoCache lineaPedidoCache = new LineaPedidoCache(this.context);
		RestaurantCache restaurantCache = new RestaurantCache(this.context);
		PlaceCache placeCache = new PlaceCache(this.context);
		
		// Coger place de la base de datos a partir de su id
		Place place = placeCache.getPlaceFromCache(mesaId);
		// Coger restaurant de la base de datos a partir de su id
		Restaurant restaurant = restaurantCache.getRestaurantFromCache(restauranteId);
		
		// Si el pedido no est‡ creado, se crea un nuevo pedido no confirmado
		if(pedidoCache.IsThereOrderNotConfirmed()){
			Pedido pedido = new Pedido(new Date());
			pedido.setPlace(place);
			pedido.setRestaurant(restaurant);
			pedidoCache.insertPedido(pedido);
		}
		// Coger pedidoNoConfirmado de la base de datos
		Pedido pedidoNoConfirmado = pedidoCache.getPedidoNotConfirmed(place, restaurant);
		
		// Crear LineaPedido a partir de la cantidad y el producto
		LineaPedido lineaPedido = new LineaPedido(cantidad, Estado.EN_COLA, producto);
		lineaPedido.setOrder(pedidoNoConfirmado);
		
		// Insertar LineaPedido en la base de datos
		lineaPedidoCache.insertLineaPedido(lineaPedido);
		
		// Devolver el identificador de la l’nea de pedido creada y el identificador del pedido
		IdPedidoAndIdLinea idPedidoAndIdLinea = new IdPedidoAndIdLinea();
		idPedidoAndIdLinea.idPedido = pedidoNoConfirmado.getId();
		idPedidoAndIdLinea.idLinea = lineaPedido.getId();
		
		return idPedidoAndIdLinea;
	}
	// TODO Implementar cancelarPedido
	// Borrar todas las l’neas de pedido de ese pedido.
	// Borrar Pedido.
	public void cancelarPedido(){
		
	}
}
