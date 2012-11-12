package com.servinow.android;

import java.util.Date;

import com.actionbarsherlock.app.SherlockActivity;
import com.servinow.android.dao.LineaPedidoCache;
import com.servinow.android.dao.PedidoCache;
import com.servinow.android.dao.ProductoCache;
import com.servinow.android.domain.Estado;
import com.servinow.android.domain.LineaPedido;
import com.servinow.android.domain.Pedido;
import com.servinow.android.domain.Producto;
import com.servinow.android.domain.TipoProducto;
import com.servinow.android.ListaPedidoActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends SherlockActivity {
	

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainactivity); /*A esta actividad/ventana le pones este layout*/
        
        this.deleteDatabase("servinowDatabase.db");
        
        // Insetar datos de prueba en la base de datos
        PedidoCache pedidoDAO = new PedidoCache( MainActivity.this );
        LineaPedidoCache lineaPedidoDAO = new LineaPedidoCache(MainActivity.this);
        ProductoCache productoDAO = new ProductoCache(MainActivity.this);
		pedidoDAO.deleteAll();
		lineaPedidoDAO.deleteAll();
		productoDAO.deleteAll();
		
		
		
		Date fecha = new Date();
		Pedido pedido = new Pedido(fecha);
		
		Pedido pedido1 = new Pedido(fecha);
		pedido1.setConfirmado(true);
	
		pedidoDAO.insertPedido(pedido);
		pedidoDAO.insertPedido(pedido1);
		
		Producto producto = new Producto("Macarrones", R.drawable.macarrones, "Ricos macarrones", 5.0, true);
		Producto producto1 = new Producto("Sushi", R.drawable.sushi, "Sushi", 4.0, true);
		Producto producto2 = new Producto("Hamburguesa", R.drawable.hamburguesa, "Hambrguesa", 5.0, true);
		Producto producto3 = new Producto("Cerveza", R.drawable.cerveza, "Cerveza", 5.0, true);
		
		producto.setId(1);
		producto1.setId(2);
		producto2.setId(3);
		producto3.setId(4);
		
		producto.setTipo(TipoProducto.PLATO);
		producto1.setTipo(TipoProducto.PLATO);
		producto2.setTipo(TipoProducto.PLATO);
		producto3.setTipo(TipoProducto.PLATO);
		
		productoDAO.insertPedido(producto);
		productoDAO.insertPedido(producto1);
		productoDAO.insertPedido(producto2);
		productoDAO.insertPedido(producto3);
		
		LineaPedido lineaPedido = new LineaPedido(4, Estado.EN_COLA, producto, pedido);
		LineaPedido lineaPedido1 = new LineaPedido(2, Estado.EN_COLA, producto1, pedido);
		LineaPedido lineaPedido2 = new LineaPedido(3, Estado.EN_COLA, producto2, pedido);
		LineaPedido lineaPedido3 = new LineaPedido(5, Estado.EN_COLA, producto3, pedido);
		
		lineaPedidoDAO.insertLineaPedido(lineaPedido);
		lineaPedidoDAO.insertLineaPedido(lineaPedido1);
		lineaPedidoDAO.insertLineaPedido(lineaPedido2);
		lineaPedidoDAO.insertLineaPedido(lineaPedido3);
        
        Intent myIntent = new Intent(this, ListaPedidoActivity.class);
        this.startActivity(myIntent);
    }
}