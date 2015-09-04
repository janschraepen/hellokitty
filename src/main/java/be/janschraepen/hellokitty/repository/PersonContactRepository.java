package be.janschraepen.hellokitty.repository;

import be.janschraepen.hellokitty.domain.person.Person;
import be.janschraepen.hellokitty.domain.person.PersonContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * PersonContactRepository interface. This interface handles all data
 * access to the hsql database PersonContact records.
 */
@Repository
public interface PersonContactRepository extends JpaRepository<PersonContact, String> {

    /**
     * Find PersonContact by its id.
     *
     * @param id the id to find
     * @return the found PersonContact, or null if not found
     */
    PersonContact findById(String id);

    /**
     * Find all Email contacts
     *
     * @return List<PersonContact> list of found persons
     */
    @Query("SELECT distinct pc FROM PersonContact pc WHERE pc.type = 'EMAIL'")
    List<PersonContact> findEmailContacts();

}
