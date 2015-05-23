package be.janschraepen.hellokitty.repository;

import be.janschraepen.hellokitty.domain.cat.CatPerson;
import be.janschraepen.hellokitty.domain.person.PersonContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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

}
