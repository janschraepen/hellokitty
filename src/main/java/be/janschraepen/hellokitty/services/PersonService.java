package be.janschraepen.hellokitty.services;

import be.janschraepen.hellokitty.domain.person.PersonContactDTO;
import be.janschraepen.hellokitty.domain.person.PersonDTO;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolationException;
import java.util.List;

/**
 * PersonService interface. This class defines all possibles actions
 * regarding the Persons.
 */
public interface PersonService {

    /**
     * Find a Person with given uuid.
     *
     * @param uuid the uuid to find
     * @return PersonDTO the found Person, or null if not found
     */
    @Transactional(readOnly = true)
    PersonDTO findPerson(String uuid);

    /**
     * Find Persons by given search criteria.
     *
     * @param searchFor the search criteria
     * @return List<PersonDTO> list of found persons
     */
    @Transactional(readOnly = true)
    List<PersonDTO> findPersons(String searchFor);

    /**
     * Find all Persons.
     *
     * @return a List of all Persons
     */
    @Transactional(readOnly = true)
    List<PersonDTO> findAllPersons();

    /**
     * Save/update a Person.
     *
     * @param dto the person to save/update
     * @return PersonDTO the saved instance
     * @throws ConstraintViolationException if one occurs
     */
    @Transactional
    PersonDTO savePerson(PersonDTO dto) throws ConstraintViolationException;

    /**
     * Delete a Person.
     *
     * @param uuid the uuid to delete
     */
    @Transactional
    void deletePerson(String uuid);

    /**
     * Delete multiple Persons.
     *
     * @param uuids the uuids to delete
     */
    @Transactional
    void deletePersons(String[] uuids);

    /**
     * Save/update a PersonContact.
     *
     * @param dto the personContact to save/update
     * @return PersonContactDTO the saved instance
     */
    @Transactional
    PersonContactDTO savePersonContact(PersonContactDTO dto);

    /**
     * Delete a PersonContact.
     *
     * @param uuid the uuid to delete
     */
    @Transactional
    void deletePersonContact(String uuid);

    /**
     * Delete multiple PersonContacts.
     *
     * @param uuids the uuids to delete
     */
    @Transactional
    void deletePersonContacts(String[] uuids);

}
