/*
 * Name: Yahya Angawi
 * Student ID: D00233709
 ** IMPORTANT NOTE**
 * I did not test the helper methods (methods that are used by p1 methods) as Kevin instucted us that we do not need to test them
 */
package oop19_ca2_yahya_angawi;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author madwolff
 */
public class PetTest {
    
    public PetTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of editPet method, of class Pet.
     */
    @Test
    public void testEditPet() {
        System.out.println("editPet");
        String name = "su";
        String date = "22/02/2002";
        Pet p = new Bird("B", "mo", "Poodle", 6, "Black", Gender.M, "29/10/2000", "PID125", "OID113", 2.5, true);
        // edit a Pet and test it by printing it
        p.editPet(name, date);
        String expResult = "Bird {name=su, type=B, breed=Poodle, colour=Black, date=22/02/2002, ownerID=OID113, age=6, gender=M, petId=PID125} {wingspan= 2.5, fly= true}";
        String result = p.toString();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of compareTo method, of class Pet.
     */
    @Test
    public void testCompareTo() {
        System.out.println("compareTo");
        // IDs are the same
        Pet p1 = new Bird("B", "mo", "Poodle", 6, "Black", Gender.M, "29/10/2000", "PID125", "OID113", 2.5, true);
        Pet p2 = new Bird("B", "mo", "Poodle", 6, "Black", Gender.M, "29/10/2000", "PID125", "OID113", 2.5, true);
        int expResult = 0;
        int result = p2.compareTo(p1);
        assertEquals(expResult, result);
        // p2's id is biger, sort with increasing order
        p1 = new Bird("B", "mo", "Poodle", 6, "Black", Gender.M, "29/10/2000", "PID125", "OID113", 2.5, true);
        p2 = new Bird("B", "mo", "Poodle", 6, "Black", Gender.M, "29/10/2000", "PID128", "OID113", 2.5, true);
        expResult = 1;
        result = p2.compareTo(p1);
        assertEquals(expResult, result);
        // p2's id is smaller, , sort with increasing order
        p1 = new Bird("B", "mo", "Poodle", 6, "Black", Gender.M, "29/10/2000", "PID125", "OID113", 2.5, true);
        p2 = new Bird("B", "mo", "Poodle", 6, "Black", Gender.M, "29/10/2000", "PID123", "OID113", 2.5, true);
        expResult = -1;
        result = p2.compareTo(p1);
        assertEquals(expResult, result);

    }

    /**
     * Test of toString method, of class Pet.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Pet p = new Bird("B", "mo", "Poodle", 6, "Black", Gender.M, "29/10/2000", "PID125", "OID113", 2.5, true);
       // print a pet
        String expResult = "Bird {name=mo, type=B, breed=Poodle, colour=Black, date=29/10/2000, ownerID=OID113, age=6, gender=M, petId=PID125} {wingspan= 2.5, fly= true}";
        String result = p.toString();
        assertEquals(expResult, result);
    }
    
}
