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
public class ReadWriteBinaryTest {
    
    public ReadWriteBinaryTest() {
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

    /**IMPORTANT NOTE***
     * THE FOLLOWING TEST METHOD WORKS, BUT WHEN TEST IT WITHIN MAINTEST JUNIT CLASS, IT SOMEHOW CAUSE MAINTEST TO FAIL SOMETIMES WHEN REPEATING THE TEST 
     * 
     * Test of readBinaryRegisterData method, of class Main.
     */
    @Test
    public void testReadBinaryRegisterData() {
        System.out.println("readBinaryRegisterData");
        Register reg = new Register();
        String file = "WirteToFile.txt";
        Register expResult = new Register();
        // add the expected objects to the register
        Owner o = new Owner("yahya", 111, "yahya@yahoo.com", "087-1254845", "anything street dundalk");
        Pet p = new Mammal("M", "too", "Poodle", 4, "Black", Gender.M, "29/10/1999", "PID123", "OID111", true);
        Pet p2 = new Fish("F", "foo", "Poodle", 5, "Black", Gender.F, "30/10/2019", "PID124", "OID111", "freshwater");
        Pet p3 = new Bird("B", "su", "Poodle", 6, "Orange", Gender.F, "29/11/2009", "PID125", "OID111", 3.5, true);
        
        expResult.addOwner(o);
        expResult.addPet(o, p);
        expResult.addPet(o, p2);
        expResult.addPet(o, p3);
        // compare the created objects with what was read
        Register result = Main.readBinaryRegisterData(reg, file);
        assertEquals(expResult, result);
    }

    /**we have been told by kevin not to test Write Binary To File
     * Test of writeBinaryToFile method, of class Main.
     */
    @Test
    public void testWriteBinaryToFile() {
        System.out.println("writeBinaryToFile");
        Register reg = new Register();
        // add the expected objects to the register
        Owner o = new Owner("yahya", 111, "yahya@yahoo.com", "087-1254845", "anything street dundalk");
        Pet p = new Mammal("M", "too", "Poodle", 4, "Black", Gender.M, "29/10/1999", "PID123", "OID111", true);
        Pet p2 = new Fish("F", "foo", "Poodle", 5, "White", Gender.F, "30/10/2019", "PID124", "OID111", "freshwater");
        Pet p3 = new Bird("B", "su", "Poodle", 6, "Orange", Gender.F, "29/11/2009", "PID125", "OID111", 3.5, true);
        reg.addOwner(o);
        reg.addPet(o, p);
        reg.addPet(o, p2);
        reg.addPet(o, p3);
        String file = "WirteToFile.txt";
        Main.writeBinaryToFile(reg, file);

        Register expResult = new Register();
        expResult.addOwner(o);
        expResult.addPet(o, p);
        reg.addPet(o, p2);
        reg.addPet(o, p3);
        // compare the created objects with what was written by reading it firs
        Register result = Main.readBinaryRegisterData(reg, file);
        assertEquals(expResult, result);
    }
}
