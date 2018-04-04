package edu.gatech.pistolpropulsion.homesforall.Models;

/**
 * Created by cjboylston on 3/5/18. Enum class to represent different age groups
 * of shelter seekers.
 */
public enum AgeGroup {

        INVALID(-1), NEWBORN(0), CHILD(5), YOUNGADULT(18), ADULT(26), SENIOR(55);

        private final int age;

        AgeGroup(int age) {
            this.age = age;
        }

        public static AgeGroup getGroup(int age) {

            AgeGroup group = INVALID;
            for (AgeGroup ag : values()) {
                if (ag.age <= age) {
                    group = ag;
                }
            }
            return group;
        }

}
