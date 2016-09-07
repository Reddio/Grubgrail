package com.redd.sean.grubgrail;

import android.content.Context;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity
		implements LocationListener, GpsStatus.Listener{

	private ArrayList<String> al;
    private ArrayAdapter<String> arrayAdapter;
    private int i;


	// GPS Data
	private LocationManager mService;
	private GpsStatus mStatus;

	// User location data
	double latitude;
	double longitude;

	// Data to make the JSON query, in order of input
	private static String APIkey = "AIzaSyDnpvuf4npzz8DO1QTZI3QZU4JcTtJAa2Y";
	private static int RADIUS = 10; // miles
	private static String locationType = "restaurant";


	@BindView(R.id.frame) SwipeFlingAdapterView flingContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		ButterKnife.bind(this);

        al = new ArrayList<String>();
        arrayAdapter = new ArrayAdapter<String>(this, R.layout.item, R.id.helloText, al);

	    // SET THE LISTENERS FOR SWIPING

        flingContainer.setAdapter(arrayAdapter);
	    flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
		    @Override
		    public void removeFirstObjectInAdapter() {
			    // Remove object from stack
				al.remove(0);
			    arrayAdapter.notifyDataSetChanged();
		    }

		    @Override
		    public void onLeftCardExit(Object o) {
				//TODO
		    }

		    @Override
		    public void onRightCardExit(Object o) {
				//TODO: Add to Grails (volatile? sort by location?)
		    }

		    @Override
		    public void onAdapterAboutToEmpty(int i) {
				//TODO: Loop array
		    }

		    @Override
		    public void onScroll(float v) {
/*			    View view = flingContainer.getSelectedView();
			    view.findViewById(R.id.item_swipe_right_indicator)
					    .setAlpha(scrollProgressPercent < 0 ? -scrollProgressPercent : 0);
			    view.findViewById(R.id.item_swipe_left_indicator)
					    .setAlpha(scrollProgressPercent > 0 ? scrollProgressPercent : 0);*/
		    }
	    });

	    Toast.makeText(MainActivity.this, "Location:"+latitude+","+longitude, Toast.LENGTH_SHORT).show();

    }

	// Button clicks right / left
	@OnClick(R.id.right)
	public void right() {
		flingContainer.getTopCardListener().selectRight();
	}

	@OnClick(R.id.left)
	public void left() {
		flingContainer.getTopCardListener().selectLeft();
	}


//--------------------------------------------------------------------------------//
/*--------------------------- DATA FROM SEPARATE CLASS ---------------------------*/


	LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
	Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);

	public static void getNearby() {

	}

	private final LocationListener locationListener = new LocationListener() {
		@Override
		public void onLocationChanged(Location location) {
			longitude = location.getLongitude();
			latitude = location.getLatitude();
		}

		@Override
		public void onProviderDisabled(String provider) {}

		@Override
		public void onProviderEnabled(String provider) {}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras){}
	};

	@Override
	public void onLocationChanged(Location location) {
		longitude = location.getLongitude();
		latitude = location.getLatitude();
	}

	@Override
	public void onProviderDisabled(String provider) {}

	@Override
	public void onProviderEnabled(String provider) {}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras){}

	//lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 10, locationListener);

	public void onGpsStatusChanged(int event) {
		mStatus = mService.getGpsStatus(mStatus);
		switch (event) {
			case GpsStatus.GPS_EVENT_STARTED:
				// Do Something with mStatus info
				break;

			case GpsStatus.GPS_EVENT_STOPPED:
				// Do Something with mStatus info
				break;

			case GpsStatus.GPS_EVENT_FIRST_FIX:
				// Do Something with mStatus info
				break;

			case GpsStatus.GPS_EVENT_SATELLITE_STATUS:
				// Do Something with mStatus info
				break;
		}

	}


/*------------------------- DATA FROM SEPARATE CLASS END -------------------------*/
//--------------------------------------------------------------------------------//


}
