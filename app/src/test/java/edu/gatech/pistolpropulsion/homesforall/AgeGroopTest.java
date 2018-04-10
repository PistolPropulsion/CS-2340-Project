package edu.gatech.pistolpropulsion.homesforall;

import org.junit.Test;

import edu.gatech.pistolpropulsion.homesforall.Models.AgeGroup;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Vaud Burton on 4/9/2018
 */

public class AgeGroopTest {
    @Test
    public void testAgeGroupInvalid() {
        assertEquals(AgeGroup.getGroup(-46), AgeGroup.INVALID);
    }

    @Test
    public void testAgeGroupNewborn() {
        assertEquals(AgeGroup.getGroup(3), AgeGroup.NEWBORN);
    }

    @Test
    public void testAgeGroupChild() {
        assertEquals(AgeGroup.getGroup(13), AgeGroup.CHILD);
    }

    @Test
    public void testAgeGroupYoungAdult() {
        assertEquals(AgeGroup.getGroup(21), AgeGroup.YOUNGADULT);
    }

    @Test
    public void testAgeGroupAdult() {
        assertEquals(AgeGroup.getGroup(35), AgeGroup.ADULT);
    }
    @Test
    public void testAgeGroupSenior() {
        assertEquals(AgeGroup.getGroup(87), AgeGroup.SENIOR);
    }
}
