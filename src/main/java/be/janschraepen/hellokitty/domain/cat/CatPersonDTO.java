package be.janschraepen.hellokitty.domain.cat;

import java.io.Serializable;

/**
 * CatPerson Data Transfer Object. Used for transferring data
 * throughout the layers.
 */
public class CatPersonDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String catId;

    private String catName;

    private String personTypeId;

    private String personType;

    private String personId;

    private String personFirstName;

    private String personLastName;

    private String personAddressLine1;

    private String personAddressLine2;

    private String personContacts;

    /**
     * Instantiates a new CatPersonDTO.
     */
    public CatPersonDTO() {

    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the Cat id
     */
    public String getCatId() {
        return catId;
    }

    /**
     * @param catId the Cat id to set
     */
    public void setCatId(String catId) {
        this.catId = catId;
    }

    /**
     * @return the Cat name
     */
    public String getCatName() {
        return catName;
    }

    /**
     * @param catName the Cat name to set
     */
    public void setCatName(String catName) {
        this.catName = catName;
    }

    /**
     * @return the PersonType id
     */
    public String getPersonTypeId() {
        return personTypeId;
    }

    /**
     * @param personTypeId the PersonType id to set
     */
    public void setPersonTypeId(String personTypeId) {
        this.personTypeId = personTypeId;
    }

    /**
     * @return the personType
     */
    public String getPersonType() {
        return personType;
    }

    /**
     * @param personType the personType to set
     */
    public void setPersonType(String personType) {
        this.personType = personType;
    }

    /**
     * @return the Person id
     */
    public String getPersonId() {
        return personId;
    }

    /**
     * @param personId the Person id to set
     */
    public void setPersonId(String personId) {
        this.personId = personId;
    }

    /**
     * @return the Person firstName
     */
    public String getPersonFirstName() {
        return personFirstName;
    }

    /**
     * @param personFirstName the Person firstName to set
     */
    public void setPersonFirstName(String personFirstName) {
        this.personFirstName = personFirstName;
    }

    /**
     * @return the Person lastName
     */
    public String getPersonLastName() {
        return personLastName;
    }

    /**
     * @param personLastName the Person lastName to set
     */
    public void setPersonLastName(String personLastName) {
        this.personLastName = personLastName;
    }

    /**
     * @return the Person addressLine1
     */
    public String getPersonAddressLine1() {
        return personAddressLine1;
    }

    /**
     * @param personAddressLine1 the Person addressLine1 to set
     */
    public void setPersonAddressLine1(String personAddressLine1) {
        this.personAddressLine1 = personAddressLine1;
    }

    /**
     * @return the Person addressLine2
     */
    public String getPersonAddressLine2() {
        return personAddressLine2;
    }

    /**
     * @param personAddressLine2 the Person addressLine2 to set
     */
    public void setPersonAddressLine2(String personAddressLine2) {
        this.personAddressLine2 = personAddressLine2;
    }

    /**
     * @return the Person contact details
     */
    public String getPersonContacts() {
        return personContacts;
    }

    /**
     * @param personContacts the Person contact details to set
     */
    public void setPersonContacts(String personContacts) {
        this.personContacts = personContacts;
    }

}
