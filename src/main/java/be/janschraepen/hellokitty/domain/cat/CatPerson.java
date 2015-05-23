package be.janschraepen.hellokitty.domain.cat;


import be.janschraepen.hellokitty.domain.Entity;
import be.janschraepen.hellokitty.domain.person.ContactType;
import be.janschraepen.hellokitty.domain.person.Person;
import be.janschraepen.hellokitty.domain.persontype.PersonType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * CatPerson Entity class. This class represents a Person related to a
 * Cat with attributes such as type (of relation), person, etc..
 */
@javax.persistence.Entity
@Table(name = "CAT_PERSON")
public class CatPerson extends Entity {

    private static final long serialVersionUID = 1L;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CAT_ID", nullable = false)
    private Cat cat;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PERSON_TYPE_ID", nullable = false)
    private PersonType type;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PERSON_ID", nullable = false)
    private Person person;

    /**
     *
     * @return the cat
     */
    public Cat getCat() {
        return cat;
    }

    /**
     *
     * @param cat the cat to set
     */
    public void setCat(Cat cat) {
        this.cat = cat;
    }

    /**
     *
     * @return the type
     */
    public PersonType getType() {
        return type;
    }

    /**
     *
     * @param type the type to set
     */
    public void setType(PersonType type) {
        this.type = type;
    }

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

}
