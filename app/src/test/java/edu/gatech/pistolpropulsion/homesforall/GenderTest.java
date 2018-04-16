package edu.gatech.pistolpropulsion.homesforall;

import org.junit.Test;

import edu.gatech.pistolpropulsion.homesforall.Models.Gender;

import static org.junit.Assert.*;

/**
 * Created by Christian Boylston on 4/10/18
 */
public class GenderTest {

    private char[] validChar = {'M', 'F', 'O', 'P', 'G', 'B', 'A', 'I'};
    private char[] alphabet = new char[128];

    @Test
    public void testInvalidGender() {
        int j = 0;
        for (int i = 0; i < 128; i++) {
            alphabet[j] = (char) i;
            j++;
        }
        for (char c : alphabet) {
            if(!contains(c)) {
                assertEquals(Gender.getGender(c), Gender.Invalid);
            }
        }

    }

    @Test
    public void testMaleGender() {
        assertEquals(Gender.getGender('M'), Gender.Male);
    }

    @Test
    public void testFemaleGender() {
        assertEquals(Gender.getGender('F'), Gender.Female);
    }

    @Test
    public void testOtherGender() {
        assertEquals(Gender.getGender('O'), Gender.Other);
    }

    @Test
    public void testPanGender() {
        assertEquals(Gender.getGender('P'), Gender.Pangender);
    }

    @Test
    public void testGenderQueer() {
        assertEquals(Gender.getGender('G'), Gender.Genderqueer);
    }

    @Test
    public void testBigender() {
        assertEquals(Gender.getGender('B'), Gender.Bigender);
    }

    @Test
    public void testAgender() {
        assertEquals(Gender.getGender('A'), Gender.Agender);
    }

    /**
     *
     * @param c is the attribute to be checked for validity
     * @return if the character is valid
     */
    private boolean contains(char c) {
        boolean found = false;
        for (char c2 : validChar) {
            if (c == c2) {
                return true;
            }
        }
        return false;
    }
}