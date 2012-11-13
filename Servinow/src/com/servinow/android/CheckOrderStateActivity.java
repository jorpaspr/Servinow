package com.servinow.android;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockActivity;
import com.servinow.android.CheckOrderState.OrdersState;
import com.servinow.android.widget.CheckStateArrayAdapter;

public class CheckOrderStateActivity extends SherlockActivity {

	private ListView lv;
/*	static final String[] PLATOS = 
            new String[] { "Arroz con mocos fritos", "Pollo", "Patatas", "Bacalao", "Arroz", "Pollo", "Patatas", 
		"Arroz", "Pollo", "Patatas", "Bacalao", "Bacalao", "Arroz", "Pollo", "Patatas", "Bacalao"};*/
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
    //	List<OrdersState> orders = new List<OrdersState>;
    	lv =  (ListView) findViewById(R.id.listViewCheckState);
    	lv.setAdapter(new CheckStateArrayAdapter(this, ordersToDisplay));
   // 	String[] round = {"1","2"};
   // 	lv.setAdapter(new CheckStateRoundArrayAdapter(this, round));
    }
    
    public void populateOrders(){
    	//TODO acceder a la BD para poblar la lista de —rdenes
    	
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
    	
    	int i;
    	for(i=1; i<5; i++){
    		for(int j=0; j<10; j++){
    			OrdersState ord = new OrdersState();
    			
    			ord.name = "Arroz"+i+"  "+j;
    			ord.state = this.getString(R.string.checkstateactivity_encola);
    			ord.image = mIcon_val;
    			ord.round=i;
    			
    			orders.add(ord);
    		}
    	}
    }
    
    public void prepareToDisplay(){
    	int round=-1;
    	for(int i=orders.size()-1; i>=0; i--){
    		if(orders.get(i).round!=round){
    			round=orders.get(i).round;
    			OrdersState ord = new OrdersState();
    			ord.roundmark=true;
    			ord.round=round;
    			ordersToDisplay.add(ord);
    		} 
    		
    		ordersToDisplay.add(orders.get(i));
    		
    	}
    
    }
}
