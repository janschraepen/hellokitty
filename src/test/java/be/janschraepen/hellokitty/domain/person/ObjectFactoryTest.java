package be.janschraepen.hellokitty.domain.person;

import be.janschraepen.hellokitty.domain.persontype.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class ObjectFactoryTest {

    private static final String PERSONTYPE_ID = "personType-uuid";
    private static final String PERSONTYPE_SHORT_CODE = "shortCode";
    private static final String PERSONTYPE_NAME = "name";
    public static final String FIRST_NAME = "firstName";
    public static final String LAST_NAME = "lastName";
    public static final String ADDRESS_LINE_1 = "addressLine1";
    public static final String ADDRESS_LINE_2 = "addressLine2";
    public static final String TELEPHONE = "telephone";
    public static final String GSM = "gsm";
    public static final String EMAIL = "email";

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
    public void testCreateDTO_withEntity() throws Exception {
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
        person.setTelephone(TELEPHONE);
        person.setGsm(GSM);
        person.setEmail(EMAIL);

        PersonDTO dto = underTest.createDTO(person);
        assertNotNull(dto);
        assertEquals(PERSONTYPE_ID, dto.getPersonTypeId());
        assertEquals(FIRST_NAME, dto.getFirstName());
        assertEquals(LAST_NAME, dto.getLastName());
        assertEquals(ADDRESS_LINE_1, dto.getAddressLine1());
        assertEquals(ADDRESS_LINE_2, dto.getAddressLine2());
        assertEquals(TELEPHONE, dto.getTelephone());
        assertEquals(GSM, dto.getGsm());
        assertEquals(EMAIL, dto.getEmail());
    }

    @Test
    public void testCreateDTO_withParams() throws Exception {
        PersonDTO dto = underTest.createDTO("uuid", PERSONTYPE_ID, FIRST_NAME, LAST_NAME, ADDRESS_LINE_1, ADDRESS_LINE_2, TELEPHONE, GSM, EMAIL);
        assertNotNull(dto);
        assertEquals(PERSONTYPE_ID, dto.getPersonTypeId());
        assertEquals(FIRST_NAME, dto.getFirstName());
        assertEquals(LAST_NAME, dto.getLastName());
        assertEquals(ADDRESS_LINE_1, dto.getAddressLine1());
        assertEquals(ADDRESS_LINE_2, dto.getAddressLine2());
        assertEquals(TELEPHONE, dto.getTelephone());
        assertEquals(GSM, dto.getGsm());
        assertEquals(EMAIL, dto.getEmail());
    }

    @Test
    public void testCreateListDTOs() throws Exception {
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
        person.setTelephone(TELEPHONE);
        person.setGsm(GSM);
        person.setEmail(EMAIL);

        List<PersonDTO> dtos = underTest.createListDTOs(Arrays.asList(new Person[]{person}));
        assertNotNull(dtos);
        assertEquals(1, dtos.size(), 0);

        PersonDTO dto = dtos.get(0);
        assertNotNull(dto);
        assertEquals(PERSONTYPE_ID, dto.getPersonTypeId());
        assertEquals(FIRST_NAME, dto.getFirstName());
        assertEquals(LAST_NAME, dto.getLastName());
        assertEquals(ADDRESS_LINE_1, dto.getAddressLine1());
        assertEquals(ADDRESS_LINE_2, dto.getAddressLine2());
        assertEquals(TELEPHONE, dto.getTelephone());
        assertEquals(GSM, dto.getGsm());
        assertEquals(EMAIL, dto.getEmail());
    }

}