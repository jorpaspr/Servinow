package com.servinow.android;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockActivity;
import com.servinow.android.dao.PedidoCache;
import com.servinow.android.domain.Estado;
import com.servinow.android.domain.LineaPedido;
import com.servinow.android.domain.OrdersState;
import com.servinow.android.domain.Pedido;
import com.servinow.android.domain.Producto;
import com.servinow.android.widget.CheckStateArrayAdapter;

public class CheckOrderStateActivity extends SherlockActivity {

	private ListView lv;
	
	private List<Pedido> listaPedidos = null;

	private ArrayList<OrdersState> orders = new ArrayList<OrdersState>();
	private ArrayList<OrdersState> ordersToDisplay = new ArrayList<OrdersState>();
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_state); 
        setTitle("Servinow");
        
        createListView(this);
        
    }

    public void createListView(Context ctx){
    	
    	
    	populateOrders(); // Sobreescribir para cogerlos de la BD
    	
    	prepareToDisplay();
    	lv =  (ListView) findViewById(R.id.listViewCheckState);
    	lv.setAdapter(new CheckStateArrayAdapter(this, ordersToDisplay));
    }
    
    public void populateOrders(){
    	//TODO acceder a la BD para poblar la lista de —rdenes
    	
   
    	PedidoCache pd = new PedidoCache(this);
    	listaPedidos = pd.getAllPedidos();
    }
    
 
    
    public void prepareToDisplay(){
    	
    	Bitmap mIcon_val=null;
    	URL newurl;
		try {
			newurl = new URL("http://www.recetasdiarias.com/wp-content/uploads/2010/01/tarta-de-queso.jpg");
			mIcon_val = BitmapFactory.decodeStream(newurl.openConnection() .getInputStream()); 
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    	
    	OrdersState ord= new OrdersState();
    	for(int i=listaPedidos.size()-1; i>=0; i--){
    		ord.round=listaPedidos.get(i).getId();
    		ord.roundmark=true;
    		ordersToDisplay.add(ord);
    		Iterator<LineaPedido> itr = listaPedidos.get(i).getLineas().iterator();
    		while (itr.hasNext()) {
    			LineaPedido lp = itr.next();
    			for(int j=0; j<lp.getCantidad(); j++){
    				ord.name = lp.getProducto().getNombre();
    				ord.state = lp.getEstado();
    				ord.image = mIcon_val;
    				ordersToDisplay.add(ord);
    			}
    		}
    	}
    }
    
}
