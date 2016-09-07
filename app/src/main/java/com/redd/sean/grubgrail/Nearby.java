package com.redd.sean.grubgrail;

import android.app.Service;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import java.util.ArrayList;

public class Nearby extends Service implements LocationListener{

	private final Context mContext;
	protected LocationManager locationManager;

	// User location data
	Location location;
	double latitude;
	double longitude;

	private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // 10 meters
	private static final long MIN_TIME_BETWEEN_UPDATES = 1000 * 60 * 1; // 1 minute

	// Flags to get location
	boolean isGPSEnabled = false;
	boolean isNetworkEnables = false;
	boolean canGetLocation = false;

	// Data to make the JSON query, in order of input
	private static String APIkey = "AIzaSyDnpvuf4npzz8DO1QTZI3QZU4JcTtJAa2Y";
	private static int RADIUS = 10; // miles
		//LOCATION
	private static String locationType = "restaurant";


	public getNearby(Context context) {
		this.mContext = context;
		getLocation();
	}

	public Location getLocation() {
		try {
			locationManager = (LocationManager) mContext.getSystemService(LOCATION_SERVICE);

			// Get GPS status
			isGPSEnabled = locationManager.isProviderEnabled(LOCATION_SERVICE.GPS_PROVIDER);
		}
	}
}
