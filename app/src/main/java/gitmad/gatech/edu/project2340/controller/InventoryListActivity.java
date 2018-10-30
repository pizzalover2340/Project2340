package gitmad.gatech.edu.project2340.controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import gitmad.gatech.edu.project2340.Model.Donation;
import gitmad.gatech.edu.project2340.Model.Model;
import gitmad.gatech.edu.project2340.R;

import java.util.List;

/**
 * An activity representing a list of Locations. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link LocationDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 *
 * This is using a RecyclerView, which is the preferred standard for displaying
 * lists of things like our courses.
 */
public class InventoryListActivity extends AppCompatActivity {
    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.  For 2340, this is optional, since multi-display support is extra credit.
     */
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_list);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        //Step 1.  Setup the recycler view by getting it from our layout in the main window
        View recyclerView = findViewById(R.id.donation_list);
        assert recyclerView != null;
        //Step 2.  Hook up the adapter to the view
        setupRecyclerView((RecyclerView) recyclerView);


        //this is only needed if you are doing an optional support for multiple display sizes
        /*if (findViewById(R.id.location_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }*/
    }

    /**
     * Set up an adapter and hook it to the provided view
     * @param recyclerView  the view that needs this adapter
     */
    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        Model model = Model.getInstance();
        recyclerView.setAdapter(new SimpleLocationDataRecyclerViewAdapter(model.getDonations()));
    }

    /**
     * This inner class is our custom adapter.  It takes our basic model information and
     * converts it to the correct layout for this view.
     *
     * In this case, we are just mapping the toString of the Location Data object to a text field.
     */
    public class SimpleLocationDataRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleLocationDataRecyclerViewAdapter.ViewHolder> {

        /**
         * Collection of the items to be shown in this list.
         */
        private final List<Donation> mDonations;

        /**
         * set the items to be used by the adapter
         * @param items the list of items to be displayed in the recycler view
         */
        public SimpleLocationDataRecyclerViewAdapter(List<Donation> items) {
            mDonations = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            /*

              This sets up the view for each individual item in the recycler display
              To edit the actual layout, we would look at: res/layout/location_list_content.xml
              If you look at the example file, you will see it currently just 2 TextView elements
             */
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.donation_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {

            final Model model = Model.getInstance();
            /*
            This is where we have to bind each data element in the list (given by position parameter)
            to an element in the view (which is one of our two TextView widgets
             */
            //start by getting the element at the correct position
            holder.mDonations = mDonations.get(position);
            /*
              Now we bind the data to the widgets.  In this case, pretty simple, put the key in one
              textview and the string rep of a location in the other.
             */
            holder.mPriceView.setText("" + mDonations.get(position).getPrice());
            holder.mDescriptionView.setText(mDonations.get(position).toString());

            /*
             * set up a listener to handle if the user clicks on this list item, what should happen?
             */
            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /*if (mTwoPane) {
                        //if a two pane window, we change the contents on the main screen
                        Bundle arguments = new Bundle();
                        arguments.putInt(LocationDetailFragment.ARG_LOCATION_DATA_KEY, holder.mDonations.getKey());

                        LocationDetailFragment fragment = new LocationDetailFragment();
                        fragment.setArguments(arguments);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.location_detail_container, fragment)
                                .commit();
                    } else {*/
                        //on a phone, we need to change windows to the detail view
                        Context context = v.getContext();
                        //create our new intent with the new screen (activity)
                        //TODO:Intent intent = new Intent(context, LocationDetailActivity.class);
                        /*
                            pass along the key of the course so we can retrieve the correct data in
                            the next window
                         */
                        //TODO:intent.putExtra(LocationDetailFragment.ARG_LOCATION_DATA_KEY, holder.mDonations.getLocation());

                        //TODO:model.setCurrentLocationData(holder.mDonations);

                        //now just display the new window
                        //TODO:context.startActivity(intent);
                    //}
                }
            });
        }

        @Override
        public int getItemCount() {
            return mDonations.size();
        }

        /**
         * This inner class represents a ViewHolder which provides us a way to cache information
         * about the binding between the model element (in this case a Location) and the widgets in
         * the list view (in this case the two TextView)
         */

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final TextView mPriceView;
            public final TextView mDescriptionView;
            public Donation mDonations;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mPriceView = (TextView) view.findViewById(R.id.price);
                mDescriptionView = (TextView) view.findViewById(R.id.description);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + mDescriptionView.getText() + "'";
            }
        }
    }
}
