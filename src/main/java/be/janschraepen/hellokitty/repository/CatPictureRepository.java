package be.janschraepen.hellokitty.repository;

import be.janschraepen.hellokitty.domain.cat.CatPerson;
import be.janschraepen.hellokitty.domain.cat.CatPicture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * CatPictureRepository interface. This interface handles all data
 * access to the hsql database CatPicture records.
 */
@Repository
public interface CatPictureRepository extends JpaRepository<CatPicture, String> {

    /**
     * Find CatPicture for Cat.
     *
     * @param catId the Cat id to find
     * @return the found CatPicture, or null if not found
     */
    @Query("SELECT p FROM CatPicture p WHERE p.cat.id = ?1")
    CatPicture findPictureForCat(String catId);

    /**
     * Find CatPicture for Cat.
     *
     * @param catId the Cat id to find
     * @return the found CatPicture, or null if not found
     */
    @Modifying
    @Query("DELETE FROM CatPicture p WHERE p.cat.id = ?1")
    void deleteAllPicutesForCat(String catId);

}
