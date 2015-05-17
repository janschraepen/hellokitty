package be.janschraepen.hellokitty.domain.person;


import be.janschraepen.hellokitty.domain.Entity;
import be.janschraepen.hellokitty.domain.persontype.PersonType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * PersonContact Entity class. This class represents a Contacts of a Person
 * with attributes such as type, value, etc..
 */
@javax.persistence.Entity
@Table(name = "PERSON_CONTACT")
public class PersonContact extends Entity {

    private static final long serialVersionUID = 1L;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PERSON_ID", nullable = false)
    private Person person;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE")
    private ContactType type;

    @NotNull
    @Column(name = "VALUE")
    private String value;

    /**
     *
     * @return the person
     */
    public Person getPerson() {
        return person;
    }

    /**
     *
     * @param person the person to set
     */
    public void setPerson(Person person) {
        this.person = person;
    }

    /**
     *
     * @return the ContactType
     */
    public ContactType getType() {
        return type;
    }

    /**
     *
     * @param type the ContactType to set
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

    @Override
    public String toString() {
        return "PersonContact{" +
                "person=" + person +
                ", type=" + type +
                ", value='" + value + '\'' +
                '}';
    }

}
