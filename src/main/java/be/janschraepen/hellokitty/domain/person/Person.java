package be.janschraepen.hellokitty.domain.person;


import be.janschraepen.hellokitty.domain.Entity;
import be.janschraepen.hellokitty.domain.cat.CatPerson;
import be.janschraepen.hellokitty.domain.persontype.PersonType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Person Entity class. This class represents a Person with attributes
 * such as firstName, lastName, tel, gsm, e-mail, etc..
 */
@javax.persistence.Entity
@Table(name = "PERSON")
public class Person extends Entity {

    private static final long serialVersionUID = 1L;

    @Size(min = 1, max = 100)
    @NotNull
    @Column(name = "FIRST_NAME")
    private String firstName;

    @Size(min = 1, max = 100)
    @NotNull
    @Column(name = "LAST_NAME")
    private String lastName;

    @Size(min = 1, max = 100)
    @NotNull
    @Column(name = "ADDRESS_LINE_1")
    private String addressLine1;

    @Size(min = 1, max = 100)
    @NotNull
    @Column(name = "ADDRESS_LINE_2")
    private String addressLine2;

    @Size(max = 512)
    @Column(name = "EXTRA_INFO")
    private String extraInfo;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "person")
    private List<PersonContact> contacts;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "person")
    private List<CatPerson> catPersons;

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
     * @return the extra info
     */
    public String getExtraInfo() {
        return extraInfo;
    }

    /**
     * @param extraInfo the extra info to set
     */
    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
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

    /**
     *
     * @return the cat_persons
     */
    public List<CatPerson> getCatPersons() {
        return catPersons;
    }

    /**
     *
     * @param catPersons the cat_persons to set
     */
    public void setCatPersons(List<CatPerson> catPersons) {
        this.catPersons = catPersons;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", addressLine1='" + addressLine1 + '\'' +
                ", addressLine2='" + addressLine2 + '\'' +
                ", extraInfo='" + extraInfo + '\'' +
                '}';
    }
}
