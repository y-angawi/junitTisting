/*
 * Name: Yahya Angawi
 * Student ID: D00233709
 ** IMPORTANT NOTE**
 * I did not test the helper methods (methods that are used by other methods) as Kevin instucted us that we do not need to test them
 */
package oop19_ca2_yahya_angawi;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/* TO DO:
   
 */
public class Owner implements Serializable {

    // to fix ocal class incompatible: stream classdesc serialVersionUID different error
    private static final long serialVersionUID = -7081753203408999558L;
    private String name, email, telephone, address;
    private ArrayList<Pet> pets;
    private static int id;
    private String oid;

    public Owner(String name, String email, String telephone, String address) {
        pets = new ArrayList<Pet>();
        this.name = name;
        this.setEmail(email);
        this.setTelephone(telephone);
        this.address = address;
        id++;
        this.setOid(id);
    }
    // id passed as string not int

    public Owner(String name, int id, String email, String telephone, String address) {  //file
        pets = new ArrayList<Pet>();
        this.name = name;
        this.setOid(id);
        this.setEmail(email);
        this.setTelephone(telephone);
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(email);

        if (!matcher.matches()) {
            throw new IllegalArgumentException("The Email Must Be Of The format");
        }

        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {

        String regex = "[0-9]{3}-[0-9]{7}";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(telephone);

        if (!matcher.matches()) {
            throw new IllegalArgumentException("The Phone Number Must Be Of The format 087-1234567");
        }

        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOid() {
        return oid;
    }

    public static void setId(int id) {
        Owner.id = id;
    }

    public final void setOid(int id) {
        oid = "OID" + id;
    }

    public void addOwnerPet(Pet p) {

        int index = findPet(p);
        if (index < 0) {
            pets.add(p);
            System.out.println("Pet is Added To Owner With ID Of " + this.getOid());
        } else {
            System.out.println("Pet is already exists");
        }
    }

    private int findPet(Pet p) {

        int index = -1;
        for (int i = 0; i < pets.size(); i++) {
            if (p.getPetId().equals(pets.get(i).getPetId())) {
                index = i;
            }
        }
        return index;
    }
// DID NOT USE THIS METHOD did it with an easier way, from Rgister calss (no need to access Owner class)

    public void printAPet(Pet p) {

        int index = findPet(p);
        if (index < 0) {

            System.out.println("Pet Does Not exists");
        } else {
            System.out.println(pets.get(index).toString());
        }
    }

// DID NOT USE THIS METHOD did it with an easier way, from Rgister calss (no need to access Owner class)
    public void editAPet(Pet p, String name, String date) {

        int index = findPet(p);
        if (index < 0) {

            System.out.println("Pet Does Not exists");
        } else {
            pets.get(index).setName(name);
            pets.get(index).setDate(date);

            System.out.println("Pet is Renamed And The Date Was Updated");
        }
    }

    // test
    public void removePet(Pet p) {
        int index = findPet(p);
        if (index < 0) {

            System.out.println("Pet Does Not exists");
        } else {
            pets.remove(index);
            System.out.println("Pet is Removed");
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.name);
        hash = 37 * hash + Objects.hashCode(this.email);
        hash = 37 * hash + Objects.hashCode(this.telephone);
        hash = 37 * hash + Objects.hashCode(this.address);
        hash = 37 * hash + Objects.hashCode(this.pets);
        hash = 37 * hash + Objects.hashCode(this.oid);
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
        final Owner other = (Owner) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.telephone, other.telephone)) {
            return false;
        }
        if (!Objects.equals(this.address, other.address)) {
            return false;
        }
        if (!Objects.equals(this.oid, other.oid)) {
            return false;
        }
        if (!Objects.equals(this.pets, other.pets)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Owner {" + "name= " + name + ", oid= " + oid + ", email= " + email + ", telephone= " + telephone + ", address= " + address + '}';
    }

    public ArrayList<Pet> ArrayListClone() {
        return pets;
    }

    public void displayAllOwnerPetsByDate() {

        Collections.sort(pets, new OwnerPetComparator());
        for (Pet p : pets) {
            System.out.println(p.toString());
        }
    }
    // i had to add this method to test the methods in this class, test by checking the arraylist's size
    public int arraylistSize(){
    
        return pets.size();
    }
}
