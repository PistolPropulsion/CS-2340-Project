package edu.gatech.pistolpropulsion.homesforall;

import android.content.Context;
import android.test.mock.MockContext;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;


import edu.gatech.pistolpropulsion.homesforall.Models.Shelter;
import edu.gatech.pistolpropulsion.homesforall.Models.ShelterManager;

/**
 * Created by Amaan Marfatia
 */

public class ShelterManagerTests {

    private ShelterManager empty;
    private ShelterManager full;

    @Before
    public void setUp() {
        empty = new ShelterManager();
        String[][] data = {
                {"0", "Cool Shelter", "N/A", "N/A", "N/A", "N/A", "N/A", "N/A", "N/A"},
                {"1", "Other Shelter", "134", "Male", "N/A", "N/A", "N/A", "N/A", "N/A"},
                {"2", "St. Mary's", "N/A", "N/A", "N/A", "N/A", "N/A", "N/A", "N/A"},
                {"3", "Mary's Shelter", "N/A", "N/A", "N/A", "N/A", "N/A", "N/A", "N/A"},
                {"4", "N/A", "N/A", "N/A", "N/A", "N/A", "N/A", "N/A", "N/A"},
                {"5", "This shelt Name", "N/A", "N/A", "N/A", "N/A", "N/A", "N/A", "N/A"},
        };
        full = new ShelterManager(data);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullStringOnEmptyShelter() {
        empty.searchName(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullStringOnFullShelter() {
        full.searchName(null);
    }

    @Test
    public void testEmptyString() {
        String toSearch = "";
        assertArrayEquals("Empty ShelterManger should return full shelter array", empty.getShelterArray(), empty.searchName(toSearch));
        assertArrayEquals("Full ShelterManger should return full shelter array", full.getShelterArray(), full.searchName(toSearch));
    }

    @Test
    public void searchForThingsNotInShelterArray() {
        String toSearch = "Bob";
        assertArrayEquals("Empty ShelterManger should return empty array", new Shelter[0], empty.searchName(toSearch));
        assertArrayEquals("Full ShelterManger should return empty array", new Shelter[0], full.searchName(toSearch));
    }

    @Test
    public void searchForThingsInShelterArray() {
        String[] toSearch = {"mary", "shelter", "shelt", "N/A"};
        assertArrayEquals("Empty ShelterManger should return empty array", new Shelter[0], empty.searchName(toSearch[0]));
        assertEquals("Full ShelterManger should return array of size 2", 2, full.searchName(toSearch[0]).length);
        assertEquals("Full ShelterManger should return array of size 3", 3, full.searchName(toSearch[1]).length);
        assertEquals("Full ShelterManger should return array of size 4", 4, full.searchName(toSearch[2]).length);
        assertEquals("Full ShelterManger should return array of size 1", 1, full.searchName(toSearch[3]).length);
    }
}
