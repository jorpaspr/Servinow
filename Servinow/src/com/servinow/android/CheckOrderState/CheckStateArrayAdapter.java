package com.servinow.android.CheckOrderState;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.servinow.android.R;

public class CheckStateArrayAdapter extends ArrayAdapter<OrdersState> {

	private final Context context;
	private final ArrayList<OrdersState> orders;
//	private final String[] platos;
//	public Boolean res = false;
	
	
	public CheckStateArrayAdapter(Context context, ArrayList<OrdersState> orders) {
		super(context, -1, orders);
		this.context = context;
		this.orders = orders;
	//	this.platos = platos;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO insertar im‡genes
		
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		
		
		OrdersState ord = orders.get(position);
		View rowView;
		
		if(!ord.roundmark){
		
			rowView = inflater.inflate(R.layout.list_check_state, parent, false);
		
			TextView TVName = (TextView) rowView.findViewById(R.id.TextViewCheckStateName);
			TextView TVState = (TextView) rowView.findViewById(R.id.TextViewCheckStateState);
			ImageView imageView = (ImageView) rowView.findViewById(R.id.ImageViewCheckState);
			
			
			
		
			TVName.setText(ord.name);
			TVState.setText(ord.state);
			
			if(ord.state.equals(R.string.checkstateactivity_encola))
				TVState.setTextColor(Color.argb(255, 255, 0, 0));
			else if(ord.state.equals(R.string.checkstateactivity_encocina))
				TVState.setTextColor(Color.argb(255, 187, 187, 0));
			else if(ord.state.equals(R.string.checkstateactivity_preparado))
				TVState.setTextColor(Color.argb(255, 66, 204, 68));
			
		//	imageView.setImageResource(R.drawable.arroz);
			imageView.setImageBitmap(ord.image);
			
			
			ImageButton deleteButton = (ImageButton) rowView.findViewById(R.id.CheckState_row_Cancel);
	        deleteButton.setTag(position);

	        deleteButton.setOnClickListener(
	           new Button.OnClickListener() {
	               @Override
	               public void onClick(View v) {
	            	   callDialog(context,v);
	            		
	               }
	           }
	       );
			
		}
		else{
			rowView = inflater.inflate(R.layout.list_check_state_round, parent, false);
			
			TextView TVRound = (TextView) rowView.findViewById(R.id.TextViewCheckStateRound);
			
			TVRound.setText(" - Ronda "+ord.round+" - ");
			Log.d("+++++","++++");
		}
		
		
		
	//	Log.d("+++++","++++");
		return rowView;
	}
	
	
	public void callDialog(Context ctx, View v){
		final View vv = v;
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
 	   	
 	   
		
		builder.setMessage(ctx.getString(R.string.checkstateactivity_cancelar_elem));
		builder.setPositiveButton((ctx.getString(R.string.checkstateactivity_si)), new DialogInterface.OnClickListener() {						
			
			public void onClick(DialogInterface arg0, int arg1) {
				Integer index = (Integer) vv.getTag();
     		   	orders.remove(index.intValue());  
     		   	notifyDataSetChanged();
     		   	deleteInDB();
			}						
		});
		builder.setNegativeButton(ctx.getString(R.string.checkstateactivity_no), new DialogInterface.OnClickListener() {						
			
			public void onClick(DialogInterface arg0, int arg1){
				
			}
		});
		
		AlertDialog alert = builder.create();
		alert.show();
		
	}
	
	public void deleteInDB(){
		//TODO BORRAR EN LA BASE DE DATOS
	}
	
	
}


