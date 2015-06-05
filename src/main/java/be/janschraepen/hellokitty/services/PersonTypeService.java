package be.janschraepen.hellokitty.services;

import be.janschraepen.hellokitty.domain.persontype.CannotModifyPersonTypeException;
import be.janschraepen.hellokitty.domain.persontype.PersonTypeDTO;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolationException;
import java.util.List;

/**
 * PersonTypeService interface. This class defines all possibles actions
 * regarding the PersonTypes.
 */
public interface PersonTypeService {

    /**
     * Find a PersonType with given uuid.
     *
     * @param uuid the uuid to find
     * @return PersonTypeDTO the found PersonType, or null if not found
     */
    @Transactional(readOnly = true)
    PersonTypeDTO findPersonType(String uuid);

    /**
     * Find PersonTypes by given search criteria.
     *
     * @param searchFor the search criteria
     * @return List<PersonTypeDTO> list of found personTypes
     */
    @Transactional(readOnly = true)
    List<PersonTypeDTO> findPersonTypes(String searchFor);

    /**
     * Find all PersonTypes.
     *
     * @return a List of all PersonTypes
     */
    @Transactional(readOnly = true)
    List<PersonTypeDTO> findAllPersonTypes();

    /**
     * Save/update a PersonType.
     *
     * @param dto the personType to save/update
     * @return PersonTypeDTO the saved instance
     * @throws CannotModifyPersonTypeException if a system required PersonType is being modified
     * @throws javax.validation.ConstraintViolationException if one occurs
     */
    @Transactional
    PersonTypeDTO savePersonType(PersonTypeDTO dto) throws CannotModifyPersonTypeException, ConstraintViolationException;

    /**
     * Delete a PersonType.
     *
     * @param uuid the uuid to delete
     * @throws CannotModifyPersonTypeException if a system required PersonType is being modified
     */
    @Transactional
    void deletePersonType(String uuid) throws CannotModifyPersonTypeException;

    /**
     * Delete multiple PersonTypes.
     *
     * @param uuids the uuids to delete
     * @throws CannotModifyPersonTypeException if a system required PersonType is being modified
     */
    @Transactional
    void deletePersonTypes(String[] uuids) throws CannotModifyPersonTypeException;

}
