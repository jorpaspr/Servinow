package com.servinow.android;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockListActivity;
import com.servinow.android.dao.CategoryCache;
import com.servinow.android.dao.RestaurantCache;
import com.servinow.android.domain.Categoria;
import com.servinow.android.domain.Producto;
import com.servinow.android.domain.Restaurant;
import com.servinow.android.widget.ProductoAdapter;

public class ProductosActivity extends SherlockListActivity {
	
	private Producto [] productos = new Producto[0];
	private int categoriaID;
	private int restaurantID;
	private int placeID;

	public static enum PARAM {
		GOTOPRODUCTO,
		RESTAURANT,
		PLACE,
		CATEGORIA,
		PRODUCTO;
	}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();
		if(extras != null && extras.getBoolean(CategoriasActivity.PARAM.GOTOCATEGORIA.toString(), false))
		{
			restaurantID = extras.getInt(CategoriasActivity.PARAM.RESTAURANT.toString());
			placeID = extras.getInt(CategoriasActivity.PARAM.PLACE.toString());
			categoriaID = extras.getInt(CategoriasActivity.PARAM.CATEGORIA.toString());
			Categoria categoria = new CategoryCache(this).getCategoria(categoriaID);
			List<Producto> listaProductos = new ArrayList(categoria.getProducts());
			
			productos = new Producto [listaProductos.size()];
			listaProductos.toArray(productos);
			setTitle(categoria.getNombre());
		}

		//TODO obtener las imágenes a partir de su URL
        Resources res = getResources();		
		for (Producto producto: productos)
			producto.setImagen(res.getDrawable(R.drawable.meal));

        ProductoAdapter adapter = new ProductoAdapter(this, R.layout.item_producto, productos);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(itemClickListener);
    }

    private OnItemClickListener itemClickListener = new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			int productoID = productos[arg2].getId();

			Intent i = new Intent(ProductosActivity.this, DetalleProductoActivity.class);
			Bundle b = new Bundle();
			b.putBoolean(ProductosActivity.PARAM.GOTOPRODUCTO.toString(), true);
			b.putInt(ProductosActivity.PARAM.RESTAURANT.toString(), restaurantID);
			b.putInt(ProductosActivity.PARAM.PLACE.toString(), placeID);
			b.putInt(ProductosActivity.PARAM.CATEGORIA.toString(), categoriaID);
			b.putInt(ProductosActivity.PARAM.PRODUCTO.toString(), productoID);
			i.putExtras(b);

			startActivity(i);
		}
    };
}
