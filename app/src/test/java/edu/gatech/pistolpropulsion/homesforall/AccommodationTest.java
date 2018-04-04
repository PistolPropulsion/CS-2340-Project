package edu.gatech.pistolpropulsion.homesforall;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import edu.gatech.pistolpropulsion.homesforall.Models.Shelter;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Yuan Zhang on 4/3/2018.
 */

public class AccommodationTest {

    private Shelter testShelter;

    @Test
    //empty shelter, test string can be anything
    public void testEmpty() {
        testShelter = new Shelter();
        assertEquals("Empty search list", false, testShelter.canAccommodate("ANYTHING"));
    }

    @Test
    public void testOneItem() {
        testShelter = new Shelter("","","","MEN","","","","","");
        assertEquals("Shelter for men", true, testShelter.canAccommodate(" MEN "));
    }

    @Test
    public void testEmptyString() {
        testShelter = new Shelter();
        assertEquals("Empty string search", false, testShelter.canAccommodate(""));
    }

    @Test
    public void testSpeciaNotes() {
        testShelter = new Shelter("", "Hope Atlanta", "", "WOMEN/CHILDREN", "", "", "", "VETERAN", "");
        assertEquals("Veteran status", true, testShelter.canAccommodate("VETERAN"));
    }

    @Test
    public void testNullParameter() {
        testShelter = new Shelter();
        assertEquals("Null passed in", false, testShelter.canAccommodate(null));
    }

    @Test
    public void testRandomString() {
        testShelter = new Shelter("", "Hope Atlanta", "", "WOMEN/CHILDREN", "", "", "", "VETERAN", "");
        assertEquals("Random string", false, testShelter.canAccommodate("ASDAFSLKJAHSDOIUHASJKD"));
    }
}
