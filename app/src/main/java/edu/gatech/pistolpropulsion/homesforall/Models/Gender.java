package edu.gatech.pistolpropulsion.homesforall.Models;

/**
 * gender enum
 */
public enum Gender {
    Male('M'), Female('F'), Other('O'), Pangender('P'), Genderqueer('G'), Bigender('B'),
        Agender('A'), Invalid('I');

    private final char gender;

    Gender(char gender) {
        this.gender = gender;
    }

    /**
     *
     * @param c is the character to be checked for gender
     * @return the gender associated with c attribute
     */
    public static Gender getGender(char c) {
        Gender gender = Invalid;
        for (Gender g: values()) {
            if (g.gender == c) {
                gender = g;
            }
        }
        return gender;
    }
}