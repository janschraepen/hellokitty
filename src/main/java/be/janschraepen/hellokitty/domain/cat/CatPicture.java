package be.janschraepen.hellokitty.domain.cat;


import be.janschraepen.hellokitty.domain.Entity;
import be.janschraepen.hellokitty.domain.person.Person;
import be.janschraepen.hellokitty.domain.persontype.PersonType;

import javax.persistence.*;

/**
 * CatPicture Entity class. This class represents a Picture related to a
 * Cat with attributes such as picture, etc..
 */
@javax.persistence.Entity
@Table(name = "CAT_PICTURE")
public class CatPicture extends Entity {

    private static final long serialVersionUID = 1L;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CAT_ID", nullable = false)
    private Cat cat;

    @Lob
    @Column(name = "data", nullable =  false, columnDefinition = "BLOB")
    private byte[] picture;

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
     * @return the picture
     */
    public byte[] getPicture() {
        return picture;
    }

    /**
     *
     * @param picture the picture to set
     */
    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

}
