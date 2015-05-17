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
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PERSON_TYPE_ID")
    private PersonType type;

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

    @Column(name = "TELEPHONE")
    private String telephone;

    @Column(name = "GSM")
    private String gsm;

    @Column(name = "Email")
    private String email;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "person")
    private List<PersonContact> contacts;

    /**
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return the personType
     */
    public PersonType getType() {
        return type;
    }

    /**
     *
     * @param type the personType to set
     */
    public void setType(PersonType type) {
        this.type = type;
    }

    /**
     *
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     *
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     *
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     *
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     *
     * @return the addressLine1
     */
    public String getAddressLine1() {
        return addressLine1;
    }

    /**
     *
     * @param addressLine1 the addressLine1 to set
     */
    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    /**
     *
     * @return the addressLine2
     */
    public String getAddressLine2() {
        return addressLine2;
    }

    /**
     *
     * @param addressLine2 the addressLine2 to set
     */
    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    /**
     *
     * @return the telephone
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     *
     * @param telephone the telephone to set
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     *
     * @return the gsm
     */
    public String getGsm() {
        return gsm;
    }

    /**
     *
     * @param gsm the gsm to set
     */
    public void setGsm(String gsm) {
        this.gsm = gsm;
    }

    /**
     *
     * @return the contacts
     */
    public List<PersonContact> getContacts() {
        return contacts;
    }

    /**
     *
     * @param contacts the contacts to set
     */
    public void setContacts(List<PersonContact> contacts) {
        this.contacts = contacts;
    }

    @Override
    public String toString() {
        return "Person{" +
                "type=" + type +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", addressLine1='" + addressLine1 + '\'' +
                ", addressLine2='" + addressLine2 + '\'' +
                ", telephone='" + telephone + '\'' +
                ", gsm='" + gsm + '\'' +
                ", email='" + email + '\'' +
                ", contacts=" + contacts +
                '}';
    }

}
