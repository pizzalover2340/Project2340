package gitmad.gatech.edu.project2340.controller;


import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import gitmad.gatech.edu.project2340.Model.Location;
import gitmad.gatech.edu.project2340.Model.LocationData;
import gitmad.gatech.edu.project2340.Model.Model;
import gitmad.gatech.edu.project2340.R;


/**
 * A fragment representing a single Location detail screen.
 *
 * Basically this displays a list of location data that is in a particular location
 * that was selected from the main screen.
 *
 * This fragment is either contained in a {@link LocationListActivity}
 * in two-pane mode (on tablets) or a {@link LocationDetailActivity}
 * on handsets.
 */
public class LocationDetailFragment extends Fragment {
    /**
     * The fragment arguments representing the key's that this fragment
     * represents.  Used to pass keys into other activities through Bundle/Intent
     */
    public static final String ARG_LOCATION_DATA_KEY = "location_data_key";
    //public static final String ARG_STUDENT_ID = "student_id";

    /**
     * The location that this detail view is for.
     */
    private LocationData mLocationData;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public LocationDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Check if we got a valid location passed to us
        if (getArguments().containsKey(ARG_LOCATION_DATA_KEY)) {
            // Get the key from the intent arguments (bundle) and
            //ask the model to give us the location object
            Model model = Model.getInstance();
            mLocationData = model.getLocationDataByNumber(getArguments().getInt(ARG_LOCATION_DATA_KEY));
            //mLocationData = model.getCurrentLocationData();
            Log.d("LocationDetailFragment", "Passing over location: " + mLocationData);
            //Log.d("LocationDetailFragment", "Got location data: " + mLocationData.getLocationData().size());

            Activity activity = this.getActivity();

            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mLocationData.toString());
            }
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.location_data_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (mLocationData != null) {
            TextView keyView = (TextView) rootView.findViewById(R.id.keyDetailLabel);
            Log.d("MyAPP", "Location Data" + mLocationData);
            Log.d("MyAPP", "Key View" + keyView);
            keyView.setText("" + mLocationData.getKey());

            TextView nameView = (TextView) rootView.findViewById(R.id.nameDetailLabel);
            nameView.setText(mLocationData.getName());

            TextView latitudeView = (TextView) rootView.findViewById(R.id.latitudeDetailLabel);
            latitudeView.setText("" + mLocationData.getLatitude());

            TextView longitudeView = (TextView) rootView.findViewById(R.id.longitudeDetailLabel);
            longitudeView.setText("" + mLocationData.getLongitude());

            TextView streetAddressView = (TextView) rootView.findViewById(R.id.streetAddressDetailLabel);
            streetAddressView.setText(mLocationData.getStreetAddress());

            TextView cityView = (TextView) rootView.findViewById(R.id.cityDetailLabel);
            cityView.setText(mLocationData.getCity());

            TextView stateView = (TextView) rootView.findViewById(R.id.stateDetailLabel);
            stateView.setText(mLocationData.getState());

            TextView zipcodeView = (TextView) rootView.findViewById(R.id.zipcodeDetailLabel);
            zipcodeView.setText("" + mLocationData.getZipcode());

            TextView typeView = (TextView) rootView.findViewById(R.id.typeDetailLabel);
            typeView.setText(mLocationData.getType());

            TextView phoneNumberView = (TextView) rootView.findViewById(R.id.phoneNumberDetailLabel);
            phoneNumberView.setText(mLocationData.getPhoneNumber());

            TextView websiteView = (TextView) rootView.findViewById(R.id.websiteDetailLabel);
            websiteView.setText(mLocationData.getWebsite());
        }

        return rootView;
    }

    /*@Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }*/
}
