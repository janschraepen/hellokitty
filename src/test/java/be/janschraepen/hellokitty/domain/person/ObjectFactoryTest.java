package be.janschraepen.hellokitty.domain.person;

import be.janschraepen.hellokitty.domain.cat.Cat;
import be.janschraepen.hellokitty.domain.cat.CatDTO;
import be.janschraepen.hellokitty.domain.cat.CatPerson;
import be.janschraepen.hellokitty.domain.persontype.PersonType;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ObjectFactoryTest {

    public static final String PERSONCONTACT_ID = "personContact-uuid";
    public static final String PERSONCONTACT_VALUE = "value";

    public static final String FIRST_NAME = "firstName";
    public static final String LAST_NAME = "lastName";
    public static final String ADDRESS_LINE_1 = "addressLine1";
    public static final String ADDRESS_LINE_2 = "addressLine2";
    public static final String EXTRA_INFO = "extraInfo";

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
        Cat cat = new Cat();
        cat.setName("catName");

        PersonType personType_owner = new PersonType();
        personType_owner.setShortCode(PersonType.OWNER);
        PersonType personType_vet = new PersonType();
        personType_vet.setShortCode(PersonType.VET);

        CatPerson catPerson_owner = new CatPerson();
        catPerson_owner.setCat(cat);
        catPerson_owner.setType(personType_owner);

        CatPerson catPerson_vet = new CatPerson();
        catPerson_vet.setCat(cat);
        catPerson_vet.setType(personType_vet);

        Person person = new Person();
        person.setFirstName(FIRST_NAME);
        person.setLastName(LAST_NAME);
        person.setAddressLine1(ADDRESS_LINE_1);
        person.setAddressLine2(ADDRESS_LINE_2);
        person.setCatPersons(Arrays.asList(new CatPerson[] {catPerson_owner, catPerson_vet}));

        PersonDTO dto = underTest.createPersonDTO(person);
        assertNotNull(dto);
        assertEquals(FIRST_NAME, dto.getFirstName());
        assertEquals(LAST_NAME, dto.getLastName());
        assertEquals(ADDRESS_LINE_1, dto.getAddressLine1());
        assertEquals(ADDRESS_LINE_2, dto.getAddressLine2());

        assertNotNull(dto.getCats());
        assertEquals(1, dto.getCats().size());

        CatDTO catDto = dto.getCats().get(0);
        assertNotNull(catDto);
        assertEquals("catName", catDto.getName());
    }

    @Test
    public void testCreatePersonDTO_withParams() throws Exception {
        PersonDTO dto = underTest.createPersonDTO("uuid", FIRST_NAME, LAST_NAME, ADDRESS_LINE_1, ADDRESS_LINE_2, EXTRA_INFO);
        assertNotNull(dto);
        assertEquals(FIRST_NAME, dto.getFirstName());
        assertEquals(LAST_NAME, dto.getLastName());
        assertEquals(ADDRESS_LINE_1, dto.getAddressLine1());
        assertEquals(ADDRESS_LINE_2, dto.getAddressLine2());
        assertEquals(EXTRA_INFO, dto.getExtraInfo());
    }

    @Test
    public void testCreateListPersonDTOs() throws Exception {
        Person person = new Person();
        person.setFirstName(FIRST_NAME);
        person.setLastName(LAST_NAME);
        person.setAddressLine1(ADDRESS_LINE_1);
        person.setAddressLine2(ADDRESS_LINE_2);
        person.setExtraInfo(EXTRA_INFO);

        List<PersonDTO> dtos = underTest.createListPersonDTOs(Arrays.asList(new Person[]{person}));
        assertNotNull(dtos);
        assertEquals(1, dtos.size(), 0);

        PersonDTO dto = dtos.get(0);
        assertNotNull(dto);
        assertEquals(FIRST_NAME, dto.getFirstName());
        assertEquals(LAST_NAME, dto.getLastName());
        assertEquals(ADDRESS_LINE_1, dto.getAddressLine1());
        assertEquals(ADDRESS_LINE_2, dto.getAddressLine2());
        assertEquals(EXTRA_INFO, dto.getExtraInfo());
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