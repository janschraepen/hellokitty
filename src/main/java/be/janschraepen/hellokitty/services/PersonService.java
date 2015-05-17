package be.janschraepen.hellokitty.services;

import be.janschraepen.hellokitty.domain.person.PersonDTO;
import org.springframework.transaction.annotation.Transactional;

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
     */
    @Transactional
    PersonDTO savePerson(PersonDTO dto);

    /**
     * Delete a Person.
     *
     * @param uuid the uuid to delete
     */
    @Transactional
    void deletePerson(String uuid);

    /**
     * Delete a PersonContact.
     *
     * @param uuid the uuid to delete
     */
    @Transactional
    void deletePersonContact(String uuid);

}
