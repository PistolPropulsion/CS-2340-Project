package edu.gatech.pistolpropulsion.homesforall.Models;

import com.opencsv.CSVReader;

import java.io.InputStreamReader;

public class DataReader {

    private InputStreamReader file;
    private String[][] content;
    private int count = 0;

    public DataReader(InputStreamReader reader) {
        this.file = reader;
        content = new String [50][9];
    }

    public void read(){
        try {
            CSVReader reader = new CSVReader(file);
            String[] tempArray;
            while ((tempArray = reader.readNext()) != null) {
                if(count == 0) {
                    count++;
                } else {
                    content[count-1] = tempArray;
                    count++;
                }
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public String[][] getContent(){
        return this.content;
    }

    public int getCount() {
        return this.count - 1;
    }
}
