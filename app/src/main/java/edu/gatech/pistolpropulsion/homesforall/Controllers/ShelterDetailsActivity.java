package edu.gatech.pistolpropulsion.homesforall.Controllers;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
    private Button reserveButton;
    private TextView vacancyDisplay;
    private DatabaseReference mData;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelter_details);

        currentShelter = (Shelter) getIntent().getSerializableExtra("name");
        nameDisplay = (TextView) findViewById(R.id.textview_shelterDetails_nameHeader);
        addressDisplay = (TextView) findViewById(R.id.textview_shelterDetails_address);
        phoneDisplay = (TextView) findViewById(R.id.textview_shelterDetails_phone);
        capacityDisplay = (TextView) findViewById(R.id.textview_shelterDetails_capacity);
        notesDisplay = (TextView) findViewById(R.id.textview_shelterDetails_special);
        restrictDisplay = (TextView) findViewById(R.id.textview_shelterDetails_restrict);
        reserveButton = (Button) findViewById(R.id.reserve_button);
        vacancyDisplay = (TextView) findViewById(R.id.vacancies);

        nameDisplay.setText(currentShelter.getName());
        addressDisplay.setText("Address: " + currentShelter.getAddress());
        phoneDisplay.setText("Phone Number: " + currentShelter.getPhone());
        capacityDisplay.setText("Capacity: " + currentShelter.getCapacity());
        notesDisplay.setText("Special Notes: " + currentShelter.getSpecialNotes());
        restrictDisplay.setText("Restrictions: " + currentShelter.getRestrictions());
        vacancyDisplay.setText("Vacancies: " + currentShelter.getVacancy());

        mData = FirebaseDatabase.getInstance().getReference().child("shelters");
        reserveButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                final String items[] = {"1", "2", "3"};
                AlertDialog dialog = new AlertDialog.Builder(ShelterDetailsActivity.this, R.style.Theme_AppCompat_DayNight_Dialog_Alert)
                        .setTitle("SELECT SPOTS")
                        .setSingleChoiceItems(items, 0, null)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                ListView lw = ((AlertDialog) dialog).getListView();
                                String item = (String) lw.getAdapter().getItem(lw.getCheckedItemPosition());
                                currentShelter.reserveSpots(Integer.parseInt(item));
                                mData.child(currentShelter.getKey()).setValue(currentShelter);
                                //maybe database stuff as well
                            }
                        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                //  Your code when user clicked on Cancel
                            }
                        }).create();
                dialog.show();
            }
        });

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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CALL) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                startActivity(callShelterIntent);
            } else {
                Toast.makeText(this, "Permission was not granted", Toast.LENGTH_SHORT).show();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}
