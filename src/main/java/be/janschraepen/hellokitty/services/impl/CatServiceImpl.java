package be.janschraepen.hellokitty.services.impl;

import be.janschraepen.hellokitty.domain.cat.*;
import be.janschraepen.hellokitty.repository.CatPersonRepository;
import be.janschraepen.hellokitty.repository.CatRepository;
import be.janschraepen.hellokitty.repository.PersonRepository;
import be.janschraepen.hellokitty.repository.PersonTypeRepository;
import be.janschraepen.hellokitty.services.CatService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * CatServiceImpl class. This class provides in an implementation
 * for the CatService interface.
 */
@Service
public class CatServiceImpl implements CatService {

    @Autowired
    private CatRepository catRepository;

    @Autowired
    private CatPersonRepository catPersonRepository;

    @Autowired
    private PersonTypeRepository personTypeRepository;

    @Autowired
    private PersonRepository personRepository;

    @Override
    public CatDTO findCat(String uuid) {
        Cat cat = catRepository.findById(uuid);

        CatDTO c = null;
        if (cat != null) {
            c = ObjectFactory.getInstance().createCatDTO(cat);
        }
        return c;
    }

    @Override
    public List<CatDTO> findCats(String searchFor) {
        List<Cat> cats = catRepository.find(searchFor);
        return ObjectFactory.getInstance().createListCatDTOs(cats);
    }

    @Override
    public List<CatDTO> findAllCats() {
        List<Cat> cats = catRepository.findAll();
        return ObjectFactory.getInstance().createListCatDTOs(cats);
    }

    @Override
    public CatDTO saveCat(CatDTO dto) {
        Cat cat;
        if (StringUtils.isBlank(dto.getId())) {
            // save a new cat
            cat = new Cat();
        } else {
            // update an existing cat
            cat = catRepository.findById(dto.getId());
        }
        cat.setName(dto.getName());
        cat.setBreed(dto.getBreed());
        cat.setAge(dto.getAge());
        cat.setGender(dto.getGender());
        cat.setNeutered(dto.isNeutered());
        cat.setChipped(dto.isChipped());
        cat.setAttention(dto.getAttention());
        cat.setBehavioral(dto.getBehavioral());
        cat.setNutrition(dto.getNutrition());

        cat = catRepository.saveAndFlush(cat);
        return ObjectFactory.getInstance().createCatDTO(cat);
    }

    @Override
    public void deleteCat(String uuid) {
        Cat cat = catRepository.findById(uuid);
        if (cat != null) {
            catRepository.delete(cat);
        }
    }

    @Override
    public void deleteCats(String[] uuids) {
        for (String uuid : uuids) {
            deleteCat(uuid);
        }
    }

    @Override
    public CatPersonDTO saveCatPerson(CatPersonDTO dto) {
        CatPerson catPerson = new CatPerson();
        catPerson.setCat(catRepository.findById(dto.getCatId()));
        catPerson.setType(personTypeRepository.findById(dto.getPersonTypeId()));
        catPerson.setPerson(personRepository.findById(dto.getPersonId()));

        catPerson = catPersonRepository.saveAndFlush(catPerson);
        return ObjectFactory.getInstance().createCatPersonDTO(catPerson);
    }

    @Override
    public void deleteCatPerson(String uuid) {
        CatPerson catPerson = catPersonRepository.findById(uuid);
        if (catPerson != null) {
            catPersonRepository.delete(catPerson);
        }
    }

    @Override
    public void deleteCatPersons(String[] uuids) {
        for (String uuid : uuids) {
            deleteCatPerson(uuid);
        }
    }

}
