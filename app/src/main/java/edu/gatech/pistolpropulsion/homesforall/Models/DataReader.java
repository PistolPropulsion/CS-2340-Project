package edu.gatech.pistolpropulsion.homesforall.Models;

import com.opencsv.CSVReader;

import java.io.InputStreamReader;

/**
 * class to read CSV files
 * defunct after we finished firebase
 */
public class DataReader {

    private InputStreamReader file;
    private String[][] content;
    private int count = 0;

    /**
     * constructor for this DataReader
     * @param reader InputStreamReader
     */
    public DataReader(InputStreamReader reader) {
        this.file = reader;
        // Do these need to be hard coded?
        content = new String [50][9];
    }

    /**
     * reads a file
     */
    public void read(){
        try {
            CSVReader reader = new CSVReader(file);
            String[] tempArray;
            //noinspection NestedAssignment
            //it is used though, in the else statement
            //also this method isn't being used after we started Firebase
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

    /**
     * gets content of the file read
     * @return 2D String array of CSV file
     */
    public String[][] getContent(){
        return this.content;
    }

    /**
     * counts how many lines were read
     * @return int number of lines in CSV file
     */
    public int getCount() {
        return this.count - 1;
    }
}
