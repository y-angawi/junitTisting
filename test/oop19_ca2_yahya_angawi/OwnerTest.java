/*
 * Name: Yahya Angawi
 * Student ID: D00233709
 ** IMPORTANT NOTE**
 * I did not test the helper methods (methods that are used by other methods) as Kevin instucted us that we do not need to test them
 */
package oop19_ca2_yahya_angawi;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;
import org.junit.runner.Description;

/**
 *
 * @author madwolff
 */
public class OwnerTest {
    
    private Owner o = new Owner("yahya", 113, "yahya@yahoo.com", "532-4545644", "dundalk");
     private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    
    public OwnerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
    }
    
    @After
    public void tearDown() {
        System.setOut(null);
    }
    
    @Rule
    public final TextFromStandardInputStream systemInMock = emptyStandardInputStream();

    /**
     * Test of addOwnerPet method, of class Owner.
     */
    @Test
    public void testAddOwnerPet() {
        System.out.println("addOwnerPet");
        Pet p = new Bird("B", "su", "Poodle", 6, "Black", Gender.M, "29/10/2000", "PID125", "OID113", 2.5, true);
        // test add a pet to an owner
        o.addOwnerPet(p);
        assertEquals("Adding 1 more Pet to list", 1, o.arraylistSize());
        outContent.reset();
        // test add a pet that already exists
        o.addOwnerPet(p);
        String expOutput = "Pet is already exists"+System.getProperty("line.separator");
        assertEquals(expOutput, outContent.toString());
    }
    
     @Test
    public void testPrintAPet() {
        System.out.println("printAPet");
        
        Pet p = new Bird("B", "su", "Poodle", 6, "Black", Gender.M, "29/10/2000", "PID125", "OID113", 2.5, true);

        o.addOwnerPet(p);
         outContent.reset();
          // print a pet that exist
        o.printAPet(p);
        String expOutput = p.toString() + System.getProperty("line.separator");
        assertEquals(expOutput, outContent.toString());
//        // print a pet that does not exist
        o.removePet(p);
        assertEquals("Removing a Pet from list", 0, o.arraylistSize());
        outContent.reset();
        o.printAPet(p);
        expOutput = "Pet Does Not exists"+System.getProperty("line.separator");
        assertEquals(expOutput, outContent.toString());
    }
    
    @Test
    public void testEditAPet() {
        System.out.println("editAPet");
        
        Pet p = new Bird("B", "su", "Poodle", 6, "Black", Gender.M, "29/10/2000", "PID125", "OID113", 2.5, true);

        o.addOwnerPet(p);
         // edit a pet and test it
        o.editAPet(p, "sed", "22/02/2002");
       outContent.reset();
        o.printAPet(p);
        String expOutput = "Bird {name=sed, type=B, breed=Poodle, colour=Black, date=22/02/2002, ownerID=OID113, age=6, gender=M, petId=PID125} {wingspan= 2.5, fly= true}"+ System.getProperty("line.separator");
        assertEquals(expOutput, outContent.toString());
        // remove an existing pet
        o.removePet(p);
        assertEquals("Removing 1 more Pet to list", 0, o.arraylistSize());
        outContent.reset();
        // edit a removed pet
        o.editAPet(p, "sed", "22/02/2002");
        expOutput = "Pet Does Not exists"+System.getProperty("line.separator");
        assertEquals(expOutput, outContent.toString());
    }
    
    @Test
    public void testRemovePet() {
        System.out.println("removePet");
        Pet p = new Bird("B", "su", "Poodle", 6, "Black", Gender.M, "29/10/2000", "PID125", "OID113", 2.5, true);

        o.addOwnerPet(p);
        assertEquals("adding 1 more Pet to list", 1, o.arraylistSize());
        
        // remove an existing pet
        o.removePet(p);
        assertEquals("Removing a Pet to list", 0, o.arraylistSize());
        outContent.reset();
        // print a removed pet, to test that it does not exist
        o.printAPet(p);
        String expOutput = "Pet Does Not exists"+System.getProperty("line.separator");
        assertEquals(expOutput, outContent.toString());
        outContent.reset();
        // remove a pet that does not exist
        o.removePet(p);
        String expOutput2 = "Pet Does Not exists"+System.getProperty("line.separator");
        assertEquals(expOutput2, outContent.toString());
    }
    
    /**
     * Test of toString method, of class Owner.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Owner o = new Owner("yahya", 101, "yahya@yahoo.com", "532-4545644", "dundalk");
        String expResult = "Owner {name= yahya, oid= OID101, email= yahya@yahoo.com, telephone= 532-4545644, address= dundalk}";
        // print an owner
        String result = o.toString();
        assertEquals(expResult, result);
        
    }
    /**
     * Test of displayAllOwnerPetsByDate method, of class Owner.
     */
    @Test
    public void testDisplayAllOwnerPetsByDate() {
        System.out.println("displayAllOwnerPetsByDate");
        // create the objects
        Owner o = new Owner("yahya", "yahya@yahoo.com", "532-4545644", "dundalk");
        Pet p = new Bird("B", "mo", "Poodle", 6, "Black", Gender.M, "29/10/2000", "PID125", "OID113", 2.5, true);
        Pet p2 = new Bird("B", "su", "Poodle", 6, "Black", Gender.M, "29/10/2001", "PID128", "OID113", 2.5, true);
        
        o.addOwnerPet(p);
        o.addOwnerPet(p2);
        outContent.reset();
        // sort by date, increasing
        o.displayAllOwnerPetsByDate();
        String expResult = "Bird {name=mo, type=B, breed=Poodle, colour=Black, date=29/10/2000, ownerID=OID113, age=6, gender=M, petId=PID125} {wingspan= 2.5, fly= true}" + System.getProperty("line.separator")
                + "Bird {name=su, type=B, breed=Poodle, colour=Black, date=29/10/2001, ownerID=OID113, age=6, gender=M, petId=PID128} {wingspan= 2.5, fly= true}" + System.getProperty("line.separator");
        assertEquals(expResult, outContent.toString());
        
    }
}
