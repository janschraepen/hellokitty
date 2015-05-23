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

    private String personTypeId;

    private String personType;

    private String personId;

    private String personFirstName;

    private String personLastName;

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
     *
     * @param catId the Cat id to set
     */
    public void setCatId(String catId) {
        this.catId = catId;
    }

    /**
     *
     * @return the PersonType id
     */
    public String getPersonTypeId() {
        return personTypeId;
    }

    /**
     *
     * @param personTypeId the PersonType id to set
     */
    public void setPersonTypeId(String personTypeId) {
        this.personTypeId = personTypeId;
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

    /**
     *
     * @return the Person id
     */
    public String getPersonId() {
        return personId;
    }

    /**
     *
     * @param personId the Person id to set
     */
    public void setPersonId(String personId) {
        this.personId = personId;
    }

    /**
     *
     * @return the Person firstName
     */
    public String getPersonFirstName() {
        return personFirstName;
    }

    /**
     *
     * @param personFirstName the Person firstName to set
     */
    public void setPersonFirstName(String personFirstName) {
        this.personFirstName = personFirstName;
    }

    /**
     *
     * @return the Person lastName
     */
    public String getPersonLastName() {
        return personLastName;
    }

    /**
     *
     * @param personLastName the Person lastName to set
     */
    public void setPersonLastName(String personLastName) {
        this.personLastName = personLastName;
    }

}
