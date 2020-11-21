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
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/* test (junit) equal and compair not hash and tostring*/
public class Register implements Serializable {

    // to fix ocal class incompatible: stream classdesc serialVersionUID different error
    private static final long serialVersionUID = 1L;
    private ArrayList<Owner> ownerList;

    public Register() {

        ownerList = new ArrayList<Owner>();
    }

    public void addOwner(Owner o) {

        int index = findOwner(o);
        if (index < 0) {
            ownerList.add(o);
            System.out.println("Owner is Added");
        } else {
            System.out.println("Owner is already exists");
        }

    }

    public void removeAnOwner(Owner o) {

        int index = findOwner(o);
        if (index < 0) {
            System.out.println("Owner Does Not exists");
        } else {
            ownerList.remove(o);
            System.out.println("Owner is Removed");
        }
    }

    public String printAnOwner(Owner o) {

        int index = findOwner(o);
        if (index < 0) {
            System.out.println("Owner Does Not exists");
        }else{ return ownerList.get(index).toString();}

       return "";
    }

    public void editAnOwner(Owner o, String phone, String address) {

        int index = findOwner(o);
        if (index < 0) {
            System.out.println("Owner Does Not exists");
        } else {
            ownerList.get(index).setTelephone(phone);
            ownerList.get(index).setAddress(address);
            System.out.println("Owner Registeration Edited");
        }
    }

    public void addPet(Owner o, Pet p) {
        o.addOwnerPet(p);
        System.out.println("Pet is Added To Register");
    }

    public void readOwnerData(String file) {
//        System.out.println("Reading words, and numbers from Owner Data text file");

        try {
            Scanner sc = new Scanner(new File(file));

// Delimiter: set the delimiter to be a comma character "," 
//            or a carriage-return '\r', or a newline '\n'
            sc.useDelimiter("[,\r\n]+");

            int id = sc.nextInt();
            Owner.setId(id);

            while (sc.hasNext()) {

                String name = sc.next();
                int oid = sc.nextInt();
                String email = sc.next();
                String telephone = sc.next();
                String address = sc.next();

                ownerList.add(new Owner(name, oid, email, telephone, address));

            }
//            System.out.println("Closing The File");
            sc.close();

        } catch (IOException e) {
            System.out.println("Exception thrown. " + e);
        }
    }

    // must add the pet to the probriate owner after search their id
    public void readPetData(String file) {
//        System.out.println("Reading words, and numbers from Pet Data text file");

        try {
            Scanner sc = new Scanner(new File(file));

// Delimiter: set the delimiter to be a comma character "," 
//            or a carriage-return '\r', or a newline '\n'
            sc.useDelimiter("[, \r\n]+");

            int id = sc.nextInt();
            Pet.setPetID(id);

            while (sc.hasNext()) {

                String type = sc.next();
                String name = sc.next();
                String breed = sc.next();
                int age = sc.nextInt();
                String color = sc.next();
                Gender gender = Gender.valueOf(sc.next());
                String date = sc.next();
                String pid = sc.next();
                String oid = sc.next();
                Pet p = null;
                if (type.equalsIgnoreCase("M")) {
                    boolean neutered = sc.nextBoolean();
                    p = new Mammal(type, name, breed, age, color, gender, date, pid, oid, neutered);
                } else if (type.equalsIgnoreCase("B")) {
                    double wingspan = sc.nextDouble();
                    boolean fly = sc.nextBoolean();
                    p = new Bird(type, name, breed, age, color, gender, date, pid, oid, wingspan, fly);
                } else if (type.equalsIgnoreCase("F")) {
                    String water = sc.next();
                    p = new Fish(type, name, breed, age, color, gender, date, pid, oid, water);
                }

                Owner o = findOwnerByID(oid);

                o.addOwnerPet(p);

            }

            sc.close();

        } catch (IOException e) {
            System.out.println("Exception thrown. " + e);
        }
    }

    private int findOwner(Owner o) {

        int index = -1;
        for (int i = 0; i < ownerList.size(); i++) {
            if (ownerList.get(i).getOid().equals(o.getOid())) {
                index = i;
            }
        }
        return index;
    }

