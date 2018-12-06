package gitmad.gatech.edu.project2340.controller;


import android.app.Activity;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import gitmad.gatech.edu.project2340.Model.Donation;
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
public class DonationDetailFragment extends Fragment {
    /**
     * The fragment arguments representing the key's that this fragment
     * represents.  Used to pass keys into other activities through Bundle/Intent
     */
    public static final String ARG_DONATION_DATA_TIMESTAMP = "donation_data_timestamp";

    /**
     * The location that this detail view is for.
     */
    private Donation mDonation;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public DonationDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Check if we got a valid location passed to us
        if (getArguments().containsKey(ARG_DONATION_DATA_TIMESTAMP)) {
            // Get the key from the intent arguments (bundle) and
            //ask the model to give us the location object
            Model model = Model.getInstance();
            mDonation = model.getDonationByTimestamp(getArguments().getInt(ARG_DONATION_DATA_TIMESTAMP));
            //mLocationData = model.getCurrentLocationData();
            //Log.d("LocationDetailFragment", "Passing over location: " + mDonation);
            //Log.d("LocationDetailFragment", "Got location data: " + mLocationData.getLocationData().size());

            Activity activity = this.getActivity();

            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mDonation.toString());
            }
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.donation_detail_fragment, container, false);

        // Show the dummy content as text in a TextView.
        if (mDonation != null) {
            TextView keyView = (TextView) rootView.findViewById(R.id.timestampDetailLabel);
            //Log.d("MyAPP", "Location Data" + mDonation);
            //Log.d("MyAPP", "Key View" + keyView);
            keyView.setText("" + mDonation.getTimestamp());

            TextView descriptionView = (TextView) rootView.findViewById(R.id.descriptionDetailLabel);
            descriptionView.setText(mDonation.getFullDescription());

            TextView priceView = (TextView) rootView.findViewById(R.id.priceDetailLabel);
            priceView.setText("" + mDonation.getPrice());

            TextView locationView = (TextView) rootView.findViewById(R.id.locationDetailLabel);
            locationView.setText("" + mDonation.getLocation());

            TextView categoryView = (TextView) rootView.findViewById(R.id.categoryDetailLabel);
            categoryView.setText("category");

            TextView commentView = (TextView) rootView.findViewById(R.id.commentDetailLabel);
            commentView.setText(mDonation.getComment());

        }

        return rootView;
    }

    /*@Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }*/
}
