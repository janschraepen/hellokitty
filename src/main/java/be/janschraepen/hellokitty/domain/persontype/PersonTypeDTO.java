package be.janschraepen.hellokitty.domain.persontype;

import java.io.Serializable;

/**
 * PersonType Data Transfer Object. Used for transferring data
 * throughout the layers.
 */
public class PersonTypeDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String shortCode;

    private String name;

    /**
     * Instantiates a new PersonTypeDTO.
     */
    public PersonTypeDTO() {

    }

    /**
     * Instantiates a new PersonTypeDTO.
     * @param id the uuid
     * @param shortCode the shortCode
     * @param name the name
     */
    public PersonTypeDTO(String id, String shortCode, String name) {
        this.id = id;
        this.shortCode = shortCode;
        this.name = name;
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
     * @return the shortCode
     */
    public String getShortCode() {
        return shortCode;
    }

    /**
     *
     * @param shortCode the shortCode to set
     */
    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    /**
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

}
