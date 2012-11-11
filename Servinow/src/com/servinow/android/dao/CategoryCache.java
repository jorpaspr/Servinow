package com.servinow.android.dao;

import java.util.HashMap;
import java.util.List;

import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.servinow.android.domain.Categoria;
import com.servinow.android.domain.Restaurant;

import android.content.Context;

public class CategoryCache extends ServinowDAOBase {

	public CategoryCache(Context context) {
		super(context);
	}
	
	public List<Categoria> getCategories(Restaurant restaurant){
		RuntimeExceptionDao<Categoria, Integer> categoryDAO = servinowDatabase.getRuntimeExceptionDao(Categoria.class);
		
		HashMap<String, Object> sqlFieldsToMatch = new HashMap<String, Object>();
		sqlFieldsToMatch.put(Categoria.FIELDNAME.restaurant.toString(), restaurant);
		List<Categoria> categoryList = categoryDAO.queryForFieldValues(sqlFieldsToMatch);
		
		return categoryList;
	}

}
