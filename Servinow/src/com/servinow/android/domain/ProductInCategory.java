package com.servinow.android.domain;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "productInCategory")
public class ProductInCategory {
	
	@DatabaseField(id = true)
	String id;
	
	@DatabaseField(foreign = true, canBeNull = false, foreignAutoRefresh = true)
	private Producto product;
	
	@DatabaseField(foreign = true, canBeNull = false, foreignAutoRefresh = true)
	private Categoria category;

	private ProductInCategory() {
	}

	public ProductInCategory(Producto product, Categoria category) {
		this.product = product;
		this.category = category;
		this.id = category.getId()+"_"+product.getId();
	}

	public Producto getProduct() {
		return product;
	}

	public Categoria getCategory() {
		return category;
	}
}
