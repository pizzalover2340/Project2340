package gitmad.gatech.edu.project2340.controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.File;
import java.util.List;

import gitmad.gatech.edu.project2340.Model.Donation;
import gitmad.gatech.edu.project2340.Model.UserManagementFacade;
import gitmad.gatech.edu.project2340.R;

public class DonationListActivity extends AppCompatActivity {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    SimpleItemRecyclerViewAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation_list);

        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());*/

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewDonation();
            }
        });

        View recyclerView = findViewById(R.id.donation_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);

        if (findViewById(R.id.donation_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }
    }

    /* grab the resume state so we can reshow the data in the list since we most likely just came from
       adding a new student.
     */
    @Override
    public void onResume() {
        super.onResume();
        myAdapter.notifyDataSetChanged();

    }

    /* Next two methods handle the menu options */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        UserManagementFacade umf = UserManagementFacade.getInstance();
        File file;
        /**
         * I know switch statements are usually bad, but there in this case it is required
         * by the api design.
         *
         * We just use the item id to decide which menu item was selected.
         */
        switch(item.getItemId()) {
            case R.id.load_binary:
                //create a file object in the local files section
                file = new File(this.getFilesDir(), UserManagementFacade.DEFAULT_BINARY_FILE_NAME);
                //Log.d("MY APP", "Loading Binary Data");
                umf.loadBinary(file);
                //reset adapter to new data that has come in.
                myAdapter.updateList(umf.getDonationsAsList());
                //Log.d("MY APP", "New Adaptor set");
                return true;
            case R.id.load_text:
                file = new File(this.getFilesDir(), UserManagementFacade.DEFAULT_TEXT_FILE_NAME);
                umf.loadText(file);
                myAdapter.notifyDataSetChanged();
                return true;
            case R.id.load_json:
                file = new File(this.getFilesDir(), UserManagementFacade.DEFAULT_JSON_FILE_NAME);
                umf.loadJson(file);
                myAdapter.updateList(umf.getDonationsAsList());
                return true;
            case R.id.save_binary:
                file = new File(this.getFilesDir(), UserManagementFacade.DEFAULT_BINARY_FILE_NAME);
                return umf.saveBinary(file);
            case R.id.save_json:
                file = new File(this.getFilesDir(), UserManagementFacade.DEFAULT_JSON_FILE_NAME);
                return umf.saveJson(file);
            case R.id.save_text:
                file = new File(this.getFilesDir(), UserManagementFacade.DEFAULT_TEXT_FILE_NAME);
                return umf.saveText(file);
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    /**
     * Method called from floating action button handler
     */
    private void addNewDonation() {
        Intent i = new Intent(this, DonateActivity.class);
        startActivity(i);
    }

    /**
     * Set up the list to show the students
     *
     * @param recyclerView
     */
    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        UserManagementFacade umf = UserManagementFacade.getInstance();

        myAdapter = new SimpleItemRecyclerViewAdapter(umf.getDonationsAsList());

        recyclerView.setAdapter(myAdapter);
    }

    public class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private List<Donation> mValues;

        public SimpleItemRecyclerViewAdapter(List<Donation> items) {
            mValues = items;
        }

        public void updateList(List<Donation> newdata) {
            mValues = newdata;
            notifyDataSetChanged();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.donation_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mItem = mValues.get(position);
            holder.mIdView.setText(mValues.get(position).getFullDescription());
            holder.mContentView.setText(mValues.get(position).getPrice());

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mTwoPane) {
                        Bundle arguments = new Bundle();
                        arguments.putString(DonationDetailFragment.ARG_DONATION_DATA_TIMESTAMP, holder.mItem.getFullDescription());
                        DonationDetailFragment fragment = new DonationDetailFragment();
                        fragment.setArguments(arguments);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.donation_detail_container, fragment)
                                .commit();
                    } else {
                        Log.d("MY APPLICATION", holder.mItem.toString());
                        Context context = v.getContext();
                        Intent intent = new Intent(context, DonateActivity.class);
                        intent.putExtra(DonationDetailFragment.ARG_DONATION_DATA_TIMESTAMP, holder.mItem.getFullDescription());

                        context.startActivity(intent);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final TextView mIdView;
            public final TextView mContentView;
            public Donation mItem;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mIdView = (TextView) view.findViewById(R.id.price);
                mContentView = (TextView) view.findViewById(R.id.description);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + mContentView.getText() + "'";
            }
        }
    }
}

