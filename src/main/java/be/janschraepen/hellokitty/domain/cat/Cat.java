package be.janschraepen.hellokitty.domain.cat;


import be.janschraepen.hellokitty.domain.Entity;
import be.janschraepen.hellokitty.domain.person.PersonContact;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Cat Entity class. This class represents a Cat with attributes
 * such as name, breed, age, gender, etc..
 */
@javax.persistence.Entity
@Table(name = "CAT")
public class Cat extends Entity {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Column(name = "NAME")
    private String name;

    @Column(name = "BREED")
    private String breed;

    @Column(name = "YEAR_OF_BIRTH")
    private String age;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "GENDER")
    private Gender gender;

    @NotNull
    @Column(name = "NEUTERED")
    private boolean neutered;

    @NotNull
    @Column(name = "CHIPPED")
    private boolean chipped;

    @Column(name = "ATTENTION")
    private String attention;

    @Column(name = "BEHAVIORAL")
    private String behavioral;

    @Column(name = "NUTRITION")
    private String nutrition;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "cat")
    private List<CatPerson> catPersons;

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
     * @return the breed
     */
    public String getBreed() {
        return breed;
    }

    /**
     *
     * @param breed the breed to set
     */
    public void setBreed(String breed) {
        this.breed = breed;
    }

    /**
     *
     * @return the age (in years)
     */
    public String getAge() {
        return age;
    }

    /**
     *
     * @param age the age to set (in years)
     */
    public void setAge(String age) {
        this.age = age;
    }

    /**
     *
     * @return the gender
     */
    public Gender getGender() {
        return gender;
    }

    /**
     *
     * @param gender the gender to set
     */
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    /**
     *
     * @return true if is neutered, false if not
     */
    public boolean isNeutered() {
        return neutered;
    }

    /**
     *
     * @param neutered the neutered value to set
     */
    public void setNeutered(boolean neutered) {
        this.neutered = neutered;
    }

    /**
     *
     * @return true if chipped, false if not
     */
    public boolean isChipped() {
        return chipped;
    }

    /**
     *
     * @param chipped the chipped value to set
     */
    public void setChipped(boolean chipped) {
        this.chipped = chipped;
    }

    /**
     *
     * @return the attention
     */
    public String getAttention() {
        return attention;
    }

    /**
     *
     * @param attention the attention to set
     */
    public void setAttention(String attention) {
        this.attention = attention;
    }

    /**
     *
     * @return the behavioral
     */
    public String getBehavioral() {
        return behavioral;
    }

    /**
     *
     * @param behavioral the behavioral to set
     */
    public void setBehavioral(String behavioral) {
        this.behavioral = behavioral;
    }

    /**
     *
     * @return the nutrition
     */
    public String getNutrition() {
        return nutrition;
    }

    /**
     *
     * @param nutrition the nutrition
     */
    public void setNutrition(String nutrition) {
        this.nutrition = nutrition;
    }

    /**
     *
     * @return the cat_persons
     */
    public List<CatPerson> getCatPersons() {
        return catPersons;
    }

    /**
     *
     * @param catPersons the cat_persons to set
     */
    public void setCatPersons(List<CatPerson> catPersons) {
        this.catPersons = catPersons;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", breed='" + breed + '\'' +
                ", age='" + age + '\'' +
                ", gender=" + gender +
                ", neutered=" + neutered +
                ", chipped=" + chipped +
                ", attention='" + attention + '\'' +
                ", behavioral='" + behavioral + '\'' +
                ", nutrition='" + nutrition + '\'' +
                ", catPersons=" + catPersons +
                '}';
    }

}
