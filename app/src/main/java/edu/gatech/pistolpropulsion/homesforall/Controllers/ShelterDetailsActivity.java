package edu.gatech.pistolpropulsion.homesforall.Controllers;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import edu.gatech.pistolpropulsion.homesforall.Models.Shelter;
import edu.gatech.pistolpropulsion.homesforall.R;

public class ShelterDetailsActivity extends Activity {

    private Shelter currentShelter;
    private TextView nameDisplay;
    private TextView addressDisplay;
    private TextView phoneDisplay;
    private TextView capacityDisplay;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelter_details);

        currentShelter = (Shelter) getIntent().getSerializableExtra("name");
        nameDisplay = (TextView) findViewById(R.id.name_textview);
        addressDisplay = (TextView) findViewById(R.id.address_textview);
        phoneDisplay = (TextView) findViewById(R.id.phone_textview);
        capacityDisplay = (TextView) findViewById(R.id.capacity_textview);

        nameDisplay.setText(currentShelter.getName());
        addressDisplay.setText("Address: " + currentShelter.getAddress());
        phoneDisplay.setText("Phone Number: " + currentShelter.getPhone());
        capacityDisplay.setText("Capacity: " + currentShelter.getCapacity());

    }
}
