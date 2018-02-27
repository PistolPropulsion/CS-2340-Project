package edu.gatech.pistolpropulsion.homesforall.Controllers;

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

public class MainActivity extends Activity {

    private TextView logout;
    private Button refresh;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager mLayoutManager;


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

    public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
        private Shelter[] mDataset;

        // Provide a reference to the views for each data item
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder
        public class ViewHolder extends RecyclerView.ViewHolder {
            // each data item is just a string in this case
            public TextView mTextView;
            public ViewHolder(TextView v) {
                super(v);
                mTextView = v;
            }
        }

        // Provide a suitable constructor (depends on the kind of dataset)
        public RecyclerViewAdapter(Shelter[] myDataset) {
            mDataset = myDataset;
        }

        // Create new views (invoked by the layout manager)
        @Override
        public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                 int viewType) {
            // create a new view
            TextView v = (TextView) LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.activity_listitem, parent, false);
            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element
            holder.mTextView.setText(mDataset[position].getName());

        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return mDataset.length;
        }
    }
}
