package be.janschraepen.hellokitty.repository;

import be.janschraepen.hellokitty.domain.cat.Cat;
import be.janschraepen.hellokitty.domain.person.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * CatRepository interface. This interface handles all data access
 * to the hsql database Cat records.
 */
@Repository
public interface CatRepository extends JpaRepository<Cat, String> {

    /**
     * Find Cat by its id.
     *
     * @param id the id to find
     * @return the found Cat, or null if not found
     */
    Cat findById(String id);

    /**
     * Find Cats by given search criteria
     *
     * @param searchFor the search criteria
     * @return List<Cat> list of found persons
     */
    @Query("SELECT c FROM Cat c WHERE lower(c.name) LIKE %?1%")
    List<Cat> find(String searchFor);

}
