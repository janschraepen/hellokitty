package be.janschraepen.hellokitty.services;

import be.janschraepen.hellokitty.domain.persontype.PersonTypeDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * PersonTypeService interface. This class defines all possibles actions
 *  regarding the PersonTypes.
 */
public interface PersonTypeService {

    /**
     * Find a PersonType with given uuid.
     * @param uuid the uuid to find
     * @return PersonTypeDTO to found PersonType, or null if not found
     */
    @Transactional(readOnly = true)
    PersonTypeDTO findPersonType(String uuid);

    /**
     * Find PersonTypes by given search criteria.
     * @param searchFor the search criteria
     * @return List<PersonTypeDTO> list of found personTypes
     */
    List<PersonTypeDTO> findPersonTypes(String searchFor);

    /**
     * Find all PersonTypes.
     * @return a List of all PersonTypes
     */
    @Transactional(readOnly = true)
    List<PersonTypeDTO> findAllPersonTypes();

    /**
     * Save/update a PersonType.
     * @param dto the personType to save/update
     */
    @Transactional
    void savePersonType(PersonTypeDTO dto);

    /**
     * Delete a PersonType.
     * @param uuid the uuid to delete
     */
    @Transactional
    void deletePersonType(String uuid);

}
