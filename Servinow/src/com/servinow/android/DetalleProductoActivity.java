package com.servinow.android;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;
import com.servinow.android.dao.ProductCache;
import com.servinow.android.domain.Producto;

public class DetalleProductoActivity extends SherlockActivity {

	private int productoID;
	private int categoriaID;
	private int restaurantID;
	private int placeID;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_producto);
        
        Bundle extras = getIntent().getExtras();
		if(extras != null && extras.getBoolean(ProductosActivity.PARAM.GOTOPRODUCTO.toString(), false))
		{
			restaurantID = extras.getInt(ProductosActivity.PARAM.RESTAURANT.toString());
			placeID = extras.getInt(ProductosActivity.PARAM.PLACE.toString());
			categoriaID = extras.getInt(ProductosActivity.PARAM.CATEGORIA.toString());
			productoID = extras.getInt(ProductosActivity.PARAM.PRODUCTO.toString());
			
			Producto producto = new ProductCache(this).getProducto(productoID);
	        Resources res = getResources();
			//TODO obtener las imágenes a partir de su URL
			producto.setImagen(res.getDrawable(R.drawable.meal));

	        ImageView imageView = (ImageView) findViewById(R.id.imageView);
			imageView.setImageDrawable(producto.getImagen());
			
			TextView textViewPrecio = (TextView) findViewById(R.id.textViewPrecio);
			textViewPrecio.setText(String.valueOf(producto.getPrecio()) + " €");
			
			TextView textViewDisponible = (TextView) findViewById(R.id.textViewDisponibilidad);
			String disponibilidad = producto.isStock()?"Disponible":"No disponible";
			textViewDisponible.setText(disponibilidad);
			
			TextView textViewDescripcion = (TextView) findViewById(R.id.textViewDescripcion);
			textViewDescripcion.setText(producto.getDescripcion());
			
			setTitle(producto.getNombre());
		}

		final Button button = (Button) findViewById(R.id.buttonAdd);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	printToast("Se ha hecho click en el botón de añadir");
            }
        });
    }
	
    // TODO para depuración, eliminar
	private void printToast(String mensaje) {
		Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
	}
}
