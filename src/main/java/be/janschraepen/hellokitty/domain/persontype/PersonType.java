package be.janschraepen.hellokitty.domain.persontype;


import be.janschraepen.hellokitty.domain.*;
import be.janschraepen.hellokitty.domain.Entity;

import javax.persistence.*;

/**
 * PersonType Entity class. This class represents a PersonType with
 *  attributes such as shortCode, name.
 */
@javax.persistence.Entity
@Table(name = "PERSON_TYPE")
public class PersonType extends Entity {

    private static final long serialVersionUID = 1L;

    @Column(name = "SHORT_CODE")
    private String shortCode;

    @Column(name = "NAME")
    private String name;

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

    @Override
    public String toString() {
        return "Product{" +
                "id='" + getId() + '\'' +
                ", shortCode='" + getShortCode() + '\'' +
                ", name='" + getName() + '\'' +
                '}';
    }

}
