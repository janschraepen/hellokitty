package be.janschraepen.hellokitty.domain.persontype;

import java.util.ArrayList;
import java.util.List;

/**
 * ObjectFactory class. This class must be used to create new instances
 *  of PersonTypeDTO.
 */
public final class ObjectFactory {

    private static ObjectFactory instance;

    /**
     * Get the ObjectFactory instance.
     * @return ObjectFactory instance
     */
    public static ObjectFactory getInstance() {
        if (instance == null) {
            instance = new ObjectFactory();
        }
        return instance;
    }

    /**
     * Create PersonTypeDTO object.
     * @param p the PersonType
     * @return PersonTypeDTO object
     */
    public PersonTypeDTO createDTO(PersonType p) {
        return new PersonTypeDTO(
                p.getId(),
                p.getShortCode(),
                p.getName()
        );
    }

    /**
     * Create PersonTypeDTO object.
     * @param id the uuid
     * @param shortCode the shortCode
     * @param name the name
     * @return PersonTypeDTO object
     */
    public PersonTypeDTO createDTO(String id, String shortCode, String name) {
        return new PersonTypeDTO(
                id,
                shortCode,
                name
        );
    }

    /**
     * Create List of PersonTypeDTO objects.
     * @param l the list of PersonTypes
     * @return List<PersonTypeDTO> a list of PersonTypeDTOs
     */
    public List<PersonTypeDTO> createListDTOs(List<PersonType> l) {
        List<PersonTypeDTO> list = new ArrayList<>();
        for (PersonType p : l) {
            list.add(createDTO(p));
        }
        return list;
    }

}
