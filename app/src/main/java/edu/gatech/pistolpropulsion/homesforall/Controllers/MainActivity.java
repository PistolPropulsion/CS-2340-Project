package edu.gatech.pistolpropulsion.homesforall.Controllers;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;


import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import edu.gatech.pistolpropulsion.homesforall.Models.Shelter;
import edu.gatech.pistolpropulsion.homesforall.Models.ShelterManager;
import edu.gatech.pistolpropulsion.homesforall.R;
import edu.gatech.pistolpropulsion.homesforall.View.RecyclerViewAdapter;
import edu.gatech.pistolpropulsion.homesforall.View.RecyclerItemClickListener;

/**
 * main activity screen, it's the giant list of shelters
 */
public class MainActivity extends FragmentActivity implements OnMapReadyCallback {

    private CheckBox name_checkBox;
    private EditText name_editText;
    private CheckBox age_checkBox;
    private CheckBox age_newborn;
    private CheckBox age_child;
    private CheckBox age_youngAdult;
    private CheckBox gender_checkBox;
    private CheckBox gender_male;
    private CheckBox gender_female;
    private TextView done_textView;
    private SupportMapFragment mapFragment;
    private RecyclerView recyclerView;
    private ShelterManager shelterManager;
    private Shelter[] shelterArray;
    private Shelter[] fetchedShelterArray;
    @SuppressWarnings("FieldMayBeFinal")
    //selectedName gets modified multiple times in file, why final?
    private String selectedName = "";
    private GoogleMap map;



    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);

        name_checkBox = findViewById(R.id.name_checkBox);
        name_editText = findViewById(R.id.name_editText);

        age_checkBox = findViewById(R.id.age_checkBox);
        age_newborn = findViewById(R.id.age_newborn);
        age_child = findViewById(R.id.age_child);
        age_youngAdult = findViewById(R.id.age_youngAdult);

        gender_checkBox = findViewById(R.id.gender_checkBox);
        gender_male = findViewById(R.id.gender_male);
        gender_female = findViewById(R.id.gender_female);

        done_textView = findViewById(R.id.done_textView);

        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapFragment);
        mapFragment.getMapAsync(this);

        TextView filter = findViewById(R.id.filter_textView);
        recyclerView = findViewById(R.id.shelterList);
        Button logout = findViewById(R.id.logout_btn);

        name_checkBox.setVisibility(View.GONE);
        name_editText.setVisibility(View.GONE);
        age_checkBox.setVisibility(View.GONE);
        age_newborn.setVisibility(View.GONE);
        age_child.setVisibility(View.GONE);
        age_youngAdult.setVisibility(View.GONE);
        gender_checkBox.setVisibility(View.GONE);
        gender_male.setVisibility(View.GONE);
        gender_female.setVisibility(View.GONE);
        done_textView.setVisibility(View.GONE);

        name_checkBox.setChecked(false);
        age_checkBox.setChecked(false);
        age_newborn.setChecked(false);
        age_child.setChecked(false);
        age_youngAdult.setChecked(false);
        gender_checkBox.setChecked(false);
        gender_male.setChecked(false);
        gender_female.setChecked(false);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference dbRef = database.getReference().child("shelters");
        shelterManager = ShelterManager.getInstance();

