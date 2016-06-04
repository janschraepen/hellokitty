package be.janschraepen.hellokitty.services.impl;

import be.janschraepen.hellokitty.domain.person.*;
import be.janschraepen.hellokitty.repository.PersonContactRepository;
import be.janschraepen.hellokitty.repository.PersonRepository;
import be.janschraepen.hellokitty.services.PersonService;
import be.janschraepen.hellokitty.util.EmailExporter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
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
        List<Person> persons = personRepository.find(searchFor.toLowerCase());
        return ObjectFactory.getInstance().createListPersonDTOs(persons);
    }

    @Override
    public List<PersonDTO> findAllPersons() {
        Sort sort = new Sort(Sort.Direction.ASC, "lastName", "firstName");
        List<Person> persons = personRepository.findAll(sort);
        return ObjectFactory.getInstance().createListPersonDTOs(persons);
    }

    @Override
    public PersonDTO savePerson(PersonDTO dto) throws ConstraintViolationException {
        Person person;
        if (StringUtils.isBlank(dto.getId())) {
            // save a new person
            person = new Person();
        } else {
            // update an existing person
            person = personRepository.findById(dto.getId());
        }
        person.setFirstName(dto.getFirstName());
        person.setLastName(dto.getLastName());
        person.setAddressLine1(dto.getAddressLine1());
        person.setAddressLine2(dto.getAddressLine2());
        person.setExtraInfo(dto.getExtraInfo());

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
    public PersonContactDTO savePersonContact(PersonContactDTO dto) throws ConstraintViolationException {
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

    @Override
    public String findAllEmailContacts() {
        List<PersonContact> emailContacts = personContactRepository.findEmailContacts();
        return EmailExporter.generateCSV(emailContacts);
    }

}
