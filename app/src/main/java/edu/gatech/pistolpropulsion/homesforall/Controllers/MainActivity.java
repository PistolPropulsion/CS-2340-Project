package edu.gatech.pistolpropulsion.homesforall.Controllers;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.pistolpropulsion.homesforall.Models.Shelter;
import edu.gatech.pistolpropulsion.homesforall.Models.ShelterManager;
import edu.gatech.pistolpropulsion.homesforall.R;
import edu.gatech.pistolpropulsion.homesforall.View.RecyclerViewAdapter;
import edu.gatech.pistolpropulsion.homesforall.View.RecyclerItemClickListener;

/**
 * main activity screen, it's the giant list of shelters
 */
public class MainActivity extends Activity {

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
    private RecyclerView recyclerView;
    private ShelterManager shelterManager;
    private Shelter[] shelterArray;
    private Shelter[] fetchedShelterArray;
    @SuppressWarnings("FieldMayBeFinal")
    //selectedName gets modified multiple times in file, why final?
    private List<String> selectedName = new ArrayList<>();



    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);

        TextView refresh = findViewById(R.id.refresh_textView);

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
        DatabaseReference myRef = database.getReference().child("shelters");
        shelterManager = new ShelterManager();

//        InputStreamReader csvfile = new InputStreamReader(getResources().openRawResource(R.raw.file));
//        DataReader reader = new DataReader(csvfile);
//        reader.read();
//
////                System.out.println(reader.getCount());
////                for(int i = 0; i < reader.getCount(); i++) {
////                    for (int j = 0; j < 9; j++)
////                        System.out.print(reader.getContent()[i][j]);
////                    System.out.println();
////                }
//
//
//        shelterManager = new ShelterManager(reader.getContent(), reader.getCount());
//        loadShelters(shelterManager.getShelterArray());
//        fetchedShelterArray = shelterManager.getShelterArray();

        final ArrayList<Shelter> shelterList = new ArrayList<Shelter>();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //noinspection EmptyClass must be empty or breaks the entire program
                GenericTypeIndicator<ArrayList<Shelter>> t = new GenericTypeIndicator<ArrayList<Shelter>>() {};
                Iterable<Shelter> fetch = dataSnapshot.getValue(t);

                shelterList.clear();

                assert fetch != null;
                for (Shelter s : fetch) {
                    if (s != null) {
                        shelterList.add(s);
                    }
                }

                fetchedShelterArray = new Shelter[shelterList.size()];
                fetchedShelterArray = shelterList.toArray(fetchedShelterArray);
                shelterArray = fetchedShelterArray;

                loadShelters(shelterArray);
                shelterManager.setShelterArray(shelterArray);

            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(MainActivity.this, Welcome.class));
            }
        });

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        filter.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
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
        });

        name_checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name_checkBox.isChecked()) {
                    name_editText.setVisibility(View.VISIBLE);
                } else {
                    name_editText.setVisibility(View.GONE);
                    name_editText.setText("");
                    selectedName.clear();
                    refresh();
                }
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

        age_checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        });

        age_newborn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refresh();
            }
        });

        age_child.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refresh();
            }
        });

        age_youngAdult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refresh();
            }
        });

        gender_checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        });

        gender_male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refresh();
            }
        });

        gender_female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refresh();
            }
        });

        done_textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
            }
        });

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(), recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        Shelter item = shelterArray[position];

                        Intent myIntent = new Intent(MainActivity.this, ShelterDetailsActivity.class);
                        myIntent.putExtra("name", item);
                        startActivity(myIntent);
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );
    }

    public void loadShelters(Shelter[] array){

        // THIS WAS FOR CORRECTLY UPLOADING TO SERVER - CAN BE USED LATER FOR EMPLOYEES ADDING SHELTERS
//        for (Shelter s : array) {
//            myRef.child(s.getKey()).setValue(s);
//        }

        //noinspection AssignmentToCollectionOrArrayFieldFromParameter
        shelterArray = array;
//                String[] namesArray = shelterManager.getNamesArray();
//                for(int i = 0; i < 13; i++) {
//                    System.out.println(namesArray[i]);
//                }
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
//
        RecyclerViewAdapter namesAdapter = new RecyclerViewAdapter(shelterArray);
        recyclerView.setAdapter(namesAdapter);
    }

    @Override
    public void onBackPressed() {
        //do nothing
    }

    public void refresh() {
        List<String> selectedItems;
        selectedItems = new ArrayList<>();
        String search = "";

        if (age_checkBox.isChecked()) {

            final String[] ageItems = {" NEWBORN ", " CHILD ", " YOUNG ADULT "};// arraylist to keep the selected items

            if (age_newborn.isChecked()) {
                selectedItems.add(ageItems[0]);
            } else {
                selectedItems.remove(ageItems[0]);
            }
            if (age_child.isChecked()) {
                selectedItems.add(ageItems[1]);
            } else {
                //System.out.println("REMOVED");
                selectedItems.remove(ageItems[1]);
            }
            if (age_youngAdult.isChecked()) {
                selectedItems.add(ageItems[2]);
            } else {
                selectedItems.remove(ageItems[2]);
            }
            search = search + "age ";
        }

        if (gender_checkBox.isChecked()) {

            final String[] genderItems = {" MEN ", " WOMEN "};// arraylist to keep the selected items

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

            search = search + "gender ";

        }

        if (name_checkBox.isChecked()) {

            //System.out.println(name_editText.getText().toString());

            if (!("".equals(name_editText.getText().toString()))) {
                selectedName.clear();
                selectedName.add(name_editText.getText().toString());
            } else {
                selectedName.clear();
            }

            search = search + "name ";

        }

        //System.out.println(search);

        if(selectedItems.isEmpty() && selectedName.isEmpty()) {
            loadShelters(fetchedShelterArray);
        } else {
            Shelter[] temp = fetchedShelterArray;
            ShelterManager tempManager = new ShelterManager();
            tempManager.setShelterArray(temp);
            //System.out.println("BEFORE");
            for (Shelter s : temp) {
                //System.out.println(s.getName());
            }
            //System.out.println("-");
            if (name_checkBox.isChecked() && !selectedName.isEmpty()) {
                temp = tempManager.searchName(selectedName);
                tempManager.setShelterArray(temp);
                //System.out.println("AFTER");
                //for (Shelter s : temp) {
                    //System.out.println(s.getName());
                //}
                //System.out.println("-");
            }

            if (!selectedItems.isEmpty()) {
                temp = tempManager.search(selectedItems);
            }

            loadShelters(temp);
        }
    }
}
