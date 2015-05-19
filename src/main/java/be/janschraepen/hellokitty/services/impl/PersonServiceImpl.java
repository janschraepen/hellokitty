package be.janschraepen.hellokitty.services.impl;

import be.janschraepen.hellokitty.domain.person.*;
import be.janschraepen.hellokitty.domain.persontype.PersonType;
import be.janschraepen.hellokitty.repository.PersonContactRepository;
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

    @Autowired
    private PersonContactRepository personContactRepository;

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

    @Override
    public void deletePersons(String[] uuids) {
        for (String uuid : uuids) {
            deletePerson(uuid);
        }
    }

    @Override
    public PersonContactDTO savePersonContact(PersonContactDTO dto) {
        PersonContact personContact = new PersonContact();
        personContact.setPerson(personRepository.findById(dto.getPersonId()));
        personContact.setType(dto.getType());
        personContact.setValue(dto.getValue());

        personContact = personContactRepository.saveAndFlush(personContact);
        return ObjectFactory.getInstance().createPersonContactDTO(personContact);
    }

    @Override
    public void deletePersonContact(String uuid) {
        PersonContact personContact = personContactRepository.findById(uuid);
        if (personContact != null) {
            personContactRepository.delete(personContact);
        }
    }

    @Override
    public void deletePersonContacts(String[] uuids) {
        for (String uuid : uuids) {
            deletePersonContact(uuid);
        }
    }

}
