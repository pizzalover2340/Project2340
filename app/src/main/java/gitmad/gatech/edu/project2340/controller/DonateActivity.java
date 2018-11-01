package gitmad.gatech.edu.project2340.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import gitmad.gatech.edu.project2340.Model.LocationData;
import gitmad.gatech.edu.project2340.Model.Donation;
import gitmad.gatech.edu.project2340.Model.Model;
import gitmad.gatech.edu.project2340.R;


public class DonateActivity extends AppCompatActivity {//} implements AdapterView.OnItemSelectedListener {

    /* ************************
        Widgets we will need for binding and getting information
     */
    private TextView timestampField;
    private Spinner locSpinner;
    private EditText fDescField;
    private EditText sDescField;
    private EditText priceField;
    private Spinner catSpinner;
    private EditText commentField;
    private Button donateButton;

    /* ************************
       Keeping track of spinner changes.  Not really used right now, probably don't need this.
     */
    //private String _major = "NA";

    /* ***********************
       Data for student being edited.
     */
    private Donation _donation;

    // editing flag
    private boolean editing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.donate_activity);

        /**
         * Grab the dialog widgets so we can get info for later
         */
        timestampField = (TextView) findViewById(R.id.timestamp_field);
        locSpinner = (Spinner) findViewById(R.id.loc_spinner);
        sDescField = (EditText) findViewById(R.id.short_description_input);
        fDescField = (EditText) findViewById(R.id.full_description_input);
        priceField = (EditText) findViewById(R.id.price_field);
        catSpinner = (Spinner) findViewById(R.id.cat_spinner);
        commentField = (EditText) findViewById(R.id.comment_field);
        donateButton = (Button) findViewById(R.id.donate_button);

        /*
          Set up the adapter to display the allowable majors in the spinner
         */
        Model model = Model.getInstance();
        List<LocationData> locationData = model.getLocationData();
        List<String> locNameList = new ArrayList<>();
        for (LocationData locData : locationData) {
            locNameList.add(locData.getName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, locNameList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locSpinner.setAdapter(adapter);

        ArrayAdapter<Donation.Category> adapter2 = new ArrayAdapter<Donation.Category>(this, android.R.layout.simple_spinner_item, Donation.Category.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        catSpinner.setAdapter(adapter2);

        /*
           If a student has been passed in, this was an edit, if not, this is a new add
         */
        /*if (getIntent().hasExtra(DonationDetailFragment.ARG_DONATION_TIMESTAMP)) {
            _donation = (Donation) getIntent().getParcelableExtra(DonationDetailFragment.ARG_DONATION_TIMESTAMP);
            locSpinner.setSelection(Donation.findPosition(_donation.getLocation()));
            catSpinner.setSelection(_donation.getCategory().ordinal());
            editing = true;
        } else {*/
            _donation = new Donation();
            //editing = false;
        //}

        //fDescField.setText(_donation.getFullDescription());
        //timestampField.setText("" + _donation.getTimestamp());
        //priceField.setText(_donation.getPrice());

        final Intent locationPage = new Intent(this, LocationListActivity.class);
        donateButton.setOnClickListener((view) -> {
            _donation.setFullDescription(fDescField.getText().toString());
            _donation.setADescription(sDescField.getText().toString());
            _donation.setLocation(0); //locSpinner.getSelectedItem());
            _donation.setCategory((Donation.Category) catSpinner.getSelectedItem());
            _donation.setPrice(Integer.parseInt(priceField.getText().toString()));

            String fileName = "\\raw.donation";

            try {
                String content = _donation.getFullDescription() + ";" + _donation.getADescription()
                        + ";" + _donation.getLocation() + ";" + _donation.getCategory() + ";" +
                        _donation.getPrice();
                File file = new File(fileName +".csv");
                // if file doesnt exists, then create it
                if (!file.exists()) {
                    file.createNewFile();
                }

                FileWriter fw = new FileWriter(file.getAbsoluteFile());
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(content);
                bw.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

            model.addDonation(_donation);

            startActivity(locationPage);
        });
    }

    /**
     * Button handler for cancel
     *
     * @param view the button pressed
     */
    public void onCancelPressed(View view) {
        Log.d("Edit", "Cancel Donate");
        finish();
    }

/*    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        _major = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        _major = "NA";
    } */
}