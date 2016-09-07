package com.redd.sean.grubgrail.objects;

/*
	Custom object to hold all the information from the places API into
	an arrayList of data. Each index will be one restaurant and all
	of its associated data.
 */

import java.util.ArrayList;

public class RestaurantObject {
	String Name;
	String Location;
	String PhotoURL;

	public RestaurantObject(String name, String location, String photourl) {
		this.Name      = name;
		this.Location  = location;
		this.PhotoURL  = photourl;
	}

	ArrayList<RestaurantObject> restaurantAL = new ArrayList<RestaurantObject>();

}
