package com.redd.sean.grubgrail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.lorentzos.flingswipe.SwipeFlingAdapterView;
import com.redd.sean.grubgrail.retrievers.CoordinateRetrieverActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

	private ArrayList<String> al;
    private ArrayAdapter<String> arrayAdapter;
    private int i;

	// Data to make the JSON query, in order of input
	private static String APIkey = "AIzaSyDnpvuf4npzz8DO1QTZI3QZU4JcTtJAa2Y";
	double longitude, latitude;
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

	    getCoordinates();

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

	public void getCoordinates() {
		Intent intent = new Intent(this, CoordinateRetrieverActivity.class);
		startActivityForResult(intent, 1);
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode == 1){
			Bundle extras = data.getExtras();
			longitude = extras.getDouble("Longitude");
			latitude = extras.getDouble("Latitude");
		}
	}

	public void getNearby(double x, double y) {

		/*
		//	Send all relevant information to new class to conduct search
		//
		//	Put all relevant information in custom ArrayList object and send back
		//		to MainActivity for use as cards.
		*/


	}
}
