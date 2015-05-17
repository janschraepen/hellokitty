package be.janschraepen.hellokitty.services.impl;

import be.janschraepen.hellokitty.domain.person.ObjectFactory;
import be.janschraepen.hellokitty.domain.person.Person;
import be.janschraepen.hellokitty.domain.person.PersonDTO;
import be.janschraepen.hellokitty.domain.persontype.PersonType;
import be.janschraepen.hellokitty.repository.PersonRepository;
import be.janschraepen.hellokitty.repository.PersonTypeRepository;
import be.janschraepen.hellokitty.services.PersonService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * PersonServiceImpl class. This class provides in an implementation
 * for the PersonService interface.
 */
@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonTypeRepository personTypeRepository;

    @Override
    public PersonDTO findPerson(String uuid) {
        Person person = personRepository.findById(uuid);

        PersonDTO p = null;
        if (person != null) {
            p = ObjectFactory.getInstance().createPersonDTO(person);
        }
        return p;
    }

    @Override
    public List<PersonDTO> findPersons(String searchFor) {
        List<Person> persons = personRepository.find(searchFor);
        return ObjectFactory.getInstance().createListPersonDTOs(persons);
    }

    @Override
    public List<PersonDTO> findAllPersons() {
        List<Person> persons = personRepository.findAll();
        return ObjectFactory.getInstance().createListPersonDTOs(persons);
    }

    @Override
    public PersonDTO savePerson(PersonDTO dto) {
        Person person;
        if (StringUtils.isBlank(dto.getId())) {
            // save a new person
            person = new Person();
        } else {
            // update an existing person
            person = personRepository.findById(dto.getId());
        }

        PersonType personType = personTypeRepository.findById(dto.getPersonTypeId());
        person.setType(personType);
        person.setFirstName(dto.getFirstName());
        person.setLastName(dto.getLastName());
        person.setAddressLine1(dto.getAddressLine1());
        person.setAddressLine2(dto.getAddressLine2());
        person.setTelephone(dto.getTelephone());
        person.setGsm(dto.getGsm());
        person.setEmail(dto.getEmail());

        person = personRepository.saveAndFlush(person);
        return ObjectFactory.getInstance().createPersonDTO(person);
    }

    @Override
    public void deletePerson(String uuid) {
        Person person = personRepository.findById(uuid);
        if (person != null) {
            personRepository.delete(person);
        }
    }

}
