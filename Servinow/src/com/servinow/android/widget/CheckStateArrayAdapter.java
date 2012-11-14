package com.servinow.android.widget;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import android.app.Activity;
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
import com.servinow.android.domain.Estado;
import com.servinow.android.domain.OrdersState;
import com.servinow.android.widget.PurchasedItemAdapter.ViewHolder;

public class CheckStateArrayAdapter extends ArrayAdapter<OrdersState> {

	private final Context context;
	private final ArrayList<OrdersState> orders;
//	private final String[] platos;
//	public Boolean res = false;
	
	
	static class ViewHolder {
		    TextView name;
		    TextView state;
		    ImageView image;
		    TextView ronda;
	}
	
	public CheckStateArrayAdapter(Context context, ArrayList<OrdersState> orders) {
		super(context, -1, orders);
		this.context = context;
		this.orders = orders;
	//	this.platos = platos;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO insertar im‡genes
		View rowView = convertView;
		ViewHolder holder=new ViewHolder();;
		
		LayoutInflater inflater = null;
		
		OrdersState ord = orders.get(position);
	//	if(rowView==null){
	//		inflater = (LayoutInflater) context
	//			.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			inflater = ((Activity)getContext()).getLayoutInflater();	
		/*	if(!ord.roundmark){
				rowView = inflater.inflate(R.layout.list_check_state, parent, false);
			  
			}else{
				rowView = inflater.inflate(R.layout.list_check_state_round, parent, false);
			}
			holder.name = (TextView) rowView.findViewById(R.id.TextViewCheckStateName);
			holder.state = (TextView) rowView.findViewById(R.id.TextViewCheckStateState);
			holder.image = (ImageView) rowView.findViewById(R.id.ImageViewCheckState);
			holder.ronda = (TextView) rowView.findViewById(R.id.TextViewCheckStateRound);
			rowView.setTag(holder);
		}else{
			holder = (ViewHolder) rowView.getTag();*/
//		}
		
		
		if(!ord.roundmark){
			rowView = inflater.inflate(R.layout.list_check_state, parent, false);
			
			TextView TVName = (TextView) rowView.findViewById(R.id.TextViewCheckStateName);
			TextView TVState = (TextView) rowView.findViewById(R.id.TextViewCheckStateState);
			ImageView imageView = (ImageView) rowView.findViewById(R.id.ImageViewCheckState);
			
		
			TVName.setText(ord.name);
			
			
			if(ord.state == Estado.EN_COLA){
				TVState.setTextColor(Color.argb(255, 255, 0, 0));
				TVState.setText(R.string.checkstateactivity_encola);
			}
			else if(ord.state == Estado.PREPARANDO){
				TVState.setTextColor(Color.argb(255, 187, 187, 0));
				TVState.setText(R.string.checkstateactivity_encocina);
			}
			else if(ord.state == Estado.LISTO){
				TVState.setTextColor(Color.argb(255, 66, 204, 68));
				TVState.setText(R.string.checkstateactivity_preparado);
			}else{
				TVState.setText(R.string.checkstateactivity_servido);
			}
			
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
			
			TextView tv = (TextView) rowView.findViewById(R.id.TextViewCheckStateRound);
			tv.setText(" - Ronda "+ord.round+" - ");
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


