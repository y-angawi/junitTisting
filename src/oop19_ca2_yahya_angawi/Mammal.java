/*
 * Name: Yahya Angawi
 * Student ID: D00233709
 ** IMPORTANT NOTE**
 * I did not test the helper methods (methods that are used by other methods) as Kevin instucted us that we do not need to test them
 */
package oop19_ca2_yahya_angawi;

import java.io.Serializable;

/**
 *
 * @author ASUSN56V
 */
public class Mammal extends Pet implements Serializable {

    private boolean neutered;

    public Mammal(String type, String name, String breed, int age, String colour, Gender gender, String date, String petId, String ownerID, boolean neutered) {
        super(type, name, breed, age, colour, gender, date, petId, ownerID);
        this.neutered = neutered;
    }

    public Mammal() {
    }

    public boolean isNeutered() {
        return neutered;
    }

    public void setNeutered(boolean neutered) {
        this.neutered = neutered;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + (this.neutered ? 1 : 0);
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
        final Mammal other = (Mammal) obj;
        if (this.neutered != other.neutered) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return super.toString() + " {" + "neutered= " + neutered + '}';
    }

}
