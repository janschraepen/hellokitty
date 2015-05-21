package be.janschraepen.hellokitty.services.impl;

import be.janschraepen.hellokitty.domain.person.*;
import be.janschraepen.hellokitty.repository.PersonContactRepository;
import be.janschraepen.hellokitty.repository.PersonRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PersonServiceImplTest {

    public static final String SEARCH_FOR = "_2";
    public static final String UUID = "uuid";

    @Mock
    private PersonRepository personRepository;

    @Mock
    private PersonContactRepository personContactRepository;

    @InjectMocks
    private PersonServiceImpl underTest = new PersonServiceImpl();

    @Test
    public void testFindPerson() throws Exception {
        Person person_1 = createPerson("firstName_1", "lastName_1", "addressLine1_1", "addressLine2_1");

        when(personRepository.findById(UUID)).thenReturn(person_1);

        PersonDTO dto = underTest.findPerson(UUID);
        assertNotNull(dto);
        assertEquals("firstName_1", dto.getFirstName());
        assertEquals("lastName_1", dto.getLastName());
        assertEquals("addressLine1_1", dto.getAddressLine1());
        assertEquals("addressLine2_1", dto.getAddressLine2());
    }

    @Test
    public void testFindPerson_noResultReturnsNull() throws Exception {
        when(personRepository.findById(UUID)).thenReturn(null);

        PersonDTO dto = underTest.findPerson(UUID);
        assertNull(dto);
    }

    @Test
    public void testFindPersons() throws Exception {
        Person person_1 = createPerson("firstName_1", "lastName_1", "addressLine1_1", "addressLine2_1");
        Person person_2 = createPerson("firstName_2", "lastName_2", "addressLine1_2", "addressLine2_2");

        when(personRepository.find(SEARCH_FOR)).thenReturn(Arrays.asList(person_2));

        List<PersonDTO> list = underTest.findPersons(SEARCH_FOR);
        assertNotNull(list);
        assertEquals(1, list.size());

        assertEquals("firstName_2", list.get(0).getFirstName());
        assertEquals("lastName_2", list.get(0).getLastName());
        assertEquals("addressLine1_2", list.get(0).getAddressLine1());
        assertEquals("addressLine2_2", list.get(0).getAddressLine2());
    }

    @Test
    public void testFindPersons_noResultsReturnsEmptyList() throws Exception {
        when(personRepository.find(SEARCH_FOR)).thenReturn(Collections.emptyList());

        List<PersonDTO> list = underTest.findPersons(SEARCH_FOR);
        assertNotNull(list);
        assertEquals(0, list.size());
    }

    @Test
    public void testFindAllPersons() throws Exception {
        Person person_1 = createPerson("firstName_1", "lastName_1", "addressLine1_1", "addressLine2_1");
        Person person_2 = createPerson("firstName_2", "lastName_2", "addressLine1_2", "addressLine2_2");

        when(personRepository.findAll()).thenReturn(Arrays.asList(person_1, person_2));

        List<PersonDTO> list = underTest.findAllPersons();
        assertNotNull(list);
        assertEquals(2, list.size());

        assertEquals("firstName_1", list.get(0).getFirstName());
        assertEquals("lastName_1", list.get(0).getLastName());
        assertEquals("addressLine1_1", list.get(0).getAddressLine1());
        assertEquals("addressLine2_1", list.get(0).getAddressLine2());

        assertEquals("firstName_2", list.get(1).getFirstName());
        assertEquals("lastName_2", list.get(1).getLastName());
        assertEquals("addressLine1_2", list.get(1).getAddressLine1());
        assertEquals("addressLine2_2", list.get(1).getAddressLine2());
    }

    @Test
    public void testFindAllPersons_noResultsReturnsEmptyList() throws Exception {
        when(personRepository.findAll()).thenReturn(Collections.emptyList());

        List<PersonDTO> list = underTest.findAllPersons();
        assertNotNull(list);
        assertEquals(0, list.size());
    }

    @Test
    public void testSavePerson_newInstance() throws Exception {
        Person update = createPerson(UUID, "firstName_3", "lastName_3", "addressLine1_3", "addressLine2_3");
        PersonDTO _new = createPersonDTO("firstName_3", "lastName_3", "addressLine1_3", "addressLine2_3");

        ArgumentCaptor<Person> p = ArgumentCaptor.forClass(Person.class);
        when(personRepository.saveAndFlush(p.capture())).thenReturn(update);

        underTest.savePerson(_new);

        Person arg = p.getValue();
        assertNotNull(arg);
        assertEquals("firstName_3", arg.getFirstName());
        assertEquals("lastName_3", arg.getLastName());
        assertEquals("addressLine1_3", arg.getAddressLine1());
        assertEquals("addressLine2_3", arg.getAddressLine2());
    }

    @Test
    public void testSavePerson_existingInstance() throws Exception {
        Person toUpdate = createPerson(UUID, "firstName_3", "lastName_3", "addressLine1_3", "addressLine2_3");
        Person update = createPerson(UUID, "firstName_3", "lastName_4", "addressLine1_3", "addressLine2_3");
        PersonDTO updated = createPersonDTO(UUID, "firstName_3", "lastName_4", "addressLine1_3", "addressLine2_3");

        when(personRepository.findById(UUID)).thenReturn(toUpdate);

        ArgumentCaptor<Person> p = ArgumentCaptor.forClass(Person.class);
        when(personRepository.saveAndFlush(p.capture())).thenReturn(update);

        underTest.savePerson(updated);

        Person arg = p.getValue();
        assertNotNull(arg);
        assertEquals("firstName_3", arg.getFirstName());
        assertEquals("lastName_4", arg.getLastName());
        assertEquals("addressLine1_3", arg.getAddressLine1());
        assertEquals("addressLine2_3", arg.getAddressLine2());
    }

    @Test
    public void testDeletePerson_nonExistingInstance() throws Exception {
        when(personRepository.findById(UUID)).thenReturn(null);

        underTest.deletePerson(UUID);
    }

    @Test
    public void testDeletePerson_existingInstance() throws Exception {
        Person toDelete = createPerson(UUID, "firstName_3", "lastName_3", "addressLine1_3", "addressLine2_3");

        when(personRepository.findById(UUID)).thenReturn(toDelete);

        underTest.deletePerson(UUID);

        verify(personRepository).delete(toDelete);
    }

    @Test
    public void testSavePersonContact_newInstance() throws Exception {
        PersonContact update = createPersonContact(UUID, ContactType.EMAIL, "value");
        PersonContactDTO _new = createPersonContactDTO("uuid", ContactType.EMAIL, "value");

        Person person = new Person();
        when(personRepository.findById("uuid")).thenReturn(person);

        ArgumentCaptor<PersonContact> p = ArgumentCaptor.forClass(PersonContact.class);
        when(personContactRepository.saveAndFlush(p.capture())).thenReturn(update);

        underTest.savePersonContact(_new);

        PersonContact arg = p.getValue();
        assertNotNull(arg);
        assertSame(person, arg.getPerson());
        assertEquals(ContactType.EMAIL, arg.getType());
        assertEquals("value", arg.getValue());
    }

    @Test
    public void testDeletePersons_existingInstance() throws Exception {
        Person toDelete = createPerson(UUID, "firstName_3", "lastName_3", "addressLine1_3", "addressLine2_3");

        when(personRepository.findById(UUID)).thenReturn(toDelete);

        underTest.deletePersons(new String[]{UUID});

        ArgumentCaptor<Person> p = ArgumentCaptor.forClass(Person.class);
        verify(personRepository).delete(p.capture());

        Person arg = p.getValue();
        assertNotNull(arg);
        assertEquals("firstName_3", arg.getFirstName());
        assertEquals("lastName_3", arg.getLastName());
        assertEquals("addressLine1_3", arg.getAddressLine1());
        assertEquals("addressLine2_3", arg.getAddressLine2());
    }

    @Test
    public void testDeletePersonContact_nonExistingInstance() throws Exception {
        when(personRepository.findById(UUID)).thenReturn(null);

        underTest.deletePersonContact(UUID);
    }

    @Test
    public void testDeletePersonContact_existingInstance() throws Exception {
        PersonContact toDelete = createPersonContact(UUID, ContactType.EMAIL, "email");

        when(personContactRepository.findById(UUID)).thenReturn(toDelete);

        underTest.deletePersonContact(UUID);

        ArgumentCaptor<PersonContact> p = ArgumentCaptor.forClass(PersonContact.class);
        verify(personContactRepository).delete(p.capture());

        PersonContact arg = p.getValue();
        assertNotNull(arg);
        assertEquals(ContactType.EMAIL, arg.getType());
        assertEquals("email", arg.getValue());
    }

    @Test
    public void testDeletePersonContacts_existingInstance() throws Exception {
        PersonContact toDelete = createPersonContact(UUID, ContactType.EMAIL, "email");

        when(personContactRepository.findById(UUID)).thenReturn(toDelete);

        underTest.deletePersonContacts(new String[]{UUID});

        ArgumentCaptor<PersonContact> p = ArgumentCaptor.forClass(PersonContact.class);
        verify(personContactRepository).delete(p.capture());

        PersonContact arg = p.getValue();
        assertNotNull(arg);
        assertEquals(ContactType.EMAIL, arg.getType());
        assertEquals("email", arg.getValue());
    }

    private PersonDTO createPersonDTO(String firstName, String lastName, String addressLine1, String addressLine2) {
        return createPersonDTO(null, firstName, lastName, addressLine1, addressLine2);
    }

    private PersonDTO createPersonDTO(String uuid, String firstName, String lastName, String addressLine1, String addressLine2) {
        PersonDTO dto = new PersonDTO();
        dto.setId(uuid);
        dto.setFirstName(firstName);
        dto.setLastName(lastName);
        dto.setAddressLine1(addressLine1);
        dto.setAddressLine2(addressLine2);
        return dto;
    }

    private Person createPerson(String firstName, String lastName, String addressLine1, String addressLine2) {
        return createPerson(null, firstName, lastName, addressLine1, addressLine2);
    }

    private Person createPerson(String uuid, String firstName, String lastName, String addressLine1, String addressLine2) {
        Person person = new Person();
        person.setId(uuid);
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setAddressLine1(addressLine1);
        person.setAddressLine2(addressLine2);
        return person;
    }

    private PersonContact createPersonContact(String uuid, ContactType type, String value) {
        Person person = new Person();

        PersonContact personContact = new PersonContact();
        personContact.setId(uuid);
        personContact.setPerson(person);
        personContact.setType(type);
        personContact.setValue(value);
        return personContact;
    }

    private PersonContactDTO createPersonContactDTO(String uuid, ContactType type, String value) {
        PersonContactDTO personContact = new PersonContactDTO();
        personContact.setPersonId(uuid);
        personContact.setType(type);
        personContact.setValue(value);
        return personContact;
    }

}