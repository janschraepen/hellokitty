package be.janschraepen.hellokitty.services;

import be.janschraepen.hellokitty.domain.cat.CatDTO;
import be.janschraepen.hellokitty.domain.cat.CatPersonDTO;
import be.janschraepen.hellokitty.domain.cat.CatPictureDTO;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolationException;
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
     * @throws ConstraintViolationException if one occurs
     */
    @Transactional
    CatDTO saveCat(CatDTO dto) throws ConstraintViolationException;

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
     * Find CatPersons by given search criteria.
     *
     * @param searchFor the search criteria
     * @return List<CatPersonDTO> list of found catPersons
     */
    @Transactional(readOnly = true)
    List<CatPersonDTO> findCatPersons(String searchFor);

    /**
     * Save/update a CatPerson.
     *
     * @param dto the catPerson to save/update
     * @return CatPersonDTO the saved instance
     */
    @Transactional
    CatPersonDTO saveCatPerson(CatPersonDTO dto);

    /**
     * Delete a CatPerson.
     *
     * @param uuid the uuid to delete
     */
    @Transactional
    void deleteCatPerson(String uuid);

    /**
     * Delete multiple CatPersons.
     *
     * @param uuids the uuids to delete
     */
    @Transactional
    void deleteCatPersons(String[] uuids);

    /**
     * Find CatPicture with given uuid.
     * @param uuid the Cat id
     * @return CatPictureDTO the found CatPicture or null if not found
     */
    @Transactional(readOnly = true)
    CatPictureDTO findCatPicture(String uuid);

    /**
     * Update CatPicture. Removes old picture first, before adding
     * a new picture
     * @param dto the catPicture to save/update
     */
    @Transactional
    void updateCatPicture(CatPictureDTO dto);

}
