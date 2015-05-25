package be.janschraepen.hellokitty.repository;

import be.janschraepen.hellokitty.domain.persontype.PersonType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * PersonTypeRepository interface. This interface handles all data access
 *  to the hsql database PersonType records.
 */
@Repository
public interface PersonTypeRepository extends JpaRepository<PersonType, String> {

    /**
     * Find PersonType by its id.
     * @param id the id to find
     * @return the found PersonType, or null if not found
     */
    PersonType findById(String id);

    /**
     * Find PersonTypes by given search criteria
     * @param searchFor the search criteria
     * @return List<PersonType> list of found personTypes
     */
    @Query("SELECT p FROM PersonType p WHERE lower(p.name) LIKE %?1%")
    List<PersonType> find(String searchFor);

}
