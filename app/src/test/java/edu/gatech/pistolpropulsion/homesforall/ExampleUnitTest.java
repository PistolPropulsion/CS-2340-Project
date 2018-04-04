package edu.gatech.pistolpropulsion.homesforall;

import org.junit.Test;

import edu.gatech.pistolpropulsion.homesforall.Models.Administrator;
import edu.gatech.pistolpropulsion.homesforall.Models.AgeGroup;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }
    @Test
    public void admin_constructor() throws Exception {
        Administrator admin1 = new Administrator("email@website.com", "Password");
        Administrator admin2 = new Administrator("email@website.com", "Password", "Bob");
        assertEquals(admin1.getEmail(), admin2.getEmail());
        assertEquals(admin1.getName(), "");
    }

    @Test
    public void ageGroup() throws Exception {
        assertEquals(AgeGroup.getGroup(-46), AgeGroup.INVALID);
        assertEquals(AgeGroup.getGroup(3), AgeGroup.NEWBORN);
        assertEquals(AgeGroup.getGroup(13), AgeGroup.CHILD);
        assertEquals(AgeGroup.getGroup(21), AgeGroup.YOUNGADULT);
        assertEquals(AgeGroup.getGroup(35), AgeGroup.ADULT);
        assertEquals(AgeGroup.getGroup(87), AgeGroup.SENIOR);
    }

    @Test
    public void shelter() throws Exception {
        //InputStreamReader csvfile = new InputStreamReader(new FileInputStream(R.raw.file));
        //DataReader reader = new DataReader(csvfile);
    }
}