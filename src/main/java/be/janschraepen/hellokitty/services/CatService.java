package be.janschraepen.hellokitty.services;

import be.janschraepen.hellokitty.domain.cat.CatDTO;
import be.janschraepen.hellokitty.domain.cat.CatPersonDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * CatService interface. This class defines all possibles actions
 * regarding the Cats.
 */
public interface CatService {

    /**
     * Find a Cat with given uuid.
     *
     * @param uuid the uuid to find
     * @return CatDTO the found Cat, or null if not found
     */
    @Transactional(readOnly = true)
    CatDTO findCat(String uuid);

    /**
     * Find Cats by given search criteria.
     *
     * @param searchFor the search criteria
     * @return List<CatDTO> list of found cats
     */
    @Transactional(readOnly = true)
    List<CatDTO> findCats(String searchFor);

    /**
     * Find all Cats.
     *
     * @return a List of all Cats
     */
    @Transactional(readOnly = true)
    List<CatDTO> findAllCats();

    /**
     * Save/update a Cat.
     *
     * @param dto the cat to save/update
     * @return CatDTO the saved instance
     */
    @Transactional
    CatDTO saveCat(CatDTO dto);

    /**
     * Delete a Cat.
     *
     * @param uuid the uuid to delete
     */
    @Transactional
    void deleteCat(String uuid);

    /**
     * Delete multiple Cats.
     *
     * @param uuids the uuids to delete
     */
    @Transactional
    void deleteCats(String[] uuids);

    /**
     * Save/update a CatPerson.
     *
     * @param dto the catPerson to save/update
     * @return CatPersonDTO the saved instance
     */
    @Transactional
    CatPersonDTO saveCatPerson(CatPersonDTO dto);

}
