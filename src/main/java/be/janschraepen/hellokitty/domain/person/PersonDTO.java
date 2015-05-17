package be.janschraepen.hellokitty.domain.person;

import java.io.Serializable;
import java.util.List;

/**
 * Person Data Transfer Object. Used for transferring data
 * throughout the layers.
 */
public class PersonDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String personTypeId;

    private String firstName;

    private String lastName;

    private String addressLine1;

    private String addressLine2;

    private String telephone;

    private String gsm;

    private String email;

    private List<PersonContactDTO> contacts;

    // convenience attributes
    private String personType;

    /**
     * Instantiates a new PersonDTO.
     */
    public PersonDTO() {

    }

    /**
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return the personTypeId
     */
    public String getPersonTypeId() {
        return personTypeId;
    }

    /**
     *
     * @param personTypeId the personTypeId to set
     */
    public void setPersonTypeId(String personTypeId) {
        this.personTypeId = personTypeId;
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
     * @return the contacts
     */
    public List<PersonContactDTO> getContacts() {
        return contacts;
    }

    /**
     *
     * @param contacts the contacts to set
     */
    public void setContacts(List<PersonContactDTO> contacts) {
        this.contacts = contacts;
    }

    /**
     *
     * @return the personType
     */
    public String getPersonType() {
        return personType;
    }

    /**
     *
     * @param personType the personType to set
     */
    public void setPersonType(String personType) {
        this.personType = personType;
    }

}
