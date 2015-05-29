package be.janschraepen.hellokitty.services.impl;

import be.janschraepen.hellokitty.domain.persontype.CannotModifyPersonTypeException;
import be.janschraepen.hellokitty.domain.persontype.ObjectFactory;
import be.janschraepen.hellokitty.domain.persontype.PersonType;
import be.janschraepen.hellokitty.domain.persontype.PersonTypeDTO;
import be.janschraepen.hellokitty.repository.PersonTypeRepository;
import be.janschraepen.hellokitty.services.PersonTypeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * PersonTypeServiceImpl class. This class provides in an implementation
 * for the PersonTypeService interface.
 */
@Service
public class PersonTypeServiceImpl implements PersonTypeService {

    @Autowired
    private PersonTypeRepository personTypeRepository;

    @Override
    public PersonTypeDTO findPersonType(String uuid) {
        PersonType personType = personTypeRepository.findById(uuid);

        PersonTypeDTO p = null;
        if (personType != null) {
            p = ObjectFactory.getInstance().createDTO(personType);
        }
        return p;
    }

    @Override
    public List<PersonTypeDTO> findPersonTypes(String searchFor) {
        List<PersonType> personTypes = personTypeRepository.find(searchFor.toLowerCase());
        return ObjectFactory.getInstance().createListDTOs(personTypes);
    }

    @Override
    public List<PersonTypeDTO> findAllPersonTypes() {
        List<PersonType> personTypes = personTypeRepository.findAll();
        return ObjectFactory.getInstance().createListDTOs(personTypes);
    }

    @Override
    public PersonTypeDTO savePersonType(PersonTypeDTO dto) throws CannotModifyPersonTypeException {
        PersonType personType;
        if (StringUtils.isBlank(dto.getId())) {
            // save a new personType
            personType = new PersonType();
            if (isSystemPersonType(dto.getShortCode())) {
                throw new CannotModifyPersonTypeException();
            }
        } else {
            // update an existing personType
            personType = personTypeRepository.findById(dto.getId());
            if (isSystemPersonType(personType.getShortCode())) {
                throw new CannotModifyPersonTypeException();
            }
        }
        personType.setShortCode(dto.getShortCode());
        personType.setName(dto.getName());

        personType = personTypeRepository.saveAndFlush(personType);
        return ObjectFactory.getInstance().createDTO(personType);
    }

    @Override
    public void deletePersonType(String uuid) throws CannotModifyPersonTypeException {
        PersonType personType = personTypeRepository.findById(uuid);
        if (personType != null) {
            if (isSystemPersonType(personType.getShortCode())) {
                throw new CannotModifyPersonTypeException();
            }
            personTypeRepository.delete(personType);
        }
    }

    @Override
    public void deletePersonTypes(String[] uuids) throws CannotModifyPersonTypeException {
        for (String uuid : uuids) {
            deletePersonType(uuid);
        }
    }

    /**
     * Check wh
     * @param shortCode
     * @return
     */
    boolean isSystemPersonType(String shortCode) {
        return PersonType.OWNER.equals(shortCode)
                || PersonType.CONTACT.equals(shortCode)
                || PersonType.VET.equals(shortCode);
    }

}
