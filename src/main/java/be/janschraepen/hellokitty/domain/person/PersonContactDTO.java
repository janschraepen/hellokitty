package be.janschraepen.hellokitty.domain.person;

import java.io.Serializable;

/**
 * PersonContact Data Transfer Object. Used for transferring data
 * throughout the layers.
 */
public class PersonContactDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String personId;

    private ContactType type;

    private String value;

    /**
     * Instantiates a new PersonContactDTO.
     */
    public PersonContactDTO() {

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
     * @return the person uuid
     */
    public String getPersonId() {
        return personId;
    }

    /**
     *
     * @param personId the person uuid to set
     */
    public void setPersonId(String personId) {
        this.personId = personId;
    }

    /**
     *
     * @return the ContactTypes
     */
    public ContactType getType() {
        return type;
    }

    /**
     *
     * @param type the ContactTypes to set
     */
    public void setType(ContactType type) {
        this.type = type;
    }

    /**
     *
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     *
     * @param value the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }

}
