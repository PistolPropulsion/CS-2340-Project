package edu.gatech.pistolpropulsion.homesforall.Controllers;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.widget.TextView;
import android.widget.Toast;


import edu.gatech.pistolpropulsion.homesforall.Models.Shelter;
import edu.gatech.pistolpropulsion.homesforall.R;

public class ShelterDetailsActivity extends Activity {

    private final int REQUEST_CALL = 1;
    private Intent callShelterIntent;

    private Shelter currentShelter;
    private TextView nameDisplay;
    private TextView addressDisplay;
    private TextView phoneDisplay;
    private TextView capacityDisplay;
    private TextView notesDisplay;
    private TextView restrictDisplay;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelter_details);

        currentShelter = (Shelter) getIntent().getSerializableExtra("name");
        nameDisplay = (TextView) findViewById(R.id.name_textview);
        addressDisplay = (TextView) findViewById(R.id.address_textview);
        phoneDisplay = (TextView) findViewById(R.id.phone_textview);
        capacityDisplay = (TextView) findViewById(R.id.capacity_textview);
        notesDisplay = (TextView) findViewById(R.id.special_textview);
        restrictDisplay = (TextView) findViewById(R.id.restrict_textview);

        nameDisplay.setText(currentShelter.getName());
        addressDisplay.setText("Address: " + currentShelter.getAddress());
        phoneDisplay.setText("Phone Number: " + currentShelter.getPhone());
        capacityDisplay.setText("Capacity: " + currentShelter.getCapacity());
        notesDisplay.setText("Special Notes: " + currentShelter.getSpecialNotes());
        restrictDisplay.setText("Restrictions: " + currentShelter.getRestrictions());


        phoneDisplay.setOnClickListener((v) -> {
            callShelterIntent = new Intent(Intent.ACTION_CALL);
            callShelterIntent.setData(Uri.parse("tel:" + currentShelter.getPhone()));
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                startActivity(callShelterIntent);
            } else {

                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CALL_PHONE)) {
                    Toast.makeText(this, "Phone calling permission needed to call the shelter.", Toast.LENGTH_SHORT).show();
                }

                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            }
        });
    }
}
