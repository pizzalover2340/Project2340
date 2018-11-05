package gitmad.gatech.edu.project2340.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import gitmad.gatech.edu.project2340.Model.Donation;
import gitmad.gatech.edu.project2340.Model.LocationData;
import gitmad.gatech.edu.project2340.Model.Model;
import gitmad.gatech.edu.project2340.R;

public class SearchActivity extends AppCompatActivity {
    private Button locationButton;
    private Spinner locationSpinner;
    private Spinner categorySpinner;
    private Button confirmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        locationButton = (Button) findViewById(R.id.locationButton);
        locationSpinner = (Spinner) findViewById(R.id.locationSpinner);
        categorySpinner = (Spinner) findViewById(R.id.categorySpinner);
        confirmButton = (Button) findViewById(R.id.confirmButton);

        InputStream inputStream = getResources().openRawResource(R.raw.locationdata);
        CVReader csv = new CVReader(inputStream);
        List<LocationData> LocationList = csv.read();

        Model model = Model.getInstance();
        for(LocationData LocationData : LocationList) {
            //add location data to the model
            model.addLocationData(LocationData);
        }

        //add all categories and locations to both the spinners
        List<LocationData> locationData = model.getLocationData();
        List<String> locNameList = new ArrayList<>();
        for (LocationData locData : locationData) {
            locNameList.add(locData.getName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, locNameList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationSpinner.setAdapter(adapter);

        ArrayAdapter<Donation.Category> adapter2 = new ArrayAdapter<Donation.Category>(this, android.R.layout.simple_spinner_item, Donation.Category.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter2);

        //display all locations
        final Intent locationPage = new Intent(this, LocationListActivity.class);
        locationButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                startActivity(new Intent(locationPage));

            }
        });

        final Intent searchPage = new Intent(this, DonationListActivity.class);
        confirmButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                /*String category = categorySpinner.getSelectedItem().toString();
                List<Donation> donationData = model.getDonations();
                List<String> donationList = new ArrayList<>();
                for (Donation donation: donationData) {
                    if (donation.getCategory().equals(category)) {
                        donationList.add(donation.getFullDescription());
                    }
                }*/

                /*String location = locationSpinner.getSelectedItem().toString();
                for (LocationData locData : locationData) {
                    if (locData.getName().equals(location)) {
                        locNameList.add(locData.getName());
                    }
                }*/

                startActivity(new Intent(searchPage));

            }
        });
    }
}