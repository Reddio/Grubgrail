package com.redd.sean.grubgrail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;

public class MainActivity extends AppCompatActivity implements OnConnectionFailedListener{

    private ArrayList<String> al;
    private ArrayAdapter<String> arrayAdapter;
    private int i;

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
}