//        InputStreamReader csvfile = new InputStreamReader(getResources().
//                  openRawResource(R.raw.file));
//        DataReader reader = new DataReader(csvfile);
//        reader.read();
//
//        System.out.println(reader.getCount());
//        for(int i = 0; i < reader.getCount(); i++) {
//            for (int j = 0; j < 9; j++)
//                System.out.print(reader.getContent()[i][j]);
//            System.out.println();
//        }
//
//        shelterManager = new ShelterManager(reader.getContent(), reader.getCount());
//        loadShelters(shelterManager.getShelterArray());
//        fetchedShelterArray = shelterManager.getShelterArray();

        final ArrayList<Shelter> shelterList = new ArrayList<>();

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //noinspection EmptyClass must be empty or breaks the entire program
                GenericTypeIndicator<ArrayList<Shelter>> t =
                        new GenericTypeIndicator<ArrayList<Shelter>>() {};
                Iterable<Shelter> fetch = dataSnapshot.getValue(t);

                shelterList.clear();

                assert fetch != null;
                for (Shelter s : fetch) {
                    if (s != null) {
                        shelterList.add(s);
                    }
                }

                fetchedShelterArray = new Shelter[shelterList.size()];  // May not need this line
                fetchedShelterArray = shelterList.toArray(fetchedShelterArray);
                shelterArray = fetchedShelterArray;

                loadShelters(shelterArray);
                try {
                    setUpMapMarkers();
                } catch (IllegalStateException e) {

                }
                shelterManager.setShelterArray(shelterArray);

            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });

        logout.setOnClickListener((view) -> {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(MainActivity.this, Welcome.class));
            }
        );

        filter.setOnClickListener(view -> {
                mapFragment.getView().setVisibility(View.GONE);

                name_checkBox.setVisibility(View.VISIBLE);
                if (name_checkBox.isChecked()) {
                    name_editText.setVisibility(View.VISIBLE);
                }

                age_checkBox.setVisibility(View.VISIBLE);
                if (age_checkBox.isChecked()) {
                    age_newborn.setVisibility(View.VISIBLE);
                    age_child.setVisibility(View.VISIBLE);
                    age_youngAdult.setVisibility(View.VISIBLE);
                }

                gender_checkBox.setVisibility(View.VISIBLE);
                if (gender_checkBox.isChecked()) {
                    gender_male.setVisibility(View.VISIBLE);
                    gender_female.setVisibility(View.VISIBLE);
                }

                done_textView.setVisibility(View.VISIBLE);
            }
        );

        name_checkBox.setOnClickListener(view -> {
                if (name_checkBox.isChecked()) {
                    name_editText.setVisibility(View.VISIBLE);
                } else {
                    name_editText.setVisibility(View.GONE);
                    name_editText.setText("");
                    selectedName = "";
                    refresh();
                }
        });

        name_editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                refresh();
            }

            @Override
            public void afterTextChanged(Editable s) {
                //
            }
        });

        age_checkBox.setOnClickListener(view -> {
                if (age_checkBox.isChecked()) {
                    age_newborn.setVisibility(View.VISIBLE);
                    age_child.setVisibility(View.VISIBLE);
                    age_youngAdult.setVisibility(View.VISIBLE);
                } else {
                    age_newborn.setVisibility(View.GONE);
                    age_child.setVisibility(View.GONE);
                    age_youngAdult.setVisibility(View.GONE);
                    age_newborn.setChecked(false);
                    age_child.setChecked(false);
                    age_youngAdult.setChecked(false);
                }
                refresh();
            }
        );

        age_newborn.setOnClickListener(view -> refresh());

        age_child.setOnClickListener(view -> refresh());

        age_youngAdult.setOnClickListener(view -> refresh());

        gender_checkBox.setOnClickListener(view -> {
                if (gender_checkBox.isChecked()) {
                    gender_male.setVisibility(View.VISIBLE);
                    gender_female.setVisibility(View.VISIBLE);
                } else {
                    gender_male.setVisibility(View.GONE);
                    gender_female.setVisibility(View.GONE);
                    gender_male.setChecked(false);
                    gender_female.setChecked(false);
                }
                refresh();
            }
        );

        gender_male.setOnClickListener(view -> refresh());

        gender_female.setOnClickListener(view -> refresh());

        done_textView.setOnClickListener(view -> {
                name_checkBox.setVisibility(View.GONE);
                name_editText.setVisibility(View.GONE);
                age_checkBox.setVisibility(View.GONE);
                age_newborn.setVisibility(View.GONE);
                age_child.setVisibility(View.GONE);
                age_youngAdult.setVisibility(View.GONE);
                gender_checkBox.setVisibility(View.GONE);
                gender_male.setVisibility(View.GONE);
                gender_female.setVisibility(View.GONE);
                done_textView.setVisibility(View.GONE);

                try {
                    setUpMapMarkers();
                } catch (IllegalStateException e) {

                }

                mapFragment.getView().setVisibility(View.VISIBLE);
            }
        );

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(),
                        recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        Shelter item = shelterArray[position];

                        Intent myIntent = new Intent(MainActivity.this,
                                ShelterDetailsActivity.class);
                        myIntent.putExtra("name", item);
                        startActivity(myIntent);
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        try {
            setUpMapMarkers();
        } catch (IllegalStateException e) {

        }
    }

    private void setUpMapMarkers() {
        // clear markers, set markers to addresses of shelterArray, zoom to right place
        if (map == null || shelterArray == null) {
            return;
        }
        map.clear();
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for (Shelter s : shelterArray) {
            try {
                Geocoder selected_place_geocoder = new Geocoder(this, Locale.getDefault());
                List<Address> addresses =
                        selected_place_geocoder.getFromLocationName(s.getAddress(), 1);

                if (addresses != null) {
                    Address address = addresses.get(0);
                    LatLng location = new LatLng(address.getLatitude(), address.getLongitude());
                    map.addMarker(new MarkerOptions()
                            .position(location)
                            .title(s.getName())
                            .snippet(s.getAddress())
                    );
                    builder.include(location);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        LatLngBounds bounds = builder.build();
        //noinspection MagicNumber just GUI stuff?
        int padding = (int) (getResources().getDisplayMetrics().widthPixels * 0.10);
        // offset from edges of the map in pixels
        CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);
        map.moveCamera(cu);

    }

    public void loadShelters(Shelter[] array){

//  THIS WAS FOR CORRECTLY UPLOADING TO SERVER - CAN BE USED LATER FOR EMPLOYEES ADDING SHELTERS
//       for (Shelter s : array) {
//           dbRef.child(s.getKey()).setValue(s);
//       }

        //noinspection AssignmentToCollectionOrArrayFieldFromParameter
        shelterArray = array;
//                String[] namesArray = shelterManager.getNamesArray();
//                for(int i = 0; i < 13; i++) {
//                    System.out.println(namesArray[i]);
//                }
        RecyclerView.LayoutManager mLayoutManager =
                new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);

        RecyclerViewAdapter namesAdapter = new RecyclerViewAdapter(shelterArray);
        recyclerView.setAdapter(namesAdapter);
    }

    @Override
    public void onBackPressed() {
        // do nothing
    }

    public void refresh() {
        List<String> selectedItems;
        selectedItems = new ArrayList<>();

        if (age_checkBox.isChecked()) {

            // arraylist to keep the selected items
            final String[] ageItems = {" NEWBORN ", " CHILD ", " YOUNG ADULT "};

            if (age_newborn.isChecked()) {
                selectedItems.add(ageItems[0]);
            } else {
                selectedItems.remove(ageItems[0]);
            }
            if (age_child.isChecked()) {
                selectedItems.add(ageItems[1]);
            } else {
//                System.out.println("REMOVED");
                selectedItems.remove(ageItems[1]);
            }
            if (age_youngAdult.isChecked()) {
                selectedItems.add(ageItems[2]);
            } else {
                selectedItems.remove(ageItems[2]);
            }
        }

        if (gender_checkBox.isChecked()) {

            // arraylist to keep the selected items
            final String[] genderItems = {" MEN ", " WOMEN "};

            if (gender_male.isChecked()) {
                selectedItems.add(genderItems[0]);
            } else {
                selectedItems.remove(genderItems[0]);
            }
            if (gender_female.isChecked()) {
                selectedItems.add(genderItems[1]);
            } else {
                selectedItems.remove(genderItems[1]);
            }
        }

        if (name_checkBox.isChecked()) {
            if (!("".equals(name_editText.getText().toString()))) {
                selectedName = name_editText.getText().toString();
            } else {
                selectedName = "";
            }
        }

        if(selectedItems.isEmpty() && selectedName.length() == 0) {
            loadShelters(fetchedShelterArray);
        } else {
            Shelter[] temp = fetchedShelterArray;
            shelterManager.setTempShelterArray(temp);
//            System.out.println("BEFORE");
//            for (Shelter s : temp) {
//                System.out.println(s.getName());
//            }
//            System.out.println("-");
            if (name_checkBox.isChecked() && selectedName.length() > 0) {
                temp = shelterManager.searchTempName(selectedName);
                shelterManager.setTempShelterArray(temp);
//                System.out.println("AFTER");
//                for (Shelter s : temp) {
//                    System.out.println(s.getName());
//                }
//                System.out.println("-");
            }

            if (!selectedItems.isEmpty()) {
                temp = shelterManager.searchTemp(selectedItems);
            }

            loadShelters(temp);
        }
    }
}
