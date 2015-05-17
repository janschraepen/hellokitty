package be.janschraepen.hellokitty.services.impl;

import be.janschraepen.hellokitty.domain.person.Person;
import be.janschraepen.hellokitty.domain.person.PersonDTO;
import be.janschraepen.hellokitty.domain.persontype.PersonType;
import be.janschraepen.hellokitty.repository.PersonRepository;
import be.janschraepen.hellokitty.repository.PersonTypeRepository;
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
    private PersonTypeRepository personTypeRepository;

    @InjectMocks
    private PersonServiceImpl underTest = new PersonServiceImpl();

    @Test
    public void testFindPerson() throws Exception {
        Person person_1 = createPerson("firstName_1", "lastName_1", "addressLine1_1", "addressLine2_1", "telephone_1", "gsm_1", "email_1");

        when(personRepository.findById(UUID)).thenReturn(person_1);

        PersonDTO dto = underTest.findPerson(UUID);
        assertNotNull(dto);
        assertEquals("personType-uuid", dto.getPersonTypeId());
        assertEquals("firstName_1", dto.getFirstName());
        assertEquals("lastName_1", dto.getLastName());
        assertEquals("addressLine1_1", dto.getAddressLine1());
        assertEquals("addressLine2_1", dto.getAddressLine2());
        assertEquals("telephone_1", dto.getTelephone());
        assertEquals("gsm_1", dto.getGsm());
        assertEquals("email_1", dto.getEmail());
    }

    @Test
    public void testFindPerson_noResultReturnsNull() throws Exception {
        when(personRepository.findById(UUID)).thenReturn(null);

        PersonDTO dto = underTest.findPerson(UUID);
        assertNull(dto);
    }

    @Test
    public void testFindPersons() throws Exception {
        Person person_1 = createPerson("firstName_1", "lastName_1", "addressLine1_1", "addressLine2_1", "telephone_1", "gsm_1", "email_1");
        Person person_2 = createPerson("firstName_2", "lastName_2", "addressLine1_2", "addressLine2_2", "telephone_2", "gsm_2", "email_2");

        when(personRepository.find(SEARCH_FOR)).thenReturn(Arrays.asList(person_2));

        List<PersonDTO> list = underTest.findPersons(SEARCH_FOR);
        assertNotNull(list);
        assertEquals(1, list.size());

        assertEquals("personType-uuid", list.get(0).getPersonTypeId());
        assertEquals("firstName_2", list.get(0).getFirstName());
        assertEquals("lastName_2", list.get(0).getLastName());
        assertEquals("addressLine1_2", list.get(0).getAddressLine1());
        assertEquals("addressLine2_2", list.get(0).getAddressLine2());
        assertEquals("telephone_2", list.get(0).getTelephone());
        assertEquals("gsm_2", list.get(0).getGsm());
        assertEquals("email_2", list.get(0).getEmail());
    }

    @Test
    public void testFindPersons_noResultsReturnsEmptyList() throws Exception {
        when(personRepository.find(SEARCH_FOR)).thenReturn(Collections.emptyList());

        List<PersonDTO> list = underTest.findAllPersons();
        assertNotNull(list);
        assertEquals(0, list.size());
    }

    @Test
    public void testFindAllPersons() throws Exception {
        Person person_1 = createPerson("firstName_1", "lastName_1", "addressLine1_1", "addressLine2_1", "telephone_1", "gsm_1", "email_1");
        Person person_2 = createPerson("firstName_2", "lastName_2", "addressLine1_2", "addressLine2_2", "telephone_2", "gsm_2", "email_2");

        when(personRepository.findAll()).thenReturn(Arrays.asList(person_1, person_2));

        List<PersonDTO> list = underTest.findAllPersons();
        assertNotNull(list);
        assertEquals(2, list.size());

        assertEquals("personType-uuid", list.get(0).getPersonTypeId());
        assertEquals("firstName_1", list.get(0).getFirstName());
        assertEquals("lastName_1", list.get(0).getLastName());
        assertEquals("addressLine1_1", list.get(0).getAddressLine1());
        assertEquals("addressLine2_1", list.get(0).getAddressLine2());
        assertEquals("telephone_1", list.get(0).getTelephone());
        assertEquals("gsm_1", list.get(0).getGsm());
        assertEquals("email_1", list.get(0).getEmail());

        assertEquals("personType-uuid", list.get(1).getPersonTypeId());
        assertEquals("firstName_2", list.get(1).getFirstName());
        assertEquals("lastName_2", list.get(1).getLastName());
        assertEquals("addressLine1_2", list.get(1).getAddressLine1());
        assertEquals("addressLine2_2", list.get(1).getAddressLine2());
        assertEquals("telephone_2", list.get(1).getTelephone());
        assertEquals("gsm_2", list.get(1).getGsm());
        assertEquals("email_2", list.get(1).getEmail());
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
        PersonDTO _new = createPersonDTO("firstName_3", "lastName_3", "addressLine1_3", "addressLine2_3", "telephone_3", "gsm_3", "email_3");

        PersonType personType = new PersonType();
        when(personTypeRepository.findById("personType-uuid")).thenReturn(personType);

        underTest.savePerson(_new);

        ArgumentCaptor<Person> p = ArgumentCaptor.forClass(Person.class);
        verify(personRepository).saveAndFlush(p.capture());

        Person arg = p.getValue();
        assertNotNull(arg);
        assertSame(personType, arg.getType());
        assertEquals("firstName_3", arg.getFirstName());
        assertEquals("lastName_3", arg.getLastName());
        assertEquals("addressLine1_3", arg.getAddressLine1());
        assertEquals("addressLine2_3", arg.getAddressLine2());
        assertEquals("telephone_3", arg.getTelephone());
        assertEquals("gsm_3", arg.getGsm());
        assertEquals("email_3", arg.getEmail());
    }

    @Test
    public void testSavePerson_existingInstance() throws Exception {
        Person toUpdate = createPerson(UUID, "firstName_3", "lastName_3", "addressLine1_3", "addressLine2_3", "telephone_3", "gsm_3", "email_3");
        PersonDTO updated = createPersonDTO(UUID, "firstName_3", "lastName_4", "addressLine1_3", "addressLine2_3", "telephone_3", "gsm_3", "email_3");

        when(personRepository.findById(UUID)).thenReturn(toUpdate);

        PersonType personType = new PersonType();
        when(personTypeRepository.findById("personType-uuid")).thenReturn(personType);

        underTest.savePerson(updated);

        ArgumentCaptor<Person> p = ArgumentCaptor.forClass(Person.class);
        verify(personRepository).saveAndFlush(p.capture());

        Person arg = p.getValue();
        assertNotNull(arg);
        assertSame(personType, arg.getType());
        assertEquals("firstName_3", arg.getFirstName());
        assertEquals("lastName_4", arg.getLastName());
        assertEquals("addressLine1_3", arg.getAddressLine1());
        assertEquals("addressLine2_3", arg.getAddressLine2());
        assertEquals("telephone_3", arg.getTelephone());
        assertEquals("gsm_3", arg.getGsm());
        assertEquals("email_3", arg.getEmail());
    }

    @Test
    public void testDeletePerson_nonExistingInstance() throws Exception {
        when(personRepository.findById(UUID)).thenReturn(null);

        underTest.deletePerson(UUID);
    }

    @Test
    public void testDeletePerson_existingInstance() throws Exception {
        Person toDelete = createPerson(UUID, "firstName_3", "lastName_3", "addressLine1_3", "addressLine2_3", "telephone_3", "gsm_3", "email_3");

        when(personRepository.findById(UUID)).thenReturn(toDelete);

        underTest.deletePerson(UUID);

        ArgumentCaptor<Person> p = ArgumentCaptor.forClass(Person.class);
        verify(personRepository).delete(p.capture());

        Person arg = p.getValue();
        assertNotNull(arg);
        assertEquals("firstName_3", arg.getFirstName());
        assertEquals("lastName_3", arg.getLastName());
        assertEquals("addressLine1_3", arg.getAddressLine1());
        assertEquals("addressLine2_3", arg.getAddressLine2());
        assertEquals("telephone_3", arg.getTelephone());
        assertEquals("gsm_3", arg.getGsm());
        assertEquals("email_3", arg.getEmail());
    }

    private PersonDTO createPersonDTO(String firstName, String lastName, String addressLine1, String addressLine2, String telephone, String gsm, String email) {
        return createPersonDTO(null, firstName, lastName, addressLine1, addressLine2, telephone, gsm, email);
    }

    private PersonDTO createPersonDTO(String uuid, String firstName, String lastName, String addressLine1, String addressLine2, String telephone, String gsm, String email) {
        PersonDTO dto = new PersonDTO();
        dto.setId(uuid);
        dto.setPersonTypeId("personType-uuid");
        dto.setFirstName(firstName);
        dto.setLastName(lastName);
        dto.setAddressLine1(addressLine1);
        dto.setAddressLine2(addressLine2);
        dto.setTelephone(telephone);
        dto.setGsm(gsm);
        dto.setEmail(email);
        return dto;
    }

    private Person createPerson(String firstName, String lastName, String addressLine1, String addressLine2, String telephone, String gsm, String email) {
        return createPerson(null, firstName, lastName, addressLine1, addressLine2, telephone, gsm, email);
    }

    private Person createPerson(String uuid, String firstName, String lastName, String addressLine1, String addressLine2, String telephone, String gsm, String email) {
        PersonType personType = new PersonType();
        personType.setId("personType-uuid");
        personType.setShortCode("shortCode");
        personType.setName("name");

        Person person = new Person();
        person.setId(uuid);
        person.setType(personType);
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setAddressLine1(addressLine1);
        person.setAddressLine2(addressLine2);
        person.setTelephone(telephone);
        person.setGsm(gsm);
        person.setEmail(email);
        return person;
    }

}