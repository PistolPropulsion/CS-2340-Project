package edu.gatech.pistolpropulsion.homesforall;

import org.junit.Test;

import edu.gatech.pistolpropulsion.homesforall.Models.AgeGroup;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Vaud Burton on 4/9/2018
 */

public class AgeGroupTest {
    @Test
    public void testAgeGroupInvalid() {
        assertEquals(AgeGroup.getGroup(-2147483648), AgeGroup.INVALID);
        assertEquals(AgeGroup.getGroup(-1), AgeGroup.INVALID);
    }

    @Test
    public void testAgeGroupNewborn() {
        assertEquals(AgeGroup.getGroup(0), AgeGroup.NEWBORN);
        assertEquals(AgeGroup.getGroup(4), AgeGroup.NEWBORN);
    }

    @Test
    public void testAgeGroupChild() {
        assertEquals(AgeGroup.getGroup(6), AgeGroup.CHILD);
        assertEquals(AgeGroup.getGroup(17), AgeGroup.CHILD);
    }

    @Test
    public void testAgeGroupYoungAdult() {
        assertEquals(AgeGroup.getGroup(18), AgeGroup.YOUNGADULT);
        assertEquals(AgeGroup.getGroup(25), AgeGroup.YOUNGADULT);
    }

    @Test
    public void testAgeGroupAdult() {
        assertEquals(AgeGroup.getGroup(26), AgeGroup.ADULT);
        assertEquals(AgeGroup.getGroup(54), AgeGroup.ADULT);
    }
    @Test
    public void testAgeGroupSenior() {
        assertEquals(AgeGroup.getGroup(55), AgeGroup.SENIOR);
        assertEquals(AgeGroup.getGroup(2147483647), AgeGroup.SENIOR);
    }
}
