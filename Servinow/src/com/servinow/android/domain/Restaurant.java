package com.servinow.android.domain;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "restaurant")
public class Restaurant {
	
	@DatabaseField(generatedId = true)
	private int onlineID;

	@DatabaseField(canBeNull = false)
	private long lastUpdate;
	
	@DatabaseField(canBeNull = false)
	private String name;
	
	@ForeignCollectionField(eager = true) //lazy = false
	private ForeignCollection<Place> places;


	public int getOnlineID() {
		return onlineID;
	}
	public long getLastUpdate() {
		return lastUpdate;
	}
	public String getName() {
		return name;
	}
	public ForeignCollection<Place> getPlaces() {
		return places;
	}
	
	Restaurant() {
	}
}
