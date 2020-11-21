/*
 * Name: Yahya Angawi
 * Student ID: D00233709
 ** IMPORTANT NOTE**
 * I did not test the helper methods (methods that are used by other methods) as Kevin instucted us that we do not need to test them
 */
package oop19_ca2_yahya_angawi;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author ASUSN56V
 */
public class Fish extends Pet implements Serializable {

    private String water;

    public Fish(String type, String name, String breed, int age, String colour, Gender gender, String date, String petId, String ownerID, String water) {
        super(type, name, breed, age, colour, gender, date, petId, ownerID);
        this.water = water;
    }

    public String getWater() {
        return water;
    }

    public void setWater(String water) {
        this.water = water;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.water);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Fish other = (Fish) obj;
        if (!Objects.equals(this.water, other.water)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return super.toString() + " {" + "water= " + water + '}';
    }

}
