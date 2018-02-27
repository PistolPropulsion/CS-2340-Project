package edu.gatech.pistolpropulsion.homesforall.Models;

import com.opencsv.CSVReader;

import java.io.InputStreamReader;

public class DataReader {

    private InputStreamReader file;
    private String[] content;

    public DataReader(InputStreamReader reader) {
        this.file = reader;
    }

    public void read(){
        try {
            CSVReader reader = new CSVReader(file);
            while ((content = reader.readNext()) != null) {
                for(int i = 0; i < 9; i++)
                    System.out.print(content[i]);
                System.out.println();
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}
