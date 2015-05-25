package be.janschraepen.hellokitty.repository;

import be.janschraepen.hellokitty.domain.person.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * PersonRepository interface. This interface handles all data access
 * to the hsql database Person records.
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, String> {

    /**
     * Find Person by its id.
     *
     * @param id the id to find
     * @return the found Person, or null if not found
     */
    Person findById(String id);

    /**
     * Find Persons by given search criteria
     *
     * @param searchFor the search criteria
     * @return List<Person> list of found persons
     */
    @Query("SELECT distinct p FROM Person p INNER JOIN p.contacts c WHERE lower(p.firstName) LIKE %?1% OR lower(p.lastName) LIKE %?1% OR lower(c.value) LIKE %?1%")
    List<Person> find(String searchFor);

}
