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


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import edu.gatech.pistolpropulsion.homesforall.Models.Shelter;
import edu.gatech.pistolpropulsion.homesforall.R;

public class ShelterDetailsActivity extends Activity {

    private final int REQUEST_CALL = 1;
    private Intent callShelterIntent;

    private Shelter currentShelter;
    private DatabaseReference mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelter_details);

        currentShelter = (Shelter) getIntent().getSerializableExtra("name");
        TextView nameDisplay = findViewById(R.id.textView_shelterDetails_nameHeader);
        TextView addressDisplay = findViewById(R.id.textView_shelterDetails_address);
        TextView phoneDisplay = findViewById(R.id.textView_shelterDetails_phone);
        TextView capacityDisplay = findViewById(R.id.textView_shelterDetails_capacity);
        TextView notesDisplay = findViewById(R.id.textView_shelterDetails_special);
        TextView restrictDisplay = findViewById(R.id.textView_shelterDetails_restrict);
        Button reserveButton = findViewById(R.id.button_shelterDetails_reserve);
        TextView vacancyDisplay = findViewById(R.id.textView_shelter_Details_vacancies);
        Button exitButton =  findViewById(R.id.button_shelterdetails_exit);

        nameDisplay.setText(currentShelter.getName());
        addressDisplay.setText("Address: " + currentShelter.getAddress());
        phoneDisplay.setText("Phone Number: " + currentShelter.getPhone());
        capacityDisplay.setText("Capacity: " + currentShelter.getCapacity());
        notesDisplay.setText("Special Notes: " + currentShelter.getSpecialNotes());
        restrictDisplay.setText("Restrictions: " + currentShelter.getRestrictions());
        vacancyDisplay.setText("Vacancies: " + currentShelter.getVacancy());

        mData = FirebaseDatabase.getInstance().getReference().child("shelters");

        mData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                vacancyDisplay.setText("Vacancies: " + currentShelter.getVacancy());
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });

        reserveButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                final String items[] = {"1", "2", "3"};
                AlertDialog dialog = new AlertDialog.Builder(ShelterDetailsActivity.this,
                        R.style.Theme_AppCompat_DayNight_Dialog_Alert)
                        .setTitle(R.string.beds)
                        .setSingleChoiceItems(items, 0, null)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                ListView lw = ((AlertDialog) dialog).getListView();
                                String item = (String) lw.getAdapter().
                                        getItem(lw.getCheckedItemPosition());
                                currentShelter.reserveSpots(Integer.parseInt(item));
                                mData.child(currentShelter.getKey()).setValue(currentShelter);
                                //maybe database stuff as well
                                Toast.makeText(getApplicationContext(), "Rooms Reserved",
                                        Toast.LENGTH_SHORT).show();
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
            if (ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                startActivity(callShelterIntent);
            } else {

                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.CALL_PHONE)) {
                    Toast.makeText(this,
                            "Phone calling permission needed to call the shelter.",
                            Toast.LENGTH_SHORT).show();
                }

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            }
        });
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CALL) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                startActivity(callShelterIntent);
            } else {
                Toast.makeText(this, "Permission was not granted",
                        Toast.LENGTH_SHORT).show();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}
