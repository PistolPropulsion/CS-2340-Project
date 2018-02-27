package edu.gatech.pistolpropulsion.homesforall.Controllers;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.io.InputStreamReader;

import edu.gatech.pistolpropulsion.homesforall.Models.DataReader;
import edu.gatech.pistolpropulsion.homesforall.Models.Shelter;
import edu.gatech.pistolpropulsion.homesforall.Models.ShelterManager;
import edu.gatech.pistolpropulsion.homesforall.R;
import edu.gatech.pistolpropulsion.homesforall.View.RecyclerViewAdapter;

public class MainActivity extends Activity {

    private TextView logout;
    private Button refresh;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager mLayoutManager;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);

        logout = (TextView) findViewById(R.id.logout_textView);
        refresh = (Button) findViewById(R.id.refresh_button);
        recyclerView = (RecyclerView) findViewById(R.id.shelterList);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Welcome.class));
            }
        });

        refresh.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                InputStreamReader csvfile = new InputStreamReader(getResources().openRawResource(R.raw.file));
                DataReader reader = new DataReader(csvfile);
                reader.read();

//                System.out.println(reader.getCount());
//                for(int i = 0; i < reader.getCount(); i++) {
//                    for (int j = 0; j < 9; j++)
//                        System.out.print(reader.getContent()[i][j]);
//                    System.out.println();
//                }

                ShelterManager shelterManager = new ShelterManager(reader.getContent(), reader.getCount());
                Shelter[] shelterArray = shelterManager.getShelterArray();
//                String[] namesArray = shelterManager.getNamesArray();
//                for(int i = 0; i < 13; i++) {
//                    System.out.println(namesArray[i]);
//                }
                mLayoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(mLayoutManager);
//
                RecyclerViewAdapter namesAdapter = new RecyclerViewAdapter(shelterManager.getShelterArray());
                recyclerView.setAdapter(namesAdapter);
            }
        });
    }

    @Override
    public void onBackPressed() {
        //do nothing
    }
}
