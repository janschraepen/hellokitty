package be.janschraepen.hellokitty.domain.person;


import be.janschraepen.hellokitty.domain.Entity;
import be.janschraepen.hellokitty.domain.persontype.PersonType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Person Entity class. This class represents a Person with attributes
 * such as firstName, lastName, tel, gsm, e-mail, etc..
 */
@javax.persistence.Entity
@Table(name = "PERSON")
public class Person extends Entity {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Column(name = "FIRST_NAME")
    private String firstName;

    @NotNull
    @Column(name = "LAST_NAME")
    private String lastName;

    @NotNull
    @Column(name = "ADDRESS_LINE_1")
    private String addressLine1;

    @NotNull
    @Column(name = "ADDRESS_LINE_2")
    private String addressLine2;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "person")
    private List<PersonContact> contacts;

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the addressLine1
     */
    public String getAddressLine1() {
        return addressLine1;
    }

    /**
     * @param addressLine1 the addressLine1 to set
     */
    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    /**
     * @return the addressLine2
     */
    public String getAddressLine2() {
        return addressLine2;
    }

    /**
     * @param addressLine2 the addressLine2 to set
     */
    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    /**
     * @return the contacts
     */
    public List<PersonContact> getContacts() {
        return contacts;
    }

    /**
     * @param contacts the contacts to set
     */
    public void setContacts(List<PersonContact> contacts) {
        this.contacts = contacts;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", addressLine1='" + addressLine1 + '\'' +
                ", addressLine2='" + addressLine2 + '\'' +
                ", contacts=" + contacts +
                '}';
    }

}
