/*
 * Name: Yahya Angawi
 * Student ID: D00233709
 ** IMPORTANT NOTE**
 * I did not test the helper methods (methods that are used by other methods) as Kevin instucted us that we do not need to test them
 */
package oop19_ca2_yahya_angawi;

import java.util.Comparator;

/**
 *
 * @author madwolff
 */
public class PetGenderComparator implements Comparator<Pet> {

    public int compare(Pet a, Pet b) {

        return a.getGender().compareTo(b.getGender());
    }

}
