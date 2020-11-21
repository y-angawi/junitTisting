/*
 * Name: Yahya Angawi
 * Student ID: D00233709
 ** IMPORTANT NOTE**
 * I did not test the helper methods (methods that are used by other methods) as Kevin instucted us that we do not need to test them
 */
package oop19_ca2_yahya_angawi;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.InputStream;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

/**
 *
 * @author madwolff
 */
public class MainTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    public MainTest() {

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
     * Test of main method, of class Main.
     */
    @Test
    public void testMain() {
        System.out.println("main test");
        // remove what was printed to the console 
        outContent.reset(); 
        // test the following inputs
        systemInMock.provideLines("1", "2", "m", "hoi", "Poodle", "2", "black", "M", 
                  "22/02/2002", "PID199", "OID111", "true", "9", "OID111", "6", "PID123", "16", "-2", "K", "0");

        String[] args = null;
        Main.main(args);

        String menu1 = "\nAvailable options:\npress" + System.getProperty("line.separator");
        String menu2 = "0 - to shutdown\n";
        String menu3 = "1 - To Read Owners And Pets Data From A Text File\n";
        String menu4 = "2 - To Register A New Pet To An Existing Owner (using ID values)\n";
        String menu5 = "3 - To Register A New Pet To A New Owner\n";
        String menu6 = "4 - To Edit A Pet\n";
        String menu7 = "5 - To Remove A Pet\n";
        String menu8 = "6 - To Print A Pet\n";
        String menu9 = "7 - To Register An Owner\n";
        String menu10 = "8 - To Edit An Owner\n";
        String menu11 = "9 - To Print An Owner\n";
        String menu12 = "10 - To Remove An Owner\n";
        String menu13 = "11 - To print of all pets Sorted By Their PetID\n";
        String menu14 = "12 - To print of all pets Sorted By Their Gender\n";
        String menu15 = "13 - To print of all pets Sorted By Their Age\n";
        String menu16 = "14 - To Print all pets registered to an owner in date/time order\n";
        String menu17 = "15 - To print Pets either Mammals, Fish or Birds\n";
        String menu18 = "16 - To Generate Pet Statistics\n";
        String menu19 = "17 - To Return To The Menu\n" + System.getProperty("line.separator");
        String menu = menu1 + menu2 + menu3 + menu4 + menu5 + menu6 + menu7 + menu8 + menu9 + menu10 + menu11
                + menu12 + menu13 + menu14 + menu15 + menu16 + menu17 + menu18 + menu19;

        String prompt = "\nChoose From The Menu: (17 to show available Options)> ";
        String Shut = "Shutting down" + System.getProperty("line.separator");
        String addpet = "Please Enter The Pet Type, M for Mammals, F for Fish or B for Birds: " + System.getProperty("line.separator")
                + "Please Enter The Pet name: " + System.getProperty("line.separator")
                + "Please Enter The Pet breed: " + System.getProperty("line.separator")
                + "Please Enter The Pet age: " + System.getProperty("line.separator")
                + "Please Enter The Pet color: " + System.getProperty("line.separator")
                + "Please Enter The Pet gender (must be either M or F, case sensitive): " + System.getProperty("line.separator")
                + "Please Enter The Pet date (In dd/mm/yyyy): " + System.getProperty("line.separator")
                + "Please Enter The Pet's ID (In PID123 format): " + System.getProperty("line.separator")
                + "Please Enter The Owner's ID (In OID123 format) That you want To Add The Pet To: " + System.getProperty("line.separator")
                + "Please Enter Whether The Pet Is Neutered or not (true or false): " + System.getProperty("line.separator")
                + "Pet is Added To Owner With ID Of OID111" + System.getProperty("line.separator")
                + "Pet is Added To Register" + System.getProperty("line.separator");

        String ownerPrint = "Please Enter existing Owner ID (In OID123 format) To Print It:" + System.getProperty("line.separator")
                + "Owner {name= yahya, oid= OID111, email= yahya@yahoo.com, telephone= 087-1254845, address= anything street dundalk}" + System.getProperty("line.separator");
        String petPrint = "Please Enter The Pet's ID (In PID123 Format) That You Want To Print:" + System.getProperty("line.separator")
                + "Mammal {name=too, type=M, breed=Poodle, colour=Black, date=29/10/1999, ownerID=OID111, age=4, gender=M, petId=PID123} {neutered= true}"
                + System.getProperty("line.separator");
        String stat = "There Are 4 Pet(s), There Are 2.0 Mammal(s), 1.0 Bird(s) And 1.0 Fish.\n"
                + "The Percentage Of Pets Types Are As follows:\n"
                + " Mammal 50.00%\n"
                + "Bird 25.00%\n"
                + "Fish 25.00%"+ System.getProperty("line.separator");
        String wrong = "Wrong Input!" +System.getProperty("line.separator");

        String expOutput = menu + prompt + prompt +addpet+ prompt +ownerPrint+prompt +petPrint+prompt +stat+prompt+prompt+wrong+prompt +Shut;
        // compare the expected output with what was printed to the console
        assertEquals(expOutput, outContent.toString());
    }

}
