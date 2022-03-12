package com.rajch.hashing;

import java.util.HashSet;

public class EqulasHashcode {
    public static void main(String[] args) {
        HashSet<Name> names = new HashSet<>();
        Name raj = new Name("Raj", "Chandvaniya");
        Name raj2 = new Name("Raj", "Chandvaniya");
        Name parth = new Name("Parth", "Chandvaniya");

        names.add(raj);
        names.add(raj2);
        names.add(parth);

        System.out.println(names);

        /*
        * 1) Without Equlas and Hashcode
        * [Name{firstName='Parth', lastName='Chandvaniya'}, Name{firstName='Raj', lastName='Chandvaniya'}, Name{firstName='Raj', lastName='Chandvaniya'}]
        *
        * 2) Only Equals
        * [Name{firstName='Parth', lastName='Chandvaniya'}, Name{firstName='Raj', lastName='Chandvaniya'}, Name{firstName='Raj', lastName='Chandvaniya'}]
        *
        * 3) Only hashcode
        * [Name{firstName='Raj', lastName='Chandvaniya'}, Name{firstName='Raj', lastName='Chandvaniya'}, Name{firstName='Parth', lastName='Chandvaniya'}]
        *
        * 4) Both Equals and Hashcode
        * [Name{firstName='Raj', lastName='Chandvaniya'}, Name{firstName='Parth', lastName='Chandvaniya'}]
        *
        * */
    }
}

class Name {
    private String firstName;
    private String lastName;

    public Name(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Name{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Name name = (Name) o;

        if (firstName != null ? !firstName.equals(name.firstName) : name.firstName != null) return false;
        return lastName != null ? lastName.equals(name.lastName) : name.lastName == null;
    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        return result;
    }
}
