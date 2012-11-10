package com.servinow.android.domain;

import java.util.Collection;

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
	private Collection<Place> places;


	public int getOnlineID() {
		return onlineID;
	}
	public long getLastUpdate() {
		return lastUpdate;
	}
	public String getName() {
		return name;
	}
	public Collection<Place> getPlaces() {
		return places;
	}
	
	Restaurant() {
	}
}