    public Owner findOwnerByID(String id) {

        int index = -1;
        for (int i = 0; i < ownerList.size(); i++) {
            if (ownerList.get(i).getOid().equals(id)) {
                return ownerList.get(i);
            }
        }
//        System.out.println("Owner With id = " + id + " Does Not Exist");
        return null;
    }

    public Pet findPetByID(String id) {

//        Pet p = null;
//          for (Owner o : ownerList) {
//           p = o.findPetByID(id);
//        }
        ArrayList<Pet> pets = listAllPets();
        for (int i = 0; i < pets.size(); i++) {
            if (pets.get(i).getPetId().equals(id)) {
                return pets.get(i);
            }
        }
        System.out.println("Pet With id = " + id + " Does Not Exist");
        return null;
    }

    public ArrayList<Pet> listAllPets() {

        ArrayList<Pet> pets = new ArrayList<Pet>();

        for (Owner o : ownerList) {
            ArrayList<Pet> opets = o.ArrayListClone();
            pets.addAll(opets);
        }
        return pets;
    }

    public void displayAllPetsById() {
        ArrayList<Pet> pets = listAllPets();
        Collections.sort(pets);
        for (Pet p : pets) {
            System.out.println(p.toString());
        }
    }

    public void displayAllPetsByGender() {
        ArrayList<Pet> pets = listAllPets();
        Collections.sort(pets, new PetGenderComparator());
        for (Pet p : pets) {
            System.out.println(p.toString());
        }
    }

    public void displayAllPetsByAge() {
        ArrayList<Pet> pets = listAllPets();
        Collections.sort(pets, new PetAgeComparator());
        for (Pet p : pets) {
            System.out.println(p.toString());
        }
    }

    // sorted by date
    public void displayAllOwnerPetsByDate(String oid) {
        Owner o = findOwnerByID(oid);
        ArrayList<Pet> pets = o.ArrayListClone();
        Collections.sort(pets, new OwnerPetComparator());
        for (Pet p : pets) {
            System.out.println(p.toString());
        }
    }

    public void displayAllPetsByType(String s) {
        ArrayList<Pet> pets = listAllPets();
        if (s.equalsIgnoreCase("M")) {
            for (Pet p : pets) {
                if (p.getType().equalsIgnoreCase("M")) {
                    System.out.println(p.toString());
                }
            }
        } else if (s.equalsIgnoreCase("B")) {
            for (Pet p : pets) {
                if (p.getType().equalsIgnoreCase("B")) {
                    System.out.println(p.toString());
                }
            }
        } else if (s.equalsIgnoreCase("F")) {
            for (Pet p : pets) {
                if (p.getType().equalsIgnoreCase("F")) {
                    System.out.println(p.toString());
                }
            }
        } else {
            System.out.println("Pet type Does Not Exist");
        }
    }

    public String getStatistics() {

        ArrayList<Pet> pets = listAllPets();
        double mCount = 0, bCount = 0, fCount = 0;
        int petCount = pets.size();
        for (Pet p : pets) {

            if (p.getType().equalsIgnoreCase("M")) {
                mCount++;

            } else if (p.getType().equalsIgnoreCase("B")) {
                bCount++;

            } else if (p.getType().equalsIgnoreCase("F")) {
                fCount++;

            }
        }
        DecimalFormat dec = new DecimalFormat("#0.00");
        double mperc = (mCount / petCount) * 100;
        double bperc = (bCount / petCount) * 100;
        double fperc = (fCount / petCount) * 100;
        return "There Are " + petCount + " Pet(s), There Are " + mCount + " Mammal(s), " + bCount + " Bird(s) And " + fCount + " Fish.\n"
                + "The Percentage Of Pets Types Are As follows:\n "
                + "Mammal " + dec.format(mperc) + "%\n"
                + "Bird " + dec.format(bperc) + "%\n"
                + "Fish " + dec.format(fperc) + "%";
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.ownerList);
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
        final Register other = (Register) obj;
        if (!Objects.equals(this.ownerList, other.ownerList)) {
            return false;
        }
        return true;
    }

    public int arraylistSize() {
        return ownerList.size();
    }

}
