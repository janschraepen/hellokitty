package be.janschraepen.hellokitty.domain.cat;

import java.util.ArrayList;
import java.util.List;

/**
 * ObjectFactory class. This class must be used to create new instances
 * of CatDTO.
 */
public final class ObjectFactory {

    private static ObjectFactory instance;

    /**
     * Get the ObjectFactory instance.
     *
     * @return ObjectFactory instance
     */
    public static ObjectFactory getInstance() {
        if (instance == null) {
            instance = new ObjectFactory();
        }
        return instance;
    }

    /**
     * Create CatDTO object.
     *
     * @param c the Cat
     * @return CatDTO object
     */
    public CatDTO createCatDTO(Cat c) {
        CatDTO dto = new CatDTO();
        dto.setId(c.getId());
        dto.setName(c.getName());
        dto.setBreed(c.getBreed());
        dto.setAge(c.getAge());
        dto.setGender(c.getGender());
        dto.setNeutered(c.isNeutered());
        dto.setChipped(c.isChipped());
        dto.setAttention(c.getAttention());
        dto.setBehavioral(c.getBehavioral());
        dto.setNutrition(c.getNutrition());
        dto.setPersons(createListCatPersonDTOs(c.getCatPersons()));
        return dto;
    }

    /**
     * Create CatDTO object.
     *
     * @param id         the uuid
     * @param name       the name
     * @param breed      the breed
     * @param age        the age (in years)
     * @param gender     the gender
     * @param neutered   neutered or not
     * @param chipped    chipped or not
     * @param attention  the attention
     * @param behavioral the behavioral
     * @param nutrition  the nutrition
     * @return CatDTO object
     */
    public CatDTO createCatDTO(String id, String name, String breed, String age, Gender gender, boolean neutered, boolean chipped, String attention, String behavioral, String nutrition) {
        CatDTO dto = new CatDTO();
        dto.setId(id);
        dto.setName(name);
        dto.setBreed(breed);
        dto.setAge(age);
        dto.setGender(gender);
        dto.setNeutered(neutered);
        dto.setChipped(chipped);
        dto.setAttention(attention);
        dto.setBehavioral(behavioral);
        dto.setNutrition(nutrition);
        return dto;
    }

    /**
     * Create List of CatDTO objects.
     *
     * @param l the list of Cats
     * @return List<CatDTO> a list of CatDTOs
     */
    public List<CatDTO> createListCatDTOs(List<Cat> l) {
        List<CatDTO> list = new ArrayList<>();
        for (Cat c : l) {
            list.add(createCatDTO(c));
        }
        return list;
    }

    /**
     * Create CatPersonDTO object.
     *
     * @param c the CatPerson
     * @return CatPersonsTO object
     */
    public CatPersonDTO createCatPersonDTO(CatPerson c) {
        CatPersonDTO dto = new CatPersonDTO();
        dto.setId(c.getId());
        dto.setCatId(c.getCat().getId());
        dto.setPersonTypeId(c.getType().getId());
        dto.setPersonType(c.getType().getName());
        dto.setPersonId(c.getPerson().getId());
        dto.setPersonFirstName(c.getPerson().getFirstName());
        dto.setPersonLastName(c.getPerson().getLastName());
        return dto;
    }

    /**
     * Create CatPersonDTO object.
     *
     * @param catId the Cat id
     * @param personTypeId the PersonType id
     * @param personId the Person id
     * @return CatPersonsTO object
     */
    public CatPersonDTO createCatPersonDTO(String catId, String personTypeId, String personId) {
        CatPersonDTO dto = new CatPersonDTO();
        dto.setCatId(catId);
        dto.setPersonTypeId(personTypeId);
        dto.setPersonId(personId);
        return dto;
    }

    /**
     * Create List of CatPersonDTO objects.
     *
     * @param l the list of CatPersons
     * @return List<CatPersonDTO> a list of CatPersonDTOs
     */
    public List<CatPersonDTO> createListCatPersonDTOs(List<CatPerson> l) {
        List<CatPersonDTO> list = new ArrayList<>();
        if (l != null) {
            for (CatPerson c : l) {
                list.add(createCatPersonDTO(c));
            }
        }
        return list;
    }

}
