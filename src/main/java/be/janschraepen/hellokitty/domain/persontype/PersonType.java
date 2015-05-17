package be.janschraepen.hellokitty.domain.persontype;


import be.janschraepen.hellokitty.domain.*;
import be.janschraepen.hellokitty.domain.Entity;
import be.janschraepen.hellokitty.domain.person.Person;
import be.janschraepen.hellokitty.domain.person.PersonContact;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * PersonType Entity class. This class represents a PersonType with
 *  attributes such as shortCode, name.
 */
@javax.persistence.Entity
@Table(name = "PERSON_TYPE")
public class PersonType extends Entity {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Column(name = "SHORT_CODE")
    private String shortCode;

    @NotNull
    @Column(name = "NAME")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "type")
    private List<Person> persons;

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

    /**
     *
     * @return the persons
     */
    public List<Person> getPersons() {
        return persons;
    }

    /**
     *
     * @param persons the persons to set
     */
    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    @Override
    public String toString() {
        return "PersonType{" +
                "shortCode='" + shortCode + '\'' +
                ", name='" + name + '\'' +
                ", persons=" + persons +
                '}';
    }

}
