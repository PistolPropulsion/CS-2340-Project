package edu.gatech.pistolpropulsion.homesforall.View;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yuan Zhang on 3/7/2018.
 */

/**
 * adapter for the spinner
 */
public class SpinnerMultiAdapter extends ArrayAdapter<CheckboxValues> {

    private Context mContext;
    private ArrayList<CheckboxValues> checkedList;
    private SpinnerMultiAdapter myAdapter;
    private boolean isFromView = false;

    /**
     * constructor
     * @param context context
     * @param resource resource
     * @param objects objects
     */
    public SpinnerMultiAdapter(Context context, int resource, List<CheckboxValues> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.checkedList = (ArrayList<CheckboxValues>) objects;
        this.myAdapter = this;
    }
}
