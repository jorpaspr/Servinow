package com.servinow.android;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.servinow.android.dao.CategoryCache;
import com.servinow.android.dao.RestaurantCache;
import com.servinow.android.domain.Categoria;
import com.servinow.android.domain.Restaurant;
import com.servinow.android.widget.CategoriaAdapter;

public class CategoriasActivity extends SherlockActivity {

	public static enum PARAM {
		GOTOCATEGORIA,
		RESTAURANT,
		PLACE,
		CATEGORIA;
	}
	
	private Categoria [] categorias = new Categoria[0];
	private int restaurantID;
	private int placeID;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
		Bundle extras = getIntent().getExtras();
		if(extras != null && extras.getBoolean(QRReading.PARAM.GOTORESTAURANT.toString(), false))
		{
			restaurantID = extras.getInt(QRReading.PARAM.RESTAURANT.toString());
			placeID = extras.getInt(QRReading.PARAM.PLACE.toString());
			Restaurant restaurant = new RestaurantCache(this).
					getRestaurantFromCache(restaurantID);
			List<Categoria> listaCategorias = new CategoryCache(this).
					getCategories(restaurant);
			
			// TODO cambiar a List en el adapter
			categorias = new Categoria [listaCategorias.size()];
			listaCategorias.toArray(categorias);
			setTitle(restaurant.getName());
		}

		//TODO obtener las imágenes a partir de su URL
        Resources res = getResources();		
		for (Categoria categoria: categorias)
			categoria.setImagen(res.getDrawable(R.drawable.meal));

        String uriString = "http://www.upv.es/noticias-upv/imagenes/crue.png";
        Uri uri = Uri.parse(uriString);
		try {
			InputStream is = getContentResolver().openInputStream(uri);
			Drawable.createFromStream(is, uri.getPath());
		} catch (FileNotFoundException e) {
			Log.d(FileNotFoundException.class.getSimpleName(), "Error al crear el InputStream");
			e.printStackTrace();
		}

        setContentView(R.layout.activity_categorias);
        
        GridView gridView = (GridView) findViewById(R.id.GridViewCategorias);
        CategoriaAdapter adapter = new CategoriaAdapter(this, R.layout.item_categoria, categorias);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(itemClickListener);
    }

    private OnItemClickListener itemClickListener = new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			int categoriaID = categorias[arg2].getId();

			Intent i = new Intent(CategoriasActivity.this, ProductosActivity.class);
			Bundle b = new Bundle();
			b.putBoolean(CategoriasActivity.PARAM.GOTOCATEGORIA.toString(), true);
			b.putInt(CategoriasActivity.PARAM.RESTAURANT.toString(), restaurantID);
			b.putInt(CategoriasActivity.PARAM.PLACE.toString(), placeID);
			b.putInt(CategoriasActivity.PARAM.CATEGORIA.toString(), categoriaID);
			i.putExtras(b);

			startActivity(i);
			finish();
		}
    };
}
