/*
 * Name: Yahya Angawi
 * Student ID: D00233709
 ** IMPORTANT NOTE**
 * I did not test the helper methods (methods that are used by other methods) as Kevin instucted us that we do not need to test them
 */
package oop19_ca2_yahya_angawi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main implements Serializable {
    // to fix ocal class incompatible: stream classdesc serialVersionUID different error

    private static final long serialVersionUID = 1L;
    private static Scanner sc = new Scanner(System.in);

    public Main() {
    }

    public static void main(String[] args) {

        Register reg = new Register();
        Owner o = null;
        Pet p = null;

        reg = readBinaryRegisterData(reg, "WirteToFile.txt");

        boolean exit = false;
        menu();

        while (exit == false) {
            try {
                System.out.print("\nChoose From The Menu: (17 to show available Options)> ");
                int action = sc.nextInt();
                sc.nextLine();

                switch (action) {
                    case 0:
                        System.out.println("Shutting down");
                        // kevin told us not to test wrting to binary file,and commeted this so that does not interfer with the output of the test
//                        writeBinaryToFile(reg, "WirteToFile.txt");
//                        System.out.println("Registry Was Written To WirteToFile.txt File");
                        exit = true;
                        break;

                    case 1:
//                        reg.readOwnerData("Ownerdata.txt");
//                        reg.readPetData("Petdata.txt");
//                        reg.readOwnerData("testOwnerdata.txt");
//                        reg.readPetData("testPetdata.txt");
                        reg = readBinaryRegisterData(reg, "WirteToFile.txt");
                        
                        break;

                    case 2:
                        Pet creatPet = createPet();
                        String Petownerid = creatPet.getOwnerID();
                        o = reg.findOwnerByID(Petownerid);
                        reg.addPet(o, creatPet);
                        break;
                    case 3:

                        System.out.println("Please Enter The Owner's name: ");
                        String name = sc.next();
                        System.out.println("Please Enter The Owner's ID (must be an integer): ");
                        int id = sc.nextInt();
                        System.out.println("Please Enter The Owner's email (In xxxx@xxx.xxx format): ");
                        String email = sc.next();
                        System.out.println("Please Enter The Owner's telephone (In 087-1234567 Format): ");
                        String telephone = sc.next();
                        System.out.println("Please Enter The Owner's address: ");
                        String address = sc.next();
                        o = new Owner(name, id, email, telephone, address);
                        reg.addOwner(o);
                        p = createPet();
                        reg.addPet(o, p);
                        break;
                    case 4:
                        System.out.println("Please Enter The Pet's ID (In PID123 Format) That You Want To Edit:");
                        String pid = sc.nextLine();
                        p = reg.findPetByID(pid);
                        System.out.println("Please Enter The Pet's New Name: ");
                        String pname = sc.nextLine();
                        System.out.println("Please Enter The Pet's New Date (In dd/mm/yyyy): ");
                        String date = sc.nextLine();
                        p.editPet(pname, date);
                        break;
                    case 5:
                        System.out.println("Please Enter The Pet's ID (In PID123 Format) That You Want To Remove:");
                        pid = sc.nextLine();
                        p = reg.findPetByID(pid);
                        o = reg.findOwnerByID(p.getOwnerID());
                        o.removePet(p);
                        break;

                    case 6:
                        System.out.println("Please Enter The Pet's ID (In PID123 Format) That You Want To Print:");
                        pid = sc.nextLine();
                        p = reg.findPetByID(pid);
                        System.out.println(p.toString());
                        break;

                    case 7:
                        System.out.println("Please Enter The Owner's name: ");
                        name = sc.next();
                        System.out.println("Please Enter The Owner's ID (must be an integer): ");
                        id = sc.nextInt();
                        System.out.println("Please Enter The Owner's email (In xxxx@xxx.xxx format): ");
                        email = sc.next();
                        System.out.println("Please Enter The Owner's telephone (In 087-1234567 Format): ");
                        telephone = sc.next();
                        System.out.println("Please Enter The Owner's address: ");
                        address = sc.next();
                        o = new Owner(name, id, email, telephone, address);
                        reg.addOwner(o);
                        break;

                    case 8:
                        System.out.println("Please Enter An existing Owner ID (In OID123 format) To Edit It:");
                        String idd = sc.nextLine();
                        System.out.println("Please Enter The Owner's new telephone (In 087-1234567 Format): ");
                        telephone = sc.next();
                        System.out.println("Please Enter The Owner's new address: ");
                        address = sc.next();
                        o = reg.findOwnerByID(idd);
                        reg.editAnOwner(o, telephone, address);
                        break;

                    case 9:
                        System.out.println("Please Enter existing Owner ID (In OID123 format) To Print It:");
                        idd = sc.nextLine();
                        o = reg.findOwnerByID(idd);
                        System.out.println(reg.printAnOwner(o));
                        break;

                    case 10:
                        System.out.println("Please Enter existing Owner ID (In OID123 format) To Remove It:");
                        idd = sc.nextLine();
                        o = reg.findOwnerByID(idd);
                        reg.removeAnOwner(o);
                        break;

                    case 11:
                        reg.displayAllPetsById();
                        break;

                    case 12:
                        reg.displayAllPetsByGender();
                        break;

                    case 13:
                        reg.displayAllPetsByAge();
                        break;

                    case 14:
                        System.out.println("Please Enter existing Owner ID (In OID123 format):");
                        idd = sc.nextLine();
                        o = reg.findOwnerByID(idd);
                        o.displayAllOwnerPetsByDate();

                        break;
                    case 15:
                        System.out.println("Please Enter Type Of Pet, M for Mammals, F for Fish or B for Birds:");
                        String type = sc.nextLine();
                        reg.displayAllPetsByType(type);
                        break;

                    case 16:
                        System.out.println(reg.getStatistics());
                        break;

                    case 17:
                        menu();
                        break;
                }
            } catch (Exception e) {
                System.out.println("Wrong Input!");
                sc.next();
                continue;
            }
        }
    }

    private static void menu() {
        System.out.println("\nAvailable options:\npress");
        System.out.println("0 - to shutdown\n"
                + "1 - To Read Owners And Pets Data From A Text File\n"
                + "2 - To Register A New Pet To An Existing Owner (using ID values)\n"
                + "3 - To Register A New Pet To A New Owner\n"
                + "4 - To Edit A Pet\n"
                + "5 - To Remove A Pet\n"
                + "6 - To Print A Pet\n"
                + "7 - To Register An Owner\n"
                + "8 - To Edit An Owner\n"
                + "9 - To Print An Owner\n"
                + "10 - To Remove An Owner\n"
                + "11 - To print of all pets Sorted By Their PetID\n"
                + "12 - To print of all pets Sorted By Their Gender\n"
                + "13 - To print of all pets Sorted By Their Age\n"
                + "14 - To Print all pets registered to an owner in date/time order\n"
                + "15 - To print Pets either Mammals, Fish or Birds\n"
                + "16 - To Generate Pet Statistics\n"
                + "17 - To Return To The Menu\n");
    }

    public static Register readBinaryRegisterData(Register reg, String file) {

        File f = new File(file);
        try {

            if (f.exists()) {
                ObjectInputStream in;
                in = new ObjectInputStream(new FileInputStream(f));
                reg = (Register) in.readObject();
//                System.out.println("File Was read");
                in.close();
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }
        return reg;
    }

    public static void writeBinaryToFile(Register reg, String file) {

        File f = new File(file);
        try {

            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream(f));
            out.writeObject(reg);
            out.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static Pet createPet() {

        Pet p = null;
        try {
            System.out.println("Please Enter The Pet Type, M for Mammals, F for Fish or B for Birds: ");
            String type = sc.next();
            System.out.println("Please Enter The Pet name: ");
            String name = sc.next();
            System.out.println("Please Enter The Pet breed: ");
            String breed = sc.next();
            System.out.println("Please Enter The Pet age: ");
            int age = sc.nextInt();
            System.out.println("Please Enter The Pet color: ");
            String color = sc.next();
            System.out.println("Please Enter The Pet gender (must be either M or F, case sensitive): ");
            Gender gender = Gender.valueOf(sc.next());
            System.out.println("Please Enter The Pet date (In dd/mm/yyyy): ");
            String date = sc.next();
            System.out.println("Please Enter The Pet's ID (In PID123 format): ");
            String pid = sc.next();
            System.out.println("Please Enter The Owner's ID (In OID123 format) That you want To Add The Pet To: ");
            String oid = sc.next();

            if (type.equalsIgnoreCase("M")) {
                System.out.println("Please Enter Whether The Pet Is Neutered or not (true or false): ");
                boolean neutered = sc.nextBoolean();
                p = new Mammal(type, name, breed, age, color, gender, date, pid, oid, neutered);
            } else if (type.equalsIgnoreCase("B")) {
                System.out.println("Please Enter The wingspan Of The Pet: ");
                double wingspan = sc.nextDouble();
                System.out.println("Please Enter Whether The Pet Can Fly or not (true pr false): ");
                boolean fly = sc.nextBoolean();
                p = new Bird(type, name, breed, age, color, gender, date, pid, oid, wingspan, fly);
            } else if (type.equalsIgnoreCase("F")) {
                System.out.println("Please Enter The Type Of Water: ");
                String water = sc.next();
                p = new Fish(type, name, breed, age, color, gender, date, pid, oid, water);
            }
        } catch (Exception e) {
            System.out.println("There Was A Wrong Input!");
        }

        return p;
    }

}
