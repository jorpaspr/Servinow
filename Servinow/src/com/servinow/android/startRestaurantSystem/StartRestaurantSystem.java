package com.servinow.android.startRestaurantSystem;

import com.servinow.android.QRSystem.QRResultCallback;

public class StartRestaurantSystem {

	private int restaurantID;
	private int placeID;
	private QRResultCallback callbacks;

	public StartRestaurantSystem(int restaurantID, int placeID, QRResultCallback callbacks) {
		this.restaurantID = restaurantID;
		this.placeID = placeID;
		this.callbacks = callbacks;
	}
	
	public void start(){
		//TODO
	}
	
	public void stop(){
		//TODO
	}
}
