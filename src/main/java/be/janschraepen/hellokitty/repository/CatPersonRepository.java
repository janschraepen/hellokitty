package be.janschraepen.hellokitty.repository;

import be.janschraepen.hellokitty.domain.cat.CatPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * CatPersonRepository interface. This interface handles all data
 * access to the hsql database CatPerson records.
 */
@Repository
public interface CatPersonRepository extends JpaRepository<CatPerson, String> {

    /**
     * Find CatPerson by its id.
     *
     * @param id the id to find
     * @return the found CatPerson, or null if not found
     */
    CatPerson findById(String id);

    /**
     * Find Persons by given search criteria
     *
     * @param searchFor the search criteria
     * @return List<Person> list of found persons
     */
    @Query("SELECT distinct cp " +
            "FROM CatPerson cp " +
            "INNER JOIN cp.cat c " +
            "INNER JOIN cp.person p " +
            "INNER JOIN cp.type t " +
            "WHERE t.shortCode = 'OWNER' " +
            "AND ( lower(p.firstName) LIKE %?1% OR lower(p.lastName) LIKE %?1% OR lower(c.name) LIKE %?1% ) " +
            "ORDER BY p.lastName, p.firstName, c.name")
    List<CatPerson> find(String searchFor);


}
