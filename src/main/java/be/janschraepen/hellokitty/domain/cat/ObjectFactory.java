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

}
