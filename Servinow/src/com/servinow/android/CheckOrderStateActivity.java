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
import android.os.Handler;
import android.util.Log;
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
	public Boolean flagTimer=false;
	public int countOrders=0;
	public int countChanges=0;
	
	private List<Pedido> listaPedidos = null;

	private ArrayList<OrdersState> orders = new ArrayList<OrdersState>();
	public ArrayList<OrdersState> ordersToDisplay = new ArrayList<OrdersState>();
	private CheckStateArrayAdapter adapter = null;
	
	protected Handler taskHandler = new Handler();
	protected Boolean isComplete = false;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_state); 
        setTitle("Servinow");
        
        createListView(this);
        
    //    setTimer(1000);
    }

    public void createListView(Context ctx){
    	
    	populateOrders(); // populateOrders de la BD, populateOrdersB los crea
    	
    	prepareToDisplay();
    	
    	lv =  (ListView) findViewById(R.id.listViewCheckState);
    	adapter = new CheckStateArrayAdapter(this, ordersToDisplay);
    	lv.setAdapter(adapter);
    //	lv.setAdapter(new CheckStateArrayAdapter(this, ordersToDisplay));
    	
    }
    
    public void populateOrders(){
    	//TODO acceder a la BD para poblar la lista de —rdenes
    	
   
    	PedidoCache pd = new PedidoCache(this);
    	listaPedidos = pd.getAllPedidos();
    }
    
    public void populateOrdersB(){
    	Pedido pd = new Pedido();
    	
    	pd.setId(1);
    	ArrayList<LineaPedido> arraylinea = new ArrayList<LineaPedido>();
    	LineaPedido lp = new LineaPedido();
    	lp.setCantidad(8);
    	lp.setEstado(Estado.EN_COLA);
    	Producto pr = new Producto();
    	lp.setProducto(pr);
    	arraylinea.add(lp);
    	pd.setLineas(arraylinea);
    	listaPedidos = new ArrayList<Pedido>();
    	listaPedidos.add(pd);
    	
    }
    
 
    
    public void prepareToDisplay(){
    	
  /*  	Bitmap mIcon_val=null;
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
		} */
    	
    	
    	for(int i=listaPedidos.size()-1; i>=0; i--){
    		OrdersState ord= new OrdersState();
    		ord.round=listaPedidos.get(i).getId();
    		ord.roundmark=true;
    		ordersToDisplay.add(ord);
    		countOrders++;
    		Iterator<LineaPedido> itr = listaPedidos.get(i).getLineas().iterator();
    		while (itr.hasNext()) {
    			LineaPedido lp = itr.next();
    			for(int j=0; j<lp.getCantidad(); j++){
    				OrdersState ordp = new OrdersState();
    				ordp.roundmark=false;
    				ordp.name = lp.getProducto().getNombre();
    				ordp.state = lp.getEstado();
    				ordp.lineaPedidoId=lp.getId();
    				ordp.cantidad=lp.getCantidad();
    				//TODO imagen
    				String imageName = lp.getProducto().getImageName();
    			//	ordp.image = 
    				ordersToDisplay.add(ordp);
    				countOrders++;
    			}
    		}
    		countChanges=countOrders*3;
    	}
    }
    
    protected void setTimer( long time )
    {
        final long elapse = 1000+ (int)(Math.random()*2000);
        Runnable t = new Runnable() {
            public void run()
            {
                runNextTask();
                	if(!flagTimer)
                		taskHandler.postDelayed( this, elapse );
                	
                
            }
        };
    	taskHandler.postDelayed( t, elapse );
    }
     
    protected void runNextTask()
    {
        // run my task.
        // determine isComplete.
    	int nOrder = (int)(Math.random()*countOrders);
    	while(ordersToDisplay.get(nOrder).roundmark==true)
    		nOrder = (int)(Math.random()*countOrders);
    	
    	if(ordersToDisplay.get(nOrder).state == Estado.EN_COLA){
    		ordersToDisplay.get(nOrder).state=Estado.PREPARANDO;
    		countChanges--;
    	}else if(ordersToDisplay.get(nOrder).state == Estado.PREPARANDO){
    		ordersToDisplay.get(nOrder).state=Estado.LISTO;
    		countChanges--;
    	}else if(ordersToDisplay.get(nOrder).state == Estado.LISTO){
    		ordersToDisplay.get(nOrder).state=Estado.SERVIDO;
    		countChanges--;
    	}
    	adapter.notifyDataSetChanged();
    	if(countChanges<=0)
    		flagTimer=true;
    }
    
}
