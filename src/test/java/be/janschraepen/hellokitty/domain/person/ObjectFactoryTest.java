package be.janschraepen.hellokitty.domain.person;

import be.janschraepen.hellokitty.domain.persontype.PersonType;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ObjectFactoryTest {

    public static final String PERSONTYPE_ID = "personType-uuid";
    public static final String PERSONTYPE_SHORT_CODE = "shortCode";
    public static final String PERSONTYPE_NAME = "name";

    public static final String PERSONCONTACT_ID = "personContact-uuid";
    public static final String PERSONCONTACT_VALUE = "value";

    public static final String FIRST_NAME = "firstName";
    public static final String LAST_NAME = "lastName";
    public static final String ADDRESS_LINE_1 = "addressLine1";
    public static final String ADDRESS_LINE_2 = "addressLine2";

    private ObjectFactory underTest;

    @Before
    public void setUp() throws Exception {
        underTest = ObjectFactory.getInstance();
    }

    @Test
    public void testGetInstance() throws Exception {
        assertNotNull(underTest);
        assertTrue(underTest instanceof ObjectFactory);
    }

    @Test
    public void testCreatePersonDTO_withEntity() throws Exception {
        PersonType personType = new PersonType();
        personType.setId(PERSONTYPE_ID);
        personType.setShortCode(PERSONTYPE_SHORT_CODE);
        personType.setName(PERSONTYPE_NAME);

        Person person = new Person();
        person.setType(personType);
        person.setFirstName(FIRST_NAME);
        person.setLastName(LAST_NAME);
        person.setAddressLine1(ADDRESS_LINE_1);
        person.setAddressLine2(ADDRESS_LINE_2);

        PersonDTO dto = underTest.createPersonDTO(person);
        assertNotNull(dto);
        assertEquals(PERSONTYPE_ID, dto.getPersonTypeId());
        assertEquals(PERSONTYPE_NAME, dto.getPersonType());
        assertEquals(FIRST_NAME, dto.getFirstName());
        assertEquals(LAST_NAME, dto.getLastName());
        assertEquals(ADDRESS_LINE_1, dto.getAddressLine1());
        assertEquals(ADDRESS_LINE_2, dto.getAddressLine2());
    }

    @Test
    public void testCreatePersonDTO_withParams() throws Exception {
        PersonDTO dto = underTest.createPersonDTO("uuid", PERSONTYPE_ID, FIRST_NAME, LAST_NAME, ADDRESS_LINE_1, ADDRESS_LINE_2);
        assertNotNull(dto);
        assertEquals(PERSONTYPE_ID, dto.getPersonTypeId());
        assertEquals(FIRST_NAME, dto.getFirstName());
        assertEquals(LAST_NAME, dto.getLastName());
        assertEquals(ADDRESS_LINE_1, dto.getAddressLine1());
        assertEquals(ADDRESS_LINE_2, dto.getAddressLine2());
    }

    @Test
    public void testCreateListPersonDTOs() throws Exception {
        PersonType personType = new PersonType();
        personType.setId(PERSONTYPE_ID);
        personType.setShortCode(PERSONTYPE_SHORT_CODE);
        personType.setName(PERSONTYPE_NAME);

        Person person = new Person();
        person.setType(personType);
        person.setFirstName(FIRST_NAME);
        person.setLastName(LAST_NAME);
        person.setAddressLine1(ADDRESS_LINE_1);
        person.setAddressLine2(ADDRESS_LINE_2);

        List<PersonDTO> dtos = underTest.createListPersonDTOs(Arrays.asList(new Person[]{person}));
        assertNotNull(dtos);
        assertEquals(1, dtos.size(), 0);

        PersonDTO dto = dtos.get(0);
        assertNotNull(dto);
        assertEquals(PERSONTYPE_ID, dto.getPersonTypeId());
        assertEquals(PERSONTYPE_NAME, dto.getPersonType());
        assertEquals(FIRST_NAME, dto.getFirstName());
        assertEquals(LAST_NAME, dto.getLastName());
        assertEquals(ADDRESS_LINE_1, dto.getAddressLine1());
        assertEquals(ADDRESS_LINE_2, dto.getAddressLine2());
    }

    @Test
    public void testCreatePersonContactDTO_withEntity() throws Exception {
        Person person = new Person();
        person.setId("uuid");

        PersonContact personContact = new PersonContact();
        personContact.setId(PERSONCONTACT_ID);
        personContact.setPerson(person);
        personContact.setType(ContactType.EMAIL);
        personContact.setValue(PERSONCONTACT_VALUE);

        PersonContactDTO dto = underTest.createPersonContactDTO(personContact);
        assertNotNull(dto);
        assertEquals(PERSONCONTACT_ID, dto.getId());
        assertEquals("uuid", dto.getPersonId());
        assertEquals(ContactType.EMAIL, dto.getType());
        assertEquals(PERSONCONTACT_VALUE, dto.getValue());
    }

    @Test
    public void testCreatePersonContactDTO_withParams() throws Exception {
        PersonContactDTO dto = underTest.createPersonContactDTO("uuid", ContactType.EMAIL, PERSONCONTACT_VALUE);
        assertNotNull(dto);
        assertEquals("uuid", dto.getPersonId());
        assertEquals(ContactType.EMAIL, dto.getType());
        assertEquals(PERSONCONTACT_VALUE, dto.getValue());
    }

    @Test
    public void testCreateListPersonContactDTOs() throws Exception {
        Person person = new Person();
        person.setId("uuid");

        PersonContact personContact = new PersonContact();
        personContact.setId(PERSONCONTACT_ID);
        personContact.setPerson(person);
        personContact.setType(ContactType.EMAIL);
        personContact.setValue(PERSONCONTACT_VALUE);

        List<PersonContactDTO> dtos = underTest.createListPersonContactDTOs(Arrays.asList(new PersonContact[]{personContact}));
        assertNotNull(dtos);
        assertEquals(1, dtos.size(), 0);

        PersonContactDTO dto = dtos.get(0);
        assertNotNull(dto);
        assertEquals(PERSONCONTACT_ID, dto.getId());
        assertEquals("uuid", dto.getPersonId());
        assertEquals(ContactType.EMAIL, dto.getType());
        assertEquals(PERSONCONTACT_VALUE, dto.getValue());
    }

    @Test
    public void testCreateListPersonContactDTOs_nullListReturnsEmptyList() throws Exception {
        List<PersonContactDTO> dtos = underTest.createListPersonContactDTOs(null);
        assertNotNull(dtos);
        assertEquals(0, dtos.size(), 0);
    }

}