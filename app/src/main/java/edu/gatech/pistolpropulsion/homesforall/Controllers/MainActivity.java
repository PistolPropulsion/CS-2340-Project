package edu.gatech.pistolpropulsion.homesforall.Controllers;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;


import java.io.InputStreamReader;
import java.util.ArrayList;

import edu.gatech.pistolpropulsion.homesforall.Models.DataReader;
import edu.gatech.pistolpropulsion.homesforall.Models.Shelter;
import edu.gatech.pistolpropulsion.homesforall.Models.ShelterManager;
import edu.gatech.pistolpropulsion.homesforall.R;
import edu.gatech.pistolpropulsion.homesforall.View.RecyclerViewAdapter;
import edu.gatech.pistolpropulsion.homesforall.View.RecyclerItemClickListener;

public class MainActivity extends Activity {

    private TextView logout;
    private Spinner filter_spinner;
    private Button filter_button;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private ShelterManager shelterManager;
    private Shelter[] shelterArray;
    private boolean originalLoad = false;
    private String search;
    private ArrayList<String> selectedItems=new ArrayList<>();



    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);

        logout = (TextView) findViewById(R.id.textView_main_logout);
        filter_spinner = (Spinner) findViewById(R.id.spinner_main_filter);
        filter_button = (Button) findViewById(R.id.button_main_filter);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_main_shelterList);

        InputStreamReader csvfile = new InputStreamReader(getResources().openRawResource(R.raw.file));
        DataReader reader = new DataReader(csvfile);
        reader.read();

//                System.out.println(reader.getCount());
//                for(int i = 0; i < reader.getCount(); i++) {
//                    for (int j = 0; j < 9; j++)
//                        System.out.print(reader.getContent()[i][j]);
//                    System.out.println();
//                }

        shelterManager = new ShelterManager(reader.getContent(), reader.getCount());
        loadShelters(shelterManager.getShelterArray());

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                for(String i: selectedItems) {
//                    System.out.println(i);
//                }
//                System.out.println("Hell this is annoying");
                System.out.println(search);

                if(selectedItems.isEmpty()) {
                    loadShelters(shelterManager.getShelterArray());
                } else {
                    loadShelters(shelterManager.search(selectedItems));
                }
            }
        });

        filter_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                selectedItems = new ArrayList<>();
                AlertDialog dialog;
                String select = filter_spinner.getSelectedItem().toString().toLowerCase();
                switch(select) {
                    case "age":
                        final String[] ageItems = {" NEWBORN "," CHILD "," YOUNG ADULT "};// arraylist to keep the selected items
                        dialog = new AlertDialog.Builder(MainActivity.this, R.style.Theme_AppCompat_Dialog_Alert)
                                .setTitle("SELECT AGE")
                                .setMultiChoiceItems(ageItems, null, new DialogInterface.OnMultiChoiceClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int indexSelected, boolean isChecked) {
                                        if (isChecked) {
                                            // If the user checked the item, add it to the selected items
                                            selectedItems.add(ageItems[indexSelected]);
                                        } else if (selectedItems.contains(ageItems[indexSelected])) {
                                            // Else, if the item is already in the array, remove it
                                            selectedItems.remove(ageItems[Integer.valueOf(indexSelected)]);
                                        }
                                    }
                                }).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int id) {
                                        //  Your code when user clicked on OK
                                        //  You can write the code  to save the selected item here
                                    }
                                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int id) {
                                        //  Your code when user clicked on Cancel
                                    }
                                }).create();
                        dialog.show();
                        search = "age";
                        break;
                    case "gender":
                        search = "gender";
                        final String[] genderItems = {" MEN ", " WOMEN "};// arraylist to keep the selected items
                        dialog = new AlertDialog.Builder(MainActivity.this, R.style.Theme_AppCompat_Dialog_Alert)
                                .setTitle("SELECT GENDER")
                                .setMultiChoiceItems(genderItems, null, new DialogInterface.OnMultiChoiceClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int indexSelected, boolean isChecked) {
                                        if (isChecked) {
                                            // If the user checked the item, add it to the selected items
                                            selectedItems.add(genderItems[indexSelected]);
                                        } else if (selectedItems.contains(genderItems[indexSelected])) {
                                            // Else, if the item is already in the array, remove it
                                            selectedItems.remove(genderItems[Integer.valueOf(indexSelected)]);
                                        }
                                    }
                                }).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int id) {
                                        //  Your code when user clicked on OK
                                        //  You can write the code  to save the selected item here
                                    }
                                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int id) {
                                        //  Your code when user clicked on Cancel
                                    }
                                }).create();
                        dialog.show();
                        search = "gender";
                        break;
                    case "name":
                        final String[] nameItems = shelterManager.getNamesArray();// arraylist to keep the selected items
                        dialog = new AlertDialog.Builder(MainActivity.this, R.style.Theme_AppCompat_Dialog_Alert)
                                .setTitle("SELECT SHELTER")
                                .setMultiChoiceItems(nameItems, null, new DialogInterface.OnMultiChoiceClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int indexSelected, boolean isChecked) {
                                        if (isChecked) {
                                            // If the user checked the item, add it to the selected items
                                            selectedItems.add(nameItems[indexSelected]);
                                        } else if (selectedItems.contains(nameItems[indexSelected])) {
                                            // Else, if the item is already in the array, remove it
                                            selectedItems.remove(nameItems[Integer.valueOf(indexSelected)]);
                                        }
                                    }
                                }).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int id) {
                                        //  Your code when user clicked on OK
                                        //  You can write the code  to save the selected item here
                                    }
                                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int id) {
                                        //  Your code when user clicked on Cancel
                                    }
                                }).create();
                        dialog.show();
                        search = "name";
                        break;
                }

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
        shelterArray = array;
//                String[] namesArray = shelterManager.getNamesArray();
//                for(int i = 0; i < 13; i++) {
//                    System.out.println(namesArray[i]);
//                }
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
//
        RecyclerViewAdapter namesAdapter = new RecyclerViewAdapter(shelterArray);
        recyclerView.setAdapter(namesAdapter);
    }

    @Override
    public void onBackPressed() {
        //do nothing
    }
}
