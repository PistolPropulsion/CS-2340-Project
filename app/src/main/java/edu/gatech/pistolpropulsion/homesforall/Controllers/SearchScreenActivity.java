package edu.gatech.pistolpropulsion.homesforall.Controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;

import java.util.ArrayList;

import edu.gatech.pistolpropulsion.homesforall.Models.AgeGroup;
import edu.gatech.pistolpropulsion.homesforall.R;
import edu.gatech.pistolpropulsion.homesforall.View.CheckboxValues;
import edu.gatech.pistolpropulsion.homesforall.View.SpinnerMultiAdapter;

public class SearchScreenActivity extends AppCompatActivity {

    Spinner ageSpinner;
     Spinner genderSpinner;
    Spinner restrictionSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchscreen);

        ageSpinner =  findViewById(R.id.attribute1_spinner);
        AgeGroup[] _ageGroup = AgeGroup.values();

        ArrayList<CheckboxValues> listValues = new ArrayList<>();
        for(int i = 0; i < _ageGroup.length; i++) {
            CheckboxValues<AgeGroup> age = new CheckboxValues<>();
            age.setValue(_ageGroup[i]);
            age.setSelected(false);
            listValues.add(age);
        }

        SpinnerMultiAdapter ageAdapter = new SpinnerMultiAdapter(this.getApplicationContext(), 0, listValues);
        ageSpinner.setAdapter(ageAdapter);

        genderSpinner =  findViewById(R.id.attribute2_spinner);
        restrictionSpinner =  findViewById(R.id.attribute3_spinner);


    }
}
