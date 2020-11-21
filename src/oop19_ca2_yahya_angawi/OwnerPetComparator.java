/*
 * Name: Yahya Angawi
 * Student ID: D00233709
 ** IMPORTANT NOTE**
 * I did not test the helper methods (methods that are used by other methods) as Kevin instucted us that we do not need to test them
 */
package oop19_ca2_yahya_angawi;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

/**
 *
 * @author madwolff
 */
public class OwnerPetComparator implements Comparator<Pet> {

    //  dd/mm/yyyy 
    public int compare(Pet a, Pet b) {

//       
        if (Integer.parseInt(a.getDate().substring(6)) < Integer.parseInt(b.getDate().substring(6))) {
            return -1;
        } else if (Integer.parseInt(a.getDate().substring(6)) == Integer.parseInt(b.getDate().substring(6))) {

            if (Integer.parseInt(a.getDate().substring(3, 5)) < Integer.parseInt(b.getDate().substring(3, 5))) {
                return -1;

            } else if (Integer.parseInt(a.getDate().substring(3, 5)) == Integer.parseInt(b.getDate().substring(3, 5))) {
                if (Integer.parseInt(a.getDate().substring(0, 2)) < Integer.parseInt(b.getDate().substring(0, 2))) {
                    return -1;
                } else if (Integer.parseInt(a.getDate().substring(0, 2)) == Integer.parseInt(b.getDate().substring(0, 2))) {
                    return 0;
                } else {
                    return 1;
                }
            }
            return 0;
        } else {
            return 1;
        }
    }

}
