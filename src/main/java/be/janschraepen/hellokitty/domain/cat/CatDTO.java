package be.janschraepen.hellokitty.domain.cat;

import java.io.Serializable;
import java.util.List;

/**
 * Cat Data Transfer Object. Used for transferring data
 * throughout the layers.
 */
public class CatDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String name;

    private String breed;

    private String age;

    private Gender gender;

    private boolean neutered;

    private boolean chipped;

    private String attention;

    private String behavioral;

    private String nutrition;

    private List<CatPersonDTO> persons;

    /**
     * Instantiates a new CatDTO.
     */
    public CatDTO() {

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
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the breed
     */
    public String getBreed() {
        return breed;
    }

    /**
     * @param breed the breed to set
     */
    public void setBreed(String breed) {
        this.breed = breed;
    }

    /**
     * @return the age (in years)
     */
    public String getAge() {
        return age;
    }

    /**
     * @param age the age to set (in years)
     */
    public void setAge(String age) {
        this.age = age;
    }

    /**
     * @return the gender
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    /**
     * @return true if is neutered, false if not
     */
    public boolean isNeutered() {
        return neutered;
    }

    /**
     * @param neutered the neutered value to set
     */
    public void setNeutered(boolean neutered) {
        this.neutered = neutered;
    }

    /**
     * @return true if chipped, false if not
     */
    public boolean isChipped() {
        return chipped;
    }

    /**
     * @param chipped the chipped value to set
     */
    public void setChipped(boolean chipped) {
        this.chipped = chipped;
    }

    /**
     * @return the attention
     */
    public String getAttention() {
        return attention;
    }

    /**
     * @param attention the attention to set
     */
    public void setAttention(String attention) {
        this.attention = attention;
    }

    /**
     * @return the behavioral
     */
    public String getBehavioral() {
        return behavioral;
    }

    /**
     * @param behavioral the behavioral to set
     */
    public void setBehavioral(String behavioral) {
        this.behavioral = behavioral;
    }

    /**
     * @return the nutrition
     */
    public String getNutrition() {
        return nutrition;
    }

    /**
     * @param nutrition the nutrition to set
     */
    public void setNutrition(String nutrition) {
        this.nutrition = nutrition;
    }

    /**
     * @return the Persons
     */
    public List<CatPersonDTO> getPersons() {
        return persons;
    }

    /**
     * @param persons the Persons to set
     */
    public void setPersons(List<CatPersonDTO> persons) {
        this.persons = persons;
    }

}
