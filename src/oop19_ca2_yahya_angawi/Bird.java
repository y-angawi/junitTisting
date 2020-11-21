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
public class Bird extends Pet implements Serializable {

    private double wingspan;
    private boolean fly;

    public Bird(String type, String name, String breed, int age, String colour, Gender gender, String date, String petId, String ownerID, double wingspan, boolean fly) {
        super(type, name, breed, age, colour, gender, date, petId, ownerID);
        this.wingspan = wingspan;
        this.fly = fly;
    }

    public double getWingspan() {
        return wingspan;
    }

    public void setWingspan(double wingspan) {
        this.wingspan = wingspan;
    }

    public boolean isFly() {
        return fly;
    }

    public void setFly(boolean fly) {
        this.fly = fly;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + (int) (Double.doubleToLongBits(this.wingspan) ^ (Double.doubleToLongBits(this.wingspan) >>> 32));
        hash = 53 * hash + (this.fly ? 1 : 0);
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
        final Bird other = (Bird) obj;
        if (Double.doubleToLongBits(this.wingspan) != Double.doubleToLongBits(other.wingspan)) {
            return false;
        }
        if (this.fly != other.fly) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return super.toString() + " {" + "wingspan= " + wingspan + ", fly= " + fly + '}';
    }

}
