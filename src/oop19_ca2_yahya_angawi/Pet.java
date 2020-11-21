/*
 * Name: Yahya Angawi
 * Student ID: D00233709
 ** IMPORTANT NOTE**
 * I did not test the helper methods (methods that are used by other methods) as Kevin instucted us that we do not need to test them
 */
package oop19_ca2_yahya_angawi;

import java.io.Serializable;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Pet implements Comparable<Pet>, Serializable {

    private String name, type, breed, colour, date, ownerID;
    private int age;
    Gender gender;
    private static int pid;
    private String petId;

    // no need for owner id but have to look all onwers to find a pet
    public Pet(String type, String name, String breed, int age, String colour, Gender gender, String date, String ownerID) {
        this.name = name;
        this.type = type;
        this.breed = breed;
        this.gender = gender;
        this.colour = colour;
        this.setDate(date);
        this.ownerID = ownerID;
        this.setAge(age);
        petId = "PID" + pid;
        pid++;
    }

    public Pet(String type, String name, String breed, int age, String colour, Gender gender, String date, String PetID, String ownerID) {
        this.name = name;
        this.petId = PetID;
        this.type = type;
        this.breed = breed;
        this.colour = colour;
        this.gender = gender;
        this.setDate(date);
        this.ownerID = ownerID;
        this.setAge(age);
    }

    public Pet() {
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    // try and catch(iligalEx.. e){system.err.println("illigal.." + e.getMessage())}
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {

        String regex = "^(((((0[1-9])|(1\\d)|(2[0-8]))\\/((0[1-9])|(1[0-2])))|((31\\/((0[13578])|(1[02])))|((29|30)\\/((0[1,3-9])|(1[0-2])))))\\/((20[0-9][0-9])|(19[0-9][0-9])))|((29\\/02\\/(19|20)(([02468][048])|([13579][26]))))$";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(date);

        if (!matcher.matches()) {
            throw new IllegalArgumentException("The Date Must Be Of The format dd/mm/yyyy (1900-2099)");
        }

        this.date = date;
    }

    public String getPetId() {
        return petId;
    }

    public static void setPetID(int id) {
        pid = id;
    }

    public String getOwnerID() {
        return ownerID;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age < 0 || age > 100) {
            throw new IllegalArgumentException("Age must be Positive and less than 100");
        }
        this.age = age;
    }

    public void editPet(String name, String date) {

        this.name = name;
        this.setDate(date);
        System.out.println("The Pet is Renamed And The Date Was Updated");
    }

    @Override
    public int compareTo(Pet other) {

        if (Integer.parseInt(this.getPetId().substring(3)) < Integer.parseInt(other.getPetId().substring(3))) {
            return -1;
        } else if (Integer.parseInt(this.getPetId().substring(3)) == Integer.parseInt(other.getPetId().substring(3))) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 19 * hash + Objects.hashCode(this.name);
        hash = 19 * hash + Objects.hashCode(this.type);
        hash = 19 * hash + Objects.hashCode(this.breed);
        hash = 19 * hash + Objects.hashCode(this.colour);
        hash = 19 * hash + Objects.hashCode(this.date);
        hash = 19 * hash + Objects.hashCode(this.ownerID);
        hash = 19 * hash + this.age;
        hash = 19 * hash + Objects.hashCode(this.gender);
        hash = 19 * hash + Objects.hashCode(this.petId);
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
        final Pet other = (Pet) obj;
        if (this.age != other.age) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.breed, other.breed)) {
            return false;
        }
        if (!Objects.equals(this.colour, other.colour)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        if (!Objects.equals(this.ownerID, other.ownerID)) {
            return false;
        }
        if (!Objects.equals(this.petId, other.petId)) {
            return false;
        }
        if (this.gender != other.gender) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {name=" + name + ", type=" + type + ", breed=" + breed + ", colour=" + colour + ", date=" + date + ", ownerID=" + ownerID + ", age=" + age + ", gender=" + gender + ", petId=" + petId + '}';
    }

}
