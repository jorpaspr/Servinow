package com.servinow.android.synchronization;

public class ServinowApi_GetRestaurant extends ServinowApi {

	public ServinowApi_GetRestaurant(int restaurantID, int placeID) {
		super("/getRestaurant.php?restaurantid="+restaurantID+"&placeid="+placeID);
	}
}