/*
 * Name: Yahya Angawi
 * Student ID: D00233709
 ** IMPORTANT NOTE**
 * I did not test the helper methods (methods that are used by other methods) as Kevin instucted us that we do not need to test them
 */
package oop19_ca2_yahya_angawi;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author madwolff
 */
public class RegisterTest {
    
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private Owner o = new Owner("yahya", 101, "yahya@yahoo.com", "532-4545644", "dundalk");
    Register reg = new Register();
    
    public RegisterTest() {
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

    /**
     * Test of addOwner method, of class Register.
     */
    @Test
    public void testAddOwner() {
        System.out.println("addAnOwner");
        // adding an owner and test that it exists by printing it
        reg.addOwner(o);
        String result = reg.printAnOwner(o);
        String expResult = "Owner {name= yahya, oid= OID101, email= yahya@yahoo.com, telephone= 532-4545644, address= dundalk}";
        assertEquals(expResult, result);
        assertEquals("Adding 1 more Pet to list", 1, reg.arraylistSize());
        // adding the same owner
        Owner o2 = new Owner("yahya", 101, "yahya@yahoo.com", "532-4545644", "dundalk");
        // remove what was printed to the console
        outContent.reset();
        reg.addOwner(o2);
        String expOutput = "Owner is already exists"+System.getProperty("line.separator");
         assertEquals("Adding the same owner Pet to list", 1, reg.arraylistSize());
         assertEquals(expOutput, outContent.toString());
         
        
    }

    /**
     * Test of removeAnOwner method, of class Register.
     */
    @Test
    public void testRemoveAnOwner() {
        System.out.println("removeAnOwner");
        // first adding an owner
        reg.addOwner(o);
        assertEquals("adding 1 Pet to list", 1, reg.arraylistSize());
        // removing the added owner
        reg.removeAnOwner(o);
        // two ways to test that the oet no longer exists
        assertEquals("removing 1 Pet to list", 0, reg.arraylistSize());
        Owner result = reg.findOwnerByID("OID101");
        // remove what was printed to the console
        assertNull(result);
        outContent.reset();
        // removing an owner that does not exists in the register.
        Owner o2 = new Owner("yahya", 111, "yahya@yahoo.com", "532-4545644", "dundalk");
        reg.removeAnOwner(o2);
        String expOutput = "Owner Does Not exists"+System.getProperty("line.separator");
         assertEquals("Adding the same owner Pet to list", 0, reg.arraylistSize());
         assertEquals(expOutput, outContent.toString());
    }

    /**
     * Test of printAnOwner method, of class Register.
     */
    @Test
    public void testPrintAnOwner() {
        System.out.println("printAnOwner");
        // printing an owner that exists in the register.
        reg.addOwner(o);
        String expResult = "Owner {name= yahya, oid= OID101, email= yahya@yahoo.com, telephone= 532-4545644, address= dundalk}";
        String result = reg.printAnOwner(o);
        assertEquals(expResult, result);
        // printing an owner that does not exists in the register.
        Owner o2 = new Owner("yahya", 111, "yahya@yahoo.com", "532-4545644", "dundalk");
        // remove what was printed to the console
        outContent.reset();
        reg.printAnOwner(o2);
        String expOutput = "Owner Does Not exists"+System.getProperty("line.separator");
         assertEquals(expOutput, outContent.toString());
    }

    /**
     * Test of editAnOwner method, of class Register.
     */
    @Test
    public void testEditAnOwner() {
        System.out.println("editAnOwner");
        // edit an owner that exists
        reg.addOwner(o);
        String phone = "087-1234567";
        String address = "whatever dundalk";
        reg.editAnOwner(o, phone, address);
        String expResult = "Owner {name= yahya, oid= OID101, email= yahya@yahoo.com, telephone= 087-1234567, address= whatever dundalk}";
        String result = reg.printAnOwner(o);
        assertEquals(expResult, result);
        // remove what was printed to the console
        outContent.reset();
        // edit an owner that does not exists
        Owner o2 = new Owner("yahya", 222, "yahya@yahoo.com", "532-4545644", "dundalk");
        reg.editAnOwner(o2, phone, address);
        String expOutput = "Owner Does Not exists"+System.getProperty("line.separator");

       assertEquals(expOutput, outContent.toString());
    }

    /**
     * Test of addPet method, of class Register.
     */
    @Test
    public void testAddPet() {
        System.out.println("addPet");
        // add owner and pet
        Owner o = new Owner("yahya", 111, "yahya@yahoo.com", "532-4545644", "dundalk");
        reg.addOwner(o);
       Pet p = new Bird("B", "mo", "Poodle", 6, "Black", Gender.M, "29/10/2000", "PID125", "OID113", 2.5, true);
        reg.addPet(o, p);
        // if it found, that is mean it is added
       Pet result = reg.findPetByID("PID125");
       // testing the change in arraylist size, which will increase
       assertEquals( 1, o.arraylistSize());
       assertEquals(p, result);
       // adding a pet that already added
       // remove what was printed to the console
       outContent.reset();
       reg.addPet(o, p);
       String expOutput = "Pet is already exists"+System.getProperty("line.separator")+"Pet is Added To Register"+System.getProperty("line.separator");
         assertEquals(expOutput, outContent.toString()); 
    }

    /**
     * Test of readOwnerData method, of class Register.
     */
    @Test
    public void testReadOwnerData() {
        System.out.println("readOwnerData");
        // reading an owner from a binary file
        String file = "testOwnerdata.txt";
        reg.readOwnerData(file);
        // if the owner fount and printed that is means it exists
        Owner o2 = reg.findOwnerByID("OID111");
        String result = o2.toString();
        String expResult = "Owner {name= yahya, oid= OID111, email= yahya@yahoo.com, telephone= 087-1254845, address= anything street dundalk}";
        assertEquals(expResult, result);
    }

    /**
     * Test of readPetData method, of class Register.
     */
    @Test
    public void testReadPetData() {
        System.out.println("readPetData");
        // reading a pet from a binary file
        String file = "testPetdata.txt";
        Owner o = new Owner("yahya", 111, "yahya@yahoo.com", "532-4545644", "dundalk");
        reg.addOwner(o);
        reg.readPetData(file);
        // if the pet fount and printed that is means it exists
        Pet p = reg.findPetByID("PID123");
        String result = p.toString();
        String expResult = "Mammal {name=too, type=M, breed=Poodle, colour=Black, date=29/10/1999, ownerID=OID111, age=4, gender=M, petId=PID123} {neutered= true}";
        assertEquals(expResult, result);
    }

    /**
     * Test of findOwnerByID method, of class Register.
     */
    @Test
    public void testFindOwnerByID() {
        System.out.println("findOwnerByID");
        String id = "OID111";
        Owner expResult = new Owner("yahya", 111, "yahya@yahoo.com", "532-4545644", "dundalk");
        Register instance = new Register();
        instance.addOwner(expResult);
        // trying to find an owner that exists
        Owner result = instance.findOwnerByID(id);
        assertEquals(expResult, result);
        // trying to find an owner that does not exists
        Owner result2 = reg.findOwnerByID("OID101");
        assertNull(result2);
    }
//
//    /**
//     * Test of findPetByID method, of class Register.
//     */
    @Test
    public void testFindPetByID() {
        System.out.println("findPetByID");
        String id = "PID123";
        Owner o = new Owner("yahya", 111, "yahya@yahoo.com", "532-4545644", "dundalk");
        Pet expResult = new Bird("B", "mo", "Poodle", 6, "Black", Gender.M, "29/10/2000", "PID123", "OID111", 2.5, true);
        Register reg = new Register();
        reg.addOwner(o);
        o.addOwnerPet(expResult);
        // trying to find a pet that exists
        Pet result = reg.findPetByID(id);
        assertEquals(expResult, result);
        // trying to find a pet that does not exists
        Pet result2 = reg.findPetByID("PID155");
        assertNull(result2);
    }

   
    /**
     * Test of getStatistics method, of class Register.
     */
    @Test
    public void testGetStatistics() {
        System.out.println("getStatistics");
        // creating the owner and add the pets to it
        Owner o = new Owner("yahya", 111, "yahya@yahoo.com", "532-4545644", "dundalk");
        reg.addOwner(o);
        Pet p = new Bird("B", "mo", "Poodle", 6, "Black", Gender.M, "29/10/2000", "PID125", "OID111", 2.5, true);
        Pet p2 = new Mammal("M", "too", "Poodle", 4, "Black", Gender.M, "29/10/1999", "PID123", "OID111", true);
        Pet p3 = new Fish("F", "fish", "Poodle", 2, "Black", Gender.F, "29/10/1999", "PID128", "OID111", "sea");
        reg.addPet(o, p);
        reg.addPet(o, p2);
        reg.addPet(o, p3);
        double petype = 1;
        int petCount = 3;
        // generate a statistics of pet types
        DecimalFormat dec = new DecimalFormat("#0.00");
        double mperc = (petype / petCount) * 100;
        double bperc = (petype / petCount) * 100;
        double fperc = (petype / petCount) * 100;
        
        String expResult = "There Are " + petCount + " Pet(s), There Are " + petype + " Mammal(s), " + petype + " Bird(s) And " + petype + " Fish.\n"
                + "The Percentage Of Pets Types Are As follows:\n "
                + "Mammal " + dec.format(mperc) + "%\n"
                + "Bird " + dec.format(bperc) + "%\n"
                + "Fish " + dec.format(fperc) + "%";
        String result = reg.getStatistics();
        assertEquals(expResult, result);
    }

    /**
     * Test of displayAllPetsById method, of class Register.
     */
    @Test
    public void testDisplayAllPetsById() {
        System.out.println("displayAllPetsById");
        Owner o = new Owner("yahya", 111, "yahya@yahoo.com", "532-4545644", "dundalk");
        reg.addOwner(o);
        Pet p = new Bird("B", "su", "Poodle", 6, "Orange", Gender.F, "29/11/2009", "PID125", "OID111", 3.5, true);
        Pet p2 = new Mammal("M", "too", "Poodle", 4, "Black", Gender.M, "29/10/1999", "PID123", "OID111", true);
        Pet p3 = new Fish("F", "foo", "Poodle", 5, "White", Gender.F, "30/10/2019", "PID124", "OID111", "freshwater");
        reg.addPet(o, p);
        reg.addPet(o, p2);
        reg.addPet(o, p3);
        String expOutput = "Mammal {name=too, type=M, breed=Poodle, colour=Black, date=29/10/1999, ownerID=OID111, age=4, gender=M, petId=PID123} {neutered= true}" + System.getProperty("line.separator")+
                           "Fish {name=foo, type=F, breed=Poodle, colour=White, date=30/10/2019, ownerID=OID111, age=5, gender=F, petId=PID124} {water= freshwater}"+ System.getProperty("line.separator") +
                           "Bird {name=su, type=B, breed=Poodle, colour=Orange, date=29/11/2009, ownerID=OID111, age=6, gender=F, petId=PID125} {wingspan= 3.5, fly= true}"+ System.getProperty("line.separator");
        // remove what was printed to the console
        outContent.reset();
        // sort by pet id, increasing
        reg.displayAllPetsById();
        assertEquals(expOutput, outContent.toString());
    }

    /**
     * Test of displayAllPetsByGender method, of class Register.
     */
    @Test
    public void testDisplayAllPetsByGender() {
        System.out.println("displayAllPetsByGender");
       Owner o = new Owner("yahya", 111, "yahya@yahoo.com", "532-4545644", "dundalk");
        reg.addOwner(o);
        Pet p = new Mammal("M", "too", "Poodle", 4, "Black", Gender.M, "29/10/1999", "PID123", "OID111", true);
        Pet p2 = new Fish("F", "foo", "Poodle", 5, "White", Gender.F, "30/10/2019", "PID124", "OID111", "freshwater");
        Pet p3 = new Bird("B", "su", "Poodle", 6, "Orange", Gender.F, "29/11/2009", "PID125", "OID111", 3.5, true);
        
        reg.addPet(o, p);
        reg.addPet(o, p2);
        reg.addPet(o, p3);
        String expOutput = "Mammal {name=too, type=M, breed=Poodle, colour=Black, date=29/10/1999, ownerID=OID111, age=4, gender=M, petId=PID123} {neutered= true}" + System.getProperty("line.separator")+
                           "Fish {name=foo, type=F, breed=Poodle, colour=White, date=30/10/2019, ownerID=OID111, age=5, gender=F, petId=PID124} {water= freshwater}"+ System.getProperty("line.separator") +
                           "Bird {name=su, type=B, breed=Poodle, colour=Orange, date=29/11/2009, ownerID=OID111, age=6, gender=F, petId=PID125} {wingspan= 3.5, fly= true}"+ System.getProperty("line.separator");
        // remove what was printed to the console
        outContent.reset();
        // sort by pet Gender, M then F
        reg.displayAllPetsByGender();
        assertEquals(expOutput, outContent.toString());
    }

    /**
     * Test of displayAllPetsByAge method, of class Register.
     */
    @Test
    public void testDisplayAllPetsByAge() {
        System.out.println("displayAllPetsByAge");
       Owner o = new Owner("yahya", 111, "yahya@yahoo.com", "532-4545644", "dundalk");
        reg.addOwner(o);
        Pet p = new Mammal("M", "too", "Poodle", 4, "Black", Gender.M, "29/10/1999", "PID123", "OID111", true);
        Pet p2 = new Fish("F", "foo", "Poodle", 5, "White", Gender.F, "30/10/2019", "PID124", "OID111", "freshwater");
        Pet p3 = new Bird("B", "su", "Poodle", 6, "Orange", Gender.F, "29/11/2009", "PID125", "OID111", 3.5, true);
        
        reg.addPet(o, p);
        reg.addPet(o, p2);
        reg.addPet(o, p3);
        String expOutput = "Mammal {name=too, type=M, breed=Poodle, colour=Black, date=29/10/1999, ownerID=OID111, age=4, gender=M, petId=PID123} {neutered= true}" + System.getProperty("line.separator")+
                           "Fish {name=foo, type=F, breed=Poodle, colour=White, date=30/10/2019, ownerID=OID111, age=5, gender=F, petId=PID124} {water= freshwater}"+ System.getProperty("line.separator") +
                           "Bird {name=su, type=B, breed=Poodle, colour=Orange, date=29/11/2009, ownerID=OID111, age=6, gender=F, petId=PID125} {wingspan= 3.5, fly= true}"+ System.getProperty("line.separator");
        // remove what was printed to the console
        outContent.reset();
        // sort by pet age, increasing
        reg.displayAllPetsByAge();
        assertEquals(expOutput, outContent.toString());
    }

    /**
     * Test of displayAllOwnerPetsByDate method, of class Register.
     */
    @Test
    public void testDisplayAllOwnerPetsByDate() {
        System.out.println("displayAllOwnerPetsByDate");
        String oid = "OID111";
       Owner o = new Owner("yahya", 111, "yahya@yahoo.com", "532-4545644", "dundalk");
        reg.addOwner(o);
        Pet p = new Bird("B", "su", "Poodle", 6, "Orange", Gender.F, "29/11/2009", "PID125", "OID111", 3.5, true);
        Pet p2 = new Mammal("M", "too", "Poodle", 4, "Black", Gender.M, "29/10/1999", "PID123", "OID111", true);
        Pet p3 = new Fish("F", "foo", "Poodle", 5, "White", Gender.F, "30/10/2019", "PID124", "OID111", "freshwater");
        reg.addPet(o, p);
        reg.addPet(o, p2);
        reg.addPet(o, p3);
        String expOutput = "Mammal {name=too, type=M, breed=Poodle, colour=Black, date=29/10/1999, ownerID=OID111, age=4, gender=M, petId=PID123} {neutered= true}" + System.getProperty("line.separator")+
                            "Bird {name=su, type=B, breed=Poodle, colour=Orange, date=29/11/2009, ownerID=OID111, age=6, gender=F, petId=PID125} {wingspan= 3.5, fly= true}"+ System.getProperty("line.separator")+
                           "Fish {name=foo, type=F, breed=Poodle, colour=White, date=30/10/2019, ownerID=OID111, age=5, gender=F, petId=PID124} {water= freshwater}"+ System.getProperty("line.separator");
        // remove what was printed to the console
        outContent.reset();
        // sort by pet date, increasing
        reg.displayAllOwnerPetsByDate(oid);
        assertEquals(expOutput, outContent.toString());
    }

    /**
     * Test of displayAllPetsByType method, of class Register.
     */
    @Test
    public void testDisplayAllPetsByType() {
        System.out.println("displayAllPetsByType");
        String s = "M";
        Owner o = new Owner("yahya", 111, "yahya@yahoo.com", "532-4545644", "dundalk");
        reg.addOwner(o);
       Pet p = new Bird("B", "su", "Poodle", 6, "Orange", Gender.F, "29/11/2009", "PID125", "OID111", 3.5, true);
        Pet p2 = new Mammal("M", "too", "Poodle", 4, "Black", Gender.M, "29/10/1999", "PID123", "OID111", true);
        Pet p3 = new Fish("F", "foo", "Poodle", 5, "White", Gender.F, "30/10/2019", "PID124", "OID111", "freshwater");
        reg.addPet(o, p);
        reg.addPet(o, p2);
        reg.addPet(o, p3);
        String expOutput = "Mammal {name=too, type=M, breed=Poodle, colour=Black, date=29/10/1999, ownerID=OID111, age=4, gender=M, petId=PID123} {neutered= true}" + System.getProperty("line.separator");
        // remove what was printed to the console
        outContent.reset();
        reg.displayAllPetsByType(s);
        assertEquals(expOutput, outContent.toString());
        // test type does not exists
        String expOutput2 = "Pet type Does Not Exist" + System.getProperty("line.separator");
        // remove what was printed to the console
        outContent.reset();
        s = "L";
        reg.displayAllPetsByType(s);
        assertEquals(expOutput2, outContent.toString());
    }
    
}
